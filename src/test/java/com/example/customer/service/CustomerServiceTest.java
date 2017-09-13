package com.example.customer.service;

import com.example.customer.model.Address;
import com.example.customer.model.Customer;
import com.example.customer.model.Email;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceTest {

	@Autowired
	CustomerService customerService;

	//need to refactor tests to test transactions and everything else OR
	//simply add address and email testing to all tests


	@Test
	public void testAddGet() {

		Customer customer1 = createTestCustomer();
		Customer customer2 = createTestCustomer();

		customerService.add(customer1);
		customerService.add(customer2);

		List<Customer> customers = customerService.get();

		Customer customer1Retrieved =
				findInList(customers, customer1.getFirstName(), customer1.getLastName());

		Assert.assertNotNull("The first customer should exist", customer1Retrieved);

	}

	@Test
	public void testUpdate() {
		Customer customer1 = createTestCustomer();


		customerService.add(customer1);
		List<Customer> customers = customerService.get();

		Customer customer2 = findInList(customers, customer1.getFirstName(), customer1.getLastName());
		customerService.add(customer2);
		Assert.assertNotNull(customerService);

		String updateFirstName = Long.toString(System.currentTimeMillis());
		String updateLastName = Long.toString(System.currentTimeMillis());

		customer2.setFirstName(updateFirstName);
		customer2.setLastName(updateLastName);

		customerService.update(customer2);

		customers = customerService.get();

		Customer customer3 = findInList(customers, updateFirstName, updateLastName);

		Assert.assertNotNull(customer3);
		Assert.assertEquals(customer2.getId(), customer3.getId());

	}

	@Test
	public void testDelete() {

		//logical path:
		//create an object instance, add it to the interface implement class
		//create a list to test against, add the current objects within implement class
		//create a new object instance, add the first object instance values, then test for existence
		//delete from the implement class the ID of the second instance (which is the same as the first?)
		//now assign the current implement class contents to the list, updating it
		//then create a third instance of the object, and add the values from the list to it.
		//the test should return null, since the instance was deleted previously. If it was not deleted, check the implement code.
		Customer customer1 = createTestCustomer();

		customerService.add(customer1);

		List<Customer> customers = customerService.get();

		Customer customer2 = findInList(customers, customer1.getFirstName(), customer1.getLastName());
		Assert.assertNotNull(customer2);

		customerService.delete(customer2.getId());

		customers = customerService.get();
		Customer customer3 = findInList(customers, customer1.getFirstName(), customer1.getLastName());

		Assert.assertNull(customer3);
	}

	private Customer createTestCustomer() {
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

	private Customer findInList(List<Customer> customers, String first, String last) {
		for (Customer customer: customers) {
			if(customer.getFirstName().equals(first) && customer.getLastName().equals(last)) {
				return customer;
			}
		}
		return null;
	}
}