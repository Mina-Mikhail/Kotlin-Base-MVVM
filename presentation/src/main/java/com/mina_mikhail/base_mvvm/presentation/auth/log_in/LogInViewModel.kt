package com.mina_mikhail.base_mvvm.presentation.auth.log_in

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mina_mikhail.base_mvvm.domain.account.repository.AccountRepository
import com.mina_mikhail.base_mvvm.domain.auth.entity.model.User
import com.mina_mikhail.base_mvvm.domain.auth.entity.request.LogInRequest
import com.mina_mikhail.base_mvvm.domain.auth.enums.AuthFieldsValidation
import com.mina_mikhail.base_mvvm.domain.auth.repository.AuthRepository
import com.mina_mikhail.base_mvvm.domain.utils.BaseResponse
import com.mina_mikhail.base_mvvm.domain.utils.Resource
import com.mina_mikhail.base_mvvm.presentation.base.BaseViewModel
import com.mina_mikhail.base_mvvm.presentation.base.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogInViewModel @Inject constructor(
  private val authRepository: AuthRepository,
  private val accountRepository: AccountRepository
) : BaseViewModel() {

  var request: LogInRequest = LogInRequest()
  var logInResponse: MutableLiveData<Resource<BaseResponse<User>>> = MutableLiveData()

  var togglePassword: SingleLiveEvent<Void> = SingleLiveEvent()
  var openForgotPassword: SingleLiveEvent<Void> = SingleLiveEvent()

  var onInvalidField: SingleLiveEvent<AuthFieldsValidation> = SingleLiveEvent()

  fun onPasswordToggleClicked() {
    togglePassword.call()
  }

  fun onForgotPasswordClicked() {
    openForgotPassword.call()
  }

  fun onLogInClicked() {
    if (request.email.isEmpty()) {
      onInvalidField.value = AuthFieldsValidation.EMPTY_EMAIL
      return
    }

    if (request.password.isEmpty()) {
      onInvalidField.value = AuthFieldsValidation.EMPTY_PASSWORD
      return
    }

    viewModelScope.launch {
      logInResponse.value = Resource.Loading
      logInResponse.value = authRepository.logIn(request)
    }
  }

  fun saveUserToLocal(user: User) {
    accountRepository.saveUserToLocal(user)
  }
}