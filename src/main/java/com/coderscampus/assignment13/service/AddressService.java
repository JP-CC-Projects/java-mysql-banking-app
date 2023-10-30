package com.coderscampus.assignment13.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.coderscampus.assignment13.domain.Address;
import com.coderscampus.assignment13.domain.User;
import com.coderscampus.assignment13.repository.AddressRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class AddressService {

    private final AddressRepository addressRepo;

    @Autowired
    public AddressService(AddressRepository addressRepo) {
        this.addressRepo = addressRepo;
    }


    // Find address by user ID
    public Optional<Address> findAddressByUserId(Long userId) {
        return addressRepo.findById(userId);  // Since the address has user's ID as its PK.
    }

    // Save or update an address
    public Address saveOrUpdateAddress(Address address) {
        return addressRepo.save(address);
    }

    // Delete an address by user ID
    public void deleteAddressByUserId(Long userId) {
        addressRepo.deleteById(userId);
    }

    // Find all addresses (may not be frequently used given the nature of the data)
    public Iterable<Address> findAllAddresses() {
        return addressRepo.findAll();
    }
    public User saveUserAddress(User user) {

        if (user.getAddress().getUserId() != null) {
            addressRepo.save(user.getAddress());
            System.out.println("User address is being saved to DB: " + user);
        }
        return user;
    }
}
