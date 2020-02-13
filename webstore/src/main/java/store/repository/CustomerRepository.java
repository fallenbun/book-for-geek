package store.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import store.enums.Role;
import store.models.Customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer>,CrudRepository<Customer,Integer> {

    //@Query(value = "select u from Customer u where u.email like :email")
    //Optional<Customer> findOneByEmail(String email);

    @Query(value = "select * from users where USER_ID like :id",nativeQuery = true)
    Customer findByUserId(@Param("id") Integer id);

    @Query(value = "select * from users where ROLE", nativeQuery = true)
    List<Customer> findAllByRole(@Param("role") Role role);
}
