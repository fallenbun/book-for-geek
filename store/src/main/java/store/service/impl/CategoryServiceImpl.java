package store.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import store.entity.Category;
import store.repository.CategoryRepository;
import store.service.CategoryService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    EntityManagerFactory entityManagerFactory;

    @Override
    @Transactional(readOnly = true)
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public void addCategory(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public void editCategory(Category category) {
        Category category1 = categoryRepository.findCategoryByName(category.getCategoryName());
        category1.setCategoryName(category.getCategoryName());
        if (category.getParent() == null) category1.setParent(null);
        else category1.setParent(category.getParent());
        categoryRepository.save(category1);
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        categoryRepository.deleteById(categoryId);
    }
}
