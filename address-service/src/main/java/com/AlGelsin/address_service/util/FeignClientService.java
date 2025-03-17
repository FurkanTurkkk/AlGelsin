package com.AlGelsin.address_service.util;

import com.AlGelsin.address_service.feignclient.UserFeignClient;
import org.AlGelsin.UserDto;
import org.springframework.stereotype.Component;

@Component
public class FeignClientService {

    private final UserFeignClient userFeignClient;

    public FeignClientService(UserFeignClient userFeignClient) {
        this.userFeignClient = userFeignClient;
    }

    public UserDto getUserDtoByAuthId(Long authId){
        return userFeignClient.getUserByAuthId(authId).getBody();
    }

}
