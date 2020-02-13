package store.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class OrderedProductPK implements Serializable {

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    private Order customerOrder;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product orderProduct;

    public OrderedProductPK() {
    }

    public OrderedProductPK(Order customerOrder, Product orderProduct) {
        this.customerOrder = customerOrder;
        this.orderProduct = orderProduct;
    }

    public Order getCustomerOrder() {
        return customerOrder;
    }

    public void setCustomerOrder(Order customerOrder) {
        this.customerOrder = customerOrder;
    }

    public Product getOrderProduct() {
        return orderProduct;
    }

    public void setOrderProduct(Product orderProduct) {
        this.orderProduct = orderProduct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderedProductPK that = (OrderedProductPK) o;
        return Objects.equals(customerOrder, that.customerOrder) &&
                Objects.equals(orderProduct, that.orderProduct);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerOrder, orderProduct);
    }
}
