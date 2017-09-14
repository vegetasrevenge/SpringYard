package com.example.customer.controller;

import com.example.customer.model.Address;
import com.example.customer.model.Customer;
import com.example.customer.model.Email;
import com.example.customer.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/api/customer")
    public String addCustomer(@RequestBody String json) throws IOException {
        Customer customer = objectMapper.readValue(json, Customer.class);
        customerService.add(customer);

        return "ok";
    }

    @PutMapping("/api/customer/{id}")
    public String updateCustomer(@PathVariable("id") Integer id,
                                 @RequestBody String json) throws IOException {
        Customer customer = objectMapper.readValue(json, Customer.class);
        customer.setId(id);
        customerService.update(customer);
        return "ok";
    }

    @GetMapping("/api/customers")
    public List<Customer> getAll(Model model) {return customerService.get();}

    @GetMapping("/api/customer/{id}")
    public Customer getCustomer(@PathVariable("id") Integer id) {
        return customerService.getById(id);
    }

    @PostMapping("/api/customer/{id}/address")
    public String addAddress(@PathVariable("id") Integer id,
                             @RequestBody String json) throws IOException {
        Address address = objectMapper.readValue(json, Address.class);
        Customer customer = customerService.getById(id);
        address.setCustomer(customer);
        customerService.addAddress(address);
        return "ok";

    }

    @DeleteMapping("/api/customer/{id}/address/{addressId}")
    public String deleteAddress(@PathVariable("id") Integer id,
                                @PathVariable("addressId") Integer addressId) {
        customerService.deleteAddress(id, addressId);
        return "ok";
    }

    @PostMapping("/api/customer/{id}/email")
    public String addEmail(@PathVariable("id") Integer id,
                           @RequestBody String json) throws IOException {
        Email email = objectMapper.readValue(json, Email.class);
        Customer customer = customerService.getById(id);
        email.setCustomer(customer);
        customerService.addEmail(email);
        return "ok";
    }

    @DeleteMapping("/api/person/{id}/email/{emailId}")
    public String deleteEmail(@PathVariable("id") Integer id,
                              @PathVariable("emailId") Integer emailId) {
        customerService.deleteEmail(id, emailId);
        return "ok";
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleAppException(Exception ex) {
        System.out.println("\n\n### " + ex);
        ex.printStackTrace();
        return ex.getMessage();
    }
}
