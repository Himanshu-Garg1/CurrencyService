package com.springbootpractice.currencyexchangeservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyExchangeController {

    @Autowired
    public CurrencyExchangeRepository currencyExchangeRepository;
    @Autowired
    private Environment environment;
    @GetMapping("currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange retrieveExchangeValue(@PathVariable String from,@PathVariable String to)
    {
       String port= environment.getProperty("local.server.port");
        CurrencyExchange currencyExchange=currencyExchangeRepository.findByFromAndTo(from,to);
        if(currencyExchange==null)
        {
            throw new RuntimeException("Unable to find data for "+from+" to "+to);
        }
        currencyExchange.setEnvironment(port);


        return currencyExchange;

    }
}
