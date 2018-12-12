<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style>
        img.cat {
            max-width: 300px;
            }
    </style>
    <script>
        function sendRequest(data) {
            var xhr = new XMLHttpRequest();
            xhr.open("POST", "vote", true);
            xhr.onload = function() {
                if (xhr.status === 200) {
                    console.log("Successful request");
                }

                if (xhr.status >= 400) {
                    console.log("Not good, something went wrong, status code: ", xhr.status);
                }
            }
            xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
            var sentData;
            if (data == 1) {
                sentData = "${requestScope.kep1}";
            } else {
                sentData = "${requestScope.kep2}";
            }

            xhr.send(sentData);
            location.reload();
        }
    </script>
</head>
<body>
    <img src=${requestScope.kep1} class="cat" onclick="sendRequest(1)">
    <img src=${requestScope.kep2} class="cat" onclick="sendRequest(2)">
</body>
</html>
