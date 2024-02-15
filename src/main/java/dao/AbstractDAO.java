package dao;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import connection.ConnectionFactory;

import javax.swing.table.DefaultTableModel;

public class AbstractDAO<T> {
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

    private final Class<T> type;


    public AbstractDAO()
    {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     * method used to create SELECT * query
     * @return
     */
        private String createSelectAllQuery()
        {
            StringBuilder str = new StringBuilder();
            str.append("SELECT ");
            str.append(" * ");
            str.append(" FROM ");
            str.append("`"+type.getSimpleName()+"` "); // pun ` pentru ca order e cuvant rezervat
            return str.toString();

        }

    /**
     * method used to create a select query based on id
     * @param field
     * @return
     */
    private String createSelectQuery(String field)
        {
            StringBuilder str = new StringBuilder();
            str.append("SELECT ");
            str.append(" * ");
            str.append(" FROM ");
            str.append("`"+type.getSimpleName()+"` ");
            str.append(" WHERE "+ field+" =?");
            return str.toString();

        }

    /**
     * method used to create insert query
     * @return
     */
        private String createInsertQuery()
        {
            StringBuilder str = new StringBuilder();
            str.append("INSERT INTO ");
            str.append("`"+type.getSimpleName()+"` ");
            str.append(" ( ");

            int index=0;
            int size = type.getDeclaredFields().length;
            for (Field field : type.getDeclaredFields())
            {

                String fieldName = field.getName();
                if(field.getName().contains("generatedId")==false) {
                    if (index < size - 2) {
                        str.append(fieldName + " , ");
                    } else {
                        str.append(fieldName + " ) ");

                    }
                    index++;
                }
            }
            str.append("  VALUES ( ");
            for(int i = 0 ; i < size-2; i++)
            {
                str.append(" ? , ");
            }
            str.append(" ? )");
            System.out.println("~~~~~~~~~~~~~~~~~~ \n"+str.toString());
            return str.toString();
        }

    /**
     * method used to create update query
     * @param fieldId
     * @return
     */
    private String createUpdateQuery(String fieldId)
        {
            StringBuilder str = new StringBuilder();
            str.append("UPDATE ");
            str.append("`"+type.getSimpleName()+"` ");
            str.append(" SET ");
            int size = type.getDeclaredFields().length ;
            int comma=0;
            for(Field field: type.getDeclaredFields())
            {
                String fieldName = field.getName();

                    if(fieldName.contains("generatedId")==false) {
                        str.append(fieldName + " =? ");
                        if (comma + 1 < size-1) {
                            str.append(" , ");

                        }
                        comma++;
                    }
            }
            str.append(" WHERE " + fieldId + " = ?;");
            System.out.println("aaaaaaaa--------------->>>> "+str.toString());
            return str.toString();
        }

    /**
     * method used to create delete query
     * @param fieldId
     * @return
     */
    private String createDeleteQuery(String fieldId)
        {
            StringBuilder str = new StringBuilder();
            str.append("DELETE FROM ");
            str.append("`"+type.getSimpleName()+"` ");
            str.append(" WHERE ");
            str.append(fieldId+" = ? ;");
            System.out.println("str");
            System.out.println(str);
            return str.toString();
        }


    /**
     * method that get data and transfer it to the database (insert operation)
     * @param t
     * @return
     */
    public T insert(T t)
    {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createInsertQuery();

        try{
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);


            // am nevoie de valori ca sa pot completa "?" din insert_query

            int index=1;
            for (Field field : type.getDeclaredFields())
            {
                field.setAccessible(true);
                if(field.getName().contains("generatedId")==false){

                    T value = (T) field.get(t); // sau object
                    System.out.println("aaa ------->" + value.toString()+"   -  "+field.getName().toString());
                    statement.setObject(index, value);
                    index++;
                }
            }
            statement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO: insert " + e.getMessage());
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } finally{
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }


    /**
     * generic method that returns all rows of a table
     * @return
     */
    public List<T> findAll(){

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectAllQuery();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            return createObjects(resultSet);
        } catch (SQLException throwables) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findAll " + throwables.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * generic method that return the Object with a given id
     * @param id
     * @return
     */
    public T findById(int id)
        {
            Connection connection = null;
            PreparedStatement statement = null;
            ResultSet resultSet = null;

            //get id field name
            String idField = new String();
            for (Field field : type.getDeclaredFields())
            {
                field.setAccessible(true);
                if(field.getName().contains("id"))
                {
                    idField = field.getName();
                    break;
                }
            }
            String query = createSelectQuery(idField);

            try{
                connection = ConnectionFactory.getConnection();
                statement = connection.prepareStatement(query);
                statement.setInt(1, id);
                resultSet = statement.executeQuery();
                List<T> lst = createObjects(resultSet);
                if(resultSet == null || lst==null)
                {
                    System.out.println("e null!!!!!");
                }
                System.out.println("size:::"+ lst.size());
                return lst.get(0);

            }catch(SQLException e){
                LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());

            }finally {
                ConnectionFactory.close(resultSet);
                ConnectionFactory.close(statement);
                ConnectionFactory.close(connection);
            }
            return null;
        }

    /**
     * generic method used for getting all values as an array of strings based on an id
     * @param id
     * @return
     */
        public String[] findValuesById(int id)
        {
            return null;
        }

