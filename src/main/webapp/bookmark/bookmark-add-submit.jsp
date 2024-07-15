<%@ page import="com.project.wifi.bookmark.BookMarkServiceImpl" %><%--
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
    request.setCharacterEncoding("utf-8");
    String bookMarkSeq = request.getParameter("bookMarkSeq");
    String wifiManageNo = request.getParameter("wifiManageNo");

    BookMarkServiceImpl bookMarkService = new BookMarkServiceImpl();
    bookMarkService.insertBookMark(bookMarkSeq, wifiManageNo);

%>
</body>
<script>
    window.onload = function() {
        alert("북마크 정보를 추가하였습니다.");
        var link = '/bookmark/bookmark-list.jsp';
        location.href=link;
    }
</script>
</html>
