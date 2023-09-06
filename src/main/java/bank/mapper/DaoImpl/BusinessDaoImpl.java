package bank.mapper.DaoImpl;

import bank.entity.Business;
import bank.mapper.BusinessDao;

import java.sql.*;
import java.util.ArrayList;

public class BusinessDaoImpl extends BaseDaoImpl implements BusinessDao {
    public Business findByBid(int bid){//业务ID不重复
        String sql = "SELECT * FROM business WHERE BID=?";//注意这里?两端不能加""号，否则pstmt无法解析参数序号
        Business business = new Business();
        try {
            Connection conn = getConn();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,bid);
            ResultSet rs=pstmt.executeQuery();
            while(rs.next()){
                business.setBid(rs.getInt("BID"));
                business.setBnumber(rs.getString("BNUMBER"));
                business.setBname(rs.getString("BNAME"));
            }
            rs.close();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return business;
    }
    public Business findByBnumber(String bnumber){//业务编号不重复
        String sql="SELECT * FROM business WHERE BNUMBER=?";
        Business business=new Business();
        try{
            Connection conn = getConn();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,bnumber);
            ResultSet rs=pstmt.executeQuery();
            while(rs.next()){
                business.setBid(rs.getInt("BID"));
                business.setBnumber(rs.getString("BNUMBER"));
                business.setBname(rs.getString("BNAME"));
            }
            rs.close();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return business;
    }
    public Business findByBname(String bname){//业务名不重复
        String sql="SELECT * FROM business WHERE BNAME=?";
        Business business=new Business();
        try{
            Connection conn = getConn();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,bname);
            ResultSet rs=pstmt.executeQuery();
            while(rs.next()){
                business.setBid(rs.getInt("BID"));
                business.setBnumber(rs.getString("BNUMBER"));
                business.setBname(rs.getString("BNAME"));
            }
            rs.close();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return business;
    }
    public ArrayList<Business> findAll(){//查出来是一个集合
        String sql = "SELECT * FROM business";
        ArrayList<Business> list=new ArrayList<>();
        try {
            Connection conn = getConn();
            Statement stmt = conn.createStatement();
            ResultSet rs=stmt.executeQuery(sql);
            while(rs.next()){
                Business business=new Business();
                business.setBid(rs.getInt("BID"));
                business.setBnumber(rs.getString("BNUMBER"));
                business.setBname(rs.getString("BNAME"));
                list.add(business);
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return list;
    }
    public int addBusiness(Business business){
        String sql="INSERT INTO business values(?,?,?)";
        int bid=findAll().size()+1;//记得让id+1，id不能为空
        int count=0;
        try {
            Connection conn = getConn();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, bid);
            pstmt.setString(2, business.getBnumber());
            pstmt.setString(3, business.getBname());
            count = pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return count;
    }
    public int deleteOne(Business business){//业务逻辑：先使用个体查询，再对仅有的那行数据删除
        String sql="DELETE FROM business WHERE BID='"+business.getBid()+"'";//用主键确定要删除的那行
        int count=0;
        try{
            Connection conn = getConn();
            Statement stmt = conn.createStatement();
            count=stmt.executeUpdate(sql);
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return count;
    }
    public int deleteAll(){//业务逻辑：先使用全体查询，再把全部的数据删除
        String sql="DELETE FROM business";
        int count=0;
        try{
            Connection conn = getConn();
            Statement stmt = conn.createStatement();
            count=stmt.executeUpdate(sql);
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return count;
    }
    public int updateBusiness(Business business){//点击更新按钮，弹出一个对话框，里面要填写表单
        //String sql="UPDATE business SET BID=?,BNUMBER=?,BNAME=?";
        String sql="UPDATE business SET BNUMBER=?,BNAME=? WHERE BID=?";
        int count=0;
        try {
            Connection conn = getConn();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, business.getBnumber());
            pstmt.setString(2, business.getBname());
            pstmt.setInt(3, business.getBid());
            count = pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return count;
    }
}
