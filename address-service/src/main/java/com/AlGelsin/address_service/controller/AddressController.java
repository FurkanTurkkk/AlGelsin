package com.AlGelsin.address_service.controller;

import com.AlGelsin.address_service.dto.CreateAddressRequestDto;
import com.AlGelsin.address_service.service.AddressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/address")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createAddress(@RequestHeader("Auth-Id")Long authId,
                                                @RequestBody CreateAddressRequestDto request){
        addressService.createOrUpdateAddress(authId,request);

        return ResponseEntity.ok("Address created successfully");
    }
}
