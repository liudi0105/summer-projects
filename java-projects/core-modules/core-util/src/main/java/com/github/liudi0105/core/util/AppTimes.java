package com.github.liudi0105.core.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AppTimes {
    public static final String DEFAULT_LOCAL_DATETIME_PATTERN = "yyyy-MM--dd HH:mm:ss";

    public static LocalDateTime parseLocalDateTime(String value) {
        return LocalDateTime.parse(value, DateTimeFormatter.ofPattern(DEFAULT_LOCAL_DATETIME_PATTERN));
    }

    public static ZonedDateTime parseZonedDateTime(String value) {
        return ZonedDateTime.parse(value, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
    }

    public static OffsetDateTime parseOffsetDateTime(String value) {
        return OffsetDateTime.parse(value, DateTimeFormatter.ISO_OFFSET_DATE);
    }

    public static Instant parseInstant(String value) {
        return parseOffsetDateTime(value).toInstant();
    }

    public static LocalDateTime parseLocalDateTime(String value, String pattern) {
        return LocalDateTime.parse(value, DateTimeFormatter.ofPattern(pattern));
    }

    public static LocalTime parseLocalTime(String value, String pattern) {
        return LocalTime.parse(value, DateTimeFormatter.ofPattern(pattern));
    }

    public static LocalDate parseLocalDate(String value, String pattern) {
        return LocalDate.parse(value, DateTimeFormatter.ofPattern(pattern));
    }

    public static Instant toInstant(LocalDateTime localDateTime, ZoneId zoneId) {
        if (localDateTime == null) {
            return null;
        }
        return localDateTime.atZone(zoneId).toInstant();
    }

    public static Date toDate(LocalDateTime date, ZoneId zoneId) {
        return Date.from(date.atZone(zoneId).toInstant());
    }

    public static String format(OffsetDateTime time) {
        return format(time, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
    }

    public static String format(OffsetDateTime time, DateTimeFormatter formatter) {
        return time.format(formatter);
    }

    public static String format(Instant time, ZoneOffset zoneOffset) {
        return format(time.atOffset(zoneOffset));
    }

    public static String format(ZonedDateTime time) {
        return format(time.toOffsetDateTime());
    }

    public static String format(ZonedDateTime time, DateTimeFormatter formatter) {
        return format(time.toOffsetDateTime(), formatter);
    }

    public static String format(LocalDate time) {
        return format(time, DateTimeFormatter.ISO_LOCAL_DATE);
    }

    public static String format(LocalDate time, DateTimeFormatter formatter) {
        return time.format(formatter);
    }

    public static String format(LocalDateTime time) {
        return format(time, DateTimeFormatter.ofPattern(DEFAULT_LOCAL_DATETIME_PATTERN));
    }

    public static String format(LocalDateTime time, DateTimeFormatter formatter) {
        return time.format(formatter);
    }

    public static LocalDateTime sharpHourBefore(LocalDateTime localDateTime) {
        return localDateTime.toLocalDate().atTime(localDateTime.getHour(), 0);
    }

    public static LocalDateTime sharpHourAfter(LocalDateTime localDateTime) {
        return sharpHourBefore(localDateTime).plusHours(1);
    }
}
