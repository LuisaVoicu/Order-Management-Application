package presentation.model.window;

import bll.ClientBLL;
import dao.AbstractDAO;
import dao.ClientDAO;
import model.Client;
import presentation.add.AddWindow;
import presentation.add.ClientAddWindow;
import presentation.delete.ClientDeleteWindow;
import presentation.delete.DeleteWindow;
import presentation.edit.ClientEditWindow;
import presentation.edit.EditWindow;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ClientWindow extends AbstractWindow {

    /**
     *
     * @param t
     */
    public ClientWindow(ClientDAO t) {
        super(t,"Clients");

    }

    /**
     *
     * @param win
     */
    @Override
    public void doDelete(AbstractWindow win)
    {
        System.out.println("am apasat pe delete!!!");
        DeleteWindow dw = new ClientDeleteWindow(win,(ClientDAO) gettDAO());
    }

    /**
     *
     * @param win
     */
    @Override
    public void doAdd(AbstractWindow win)
    {
        List<Client> allClients = gettDAO().findAll(); //todo daca nu am niciun client cum extrag campurile?
        ArrayList<String> fields = gettDAO().getTableFieldsExceptId(allClients);

        AddWindow window = new ClientAddWindow(win,"Add Client",fields);

    }

    /**
     *
     * @param win
     * @param id
     */
    @Override
    public void doEdit(AbstractWindow win, int id)
    {
        List<Client> all = gettDAO().findAll(); //todo daca nu am niciun client cum extrag campurile?
        ArrayList<String> fields = gettDAO().getTableFields(all);

        EditWindow window = new ClientEditWindow(win.gettDAO(),win,"Edit Client",fields,id);
    }

    /**
     *
     * @return
     */
    @Override
    public DefaultListModel<Client> getListModel()
    {
        DefaultListModel<Client> listModel = new DefaultListModel<>();
        List<Client> list = gettDAO().findAll();
        for(Client c : list)
        {
            listModel.addElement(c);
        }
        return listModel;

    }


    @Override
    public int getId(Object t)
    {
        return ((Client)t).getIdClient();
    }
}
