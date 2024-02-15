package presentation.edit;

import dao.AbstractDAO;
import dao.ClientDAO;
import model.Client;
import presentation.model.window.AbstractWindow;

import java.util.ArrayList;

public class ClientEditWindow<T> extends EditWindow {

    /**
     *
     * @param t
     * @param view
     * @param title
     * @param fields
     * @param id
     */
    public ClientEditWindow (AbstractDAO t, AbstractWindow view, String title, ArrayList<String> fields, int id)
    {
        super(t, view,title,fields,id);
        System.out.println("helloooooo from client edit window ??");

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
        String clientId = infoEdit.get(0);
        String name = infoEdit.get(1);
        String addr = infoEdit.get(2);
        System.out.println("---------------------->"+clientId+ " " + name + " "+ addr);
        Client c = ((ClientDAO)t).findById(id);
        c.setNameClient(infoEdit.get(1));
        c.setAddress(infoEdit.get(2));
        t.updateById(c);
    }


}
