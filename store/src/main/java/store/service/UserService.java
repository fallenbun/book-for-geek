package store.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import store.entity.User;


public interface UserService {

    Page<User> getAllUsers(Pageable pageable);

    boolean add(User user);

    User getOneByEmail(String email);

    User getOneById(Integer id);

    User getOneByPhone(String phone);

    void update(User user);

    void delete(Integer id);
}
