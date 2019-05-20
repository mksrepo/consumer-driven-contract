package com.mark;

import org.assertj.core.api.BDDAssertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.junit.StubRunnerRule;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.mark.model.Car;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CFCTestCase {

	@Rule
	public StubRunnerRule stubRunnerRule = new StubRunnerRule()
			.downloadStub("com.example", "product-catalogue-service", "0.0.1", "stubs")
			.repoRoot("http://localhost:8081/repository/maven-releases")
			.minPort(8100).maxPort(8100)
			.withSnapshotCheckSkip(false)
			.stubsMode(StubRunnerProperties.StubsMode.REMOTE);

	@Test
	public void get_person_from_service_contract() {

		RestTemplate restTemplate = new RestTemplate();
		// Getting data from Exchange
		Car request = new Car();
		request.setId(1L);

		// CFC Service Call
		final String URI = "http://localhost:8100/CFC/quote";
		Car response = restTemplate.postForObject(URI, request, Car.class);
		BDDAssertions.then(response.getId()).isEqualTo(1L);
	}
}
