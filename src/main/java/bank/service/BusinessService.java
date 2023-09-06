package bank.service;

import bank.entity.Business;

import java.util.ArrayList;

public interface BusinessService {
    public Business findByBid(int bid);
    public Business findByBnumber(String bnumber);
    public Business findByBname(String bname);
    public ArrayList<Business> findAll();
    public int addBusiness(Business business);
    public int deleteOne(Business business);
    public int deleteAll();
    public int updateBusiness(Business business);
}
