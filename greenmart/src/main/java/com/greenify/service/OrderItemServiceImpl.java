package com.greenify.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.greenify.dao.OrderItemDao;
import com.greenify.dto.orderDtos.OrderItemDto;
import com.greenify.dto.orderDtos.OrderItemStatusCountDto;
import com.greenify.dto.orderDtos.OrderItemStatusDto;
import com.greenify.dto.orderDtos.ShippingDetailDto;
import com.greenify.entities.Order;
import com.greenify.entities.OrderItem;
import com.greenify.enums.Status;

@Service
@Transactional
public class OrderItemServiceImpl implements OrderItemService {

	@Autowired
	private OrderItemDao orderItemDao;

	@Override
	public OrderItemStatusCountDto countOrdersByStatusAndSellerId(Long sellerId) {

		List<List<String>> statusCountList = orderItemDao.countOfOrderItemByStatusAndSellerId(sellerId);

		Map<String, Long> countMap = statusCountList.stream()
				.collect(Collectors.toMap(e -> e.get(0), e -> Long.parseLong(e.get(1))));

		return OrderItemStatusCountDto.builder().pendingCount(countMap.get(Status.PENDING.name()))
				.shippedCount(countMap.get(Status.SHIPPED.name())).deliveredCount(countMap.get(Status.DELIVERED.name()))
				.build();
	}

	@Override
	public List<OrderItemStatusDto> getOrdersByStatusAndSellerId(Long sellerId, Status status) {

		List<OrderItemStatusDto> ordersStatusDtoList = new ArrayList<OrderItemStatusDto>();

		List<OrderItem> orderList = orderItemDao.findAllOrderItemBySellerIdAndStatus(sellerId, status);

		orderList.forEach(orderItem -> {

			Order order = orderItem.getOrder();

			ordersStatusDtoList.add(OrderItemStatusDto.builder().orderId(order.getOrderId())
					.orderDate(order.getOrderDate()).paymentMethod(order.getPaymentDetails().getPaymentMethod().name())
					.orderItem(OrderItemDto.builder().price(orderItem.getPrice())
							.productId(orderItem.getProduct().getProductId()).quantity(orderItem.getQuantity())
							.sellerId(orderItem.getSeller().getSellerId()).status(orderItem.getStatus()).build())
					.shippingAddress(ShippingDetailDto.builder()
							.addressLine1(order.getShippingAddress().getAddressLine1())
							.addressLine2(order.getShippingAddress().getAddressLine2())
							.city(order.getShippingAddress().getCity()).country(order.getShippingAddress().getCountry())
							.mobileNumber(order.getShippingAddress().getMobileNumber())
							.state(order.getShippingAddress().getState())
							.zipcode(order.getShippingAddress().getZipcode())
							.shippingId(order.getShippingAddress().getShippingId()).build())
					.build());
		});

		return ordersStatusDtoList;
	}

	@Override
	public Page<OrderItemStatusDto> getOrdersByStatus(Pageable pageable, Status status) {
		List<OrderItemStatusDto> ordersStatusDtoList = new ArrayList<OrderItemStatusDto>();

		Page<OrderItem> orderPage = orderItemDao.findAllOrderItemByStatus(pageable, status);

		orderPage.getContent().forEach(orderItem -> {

			Order order = orderItem.getOrder();

			ordersStatusDtoList.add(OrderItemStatusDto.builder().orderId(order.getOrderId())
					.orderDate(order.getOrderDate()).paymentMethod(order.getPaymentDetails().getPaymentMethod().name())
					.orderItem(OrderItemDto.builder().price(orderItem.getPrice())
							.productId(orderItem.getProduct().getProductId()).quantity(orderItem.getQuantity())
							.sellerId(orderItem.getSeller().getSellerId()).status(orderItem.getStatus()).build())
					.shippingAddress(ShippingDetailDto.builder()
							.addressLine1(order.getShippingAddress().getAddressLine1())
							.addressLine2(order.getShippingAddress().getAddressLine2())
							.city(order.getShippingAddress().getCity()).country(order.getShippingAddress().getCountry())
							.mobileNumber(order.getShippingAddress().getMobileNumber())
							.state(order.getShippingAddress().getState())
							.zipcode(order.getShippingAddress().getZipcode())
							.shippingId(order.getShippingAddress().getShippingId()).build())
					.build());
		});

		return new PageImpl<OrderItemStatusDto>(ordersStatusDtoList, pageable, orderPage.getTotalElements());

	}

	@Override
	public void updateOrderByStatus(Long orderId, Status status) {
		orderItemDao.updateOrderStatus(orderId, status);
	}

}
