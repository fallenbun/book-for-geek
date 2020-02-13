package store.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import store.entity.*;
import store.entity.Cart;
import store.entity.CartItem;
import store.entity.enums.OrderStatus;
import store.repository.AddressRepository;
import store.repository.OrderRepository;
import store.service.UserService;
import store.service.OrderService;

import java.time.LocalDate;
import java.util.*;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    UserService userService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<Order> getAllOrders(Pageable pageable) {
        return this.orderRepository.findAll(pageable);
    }

    @Override
    public void createOrder(User user, Cart cart, Address address) {
        Order order = new Order();
        Address one = addressRepository.findOne(Example.of(address)).orElse(address);

        Set<OrderedProduct> orderedProducts = new HashSet<>();
        for (CartItem item : cart.getCartItems()) {
            OrderedProduct orderedProduct = new OrderedProduct(order, item.getProduct(), item.getQuantity());
            orderedProducts.add(orderedProduct);
        }
        if (one.getContactId() == null){
            addressRepository.save(one);
        }
        userService.update(user);
        order.setOrderedProducts(orderedProducts);
        order.setDateCreated(LocalDate.now().plusDays(1));
        order.setOrderStatus(OrderStatus.processing);
        order.setUser(user);
        order.setShippingAddress(one);
        orderRepository.save(order);
        cart.setCartId(Integer.valueOf(order.getOrderId()));
    }

    @Override
    public void delete(Integer orderId) {
        orderRepository.deleteById(orderId);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Order> getCustomerOrders(Integer customerId, Pageable pageable) {
        return orderRepository.findAllOrdersByUserId(customerId, pageable);
    }

    @Override
    public Order getCustomerOrder(Integer customerId, Integer orderId) {
        return orderRepository.findCustomerOrder(customerId, orderId);
    }

    @Override
    @Transactional(readOnly = true)
    public Order getOrderById(Integer orderId) {
        return orderRepository.findOrderById(orderId);
    }

    @Override
    public void update(Order order) {
        orderRepository.updateOrder(order.getOrderStatus(), order.getOrderId());
    }
}
