package com.esprit.microservice.client;
import com.esprit.microservice.bean.UserResponse;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
@FeignClient(name = "USER-SERVICE" ,url="localhost:8060" , configuration = FeignClientConfiguration.class)
public interface UserClient {
	
	@GetMapping(value="/api/username", consumes = "application/json")
	@Headers("Authorization: application/json")
	    public UserResponse getUser(@RequestHeader("Authorization") String bearerToken) ;

}
