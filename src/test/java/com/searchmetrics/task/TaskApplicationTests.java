package com.searchmetrics.task;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
class TaskApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void whenCallingTheApi_returnBody_success() {
		ResponseEntity<Object> response = restTemplate.getForEntity("/prices/BTCUSDT",
				Object.class);

		assertEquals(response.getStatusCode(), HttpStatus.OK);
	}
}
