package com.mina_mikhail.base_mvvm.domain.auth.enums

enum class AuthFieldsValidation(val value: Int) {
  EMPTY_EMAIL(1),
  INVALID_EMAIL(2),
  EMPTY_PASSWORD(3)
}