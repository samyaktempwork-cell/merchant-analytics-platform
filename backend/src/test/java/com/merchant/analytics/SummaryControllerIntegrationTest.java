package com.merchant.analytics;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SummaryControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void shouldReturnMTDSummary() {

        String url = "http://localhost:" + port + "/api/summary/mtd";

        String response = restTemplate.getForObject(url, String.class);

        assertNotNull(response);
        assertTrue(response.contains("totalTransactions"));
        assertTrue(response.contains("totalApproved"));
    }
}
