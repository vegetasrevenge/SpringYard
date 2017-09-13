package com.example.customer.service;

import com.example.customer.model.Address;
import com.example.customer.model.Email;
import com.example.customer.repository.AddressRepository;
import com.example.customer.repository.CustomerRepository;
import com.example.customer.model.Customer;
import com.example.customer.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    EmailRepository emailRepository;

    @Transactional
    @Override
    public void add(Customer customer) { customerRepository.add(customer);}

    @Transactional
    @Override
    public void add(List<Customer> customers) {
        for (Customer customer : customers) {
            customerRepository.add(customer);
        }
    }

    @Override
    public Customer getById(int id) {
        return getCustomer(id);
    }

    @Override
    public List<Customer> get() {
        return customerRepository.get();
    }

    @Transactional
    @Override
    public void update(Customer customer) {
        customerRepository.update(customer);
    }

    @Transactional
    @Override
    public void delete(int id) {
        customerRepository.delete(id);
    }

    @Override
    public Customer addAddress(Address address) {
        addressRepository.add(address);
        return getCustomer(address.getCustomerId());
    }

    @Override
    public Customer updateAddress(Address address) {
        addressRepository.update(address);
        return getCustomer(address.getCustomerId());
    }

    @Override
    public Customer deleteAddress(int customerId, int addressId) {
        addressRepository.delete(addressId);
        return getCustomer(customerId);
    }

    @Override
    public Customer addEmail(Email email) {
        emailRepository.add(email);
        return getCustomer(email.getCustomerId());
    }

    @Override
    public Customer deleteEmail(int customerId, int emailId) {
        emailRepository.delete(emailId);
        return getCustomer(customerId);
    }

    private Customer getCustomer(int id) {
        Customer customer = customerRepository.getById(id);
        customer.setAddress(addressRepository.findByCustomerId(customer.getId()));
        customer.getEmails().clear();
        customer.getEmails().addAll(emailRepository.findByCustomerId(id));
        System.out.println("\n\n getCustomer " + customer);
        return customer;
    }
}
