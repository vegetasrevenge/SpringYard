package com.example.customer.controller;

import com.example.customer.model.Address;
import com.example.customer.model.Customer;
import com.example.customer.model.Email;
import com.example.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class ViewsController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    public String allCustomers (Model model) {
        model.addAttribute("customers", getCustomers()).toString();
        System.out.println(model);
        return "customers";
    }

    @RequestMapping(value = "/customer_detail/{id}", method = RequestMethod.GET)
    public String customerById(@PathVariable Integer id, Model model) {
        Customer customer = getCustomer(id);
        model.addAttribute("customer", customer);
        return "customer_detail";
    }


    @RequestMapping(value = "/add_customer_form", method = RequestMethod.POST)
    public String addCustomer(@Valid Customer customer, @Valid Email email, @Valid Address address, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("message", "Customer submitted!");
        if (bindingResult.hasErrors()) {
            System.out.println("****Input Validation Errors: " + bindingResult + "****");
            return "add_customer";
        } else {
            customerService.add(customer);
            customerService.addEmail(email);
//            customerService.addAddress(address);

            System.out.println(customer);
            return "redirect:/customers";
        }

    }

    public Customer getCustomer(Integer id) {
        Customer customer = customerService.getById(id);
        return customer;
    }

    public List<Customer> getCustomers() {
        List<Customer> customers = customerService.get();
        return customers;
    }

}
