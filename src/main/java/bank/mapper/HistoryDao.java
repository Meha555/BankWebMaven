package bank.mapper;

import bank.entity.History;
import java.util.ArrayList;

public interface HistoryDao {
    public History findByCard_id(String card_id);
    public History findByBnumber(String bnumber);
    public History findByWid(int wid);
    public ArrayList<History> findAll();
    public int addHistory(History history);
    public int deleteOne(History history);
    public int deleteAll();
    public int updateHistory(History history);
    public int findByWidNotNull();
    public int findByWidIsNull();
    public int countAllHistory();
    public int findMaxWid();
    public History findByHid(int wid);
}
