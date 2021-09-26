package com.mina_mikhail.base_mvvm.domain.auth.entity.model

data class User(
  val imagePath: String,
  val name: String,
  val refreshToken: String,
  val token: String,
  val tokenExpirationDate: String,
  val userId: String
)