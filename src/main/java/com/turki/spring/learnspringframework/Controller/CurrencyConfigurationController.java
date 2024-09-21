package com.turki.spring.learnspringframework.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.turki.spring.learnspringframework.Service.CurrencyServiceConfiguration;

@RestController
public class CurrencyConfigurationController {
    // http://localhost:8080/myApi/currency-service
    @Autowired
    private CurrencyServiceConfiguration configuration;

    @RequestMapping("/myApi/currency-service")
    public CurrencyServiceConfiguration retreiveCurrencyServiceConfiguration() {
        return configuration;
    }

}
