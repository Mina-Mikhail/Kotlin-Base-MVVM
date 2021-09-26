package com.mina_mikhail.base_mvvm.data.auth.data_source.remote

import com.mina_mikhail.base_mvvm.domain.auth.entity.model.User
import com.mina_mikhail.base_mvvm.domain.auth.entity.request.LogInRequest
import com.mina_mikhail.base_mvvm.domain.utils.BaseResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthServices {

  @POST("Account/Login")
  suspend fun logIn(@Body request: LogInRequest): BaseResponse<User>
}