package com.project.wifi.wifi.dto;

import com.project.wifi.api.wifi.data.WifiDetail;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WifiDetailDto {

    private String manageNo;           //관리번호
    private String district;            //자치구
    private String wifiName;           //와이파이명
    private String streetAddress;      //도로명주소
    private String detailAddress;      //상세주소
    private String installLocation;    //설치위치(층)
    private String installType;        //설치유형
    private String installAgency;      //설치기관
    private String serviceType;        //서비스구분
    private String networkType;        //망종류
    private String installYear;        //설치년도
    private String inoutdoorType;      //실내외구분
    private String wifiEnv;            //wifi접속환경
    private String lat;                 //Y좌표
    private String lnt;                 //X좌표
    private String workDate;           //작업일자

    public static WifiDetailDto of(WifiDetail wifiDetail) {
        return WifiDetailDto.builder()
                .manageNo(wifiDetail.getXSwifiMgrNo())
                .district(wifiDetail.getXSwifiWrdofc())
                .wifiName(wifiDetail.getXSwifiMainNm())
                .streetAddress(wifiDetail.getXSwifiAdres1())
                .detailAddress(wifiDetail.getXSwifiAdres2())
                .installLocation(wifiDetail.getXSwifiInstlFloor())
                .installType(wifiDetail.getXSwifiInstlTy())
                .installAgency(wifiDetail.getXSwifiInstlMby())
                .serviceType(wifiDetail.getXSwifiSvcSe())
                .networkType(wifiDetail.getXSwifiCmcwr())
                .installYear(wifiDetail.getXSwifiCnstcYear())
                .inoutdoorType(wifiDetail.getXSwifiInoutDoor())
                .wifiEnv(wifiDetail.getXSwifiRemars3())
                .lat(wifiDetail.getLat())
                .lnt(wifiDetail.getLnt())
                .workDate(wifiDetail.getWorkDttm())
                .build();
    }

}
