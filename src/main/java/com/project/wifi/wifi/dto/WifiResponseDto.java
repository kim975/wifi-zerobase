package com.project.wifi.wifi.dto;

import lombok.Data;

@Data
public class WifiResponseDto {
    private String distance;           //거리
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

}
