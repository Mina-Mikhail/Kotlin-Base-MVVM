package com.mina_mikhail.base_mvvm.data.account.repository

import com.mina_mikhail.base_mvvm.data.account.data_source.remote.AccountRemoteDataSource
import com.mina_mikhail.base_mvvm.data.local.preferences.AppPreferences
import com.mina_mikhail.base_mvvm.domain.account.entity.request.SendFirebaseTokenRequest
import com.mina_mikhail.base_mvvm.domain.account.repository.AccountRepository
import com.mina_mikhail.base_mvvm.domain.auth.entity.model.User
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
  private val remoteDataSource: AccountRemoteDataSource,
  private val appPreferences: AppPreferences
) : AccountRepository {

  override
  suspend fun sendFirebaseToken(request: SendFirebaseTokenRequest) = remoteDataSource.sendFirebaseToken(request)

  override
  suspend fun logOut() = remoteDataSource.logOut()

  override
  fun isFirstTime() = appPreferences.isFirstTime

  override
  fun isLoggedIn() = appPreferences.isLoggedIn

  override
  fun saveFirebaseTokenToLocal(firebaseToken: String) {
    appPreferences.firebaseToken = firebaseToken
  }

  override
  fun getFirebaseToken() = appPreferences.firebaseToken

  override
  fun saveUserToLocal(user: User) {
    appPreferences.userData = user
  }

  override
  fun setFirstTime(isFirstTime: Boolean) {
    appPreferences.isFirstTime = isFirstTime
  }

  override
  fun clearPreferences() = appPreferences.clearPreferences()
}