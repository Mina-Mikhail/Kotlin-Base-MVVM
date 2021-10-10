package com.mina_mikhail.base_mvvm.domain.general.use_case

import com.mina_mikhail.base_mvvm.domain.account.repository.AccountRepository
import javax.inject.Inject

class ClearPreferencesUseCase @Inject constructor(private val accountRepository: AccountRepository) {

  operator fun invoke() = accountRepository.clearPreferences()
}