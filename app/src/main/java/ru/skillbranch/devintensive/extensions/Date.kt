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

internal fun TimeUnits.SECOND.plural(value: Int): String {
    return plural(value, "cекунд")
}

internal fun TimeUnits.MINUTE.plural(value: Int): String {
    return plural(value, "минут")
}

private fun plural(value: Int, time: String): String {
    return when (value) {
        0 -> "$value $time"
        value > 10 && value < 20 -> "$value $time"
        value % 10 == 0 -> "$value $time"
        value % 10 == 1 -> "$value ${time}у"
        else -> "$value ${time}ы"
    }
}


internal fun TimeUnits.HOUR.plural(value: Int): String {
    return  return when (value) {
        0 -> "$value часов"
        value > 10 && value < 20 -> "$value часов"
        value % 10 == 0 -> "$value часов"
        value % 10 == 1 -> "$value час"
        else -> "$value часа"
    }
}

internal fun TimeUnits.DAY.plural(value: Int): String {
    return return  return when (value) {
        0 -> "$value дней"
        value > 10 && value < 20 -> "$value дней"
        value % 10 == 0 -> "$value дней"
        value % 10 == 1 -> "$value день"
        else -> "$value дня"
    }
}