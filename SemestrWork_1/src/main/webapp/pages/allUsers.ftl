<#ftl encoding="UTF-8">
<#include 'base.ftl'>

<#macro title>
    <title>All Users</title>
    <link rel="shortcut icon" href="/files/img4.png" type="image/png">

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

    <nav class="navbar navbar-light bg-light">
        <form class="form-inline">
            <input name="title" type="text" onkeyup="showResult(this.value)">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
        </form>
    </nav>

    <br>
    <br>
    <br>
    <br>

    <div id="result"></div>

</#macro>