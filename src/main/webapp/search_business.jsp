<%@ page import="bank.entity.Business" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>窗口列表</title>
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
            height: 475px;
            border: 2px solid gray;
            margin: 20px auto;
            padding: 0px 15px;
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
            height: 30px;
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
            margin: 10px auto;
            height: 100%;
        }

        /*table {
            font-size: large;
            margin: 0px auto;
            width: 80%;
            height: 85%;
        }*/
        table {
            font-size: large;
            margin: 0px auto;
            width: 80%;
            height: 85%;
            border-collapse: collapse;
            text-align: center;
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

        .delete:link {
            font-weight: bolder;
            text-decoration: none;
        }

        .edit:link {
            font-weight: bolder;
            text-decoration: none;
        }

        .delete:visited {
            font-weight: bolder;
            text-decoration: none;
        }

        .edit:visited {
            font-weight: bolder;
            text-decoration: none;
        }

        .delete:hover {
            font-weight: bolder;
            color: red;
            text-decoration: none;
        }

        .edit:hover {
            font-weight: bolder;
            color: green;
            text-decoration: none;
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
        <h2>业务列表/Business list</h2>
        <hr class="xuxian">
        <%
            String uri="";
        %>
        <form action="BusinessServlet/deleteBusiness" method="post" onsubmit="return del();">
            <table border="4" align="center" width="100%" height="70%">
                <%
                    int i = 0;
                    ArrayList<Business> businesslist = (ArrayList<Business>) session.getAttribute("businesslist");
                    for (i = 0; i < businesslist.size(); i++) {
                %>
                <tr>
                    <th>业务ID</th>
                    <th>业务编号</th>
                    <th>业务名称</th>
                    <th>操作</th>
                </tr>
                <tr>
                    <td id="bid" name="bid">
                        <%=businesslist.get(i).getBid()%>
                    </td>
                    <td id="bnumber">
                        <%=businesslist.get(i).getBnumber()%>
                    </td>
                    <td id="bname">
                        <%=businesslist.get(i).getBname()%>
                    </td>
                    <td id="operation">
<%--                        <a class="edit" href="BusinessServlet/editBusiness">修改</a>--%>
                        <%
                            uri = String.valueOf(businesslist.get(i).getBid());
                            session.setAttribute("bid",uri);
                        %>
                        <input class="btn" type="submit" value="删除">
<%--                        <a class="delete" href="BusinessServlet/deleteBusiness?bid=<%=uri%>">删除</a>--%>
                    </td>
                </tr>
                <%
                    }
                %>
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
<script>
    function del(){
        alert("❗业务ID为"+uri+"的业务已删除");
        return true;
    }
</script>
</body>

</html>