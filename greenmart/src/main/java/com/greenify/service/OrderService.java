package com.greenify.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.greenify.dto.orderDtos.OrderStatusCountDto;
import com.greenify.dto.orderDtos.OrdersStatusDto;
import com.greenify.dto.orderDtos.PlaceOrderDto;
import com.greenify.dto.orderDtos.UserOrderDto;
import com.greenify.enums.Status;

public interface OrderService {

	public PlaceOrderDto placeOrder(PlaceOrderDto orderDto, Long userId);

	public OrderStatusCountDto countOrdersByStatusAndSellerId(Long sellerId);

	public List<OrdersStatusDto> getOrdersByStatusAndSellerId(Long sellerId, Status status);

	public Page<OrdersStatusDto> getOrdersByStatus(Pageable pageable,Status status);

	public OrderStatusCountDto countOrdersByStatus();

	public void updateOrderByStatus(Long orderId, Status status);
	
	public List<UserOrderDto> getOrdersByUserId(Long userId);



}
