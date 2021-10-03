package com.mina_mikhail.base_mvvm.presentation.account

import androidx.lifecycle.viewModelScope
import com.mina_mikhail.base_mvvm.domain.account.use_case.AccountUseCases
import com.mina_mikhail.base_mvvm.domain.utils.BaseResponse
import com.mina_mikhail.base_mvvm.domain.utils.Resource
import com.mina_mikhail.base_mvvm.presentation.base.BaseViewModel
import com.mina_mikhail.base_mvvm.presentation.base.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(private val accountUseCases: AccountUseCases) : BaseViewModel() {

  private val _logOuResponse = MutableStateFlow<Resource<BaseResponse<Boolean>>>(Resource.Default)
  val logOutResponse = _logOuResponse

  val showLogOutPopUp = SingleLiveEvent<Void>()

  fun logOut() {
    accountUseCases.logOutUseCase()
      .onEach { result ->
        _logOuResponse.value = result
      }
      .launchIn(viewModelScope)
  }

  fun onLogOutClicked() {
    showLogOutPopUp.call()
  }
}