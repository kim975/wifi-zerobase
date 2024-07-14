package com.project.wifi.history;

import com.project.wifi.Config;
import com.project.wifi.SqlUtil;
import com.project.wifi.history.dto.WifiHistoryDto;
import com.project.wifi.history.dto.WifiHistoryResponseDto;
import com.project.wifi.wifi.dto.WifiDetailDto;
import com.project.wifi.wifi.dto.WifiResponseDto;

import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WifiHistoryRepository {

    public void insertWifiHistory(WifiHistoryDto wifiHistoryDto) throws IOException {
        Connection connection = null;

        try {
            Class.forName(Config.DB_DRIVER);
            connection = DriverManager.getConnection(Config.DB_URL);
            Statement statement = connection.createStatement();

            StringBuilder sb = new StringBuilder();
            sb.append("insert into wifi_history (lat, lnt, create_time) values");

            sb.append("(")
                    .append(SqlUtil.makeInsertSqlValue(wifiHistoryDto.getLat())).append(",")
                    .append(SqlUtil.makeInsertSqlValue(wifiHistoryDto.getLnt())).append(",")
                    .append("strftime('%Y-%m-%dT%H:%M:%S', 'now')").append(")");

            sb.append(";");

            String temp = sb.toString();
            System.out.println(temp);
            statement.executeUpdate(temp);

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<WifiHistoryResponseDto> selectWifiHistory() throws IOException {
        Connection connection = null;

        List<WifiHistoryResponseDto> wifiHistoryResponseDtoList = new ArrayList<>();

        try {
            Class.forName(Config.DB_DRIVER);
            connection = DriverManager.getConnection(Config.DB_URL);
            Statement statement = connection.createStatement();

            String selectQuery = "SELECT * FROM wifi_history ORDER BY seq DESC";

            ResultSet rs = statement.executeQuery(selectQuery);


            while (rs.next()) {
                WifiHistoryResponseDto wifiHistoryResponseDto = new WifiHistoryResponseDto();
                wifiHistoryResponseDto.setSeq(rs.getString("seq"));
                wifiHistoryResponseDto.setLat(rs.getString("lat"));
                wifiHistoryResponseDto.setLnt(rs.getString("lnt"));
                wifiHistoryResponseDto.setCreateTime(rs.getString("create_time"));
                wifiHistoryResponseDtoList.add(wifiHistoryResponseDto);
            }

            rs.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return wifiHistoryResponseDtoList;
    }


    public void deleteWifiHistory(String seq) {
        Connection connection = null;

        try {
            Class.forName(Config.DB_DRIVER);
            connection = DriverManager.getConnection(Config.DB_URL);
            Statement statement = connection.createStatement();

            String selectQuery = "DELETE FROM wifi_history WHERE seq = '" + seq + "'";

            statement.executeUpdate(selectQuery);

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


