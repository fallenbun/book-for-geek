package store.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import store.entity.*;
import store.entity.Cart;
import store.entity.CartItem;
import store.service.CartService;

@Service
@Transactional
public class CartServiceImpl implements CartService {

    @Override
    public void addCartItem(Cart cart, Product product) {
        CartItem item = cart.findItemById(product.getProductId());
        if (cart.getCartItems().contains(item)) {
            item.setQuantity(item.getQuantity() + 1);
        } else {
            item = new CartItem(cart, product, 1);
            cart.getCartItems().add(item);
        }
        cart.revalidateCart();
    }

    @Override
    public void updateCartItem(Cart cart, Product product, int quantity) {
        CartItem item = cart.findItemById(product.getProductId());
        if (cart.getCartItems().contains(item)) {
            item.setQuantity(quantity);
        }
        cart.revalidateCart();
    }

    @Override
    public void removeItemFromCart(Cart cart, Integer cartItemsId) {
        CartItem item = cart.findItemById(cartItemsId);
        if (item != null) {
            cart.getCartItems().remove(item);
        }
        else return;
        cart.revalidateCart();
    }

    @Override
    public void removeAllCartItems(Cart cart) {
        cart.clear();
    }
}
