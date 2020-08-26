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
    DAY;

    fun plural(value: Int): String {
        return when (this) {
            TimeUnits.SECOND -> plural(value, "секунд")
            TimeUnits.MINUTE -> plural(value, "минут")
            TimeUnits.HOUR -> pluralHour(value)
            TimeUnits.DAY -> pluralDay(value)
        }
    }

    private fun plural(value: Int, time: String): String {
        return when {
            value in 11..19 -> "$value $time"
            value % 100 in 11..19 -> "$value $time"
            value % 10 == 0 -> "$value $time"
            value % 10 == 1 -> "$value ${time}у"
            value % 10 in 2..4 -> "$value ${time}ы"
            else -> "$value $time"
        }
    }

    private fun pluralHour(value: Int): String {
        return when {
            value in 11..19 -> "$value часов"
            value % 100 in 11..19 -> "$value часов"
            value % 10 == 0 -> "$value часов"
            value % 10 == 1 -> "$value час"
            value % 10 in 2..4 -> "$value часа"
            else -> "$value часов"
        }
    }

    private fun pluralDay(value: Int): String {
        return when {
            value in 11..19 -> "$value дней"
            value  % 100 in 11..19 -> "$value дней"
            value % 10 == 0 -> "$value дней"
            value % 10 == 1 -> "$value день"
            value % 10 in 2..4 -> "$value дня"
            else -> "$value дней"
        }
    }
}

internal fun Date.humanizeDiff(currDate: Date): String {
    val diff = currDate.time - time
    when {
        diff <= SECOND -> {
            return "только что"
        }
        diff <= 45 * SECOND -> {
            return "несколько секунд назад"
        }
        diff <= 75 * SECOND -> {
            return "минуту назад"
        }
        diff <= 45 * MINUTE -> {
            return "${diff / MINUTE} минут назад"
        }
        diff <= 75 * MINUTE -> {
            return "час назад"
        }
        diff <= 22 * HOUR -> {
            return "${diff / HOUR} часов назад"
        }
        diff <= 26 * HOUR -> {
            return "день назад"
        }
        diff <= 360 * DAY -> {
            return "${diff / DAY} дней назад"
        }
        else -> {
            return "более года назад"
        }
    }
}

