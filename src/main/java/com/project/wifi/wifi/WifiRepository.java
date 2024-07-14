package com.project.wifi.wifi;

import com.project.wifi.Config;
import com.project.wifi.SqlUtil;
import com.project.wifi.wifi.dto.WifiDetailDto;
import com.project.wifi.wifi.dto.WifiResponseDto;

import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WifiRepository {

    public void insertWifi(List<WifiDetailDto> wifiDetailDtoList) throws IOException {
        Connection connection = null;

        try {
            Class.forName(Config.DB_DRIVER);
            connection = DriverManager.getConnection(Config.DB_URL);
            Statement statement = connection.createStatement();

            StringBuilder sb = new StringBuilder();
            sb.append("insert into wifi_detail values");

            for (int i = 0; i < wifiDetailDtoList.size(); i++) {

                WifiDetailDto wifiDetailDto = wifiDetailDtoList.get(i);

                double lat = Double.parseDouble(wifiDetailDto.getLat());
                double lnt = Double.parseDouble(wifiDetailDto.getLnt());

                double latSin = Math.sin(Math.toRadians(lat));
                double latCos = Math.cos(Math.toRadians(lat));

                double lntSin = Math.sin(Math.toRadians(lnt));
                double lntCin = Math.cos(Math.toRadians(lnt));

                sb.append("(")
                        .append(SqlUtil.makeInsertSqlValue(wifiDetailDto.getManageNo())).append(",")
                        .append(SqlUtil.makeInsertSqlValue(wifiDetailDto.getDistrict())).append(",")
                        .append(SqlUtil.makeInsertSqlValue(wifiDetailDto.getWifiName())).append(",")
                        .append(SqlUtil.makeInsertSqlValue(wifiDetailDto.getStreetAddress())).append(",")
                        .append(SqlUtil.makeInsertSqlValue(wifiDetailDto.getDetailAddress())).append(",")
                        .append(SqlUtil.makeInsertSqlValue(wifiDetailDto.getInstallLocation())).append(",")
                        .append(SqlUtil.makeInsertSqlValue(wifiDetailDto.getInstallType())).append(",")
                        .append(SqlUtil.makeInsertSqlValue(wifiDetailDto.getInstallAgency())).append(",")
                        .append(SqlUtil.makeInsertSqlValue(wifiDetailDto.getServiceType())).append(",")
                        .append(SqlUtil.makeInsertSqlValue(wifiDetailDto.getNetworkType())).append(",")
                        .append(SqlUtil.makeInsertSqlValue(wifiDetailDto.getInstallYear())).append(",")
                        .append(SqlUtil.makeInsertSqlValue(wifiDetailDto.getInoutdoorType())).append(",")
                        .append(SqlUtil.makeInsertSqlValue(wifiDetailDto.getWifiEnv())).append(",")
                        .append(SqlUtil.makeInsertSqlValue(wifiDetailDto.getLat())).append(",")
                        .append(SqlUtil.makeInsertSqlValue(wifiDetailDto.getLnt())).append(",")
                        .append(SqlUtil.makeInsertSqlValue(wifiDetailDto.getWorkDate())).append(",")
                        .append(SqlUtil.makeInsertSqlValue(String.valueOf(latSin))).append(",")
                        .append(SqlUtil.makeInsertSqlValue(String.valueOf(latCos))).append(",")
                        .append(SqlUtil.makeInsertSqlValue(String.valueOf(lntSin))).append(",")
                        .append(SqlUtil.makeInsertSqlValue(String.valueOf(lntCin))).append(")");

                if (i != wifiDetailDtoList.size() - 1) {
                    sb.append(",");
                }

            }

            sb.append(";");

            String temp = sb.toString();
            statement.executeUpdate(temp);

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<WifiResponseDto> selectWifi(double lat, double lnt) throws IOException {
        Connection connection = null;

        List<WifiResponseDto> wifiResponseDtoList = new ArrayList<>();

        try {
            Class.forName(Config.DB_DRIVER);
            connection = DriverManager.getConnection(Config.DB_URL);
            Statement statement = connection.createStatement();

            String selectQuery = "SELECT * , " + buildDistanceQuery(lat, lnt) + " AS partial_distance "
                    + " FROM wifi_detail "
                    + " ORDER BY partial_distance DESC "
                    + " LIMIT 20 ";

            System.out.println("selectQuery: " + selectQuery);
            ResultSet rs = statement.executeQuery(selectQuery);

            while (rs.next()) {
                WifiResponseDto wifiResponseDto = new WifiResponseDto();
                Field[] fields = wifiResponseDto.getClass().getDeclaredFields();
                for (Field field : fields) {
                    field.setAccessible(true);
                    String name = field.getName();

                    String temp = "";
                    try {
                        temp = rs.getString(camelToSnake(name));
                    } catch (SQLException e) {
                        continue;
                    }

                    if (temp != null) {
                        field.set(wifiResponseDto, temp);
                    }

                }

                double distance = Math.acos(Double.parseDouble(rs.getString("partial_distance"))) * 6371;
                wifiResponseDto.setDistance(String.valueOf(distance));
                wifiResponseDtoList.add(wifiResponseDto);
            }

            rs.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return wifiResponseDtoList;
    }

    public WifiResponseDto selectWifiDetail(String manageNo, double lat, double lnt) {
        Connection connection = null;

        List<WifiResponseDto> wifiResponseDtoList = new ArrayList<>();

        try {
            Class.forName(Config.DB_DRIVER);
            connection = DriverManager.getConnection(Config.DB_URL);
            Statement statement = connection.createStatement();

            String selectQuery = "";

            if (lat < 999 && lnt < 999) {
                selectQuery = "SELECT * , " + buildDistanceQuery(lat, lnt) + " AS partial_distance "
                        + " FROM wifi_detail "
                        + " WHERE manage_no = '" + manageNo + "'";
            } else {
                selectQuery = "SELECT * "
                        + " FROM wifi_detail "
                        + " WHERE manage_no = '" + manageNo + "'";
            }

            ResultSet rs = statement.executeQuery(selectQuery);

            while (rs.next()) {
                WifiResponseDto wifiResponseDto = new WifiResponseDto();
                Field[] fields = wifiResponseDto.getClass().getDeclaredFields();
                for (Field field : fields) {
                    field.setAccessible(true);
                    String name = field.getName();

                    String temp = "";
                    try {
                        temp = rs.getString(camelToSnake(name));
                    } catch (SQLException e) {
                        continue;
                    }

                    if (temp != null) {
                        field.set(wifiResponseDto, temp);
                    }

                }

                double distance = 0.0;

                try {
                    distance = Math.acos(Double.parseDouble(rs.getString("partial_distance"))) * 6371;
                } catch (SQLException e) {

                }

                wifiResponseDto.setDistance(String.valueOf(distance));
                wifiResponseDtoList.add(wifiResponseDto);
            }

            rs.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return wifiResponseDtoList.get(0);
    }

    private String buildDistanceQuery(double lat, double lnt) {

        final double coslat = Math.cos(Math.toRadians(lat));
        final double sinlat = Math.sin(Math.toRadians(lat));
        final double coslng = Math.cos(Math.toRadians(lnt));
        final double sinlng = Math.sin(Math.toRadians(lnt));

        return "(" + coslat + "*" + "lat_cos"
                + "*(" + "lnt_cos" + "*" + coslng
                + "+" + "lnt_sin" + "*" + sinlng
                + ")+" + sinlat + "*" + "lat_sin"
                + ")";
    }

    private String camelToSnake(String camelCase) {
        StringBuilder snakeCase = new StringBuilder();
        char[] characters = camelCase.toCharArray();

        for (char c : characters) {
            if (Character.isUpperCase(c)) {
                snakeCase.append('_');
                snakeCase.append(Character.toLowerCase(c));
            } else {
                snakeCase.append(c);
            }
        }
        return snakeCase.toString();
    }


}


