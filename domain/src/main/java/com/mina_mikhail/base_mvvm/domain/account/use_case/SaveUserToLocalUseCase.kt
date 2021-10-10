package com.mina_mikhail.base_mvvm.domain.account.use_case

import com.mina_mikhail.base_mvvm.domain.account.repository.AccountRepository
import com.mina_mikhail.base_mvvm.domain.auth.entity.model.User
import javax.inject.Inject

class SaveUserToLocalUseCase @Inject constructor(private val accountRepository: AccountRepository) {

  operator fun invoke(user: User) = accountRepository.saveUserToLocal(user)
}