package presentation.delete;

import dao.ProductDAO;
import model.Product;
import presentation.model.window.AbstractWindow;

import javax.swing.*;
import java.util.List;

public class ProductDeleteWindow extends DeleteWindow{
    public ProductDeleteWindow(AbstractWindow win, ProductDAO o) {
        super(win, o);
    }

    /**
     *
     * @param del
     */
    @Override
    public void doDelete(Object del)
    {
        ProductDAO cDAO = (ProductDAO) getT();
        cDAO.deleteById((Product)del);
    }
}
