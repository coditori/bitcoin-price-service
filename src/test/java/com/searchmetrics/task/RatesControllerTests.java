package com.searchmetrics.task;

import com.searchmetrics.task.dto.PriceDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
class RatesControllerTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void whenCallingCorrectRateEndpoint_returnBody_success() {
		ResponseEntity<PriceDto> response = restTemplate.getForEntity("/rates/BTCUSDT",
				PriceDto.class);

		assertEquals(response.getStatusCode(), HttpStatus.OK);
		Assertions.assertNotNull(response.getBody());
		assertEquals(response.getBody().getSymbol(), "BTCUSDT");
	}

	@Test
	void whenCallingWrongRateEndpoint_returnBody_success() {
		ResponseEntity<PriceDto> response = restTemplate.getForEntity("/rates/111111",
				PriceDto.class);

		assertNotEquals(response.getStatusCode(), HttpStatus.OK);
	}
}