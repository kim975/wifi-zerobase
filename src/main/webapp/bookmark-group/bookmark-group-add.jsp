<%--
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
<h1>와이파이 정보 구하기</h1>
<div class="topnav">
    <a href="/">홈</a>
    <a href="/wifi/history.jsp">위치 히스토리 목록</a>
    <a href="/wifi/load-wifi.jsp">Open API 와이파이 정보 가져오기</a>
    <a href="/bookmark/bookmark-list.jsp">즐겨 찾기 보기</a>
    <a class="active" href="/bookmark-group/bookmark-group.jsp">즐겨 찾기 그룹 관리</a>
</div>
    <div>
        <table>
            <tr>
                <th>북마크 이름</th>
                <td><input id="bookMarkName" type="text" value=""></td>
            </tr>
            <tr>
                <th>순서</th>
                <td><input id="bookMarkOrder" type="text" value=""></td>
            </tr>
            <tr>
                <td><button onclick="insertBookMarkGroup()">추가</button></td>
            </tr>
        </table>
    </div>
</body>
<script>
    function insertBookMarkGroup() {
        var form = document.createElement("form");
        form.setAttribute("charset", "UTF-8");
        form.setAttribute("method", "Post");
        form.setAttribute("action", "/bookmark-group/bookmark-group-add-submit.jsp");

        var bookMarkName = document.getElementById("bookMarkName").value;
        var bookMarkOrder = document.getElementById("bookMarkOrder").value;

        var hiddenField = document.createElement("input");
        hiddenField.setAttribute("type", "hidden");
        hiddenField.setAttribute("name", "bookMarkName");
        hiddenField.setAttribute("value", bookMarkName);
        form.appendChild(hiddenField);

        var hiddenField1 = document.createElement("input");
        hiddenField1.setAttribute("type", "hidden");
        hiddenField1.setAttribute("name", "bookMarkOrder");
        hiddenField1.setAttribute("value", bookMarkOrder);
        form.appendChild(hiddenField1);

        document.body.appendChild(form);

        form.submit();
        document.getElementById("form").remove();
    }
</script>
</html>
