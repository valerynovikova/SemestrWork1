<#ftl encoding="UTF-8">
<#include 'base.ftl'>

<#macro title>
    <title>All Guides</title>
    <link rel="shortcut icon" href="/files/img_3.png" type="image/png">

</#macro>

<#macro content>
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script>
        window.onload = function showAll() {
            const xmlhttp = new XMLHttpRequest();
            xmlhttp.onreadystatechange = function () {
                if (this.readyState === 4 && this.status === 200) {
                    document.getElementById("result").innerHTML = this.responseText;
                }
            }
            xmlhttp.open("GET", "/allUsersHandler", true);
            xmlhttp.send();
        }

        function showResult(nickname) {
            const xmlhttp = new XMLHttpRequest();
            xmlhttp.onreadystatechange = function () {
                if (this.readyState === 4 && this.status === 200) {
                    document.getElementById("result").innerHTML = this.responseText;
                }
            }
            xmlhttp.open("GET", "/allUsersHandler?nickname=" + nickname, true);
            xmlhttp.send();
        }
    </script>

    <br>
    <h1>All users</h1>
    <br>

    <form>
        <p class="lead" id="1" style="float: left; margin-right: 50px;">
            Search for nickname:<br>
            <input name="nickname" type="text" onkeyup="showResult(this.value)"><br>
        </p>
    </form>

    <br>
    <br>
    <br>
    <br>

    <div id="result"></div>

</#macro>