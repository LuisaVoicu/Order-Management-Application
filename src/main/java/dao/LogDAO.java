package dao;

import connection.ConnectionFactory;
import model.Bill;

import javax.swing.table.DefaultTableModel;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LogDAO {

    /**
     *
     * @return
     */
    private String createInsertQuery()
    {
        StringBuilder str = new StringBuilder();
        str.append("INSERT INTO Log");
        str.append(" ( idOrder, idClient, idProduct, quantity, title) VALUES ( ?, ?, ?, ?, ? ) ");
        return str.toString();
    }

    /**
     *
     * @return
     */
    private String createSelectAllQuery()
    {
        StringBuilder str = new StringBuilder();
        str.append("SELECT * FROM Log");
        return str.toString();
    }


    /**
     *
     * @param b
     */
    public void insert(Bill b) {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createInsertQuery();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);

            int index=1;
            for(Field field: Bill.class.getDeclaredFields())
            {
                field.setAccessible(true);
                Object value = field.get(b);
                statement.setObject(index++,value);
            }
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    /**
     *
     * @return
     */
    public List<Bill> selectAll()
    {
        List<Bill> bills = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectAllQuery();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Bill bill = new Bill(
                        resultSet.getInt("idOrder"),
                        resultSet.getInt("idClient"),
                        resultSet.getInt("idProduct"),
                        resultSet.getInt("quantity"),
                        resultSet.getString("title")
                );
                bills.add(bill);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return bills;
    }

    /**
     *
     * @param list
     * @return
     */
    public DefaultTableModel getAllInTable(List<Bill> list){
        ArrayList<Bill> all = new ArrayList<Bill>();
        DefaultTableModel table = new DefaultTableModel();

        int set_columns=1;

        for(Bill b:list) {
            String[] values =new String[100];
            int row=0;

            for(Field field:b.getClass().getDeclaredFields()) {
                try {
                    field.setAccessible(true);

                        if (set_columns == 1) {
                            table.addColumn(field.getName());
                        }
                        values[row++] = field.get(b).toString();

                }catch(Exception e)
                {
                    System.out.println("Exception in getAllInTable from LogDAO.");
                    e.printStackTrace();
                }
            }
            set_columns=0;
            table.addRow(values);
        }
        return table;
    }

}
