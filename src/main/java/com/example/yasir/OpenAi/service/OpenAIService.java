package com.example.yasir.OpenAi.service;

import com.example.yasir.Vehicles.entity.VehicleEntity;
import com.example.yasir.Vehicles.service.VehicleService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OpenAIService {
    private final String API_URL = "https://api.openai.com/v1/chat/completions";

    @Autowired
    private VehicleService vehicleService;

    @Value("${openai.api.key}")
    private String API_KEY;

    public Map<String, String> getChatGPTResponse(String prompt) {
        // Retrieve and format vehicle listdw
        List<VehicleEntity> vehicles = vehicleService.getAllVehicles();

        // Convert vehicle list to a formatted string
        String vehicleListString = vehicles.stream()
                .map(VehicleEntity::toString) // Ensure VehicleEntity has a meaningful toString method
                .collect(Collectors.joining(", "));

        // Create instructions with vehicle list
        String instructions = "\"In Kuwait, analyze the following cars: " + vehicleListString + ". Your task is to determine the best car to buy or finance under these conditions:\n" +
                "\n" +
                "- Only consider the cars listed above.\n" +
                "- Look up any missing features not provided in the list. dont change the price of the car that is the list only take the msrp price from the list  \n" +
                "\n" +
//                Brand:Toyota|
//                Model:Land Cruise|
//        Year:2025|
//                Price:21000|
                "Please evaluate based on the following criteria:\n" +
                "- Down payment\n" +
                "- Desired monthly installments\n" +
                "- Total family size (including myself)\n" +
                "- Main purpose of the car (e.g., daily commuting, family trips, off-road use)\n" +
                "- Zero-interest financing\n" +
                "\n" +
                "Consider these factors:\n" +
//                "- MSRP\n" +
                "- Main purpose of the car\n" +
                "- Fuel efficiency, maintenance costs, resale value\n" +
                "- Availability and market demand in Kuwait\n" +
//                "-All prices should be in Kuwaiti dinars and not in dollars. if the price isnt found in kuwaiti dinar take the USD price and change it to KWD \n" +
                "\n" +
                "Calculate the installment period based on the desired monthly installment with zero-interest financing. Recommend only one car and provide details as follows:\n" +

                "- Return only these information below\n" +
                "- id\n" +
                "- Car Name\n" +
                "- Price\n" +
                "- Reasons for recommendation (concise and clear, without citing sources)\"\n";

        System.out.println(instructions);

        RestTemplate restTemplate = new RestTemplate();

        // Prepare headers
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + API_KEY);
        headers.set("Content-Type", "application/json");

        // Prepare request body
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", "gpt-3.5-turbo");
        Object messageBody = new Object[]{
                Map.of("role", "system", "content", instructions),
                Map.of("role", "user", "content", prompt)
        };
        requestBody.put("messages", messageBody);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);

        try {
            // Make API call
            ResponseEntity<String> response =
                    restTemplate.exchange(API_URL, HttpMethod.POST, entity, String.class);

            // Parse response body
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(response.getBody());
            String content = jsonNode.path("choices").get(0).path("message").path("content").asText();

            // Prepare JSON response
            Map<String, String> jsonResponse = new HashMap<>();
            jsonResponse.put("response", content);

            return jsonResponse;
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("response", "Error: " + e.getMessage());
            return errorResponse;
        }
    }
}
