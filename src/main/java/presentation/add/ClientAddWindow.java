package presentation.add;

import model.Client;
import presentation.model.window.AbstractWindow;

import java.util.ArrayList;

public class ClientAddWindow extends AddWindow {

    public ClientAddWindow(AbstractWindow view, String title, ArrayList<String> fields) {
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
        String name = infoAdd.get(0);
        String addr = infoAdd.get(1);
        System.out.println("--> "+name+ "00000"+addr);
        Client c = new Client(name,addr);
        view.gettDAO().insert(c);
    }
}
