package connection;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionFactory {
    private static final Logger LOGGER = Logger.getLogger(ConnectionFactory.class.getName());
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DBURL = "jdbc:mysql://localhost:3306/ordermanagementdb";
    private static final String USER = "root";
    private static final String PASS = "Luisa2001.";

    private static ConnectionFactory singleInstance = new ConnectionFactory();

    /**
     * Connection Factory constructor
     */
    private ConnectionFactory()
    {
        try{
            Class.forName(DRIVER);

        }catch(ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Method that create the connection with database
     * @return
     */
    private Connection createConnection()
    {
        Connection connection = null;
        try{
            connection= DriverManager.getConnection(DBURL,USER,PASS);

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "An error occured while trying to connect to the database");
            e.printStackTrace();
        }
        return connection;
    }


    public static Connection getConnection() {
        return singleInstance.createConnection();
    }

    /**
     * method used to close the connection
     * @param connection
     */
    public static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "An error occured while trying to close the connection");
            }
        }
    }

    /**
     * method used to close the connection using a statement
     * @param statement
     */
    public static void close(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "An error occured while trying to close the statement");
            }
        }
    }

    /**
     * method used to close the connection using a result set
     * @param resultSet
     */

    public static void close(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "An error occured while trying to close the ResultSet");
            }
        }
    }
}
