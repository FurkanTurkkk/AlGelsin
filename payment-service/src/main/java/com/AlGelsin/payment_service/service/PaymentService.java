package com.AlGelsin.payment_service.service;

import com.AlGelsin.payment_service.config.IyzicoConfig;
import com.AlGelsin.payment_service.util.FeignClientService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;


@Service
public class PaymentService {

    private final IyzicoConfig iyzicoConfig;
    private final static Logger logger = LoggerFactory.getLogger(PaymentService.class);
    private final HttpServletRequest request;
    private final FeignClientService feignClientService;


    public PaymentService(IyzicoConfig iyzicoConfig, HttpServletRequest request, FeignClientService feignClientService) {
        this.iyzicoConfig = iyzicoConfig;
        this.request = request;
        this.feignClientService = feignClientService;
    }
    // String userIp = request.getRemoteAddr();

}
