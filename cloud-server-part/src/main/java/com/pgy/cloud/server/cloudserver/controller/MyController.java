package com.pgy.cloud.server.cloudserver.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/server")
public class MyController {
	
	@GetMapping("/index")
	public Map<String,Object> getIndex() {
		Map<String,Object> map=new HashMap<>();
		map.put("message", "Hello Spring Cloud B!");
		return map;
	}

}
