package store.service;

import store.entity.Cart;
import store.entity.Product;

public interface CartService {

    void addCartItem(Cart cart, Product product);

    void updateCartItem(Cart cart, Product product, int quantity);

    void removeItemFromCart(Cart cart, Integer cartItemsId);

    void removeAllCartItems(Cart cart);
}
