package bank.servlet;

import bank.entity.Assign;
import bank.entity.Business;
import bank.entity.History;
import bank.entity.Window;
import bank.service.BusinessService;
import bank.service.HistoryService;
import bank.service.ServiceImpl.BusinessServiceImpl;
import bank.service.ServiceImpl.HistoryServiceImpl;
import bank.service.ServiceImpl.WindowServiceImpl;
import bank.service.WindowService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 逻辑：思路1
 * 【处理业务】：员工业务列表展示现有的待处理业务；点击处理按钮，展示表单，填写信息，点击确认或标记异常（驳回）；
 *         若确认，该条业务存入history表中，标记if_complete=T，员工手动点击【叫号】按钮（防止员工不在系统却自动分配新的业务）；
 *         若驳回，该条业务不处理（不填写表单），填写驳回原因，存入history表中，标记if_complete=F；
 * 【叫号】：员工手动点击叫号按钮，点击后向window表中存入is_available=T（不用监听器、要放数据库里是因为可能有多名员工同时叫号）
 *      等待用户确认。确认后执行【处理业务】
 *      若用户取消or若1min内无人确认，则自动执行处理业务中的驳回，驳回原因是“等待超时”，选择第1步中的第2行数据
 *
 * 逻辑：思路2
 * 客户点击取号后，在数据库history表中存入取号的基本信息
 * 管理员进入【处理业务】，查看到当前history表中所有的未处理取号请求和已处理取号请求
 * 管理员点击【一键分配】，进行showAllWindow操作，多表查询出空闲的窗口——客户对，按照HID升序排序，为history表中WID赋值该行对应的WID
 */
@WebServlet("/WindowServlet/*")
public class WindowServlet extends BaseServlet{
    private WindowService windowService=new WindowServiceImpl();
    private HistoryService historyService=new HistoryServiceImpl();
    private BusinessService businessService=new BusinessServiceImpl();
    //分配窗口（叫号）【进行业务完成后调用此方法】：因为需要窗口主动结束上一个业务，所以由window完成
    public void assignWindow(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取HID、BNUMBER
        HttpSession session=request.getSession();
        int hid= (int) session.getAttribute("hid");
        //2.将history表和window表通过业务编号bnumber自然连接，按照HID升序排序，筛选is_available=T的
        ArrayList<Assign> list=windowService.assignWindow();//自然连接
        //3.获取window.WID和history.WID，并存入数据库
        for(Assign ele:list){
            History history=new History();
            if(ele.getHistoryHID()==hid){
                history.setHid(ele.getHistoryHID());
                history.setWid(ele.getWindowWID());
                history.setStart_time();
                historyService.updateHistoryWithWid(history);
                break;
            }
        }
        History history2=historyService.searchHistoryByHid(hid);
        int assign_wid=history2.getWid();
        session.setAttribute("assign_wid",assign_wid);
        System.out.println(assign_wid);
        response.getWriter().write(assign_wid);//设置response对象的值，以供AJAX使用
        //4.将窗口ID，客户ID存入application，完成叫号//5.等待客户确认。若1min内无人确认，则选择第1步中的第2行数据//6.重复3、4、5步骤
        //4.完成分配窗口，无需传参，直接重定向到展示窗口页面
        response.sendRedirect("/BankWebMaven_war/my_business.jsp");//这个页面需要手动点击【展示所有窗口信息】
    }
    //展示所有窗口信息
    public void showAllWindow(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取所有窗口的信息
        HttpSession session=request.getSession();
        //String wid=session.getAttribute("wid").toString();
        ArrayList<Window> list=windowService.findAll();
        //2.返回窗口list集合，回显在前端表格
        session.setAttribute("windowlist",list);
        response.sendRedirect("/BankWebMaven_war/window_list.jsp");
    }
    //展示业务量
    public void showYewuliang(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取所有窗口的信息
        HttpSession session=request.getSession();
        //String wid=session.getAttribute("wid").toString();
        ArrayList<Window> list1=windowService.findAll();
        ArrayList<History> list2=historyService.findAll();
        ArrayList<Business> list3=businessService.findAll();
        //2.返回窗口list集合，回显在前端表格
        session.setAttribute("windowlist",list1);
        session.setAttribute("yewulianglist",list2);
        session.setAttribute("businesslist",list3);
        response.sendRedirect("/BankWebMaven_war/chart.jsp");
    }
}
