package com.itmuch.cloud;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MovieController {
	private Log logger = LogFactory.getLog(MovieController.class);
	
    @Autowired
    private RestTemplate restTemplate;
    
    @Autowired
    private DiscoveryClient discoveryClient;
    
    @Value("${user.userServiceUrl}")
    private String userServiceUrl;
    
    @GetMapping("/user-instance")
    public List<ServiceInstance> showInfo() {
    	return this.discoveryClient.getInstances("MICROSERVICE-PROVIDER-USER");
    }
    
    @GetMapping("/user/{id}")
    public User findById(@PathVariable Long id) {
//    	return this.restTemplate.getForObject("http://localhost:8000/" + id, 
//    			User.class);
    	logger.info("User Service URL: " + userServiceUrl);
    	return this.restTemplate.getForObject(userServiceUrl + id, 
    			User.class);
    }
    
}
