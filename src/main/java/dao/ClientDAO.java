package dao;
import connection.ConnectionFactory;
import model.Client;

import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class ClientDAO extends AbstractDAO<Client>{


    /**
     *
     * @param idClient
     * @return
     */
    public Client findById(int idClient)
    {
         return super.findById(idClient);
    }

    /**
     *
     * @param c
     * @return
     */
    public Client insert(Client c)
    {
        return super.insert(c);
    }

    /**
     *
     * @param c
     * @return
     */
    public Client updateById( Client c){return super.updateById(c);}

    /**
     *
     * @param c
     * @return
     */
    public Client deleteById( Client c){return super.deleteById(c);}

    /**
     *
     * @return
     */
    public List<Client> findAll()
    {
        return super.findAll();
    }

    /**
     *
     * @param list
     * @return
     */
    public ArrayList<String> getTableFields(List<Client> list){return super.getTableFields(list);}

    /**
     *
     * @param id
     * @return
     */
    public String[] findValuesById(int id)
    {
        System.out.println("here????");

        Client c = findById(id);
        String [] result = new String[3];
        result[0] = String.valueOf(c.getIdClient());
        result[1] = c.getNameClient();
        result[2] = c.getAddress();
        return result;
    }
    /**
     *
     * @return
     */
    @Override
    public int getLastId()
    {
        List<Client> list =findAll();
        int lastId=0;
        for(Client l:list)
        {
            if(l.getIdClient()>lastId)
            {
                lastId=l.getIdClient();
            }
        }
        return lastId;
    }

}
