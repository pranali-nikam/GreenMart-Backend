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

import com.greenify.dto.orderDtos.PlaceOrderDto;
import com.greenify.dto.orderDtos.UserOrderDto;
import com.greenify.service.OrderService;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@PostMapping("/placeOrder/{userId}")
	@ResponseStatus(HttpStatus.CREATED)
	public PlaceOrderDto placeOrder(@RequestBody PlaceOrderDto placeOrder,@PathVariable Long userId) {
		return orderService.placeOrder(placeOrder, userId);
	}
	
	@GetMapping("/getAllOrderById/{userId}")
	@ResponseStatus(HttpStatus.OK)
	public List<PlaceOrderDto> getAllOrdersByUserId(@PathVariable Long userId) {
		return orderService.getOrdersByUserId(userId);
	}


}
