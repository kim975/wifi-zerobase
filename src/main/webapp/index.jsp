<%@ page import="com.project.wifi.wifi.WifiServiceImpl" %>
<%@ page import="com.project.wifi.wifi.dto.WifiResponseDto" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.project.wifi.history.WifiHistoryServiceImpl" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <title>와이파이 정보 구하기</title>
    <link href="/css/style.css" rel="stylesheet">
</head>
<body>
    <%
        request.setCharacterEncoding("utf-8");
        String lat = request.getParameter("lat");
        String lnt = request.getParameter("lnt");

        List<WifiResponseDto> a = new ArrayList<>();
        if (lat != null && lnt != null) {
            WifiServiceImpl wifiService = new WifiServiceImpl();
            a = wifiService.selectNearWifi(Double.parseDouble(lat), Double.parseDouble(lnt));


            WifiHistoryServiceImpl historyService = new WifiHistoryServiceImpl();
            historyService.insertHistory(lat, lnt);
        } else {
            lat = "0.0";
            lnt = "0.0";
        }
    %>

    <h1>와이파이 정보 구하기</h1>
    <div class="topnav">
        <a class="active" href="/">홈</a>
        <a href="/wifi/history.jsp">위치 히스토리 목록</a>
        <a href="/wifi/load-wifi.jsp">Open API 와이파이 정보 가져오기</a>
        <a href="/bookmark/bookmark-list.jsp">즐겨 찾기 보기</a>
        <a href="/bookmark-group/bookmark-group.jsp">즐겨 찾기 그룹 관리</a>
    </div>

    <div>
        <form action="/" method="get">
            <span>
                LAT: <input type="text" name="lat" id="LAT" value="<%=lat%>">,
            </span>
            <span>
                LNT: <input type="text" name="lnt" id="LNT" value="<%=lnt%>">
            </span>
            <button type="button" onclick="getMyPosition()">내 위치 가져오기</button>
            <button type="submit">내 근처 WIFI 정보 보기</button>
        </form>
    </div>

    <div>
        <table>
            <tr>
                <th scope="col">거리(Km)</th>
                <th scope="col">관리번호</th>
                <th scope="col">자치구</th>
                <th scope="col">와이파이명</th>
                <th scope="col">도로명주소</th>
                <th scope="col">상세주소</th>
                <th scope="col">설치위치(층)</th>
                <th scope="col">설치유형</th>
                <th scope="col">설치기관</th>
                <th scope="col">서비스구분</th>
                <th scope="col">망종류</th>
                <th scope="col">설치년도</th>
                <th scope="col">실내외구분</th>
                <th scope="col">WIFI접속환경</th>
                <th scope="col">X좌표</th>
                <th scope="col">Y좌표</th>
                <th scope="col">작업일자</th>
            </tr>
            <%
                if (a.size() > 0) {
                    for (WifiResponseDto dto : a) {
            %>
                <tr>
                    <td><%=dto.getDistance()%></td>
                    <td><%=dto.getManageNo()%></td>
                    <td><%=dto.getDistrict()%></td>
                    <td>
                        <a href="/wifi/detail.jsp?manageNo=<%=dto.getManageNo()%>&lat=<%=lat%>&lnt=<%=lnt%>">
                            <%=dto.getWifiName()%>
                        </a>
                    </td>
                    <td><%=dto.getStreetAddress()%></td>
                    <td><%=dto.getDetailAddress()%></td>
                    <td><%=dto.getInstallLocation()%></td>
                    <td><%=dto.getInstallType()%></td>
                    <td><%=dto.getInstallAgency()%></td>
                    <td><%=dto.getServiceType()%></td>
                    <td><%=dto.getNetworkType()%></td>
                    <td><%=dto.getInstallYear()%></td>
                    <td><%=dto.getInoutdoorType()%></td>
                    <td><%=dto.getWifiEnv()%></td>
                    <td><%=dto.getLnt()%></td>
                    <td><%=dto.getLat()%></td>
                    <td><%=dto.getWorkDate()%></td>
                </tr>
            <%
                    }
                } else {
            %>
            <tr>
                <td colspan="17">정보가 없습니다.</td>
            </tr>
            <%
                }
            %>
        </table>
    </div>
</body>
<script>
function getMyPosition() {
    navigator.geolocation.getCurrentPosition((position) => {
        document.getElementById("LAT").value = position.coords.latitude;
        document.getElementById("LNT").value = position.coords.longitude;
    });
}
</script>
</html>