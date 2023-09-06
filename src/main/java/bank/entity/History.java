package bank.entity;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class History {
    private int hid;
    private int cid;
    private String card_id;
    private String bnumber;
    private int wid;
    private Timestamp get_time;
    private Timestamp start_time;
    private String wait_time;

    private char if_complete;
    public History(){}

    public int getHid() {
        return hid;
    }

    public void setHid(int hid) {
        this.hid = hid;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getCard_id() {
        return card_id;
    }

    public void setCard_id(String card_id) {
        this.card_id = card_id;
    }

    public String getBnumber() {
        return bnumber;
    }

    public void setBnumber(String bnumber) {
        this.bnumber = bnumber;
    }

    public int getWid() {
        return wid;
    }

    public void setWid(int wid) {
        this.wid = wid;
    }

    public Timestamp getGet_time() {
        return get_time;
    }
    public void setGet_time() {//设置取号时间
        Date date = new Date();//获得当前时间
        this.get_time = new Timestamp(date.getTime());//将时间转换成Timestamp类型，这样便可以存入到Mysql数据库中
    }
    public void setGet_time(Timestamp get_time) {//设置取号时间
        this.get_time =get_time;
    }
    public Timestamp getStart_time() {
        return start_time;
    }
    public void setStart_time() {//设置开始业务时间
        Date date = new Date();//获得当前时间
        this.start_time = new Timestamp(date.getTime());//将时间转换成Timestamp类型，这样便可以存入到Mysql数据库中
    }
    public void setStart_time(Timestamp start_time) {//设置开始业务时间
        this.start_time = start_time;
    }
    public String getWait_time() {
        return wait_time;
    }
    public void setWait_time(Timestamp get_time,Timestamp start_time) {//设置等待时间
        //SimpleDateFormat timeformat = new SimpleDateFormat("yyyy-MM-dd,HH:mm:ss");
        long t1 = start_time.getTime();
        long t2 = get_time.getTime();
        int hours=(int) ((t1 - t2)/(1000*60*60));
        int minutes=(int) (((t1 - t2)/1000-hours*(60*60))/60);
        int second=(int) ((t1 - t2)/1000-hours*(60*60)-minutes*60);
        this.wait_time =""+hours+"时"+minutes+"分"+second+"秒";
    }
    public void setWait_time(String wait_time) {//设置开始业务时间
        this.wait_time = wait_time;
    }
    public char getIf_complete() {
        return if_complete;
    }

    public void setIf_complete(char if_complete) {
        this.if_complete = if_complete;
    }
    public String toString(){
        return "\nhid:" +hid+
                "\ncid:" +cid+
                "\ncard_id:" +card_id+
                "\nbnumber:" +bnumber+
                "\nwid:" +wid+
                "\nget_time:" +get_time+
                "\nstart_time:" +start_time+
                "\nwait_time:" +wait_time+
                "\nif_complete:"+if_complete;
    }
}