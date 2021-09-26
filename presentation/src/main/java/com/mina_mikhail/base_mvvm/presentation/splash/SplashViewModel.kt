package com.mina_mikhail.base_mvvm.presentation.splash

import com.mina_mikhail.base_mvvm.domain.account.repository.AccountRepository
import com.mina_mikhail.base_mvvm.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val accountRepository: AccountRepository) : BaseViewModel() {

  fun isFirstTime() = accountRepository.isFirstTime()
}