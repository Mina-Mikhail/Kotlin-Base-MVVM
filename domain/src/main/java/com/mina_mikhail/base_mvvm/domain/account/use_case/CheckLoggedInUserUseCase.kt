package com.mina_mikhail.base_mvvm.domain.account.use_case

import com.mina_mikhail.base_mvvm.domain.account.repository.AccountRepository
import javax.inject.Inject

class CheckLoggedInUserUseCase @Inject constructor(private val accountRepository: AccountRepository) {

  operator fun invoke() = accountRepository.isLoggedIn()
}