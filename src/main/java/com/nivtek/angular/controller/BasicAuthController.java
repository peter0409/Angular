package com.nivtek.angular.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class BasicAuthController {
	
	@GetMapping(path = "/basicauth")
	public Principal helloWorldBean(Principal user) {
		return user;
	}	

}
