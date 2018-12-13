<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <style>
        table {
            border-collapse: collapse;
            width: 100%
            }
        th, td {
            border-bottom: 1px solid;
            }
        th {
            height: 80px;
            }
        td {
            height: 50px;
            }
    </style>
</head>
<body>
    <button onclick="window.location='vote'">Vote</button>
    <table>
        <tr>
            <th>Cat</th>
            <th>Score</th>
        </tr>
        <c:forEach var="catScore" items="${catsScore}" varStatus="status">
            <tr>
                <td><img src=${catsURL[status.index]} width="40"></td>
                <td><%--<c:out value="${catScore}" />--%>${catScore}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
