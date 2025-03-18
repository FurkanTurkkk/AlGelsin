package com.AlGelsin.address_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String userId;
    private String country;
    private String city;
    private String town;
    private String street;
    private String apartmentNo;
    private String doorNo;
    private String zipCode;

    public Address() {
    }

    public Address(String userId, String country, String city, String town, String street, String apartmentNo, String doorNo, String zipCode) {
        this.userId = userId;
        this.country = country;
        this.city = city;
        this.town = town;
        this.street = street;
        this.apartmentNo = apartmentNo;
        this.doorNo = doorNo;
        this.zipCode = zipCode;
    }

    public String getId() {
        return id;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getTown() {
        return town;
    }

    public String getStreet() {
        return street;
    }

    public String getApartmentNo() {
        return apartmentNo;
    }

    public String getDoorNo() {
        return doorNo;
    }

    public String getZipCode() {
        return zipCode;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(country, address.country) && Objects.equals(city, address.city) && Objects.equals(town, address.town) && Objects.equals(street, address.street) && Objects.equals(apartmentNo, address.apartmentNo) && Objects.equals(doorNo, address.doorNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(country, city, town, street, apartmentNo, doorNo);
    }
}
