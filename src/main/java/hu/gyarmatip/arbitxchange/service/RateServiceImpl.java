package hu.gyarmatip.arbitxchange.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RateServiceImpl implements RateService {

    private final RestTemplate restTemplate;

    @Autowired
    public RateServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public double getRate(String baseCurrency, String currency) {
        String url = String.format("https://api.exchangeratesapi.io/latest?base=%s", baseCurrency);
        String jsonString = restTemplate.getForObject(url, String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(jsonString);
            return jsonNode.get("rates").get(currency).asDouble();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
