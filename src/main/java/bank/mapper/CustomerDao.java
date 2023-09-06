package bank.mapper;

import bank.entity.Customer;
import java.util.ArrayList;

public interface CustomerDao {
    public Customer findByCid(int cid);
    public Customer findByBnumber(String bnumber);
    public Customer findByCard_id(String card_id);
    public Customer findByTime(String year,String month,String day);//时间是模糊查询，查询某天的数据
    public Customer findByPwd(String pwd);
    public ArrayList<Customer> findAll();
    public int addCustomer(Customer customer);
    public int deleteOne(Customer customer);
    public int deleteAll();
    public int updateCustomer(Customer customer);

}
