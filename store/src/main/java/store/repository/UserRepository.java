package store.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import store.entity.User;
import store.entity.enums.Role;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "select c from User c where c.role = :role")
    Page<User> findAll(@Param("role")Role role, Pageable pageable);

    @Query(value = "select c from User c where c.userId = :userId")
    User findByUserId(@Param("userId") Integer userId);

    @Query(value = "select c from User c where c.phone = :phone")
    User findByPhone(@Param("phone") String phone);

    @Query(value = "select c from User c where c.email = :email")
    User findByEmail(@Param("email") String email);

}