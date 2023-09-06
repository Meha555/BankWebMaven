package bank.service.ServiceImpl;

import bank.entity.Assign;
import bank.entity.Window;
import bank.mapper.DaoImpl.WindowDaoImpl;
import bank.mapper.WindowDao;
import bank.service.WindowService;

import java.util.ArrayList;
import java.util.Map;

public class WindowServiceImpl implements WindowService {
    WindowDao windowDao = new WindowDaoImpl();

    public Window findByWid(int wid) {
        return windowDao.findByWid(wid);
    }

    public Window findByBnumber(String bnumber) {
        return windowDao.findByBnumber(bnumber);
    }

    /*public ArrayList<Window> findAvailable() {
        return windowDao.findAvailable();
    }*/

    public ArrayList<Window> findAll() {
        return windowDao.findAll();
    }

    public int addWindow(Window window) {
        return windowDao.addWindow(window);
    }

    public int deleteOne(Window window) {
        return windowDao.deleteOne(window);
    }

    public int deleteAll() {
        return windowDao.deleteAll();
    }

    public int updateWindow(Window window) {
        return windowDao.updateWindow(window);
    }

    public ArrayList<Assign> assignWindow() {
        return windowDao.assignWindow();
    }
}
