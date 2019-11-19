package store.entity;


import javax.persistence.*;

@Entity
@Table(name = "ordered_products")
public class OrderedProduct {

    @EmbeddedId
    private OrderedProductPK pk;

    @Column(nullable = false)
    private Integer quantity;

    public OrderedProduct() {
    }

    public OrderedProduct(Order order, Product product, Integer quantity) {
        pk = new OrderedProductPK();
        pk.setCustomerOrder(order);
        pk.setOrderProduct(product);
        this.quantity = quantity;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return this.pk.getOrderProduct();
    }


    public Integer getTotalPrice() {
        return getProduct().getPrice() * quantity;
    }
}
