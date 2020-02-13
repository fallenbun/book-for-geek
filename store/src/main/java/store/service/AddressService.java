package store.service;

import store.entity.Address;

public interface AddressService {

    Address getAddress(String email);

    void updateAddress(String email, Address address);
}
