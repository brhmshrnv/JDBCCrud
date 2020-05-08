package repository.impl;

import connection.DBConnection;
import model.Brand;
import model.Category;
import queries.CategoryQueries;
import queries.ProductQueries;
import repository.BrandRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BrandRepositoryImpl implements BrandRepository {


    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    @Override
    public Brand findBrandById(int id) {

        connection = DBConnection.getConnection();
        Brand brand =null;

        try {
            //select * from category where categoryId=?
            preparedStatement= connection.prepareStatement(ProductQueries.findProductByIdQuery);
            preparedStatement.setInt(1,id);
            resultSet =preparedStatement.executeQuery();

            if (resultSet.next()) {

                int brandId= resultSet.getInt("categoryId");
                String brandName= resultSet.getString("categoryName");
               brand = new Brand(brandId,brandName);
                           }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DBConnection.closeConnection(connection,preparedStatement,resultSet);
        }



        return brand;
    }

    @Override
    public List<Brand> findBrands() {
        connection = DBConnection.getConnection();
        List<Brand> brands= new ArrayList<>();

        try {
            //select * from category
            preparedStatement= connection.prepareStatement(ProductQueries.findProductsQuery);
            resultSet=preparedStatement.executeQuery();

            while (resultSet.next()) {
                int brandId= resultSet.getInt("brandId");
                String brandName=resultSet.getString("brandName");
                Brand brand =new Brand(brandId,brandName);
                brands.add(brand);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DBConnection.closeConnection(connection,preparedStatement,resultSet);
        }
        return brands;
    }
}
