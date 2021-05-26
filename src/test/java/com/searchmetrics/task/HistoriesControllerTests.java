package com.searchmetrics.task;

import com.searchmetrics.task.dto.PriceDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
class HistoriesControllerTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void whenCallingCorrectHistoryEndpoint_returnBody_success() {
		ResponseEntity<List> response = restTemplate.getForEntity("/histories/BTCUSDT",
				List.class);

		assertEquals(response.getStatusCode(), HttpStatus.OK);
		Assertions.assertNotNull(response.getBody());
		Assertions.assertTrue(response.getBody().size() > 0);
	}

	@Test
	void whenCallingWrongHistoryEndpoint_returnBody_fail() {
		ResponseEntity<Object> response = restTemplate.getForEntity("/histories/111111",
				Object.class);

		assertNotEquals(response.getStatusCode(), HttpStatus.OK);
	}
}