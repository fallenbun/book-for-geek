package store.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import store.entity.Address;
import store.entity.Order;
import store.entity.User;
import store.entity.Cart;

public interface OrderService {

    Page<Order> getAllOrders(Pageable pageable);

    void createOrder(User user, Cart cart, Address address);

    void delete(Integer orderId);

    Page<Order> getCustomerOrders(Integer customerId, Pageable pageable);

    Order getCustomerOrder(Integer customerId, Integer orderId);

    Order getOrderById(Integer id);

    void update(Order order);
}
