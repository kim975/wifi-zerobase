package com.project.wifi;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SqlUtil {

    public static String makeInsertSqlValue(String value) {
        return "'" + value.replace("'", "''") + "'";
    }

    public static void main(String[] args) {
        // 현재 날짜/시간
        LocalDateTime now = LocalDateTime.now();         // 현재 날짜/시간 출력
        System.out.println(now); // 2021-06-17T06:43:21.419878100

        String date = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String time = now.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        String dateTime = date + "T" + time;
        System.out.println(dateTime);
    }
}
