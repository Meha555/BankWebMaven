package bank.servlet;

import bank.entity.Business;
import bank.service.BusinessService;
import bank.service.ServiceImpl.BusinessServiceImpl;
import java.io.*;
import java.util.ArrayList;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

/**
 * 逻辑：管理员点击【业务管理】进入业务信息页面
 * 【查询业务】：点击查询，按照findByXXX查询
 * 【增加业务】：点击增加，在表单内输入要增加的业务信息（业务编号自动生成，就是+1），使用异步通信查询ID、业务名是否重复表单验证
 * 【修改信息】：点击修改，再点击查询，同【查询业务】，得到查询结果，选中一个，之后基本同【增加业务】
 * 【删除业务】；点击删除，再点击查询，同【查询业务】，得到查询结果，选中一个或多选，进行删除
 */
@WebServlet("/BusinessServlet/*")
public class BusinessServlet extends BaseServlet{
    private BusinessService businessService=new BusinessServiceImpl();
    public void findByID(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {

    }
    public void findByNumber(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {

    }
    public void findByName(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {

    }
    //展示所有业务信息
    public void showAllBusiness(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取所有窗口的信息
        HttpSession session=request.getSession();
        ArrayList<Business> list=businessService.findAll();
        //2.返回窗口list集合，回显在前端表格
        session.setAttribute("businesslist",list);
        response.sendRedirect("/BankWebMaven_war/search_business.jsp");
    }
    public void addBusiness(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {

    }
    public void editBusiness(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {

    }
    public void deleteBusiness(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        String bid= (String) session.getAttribute("bid");
        businessService.deleteOne(businessService.findByBid(Integer.parseInt(bid)));
        response.sendRedirect("/BankWebMaven_war/search_business.jsp");
    }
    public void deleteAll(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {

    }
    public void updateBusiness(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {

    }
}
