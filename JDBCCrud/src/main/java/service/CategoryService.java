package service;

import model.Category;

import java.util.List;

public interface CategoryService {


    Category findCategoryById(int id);
    List<Category> findCategories();
}
