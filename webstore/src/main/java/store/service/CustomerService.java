package store.service;

import store.models.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> getAll();

    void add(Customer customer);

    void delete(Integer id);

    Customer update(Customer customer);

    Customer getOne(Integer id);
}
