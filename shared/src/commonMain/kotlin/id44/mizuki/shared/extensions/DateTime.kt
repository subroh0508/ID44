package id44.mizuki.shared.extensions

import com.soywiz.klock.DateFormat
import com.soywiz.klock.DateTimeTz
import com.soywiz.klock.parse

fun String.toDateTimeTz() = DateFormat(DATE_TIME_TZ_PATTERN).parse(this)

fun DateTimeTz.toDateTimeTzFormat() = format(DATE_TIME_TZ_PATTERN)
fun DateTimeTz.toDate() = format(DATE_PATTERN)

internal const val DATE_TIME_TZ_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX"
internal const val DATE_PATTERN = "yyyy/MM/dd"
