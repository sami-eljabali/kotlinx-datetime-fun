<p align="center" >
   <img src="screenshots/logo.png" width=300px alt="SwiftDate" title="Kotlinx DateTime Fun">
 </p>

<h3 align="center"><strong>Kotlinx DateTime Fun</strong></h3>
<p align="center">Kotlinx DateTime extension functions.</p>
<p align="center">
  <a href="https://github.com/sami-eljabali/kotlinx-datetime-fun/actions?query=branch%3Amain"><img alt="Build Status" src="https://github.com/sami-eljabali/kotlinx-datetime-fun/actions/workflows/main.yml/badge.svg"/></a>
  <a href="https://repo1.maven.org/maven2/org/eljabali/sami/kotlinxdatetimefun/kotlinxdatetimefun/"><img alt="Maven Central" src="https://img.shields.io/maven-central/v/org.eljabali.sami.kotlinxdatetimefun/kotlinxdatetimefun?color=blue"/></a>
  <a href="https://kotlinlang.org"><img alt="Kotlin" src="https://img.shields.io/badge/Kotlin-2.1.21-orange.svg?style=flat&logo=kotlin"/></a>
</p> <br>

```diff
- val localDateFormat = LocalDate.Format {
-     byUnicodePattern("dd/MM/yyyy")
- }
- val date = LocalDate.parse("11/15/2024", localDateFormat)
+ val date = "11/15/2024".toLocalDate("dd/MM/yyyy")

- val dateFormat = LocalDate.Format {
-     byUnicodePattern("dd/MM/yyyy")
- }
- print(dateFormat.format(date))
+ print(date.print("MM/dd/yyyy"))

- val result = date.plus(days * -1, DateTimeUnit.DAY)
+ val result = date.minusDays(days)
```

## Features
### Parsing
_Convert strings into Kotlinx DateTime objects with ease_
```kotlin
// Provided time
val result = "01:30 AM".toLocalTime()

// Provided local date
val result = "2021-06-07".toLocalDate()

// Provided ambiguous date formats
val result = "06/07/2021".toLocalDateTime(format = "MM/dd/yyyy")
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
val date = "2021-07-06".toLocalDate()
val result = date.print(format = "MM/dd/yyyy")
```

### Attributes & Mutations
_Query and transform date/time attributes_
```kotlin
val result = date.isAtStartOfDay()
val result = date.getDaysInMonth()

val result = date.getLast(DayOfWeek.FRIDAY)
val result = date.getNext(DayOfWeek.MONDAY)
```

### Preset Dates
_Quickly access commonly used dates_
```kotlin
val result = LocalTime.now()
val result = LocalDate.startOfYear()
val result = LocalDateTime.yesterday()
```

## Installation
Add the following to your module’s `build.gradle`:
```gradle
repositories {
  mavenCentral()
}

dependencies {
  implementation("org.eljabali.sami.kotlinxdatetimefun:kotlinxdatetimefun:0.0.1")
}  
```

## Find this library useful? 😏
Star this repository __[as others have](https://github.com/sami-eljabali/kotlinx-datetime-fun/stargazers)__. ⭐️ <br>
