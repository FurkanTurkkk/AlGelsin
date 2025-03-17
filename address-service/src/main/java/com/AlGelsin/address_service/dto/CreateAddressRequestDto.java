package com.AlGelsin.address_service.dto;

public class CreateAddressRequestDto {


    private String country;
    private String city;
    private String town;
    private String street;
    private String apartmentNo;
    private String doorNo;
    private String zipCode;

    public CreateAddressRequestDto() {
    }

    public CreateAddressRequestDto(String country, String city, String town, String street, String apartmentNo, String doorNo, String zipCode) {
        this.country = country;
        this.city = city;
        this.town = town;
        this.street = street;
        this.apartmentNo = apartmentNo;
        this.doorNo = doorNo;
        this.zipCode = zipCode;
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
}
