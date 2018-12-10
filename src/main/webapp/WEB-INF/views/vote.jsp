<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style>
        img.cat {
            max-width: 300px;
            }
    </style>
</head>
<body>
    <img src=${requestScope.kep1} class="cat" onclick="sendRequest(1)">
    <img src=${requestScope.kep2} class="cat" onclick="sendRequest(2)">
</body>
</html>
