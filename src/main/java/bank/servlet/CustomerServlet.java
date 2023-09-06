package bank.servlet;

import bank.entity.Customer;
import bank.entity.History;
import bank.service.CustomerService;
import bank.service.HistoryService;
import bank.service.ServiceImpl.CustomerServiceImpl;
import bank.service.ServiceImpl.HistoryServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 逻辑：
 * 【登录】：//1.获取填写的卡号和登陆密码
 *         //2.查询当前数据库customer表中的卡号和登陆密码，进行表单验证【异步通信】
 *         //3.表单验证通过，登录成功，转发到首页
 * 【刷卡取号】：客户在登录界面输入卡号和登录密码，登录后点击取号，得到回显表单（当前队列编号、前面还有几人，在response对象中）
 *            若员工确认取号请求，则进入【获取窗口、处理业务】
 *            若员工驳回取号请求，则显示异常信息页面，说明驳回原因（员工填写的信息）
 * 【获取窗口、处理业务】：确认回显的叫号信息，等待发送过来的处理结果
 */
@WebServlet("/CustomerServlet/*")
public class CustomerServlet extends BaseServlet{
    private CustomerService customerService=new CustomerServiceImpl();
    private HistoryService historyService=new HistoryServiceImpl();
    //表单验证——账号
    public void accountCheck(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        String account=request.getParameter("account");
        boolean flag=false;
        if(account.equals("admin")){//管理员特判
            flag=true;
        }else{
            flag=customerService.accountCheck(account);
        }
        response.getWriter().write(flag+"");
    }
    //表单验证——密码
    /*public void pwdCheck(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        String account=request.getParameter("account");
        String pwd=request.getParameter("pwd");
        boolean flag=false;
        if(account.equals("admin")){//管理员特判
            if(pwd.equals("admin123"))
                flag=true;
        }else{
            flag=customerService.pwdCheck(account,pwd);
        }
        response.getWriter().write(flag+"");
    }*/
    //表单验证通过——登录
    public void logregCustomer(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        //1.获取填写的卡号和登陆密码
        String account=request.getParameter("account");
        String pwd=request.getParameter("pwd");
        String type=request.getParameter("subbtn");
        if(type.equals("Login")){//登录
            boolean flag=false;
            if(account.equals("admin")){//2.如果用户名是admin
                if(pwd.equals("admin123"))
                    flag=true;
            }
            else{
                //3.查询当前数据库customer表中的卡号和登陆密码
                flag=customerService.loginCustomer(account,pwd);
            }
            //4.表单验证通过，登录成功，转发到首页
            if(flag==true){
                HttpSession session=request.getSession();//这里用session，用户信息需要全程显示
                session.setAttribute("card_id",account);
                session.setAttribute("pwd",pwd);
                //request.setAttribute("logmsg","登录成功");
                response.sendRedirect("/BankWebMaven_war/index.html");//是去首页，登录页不需要了，使用重定向
            }
            else {//表单验证不通过
                //request.setAttribute("logmsg","密码错误");
                request.getRequestDispatcher("/logreg.html").forward(request,response);//回到登录页，使用转发
            }
        }
        else{//注册
            if(!(account.equals("admin"))){
                int result=customerService.registerCustomer(account,pwd);
                if(result>0){
                    HttpSession session=request.getSession();//这里用session，用户信息需要全程显示
                    session.setAttribute("card_id",account);
                    session.setAttribute("pwd",pwd);
                    //request.setAttribute("regmsg","注册成功");
                    response.sendRedirect("/BankWebMaven_war/index.html");//是去首页，登录页不需要了，使用重定向
                }
                else{
                    //request.setAttribute("regmsg","注册失败");
                    request.getRequestDispatcher("/logreg.html").forward(request,response);//回到登录页，使用转发
                }
            }
            else{
                //request.setAttribute("regmsg","注册失败");
                request.getRequestDispatcher("/logreg.html").forward(request,response);//回到登录页，使用转发
            }
        }
    }
    //刷卡取号
    public void getNumber(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        //1.获取客户在银行自主业务机上填写的表单内容（卡号、业务名称（业务名称返回的value是对应的业务编号，省得再去数据库中查））
        HttpSession session=request.getSession();
        String card_id=session.getAttribute("card_id").toString();
        String bnumber= request.getParameter("bnumber");
        session.setAttribute("bnumber",bnumber);
        //int cid= (int) session.getAttribute("cid");
        int cid=customerService.searchCustomerByCard_id(card_id).getCid();
        //2.查询当前数据库history表中WID非空,元组数目+1即为取号号码HID
        //2.查询当前数据库history表中IF_COMPLETE=‘F’的元组数目,元组数目+1即为取号号码HID
        int hid=historyService.numberOfMaxWid()+1;//实际业务一定是先完成HID小的，因此保证HID递增即可
        //3.将取到的号码（HID）、卡号、业务编号、取票时间存入数据库和session对象(id、业务编号和密码之前登录注册存过)
        session.setAttribute("hid",hid);
        History history=new History();
        history.setHid(hid);
        history.setCard_id(card_id);
        history.setCid(cid);
        history.setBnumber(bnumber);
        history.setGet_time();
        historyService.addHistoryWithoutWid(history);
        //4.回显前面还有几人queue-1（存入session对象）
        int queue=historyService.numberOfWidIsNull();
        session.setAttribute("hid",hid);
        session.setAttribute("queue",queue-1);//如果队列人数不是计算数据库行数的话，就得存在application对象里
        response.sendRedirect("/BankWebMaven_war/my_business.jsp");
    }
    //获得窗口、处理业务：因为需要客户手动确认，所以由customer完成
    /*public void confirmWindow(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
        //1.获取application中的客户ID,窗口ID

        //2.回显在前端表单（alert也可以）

        //3.确认or取消，将结果存入application，

        //4.展示【处理业务】表单/页面（就是展示处理结果，比如转账、取钱）【异步通信】

    }*/
    //查看个人信息
    public void personalInfo(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
        //1.获取session中的card_id
        HttpSession session=request.getSession();
        String card_id=session.getAttribute("card_id").toString();
        //2.查询客户信息，回显在前端表格
        Customer customer=customerService.searchCustomerByCard_id(card_id);
        /*request.setAttribute("cid",customer.getCid());
        request.setAttribute("card_id",customer.getCard_id());
        request.setAttribute("pwd",customer.getPwd());*/
        session.setAttribute("cid",customer.getCid());
        //session.setAttribute("card_id",card_id);
        //session.setAttribute("pwd",customer.getPwd());
        //3.由于使用的是request，就用转发转到personal_info.jsp
        //request.getRequestDispatcher("/personal_info.jsp").forward(request,response);
        response.sendRedirect("/BankWebMaven_war/personal_info.jsp");
    }
    //编辑个人信息
    public void personalInfoUpdate(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
        //1.获取session中的客户ID
        HttpSession session=request.getSession();
        int cid= (int) session.getAttribute("cid");
        String card_id=request.getParameter("card_id").toString();
        String pwd=request.getParameter("pwd").toString();
        //2.查询客户信息，回显在前端表格
        Customer customer=customerService.searchCustomerByCid(cid);
        customer.setCid(cid);
        customer.setCard_id(card_id);
        customer.setPwd(pwd);
        /*request.setAttribute("cid",customer.getCid());
        request.setAttribute("card_id",customer.getCard_id());
        request.setAttribute("pwd",customer.getPwd());*/
        session.setAttribute("cid",cid);
        session.setAttribute("card_id",card_id);
        session.setAttribute("pwd",pwd);
        customerService.UpdateCustomer(customer);
        //3.修改完成，重定向personal_info.jsp
        //request.getRequestDispatcher("/personal_info.jsp").forward(request,response);
        response.sendRedirect("/BankWebMaven_war/personal_info.jsp");
    }
}
