package bll;

import dao.OrderDAO;
import model.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderBLL  {

    public OrderDAO cBLL;
    public OrderBLL()
    {
        cBLL = new OrderDAO();
    }

    public OrderDAO getOrderDAO()
    {
        return cBLL;
    }
    public Order findById(int idClient)
    {

        return cBLL.findById(idClient);
    }


    public Order insert(Order c)
    {
        return cBLL.insert(c);
    }


    public Order updateById( Order c){return cBLL.updateById(c);}

    /**
     *
     * @param c
     * @return
     */
    public Order deleteById( Order c){return cBLL.deleteById(c);}


    public List<Order> findAll()
    {
        return cBLL.findAll();
    }


    public ArrayList<String> getTableFields(List<Order> list){return cBLL.getTableFields(list);}


    public String[] findValuesById(int id)
    {
        return cBLL.findValuesById(id);
    }

    public int getLastId()
    {

        return cBLL.getLastId();
    }

}
