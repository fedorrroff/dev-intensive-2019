package ru.skillbranch.devintensive.extensions

import java.lang.Math.abs
import java.text.SimpleDateFormat
import java.util.*

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern: String="HH:mm:ss dd.MM.yy"):String {
	val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
	return dateFormat.format(this)
}

fun Date.add(value: Int, units: TimeUnits = TimeUnits.SECOND): Date{
	var time = this.time

	time += when(units) {
		TimeUnits.SECOND -> value * SECOND
		TimeUnits.MINUTE -> value * MINUTE
		TimeUnits.HOUR -> value * HOUR
		TimeUnits.DAY -> value * DAY
	}

	this.time = time
	return this
}

fun Date.humanizeDiff(date: Date = Date()): String {
	val timeDiff = this.time - date.time
	val time: String
	var prefix = ""
	var postfix = ""
	if (timeDiff <= 0) postfix = " назад" else prefix = "через "

	fun plur(value: Long, unit: TimeUnits = TimeUnits.SECOND): String{
		var result = ""

		if (unit == TimeUnits.MINUTE) {
			when (value % 10) {
				in 2..4 -> result = "$value минуты"
				in 5..9 -> result = "$value минут"
				in 1..1 -> result = "$value минуту"
				in 0..0 -> result = "$value минут"
			}
		}
		if (unit == TimeUnits.HOUR) {
			when (value % 10) {
				in 2..4 -> result = "$value часа"
				in 5..9 -> result = "$value часов"
				in 1..1 -> result = "$value час"
				in 0..0 -> result = "$value часов"
			}
		}
		if (unit == TimeUnits.DAY) {
			when (value % 10) {
				in 2..4 -> result = "$value дня"
				in 5..9 -> result = "$value дней"
				in 1..1 -> result = "$value день"
				in 0..0 -> result = "$value дней"
			}
		}
		return result
	}

	when (abs(timeDiff)) {
		in 0..1  -> time = "только что"
		in 1..45 -> time = prefix + "несколько секунд" + postfix
		in 45..75  -> time = prefix + "минуту" + postfix
		in 75..45 * MINUTE -> time = prefix + plur(abs(timeDiff / MINUTE), unit = TimeUnits.MINUTE) + postfix
		in 45 * MINUTE..75 * MINUTE -> time = prefix + "час" + postfix
		in 75 * MINUTE..22 * HOUR -> time = prefix + plur(abs(timeDiff / HOUR), unit = TimeUnits.HOUR) + postfix
		in 22 * HOUR..26 * HOUR -> time = prefix + "день" + postfix
		in 26 * HOUR..360 * DAY -> time = prefix + plur(abs(timeDiff / DAY), unit = TimeUnits.DAY) + postfix
		else -> if (timeDiff <= 0) time = "более года назад" else time = "более чем через год"
	}
	return time
}


enum class TimeUnits{
	SECOND {
		override fun plural(value: Int): String {
			var result = ""

			when (value % 10){
				in 2..4 -> result = "$value секунды"
				in 5..9 -> result = "$value секунд"
				1 -> result = "$value секунду"
				0 -> result = "$value секунд"
			}
			return result
		}
	},
	MINUTE {
		override fun plural(value: Int): String {
			var result = ""
			when (value % 10){
				in 2..4 -> result = "$value минуты"
				in 5..9 -> result = "$value минут"
				1 -> result = "$value минуту"
				0 -> result = "$value минут"
			}
			return result
		}
	},
	HOUR {
		override fun plural(value: Int): String {
			var result = ""
			when (value % 10){
				in 2..4 -> result = "$value часа"
				in 5..9 -> result = "$value часов"
				1 -> result = "$value час"
				0 -> result = "$value часов"
			}
			return result		}
	},
	DAY {
		override fun plural(value: Int): String {
			var result = ""
			when (value % 10){
				in 2..4 -> result = "$value дня"
				in 5..9 -> result = "$value дней"
				1 -> result = "$value день"
				0 -> result = "$value дней"
			}
			return result
		}
	};

	abstract fun plural(value: Int): String
}
