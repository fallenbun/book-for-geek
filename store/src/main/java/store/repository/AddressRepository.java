package store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import store.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
}
