package com.example.customer.service;

import com.example.customer.model.Address;
import com.example.customer.model.Customer;
import com.example.customer.model.Email;

import java.util.List;

public interface CustomerService {
    public Customer add(Customer customer);
    void add(List<Customer> customers);
    Customer getById(int id);
    List<Customer> get();
    void update(Customer customer);
    void delete(int id);

    Customer addAddress(Address address);
    Customer updateAddress(Address address);
    Customer deleteAddress(int customerId, int addressId);

    Customer addEmail(Email email);
    Customer deleteEmail(int customerId, int emailId);
}
