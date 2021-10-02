package com.mina_mikhail.base_mvvm.domain.utils

data class BaseResponse<T>(
  val result: T,
  val detail: String
)