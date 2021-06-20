package com.my.resttemplete;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RestClientTest {

	private static final String BASE_URL = "http://localhost";

	@LocalServerPort
	private int randomServerPort;

	@Autowired
	private RestClient restClient;

	@Test
	void uriParameterTestWithUriComponentBuilderEncoding() {
		// given
		Map<String, String> urlParameters = new HashMap<>();
		urlParameters.put("email", "joojimin+++++##$%#$%#$%#$@@naver.com");

		// when
		String response = restClient.exchange(HttpMethod.GET,
											  BASE_URL + ":" + randomServerPort + "/test/1/email/{email}",
											  null,
											  urlParameters,
											  HttpHeaders.EMPTY,
											  String.class).getBody();

		// then
		System.out.println(response);
	}


	@Test
	void queryParameterTestWithUriComponentBuilderEncoding() {
		// given
		Map<String, String> queryParameters = new HashMap<>();
		queryParameters.put("email", "joojimin+++++##$%#$%#$%#$@@naver.com");

		// when
		String response = restClient.exchange(HttpMethod.GET,
											  BASE_URL + ":" + randomServerPort + "/test/2",
											  queryParameters,
											  new HashMap<>(),
											  HttpHeaders.EMPTY,
											  String.class).getBody();

		// then
		System.out.println(response);
	}

}
