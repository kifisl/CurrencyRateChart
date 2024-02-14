package com.example.testtask.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import com.example.testtask.model.Currency;
import com.example.testtask.model.CurrencyRate;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

@Controller
public class CurrencyController {

    @GetMapping("/")
    public String homePage(Model model) throws JsonMappingException, JsonProcessingException {

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> currenciesResponse = restTemplate.getForEntity("https://api.nbrb.by/exrates/currencies", String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        TypeFactory typeFactory = objectMapper.getTypeFactory();
        List<Currency> currencies = objectMapper.readValue(currenciesResponse.getBody(), typeFactory.constructCollectionType(List.class, Currency.class));
        model.addAttribute("currencies", currencies);

        return "index";
    }

    @GetMapping("/rate")
    public String getRate(@RequestParam String id, @RequestParam String startDate, @RequestParam String endDate, Model model) throws JsonMappingException, JsonProcessingException {
        
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> rateResponse = restTemplate.getForEntity("https://api.nbrb.by/exrates/rates/dynamics/"+ id + "?startDate=" + startDate +"&enddate=" + endDate, String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        TypeFactory typeFactory = objectMapper.getTypeFactory();
        List<CurrencyRate> rates = objectMapper.readValue(rateResponse.getBody(), typeFactory.constructCollectionType(List.class, CurrencyRate.class));
        List<Double> officialRates = new ArrayList<>();

        List<Long> timestamps = new ArrayList<>();
        for (CurrencyRate rate : rates) {
            timestamps.add(rate.getDate().getTime());
            officialRates.add(rate.getRate());
        }

        model.addAttribute("timestamps", timestamps);
        model.addAttribute("rates", officialRates);

        return "chart";
    }



}