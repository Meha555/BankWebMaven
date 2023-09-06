package bank.entity;

import java.sql.Timestamp;

public class Assign {
    private int windowWID;
    private int historyHID;
    private Timestamp get_time;
    public Assign(){}

    public int getWindowWID() {
        return windowWID;
    }

    public void setWindowWID(int windowWID) {
        this.windowWID = windowWID;
    }

    public int getHistoryHID() {
        return historyHID;
    }

    public void setHistoryHID(int historyHID) {
        this.historyHID = historyHID;
    }
    public Timestamp getGet_time() {
        return get_time;
    }
    public void setGet_time(Timestamp get_time) {//设置取号时间
        this.get_time =get_time;
    }
}
