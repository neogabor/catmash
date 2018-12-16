<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta http-equiv="refresh" content="10" />
    <style>
        table {
            border-collapse: collapse;
            width: 50%;
            }
        th, td {
            border-bottom: 1px solid;
            }
        th {
            height: 80px;
            font-size: 40px;
            }
        td {
            height: 55px;
            font-size: 25px;
            }
    </style>
</head>
<body>
    <button onclick="window.location='index.html'">Vote</button>
    <table>
        <tr>
            <th>Cat</th>
            <th style="text-align: left;">Score</th>
        </tr>
        <c:forEach var="catScore" items="${catsScore}" varStatus="status">
            <tr>
                <td style="text-align: center;">
                    <a href=${catsURL[status.index]} target="_blank">
                        <img src=${catsURL[status.index]} height="50">
                    </a>
                </td>
                <td>${catScore}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
