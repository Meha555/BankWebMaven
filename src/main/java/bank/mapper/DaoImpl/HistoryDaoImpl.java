package bank.mapper.DaoImpl;

import bank.entity.History;
import bank.mapper.HistoryDao;

import java.sql.*;
import java.util.ArrayList;

public class HistoryDaoImpl extends BaseDaoImpl implements HistoryDao {
    public History findByCard_id(String card_id) {
        String sql = "SELECT * FROM history WHERE CARD_ID=?";//注意这里?两端不能加""号，否则pstmt无法解析参数序号
        History history = new History();
        try {
            Connection conn=getConn();
            PreparedStatement pstmt =conn.prepareStatement(sql);
            pstmt.setString(1,card_id);
            ResultSet rs=pstmt.executeQuery();
            while(rs.next()){
                history.setHid(rs.getInt("HID"));
                history.setCid(rs.getInt("CID"));
                history.setCard_id(rs.getString("CARD_ID"));
                history.setBnumber(rs.getString("BNUMBER"));
                history.setWid(rs.getInt("WID"));
                history.setGet_time(rs.getTimestamp("GET_TIME"));
                history.setStart_time(rs.getTimestamp("START_TIME"));
                history.setWait_time(rs.getString("WAIT_TIME"));
                history.setIf_complete(rs.getString("IF_COMPLETE").charAt(0));//获取char类型数据
            }
            rs.close();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return history;
    }

    public History findByBnumber(String bnumber) {
        String sql = "SELECT * FROM history WHERE BNUMBER=?";//注意这里?两端不能加""号，否则pstmt无法解析参数序号
        History history = new History();
        try {
            Connection conn=getConn();
            PreparedStatement pstmt =conn.prepareStatement(sql);
            pstmt.setString(1,bnumber);
            ResultSet rs=pstmt.executeQuery();
            while(rs.next()){
                history.setHid(rs.getInt("HID"));
                history.setCid(rs.getInt("CID"));
                history.setCard_id(rs.getString("CARD_ID"));
                history.setBnumber(rs.getString("BNUMBER"));
                history.setWid(rs.getInt("WID"));
                history.setGet_time(rs.getTimestamp("GET_TIME"));
                history.setStart_time(rs.getTimestamp("START_TIME"));
                history.setWait_time(rs.getString("WAIT_TIME"));
                history.setIf_complete(rs.getString("IF_COMPLETE").charAt(0));//获取char类型数据
            }
            rs.close();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return history;
    }
    public History findByHid(int hid) {
        String sql = "SELECT * FROM history WHERE HID=?";//注意这里?两端不能加""号，否则pstmt无法解析参数序号
        History history = new History();
        try {
            Connection conn=getConn();
            PreparedStatement pstmt =conn.prepareStatement(sql);
            pstmt.setInt(1,hid);
            ResultSet rs=pstmt.executeQuery();
            while(rs.next()){
                history.setHid(rs.getInt("HID"));
                history.setCid(rs.getInt("CID"));
                history.setCard_id(rs.getString("CARD_ID"));
                history.setBnumber(rs.getString("BNUMBER"));
                history.setWid(rs.getInt("WID"));
                history.setGet_time(rs.getTimestamp("GET_TIME"));
                history.setStart_time(rs.getTimestamp("START_TIME"));
                history.setWait_time(rs.getString("WAIT_TIME"));
                history.setIf_complete(rs.getString("IF_COMPLETE").charAt(0));//获取char类型数据
            }
            rs.close();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return history;
    }
    public History findByWid(int wid) {
        String sql = "SELECT * FROM history WHERE WID=?";//注意这里?两端不能加""号，否则pstmt无法解析参数序号
        History history = new History();
        try {
            Connection conn=getConn();
            PreparedStatement pstmt =conn.prepareStatement(sql);
            pstmt.setInt(1,wid);
            ResultSet rs=pstmt.executeQuery();
            while(rs.next()){
                history.setHid(rs.getInt("HID"));
                history.setCid(rs.getInt("CID"));
                history.setCard_id(rs.getString("CARD_ID"));
                history.setBnumber(rs.getString("BNUMBER"));
                history.setWid(rs.getInt("WID"));
                history.setGet_time(rs.getTimestamp("GET_TIME"));
                history.setStart_time(rs.getTimestamp("START_TIME"));
                history.setWait_time(rs.getString("WAIT_TIME"));
                history.setIf_complete(rs.getString("IF_COMPLETE").charAt(0));//获取char类型数据
            }
            rs.close();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return history;
    }
    public int findByWidNotNull() {//获取已经提交了取号申请，但是还没有分配窗口的元组数目
        String sql = "SELECT COUNT(*) FROM history WHERE WID IS not NULL";//注意这里?两端不能加""号，否则pstmt无法解析参数序号
        int count=0;
        try {
            Connection conn=getConn();
            Statement stmt =conn.createStatement();
            ResultSet rs=stmt.executeQuery(sql);
            if(rs.next()) count=rs.getInt(1);
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return count;
    }
    public int findMaxWid() {
        String sql = "SELECT MAX(HID) FROM history";
        int count=0;
        try {
            Connection conn=getConn();
            Statement stmt =conn.createStatement();
            ResultSet rs=stmt.executeQuery(sql);
            if(rs.next()) count=rs.getInt(1);
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return count;
    }
    public int findByWidIsNull() {//获取已经在窗口办理业务的元组数目
        String sql = "SELECT COUNT(*) FROM history WHERE WID IS NULL";//注意这里?两端不能加""号，否则pstmt无法解析参数序号
        int count=0;
        try {
            Connection conn=getConn();
            Statement stmt =conn.createStatement();
            ResultSet rs=stmt.executeQuery(sql);
            if(rs.next()) count=rs.getInt(1);
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return count;
    }

    public ArrayList<History> findAll() {
        String sql = "SELECT * FROM history";
        ArrayList<History> list=new ArrayList<>();
        try {
            Connection conn=getConn();
            Statement stmt = conn.createStatement();
            ResultSet rs=stmt.executeQuery(sql);
            while(rs.next()){
                History history = new History();
                history.setHid(rs.getInt("HID"));
                history.setCid(rs.getInt("CID"));
                history.setCard_id(rs.getString("CARD_ID"));
                history.setBnumber(rs.getString("BNUMBER"));
                history.setWid(rs.getInt("WID"));
                history.setGet_time(rs.getTimestamp("GET_TIME"));
                history.setStart_time(rs.getTimestamp("START_TIME"));
                history.setWait_time(rs.getString("WAIT_TIME"));
                history.setIf_complete(rs.getString("IF_COMPLETE").charAt(0));//获取char类型数据
                //if_complete必须要加入history表才会有，因此不能在addhistory函数使用findall查询，否则出现空指针异常
                list.add(history);
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return list;
    }
    public int countAllHistory(){
        String sql="SELECT COUNT(*) FROM history";
        int count=0;
        try {
            Connection conn=getConn();
            Statement stmt =conn.createStatement();
            ResultSet rs=stmt.executeQuery(sql);
            if(rs.next()) count=rs.getInt(1);
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return count;
    }
    public int addHistory(History history) {//取号成功就添加记录（还未分配窗口）
        String sql="INSERT INTO history(HID,CID,CARD_ID,BNUMBER,GET_TIME,IF_COMPLETE) values(?,?,?,?,?,?)";
        int hid=countAllHistory()+1;//记得让id+1，id不能为空
        history.setHid(hid);
        int count=0;
        try {
            Connection conn = getConn();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, hid);
            pstmt.setInt(2, history.getCid());
            pstmt.setString(3, history.getCard_id());
            pstmt.setString(4, history.getBnumber());
            pstmt.setTimestamp(5, history.getGet_time());//注意时间
            pstmt.setString(6,"F");//记得设置IF_COMPLETE
            count = pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return count;//受影响的行数
    }

    public int deleteOne(History history) {
        String sql="DELETE FROM history WHERE HID='"+history.getHid()+"'";//用主键确定要删除的那行
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

    public int deleteAll() {
        String sql="DELETE FROM history";
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

    public int updateHistory(History history) {//匹配上窗口后，添加时间和WID（窗口接受了就一定会完成，因为需要客户确认）
        String sql="UPDATE history SET WID=?,START_TIME=?,WAIT_TIME=?,IF_COMPLETE=? WHERE HID=?";
        int count=0;System.out.println("***1");
        History history2=findByHid(history.getHid());
        history.setStart_time();System.out.println("***2");
        Timestamp start_time=history.getStart_time();System.out.println("***3");
        history.setWait_time(history2.getGet_time(),start_time);System.out.println("***4");
        System.out.println("history="+history.toString());
        try {
            Connection conn = getConn();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            System.out.println("***5");
            pstmt.setInt(1,history.getWid());
            pstmt.setTimestamp(2, start_time);
            pstmt.setString(3, history.getWait_time());
            pstmt.setString(4,"T");//标记已完成
            pstmt.setInt(5,history.getHid());
            count = pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return count;//受影响的行数
    }
}
