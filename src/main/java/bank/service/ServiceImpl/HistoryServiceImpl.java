package bank.service.ServiceImpl;

import bank.entity.History;
import bank.mapper.DaoImpl.HistoryDaoImpl;
import bank.mapper.HistoryDao;
import bank.service.HistoryService;

import java.util.ArrayList;

public class HistoryServiceImpl implements HistoryService {
    HistoryDao historyDao = new HistoryDaoImpl();

    public History searchHistoryByCard_id(String card_id) {
        return historyDao.findByCard_id(card_id);
    }

    public History searchHistoryByBnumber(String Bnumber) {
        return historyDao.findByBnumber(Bnumber);
    }

    public History searchHistoryByWid(int wid) {
        return historyDao.findByWid(wid);
    }

    public int addHistoryWithoutWid(History history) {
        return historyDao.addHistory(history);
    }
    public int updateHistoryWithWid(History history){
        return historyDao.updateHistory(history);
    }
    public int numberOfWidNotNull() {
        return historyDao.findByWidNotNull();
    }
    public int numberOfMaxWid() {
        return historyDao.findMaxWid();
    }
    public int numberOfWidIsNull(){
        return historyDao.findByWidIsNull();
    }
    public History searchHistoryByHid(int hid) {
        return historyDao.findByHid(hid);
    }
    public ArrayList<History> findAll(){
        return historyDao.findAll();
    }
}
