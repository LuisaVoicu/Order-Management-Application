package presentation.delete;

import dao.ClientDAO;
import model.Client;
import presentation.model.window.AbstractWindow;

import javax.swing.*;
import java.util.List;

public class ClientDeleteWindow<T> extends DeleteWindow{
    public ClientDeleteWindow(AbstractWindow win, ClientDAO o) {
        super(win,o);
    }

    /**
     *
     * @param del
     */
    @Override
    public void doDelete(Object del)
    {
        ClientDAO cDAO = (ClientDAO) getT();
        cDAO.deleteById((Client)del);
    }
}
