<%@ page import="com.project.wifi.bookmark.BookMarkServiceImpl" %>
<%@ page import="com.project.wifi.bookmark.dto.BookMarkGroupDto" %>
<%@ page import="com.project.wifi.bookmark.dto.BookMarkDto" %><%--
  Created by IntelliJ IDEA.
  User: scilo
  Date: 2024-07-12
  Time: 오전 1:47
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

    BookMarkDto bookMarkDto = new BookMarkDto();
    bookMarkDto.setSeq(id);

    BookMarkServiceImpl bookMarkService = new BookMarkServiceImpl();
    bookMarkService.deleteBookMark(bookMarkDto);

%>
</body>
<script>
    window.onload = function() {
        alert("북마크 정보를 삭제하였습니다.");
        var link = '/bookmark/bookmark-list.jsp';
        location.href=link;
    }
</script>
</html>
