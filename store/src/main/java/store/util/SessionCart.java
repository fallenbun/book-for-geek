package store.util;

import store.entity.Cart;

import javax.servlet.http.HttpServletRequest;

public class SessionCart {

    public static Cart getCartInSession(HttpServletRequest request) {
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            request.getSession().setAttribute("cart", cart);
        }
        return cart;
    }
}
