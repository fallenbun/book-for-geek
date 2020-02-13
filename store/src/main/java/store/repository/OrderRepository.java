package store.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import store.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    Page<Order> findAll(Pageable pageable);

    @Query(value = "select * from orders o where user_id = :userId", nativeQuery = true)
    Page<Order> findAllOrdersByUserId(@Param("userId") Integer userId, Pageable pageable);

    @Query(value = "select * from orders o where order_id = :orderId", nativeQuery = true)
    Order findOrderById(@Param("orderId") Integer orderId);

    @Query(value = "select * from orders where user_id = :userId and order_id = :orderId", nativeQuery = true)
    Order findCustomerOrder(@Param("userId") Integer userId, @Param("orderId") Integer orderId );

    @Modifying(clearAutomatically = true)
    @Query(value = "update orders set order_status = :orderStatus where order_id = :orderId", nativeQuery = true)
    void updateOrder(@Param("orderStatus")String orderStatus, @Param("orderId") Integer orderId);
}
