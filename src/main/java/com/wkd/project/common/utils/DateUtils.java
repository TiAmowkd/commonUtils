package com.wkd.project.common.utils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

/**
 * @author wkd
 * @version 1.0.0
 * @className DateUtils.java
 * @description 日期时间通用类
 * @createTime 2021-06-07 11:20
 */
public class DateUtils {

    private static final DateTimeFormatter FORMATTER_YMD = DateTimeFormatter.ofPattern("yyyyMMdd");

    public static final DateTimeFormatter FORMATTER_Y_M_D = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static final DateTimeFormatter FORMATTER_YMD_HMS = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * 获取 UTC 时间
     *
     * @return
     */
    public static Date getUTCDate() {
        LocalDateTime now = LocalDateTime.now(Clock.systemUTC());
        ZonedDateTime zonedDateTime = now.atZone(ZoneId.systemDefault());
        return Date.from(zonedDateTime.toInstant());
    }

    /**
     * LocalDateTime 转时间戳
     *
     * @param localDateTime /
     * @return /
     */
    public static Long getTimeStamp(LocalDateTime localDateTime) {
        return localDateTime.atZone(ZoneId.systemDefault()).toEpochSecond();
    }

    /**
     * 时间戳转LocalDateTime
     *
     * @param timeStamp /
     * @return /
     */
    public static LocalDateTime fromTimeStamp(Long timeStamp) {
        return LocalDateTime.ofEpochSecond(timeStamp, 0, OffsetDateTime.now().getOffset());
    }

    /**
     * 获取当前日期
     *
     * @return 格式：yyyyMMdd
     */
    public static String getNowDate() {
        return LocalDate.now().format(FORMATTER_YMD);
    }

    /**
     * 获取给定日期所在周周一的日期
     *
     * @param localDate 给定日期
     * @return {@link LocalDate}
     */
    public static LocalDate getMonday(LocalDate localDate) {
        return localDate.with(TemporalAdjusters.previous(DayOfWeek.SUNDAY)).plusDays(1);
    }

    /**
     * 获取给定日期所在周周末的日期
     *
     * @param localDate 给定日期
     * @return {@link LocalDate}
     */
    public static LocalDate getSunday(LocalDate localDate) {
        return localDate.with(TemporalAdjusters.next(DayOfWeek.MONDAY)).minusDays(1);
    }

}