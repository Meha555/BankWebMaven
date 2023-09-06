package bank.mapper.DaoImpl;

import bank.entity.Assign;
import bank.entity.Window;
import bank.mapper.WindowDao;

import java.sql.*;
import java.util.ArrayList;

public class WindowDaoImpl extends BaseDaoImpl implements WindowDao {
    public Window findByWid(int wid) {
        String sql = "SELECT * FROM `window` WHERE WID=?";//注意这里?两端不能加""号，否则pstmt无法解析参数序号
        Window window = new Window();
        try {
            Connection conn = getConn();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, wid);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                window.setWid(rs.getInt("WID"));
                window.setBnumber(rs.getString("BNUMBER"));
                window.setIs_available(rs.getString("IS_AVAILABLE").charAt(0));
            }
            rs.close();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return window;
    }

    public Window findByBnumber(String bnumber) {
        String sql = "SELECT * FROM `window` WHERE BNUMBER=?";//注意这里?两端不能加""号，否则pstmt无法解析参数序号
        Window window = new Window();
        try {
            Connection conn = getConn();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, bnumber);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                window.setWid(rs.getInt("WID"));
                window.setBnumber(rs.getString("BNUMBER"));
                window.setIs_available(rs.getString("IS_AVAILABLE").charAt(0));
            }
            rs.close();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return window;
    }

    public ArrayList<Window> findAll() {
        String sql = "SELECT WID,BNUMBER,IS_AVAILABLE FROM `window`";
        ArrayList<Window> list = new ArrayList<>();
        try {
            Connection conn = getConn();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Window window = new Window();
                window.setWid(rs.getInt("WID"));
                window.setBnumber(rs.getString("BNUMBER"));
                window.setIs_available(rs.getString("IS_AVAILABLE").charAt(0));
                list.add(window);
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return list;
    }

    /*public ArrayList<Window> findAvailable(){//找出所有可用的窗口
        String sql = "SELECT * FROM `window` WHERE IS_AVAILABLE='T'";
        Window window=new Window();
        ArrayList<Window> list=new ArrayList<>();
        try {
            Connection conn = getConn();
            Statement stmt = conn.createStatement();
            ResultSet rs=stmt.executeQuery(sql);
            while(rs.next()){
                window.setWid(rs.getInt("BID"));
                window.setBnumber(rs.getString("BNUMBER"));
                window.setIs_available(rs.getString("IS_AVAILABLE").charAt(0));
                list.add(window);
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return list;
    }*/
    public ArrayList<Assign> assignWindow() {//找出所有满足分配条件的窗口
        String sql = "SELECT `window`.WID AS windowWID,history.WID AS historyWID,GET_TIME FROM `window`,history WHERE `window`.BNUMBER=history.BNUMBER AND `window`.WID=history.WID AND IS_AVAILABLE='T' ORDER BY HID ASC";
        ArrayList<Assign> list = new ArrayList<>();
        try {
            Connection conn = getConn();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Assign assign = new Assign();
                assign.setWindowWID(rs.getInt("windowWID"));
                assign.setHistoryHID(rs.getInt("historyWID"));
                assign.setGet_time(rs.getTimestamp("GET_TIME"));
                list.add(assign);
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return list;
    }

    public int addWindow(Window window) {//管理员添加一个窗口，当然是可用的
        String sql = "INSERT INTO `window` values(?,?,?)";
        int wid = findAll().size() + 1;//记得让id+1，id不能为空
        int count = 0;
        try {
            Connection conn = getConn();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, wid);
            pstmt.setString(2, window.getBnumber());
            pstmt.setString(3, "T");
            count = pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return count;
    }

    public int deleteOne(Window window) {
        String sql = "DELETE FROM `window` WHERE WID='" + window.getWid() + "'";//用主键确定要删除的那行
        int count = 0;
        try {
            Connection conn = getConn();
            Statement stmt = conn.createStatement();
            count = stmt.executeUpdate(sql);
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return count;
    }

    public int deleteAll() {
        String sql = "DELETE FROM `window`";
        int count = 0;
        try {
            Connection conn = getConn();
            Statement stmt = conn.createStatement();
            count = stmt.executeUpdate(sql);
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return count;
    }

    public int updateWindow(Window window) {//管理员更新窗口信息，不是分配窗口
        String sql = "UPDATE `window` SET BNUMBER=?,IS_AVALIABLE WHERE WID=?";
        int count = 0;
        try {
            Connection conn = getConn();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, window.getBnumber());
            pstmt.setObject(2, window.getIs_available());
            pstmt.setInt(3, window.getWid());
            count = pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return count;
    }
}
