package store.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import store.entity.Address;
import store.entity.User;
import store.repository.AddressRepository;
import store.repository.UserRepository;
import store.service.AddressService;

@Service
@Transactional
public class AddressServiceImpl implements AddressService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Override
    @Transactional(readOnly = true)
    public Address getAddress(String email) {

        return null;
    }

    @Override
    public void updateAddress(String email, Address newAddress) {
        User user = userRepository.findByEmail(email);


    }
}
