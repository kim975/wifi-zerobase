<%@ page import="com.project.wifi.wifi.dto.WifiResponseDto" %>
<%@ page import="com.project.wifi.wifi.WifiServiceImpl" %>
<%@ page import="com.project.wifi.bookmark.BookMarkServiceImpl" %>
<%@ page import="com.project.wifi.bookmark.dto.BookMarkGroupResponseDto" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: scilo
  Date: 2024-07-12
  Time: 오전 1:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>와이파이 정보 구하기</title>
    <link href="/css/style.css" rel="stylesheet">
</head>
<body>
<%
    String manageNo = request.getParameter("manageNo");
    String lat = request.getParameter("lat");
    String lnt = request.getParameter("lnt");

    WifiResponseDto wifiResponseDto = null;
    if (manageNo != null) {
        WifiServiceImpl wifiService = new WifiServiceImpl();

        if (lat == null || lnt == null) {
            lat = "1000.0";
            lnt = "1000.0";
        }

        wifiResponseDto = wifiService.selectWifiDetail(manageNo, Double.parseDouble(lat), Double.parseDouble(lnt));
    }
%>
    <h1>와이파이 정보 구하기</h1>
<div class="topnav">
    <a class="active" href="/">홈</a>
    <a href="/wifi/history.jsp">위치 히스토리 목록</a>
    <a href="/wifi/load-wifi.jspi.jsp">Open API 와이파이 정보 가져오기</a>
    <a href="/bookmark/bookmark-list.jsp">즐겨 찾기 보기</a>
    <a href="/bookmark-group/bookmark-group.jsp">즐겨 찾기 그룹 관리</a>
</div>
<div>
    <select name="bookMarkSeq" id="bookMarkSeq">
        <option value="">북마크 그룹 이름 선택</option>
        <%
            BookMarkServiceImpl bookMarkService = new BookMarkServiceImpl();
            List<BookMarkGroupResponseDto> bookMarkGroupResponseDtoList = bookMarkService.selectBookMarkGroupList();
            for (BookMarkGroupResponseDto dto : bookMarkGroupResponseDtoList) {
        %>
        <option value="<%=dto.getSeq()%>"><%=dto.getBookMarkName()%></option>
        <%
            }

        %>
    </select>
    <button onclick="insertBookMark()">즐겨찾기 추가하기</button>
</div>
<div>
    <table>
        <%
            if (wifiResponseDto != null) {
        %>
        <tr>
            <th scope="col">거리(Km)</th>
            <td><%=wifiResponseDto.getDistance()%></td>
        </tr>
        <tr>
            <th scope="col">관리번호</th>
            <td><%=wifiResponseDto.getManageNo()%></td>
        </tr>
        <tr>
            <th scope="col">자치구</th>
            <td><%=wifiResponseDto.getDistrict()%></td>
        </tr>
        <tr>
            <th scope="col">와이파이명</th>
            <td><%=wifiResponseDto.getWifiName()%></td>
        </tr>
        <tr>
            <th scope="col">도로명주소</th>
            <td><%=wifiResponseDto.getStreetAddress()%></td>
        </tr>
        <tr>
            <th scope="col">상세주소</th>
            <td><%=wifiResponseDto.getDetailAddress()%></td>
        </tr>
        <tr>
            <th scope="col">설치위치(층)</th>
            <td><%=wifiResponseDto.getInstallLocation()%></td>
        </tr>
        <tr>
            <th scope="col">설치유형</th>
            <td><%=wifiResponseDto.getInstallType()%></td>
        </tr>
        <tr>
            <th scope="col">설치기관</th>
            <td><%=wifiResponseDto.getInstallAgency()%></td>
        </tr>
        <tr>
            <th scope="col">서비스구분</th>
            <td><%=wifiResponseDto.getServiceType()%></td>
        </tr>
        <tr>
            <th scope="col">망종류</th>
            <td><%=wifiResponseDto.getNetworkType()%></td>
        </tr>
        <tr>
            <th scope="col">설치년도</th>
            <td><%=wifiResponseDto.getInstallYear()%></td>
        </tr>
        <tr>
            <th scope="col">실내외구분</th>
            <td><%=wifiResponseDto.getInoutdoorType()%></td>
        </tr>
        <tr>
            <th scope="col">WIFI접속환경</th>
            <td><%=wifiResponseDto.getWifiEnv()%></td>
        </tr>
        <tr>
            <th scope="col">X좌표</th>
            <td><%=wifiResponseDto.getLnt()%></td>
        </tr>
        <tr>
            <th scope="col">Y좌표</th>
            <td><%=wifiResponseDto.getLat()%></td>
        </tr>
        <tr>
            <th scope="col">작업일자</th>
            <td><%=wifiResponseDto.getWorkDate()%></td>
        </tr>
        <%
            }
        %>
    </table>
</div>
</body>
<script>
    function insertBookMark() {
        var form = document.createElement("form");
        form.setAttribute("charset", "UTF-8");
        form.setAttribute("method", "Post");
        form.setAttribute("action", "/bookmark/bookmark-add-submit.jsp");

        var select = document.getElementById('bookMarkSeq');
        var option = select.options[select.selectedIndex];

        // document.getElementById('value').value = option.value;
        // document.getElementById('text').value = option.text;

        var bookMarkSeq = option.value

        var hiddenField1 = document.createElement("input");
        hiddenField1.setAttribute("type", "hidden");
        hiddenField1.setAttribute("name", "bookMarkSeq");
        hiddenField1.setAttribute("value", bookMarkSeq);
        form.appendChild(hiddenField1);

        var hiddenField2 = document.createElement("input");
        hiddenField2.setAttribute("type", "hidden");
        hiddenField2.setAttribute("name", "wifiManageNo");
        hiddenField2.setAttribute("value", "<%=wifiResponseDto.getManageNo()%>");
        form.appendChild(hiddenField2);

        document.body.appendChild(form);

        form.submit();
        document.getElementById("form").remove();
    }
</script>
</html>
