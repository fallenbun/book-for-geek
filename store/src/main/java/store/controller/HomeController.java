package store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import store.entity.Product;
import store.entity.User;
import store.service.CategoryService;
import store.service.ProductService;
import store.service.UserService;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Map;

@Controller
@SessionAttributes(value = "user")

public class HomeController {

    @Autowired
    private ProductService productService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    UserService userService;

    @RequestMapping("/accessDenied")
    public String accessDenied() {
        return "/main/accessDenied";
    }

    @RequestMapping({"/", "/{category}", "catalog", "/catalog/{category}"})
    public String home(Model model,
                       @PathVariable(required = false) Integer category,
                       @PageableDefault(size = 12) Pageable pageable
    ) {
        if (category != null) {
            model.addAttribute("page", productService.getAllByCategory(category, pageable));
            model.addAttribute("url", "/catalog/" + category);
            model.addAttribute("url", "/" + category);
        } else {
            model.addAttribute("page", productService.getAllProducts(pageable));
            model.addAttribute("url", "/catalog/");
            model.addAttribute("url", "/");
        }
        model.addAttribute("categories", categoryService.getAllCategories());
        return "/main/home";
    }

    @RequestMapping("/catalog/book/{id}")
    public ModelAndView getProductDetails(@PathVariable Integer id) {
        Product product = productService.getOneById(id);
        return new ModelAndView("/product/productDetails", "productDetails", product);
    }

    @RequestMapping("/account")
    public String getAccount(@AuthenticationPrincipal User user,
                             Model model){
        model.addAttribute("user", user);
        return "main/account";
    }

    @RequestMapping(value = "/account", method = RequestMethod.POST)
    public String account(@Valid @ModelAttribute("user") User user,
                          BindingResult result,
                          Model model){
        if (result.hasErrors()){
            Map<String, String> errors = ControllerUtils.getErrors(result);
            model.mergeAttributes(errors);
            return "main/account";
        }
        userService.update(user);
        model.addAttribute("success", 200);
        return "main/account";
    }
}
