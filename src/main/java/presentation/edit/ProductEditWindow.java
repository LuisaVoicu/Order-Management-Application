package presentation.edit;

import dao.AbstractDAO;
import dao.ProductDAO;
import model.Product;
import presentation.model.window.AbstractWindow;

import java.util.ArrayList;

public class ProductEditWindow extends EditWindow {
    public ProductEditWindow(AbstractDAO t, AbstractWindow view, String title, ArrayList<String> fields, int id) {
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
        String productID = infoEdit.get(0);
        String name = infoEdit.get(1);
        String quantity = infoEdit.get(2);
        Product p = ((ProductDAO)t).findById(id);
        p.setNameProduct(infoEdit.get(1));
        p.setQuantity(Integer.parseInt(infoEdit.get(2)));
        t.updateById(p);
    }

}
