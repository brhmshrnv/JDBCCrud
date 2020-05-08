package repository.impl;

import connection.DBConnection;
import model.Category;
import queries.CategoryQueries;
import repository.CategoryRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryRepositoryImpl implements CategoryRepository {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    @Override
    public Category findCategoryById(int id) {

        connection = DBConnection.getConnection();
        Category category =null;

        try {
            //select * from category where categoryId=?
            preparedStatement= connection.prepareStatement(CategoryQueries.findCategoryByIdQuery);
            preparedStatement.setInt(1,id);
            resultSet =preparedStatement.executeQuery();

            if (resultSet.next()) {

                int categoryId= resultSet.getInt("categoryId");
                String categoryName= resultSet.getString("categoryName");
                category= new Category(categoryId,categoryName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DBConnection.closeConnection(connection,preparedStatement,resultSet);
        }

        return category;
    }

    @Override
    public List<Category> findCategories() {

        connection = DBConnection.getConnection();
        List<Category> categories= new ArrayList<>();

        try {
            //select * from category
            preparedStatement= connection.prepareStatement(CategoryQueries.findCategoriesQuery);
            resultSet=preparedStatement.executeQuery();

            while (resultSet.next()) {
                int categoryId= resultSet.getInt("categoryId");
                String categoryName=resultSet.getString("categoryName");
                Category category =new Category(categoryId,categoryName);
                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DBConnection.closeConnection(connection,preparedStatement,resultSet);
        }
        return categories;
    }
}
