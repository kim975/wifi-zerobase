<%@ page import="com.project.wifi.bookmark.BookMarkServiceImpl" %>
<%@ page import="com.project.wifi.bookmark.dto.BookMarkResponseDto" %><%--
  Created by IntelliJ IDEA.
  User: scilo
  Date: 2024-07-12
  Time: 오후 6:57
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

    String id = request.getParameter("id");

    BookMarkServiceImpl bookMarkService = new BookMarkServiceImpl();
    BookMarkResponseDto bookMarkResponseDto = bookMarkService.selectBookMark(id);
%>
<h1>와이파이 정보 구하기</h1>
<div class="topnav">
    <a href="/">홈</a>
    <a href="/wifi/history.jsp">위치 히스토리 목록</a>
    <a href="/wifi/load-wifi.jsp">Open API 와이파이 정보 가져오기</a>
    <a class="active" href="/bookmark/bookmark-list.jsp">즐겨 찾기 보기</a>
    <a href="/bookmark-group/bookmark-group.jsp">즐겨 찾기 그룹 관리</a>
</div>
<div>
    <h2>북마크 그룹을 삭제하시겠습니까?</h2>
</div>
<div>
    <table>
        <tr>
            <th>북마크 이름</th>
            <td><%=bookMarkResponseDto.getBookMarkGroupName()%></td>
        </tr>
        <tr>
            <th>와이파이명</th>
            <td><%=bookMarkResponseDto.getWifiName()%></td>
        </tr>
        <tr>
            <th>등록일자</th>
            <td><%=bookMarkResponseDto.getCreateTime()%></td>
        </tr>
        <tr>
            <td colspan="2">
                <a onclick="history.back()">돌아가기</a>
                <button onclick="deleteBookMarkGroup(<%=bookMarkResponseDto.getSeq()%>)">삭제</button>
            </td>
        </tr>
    </table>
</div>
</body>
<script>
    function deleteBookMarkGroup(seq) {
        var form = document.createElement("form");
        form.setAttribute("charset", "UTF-8");
        form.setAttribute("method", "Post");
        form.setAttribute("action", "/bookmark/bookmark-delete-submit.jsp");

        var hiddenField2 = document.createElement("input");
        hiddenField2.setAttribute("type", "hidden");
        hiddenField2.setAttribute("name", "id");
        hiddenField2.setAttribute("value", seq);
        form.appendChild(hiddenField2);

        document.body.appendChild(form);

        form.submit();
        document.getElementById("form").remove();
    }
</script>
</html>
