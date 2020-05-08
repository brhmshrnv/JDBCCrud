package repository.impl;

import connection.DBConnection;
import model.Brand;
import model.Category;
import model.Product;
import model.User;
import queries.UserQueries;
import repository.UserRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;


    @Override
    public User saveUser(User user) {

        connection = DBConnection.getConnection();

        try {
            //INSERT INTO user(userId,firstName,lastName,birthOfDate,userName) values(?,?,?,?,?)
            preparedStatement = connection.prepareStatement(UserQueries.saveUserQuery);
            preparedStatement.setInt(1, user.getUserId());
            preparedStatement.setString(2, user.getFirstName());
            preparedStatement.setString(3, user.getLastName());
            preparedStatement.setDate(4, user.getBirthOfDate());
            preparedStatement.setString(5, user.getUserName());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnection(connection, preparedStatement, null);
        }

        return user;
    }

    @Override
    public boolean saveUserProduct(int userId, int productId) {
        connection = DBConnection.getConnection();

        try {
            //INSERT INTO user(userId,productId) values(?,?)
            preparedStatement = connection.prepareStatement(UserQueries.saveUser_ProductQuery);
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, productId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnection(connection, preparedStatement, null);
        }

        return true;
    }

    @Override
    public User updateUser(User user) {

        connection = DBConnection.getConnection();

        try {
            //UPDATE user SET firstName=?,lastName=?,birthOfDate=?,userName=? WHERE userId=?
            preparedStatement = connection.prepareStatement(UserQueries.updateUserQuery);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setDate(3, user.getBirthOfDate());
            preparedStatement.setString(4, user.getUserName());
            preparedStatement.setInt(5, user.getUserId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnection(connection, preparedStatement, null);
        }

        return user;
    }

    @Override
    public boolean removeUser(int userId) {

        connection = DBConnection.getConnection();
        try {
            //DELETE FROM user_product WHERE userId=?
            preparedStatement = connection.prepareStatement(UserQueries.deleteUser_ProductQuery);
            preparedStatement.setInt(1, userId);
            preparedStatement.executeUpdate();
            //DELETE FROM user WHERE userId=?
            preparedStatement = connection.prepareStatement(UserQueries.deleteUserByIdQuery);
            preparedStatement.setInt(1, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            DBConnection.closeConnection(connection, preparedStatement, null);
        }
        return true;
    }

    @Override
    public User findUserById(int userId) {

        connection = DBConnection.getConnection();
        User user = null;

        try {
            //select * from user where userId=?
            preparedStatement = connection.prepareStatement(UserQueries.findUserByIdQuery);
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("userId");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                Date birthOfDate =resultSet.getDate("birthOfDate");
                String userName= resultSet.getString("userName");

                 user = new User(id,firstName,lastName,  birthOfDate,userName);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DBConnection.closeConnection(connection,preparedStatement,resultSet);
        }
        return user;
    }

    @Override
    public User findUserProductById(int userId) {

        connection = DBConnection.getConnection();
        boolean state= true ;
        User user = null;
        List<Product> products = new ArrayList<Product>();
        try {
           /* "SELECT * " +
                    "FROM " +
                    "USER u " +
                    "LEFT OUTER JOIN user_product up ON ( u.userId = up.userId ) " +
                    "LEFT JOIN product p ON ( p.productId = up.productId ) " +
                    "LEFT JOIN category c ON ( c.categoryID = p.categoryId ) " +
                    "LEFT JOIN brand b ON ( b.brandId = p.brandId ) WHERE userId=?";
*/
            preparedStatement = connection.prepareStatement(UserQueries.findUserProductQuery);
            preparedStatement.setInt(1,userId);
            resultSet=preparedStatement.executeQuery();

            while (resultSet.next()){

                if (state){
                    int id = resultSet.getInt("userId");
                    String firstName = resultSet.getString("firstName");
                    String lastName = resultSet.getString("lastName");
                    Date birthOfDate =resultSet.getDate("birthOfDate");
                    String userName= resultSet.getString("userName");

                    user = new User(id,firstName,lastName,  birthOfDate,userName);
                    state= false;
                }
                    int productId=resultSet.getInt("productId");
                    String productName=resultSet.getString("productName");
                    double unitPrice=resultSet.getDouble("unitPrice");
                    int available = resultSet.getInt("available");
                    Date addDate = resultSet.getDate("addDate");
                    Date updateDate = resultSet.getDate("updateDate");

                    int categoryId = resultSet.getInt("categoryId");
                    String categoryName= resultSet.getString("categoryName");

                    int brandId=resultSet.getInt("brandId");
                    String brandName= resultSet.getString("brandName");

                    Category category = new Category(categoryId,categoryName);
                    Brand brand = new Brand(brandId,brandName);
                    Product product = new Product(productId,productName,unitPrice,available,addDate,updateDate,category, brand);

                    products.add(product);
                    user.setProducts(products);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DBConnection.closeConnection(connection,preparedStatement,resultSet);
        }
        return user;
    }

    @Override
    public List<User> findUsers() {

        connection = DBConnection.getConnection();

        List<User> users= new ArrayList<>();
        try {
            //select * from user
            preparedStatement = connection.prepareStatement(UserQueries.findUsersQuery);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){

                int userId= resultSet.getInt("userId");
                String firstName= resultSet.getString("firstName");
                String lastName=resultSet.getString("lastName");
                Date birthOfDate=resultSet.getDate("birthOfDate");
                String userName=resultSet.getString("userName");

                User user = new User(userId,firstName,lastName,birthOfDate,userName);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBConnection.closeConnection(connection,preparedStatement,resultSet);
        }


        return users;
    }
}
