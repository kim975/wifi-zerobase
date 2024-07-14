<%@ page import="com.project.wifi.wifi.WifiServiceImpl" %><%--
  Created by IntelliJ IDEA.
  User: scilo
  Date: 2024-07-10
  Time: 오후 11:44
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

        WifiServiceImpl wifiService = new WifiServiceImpl();
        int wifiDetailTotalCount = wifiService.insertAllWifiDetail();
    %>

    <div>
        <h1><%=wifiDetailTotalCount%>개의 WIFI 정보를 정상적으로 저장하였습니다.</h1>
    </div>

    <div>
        <a href="/">홈으로 가기</a>
    </div>
</body>
</html>
