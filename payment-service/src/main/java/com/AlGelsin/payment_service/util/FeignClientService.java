package com.AlGelsin.payment_service.util;

import com.AlGelsin.payment_service.feignclient.UserFeignClient;
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
