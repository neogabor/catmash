<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style>
        img.cat {
            max-width: 300px;
            }
        .main {
            display: flex;
            align-items: center;
            justify-content: center;
            }

        .columnleftimg {
            margin-right: 50px;
            }
        .columntxt {
            margin-left: 20px; 
            margin-right: 20px; 
            }
        .columnrightimg {
            margin-left: 50px;
            }
        .button {
            text-align: center;    
            }
    </style>
    <script>
        function sendRequest(data) {
            var xhr = new XMLHttpRequest();
            xhr.open("POST", "index.html", true);
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
    <h1 style="font-family: Arial Black; font-size: 500%" align="center">CatMash</h1>
    <h2 style="font-family: Comic Sans Ms; font-size: 200%" align="center">Click on the cuter one!</h1>
    <div class="button">
        <button onclick="window.location='rankings'">Rankings</button>
    </div>
    <div class="main">
        <div class="columnimg">
        <img src=${requestScope.kep1} class="cat" onclick="sendRequest(1)">
        </div>
        <div class="columntxt">
        <p style="font-family: Comic Sans MS, cursive; font-size: 400%;">VS</p>
        </div>
        <div class="columnrightimg">
        <img src=${requestScope.kep2} class="cat" onclick="sendRequest(2)">
        </div>
    </div>
</body>
</html>
