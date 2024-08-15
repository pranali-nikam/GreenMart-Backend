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
import com.greenify.dto.orderDtos.PaymentDetailDto;
import com.greenify.dto.orderDtos.PlaceOrderDto;
import com.greenify.dto.orderDtos.ShippingDetailDto;
import com.greenify.dto.orderDtos.ShowOrderDto;
import com.greenify.dto.orderDtos.ShowOrderItemsDto;
import com.greenify.dto.orderDtos.UserOrderDto;
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

		Order order = Order.builder().orderDate(placeOrderDto.getOrderDate())
				.shippingAddress(getShippingAddress(placeOrderDto, user)).totalAmount(placeOrderDto.getTotalAmount())
				.user(user).build();

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

			Seller seller = new Seller();
			seller.setSellerId(orderItemDto.getSellerId());

			Product product = new Product();

			if (isStockAvailable(orderItemDto.getProductId())) {
				product.setProductId(orderItemDto.getProductId());
				OrderItem orderItem = OrderItem.builder().price(orderItemDto.getPrice()).product(product)
						.quantity(orderItemDto.getQuantity()).order(order).status(orderItemDto.getStatus())
						.seller(seller).build();
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
	public Long countOrders() {

		return orderDao.count();

	}

	@Override
	public List<ShowOrderDto> getOrdersByUserId(Long userId) {
		List<ShowOrderDto> userOrderDtoList = new ArrayList<ShowOrderDto>();

		List<Order> orderList = orderDao.findAllOrderByUserId(userId);

		orderList.forEach(order -> {

			userOrderDtoList
					.add(ShowOrderDto.builder().orderDate(order.getOrderDate())
							.orderItems(mapOrderItemToOrderItemDto(order))
							.paymentDetails(PaymentDetailDto
									.builder().paymentMethod(order.getPaymentDetails().getPaymentMethod())
									.amount(order.getPaymentDetails().getAmount()).build())
							.shippingAddress(ShippingDetailDto.builder()
									.addressLine1(order.getShippingAddress().getAddressLine1())
									.addressLine2(order.getShippingAddress().getAddressLine2())
									.city(order.getShippingAddress().getCity())
									.state(order.getShippingAddress().getState())
									.country(order.getShippingAddress().getCountry())
									.zipcode(order.getShippingAddress().getZipcode())
									.mobileNumber(order.getShippingAddress().getMobileNumber()).build())
							.totalAmount(order.getTotalAmount()).build());

		});

		return userOrderDtoList;
	}

	private List<ShowOrderItemsDto> mapOrderItemToOrderItemDto(Order order) {
		List<ShowOrderItemsDto> orderItemDtos = new ArrayList<>();

		for (OrderItem orderItem : order.getOrderItems()) {
			orderItemDtos.add(ShowOrderItemsDto.builder().price(orderItem.getPrice())
					.productId(orderItem.getProduct().getProductId()).quantity(orderItem.getQuantity())
					.sellerId(orderItem.getSeller().getSellerId()).status(orderItem.getStatus())
					.productName(orderItem.getProduct().getProductName())
					.build());
		}

		return orderItemDtos;
	}

}
