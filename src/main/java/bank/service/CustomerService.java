package bank.service;

import bank.entity.Customer;

public interface CustomerService {
    public boolean accountCheck(String card_id);
    //public boolean pwdCheck(String card_id,String pwd);
    public boolean loginCustomer(String card_id,String pwd);
    public int registerCustomer(String card_id,String pwd);
    public Customer searchCustomerByCard_id(String card_id);
    public Customer searchCustomerByCid(int cid);
    public int UpdateCustomer(Customer customer);
}
