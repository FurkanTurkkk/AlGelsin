package com.AlGelsin.address_service.converter;

import com.AlGelsin.address_service.model.Address;
import org.AlGelsin.AddressDto;
import org.springframework.stereotype.Component;

@Component
public class AddressDtoConverter {

    public AddressDto convert(Address address){
        return new AddressDto(
                address.getId(),
                address.getUserId(),
                address.getCountry(),
                address.getCity(),
                address.getTown(),
                address.getStreet(),
                address.getApartmentNo(),
                address.getDoorNo(),
                address.getZipCode()
        );
    }
}
