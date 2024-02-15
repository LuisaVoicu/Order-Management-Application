package dao;
import model.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderDAO extends AbstractDAO<Order> {

    /**
     *
     * @param id
     * @return
     */
    public Order findById(int id)
    {
        return super.findById(id);
    }

    /**
     *
     * @param p
     * @return
     */
    public Order insert(Order p) {
        return super.insert(p);
    }

    /**
     *
     * @param c
     * @return
     */
    public Order updateById(Order c){
        return super.updateById(c);}

    /**
     *
     * @param c
     * @return
     */
    public Order deleteById( Order c){return super.deleteById(c);}

    /**
     *
     * @return
     */
    public List<Order> findAll()
    {
        return super.findAll();
    }

    /**
     *
     * @param list
     * @return
     */
    public ArrayList<String> getTableFields(List<Order> list){return super.getTableFields(list);}

    /**
     *
     * @param id
     * @return
     */
    public String[] findValuesById(int id) {
        System.out.println("my id is::::::: "+id);
        Order c = findById(id);
        String [] result = new String[5];
        result[0] = String.valueOf(c.getIdOrder());
        result[1] =  String.valueOf(c.getIdClient());
        result[2] =  String.valueOf(c.getIdProduct());
        result[3] =  String.valueOf(c.getQuantity());
        result[4] =  c.getTitle();
        return result;
    }

    /**
     *
     * @return
     */
    @Override
    public int getLastId() {
        List<Order> list =findAll();
        int lastId=0;
        for(Order l:list)
        {
            if(l.getIdOrder()>lastId)
            {
                lastId=l.getIdOrder();
            }
        }
        return lastId;
    }


}
