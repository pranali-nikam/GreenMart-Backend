package com.greenify.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.greenify.dao.OrderDao;
import com.greenify.dao.ProductDao;
import com.greenify.dao.ShippingAddressDao;
import com.greenify.dto.orderDtos.OrderItemDto;
import com.greenify.dto.orderDtos.OrderStatusCountDto;
import com.greenify.dto.orderDtos.OrdersStatusDto;
import com.greenify.dto.orderDtos.PlaceOrderDto;
import com.greenify.dto.orderDtos.ShippingDetailDto;
import com.greenify.entities.Order;
import com.greenify.entities.OrderItem;
import com.greenify.entities.PaymentDetail;
import com.greenify.entities.Product;
import com.greenify.entities.Seller;
import com.greenify.entities.ShippingAddress;
import com.greenify.entities.User;
import com.greenify.enums.Status;
import com.greenify.exceptions.BusinessException;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDao orderDao;

	@Autowired
	private ShippingAddressDao shippingAddressDao;

	@Autowired
	private ProductDao productDao;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public PlaceOrderDto placeOrder(PlaceOrderDto orderDto, Long userId) {

		Order order = mapPlaceOrderDtoToOrder(orderDto, userId);
		order = orderDao.save(order);

		order.setOrderItems(fetchOrderItemsFromDto(orderDto.getOrderItems(), order));
		order.setPaymentDetails(PaymentDetail.builder().amount(orderDto.getPaymentDetails().getAmount())
				.paymentMethod(orderDto.getPaymentDetails().getPaymentMethod()).order(order).build());
		orderDao.save(order);

		return modelMapper.map(order, PlaceOrderDto.class);
	}

	private Order mapPlaceOrderDtoToOrder(PlaceOrderDto placeOrderDto, Long userId) {

		User user = new User();
		user.setUserId(userId);

		Seller seller = new Seller();
		seller.setSellerId(placeOrderDto.getSellerId());

		Order order = Order.builder().orderDate(placeOrderDto.getOrderDate())
				.shippingAddress(getShippingAddress(placeOrderDto, user)).status(placeOrderDto.getStatus())
				.totalAmount(placeOrderDto.getTotalAmount()).user(user).seller(seller).build();

		return order;
	}

	private ShippingAddress getShippingAddress(PlaceOrderDto placeOrderDto, User user) {

		if (placeOrderDto.getShippingAddress().getShippingId() != null) {
			Optional<ShippingAddress> optional = shippingAddressDao
					.findById(placeOrderDto.getShippingAddress().getShippingId());
			return optional.get();
		}

		return ShippingAddress.builder().addressLine1(placeOrderDto.getShippingAddress().getAddressLine1())
				.addressLine2(placeOrderDto.getShippingAddress().getAddressLine2())
				.city(placeOrderDto.getShippingAddress().getCity())
				.country(placeOrderDto.getShippingAddress().getCountry())
				.state(placeOrderDto.getShippingAddress().getState())
				.mobileNumber(placeOrderDto.getShippingAddress().getMobileNumber())
				.zipcode(placeOrderDto.getShippingAddress().getZipcode()).user(user).build();
	}

	private List<OrderItem> fetchOrderItemsFromDto(List<OrderItemDto> orderItemDtos, Order order) {

		List<OrderItem> orderItems = new ArrayList<OrderItem>();

		orderItemDtos.forEach(orderItemDto -> {

			Product product = new Product();

			if (isStockAvailable(orderItemDto.getProductId())) {
				product.setProductId(orderItemDto.getProductId());
				OrderItem orderItem = OrderItem.builder().price(orderItemDto.getPrice()).product(product)
						.quantity(orderItemDto.getQuantity()).order(order).build();
				decrementStockCount(orderItemDto.getProductId(), orderItemDto.getQuantity());
				orderItems.add(orderItem);
			} else {
				throw new BusinessException("Product id : " + orderItemDto.getProductId() + "out of stock");
			}

		});

		return orderItems;

	}

	private Boolean isStockAvailable(Long productId) {

		if (productDao.findStockById(productId) > 0) {
			return true;
		}
		return false;
	}

	private void decrementStockCount(Long productId, Integer quantity) {
		Integer stockCount = productDao.findStockById(productId);
		productDao.decrementStock(productId, stockCount - quantity);

	}

	@Override
	public OrderStatusCountDto countOrdersByStatusAndSellerId(Long sellerId) {

		List<List<String>> statusCountList = orderDao.countOfOrderByStatusAndSellerId(sellerId);

		Map<String, Long> countMap = statusCountList.stream()
				.collect(Collectors.toMap(e -> e.get(0), e -> Long.parseLong(e.get(1))));

		return OrderStatusCountDto.builder().pendingCount(countMap.get(Status.PENDING.name()))
				.shippedCount(countMap.get(Status.SHIPPED.name())).deliveredCount(countMap.get(Status.DELIVERED.name()))
				.build();
	}

	@Override
	public List<OrdersStatusDto> getOrdersByStatusAndSellerId(Long sellerId, Status status) {

		List<OrdersStatusDto> ordersStatusDtoList = new ArrayList<OrdersStatusDto>();

		List<Order> orderList = orderDao.findAllOrdersBySellerIdAndStatus(sellerId, status);

		orderList.forEach(order -> {
			OrdersStatusDto ordersStatusDto = OrdersStatusDto.builder().orderId(order.getOrderId())
					.orderDate(order.getOrderDate()).paymentMethod(order.getPaymentDetails().getPaymentMethod())
					.totalAmount(order.getTotalAmount()).status(order.getStatus())
					.shippingAddress(ShippingDetailDto.builder()
							.addressLine1(order.getShippingAddress().getAddressLine1())
							.addressLine2(order.getShippingAddress().getAddressLine2())
							.city(order.getShippingAddress().getCity()).country(order.getShippingAddress().getCountry())
							.mobileNumber(order.getShippingAddress().getMobileNumber())
							.state(order.getShippingAddress().getState())
							.zipcode(order.getShippingAddress().getZipcode())
							.shippingId(order.getShippingAddress().getShippingId()).build())
					.orderItems(fetchDtoFromOrderItem(order.getOrderItems())).build();
			ordersStatusDtoList.add(ordersStatusDto);
		});

		return ordersStatusDtoList;
	}

	private List<OrderItemDto> fetchDtoFromOrderItem(List<OrderItem> orderItem) {

		List<OrderItemDto> orderItemDtos = new ArrayList<OrderItemDto>();

		orderItem.forEach(order -> {

			OrderItemDto orderItemDto = OrderItemDto.builder().price(order.getPrice())
					.productId(order.getProduct().getProductId()).quantity(order.getQuantity()).build();
			orderItemDtos.add(orderItemDto);

		});
		return orderItemDtos;
	}

	@Override
	public Page<OrdersStatusDto> getOrdersByStatus(Pageable pageable ,Status status) {

		List<OrdersStatusDto> ordersStatusDtoList = new ArrayList<OrdersStatusDto>();

		Page<Order> orderPage = orderDao.findAllOrdersByStatus(pageable,status);

		orderPage.getContent().forEach(order -> {
			OrdersStatusDto ordersStatusDto = OrdersStatusDto.builder().orderId(order.getOrderId())
					.orderDate(order.getOrderDate()).paymentMethod(order.getPaymentDetails().getPaymentMethod())
					.shippingAddress(ShippingDetailDto.builder()
							.addressLine1(order.getShippingAddress().getAddressLine1())
							.addressLine2(order.getShippingAddress().getAddressLine2())
							.city(order.getShippingAddress().getCity()).country(order.getShippingAddress().getCountry())
							.mobileNumber(order.getShippingAddress().getMobileNumber())
							.state(order.getShippingAddress().getState())
							.zipcode(order.getShippingAddress().getZipcode())
							.shippingId(order.getShippingAddress().getShippingId()).build())
					.orderItems(fetchDtoFromOrderItem(order.getOrderItems())).build();
			ordersStatusDtoList.add(ordersStatusDto);

		});

		return new PageImpl<OrdersStatusDto>(ordersStatusDtoList,pageable,orderPage.getTotalElements());
	}

	@Override
	public OrderStatusCountDto countOrdersByStatus() {

		List<List<String>> statusCountList = orderDao.countOrderByStatus();

		Map<String, Long> countMap = statusCountList.stream()
				.collect(Collectors.toMap(e -> e.get(0), e -> Long.parseLong(e.get(1))));

		return OrderStatusCountDto.builder().pendingCount(countMap.get(Status.PENDING.name()))
				.shippedCount(countMap.get(Status.SHIPPED.name())).deliveredCount(countMap.get(Status.DELIVERED.name()))
				.build();
	}

	@Override
	public void updateOrderByStatus(Long orderId, Status status) {

		orderDao.updateOrderStatus(orderId, status);
	}
}
