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
    public Customer add(Customer customer) { return customerRepository.save(customer);}

    @Transactional
    @Override
    public void add(List<Customer> customers) {
        for (Customer customer : customers) {
            customerRepository.save(customer);
        }
    }

    @Override
    public Customer getById(int id) {
        return getCustomer(id);
    }

    @Override
    public List<Customer> get() { return customerRepository.findAll(); }

    @Transactional
    @Override
    public void update(Customer customer) {
        customerRepository.save(customer);
    }

    @Transactional
    @Override
    public void delete(int id) {
        Customer customer = this.getCustomer(id);
        emailRepository.delete(customer.getEmails());
        if (customer.getAddress() != null) {
            addressRepository.delete(customer.getAddress());
        }
        customerRepository.delete(id);
    }
    @Transactional
    @Override
    public Customer addAddress(Address address) {
        address = addressRepository.save(address);
        Customer customer = customerRepository.findOne(address.getCustomer().getId());
        customer.setAddress(address);
        customerRepository.save(customer);
        return getCustomer(address.getCustomer().getId());
    }
    @Transactional
    @Override
    public Customer updateAddress(Address address) {
        addressRepository.save(address);
        return getCustomer(address.getCustomer().getId());
    }
    @Transactional
    @Override
    public Customer deleteAddress(int customerId, int addressId) {
        addressRepository.delete(addressId);
        Customer customer = customerRepository.findOne(customerId);
        customer.setAddress(null);
        customerRepository.save(customer);
        return getCustomer(customerId);
    }
    @Transactional
    @Override
    public Customer addEmail(Email email) {
        emailRepository.save(email);
        Customer customer = customerRepository.findOne(email.getCustomer().getId());
        customer.getEmails().add(email);
        customerRepository.save(customer);
        return getCustomer(email.getCustomer().getId());
    }
    @Transactional
    @Override
    public Customer deleteEmail(int customerId, int emailId) {
        Email email = emailRepository.findOne(emailId);
        emailRepository.delete(emailId);
        Customer customer = customerRepository.findOne(email.getCustomer().getId());
        customerRepository.save(customer);
        return getCustomer(customerId);
    }

    private Customer getCustomer(int id) {
        Customer customer = customerRepository.findOne(id);
        customer.getEmails().size();
        return customer;
    }
}
