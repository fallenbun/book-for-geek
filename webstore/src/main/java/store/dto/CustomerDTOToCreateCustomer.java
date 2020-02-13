package store.dto;

import store.enums.Role;
import store.models.Customer;

public class CustomerDTOToCreateCustomer {

    public static Customer create(CustomerDTO form){
        Customer customer = new Customer();
        customer.setFirstName(form.getFirstName());
        customer.setLastName(form.getLastName());
        customer.setEmail(form.getEmail());
        customer.setPhone(form.getPhone());
        customer.setPassword(form.getPassword());
        customer.setRole(Role.ROLE_USER);
        return customer;
    }
}
