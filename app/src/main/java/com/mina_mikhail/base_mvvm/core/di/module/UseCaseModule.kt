package com.mina_mikhail.base_mvvm.core.di.module

import com.mina_mikhail.base_mvvm.domain.account.repository.AccountRepository
import com.mina_mikhail.base_mvvm.domain.account.use_case.AccountUseCases
import com.mina_mikhail.base_mvvm.domain.account.use_case.CheckFirstTimeUseCase
import com.mina_mikhail.base_mvvm.domain.account.use_case.CheckLoggedInUserUseCase
import com.mina_mikhail.base_mvvm.domain.account.use_case.LogOutUseCase
import com.mina_mikhail.base_mvvm.domain.account.use_case.SaveUserToLocalUseCase
import com.mina_mikhail.base_mvvm.domain.account.use_case.SendFirebaseTokenUseCase
import com.mina_mikhail.base_mvvm.domain.account.use_case.SetFirstTimeUseCase
import com.mina_mikhail.base_mvvm.domain.auth.repository.AuthRepository
import com.mina_mikhail.base_mvvm.domain.auth.use_case.LogInUseCase
import com.mina_mikhail.base_mvvm.domain.general.use_case.ClearPreferencesUseCase
import com.mina_mikhail.base_mvvm.domain.general.use_case.GeneralUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

  @Provides
  @Singleton
  fun provideLogInUseCase(
    authRepository: AuthRepository,
    saveUserToLocalUseCase: SaveUserToLocalUseCase
  ): LogInUseCase = LogInUseCase(authRepository, saveUserToLocalUseCase)

  @Provides
  @Singleton
  fun provideCheckFirstTimeUseCase(
    accountRepository: AccountRepository
  ): CheckFirstTimeUseCase = CheckFirstTimeUseCase(accountRepository)

  @Provides
  @Singleton
  fun provideCheckLoggedInUserUseCase(
    accountRepository: AccountRepository
  ): CheckLoggedInUserUseCase = CheckLoggedInUserUseCase(accountRepository)

  @Provides
  @Singleton
  fun provideSetFirstTimeUseCase(
    accountRepository: AccountRepository
  ): SetFirstTimeUseCase = SetFirstTimeUseCase(accountRepository)

  @Provides
  @Singleton
  fun provideGeneralUseCases(
    checkFirstTimeUseCase: CheckFirstTimeUseCase,
    checkLoggedInUserUseCase: CheckLoggedInUserUseCase,
    setFirstTimeUseCase: SetFirstTimeUseCase,
    clearPreferencesUseCase: ClearPreferencesUseCase
  ): GeneralUseCases =
    GeneralUseCases(checkFirstTimeUseCase, checkLoggedInUserUseCase, setFirstTimeUseCase, clearPreferencesUseCase)

  @Provides
  @Singleton
  fun provideLogOutUseCase(
    accountRepository: AccountRepository
  ): LogOutUseCase = LogOutUseCase(accountRepository)

  @Provides
  @Singleton
  fun provideSendFirebaseTokenUseCase(
    accountRepository: AccountRepository
  ): SendFirebaseTokenUseCase = SendFirebaseTokenUseCase(accountRepository)

  @Provides
  @Singleton
  fun provideSaveUserToLocalUseCase(
    accountRepository: AccountRepository
  ): SaveUserToLocalUseCase = SaveUserToLocalUseCase(accountRepository)

  @Provides
  @Singleton
  fun provideClearPreferencesUseCase(
    accountRepository: AccountRepository
  ): ClearPreferencesUseCase = ClearPreferencesUseCase(accountRepository)

  @Provides
  @Singleton
  fun provideAccountUseCases(
    logOutUseCase: LogOutUseCase,
    sendFirebaseTokenUseCase: SendFirebaseTokenUseCase
  ): AccountUseCases = AccountUseCases(logOutUseCase, sendFirebaseTokenUseCase)
}