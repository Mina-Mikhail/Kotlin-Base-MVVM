package com.mina_mikhail.base_mvvm.data.local.preferences

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.mina_mikhail.base_mvvm.domain.auth.entity.model.User
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AppPreferences @Inject constructor(@ApplicationContext private val context: Context) {

  companion object {
    private const val APP_PREFERENCES_NAME = "APP-NAME-Cache"
    private const val SESSION_PREFERENCES_NAME = "APP-NAME-UserCache"
    private const val MODE = Context.MODE_PRIVATE

    private val USER_DATA = Pair("USER_DATA", "")
    private val FIRST_TIME = Pair("FIRST_TIME", true)
    private val FIREBASE_TOKEN = Pair("FIREBASE_TOKEN", "")
  }

  private val appPreferences: SharedPreferences = context.getSharedPreferences(APP_PREFERENCES_NAME, MODE)
  private val sessionPreferences: SharedPreferences =
    context.getSharedPreferences(SESSION_PREFERENCES_NAME, MODE)

  /**
   * SharedPreferences extension function, so we won't need to call edit() and apply()
   * ourselves on every SharedPreferences operation.
   */
  private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
    val editor = edit()
    operation(editor)
    editor.apply()
  }

  var userData: User?
    get() {
      val value: String? = sessionPreferences.getString(USER_DATA.first, USER_DATA.second)
      return Gson().fromJson(value, User::class.java)
    }
    set(value) = sessionPreferences.edit {
      it.putString(USER_DATA.first, Gson().toJson(value))
    }

  val userToken: String?
    get() {
      val value: String? = sessionPreferences.getString(USER_DATA.first, USER_DATA.second)
      val user = Gson().fromJson(value, User::class.java)

      return if (user == null || user.token.isEmpty()) {
        null
      } else {
        user.token
      }
    }

  val isLoggedIn: Boolean
    get() {
      val value: String? = sessionPreferences.getString(USER_DATA.first, USER_DATA.second)
      val user = Gson().fromJson(value, User::class.java)

      return !(user == null || user.token.isEmpty())
    }

  var isFirstTime: Boolean
    get() {
      return appPreferences.getBoolean(FIRST_TIME.first, FIRST_TIME.second)
    }
    set(value) = appPreferences.edit {
      it.putBoolean(FIRST_TIME.first, value)
    }

  var firebaseToken: String?
    get() {
      return sessionPreferences.getString(FIREBASE_TOKEN.first, FIREBASE_TOKEN.second)
    }
    set(value) = sessionPreferences.edit {
      it.putString(FIREBASE_TOKEN.first, value)
    }

  fun clearPreferences() {
    sessionPreferences.edit {
      it.clear().apply()
    }
  }
}