package com.greenify.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.greenify.dto.orderDtos.OrderItemStatusCountDto;
import com.greenify.dto.orderDtos.OrderItemStatusDto;
import com.greenify.enums.Status;


public interface OrderItemService {
	
	public OrderItemStatusCountDto countOrdersByStatusAndSellerId(Long sellerId);

	public List<OrderItemStatusDto> getOrdersByStatusAndSellerId(Long sellerId, Status status);

	public Page<OrderItemStatusDto> getOrdersByStatus(Pageable pageable,Status status);

	public void updateOrderByStatus(Long orderId, Status status);


}
