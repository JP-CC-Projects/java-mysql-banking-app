package com.coderscampus.assignment13.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.coderscampus.assignment13.repository.AddressRepository;


@Service
public class AddressService {

    private final AddressRepository addressRepo;
    @Autowired
    public AddressService(AddressRepository addressRepo) {
        this.addressRepo = addressRepo;
    }

}
