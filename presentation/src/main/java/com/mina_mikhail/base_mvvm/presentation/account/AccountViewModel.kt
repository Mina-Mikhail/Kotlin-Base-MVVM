package com.mina_mikhail.base_mvvm.presentation.account

import androidx.lifecycle.liveData
import com.mina_mikhail.base_mvvm.domain.account.repository.AccountRepository
import com.mina_mikhail.base_mvvm.domain.utils.Resource.Loading
import com.mina_mikhail.base_mvvm.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(private val accountRepository: AccountRepository) : BaseViewModel() {

  fun logOut() = liveData(Dispatchers.IO) {
    emit(Loading)
    emit(accountRepository.logOut())
  }

  fun clearPreferences() = accountRepository.clearPreferences()
}