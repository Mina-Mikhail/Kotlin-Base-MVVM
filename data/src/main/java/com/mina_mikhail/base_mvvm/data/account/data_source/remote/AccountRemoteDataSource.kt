package com.mina_mikhail.base_mvvm.data.account.data_source.remote

import com.mina_mikhail.base_mvvm.data.remote.BaseRemoteDataSource
import com.mina_mikhail.base_mvvm.domain.account.entity.request.SendFirebaseTokenRequest
import javax.inject.Inject

class AccountRemoteDataSource @Inject constructor(private val apiService: AccountServices) :
  BaseRemoteDataSource() {

  suspend fun sendFirebaseToken(request: SendFirebaseTokenRequest) = safeApiCall {
    apiService.sendFirebaseToken(request)
  }

  suspend fun logOut() = safeApiCall {
    apiService.logOut()
  }
}