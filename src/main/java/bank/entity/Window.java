package bank.entity;

public class Window {
    private int wid;
    private String bnumber;
    private char is_available;
    public Window(){}

    public int getWid() {
        return wid;
    }

    public void setWid(int wid) {
        this.wid = wid;
    }

    public String getBnumber() {
        return bnumber;
    }

    public void setBnumber(String bnumber) {
        this.bnumber = bnumber;
    }

    public char getIs_available() {
        return is_available;
    }

    public void setIs_available(char is_available) {
        this.is_available = is_available;
    }
}
