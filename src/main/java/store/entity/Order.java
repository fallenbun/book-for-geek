package store.entity;

import store.entity.enums.OrderStatus;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer orderId;

    @Column(name = "date_created")
    private LocalDate dateCreated;

    @Column(name = "order_status")
    private String orderStatus;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "contact_id")
    private Address shippingAddress;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "pk.customerOrder")
    private Set<OrderedProduct> orderedProducts = new HashSet<>();

    public Order() {
    }

    public Order(LocalDate dateCreated, OrderStatus orderStatus, Address shippingAddress) {
        this.dateCreated = dateCreated;
        this.orderStatus = orderStatus.getTitle();
        this.shippingAddress = shippingAddress;
    }

    @Transient
    public Integer getTotalOrderPrice() {
        int sum = 0;
        Set<OrderedProduct> orderedProducts = getOrderedProducts();
        for (OrderedProduct op : orderedProducts) {
            sum += op.getTotalPrice();
        }
        return sum;
    }

    @Transient
    public int getNumberOfProducts() {
        int count = 0;
        Set<OrderedProduct> orderedProducts = getOrderedProducts();
        for (OrderedProduct op : orderedProducts) {
            count += op.getQuantity();
        }
        return count;
    }

    public Set<OrderedProduct> getOrderedProducts() {
        return orderedProducts;
    }

    public void setOrderedProducts(Set<OrderedProduct> orderedProducts) {
        this.orderedProducts = orderedProducts;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus.getTitle();
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }
}
