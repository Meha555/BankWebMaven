package bank.service.ServiceImpl;

import bank.entity.Customer;
import bank.mapper.CustomerDao;
import bank.mapper.DaoImpl.CustomerDaoImpl;
import bank.service.CustomerService;

public class CustomerServiceImpl implements CustomerService {
    CustomerDao customerDao=new CustomerDaoImpl();
    public boolean accountCheck(String card_id){//账号存在--true
        if(card_id.equals(customerDao.findByCard_id(card_id).getCard_id())) return true;
        else return false;
    }

    /*public boolean pwdCheck(String card_id,String pwd) {
        Customer customer=new Customer();
        customer=customerDao.findByCard_id(card_id);
        if(customer.getPwd().equals(pwd)) return true;
        else return false;
    }*/

    public boolean loginCustomer(String card_id,String pwd){//账号和密码匹配--true
        if(customerDao.findByCard_id(card_id).getPwd().equals(pwd)) return true;
        else return false;
    }
    public int registerCustomer(String card_id,String pwd){//账号和密码匹配--true
        Customer customer=new Customer();
        customer.setCard_id(card_id);
        customer.setPwd(pwd);
        return customerDao.addCustomer(customer);
    }
    public Customer searchCustomerByCard_id(String card_id){
        return customerDao.findByCard_id(card_id);
    }

    public Customer searchCustomerByCid(int cid){
        return customerDao.findByCid(cid);
    }
    public int UpdateCustomer(Customer customer){
        return customerDao.updateCustomer(customer);
    }
}
