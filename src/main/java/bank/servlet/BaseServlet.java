package bank.servlet;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * BaseServlet用于实现请求的分发，避免一个功能就要一个Servlet类
 * 其他Servlet要实现功能，则调用它们各自的方法，方法名会在浏览器地址栏显示
 * 所以截取地址栏可以获得调用的方法名
 * 根据条件决定去调用什么对象，什么方法
 */
//@WebServlet("/BaseServlet")
public class BaseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri=request.getRequestURI();//获取统一资源标识符
        String methodName=uri.substring(uri.lastIndexOf('/')+1);//获取方法名，在路径的最后一截
        Class<? extends BaseServlet> cls=this.getClass();//使用类型通配符获取Servlet类名
        try{
            Method method=cls.getMethod(methodName,HttpServletRequest.class,HttpServletResponse.class);
            //getMethod方法：第一个参数name是要获得的方法的名字，后面的参数parameterTypes是按声明顺序标识该方法形参数据类型（就是这个方法的参数列表）
            method.invoke(this,request,response);//通过反射制造“替身”，执行所找到的method方法
        } catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
            System.out.println("Error:"+e.toString());
            System.out.println(e.getCause());
        }
    }
}
