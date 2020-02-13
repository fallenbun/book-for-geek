package store.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;
import store.entity.Product;


public interface ProductService {

    Page<Product> getAllProducts(Pageable pageable);

    Page<Product> getAllByCategory(Integer category, Pageable pageable);

    void add(Product product, MultipartFile file);

    Product getOneById(Integer id);

    void deleteProduct(Integer id);
}
