package presentation.model.window;

import dao.AbstractDAO;
import dao.ProductDAO;
import model.Product;
import presentation.add.AddWindow;
import presentation.add.ProductAddWindow;
import presentation.delete.DeleteWindow;
import presentation.delete.ProductDeleteWindow;
import presentation.edit.EditWindow;
import presentation.edit.ProductEditWindow;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ProductWindow extends AbstractWindow {
    /**
     *
     * @param t
     */
    public ProductWindow(AbstractDAO t) {
        super(t,"Products");
    }

    /**
     *
     * @param win
     */
    @Override
    public void doDelete(AbstractWindow win)
    {
        DeleteWindow dw = new ProductDeleteWindow(win,(ProductDAO) gettDAO());
    }

    /**
     *
     * @param win
     */
    @Override
    public void doAdd(AbstractWindow win)
    {
        List<Product> allProducts = gettDAO().findAll(); //todo daca nu am niciun client cum extrag campurile?
        ArrayList<String> fields = gettDAO().getTableFieldsExceptId(allProducts);
        AddWindow window = new ProductAddWindow(win,"Add Product",fields);

    }

    /**
     *
     * @param win
     * @param id
     */
    @Override
    public void doEdit(AbstractWindow win, int id)
    {
        List<Product> all = gettDAO().findAll(); //todo daca nu am niciun client cum extrag campurile?
        ArrayList<String> fields = gettDAO().getTableFields(all);
        EditWindow window = new ProductEditWindow(win.gettDAO(),win,"Edit Product",fields,id);
    }

    /**
     *
     * @return
     */
    @Override
    public DefaultListModel<Product> getListModel()
    {
        DefaultListModel<Product> listModel = new DefaultListModel<>();
        List<Product> list = gettDAO().findAll();
        for(Product c : list)
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
        return ((Product)t).getIdProduct();
    }


}
