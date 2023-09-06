<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>个人信息</title>
    <style>
        * {
            margin: 0;
            padding: 0;
        }

        body {
            background: url(assets/images/index_bg.jpg) center 0 no-repeat fixed;
            background-size: cover;
        }

        a {
            font-family: "宋体";
            margin: auto;
        }

        a:link {
            color: black;
            text-decoration: none;
        }

        a:visited {
            color: purple;
            text-decoration: underline;
        }

        a:hover {
            color: blue;
            text-decoration: underline;
        }

        a:active {
            color: orange;
        }

        #nav {
            height: 55px;
            background-color: white;
            border-top: 5px solid orange;
            box-shadow: 0 5px 30px rgba(0, 0, 0, 0.3);
            font-weight: bold;
        }

        #nav a {
            font-family: "微软雅黑";
            font-size: 20px;
            padding: 0 20px;
            height: 40px;
            line-height: 50px;
        }

        #nav a:link {
            color: black;
            text-decoration: none;
        }

        #nav a:visited {
            text-decoration: none;
            color: black;
        }

        #nav a:hover {
            color: orangered;
            font-size: 135%;
            text-decoration: none;
        }

        #nav a:active {
            color: skyblue;
            text-decoration: none;
        }

        #main {
            text-align: center;
            font-family: "华文楷体";
            width: 70%;
            height: 245px;
            border: 2px solid gray;
            margin: 100px auto;
            padding: 20px 15px;
            background: rgba(249, 246, 245, 0.411);
        }

        #main li {
            font-size: large;
            font-weight: bold;
            list-style: none;
            height: 38px;
            line-height: 40px;
            text-indent: 2em;
            /*缩进*/
            border-bottom: 2px dashed gray;
        }

        #footer {
            text-align: center;
            height: 60px;
            width: auto;
            color: white;
            background-color: midnightblue;
            padding: 60px 100px;
            margin: 0 auto;
            font-size: 20px;
            font-family: "宋体";
        }

        .xuxian {
            padding-top: 10px;
            border: 0;
            border-bottom: 1px dashed;
        }

        #footer li {
            list-style: none;
            line-height: 30px;
        }

        .header {
            margin: 0px 30px;
            font-family: "华文中宋";
            color: purple;
            font-size: 25px;
            padding: 0 10px;
            height: 40px;
            line-height: 50px;
        }

        .left {
            float: left;
            display: inline;
        }

        .right {
            float: right;
            display: inline;
        }
        form {
            margin: 20px auto;
            height: 70%;
        }
        table {
            font-size: large;
            border-collapse: collapse;
            text-align: center;
            line-height: 30px;
            margin: 0 auto;
            width: 80%;
            height: 70%;
        }

        table td, table th {
            border: 1px solid #cad9ea;
            color: rgb(102, 102, 102);
            height: 30px;
        }

        table thead th {
            background-color: rgba(204, 232, 235, 0.864);
            width: 100px;
        }

        table tr:nth-child(odd) {
            background: rgb(255, 255, 255);
        }

        table tr:nth-child(even) {
            background: rgba(245, 250, 250, 0.864);
        }
    </style>
</head>

<body>
<div>
    <div id="nav">
        <div class="left">
            <span class="header"><a href="index.html">🏦银行叫号系统</a></span>
        </div>
        <div class="right">
            <a href="CustomerServlet/personalInfo">👮‍♂️个人信息</a>
            <a href="business_info.jsp">💼办理业务</a>
            <a href="WindowServlet/showAllWindow">🎫窗口列表</a>
            <a href="chart.jsp">📈数据统计</a>
            <a href="logreg.html">👉退出</a>
        </div>
    </div>
    <div id="main">
        <h2>个人信息/Personal infomation</h2>
        <hr class="xuxian">
        <form action="CustomerServlet/personalInfoUpdate" method="get">
            <table border="4" align="center" width="100%" height="70%">
                <tr>
                    <th>卡号</th>
                    <th>登录密码</th>
                </tr>
                <tr>
                    <td>
                        <input type="text" name="card_id"/>
                    </td>
                    <td>
                        <input type="text" name="pwd"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="3">
                        <input class="btn" type="submit" value="提交"/>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div id="footer">

        <ul>
            <li>--------Java Web开发技术三级项目--------</li>
            <li>------- Copyright &copy; 黄祯彦 All rights reserved.-------</li>
        </ul>

    </div>
</div>

</body>

</html>