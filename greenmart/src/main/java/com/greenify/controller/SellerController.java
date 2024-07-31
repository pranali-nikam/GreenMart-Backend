package com.greenify.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.greenify.dto.SellerDto;
import com.greenify.service.SellerService;

@RestController
@RequestMapping("api/sellers")
public class SellerController {

	@Autowired
	private SellerService sellerService;
	
	@PostMapping("/register")
	@ResponseStatus(HttpStatus.CREATED)
	public SellerDto registerSeller(@RequestBody SellerDto sellerDto) {
		return sellerService.registerSeller(sellerDto);
	}
}
