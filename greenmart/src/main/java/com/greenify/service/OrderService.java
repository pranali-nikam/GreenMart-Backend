package com.greenify.service;

import java.util.List;

import com.greenify.dto.orderDtos.OrderStatusCountDto;
import com.greenify.dto.orderDtos.OrdersStatusDto;
import com.greenify.dto.orderDtos.PlaceOrderDto;
import com.greenify.enums.Status;

public interface OrderService {

	public PlaceOrderDto placeOrder(PlaceOrderDto orderDto, Long userId);
	
	public OrderStatusCountDto countOrdersByStatusAndSellerId(Long sellerId);
	
	public List<OrdersStatusDto> getOrdersByStatusAndSellerId(Long sellerId,Status status);
	
	public List<OrdersStatusDto> geOrdersByStatus(Status status);
	
	public OrderStatusCountDto countOrdersByStatus();

}
