package com.example.customer.model;

public class Address {
    private int id;
    private String street;
    private String city;
    private String zip;
    private String state;
    private int customerId;

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getStreet() { return street; }

    public void setStreet(String street) { this.street = street; }

    public String getCity() { return city; }

    public void setCity(String city) { this.city = city; }

    public String getZip() { return zip; }

    public void setZip(String zip) { this.zip = zip; }

    public String getState() { return state; }

    public void setState(String state) { this.state = state; }

    public int getCustomerId() { return customerId; }

    public void setCustomerId(int personId) { this.customerId = personId; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        if (customerId != address.customerId) return false;
        if (street != null ? !street.equals(address.street) : address.street != null) return false;
        if (!city.equals(address.city)) return false;
        if (zip != null ? !zip.equals(address.zip) : address.zip != null) return false;
        return state.equals(address.state);
    }

    @Override
    public int hashCode() {
        int result = street != null ? street.hashCode() : 0;
        result = 31 * result + city.hashCode();
        result = 31 * result + (zip != null ? zip.hashCode() : 0);
        result = 31 * result + state.hashCode();
        result = 31 * result + customerId;
        return result;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", zip='" + zip + '\'' +
                ", state='" + state + '\'' +
                ", customerId=" + customerId +
                '}';
    }
}