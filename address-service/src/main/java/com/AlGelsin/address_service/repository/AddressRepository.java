package com.AlGelsin.address_service.repository;

import com.AlGelsin.address_service.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address,String> {
    Optional<Address> findByUserId(String userId);
    Optional<Address> findByCountryAndCityAndTownAndStreetAndApartmentNoAndDoorNo(String country,
                                                                                  String city,
                                                                                  String town,
                                                                                  String street,
                                                                                  String apartmentNo,
                                                                                  String doorNo);

}
