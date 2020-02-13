package store.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import store.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    Page<Product> findAll(Pageable pageable);

    @Query(value = "select * from products p where product_id = :productId", nativeQuery = true)
    Product findByProductId(@Param("productId") Integer productId);

    @Query(value = "select * from products p left join category c on p.category_id = c.category_id" +
            " where c.category_id=:categoryId or c.parent_id=:categoryId", nativeQuery = true)
    Page<Product> findAllByCategoryId(@Param("categoryId")Integer categoryId, Pageable pageable);
}
