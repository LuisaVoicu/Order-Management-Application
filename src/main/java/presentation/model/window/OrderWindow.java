package presentation.model.window;

import dao.*;
import model.Order;
import presentation.add.OrderAddWindow;
import presentation.delete.DeleteWindow;
import presentation.delete.OrderDeleteWindow;
import presentation.edit.EditWindow;
import presentation.edit.OrderEditWindow;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;


public class OrderWindow extends AbstractWindow {
    private ClientDAO clientDAO;
    private ProductDAO productDAO;
    private LogDAO logDAO;

    /**
     *
     * @param o
     * @param c
     * @param p
     * @param log
     */
    public OrderWindow(OrderDAO o, ClientDAO c, ProductDAO p, LogDAO log) {
        super(o,"Orders");
        this.clientDAO = c;
        this.productDAO = p;
        logDAO = log;
    }

    /**
     *
     * @param win
     */
    @Override
    public void doDelete(AbstractWindow win)
    {
        DeleteWindow dw = new OrderDeleteWindow(win,(OrderDAO) gettDAO());
    }

    /**
     *
     * @param win
     */
    @Override
    public void doAdd(AbstractWindow win)
    {
        OrderAddWindow oaw = new OrderAddWindow((OrderDAO) gettDAO(),clientDAO,productDAO,logDAO, "Add Order");
    }

    /**
     *
     * @param win
     * @param id
     */
    @Override
    public void doEdit(AbstractWindow win,int id)
    {
        List<Order> all = gettDAO().findAll();
        ArrayList<String> fields = gettDAO().getTableFields(all);
        EditWindow window = new OrderEditWindow(win.gettDAO(),win,"Edit Order",fields,id);
    }

    /**
     *
     * @return
     */
    @Override
    public DefaultListModel<Order> getListModel()
    {
        DefaultListModel<Order> listModel = new DefaultListModel<>();
        List<Order> list = gettDAO().findAll();
        for(Order c : list)
        {
            listModel.addElement(c);
        }
        return listModel;

    }

    /**
     *
     * @param t
     * @return
     */
    @Override
    public int getId(Object t)
    {
        return ((Order)t).getIdOrder();
    }
}
