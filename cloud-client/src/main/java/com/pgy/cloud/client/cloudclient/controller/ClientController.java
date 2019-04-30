package com.pgy.cloud.client.cloudclient.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.pgy.cloud.client.cloudclient.client.CloudClient;

@RestController
@RequestMapping("/demo")
public class ClientController {

	@Resource
	private CloudClient cloudClient;

	@SuppressWarnings("unchecked")
	@GetMapping("/getByTemplate")
	public Object getByTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		Map<String, Object> map = null;
		map = restTemplate.getForObject("http://localhost/cloud-server/server/index", HashMap.class);
		return map;
	}

	@HystrixCommand(fallbackMethod = "demoError", commandProperties = {
			@HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000"),
			@HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "2") }, threadPoolProperties = {
					@HystrixProperty(name = "coreSize", value = "1"),
					@HystrixProperty(name = "maxQueueSize", value = "-1") })
	@GetMapping("/getByFegin")
	public Object getByFegin() {
		Map<String,Object> map=cloudClient.getMsg();
		return map;
	}

	public Object demoError() {
		return "ERROR !";
	}

}
