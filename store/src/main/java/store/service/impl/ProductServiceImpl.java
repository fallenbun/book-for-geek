package store.service.impl;

import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import store.entity.Product;
import store.repository.ProductRepository;
import store.service.ProductService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Value("${upload.path}")
    private String uploadPath;

    @Override
    @Transactional(readOnly = true)
    public Page<Product> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Product> getAllByCategory(Integer category, Pageable pageable) {
        return productRepository.findAllByCategoryId(category, pageable);
    }

    @Override
    public void add(Product product, MultipartFile file){
        uploadFile(product, file);
        productRepository.save(product);
    }

    @Override
    @Transactional(readOnly = true)
    public Product getOneById(Integer id) {
        return productRepository.findByProductId(id);
    }

    @Override
    public void deleteProduct(Integer id) {
        String filename = productRepository.findByProductId(id).getFilename();
        Path path = Paths.get(uploadPath + "/" + filename);
        if (Files.exists(path)){
            try {
                Files.delete(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        productRepository.deleteById(id);
    }

    private void uploadFile(Product product, MultipartFile file) {
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            String filename = product.getFilename();
            if (filename != null){
                Path path = Paths.get(uploadPath + "/" + filename);
                if (Files.exists(path)){
                    try {
                        Files.delete(path);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String originalFilename = file.getOriginalFilename();
            String substring = originalFilename.substring(originalFilename.lastIndexOf('.'));
            String fileId = RandomString.make(8);
            String resultFilename = fileId + substring;

            try {
                file.transferTo(new File(uploadPath + "/" + resultFilename));
            } catch (IOException e) {
                e.printStackTrace();
            }
            product.setFilename(resultFilename);
        }
    }
}
