package bank.mapper.DaoImpl;
import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;

public class BaseDaoImpl{
    private final String url="jdbc:mysql://localhost:3306/bank?useSSL=false&serverTimezone=GMT%2B8&allowPublicKeyRetrieval=true";
    private final String username="root";
    private final String password="mysql123";
    public Connection getConn(){//创建数据库链接
        Connection conn = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn=DriverManager.getConnection(url,username,password);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.toString());
        }
        return conn;
    }
    /*public void closeConn(){//关闭资源
        try{
            *//*if(rs!=null){
                rs.close();
            }
            if(pstmt!=null){
                pstmt.close();
            }*//*
            if(conn!=null){
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }*/
    /*public int executeCUD(String sql, ArrayList<?> list){//预编译的增删改方法
        int i=1;
        int count=0;
        try {
            getConn();//建立数据库链接
            System.out.println(111111);
            PreparedStatement pstmt=conn.prepareStatement(sql);
            System.out.println(list);
            for(Object obj:list){//每个表的数据类型都不一样，这里使用泛型，然后用多态，使用Object接住list集合的数据
                pstmt.setObject(i,obj);//用Object类型
                i++;
                System.out.println(obj);
            }
            count=pstmt.executeUpdate();
            pstmt.close();
            *//*Statement stmt = conn.createStatement();//创建数据库操作对象
            stmt.executeUpdate(sql);//执行增删改操作
            stmt.close();//正好由内向外关闭*//*
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return count;
    }*/
    /*public ResultSet search(String sql,ArrayList<?> list){//查询方法，因为查询一定知道是查询什么表的什么字段，所以不需要预编译
        int i=1;
        try{
            getConn();
            *//*Statement stmt=conn.createStatement();
            ResultSet rs=stmt.executeQuery(sql);
            ArrayList<cls> list=new ArrayList<>();
            ResultSetMetaData rsmd=rs.getMetaData();
            int columns=rsmd.getColumnCount();
            while(rs.next()){//读取结果集的每一行
                for(int i=0;i<columns;i++){//读取结果集的每一行的每一列
                    list.add(rs.getObject(i+1));
                }
            }
            rs.close();
            stmt.close();*//*
            PreparedStatement pstmt=conn.prepareStatement(sql);
            for(Object obj:list){//每个表的数据类型都不一样，这里使用泛型，然后用多态，使用Object接住list集合的数据
                pstmt.setObject(i,obj);
                i++;
            }
            ResultSet rs=pstmt.executeQuery();
            return rs;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }*/
}
