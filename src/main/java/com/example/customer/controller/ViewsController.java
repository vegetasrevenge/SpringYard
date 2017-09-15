package com.example.customer.controller;

import com.example.customer.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class ViewsController {

    @Autowired
    private CustomerController customerController;

    @RequestMapping("/customers")
    public String allCustomers (Model model) {
        model.addAttribute("customers", getCustomers());
        System.out.println(model);
        return "customers";
    }

    public static List<Customer> getCustomers() {
        RestTemplate restTemplate = new RestTemplate();
        Customer[] customers = restTemplate.getForObject("http://localhost:8080/api/customers", Customer[].class);
        return Arrays.asList(customers);

    }

}
