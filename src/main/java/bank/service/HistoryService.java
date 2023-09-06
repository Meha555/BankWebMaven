package bank.service;


import bank.entity.History;

import java.util.ArrayList;

public interface HistoryService {
    public History searchHistoryByCard_id(String card_id);
    public History searchHistoryByBnumber(String Bnumber);
    public History searchHistoryByWid(int wid);
    public int addHistoryWithoutWid(History history);
    public int updateHistoryWithWid(History history);
    public int numberOfWidNotNull();
    public int numberOfWidIsNull();
    public int numberOfMaxWid();
    public History searchHistoryByHid(int hid);
    public ArrayList<History> findAll();
}
