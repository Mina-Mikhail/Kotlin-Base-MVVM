package com.mina_mikhail.base_mvvm.presentation.base.utils

import android.text.format.DateUtils
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class DateUtils {
  companion object {
    const val API_DATE_FORMAT = "yyyy-MM-dd"
    const val UI_DATE_FORMAT = "dd-MM-yyyy"
    const val FULL_DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss"
    const val MONTH_UI_DATE_FORMAT = "dd MMM yyyy"
    const val DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss"
    const val TIME_12_FORMAT = "hh:mm a"
    const val TIME_24_FORMAT = "HH:mm"
    const val TIME_24_FORMAT_WITH_SECONDS = "HH:mm:ss"
    const val TIME_HOUR_ONLY = "HH"
    const val TIME_MINUTE_ONLY = "mm"
    const val DATE_TIME_12_FORMAT = "yyyy-MM-dd hh:mm a"
    const val DATE_TIME_UI_FORMAT = "dd MMM yyyy, hh:mm a"
    const val DAY_NAME = "EEEE"
    const val SHORT_DAY_NAME = "E"
    const val DATE_WITH_DAY_NAME = "EEE, dd MMM yyyy"
    const val HOUR = "HH"
    const val MINUTE = "mm"
    const val DAY = "dd"
    const val MONTH = "MM"
    const val YEAR = "yyyy"
  }
}

fun String.changeDataFormat(oldFormat: String, newFormat: String): String {
  val formatView = SimpleDateFormat(oldFormat, getLocale())
  val newFormatView = SimpleDateFormat(newFormat, getLocale())
  var dateObj: Date? = null
  try {
    dateObj = formatView.parse(this)
  } catch (e: ParseException) {
    e.printStackTrace()
  }

  return if (dateObj == null) "" else newFormatView.format(dateObj)
}

fun String.convertDateTimeToTimesAgo(format: String): String {
  val inputFormat = SimpleDateFormat(format, getLocale())
  val date: Date?
  return try {
    date = inputFormat.parse(this)

    // the new date style
    DateUtils.getRelativeTimeSpanString(
      date.time,
      Calendar.getInstance().timeInMillis,
      DateUtils.MINUTE_IN_MILLIS
    ) as String
  } catch (e: Exception) {
    e.printStackTrace()
    ""
  }
}

fun getLocale(): Locale? {
  return when (Locale.getDefault().language) {
    "en" -> {
      Locale.ENGLISH
    }
    "ar" -> {
      Locale("ar")
    }
    "fr" -> {
      Locale.FRENCH
    }
    else -> {
      Locale.ENGLISH
    }
  }
}