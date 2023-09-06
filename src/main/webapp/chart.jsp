<%@ page import="java.util.ArrayList" %>
<%@ page import="bank.entity.Window" %>
<%@ page import="bank.entity.History" %>
<%@ page import="bank.entity.Business" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <script src="assets/js/echarts.min.js"></script>
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
            margin: 20px auto;
            padding: 0px 15px;
            background: rgb(255, 255, 255);
            box-shadow: 0 0 10px 10px rgb(255, 255, 255);
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

        #chart_yewuliang {
            width: 50%;
            height: 400px;
            margin: 0 auto;
            padding: 0px;
            float: left;
        }

        #chart_yewulzhanbi {
            width: 50%;
            height: 400px;
            margin: 0 auto;
            padding: 0px;
            float: right;
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
        <h2>数据统计/Data statistics</h2>
        <hr class="xuxian">
        <%--<%
            ArrayList<Window> windowlist= (ArrayList<Window>) session.getAttribute("windowlist");
            ArrayList<History> yewulianglist= (ArrayList<History>) session.getAttribute("yewulianglist");
            ArrayList<Business> businesslist= (ArrayList<Business>) session.getAttribute("businesslist");
            int sum1=windowlist.size();//窗口数目
            int sum2=yewulianglist.size();//记录数目
            int sum3=businesslist.size();//业务数目
            int[] windows=new int[sum1];
            int[] yewuliang=new int[sum1];
            char[] business=new int[sum3];
            for(int i=0;i<sum1;i++){
                windows[windowlist.get(i).getWid()]++;
                for(int j=0;j<sum2;j++){
                    yewuliang[yewulianglist.get(j).getWid()]++;
                }
            }
            for(int i=0;i<sum3;i++){
                business[i]=1+"";
            }
            business=business.toString();
            for(int i=0;i<sum3;i++){
                business[i]=business[i]+"号窗口";
            }
        %>--%>
        <div id="chart_yewuliang"></div><!--各窗口业务量-->
        <div id="chart_yewulzhanbi"></div><!--各类型业务占比-->
    </div>
    <div id="footer">
        <ul>
            <li>--------Java Web开发技术三级项目--------</li>
            <li>------- Copyright &copy; 黄祯彦 All rights reserved.-------</li>
        </ul>
    </div>
</div>
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var chart_yewuliang = echarts.init(document.getElementById('chart_yewuliang'));
    var chart_yewulzhanbi = echarts.init(document.getElementById('chart_yewulzhanbi'));
    // 指定图表的配置项和数据
    var option_yewuliang = {
        title: {
            text: '窗口业务量统计'
        },
        tooltip: {},
        legend: {
            data: ['业务量']
        },
        xAxis: {
            data: ['1号窗口', '2号窗口', '3号窗口', '4号窗口', '5号窗口', '6号窗口']
        },
        yAxis: {},
        series: [
            {
                name: '业务量',
                type: 'bar',
                data: [3, 2, 1, 2, 1, 0]
            }
        ]
    };
    option_yewuzhanbi = {
        title: {
            text: '各类型业务占比',
            left: 'center',
            top: 'center'
        },
        series: [
            {
                type: 'pie',
                data: [
                    {
                        value: 3,
                        name: '00001 取款'
                    },
                    {
                        value: 2,
                        name: '00002 存款'
                    },
                    {
                        value: 1,
                        name: '00003 贷款'
                    },
                    {
                        value: 2,
                        name: '00004 查询'
                    }
                ],
                radius: ['40%', '70%']
            }
        ]
    };
    // 使用刚指定的配置项和数据显示图表。
    chart_yewuliang.setOption(option_yewuliang);
    chart_yewulzhanbi.setOption(option_yewuzhanbi);
</script>
</body>

</html>