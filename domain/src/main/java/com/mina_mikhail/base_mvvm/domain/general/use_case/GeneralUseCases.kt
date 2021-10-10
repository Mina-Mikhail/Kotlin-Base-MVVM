package com.mina_mikhail.base_mvvm.domain.general.use_case

import com.mina_mikhail.base_mvvm.domain.account.use_case.CheckFirstTimeUseCase
import com.mina_mikhail.base_mvvm.domain.account.use_case.CheckLoggedInUserUseCase
import com.mina_mikhail.base_mvvm.domain.account.use_case.SetFirstTimeUseCase

class GeneralUseCases(
  val checkFirstTimeUseCase: CheckFirstTimeUseCase,
  val checkLoggedInUserUseCase: CheckLoggedInUserUseCase,
  val setFirstTimeUseCase: SetFirstTimeUseCase,
  val clearPreferencesUseCase: ClearPreferencesUseCase
)