package org.AlGelsin;

public class AddressDto {

    private String id;
    private String userId;
    private String country;
    private String city;
    private String town;
    private String street;
    private String apartmentNo;
    private String doorNo;
    private String zipCode;

    public AddressDto() {
    }

    public AddressDto(String id, String userId, String country, String city, String town, String street, String apartmentNo, String doorNo, String zipCode) {
        this.id = id;
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
}
