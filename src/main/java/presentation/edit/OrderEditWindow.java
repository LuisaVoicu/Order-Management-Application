package presentation.edit;

import dao.AbstractDAO;
import dao.OrderDAO;
import model.Order;
import presentation.model.window.AbstractWindow;

import java.util.ArrayList;

public class OrderEditWindow extends EditWindow {
    public OrderEditWindow(AbstractDAO t, AbstractWindow view, String title, ArrayList<String> fields, int id) {
        super(t, view, title, fields, id);
    }

    /**
     *
     * @param t
     * @param infoEdit
     * @param id
     * @param view
     */
    @Override
    public void doEditWindow(AbstractDAO t, ArrayList<String> infoEdit, int id, AbstractWindow view)
    {

        int idO =Integer.parseInt(infoEdit.get(0));
        int idC =Integer.parseInt(infoEdit.get(1));
        int idP =Integer.parseInt(infoEdit.get(2));
        int q = Integer.parseInt(infoEdit.get(3));
        String title = infoEdit.get(4);
        Order o = ((OrderDAO)t).findById(id);
        o.setQuantity(q);
        o.setTitle(title);
        t.updateById(o);
    }

}
