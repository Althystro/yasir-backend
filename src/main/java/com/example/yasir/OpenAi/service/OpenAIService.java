package com.example.yasir.OpenAi.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class OpenAIService {

    private final String API_URL = "https://api.openai.com/v1/chat/completions";
    private final String instructions = "\n" +
            "\"In Kuwait, analyze all Ford, Chevrolet, Toyota, GMC, and Mercedes cars available to determine the best car to buy or finance under these conditions:\n" +
            "\n" +
            "I want you to take in these answers and make a shortlist of what cars that could fit the budget\n" +
            "\n" +
            "Down payment: \n" +
            "\n" +
            "Desired monthly installments: \n" +
            "\n" +
            "Total family size: ? (including myself)\n" +
            "\n" +
            "Main purpose of the car: (e.g., daily commuting, family trips, or off-road use)\n" +
            "\n" +
            "Zero-interest financing\n" +
            "\n" +
            "\n" +
            "Consider the following factors:\n" +
            "\n" +
            "MSRP\n" +
            "\n" +
            "\n" +
            "\n" +
            "Main purpose of the car\n" +
            "\n" +
            "Fuel efficiency, maintenance costs, and resale value\n" +
            "\n" +
            "Availability and market demand in Kuwait\n" +
            "\n" +
            "\n" +
            "The installment period should be calculated based on the desired monthly installment amount, with zero-interest financing in mind. Recommend only one car and structure the response as follows:\n" +
            "\n" +
            "Car Name:\n" +
            "MSRP Cost:\n" +
            "Reasons: (Provide concise and clear reasons without citing sources.)\"";
    @Value("${openai.api.key}")
    private String API_KEY;

    public Map<String, String> getChatGPTResponse(String prompt) {

        System.out.println("API KEY : " + API_KEY);

        RestTemplate restTemplate = new RestTemplate();

        // Prepare headers
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + API_KEY);
        headers.set("Content-Type", "application/json");
//        headers.set("OpenAI-Beta", "assistants=v2");

        // Prepare request body
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", "gpt-3.5-turbo");
        Object messageBody = new Object[]{
                Map.of("role", "system", "content", instructions),
                Map.of("role", "user", "content", prompt)
        };
        requestBody.put("messages", messageBody);
//        requestBody.put("assistant", "asst_F3off8AKu84mIetJv5BEQBsz");

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