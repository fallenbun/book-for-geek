package store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import store.entity.Category;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @Query(value = "select c from Category c where c.categoryName = :name")
    Category findCategoryByName(@Param("name")String name);

    @Query(value = "select c from Category c where c.parent = :parent")
    List<Category> findSubcategoryByParent(@Param("parent") Integer parent);
}
