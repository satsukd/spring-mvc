package com.study.shop.restclient;

import com.study.shop.restclient.entity.ExchangeRateRow;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class ExcanangeRate {

    private static final String url = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json";

    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        List<ExchangeRateRow> exchangeRateData = restTemplate.getForObject(url, List.class);
        System.out.println("ExcanangeRate.main " + exchangeRateData);
    }
}
