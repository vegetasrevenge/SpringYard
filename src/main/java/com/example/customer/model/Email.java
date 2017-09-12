package com.example.customer.model;

public class Email {
    private int id;
    private String email;
    private int customerId;

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }


    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }


    public int getCustomerId() { return customerId; }

    public void setCustomerId(int personId) { this.customerId = personId; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Email email1 = (Email) o;

        if (customerId != email1.customerId) return false;
        return email != null ? email.equals(email1.email) : email1.email == null;
    }

    @Override
    public int hashCode() {
        int result = email != null ? email.hashCode() : 0;
        result = 31 * result + customerId;
        return result;
    }

    @Override
    public String toString() {
        return "Email{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", customerId=" + customerId +
                '}';
    }
}
