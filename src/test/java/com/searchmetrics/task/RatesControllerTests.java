package com.searchmetrics.task;

import com.searchmetrics.task.cache.CacheService;
import com.searchmetrics.task.dto.PriceDto;
import com.searchmetrics.task.runner.RatesRunner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
class RatesControllerTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@BeforeAll
	public void init() throws InterruptedException {
	    Thread.sleep(10000);
	}

	@Test
	@EventListener(ApplicationReadyEvent.class)
	void whenCallingCorrectRateEndpoint_returnBody_success() throws InterruptedException {
		ResponseEntity<PriceDto> response = restTemplate.getForEntity("/rates/BTCUSDT",
				PriceDto.class);

		assertEquals(response.getStatusCode(), HttpStatus.OK);
		Assertions.assertNotNull(response.getBody());
		assertEquals(response.getBody().getSymbol(), "BTCUSDT");
	}

	@Test
	void whenCallingWrongRateEndpoint_returnNothing_fail() {
		ResponseEntity<Object> response = restTemplate.getForEntity("/rates/111111",
				Object.class);

		assertNotEquals(response.getStatusCode(), HttpStatus.OK);
	}
}