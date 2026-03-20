<p align="center" >
   <img src="screenshots/logo.png" width=300px alt="SwiftDate" title="Kotlinx DateTime Fun">
 </p>

<h1 align="center"><strong>Kotlinx DateTime Fun</strong></h1>
<p align="center">Every kotlinx.datetime extension function you'll ever need.</p>
<p align="center">
  <a href="https://github.com/sami-eljabali/kotlinx-datetime-fun/actions?query=branch%3Amain"><img alt="Build Status" src="https://github.com/sami-eljabali/kotlinx-datetime-fun/actions/workflows/main.yml/badge.svg"/></a>
  <a href="https://repo1.maven.org/maven2/org/eljabali/sami/kotlinxdatetimefun/kotlinxdatetimefun/"><img alt="Maven Central" src="https://img.shields.io/maven-central/v/org.eljabali.sami.kotlinxdatetimefun/kotlinxdatetimefun?color=blue"/></a>
  <a href="https://kotlinlang.org"><img alt="Kotlin" src="https://img.shields.io/badge/Kotlin-2.3.20-orange.svg?style=flat&logo=kotlin"/></a>
</p> <br>

```diff
- val localDateFormat = LocalDate.Format {
-     byUnicodePattern("dd/MM/yyyy")
- }
- val date = LocalDate.parse("11/15/2024", localDateFormat)
+ val date = "11/15/2024".toLocaldate("dd/MM/yyyy")

- val dateFormat = LocalDate.Format {
-     byUnicodePattern("dd/MM/yyyy")
- }
- print(dateFormat.format(date))
+ print(date.print("MM/dd/yyyy"))

- val result = date.plus(days * -1, DateTimeUnit.DAY)
+ val result = date.minusDays(days)
```

## Platforms
JVM · Android · iOS · JS (browser & Node.js)

## Features
### Parsing
_Convert strings into [Kotlinx DateTime](https://github.com/Kotlin/kotlinx-datetime) objects with ease_
```kotlin
val result = "01:30 AM".toLocaltime()
val result = "2021-06-07".toLocaldate()
val result = "06/07/2021".toLocaldate("MM/dd/yyyy")
val result = "06/07/2021".toLocaldate("MM/dd/yyyy", "yyyy-MM-dd") // fall back parsing formats
val result = "2024-11-15T12:34:56.123456Z".toLocalDatetime() // handles fractional seconds that Kotlinx DateTime doesn't
```

### Comparisons
_Compare dates and times at various granularities_
```kotlin
// Year
val result = dateA.compareYear(dateB)
val result = dateA.isBeforeYear(dateB)

// Month
val result = dateA.compareMonth(dateB)
val result = dateA.getMonthDifference(dateB)
val result = dateA.isEqualMonth(dateB)

// Day
val result = dateA.compareDay(dateB)
val result = dateA.getDayDifference(dateB)
val result = dateA.isAfterEqualDay(dateB)

// Time
val result = dateA.compareTime(dateB)
val result = dateA.getMinuteDifference(dateB)
val result = dateA.isAfterEqualTime(dateB)
```

### Formatting
_Print dates and times using a custom format_
```kotlin
val date = "2021-07-06".toLocaldate()
val result = date.print("MM/dd/yyyy")
```

### Attributes
_Query date/time properties_
```kotlin
// LocalDate
val result = date.isWeekend()
val result = date.isWeekday()
val result = date.isInLeapYear()
val result = date.getDaysInMonth()
val result = date.getMonthBaseZero()

// LocalTime
val result = time.isAtStartOfDay()
val result = time.isAtEndOfDay()
val result = time.isAtNoon()
val result = time.isInAm()
val result = time.isInPm()
```

### Mutations
_Add, subtract, and set fields_
```kotlin
// Date arithmetic
val result = date.plusSeconds(1)
val result = date.minusMinutes(2)
val result = date.plusDays(3)

// Time arithmetic
val result = time.plusHours(3)
val result = time.minusMinutes(15)

// Field setters
val result = date.withYear(2025)
val result = date.withMonth(6)
val result = dateTime.withHour(9)

// Boundaries
val result = date.atStartOfDay()
val result = date.atEndOfDay()
val result = date.atStartOfMonth()
val result = date.atEndOfMonth()

// Navigation
val result = date.getLast(DayOfWeek.FRIDAY)
val result = date.getNext(DayOfWeek.MONDAY)
```

### Time Zone Conversion
_Convert between time zones_
```kotlin
val result = dateTime.fromZoneToZone(ZoneIds.AMERICA_NEW_YORK, ZoneIds.ASIA_TOKYO)
val result = dateTime.fromUtcToZone(ZoneIds.EUROPE_LONDON)
val result = dateTime.fromZoneToUtc(ZoneIds.AMERICA_LOS_ANGELES)
```

### Preset Dates
_Quickly access commonly used dates_
```kotlin
// Now
val result = LocalTime.now()
val result = LocalDate.now()
val result = LocalDateTime.now()
val result = LocalDateTime.now(ZoneIds.AMERICA_LOS_ANGELES)

// Relative days
val result = LocalDate.yesterday()
val result = LocalDate.today()
val result = LocalDate.tomorrow()

// Day-of-week shortcuts
val result = LocalDate.lastFriday()
val result = LocalDate.nextMonday()
val result = LocalDateTime.lastSunday()
val result = LocalDateTime.nextWednesday()

// Period boundaries
val result = LocalDate.startOfYear()
val result = LocalDate.endOfYear()
val result = LocalDate.startOfMonth()
val result = LocalDate.endOfMonth()
```

## Installation
Add the following to your module's `build.gradle`:
```gradle
repositories {
  mavenCentral()
}

dependencies {
  implementation("org.eljabali.sami.kotlinxdatetimefun:kotlinxdatetimefun:0.0.7")
}
```

## 😏 Find this library useful?
Star this repository __[as others have](https://github.com/sami-eljabali/kotlinx-datetime-fun/stargazers)__. ⭐️ <br>

## 🍪 Extras
* [DateTimeFormats](https://gist.github.com/sami-eljabali/d9476427db7f39645d8f94f8a0707751) to help with day to day printing/parsing!
* [Java Time Fun](https://github.com/sami-eljabali/java-time-fun) library for JVM based projects.
