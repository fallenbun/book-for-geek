package store.entity;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    private Integer cartId;

    private List<CartItem> cartItems = new ArrayList<>();

    private int totalItems;

    private double totalPrice;

    public Cart() {
    }

    public Cart(List<CartItem> cartItems, double totalPrice) {
        this.cartItems = cartItems;
        this.totalPrice = totalPrice;
    }

    public boolean isEmpty(){
        return getTotalItems() == 0;
    }

    public CartItem findItemById(Integer cartItemId){
        for (CartItem item : this.getCartItems()) {
            if (item.getProduct().getProductId().equals(cartItemId)) {
                return item;
            }
        }
        return null;
    }

    public void clear() {
        getCartItems().clear();
        revalidateCart();
    }

    public void revalidateCart() {
        int totalItems = 0;
        double cost = 0;
        for (CartItem item : getCartItems()) {
            totalItems += item.getQuantity();
            cost += item.getQuantity() * item.getProduct().getPrice();
        }
        setTotalPrice(cost);
        setTotalItems(totalItems);
    }

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }
}
