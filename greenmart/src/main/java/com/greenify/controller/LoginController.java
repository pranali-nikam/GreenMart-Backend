package com.greenify.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greenify.dto.SignInRequest;
import com.greenify.service.UserService;

@RestController
@RequestMapping("api/login")
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/signin")
	public ResponseEntity<?> signInUser(@RequestBody 
			@Valid SignInRequest request) {		
			return ResponseEntity
					.ok(userService.authenticateUser(request));
		
	}

}
