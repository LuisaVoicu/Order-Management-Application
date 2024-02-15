package bll;

import dao.ClientDAO;
import model.Client;

import java.util.ArrayList;
import java.util.List;

public class ClientBLL {
    public ClientDAO cBLL;
    public ClientBLL()
    {
        cBLL = new ClientDAO();
    }


    public ClientDAO getClientDAO()
    {
        return cBLL;
    }

    public Client findById(int idClient)
    {

        return cBLL.findById(idClient);
    }


    public Client insert(Client c)
    {
        return cBLL.insert(c);
    }


    public Client updateById( Client c){return cBLL.updateById(c);}

    /**
     *
     * @param c
     * @return
     */
    public Client deleteById( Client c){return cBLL.deleteById(c);}


    public List<Client> findAll()
    {
        return cBLL.findAll();
    }


    public ArrayList<String> getTableFields(List<Client> list){return cBLL.getTableFields(list);}


    public String[] findValuesById(int id)
    {
        return cBLL.findValuesById(id);
    }

    public int getLastId()
    {

        return cBLL.getLastId();
    }

}
