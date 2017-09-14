package com.example.customer.model;



import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;


@Entity
@Table(name="customer")
public class Customer {
    private int id;
    private String firstName;
    private String lastName;
    private String phone;
    private List<Email> emails = new ArrayList<>();
    private Address address;


    public Customer() {
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @Column(name="firstname")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    @Column(name="lastname")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name="phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    //@JsonIgnore
    public void setEmails(List<Email> emails) {
        this.emails = emails;
    }

    @OneToMany(mappedBy="customer", fetch=FetchType.LAZY)
    public List<Email> getEmails() {
        return emails;
    }

    @OneToOne(mappedBy = "customer")
    public Address getAddress() { return address; }

    public void setAddress(Address address) { this.address = address; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        return id == customer.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", emails=" + emails +
                ", address=" + address +
                '}';
    }
}
