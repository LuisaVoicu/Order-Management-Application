package presentation.delete;



import dao.OrderDAO;
import model.Order;
import presentation.model.window.AbstractWindow;

import javax.swing.*;
import java.util.List;

public class OrderDeleteWindow extends DeleteWindow{
    public OrderDeleteWindow(AbstractWindow win, OrderDAO o) {
        super(win,o);
    }

    /**
     *
     * @param del
     */

    @Override
    public void doDelete(Object del)
    {
        OrderDAO cDAO = (OrderDAO) getT();
        cDAO.deleteById((Order)del);
        System.out.println("DONE!!");
    }
}
