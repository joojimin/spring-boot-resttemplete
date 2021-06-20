package com.my.resttemplete;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Map;

@Component
public class RestClient {

	@Autowired
	private RestTemplate restTemplate;

	public ResponseEntity<String> exchange(HttpMethod method,
											String url,
											Map<String, String> queryParams,
											Map<String, String> uriVariables,
											HttpHeaders headers,
											Object body) {

		URI uri = getUri(url, queryParams, uriVariables);
		System.out.println("URI: " + uri);
		HttpEntity requestEntity = new HttpEntity(body, headers);
		return restTemplate.exchange(uri, method, requestEntity, String.class);
	}

	public static URI getUri(String url, Map<String, String> queryParams, Map<String, String> uriVariables) {
		UriComponentsBuilder uriComponentBuilder = UriComponentsBuilder.fromHttpUrl(url);
		if (queryParams != null) {
			for (Map.Entry<String, String> queryParam : queryParams.entrySet()) {
				uriComponentBuilder = uriComponentBuilder.queryParam(queryParam.getKey(), queryParam.getValue());
			}
		}
		return uriComponentBuilder.encode()
								  .buildAndExpand(uriVariables)
								  .toUri();
	}
}
