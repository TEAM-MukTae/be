package io.github.muktae.be_codebase.common.utils;

import io.github.muktae.be_codebase.common.exception.BusinessException;
import io.github.muktae.be_codebase.common.exception.ErrorCode;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.stream.Stream;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DateUtils {

    public static LocalDateTime getFirstDate(int year, int month) {
        validateMonth(month);
        return LocalDateTime.of(year, month, 1, 0, 0, 0);
    }

    public static LocalDateTime getLastDate(int year, int month) {
        validateMonth(month);
        return LocalDateTime.of(year, month, getMaximumDay(month), 0, 0, 0);
    }

    public static LocalDate getDate(int year, int month, int day) {
        validateDate(month, day);
        return LocalDate.of(year, month, day);
    }

    private static void validateMonth(int month) {
        if (month > 12 || month < 1) throw new BusinessException(ErrorCode.INVALID_INPUT_VALUE);
    }

    private static void validateDate(int month, int day) {
        validateMonth(month);
        if (day > getMaximumDay(month) || day < 1) throw new BusinessException(ErrorCode.INVALID_INPUT_VALUE);
    }

    private static int getMaximumDay(int month) {
        if (month == 2) return 29;
        if (Stream.of(1, 3, 5, 7, 8, 10, 12).anyMatch(m -> m == month)) return 31;
        else return 30;
    }
}
