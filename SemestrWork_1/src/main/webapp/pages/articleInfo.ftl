<#ftl encoding="UTF-8">
<#include 'base.ftl'>

<#macro title>
    <title>Article</title>
    <link rel="shortcut icon" href="/files/img4.png" type="image/png">
</#macro>

<#macro content>

    <#if article?has_content>
        <br>
        <br>
        <br>
        <h1>${article.title}</h1>
        <br>
        <br>
        <img src="${article.photoUrl}" width="709" height="350">
        <br>
        <br>
        <br>
        <div>${article.text}</div>
        <br>
        <br>
        <br>
        <div>
            <table>
                <tr>
                    <td>
                        <a href="/userInfo?id=${author.id}">
                            <img alt="user_img" src="${author.avatarUrl}" width="50" height="50" class="rounded-circle">
                        </a>
                    </td>

                    <td><strong style="font-size:20px">Nick: ${article.userNick}</strong></td>
                    <td><small class="text-muted" style="font-size:17px"><em>${article.data}</em></small></td>
                    <td><small class="text-muted" style="font-size:17px">Build ${article.id}</small></td>
                </tr>
            </table>
        </div>

        <br>

        <#if isComments??>
            <#if comments?has_content>
                <p class="lead">Comments:</p>

                <#list comments as comment>
                    <table>
                        <tr>
                            <td>
                                <a href="/userInfo?id=${comment.user.id}">
                                    <img alt="user_img" src="${comment.user.avatarUrl}" width="50" height="50" class="rounded-circle">
                                </a>
                            </td>
                            <td><strong style="font-size:20px">Nick: ${comment.user.nick}</strong></td>
                        </tr>
                    </table>
                    <div class="alert alert-dark" role="alert">
                        <div>${comment.text}</div>
                    </div>
                </#list>
            <#else>
                <p class="lead">No comments</p>
            </#if>

            <#if userNow?has_content>
                <form action="/articleInfo?id=${article.id}" method="post" novalidate>
                    <p class="lead">Enter comment:</p>
                    <p class="lead">
                        <label>
                            <textarea name="comment" placeholder="Comment" class="comment" style="width: 710px"></textarea>
                        </label><br>
                    </p>
                    <p class="lead"><input type="submit" value="Save"></p>
                    <br>
                </form>
            <#else>
                <p class="lead">Sign in to leave comments</p>
            </#if>
        </#if>
    <#else>
        <p class="lead">Something went wrong</p>
    </#if>

</#macro>