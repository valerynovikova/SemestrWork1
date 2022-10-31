<#include "base.ftl">

<html lang="en">
<#macro title>
    <title>SignIn</title>
    <link rel="shortcut icon" href="/files/img4.png" type="image/jpg">
</#macro>
<style>
    @font-face {
        font-family: Oswald, system-ui;
        src: url(/fonts/Oswald-Medium.ttf)
    }

    html {
        font-family: Oswald, system-ui;
    }

</style>
<#if message??>
    <script>
        alert("failed to auth")
    </script>
</#if>
<#macro content>
    <br>
    <h1>Sign In</h1>
    <br>
        <form action="/signIn" method="post">
            <p class="lead">
                Login:<br>
                <input name="userLogin" type="text" placeholder="Login"/>
            </p>
            <p class="lead">
                Password:<br>
                <input name="userPass" type="password" placeholder="Password"/>
            </p>
            <br>
            <p class="lead">
                Remember me:
                <input name="isRemember" type="checkbox">
            </p>
            <br>
            <p class="lead"><input type="submit" value="Enter"></p>
            <br>
        </form>
</#macro>
</html>
