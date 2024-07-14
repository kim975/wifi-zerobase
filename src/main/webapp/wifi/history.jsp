<%@ page import="com.project.wifi.wifi.WifiServiceImpl" %>
<%@ page import="com.project.wifi.wifi.dto.WifiResponseDto" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.project.wifi.history.WifiHistoryServiceImpl" %>
<%@ page import="com.project.wifi.history.WifiHistoryRepository" %>
<%@ page import="com.project.wifi.history.dto.WifiHistoryResponseDto" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <title>와이파이 정보 구하기</title>
    <link href="/css/style.css" rel="stylesheet">
</head>
<body>
    <%

        String seq = request.getParameter("seq");
        WifiHistoryServiceImpl wifiHistoryService = new WifiHistoryServiceImpl();

        if (seq != null) {
            wifiHistoryService.deleteWifiHistory(seq);
        }

        List<WifiHistoryResponseDto> wifiHistoryResponseDtoList = wifiHistoryService.selectWifiHistory();
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
        <table>
            <tr>
                <th scope="col">ID</th>
                <th scope="col">X좌표</th>
                <th scope="col">Y좌표</th>
                <th scope="col">조회일자</th>
                <th scope="col">비고</th>
            </tr>
            <%
                if (wifiHistoryResponseDtoList.size() > 0) {
                    for (WifiHistoryResponseDto dto : wifiHistoryResponseDtoList) {
            %>
                <tr>
                    <td><%=dto.getSeq()%></td>
                    <td><%=dto.getLnt()%></td>
                    <td><%=dto.getLat()%></td>
                    <td><%=dto.getCreateTime()%></td>
                    <td><button onclick="deleteHistory(<%=dto.getSeq()%>)">삭제</button></td>
                </tr>
            <%
                    }
                } else {
            %>
            <tr>
                <td colspan="5">정보가 없습니다.</td>
            </tr>
            <%
                }
            %>
        </table>
    </div>
</body>
<script>
function deleteHistory(seq) {
    var form = document.createElement("form");
    form.setAttribute("charset", "UTF-8");
    form.setAttribute("method", "Get");
    form.setAttribute("action", "/wifi/history.jsp");

    var hiddenField = document.createElement("input");
    hiddenField.setAttribute("type", "hidden");
    hiddenField.setAttribute("name", "seq");
    hiddenField.setAttribute("value", seq);
    form.appendChild(hiddenField);

    document.body.appendChild(form);

    form.submit();
    document.getElementById("form").remove();
}
</script>
</html>