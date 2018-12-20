package com.cts.springcloud.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.cts.springcloud.model.Student;

@RestController
public class StudentController {

	@Autowired
	private DiscoveryClient discoveryClient;

	@Autowired
	private LoadBalancerClient loadBalancer;

	private static final String serviceId = "student-sub-services";

	@RequestMapping(value = "/student", method = RequestMethod.GET)
	public Student studentService() {

		Student student = buildTemp();

		// String url = getUrl(serviceId) + "/history";
		String url = getLoadBalancedUrl(serviceId) + "/history";
		String history = callSubSystem(url);

		student.setPrvHistory(history);

		return student;
	}

	private String callSubSystem(String url) {

		ResponseEntity<String> response = null;
		RestTemplate restTemplate = new RestTemplate();
		try {
			response = restTemplate.exchange(url, HttpMethod.GET, getHeaders(), String.class);
		} catch (RestClientException | IOException ex) {
			System.err.println("Exception at rest call" + ex);
		}

		return response.getBody();
	}

	private String getLoadBalancedUrl(String serviceId) {
		ServiceInstance instance = loadBalancer.choose(serviceId);
		return instance.getUri().toString();
	}

	private String getUrl(String serviceId) {
		String url = null;
		List<ServiceInstance> instances = discoveryClient.getInstances(serviceId);
		for (ServiceInstance instance : instances) {
			System.out.println("Instance URL ::" + instance.getUri().toString());
		}

		if (instances != null && instances.size() > 0) {
			url = instances.get(0).getUri().toString();
		}

		return url;
	}

	private static HttpEntity<?> getHeaders() throws IOException {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		return new HttpEntity<>(headers);
	}

	private Student buildTemp() {
		Student student = new Student("John", 23, "Previoud history");
		return student;
	}

}
