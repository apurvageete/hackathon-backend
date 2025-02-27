package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}

package com.example.demo.controller;

		import com.example.demo.service.FormDataService;
		import org.springframework.http.ResponseEntity;
		import org.springframework.web.bind.annotation.*;
		import java.util.Map;

@RestController
@RequestMapping("/api")
public class FormDataController {
	private final FormDataService formDataService;

	public FormDataController(FormDataService formDataService) {
		this.formDataService = formDataService;
	}

	@PostMapping("/submit")
	public ResponseEntity<String> handleFormData(@RequestParam Map<String, String> formData,
												 @RequestHeader("Authorization") String token) {
		return formDataService.processFormData(formData, token);
	}
}

package com.example.demo.service;

		import org.springframework.http.*;
		import org.springframework.stereotype.Service;
		import org.springframework.web.client.RestTemplate;
		import org.springframework.util.LinkedMultiValueMap;
		import org.springframework.util.MultiValueMap;
		import java.util.Map;

@Service
public class FormDataService {
	private final RestTemplate restTemplate = new RestTemplate();
	private final String EXTERNAL_API_URL = "https://external-api.com/endpoint";

	public ResponseEntity<String> processFormData(Map<String, String> formData, String token) {
		MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
		body.setAll(formData);

		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", token);
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(body, headers);
		ResponseEntity<String> response = restTemplate.exchange(EXTERNAL_API_URL, HttpMethod.POST, requestEntity, String.class);

		return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
	}
}
