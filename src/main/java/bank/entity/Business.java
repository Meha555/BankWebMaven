package bank.entity;

public class Business {
    private int bid;
    private String bnumber;
    private String bname;
    public Business(){}
    public void setBid(int bid){
        this.bid=bid;
    }
    public int getBid(){
        return bid;
    }
    public void setBnumber(String bnumber){
        this.bnumber=bnumber;
    }
    public String getBnumber(){
        return bnumber;
    }
    public void setBname(String bname){
        this.bname=bname;
    }
    public String getBname(){
        return bname;
    }
}
