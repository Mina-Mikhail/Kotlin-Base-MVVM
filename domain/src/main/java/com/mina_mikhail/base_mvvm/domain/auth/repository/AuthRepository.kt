package com.mina_mikhail.base_mvvm.domain.auth.repository

import com.mina_mikhail.base_mvvm.domain.auth.entity.model.User
import com.mina_mikhail.base_mvvm.domain.auth.entity.request.LogInRequest
import com.mina_mikhail.base_mvvm.domain.utils.BaseResponse
import com.mina_mikhail.base_mvvm.domain.utils.Resource

interface AuthRepository {

  suspend fun logIn(request: LogInRequest): Resource<BaseResponse<User>>
}