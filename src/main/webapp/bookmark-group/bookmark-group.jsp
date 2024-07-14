<%@ page import="com.project.wifi.bookmark.dto.BookMarkGroupResponseDto" %>
<%@ page import="com.project.wifi.bookmark.BookMarkServiceImpl" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: scilo
  Date: 2024-07-12
  Time: 오전 1:50
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
    BookMarkServiceImpl bookMarkService = new BookMarkServiceImpl();
    List<BookMarkGroupResponseDto> bookMarkGroupResponseDtoList = bookMarkService.selectBookMarkGroupList();
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
    <button onclick="location.href='/bookmark-group/bookmark-group-add.jsp'">북마크 그룹 이름 추가</button>
</div>
<div>
    <table>
        <tr>
            <th scope="col">ID</th>
            <th scope="col">북마크 이름</th>
            <th scope="col">순서</th>
            <th scope="col">등록일자</th>
            <th scope="col">수정일자</th>
            <th scope="col">비고</th>
        </tr>
        <%
            if (!bookMarkGroupResponseDtoList.isEmpty()) {
                for (BookMarkGroupResponseDto dto : bookMarkGroupResponseDtoList) {
        %>
        <tr>
            <td><%=dto.getSeq()%></td>
            <td><%=dto.getBookMarkName()%></td>
            <td><%=dto.getBookMarkOrder()%></td>
            <td><%=dto.getCreateTime()%></td>
            <td><%=dto.getUpdateTime()%></td>
            <td>
                <button onclick="updateBookMarkGroup(<%=dto.getSeq()%>)">수정</button>
                <button onclick="deleteBookMarkGroup(<%=dto.getSeq()%>)">삭제</button>
            </td>
        </tr>
        <%
            }
        } else {
        %>
        <tr>
            <td colspan="6">정보가 없습니다.</td>
        </tr>
        <%
            }
        %>
    </table>
</div>
</body>
<script>
    function updateBookMarkGroup(seq) {
        var form = document.createElement("form");
        form.setAttribute("charset", "UTF-8");
        form.setAttribute("method", "Get");
        form.setAttribute("action", "/bookmark-group/bookmark-group-edit.jsp");

        var hiddenField = document.createElement("input");
        hiddenField.setAttribute("type", "hidden");
        hiddenField.setAttribute("name", "id");
        hiddenField.setAttribute("value", seq);
        form.appendChild(hiddenField);

        document.body.appendChild(form);

        form.submit();
        document.getElementById("form").remove();
    }

    function deleteBookMarkGroup(seq) {
        var form = document.createElement("form");
        form.setAttribute("charset", "UTF-8");
        form.setAttribute("method", "Get");
        form.setAttribute("action", "/bookmark-group/bookmark-group-delete.jsp");

        var hiddenField = document.createElement("input");
        hiddenField.setAttribute("type", "hidden");
        hiddenField.setAttribute("name", "id");
        hiddenField.setAttribute("value", seq);
        form.appendChild(hiddenField);

        document.body.appendChild(form);

        form.submit();
        document.getElementById("form").remove();
    }
</script>
</html>
