package store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import store.entity.Cart;
import store.entity.Product;
import store.service.CartService;
import store.service.UserService;
import store.service.ProductService;
import store.util.SessionCart;

import javax.servlet.http.HttpServletRequest;

@Controller
@SessionAttributes({"cart"})
public class CartController {

    @Autowired
    CartService cartService;

    @Autowired
    UserService userService;

    @Autowired
    ProductService productService;

    @RequestMapping("/cart")
    public String getCart(HttpServletRequest request, Model model) {
        Cart cart = SessionCart.getCartInSession(request);
        model.addAttribute("cart", cart);
        return "/main/cart";
    }

    @RequestMapping(value = "/buyProduct")
    public String addItemToCart(HttpServletRequest request, @RequestParam Integer productId) {
        Product product = productService.getOneById(productId);
        if (product != null) {
            Cart cart = SessionCart.getCartInSession(request);
            cartService.addCartItem(cart, product);
        }
        return "redirect:/cart";
    }

    @RequestMapping("/cart/item/update")
    public String updateCart(HttpServletRequest request, @RequestParam Integer productId, @RequestParam Integer quantity){
        Product product = productService.getOneById(productId);
        if (product != null) {
            Cart cart = SessionCart.getCartInSession(request);
            cartService.updateCartItem(cart, product, quantity);
        }
        return "redirect:/cart";
    }

    @RequestMapping("/cart/item/delete")
    public String deleteItemFromCart(HttpServletRequest request, @RequestParam Integer productId) {
        Cart cart = SessionCart.getCartInSession(request);
        cartService.removeItemFromCart(cart, productId);
        return "redirect:/cart";
    }

    @RequestMapping("/cart/clear")
    public String clearCart(HttpServletRequest request) {
        Cart cart = SessionCart.getCartInSession(request);
        cartService.removeAllCartItems(cart);
        return "redirect:/cart";
    }
}
