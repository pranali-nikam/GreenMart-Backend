package com.greenify.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.greenify.dto.orderDtos.OrderItemStatusCountDto;
import com.greenify.dto.orderDtos.OrderStatusCountDto;
import com.greenify.dto.orderDtos.OrdersStatusDto;
import com.greenify.dto.orderDtos.PlaceOrderDto;
import com.greenify.dto.orderDtos.ShowOrderDto;
import com.greenify.dto.orderDtos.UserOrderDto;
import com.greenify.enums.Status;

public interface OrderService {

	public PlaceOrderDto placeOrder(PlaceOrderDto orderDto, Long userId);
	
	public List<ShowOrderDto> getOrdersByUserId(Long userId);

	public Long countOrders();


}
