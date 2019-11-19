package store.service;

import store.entity.Category;

import java.util.List;

public interface CategoryService {

    List<Category> getAllCategories();

    void addCategory(Category category);

    void editCategory(Category category);

    void deleteCategory(Integer categoryId);
}