    /**
     * generic method used for updating an existing row based on an given object
     * @param t
     * @return
     */
    public T updateById(T t)
        {
            Connection connection = null;
            PreparedStatement statement = null;
            String fieldId = this.getTableFields(t).get(0);
            String query = createUpdateQuery(fieldId);

            try
            {
                connection = ConnectionFactory.getConnection();
                statement = connection.prepareStatement(query);
                int index=1;
                T idValue = null;
                System.out.println();
                for (Field field : type.getDeclaredFields())
                {

                    field.setAccessible(true);
                    if(field.getName().contains("generatedId")==false ) {
                        T value = (T) field.get(t);
                        System.out.println("aaa ------->" + value.toString()+"   -  "+field.getName().toString()+ " index "+index);
                        statement.setObject(index, value);

                        if (field.getName().contains("id")) {
                            idValue = value;
                        }
                        index++;
                    }
                }
                System.out.println("id val:::: "+idValue);

                statement.setObject(index, idValue);
                statement.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
                LOGGER.log(Level.WARNING, type.getName() + "DAO:updateById " + e.getMessage());

            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
            }
            return null;
        }


    /**
     * method used for deleting row in table based on an given id
     * @param t
     * @return
     */
    public T deleteById( T t)
        {
            Connection connection = null;
            PreparedStatement statement = null;
            String fieldId = this.getTableFields(t).get(0);
            String query = createDeleteQuery(fieldId);
            T idValue = null;

            try {
                connection = ConnectionFactory.getConnection();
                statement = connection.prepareStatement(query);
                for (Field field : type.getDeclaredFields())
                {
                    field.setAccessible(true);
                    if(field.getName().contains("generatedId")==false) {
                        T value = (T) field.get(t);

                        if (field.getName().contains("id")) {
                            idValue = value;
                            break;
                        }
                    }
                }
                statement.setObject(1, idValue);
                statement.executeUpdate();
            }catch (SQLException e) {
                e.printStackTrace();
                LOGGER.log(Level.WARNING, type.getName() + "DAO:deleteById " + e.getMessage());
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } finally {
                ConnectionFactory.close(statement);
                ConnectionFactory.close(connection);
            }
            return null;
        }


    /**
     * method used for creating a list of objects based on a result set
     * @param resultSet
     * @return
     */
    private List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();
        Constructor[] ctors = type.getDeclaredConstructors();
        Constructor ctor = null;
        for (int i = 0; i < ctors.length; i++) {
            ctor = ctors[i];
            if (ctor.getGenericParameterTypes().length == 0)
                break;
        }
        try {
            while (resultSet.next()) {
                ctor.setAccessible(true);
                T instance = (T)ctor.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    field.setAccessible(true);

                    System.out.println("fields: ==> " + field.getName());
                    if(field.getName().contains("generatedId")==false)
                    {

                        String fieldName = field.getName();
                        Object value = resultSet.getObject(fieldName);
                        PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                        Method method = propertyDescriptor.getWriteMethod();
                        method.invoke(instance, value);
                    }
                }
                list.add(instance);
                System.out.println("added: " + instance.toString()+ "  " + list.size());
            }

            System.out.println("in function: "+ list.size());
            return list;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * method used for getting all elements from a list as a table model
     * @param list
     * @return
     */
    public DefaultTableModel getAllInTable(List<T> list){
        ArrayList<T> all = new ArrayList<T>();
        DefaultTableModel table = new DefaultTableModel();

        int set_columns=1;

        for(Object object:list) {
            String[] values =new String[100];
            int row=0;

            for(Field field:object.getClass().getDeclaredFields()) {
                try {
                    field.setAccessible(true);
                    if(field.getName().contains("generatedId")==false) {
                        if (set_columns == 1) {
                            table.addColumn(field.getName());
                        }
                        values[row++] = field.get(object).toString();
                    }
                }catch(Exception e)
                {
                    System.out.println("Exception in getAllInTable from AbstractDAO.");
                    e.printStackTrace();
                }
            }
            set_columns=0;
            table.addRow(values);
        }
        return table;
    }

    /**
     * method used for getting all table fields
     * @param t
     * @return
     */
    public ArrayList<String> getTableFields(T t)
    {
        ArrayList<String> fieldsTable= new ArrayList<String>();

        Object object = t; // e suficient sa parcurg prima linie din tabel
        int index=0;
        for(Field field: object.getClass().getDeclaredFields())
        {
            field.setAccessible(true);
            if(field.getName().contains("generatedId")==false) {
                fieldsTable.add(field.getName().toString());
            }

        }
        return fieldsTable;
    }

    /**
     * method used for getting all fields of a table
     * @param list
     * @return
     */
    public ArrayList<String> getTableFields(List<T> list)
    {
        ArrayList<String> fieldsTable= new ArrayList<String>();

        Object object = list.get(0); // e suficient sa parcurg prima linie din tabel
        int index=0;
        for(Field field: object.getClass().getDeclaredFields())
        {
            field.setAccessible(true);
            if(field.getName().contains("generatedId")==false) {
                fieldsTable.add(field.getName().toString());
            }
        }
        return fieldsTable;
    }

    /**
     * method used for geting all fields except idField
     * @param list
     * @return
     */

    public ArrayList<String> getTableFieldsExceptId(List<T> list)
    {
        ArrayList<String> fieldsTable= new ArrayList<String>();

        Object object = list.get(0); // e suficient sa parcurg prima linie din tabel
        int index=0;
        for(Field field: object.getClass().getDeclaredFields())
        {

            field.setAccessible(true);
            if(field.getName().contains("generatedId")==false && field.getName().contains("id")==false) {
                fieldsTable.add(field.getName().toString());
            }
        }
        return fieldsTable;
    }

    /**
     *
     * @return
     */
    public int getLastId(){
        return 0;
    }
}
