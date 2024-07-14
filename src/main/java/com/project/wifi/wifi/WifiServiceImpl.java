package com.project.wifi.wifi;

import com.project.wifi.api.wifi.data.WifiDetail;
import com.project.wifi.api.wifi.PublicWifiApiServiceImpl;
import com.project.wifi.wifi.dto.WifiDetailDto;
import com.project.wifi.wifi.dto.WifiResponseDto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WifiServiceImpl {

    public int insertAllWifiDetail() throws IOException {

        PublicWifiApiServiceImpl publicWifiApiService = new PublicWifiApiServiceImpl();

        int wifiDetailTotalCount = publicWifiApiService.getWifiTotalCount();
        int repeatNumber = (int) Math.ceil(wifiDetailTotalCount / 1000.0) ;

        WifiRepository wifiRepository = new WifiRepository();

        for (int i = 0; i < repeatNumber; i++) {

            int startNum =  i * 1000 + 1;
            int endNum = (i + 1) * 1000;

            List<WifiDetail> wifiDetailList = publicWifiApiService.getWifiDetailPage(startNum, endNum);
            List<WifiDetailDto> wifiDetailDtoList = new ArrayList<>();
            for (WifiDetail wifiDetail : wifiDetailList) {
                wifiDetailDtoList.add(WifiDetailDto.of(wifiDetail));
            }

            wifiRepository.insertWifi(wifiDetailDtoList);
        }

        return wifiDetailTotalCount;

    }

    public List<WifiResponseDto> selectNearWifi(double lat, double lnt) throws IOException {

        WifiRepository wifiRepository = new WifiRepository();

        return wifiRepository.selectWifi(lat, lnt);

    }

    public WifiResponseDto selectWifiDetail(String manageNo, double lat, double lnt) {
        WifiRepository wifiRepository = new WifiRepository();
        return wifiRepository.selectWifiDetail(manageNo, lat, lnt);
    }
}
