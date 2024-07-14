package com.project.wifi.history;

import com.project.wifi.history.dto.WifiHistoryDto;
import com.project.wifi.history.dto.WifiHistoryResponseDto;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class WifiHistoryServiceImpl {
    public void insertHistory(String lat, String lnt) throws IOException {

        LocalDateTime now = LocalDateTime.now();
        String date = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String time = now.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        String dateTime = date + "T" + time;

        WifiHistoryRepository wifiHistoryRepository  = new WifiHistoryRepository();
        WifiHistoryDto wifiHistoryDto  = new WifiHistoryDto();
        wifiHistoryDto.setLat(lat);
        wifiHistoryDto.setLnt(lnt);
        wifiHistoryDto.setCreateTime(dateTime);

        wifiHistoryRepository.insertWifiHistory(wifiHistoryDto);

    }

    public List<WifiHistoryResponseDto> selectWifiHistory() throws IOException {

        WifiHistoryRepository wifiHistoryRepository = new WifiHistoryRepository();
        return wifiHistoryRepository.selectWifiHistory();
    }

    public void deleteWifiHistory(String seq) throws IOException {
        WifiHistoryRepository wifiHistoryRepository = new WifiHistoryRepository();
        wifiHistoryRepository.deleteWifiHistory(seq);
    }
}
