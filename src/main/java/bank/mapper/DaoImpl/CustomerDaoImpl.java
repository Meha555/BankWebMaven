package bank.mapper.DaoImpl;

import bank.entity.Customer;
import bank.mapper.CustomerDao;

import java.sql.*;
import java.util.ArrayList;

public class CustomerDaoImpl extends BaseDaoImpl implements CustomerDao {
    public Customer findByCid(int cid){
        String sql = "SELECT * FROM customer WHERE CID=?";//注意这里?两端不能加""号，否则pstmt无法解析参数序号
        Customer customer = new Customer();
        try {
            Connection conn=getConn();
            PreparedStatement pstmt =conn.prepareStatement(sql);
            pstmt.setInt(1,cid);
            ResultSet rs=pstmt.executeQuery();
            while(rs.next()){
                customer.setCid(rs.getInt("CID"));
                customer.setCard_id(rs.getString("CARD_ID"));
                customer.setBnumber(rs.getString("BNUMBER"));
                customer.setTime(rs.getString("TIME"));
                customer.setPwd(rs.getString("PWD"));
            }
            rs.close();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return customer;
    }
    public Customer findByBnumber(String bnumber){//业务编号不重复
        String sql="SELECT * FROM customer WHERE BNUMBER=?";
        Customer customer=new Customer();
        try{
            Connection conn=getConn();
            PreparedStatement pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,bnumber);
            ResultSet rs=pstmt.executeQuery();
            while(rs.next()){
                customer.setCid(rs.getInt("CID"));
                customer.setCard_id(rs.getString("CARD_ID"));
                customer.setBnumber(rs.getString("BNUMBER"));
                customer.setTime(rs.getString("TIME"));
                customer.setPwd(rs.getString("PWD"));
            }
            rs.close();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return customer;
    }
    public Customer findByCard_id(String card_id){
        String sql="SELECT * FROM customer WHERE CARD_ID=?";
        Customer customer=new Customer();
        try{
            Connection conn=getConn();
            PreparedStatement pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,card_id);
            ResultSet rs=pstmt.executeQuery();
            while(rs.next()){
                customer.setCid(rs.getInt("CID"));
                customer.setCard_id(rs.getString("CARD_ID"));
                customer.setBnumber(rs.getString("BNUMBER"));
                customer.setTime(rs.getString("TIME"));
                customer.setPwd(rs.getString("PWD"));
            }
            rs.close();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return customer;
    }
    public Customer findByTime(String year,String month,String day){//时间是模糊查询，查询某天的数据
        String sql="SELECT * FROM customer WHERE TIME LIKE ?";//yyyy-MM-dd HH:mm:ss
        Customer customer=new Customer();
        try{
            Connection conn=getConn();
            PreparedStatement pstmt=conn.prepareStatement(sql);
            String time=year+"-"+month+"-"+day+"%";
            pstmt.setString(1,time);
            ResultSet rs=pstmt.executeQuery();
            while(rs.next()){
                customer.setCid(rs.getInt("CID"));
                customer.setCard_id(rs.getString("CARD_ID"));
                customer.setBnumber(rs.getString("BNUMBER"));
                customer.setTime(rs.getString("TIME"));
                customer.setPwd(rs.getString("PWD"));
            }
            rs.close();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return customer;
    }
    public Customer findByPwd(String pwd){
        String sql="SELECT * FROM customer WHERE PWD=?";
        Customer customer=new Customer();
        try{
            Connection conn=getConn();
            PreparedStatement pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,pwd);
            ResultSet rs=pstmt.executeQuery();
            while(rs.next()){
                customer.setCid(rs.getInt("CID"));
                customer.setCard_id(rs.getString("CARD_ID"));
                customer.setBnumber(rs.getString("BNUMBER"));
                customer.setTime(rs.getString("TIME"));
                customer.setPwd(rs.getString("PWD"));
            }
            rs.close();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return customer;
    }
    public ArrayList<Customer> findAll(){//查出来是一个集合
        String sql = "SELECT * FROM customer";
        ArrayList<Customer> list=new ArrayList<>();
        try {
            Connection conn=getConn();
            Statement stmt = conn.createStatement();
            ResultSet rs=stmt.executeQuery(sql);
            while(rs.next()){
                Customer customer=new Customer();
                customer.setCid(rs.getInt("CID"));
                customer.setCard_id(rs.getString("CARD_ID"));
                customer.setBnumber(rs.getString("BNUMBER"));
                customer.setTime(rs.getString("TIME"));
                customer.setPwd(rs.getString("PWD"));
                list.add(customer);
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return list;
    }
    public int addCustomer(Customer customer){
        String sql="INSERT INTO customer values(?,?,?,?,?)";
        int cid=findAll().size()+1;//记得让id+1，id不能为空
        int count=0;
        try {
            Connection conn = getConn();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, cid);
            pstmt.setString(2, customer.getCard_id());
            pstmt.setString(3, customer.getBnumber());
            pstmt.setString(4, customer.getTime());//注意时间
            pstmt.setString(5, customer.getPwd());
            count = pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return count;//受影响的行数
    }
    public int deleteOne(Customer customer){//业务逻辑：先使用个体查询，再对仅有的那行数据删除
        String sql="DELETE FROM customer WHERE CID='"+customer.getCid()+"'";//用主键确定要删除的那行
        int count=0;
        try{
            Connection conn = getConn();
            Statement stmt=conn.createStatement();
            count=stmt.executeUpdate(sql);
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return count;
    }
    public int deleteAll(){//业务逻辑：先使用全体查询，再把全部的数据删除
        String sql="DELETE FROM customer";
        int count=0;
        try{
            Connection conn = getConn();
            Statement stmt=conn.createStatement();
            count=stmt.executeUpdate(sql);
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return count;
    }
    public int updateCustomer(Customer customer){//点击更新按钮，弹出一个对话框，里面要填写表单
        //String sql="UPDATE customer SET CID=?,CARD_ID=?,BNUMBER=?,TIME=?,PWD=?";
        String sql="UPDATE customer SET CARD_ID=?,BNUMBER=?,TIME=?,PWD=? WHERE CID=?";
        int count=0;
        try {
            Connection conn = getConn();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, customer.getCard_id());
            pstmt.setString(2, customer.getBnumber());
            pstmt.setString(3, customer.getTime());//注意时间
            pstmt.setString(4, customer.getPwd());
            pstmt.setInt(5, customer.getCid());//CID是不会变的
            count = pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return count;
    }
}
