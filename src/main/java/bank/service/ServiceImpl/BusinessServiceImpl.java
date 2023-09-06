package bank.service.ServiceImpl;

import bank.entity.Business;
import bank.mapper.BusinessDao;
import bank.mapper.DaoImpl.BusinessDaoImpl;
import bank.service.BusinessService;
import java.util.ArrayList;

/**
 * Business是由管理员操作的
 */
//将Dao层的对象创建在Service层中进行，使之更加隐蔽
public class BusinessServiceImpl implements BusinessService {
    BusinessDao bussinessDao=new BusinessDaoImpl();//多态
    public Business findByBid(int bid){
        return bussinessDao.findByBid(bid);
    }
    public Business findByBnumber(String bnumber){
        return bussinessDao.findByBnumber(bnumber);
    }
    public Business findByBname(String bname){
        return bussinessDao.findByBname(bname);
    }
    public ArrayList<Business> findAll(){
        return bussinessDao.findAll();
    }
    public int addBusiness(Business business){
        return bussinessDao.addBusiness(business);
    }
    public int deleteOne(Business business){
        return bussinessDao.deleteOne(business);
    }
    public int deleteAll(){
        return bussinessDao.deleteAll();
    }
    public int updateBusiness(Business business){
        return bussinessDao.updateBusiness(business);
    }
}
