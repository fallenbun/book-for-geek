package store.service;

import store.enums.Role;
import store.models.Customer;
import store.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Customer> getAll() {
        return customerRepository.findAllByRole(Role.ROLE_USER);
    }

    @Override
    public void add(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public void delete(Integer id) {
        customerRepository.deleteById(id);
    }

    @Override
    public Customer update(Customer customer) {
        Customer newCustomer = customerRepository.getOne(customer.getCustomerId());
        if (newCustomer != null) {
            newCustomer.setFirstName(customer.getFirstName());
            newCustomer.setLastName(customer.getLastName());
            newCustomer.setPhone(customer.getPhone());
            newCustomer.setEmail(customer.getEmail());
            return customerRepository.save(newCustomer);
        } else return customer;
    }

    @Override
    public Customer getOne(Integer id) {
        return customerRepository.findByUserId(id);
    }

}
