package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Int, units: TimeUnits = TimeUnits.MINUTE): Date {
    var time = this.time
    time += when (units) {
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }
    this.time = time
    return this
}

enum class TimeUnits {
    SECOND,
    MINUTE,
    HOUR,
    DAY
}

internal fun Date.humanizeDiff(): String {
    val now = Date()
    val diff = now.time - time
    if (diff <= SECOND) {
        return "только что"
    } else if (diff <= 45 * SECOND) {
        return "несколько секунд назад"
    } else if (diff <= 75 * SECOND) {
        return "минуту назад"
    } else if (diff <= 45 * MINUTE) {
        return "${diff / MINUTE} минут назад"
    } else if (diff <= 75 * MINUTE) {
        return "час назад"
    } else if (diff <= 22 * HOUR) {
        return "${diff / HOUR} часов назад"
    } else if (diff <= 26 * HOUR) {
        return "день назад"
    } else if (diff <= 360 * DAY) {
        return "${diff / DAY} дней назад"
    } else {
        return "более года назад"
    }

}