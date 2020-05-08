package service.impl;

import model.Category;
import repository.BrandRepository;
import repository.CategoryRepository;
import repository.impl.BrandRepositoryImpl;
import repository.impl.CategoryRepositoryImpl;
import service.CategoryService;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository = new CategoryRepositoryImpl();
    @Override
    public Category findCategoryById(int id) {
        Category category = categoryRepository.findCategoryById(id);
        return category;
    }

    @Override
    public List<Category> findCategories() {
        List<Category> categories = categoryRepository.findCategories();
        return categories;
    }
}
