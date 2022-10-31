<#ftl encoding="UTF-8">
<#include "base.ftl">

<#macro title>
    <title>Add report</title>
    <link rel="shortcut icon" href="/files/img4.png" type="image/png">
</#macro>

<#macro content>
    <br>
    <h1>Report</h1>
    <br>
    <form action="/addReport" method="post" novalidate enctype="multipart/form-data">
        <p class="lead">
            Write title:<br>
            <input name="title" type="text" style="width: 710px"><br>
        </p>


        <p class="lead">
            Write text:<br>
            <label>
                <textarea name="content" class="recipe" style="width: 710px"></textarea>
            </label><br>
        </p>

        <p class="lead">
            <input name="photo" type="file" accept=".jpg, .png, .jpeg"><br>
        </p>
        <br>
        <p class="lead"><input type="submit" value="Save"></p>
        <br>
    </form>
</#macro>