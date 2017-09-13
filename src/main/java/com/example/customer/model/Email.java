package com.example.customer.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

public class Email {

    private int id;
    private Customer customer;
    private String email;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="customer_id")
    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Email email = (Email) o;

        return id == email.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Email{" +
                "id=" + id +
                ", customer=" + (customer == null ? "" : customer.getId()) +
                ", email='" + email + '\'' +
                '}';
    }
}
