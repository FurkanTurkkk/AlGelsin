package com.AlGelsin.address_service.service;

import com.AlGelsin.address_service.converter.AddressDtoConverter;
import com.AlGelsin.address_service.dto.CreateAddressRequestDto;
import com.AlGelsin.address_service.exception.AddressNotFoundException;
import com.AlGelsin.address_service.model.Address;
import com.AlGelsin.address_service.repository.AddressRepository;
import com.AlGelsin.address_service.util.FeignClientService;
import org.AlGelsin.AddressDto;
import org.AlGelsin.UserDto;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressService {
    private final AddressRepository addressRepository;
    private final AddressDtoConverter converter;
    private final FeignClientService feignClientService;

    public AddressService(AddressRepository addressRepository, AddressDtoConverter converter, FeignClientService feignClientService) {
        this.addressRepository = addressRepository;
        this.converter = converter;
        this.feignClientService = feignClientService;
    }

    public AddressDto createOrUpdateAddress(Long authId,CreateAddressRequestDto request){
        UserDto userDto = feignClientService.getUserDtoByAuthId(authId);
        String userId = userDto.getId();
        Optional<Address> address = addressRepository.findByCountryAndCityAndTownAndStreetAndApartmentNoAndDoorNo(
                request.getCountry(),
                request.getCity(),
                request.getTown(),
                request.getStreet(),
                request.getApartmentNo(),
                request.getDoorNo()
        );
        if(address.isPresent()){
            return converter.convert(address.get());
        }else {
            Address createdAddress = new Address(
                    userId,
                    request.getCountry(),
                    request.getCity(),
                    request.getTown(),
                    request.getStreet(),
                    request.getApartmentNo(),
                    request.getDoorNo(),
                    request.getZipCode()
            );
            addressRepository.save(createdAddress);
            return converter.convert(createdAddress);
        }
    }

    public AddressDto getAddressByUserId(Long authId){
        String userId = feignClientService.getUserDtoByAuthId(authId).getId();
        Optional<Address> address = addressRepository.findByUserId(userId);
        if(address.isEmpty()){

            throw new AddressNotFoundException("Address could not found for user");
        }
        return converter.convert(address.get());
    }
}
