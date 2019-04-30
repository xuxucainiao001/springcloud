package com.pgy.cloud.client.cloudclient.client;

import java.util.Collections;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="cloud-server",fallback=HystrixCloudClient.class)
public interface CloudClient {
	
	
	
	@GetMapping("/server/index")
	Map<String,Object> getMsg();
	
	

}

@Component
class HystrixCloudClient implements CloudClient{

	@Override
	public Map<String, Object> getMsg() {
		
		return Collections.emptyMap();
	}
	
}
