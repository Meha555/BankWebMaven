package bank.mapper;

import bank.entity.Assign;
import bank.entity.Window;

import java.util.ArrayList;
import java.util.Map;

public interface WindowDao {
    public Window findByWid(int wid);

    public Window findByBnumber(String bnumber);

    public ArrayList<Window> findAll();

    public int addWindow(Window window);

    public int deleteOne(Window window);

    public int deleteAll();

    public int updateWindow(Window window);

    //public ArrayList<Window> findAvailable();
    public ArrayList<Assign> assignWindow();
}
