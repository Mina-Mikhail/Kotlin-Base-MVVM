package com.mina_mikhail.base_mvvm.presentation.account

import android.content.Context
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.messaging.FirebaseMessaging
import com.mina_mikhail.base_mvvm.domain.account.entity.request.SendFirebaseTokenRequest
import com.mina_mikhail.base_mvvm.domain.account.repository.AccountRepository
import com.mina_mikhail.base_mvvm.domain.utils.Resource.Loading
import com.mina_mikhail.base_mvvm.domain.utils.Resource.Success
import com.mina_mikhail.base_mvvm.presentation.base.BaseViewModel
import com.mina_mikhail.base_mvvm.presentation.base.utils.getDeviceId
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(private val accountRepository: AccountRepository) : BaseViewModel() {

  fun sendFirebaseToken(context: Context) {
    if (accountRepository.isLoggedIn() &&
      (
        accountRepository.getFirebaseToken() == null ||
          accountRepository.getFirebaseToken()?.isEmpty() == true
        )
    ) {
      FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
        if (task.isSuccessful) {
          val request = SendFirebaseTokenRequest()
          request.deviceType = "android"
          request.deviceId = getDeviceId(context)
          request.token = task.result

          viewModelScope.launch {
            accountRepository.sendFirebaseToken(request).let {
              when (it) {
                is Success -> {
                  accountRepository.saveFirebaseTokenToLocal(request.token)
                }
              }
            }
          }
        }
      }
    }
  }

  fun logOut() = liveData(Dispatchers.IO) {
    emit(Loading)
    emit(accountRepository.logOut())
  }

  fun clearPreferences() = accountRepository.clearPreferences()
}