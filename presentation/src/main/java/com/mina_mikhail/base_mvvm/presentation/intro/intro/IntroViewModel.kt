package com.mina_mikhail.base_mvvm.presentation.intro.intro

import com.mina_mikhail.base_mvvm.domain.account.repository.AccountRepository
import com.mina_mikhail.base_mvvm.presentation.base.BaseViewModel
import com.mina_mikhail.base_mvvm.presentation.base.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class IntroViewModel @Inject constructor(private val accountRepository: AccountRepository) : BaseViewModel() {

  val openLogIn: SingleLiveEvent<Void> = SingleLiveEvent()

  fun onLogInClicked() {
    openLogIn.call()
  }

  fun setFirstTime(isFirstTime: Boolean) = accountRepository.setFirstTime(isFirstTime)
}