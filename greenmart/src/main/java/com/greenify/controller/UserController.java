package com.greenify.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.greenify.dto.orderDtos.ShippingDetailDto;
import com.greenify.dto.userDtos.UserDto;
import com.greenify.service.UserService;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/register")
	@ResponseStatus(HttpStatus.CREATED)
	public UserDto registerUser(@RequestBody UserDto userDto) {
		
		return userService.registerUser(userDto);
	}
	
	@PostMapping("/addShippingAddress/{userId}")
	@ResponseStatus(HttpStatus.CREATED)
	public ShippingDetailDto addShippingAddress(@RequestBody ShippingDetailDto shippingDetailDto, @PathVariable("userId") Long userId) {
		return userService.addShippingAddress(shippingDetailDto, userId);
		
	}
	
	
	@GetMapping("/getShippingAddress/{userId}")
	@ResponseStatus(HttpStatus.OK)
	public List<ShippingDetailDto> getShippingAddress(@PathVariable("userId") Long userId) {
		return userService.getShippingAddress(userId);
		
	}
	
}
