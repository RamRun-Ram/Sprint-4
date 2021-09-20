package ru.sber.datetime


import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.Month
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjusters
import java.util.*


// 1.
fun getZonesWithNonDivisibleByHourOffset(): Set<String> {
    val listTimeZoneWithOffset: ArrayList<String> = ArrayList()
    for (zone in TimeZone.getAvailableIDs()) {
        val timeZone = TimeZone.getTimeZone(zone)
        if (timeZone.rawOffset % 3600000 != 0) listTimeZoneWithOffset.add(zone)
    }
    listTimeZoneWithOffset.removeIf { it.length == 3 }
    return listTimeZoneWithOffset.toSet()
}

// 2.
fun getLastInMonthDayWeekList(year: Int): List<String> {
    val listLastInMonthDayWeek = mutableListOf<String>()
    for (month in Month.values()) {
        val date = LocalDate.of(year, month, 1)
        val lastDayOfNextMonth = date.with(TemporalAdjusters.lastDayOfMonth())
        val lastInMonthDayWeek = lastDayOfNextMonth.dayOfWeek.toString()
        listLastInMonthDayWeek.add(lastInMonthDayWeek)
    }
    return listLastInMonthDayWeek
}

// 3.
fun getNumberOfFridayThirteensInYear(year: Int): Int {
    var numberOfFridayThirteensInYear = 0
    for (month in Month.values()) {
        val date = LocalDate.of(year, month, 13)
        val dateOfThirteens = date.dayOfWeek
        if (dateOfThirteens == DayOfWeek.FRIDAY) {
            ++numberOfFridayThirteensInYear
        }
    }
    return numberOfFridayThirteensInYear
}

// 4.
fun getFormattedDateTime(dateTime: LocalDateTime): String {
    var dateForUS = dateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy, H:mm").withLocale(Locale.US))
    return dateForUS
}


