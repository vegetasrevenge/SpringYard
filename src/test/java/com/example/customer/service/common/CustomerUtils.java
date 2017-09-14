package com.example.customer.service.common;

import com.example.customer.model.Address;
import com.example.customer.model.Customer;
import com.example.customer.model.Email;

import java.util.List;
import java.util.Random;

public class CustomerUtils {

    public static Customer createTestCustomer() {
        String firstName = Long.toString(System.currentTimeMillis());
        String lastName = Long.toString(System.currentTimeMillis());

        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        return customer;
    }

    //random number generator
    private static Random random = new Random();

    public static Address createRandomAddress() {
        Address address = new Address();
        address.setStreet(Integer.toString(random.nextInt()));
        address.setCity(Integer.toString(random.nextInt()));
        address.setState("TX");
        address.setZip("12345");
        return address;
    }

    public static Email createRandomEmail() {
        Email email = new Email();
        email.setEmail(Integer.toString(random.nextInt()));
        return email;
    }

    public static Customer findInList(List<Customer> customers, String first, String last) {
        for (Customer customer: customers) {
            if(customer.getFirstName().equals(first) && customer.getLastName().equals(last)) {
                return customer;
            }
        }
        return null;
    }
}
