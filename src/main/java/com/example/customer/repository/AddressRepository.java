package com.example.customer.repository;

import com.example.customer.model.Address;

public interface AddressRepository {
    void add(Address address);
    Address findByCustomerId(int id);
    void update(Address address);
    void delete(int id);
    void deleteByCustomerId(int id);
}
