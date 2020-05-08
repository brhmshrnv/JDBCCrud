package repository.impl;

import connection.DBConnection;
import model.Brand;
import model.Category;
import model.Product;
import queries.ProductQueries;
import queries.UserQueries;
import repository.ProductRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRepositoryImpl implements ProductRepository {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    @Override
    public Product saveProduct(Product product) {
        connection = DBConnection.getConnection();

        try {
            //INSERT INTO product(productId,productName,unitPrice,available,addDate,updateDate,categoryId,brandId) values(?,?,?,?,?,?,?,?)
            preparedStatement = connection.prepareStatement(ProductQueries.saveProductQuery);
            preparedStatement.setInt(1, product.getProductId());
            preparedStatement.setString(2, product.getProductName());
            preparedStatement.setDouble(3,product.getUnitPrice());
            preparedStatement.setInt(4, product.getAvailable());
            preparedStatement.setDate(5,product.getAddDate());
            preparedStatement.setDate(6,product.getUpdateDate());
            preparedStatement.setInt(7,product.getCategory().getCategoryId());
            preparedStatement.setInt(8,product.getBrand().getBrandId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnection(connection, preparedStatement, null);
        }

        return product;
    }

    @Override
    public boolean saveBatchProduct(List<Product> products) {
        connection = DBConnection.getConnection();

        try {

            //INSERT INTO product(productId,productName,unitPrice,available,addDate,updateDate,categoryId,brandId) values(?,?,?,?,?,?,?,?)
            preparedStatement = connection.prepareStatement(ProductQueries.saveProductQuery);

            for (Product product : products) {
                preparedStatement.setInt(1, product.getProductId());
                preparedStatement.setString(2, product.getProductName());
                preparedStatement.setDouble(3, product.getUnitPrice());
                preparedStatement.setInt(4, product.getAvailable());
                preparedStatement.setDate(5, product.getAddDate());
                preparedStatement.setDate(6, product.getUpdateDate());
                preparedStatement.setInt(7, product.getCategory().getCategoryId());
                preparedStatement.setInt(8, product.getBrand().getBrandId());
                preparedStatement.addBatch();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnection(connection, preparedStatement, null);
        }

        return true;


    }

    @Override
    public Product updateProduct(Product product) {
        connection =DBConnection.getConnection();
        //UPDATE product SET productName=?,unitPrice=?,available=?,updateDate=?,categoryId=?,brandId=? where productId=?

        try {
            preparedStatement = connection.prepareStatement(ProductQueries.updateProductQuery);

            preparedStatement.setString(1, product.getProductName());
            preparedStatement.setDouble(2, product.getUnitPrice());
            preparedStatement.setInt(3, product.getAvailable());
            preparedStatement.setDate(4, product.getUpdateDate());
            preparedStatement.setInt(5, product.getCategory().getCategoryId());
            preparedStatement.setInt(6, product.getBrand().getBrandId());
            preparedStatement.setInt(7,product.getProductId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean removeProduct(int id) {

        connection =DBConnection.getConnection();

        try {
            // delete from user_product where productId=?
            preparedStatement = connection.prepareStatement(ProductQueries.deleteUser_ProductQuery);

            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();

            // delete from product where productId=?
            preparedStatement = connection.prepareStatement(ProductQueries.deleteProductByIdQuery);
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

    @Override
    public Product findProductById(int id) {

        connection = DBConnection.getConnection();
        Product product = null;

        try {
            //select * from product where productId=?
            preparedStatement = connection.prepareStatement(ProductQueries.findProductByIdQuery);
            preparedStatement.setInt(1,id);
            resultSet= preparedStatement.executeQuery();

            if (resultSet.next()) {

                int productId=resultSet.getInt("productId");
                String productName = resultSet.getString("productName");
                double unitPrice = resultSet.getDouble("unitPrice");
                int available = resultSet.getInt("available");
                Date addDate = resultSet.getDate("addDate");
                Date updateDate = resultSet.getDate("updateDate");

                int categoryId = resultSet.getInt("categoryId");
                String categoryName = resultSet.getString("categoryName");

                int brandId = resultSet.getInt("brandId");
                String brandName = resultSet.getString("brandName");

                Category category = new Category(categoryId,categoryName);
                Brand brand = new Brand(brandId,brandName);

                product = new Product(productId,productName,unitPrice,available,addDate,updateDate,category,brand);


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            DBConnection.closeConnection(connection,preparedStatement,resultSet);
        }
        return product;
    }

    @Override
    public List<Product> findProducts() {

        connection = DBConnection.getConnection();
        List<Product>products = new ArrayList<>();


        try {
            //select * from product
            preparedStatement = connection.prepareStatement(ProductQueries.findProductsQuery);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {


                int productId=resultSet.getInt("productId");
                String productName = resultSet.getString("productName");
                double unitPrice = resultSet.getDouble("unitPrice");
                int available = resultSet.getInt("available");
                Date addDate = resultSet.getDate("addDate");
                Date updateDate = resultSet.getDate("updateDate");

                int categoryId = resultSet.getInt("categoryId");
                String categoryName = resultSet.getString("categoryName");

                int brandId = resultSet.getInt("brandId");
                String brandName = resultSet.getString("brandName");

                Category category = new Category(categoryId,categoryName);
                Brand brand = new Brand(brandId,brandName);

                Product product = new Product(productId,productName,unitPrice,available,addDate,updateDate,category,brand);
                products.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBConnection.closeConnection(connection,preparedStatement,resultSet);
        }

        return products;
    }
}
