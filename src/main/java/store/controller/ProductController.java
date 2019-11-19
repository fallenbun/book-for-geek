package store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import store.entity.Category;
import store.entity.Product;
import store.service.CategoryService;
import store.service.ProductService;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
@SessionAttributes({"editProduct","categories"})
public class ProductController {

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @RequestMapping("/admin/products")
    public String getAllProducts(Model model,
                                 @PageableDefault(size = 12) Pageable pageable) {
        model.addAttribute("page", productService.getAllProducts(pageable));
        model.addAttribute("url", "/admin/products");
        return "/product/products";
    }

    @RequestMapping("/admin/products/add")
    public String getAddProduct(Model model) {
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "/product/addProduct";
    }

    @RequestMapping(value = "/admin/products/add", method = RequestMethod.POST,
            consumes = {"multipart/form-data"})
    public String addProduct(@RequestParam(value = "file") MultipartFile file,
                             @Valid @ModelAttribute("product") Product product,
                             BindingResult result,
                             Model model, SessionStatus sessionStatus){
        if (product.getCategory() == null){
            result.rejectValue("category", "", "Выберите категорию!");
        }
        if (file.getOriginalFilename().isEmpty()){
            result.rejectValue("filename","","Выберите файл!");
        }
        else if (!file.getContentType().contains("image/")){
            result.rejectValue("filename","","Выберите корректный файл!");
        }
        if (result.hasErrors()) {
            Map<String, String> errors = ControllerUtils.getErrors(result);
            model.mergeAttributes(errors);
            return "/product/addProduct";
        }
        productService.add(product, file);
        sessionStatus.setComplete();
        return "redirect:/admin/products";
    }

    @RequestMapping("/admin/products/delete")
    public String deleteProduct(@RequestParam("productId") Integer id) {
        Integer category = productService.getOneById(id).getCategory().getCategoryId();
        productService.deleteProduct(id);
        return "redirect:/" + category;
    }

    @RequestMapping(value = "/admin/products/update")
    public String getUpdateProduct(@RequestParam(value = "productId") Integer id, Model model) {
        Product product = productService.getOneById(id);
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        model.addAttribute("editProduct", product);
        return "/product/updateProduct";
    }

    @RequestMapping(value = "/admin/products/update", method = RequestMethod.POST)
    public String updateProduct(@Valid @ModelAttribute("editProduct") Product product,
                                BindingResult result, @RequestParam(value = "file") MultipartFile file,
                                Model model, SessionStatus sessionStatus) {

        if (product.getCategory() == null){
            result.rejectValue("category", "", "Выберите категорию!");
        }
        if (result.hasErrors()) {
            Map<String, String> errors = ControllerUtils.getErrors(result);
            model.mergeAttributes(errors);
            return "/product/updateProduct";
        }
        productService.add(product, file);
        sessionStatus.setComplete();
        return "redirect:/catalog/book/" + product.getProductId();
    }

    @RequestMapping("/admin/categories")
    public String getCategory (Model model){
        model.addAttribute("categories", categoryService.getAllCategories());
        return "/product/category";
    }

    @RequestMapping(value = "/admin/category/add", method = RequestMethod.POST)
    public String addCategory(@Valid Category category,
                              BindingResult result,
                              Model model){
        if (result.hasErrors()){
            Map<String, String> errors = ControllerUtils.getErrors(result);
            model.mergeAttributes(errors);
            return "/product/category";
        }
        categoryService.addCategory(category);
        return "redirect:/admin/categories";
    }

    @RequestMapping(value = "/admin/category/edit", method = RequestMethod.POST)
    public String editCategory(@Valid Category category,
                               BindingResult result,
                               Model model){
        if (result.hasErrors()){
            Map<String, String> errors = ControllerUtils.getErrors(result);
            model.mergeAttributes(errors);
            return "/product/category";
        }
        categoryService.editCategory(category);
        return "redirect:/admin/categories";
    }

    @RequestMapping(value = "/admin/category/delete")
    public String deleteCategory(@RequestParam("categoryId") Integer categoryId){
        categoryService.deleteCategory(categoryId);
        return "redirect:/admin/categories";
    }
}
