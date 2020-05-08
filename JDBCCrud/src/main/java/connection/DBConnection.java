package connection;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DBConnection {

    private static  String dbDriver;
    private static String dbUrl;
    private static String dbUser;
    private static String dbPassword;

   static{
       Properties properties = new Properties();
       try {
           InputStream inputStream = new FileInputStream("database.properties");
           properties.load(inputStream);

            dbDriver = properties.getProperty("db_driver");
            dbUrl = properties.getProperty("db_url");
            dbUser = properties.getProperty("db_user");
            dbPassword =  properties.getProperty("db_password");
       } catch (Exception e) {
           e.printStackTrace();
       }
   }

   public static Connection getConnection(){

       Connection connection = null;

       try {
           Class.forName(dbDriver);
       } catch (ClassNotFoundException e) {
           e.printStackTrace();
       }

       try {
           connection = DriverManager.getConnection(dbUrl,dbUser,dbPassword);

       } catch (SQLException e) {
           e.printStackTrace();
       }
        return connection;
   }

   public static void closeConnection(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet){
       if (resultSet !=null) {
           try {
               resultSet.close();
           } catch (SQLException e) {
               e.printStackTrace();
           }
       }

       if (preparedStatement != null) {
           try {
               preparedStatement.close();
           } catch (SQLException e) {
               e.printStackTrace();
           }
       }

       if (connection !=null) {
           try {
               connection.close();
           } catch (SQLException e) {
               e.printStackTrace();
           }
       }
   }
}
