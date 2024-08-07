package com.greenify.service;

import com.greenify.dto.orderDtos.OrderStatusCountDto;
import com.greenify.dto.orderDtos.PlaceOrderDto;

public interface OrderService {

	public PlaceOrderDto placeOrder(PlaceOrderDto orderDto, Long userId);
	
	public OrderStatusCountDto countOrdersByStatus(Long sellerId);
}
