package presentation.add;

import model.Product;
import presentation.model.window.AbstractWindow;

import java.util.ArrayList;

public class ProductAddWindow extends AddWindow {

    public ProductAddWindow(AbstractWindow view, String title, ArrayList<String> fields) {
        super(view, title, fields);
    }

    /**
     *
     * @param infoAdd
     * @param view
     */
    @Override
    public void doAdd(ArrayList<String> infoAdd, AbstractWindow view)
    {
       // int id = Integer.parseInt(infoAdd.get(0)); //todo parsare cu regex --bll?
        String nameP = infoAdd.get(0);
        int quantity = Integer.parseInt(infoAdd.get(1));
        Product c = new Product(nameP,quantity);
        view.gettDAO().insert(c);
    }
}
