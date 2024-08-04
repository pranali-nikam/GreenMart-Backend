package com.greenify.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.greenify.dao.OrderDao;
import com.greenify.dto.orderDtos.OrderItemDto;
import com.greenify.dto.orderDtos.PlaceOrderDto;
import com.greenify.dto.productDtos.ProductOrderDto;
import com.greenify.entities.Order;
import com.greenify.entities.OrderItem;
import com.greenify.entities.Product;
import com.greenify.entities.User;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDao orderDao;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public PlaceOrderDto placeOrder(PlaceOrderDto orderDto, Long userId) {
		
		Order order = mapPlaceOrderDtoToOrder(orderDto, userId);
		order = orderDao.save(order);
		
		
		return null;
	}
	
	private Order mapPlaceOrderDtoToOrder(PlaceOrderDto placeOrderDto, Long userId) {
	
		User user= new User();
		user.setUserId(userId);
		
		Order order = Order.builder()
				.orderDate(placeOrderDto.getOrderDate())
				.shippingAddress(placeOrderDto.getShippingAddress())
				.status(placeOrderDto.getStatus())
				.totalAmount(placeOrderDto.getTotalAmount())
				.orderItems(fetchOrderItemsFromDto(placeOrderDto.getOrderItems()))
				.user(user)
				.build();
				
				return order;
	}
	
	private List<OrderItem> fetchOrderItemsFromDto(List<OrderItemDto> orderItemDtos) {
		
		List<OrderItem> orderItems = new ArrayList<OrderItem>();
		
		orderItemDtos.forEach(orderItemDto ->{
		OrderItem orderItem =OrderItem.builder()
			.price(orderItemDto.getPrice())
			.product(fetchProductFromDto(orderItemDto.getProduct()))
			.quantity(orderItemDto.getQuantity())
			.build();
			orderItems.add(orderItem);
		});
		return orderItems;
	}
	
	private Product fetchProductFromDto(ProductOrderDto productOrderDto) {
		return Product.builder()
				.productName(productOrderDto.getProductName())
				.description(productOrderDto.getDescription())
				.price(productOrderDto.getPrice())
				.build();
	}
	
	
	
}
