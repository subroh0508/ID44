package datetimes

import com.soywiz.klock.DateTimeTz
import id44.mizuki.libraries.shared.extensions.toDate

@JsName("toDiffTime")
fun toDiffTime(
    dateTime: DateTimeTz,
    i18next: (String, dynamic) -> String
): String {
    val diff = (DateTimeTz.nowLocal() - dateTime)

    val option = js("{}")
    return when {
        diff.seconds < 5.0 -> i18next("commons.time.now", "0")
        diff.seconds < 60.0 -> {
            option.time = diff.seconds.toInt().toString()
            i18next("commons.time.seconds", option)
        }
        diff.minutes < 60.0 -> {
            option.time = diff.minutes.toInt().toString()
            i18next("commons.time.minutes", option)
        }
        diff.hours < 24.0 -> {
            option.time = diff.hours.toInt().toString()
            i18next("commons.time.hours", option)
        }
        diff.days < 7 -> {
            option.day = diff.days.toString()
            i18next("commons.time.days", option)
        }
        else -> {
            option.date = dateTime.toDate()
            i18next("commons.time.date", option)
        }
    }
}
