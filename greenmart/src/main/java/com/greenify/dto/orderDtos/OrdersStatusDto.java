package com.greenify.dto.orderDtos;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.greenify.enums.PaymentMethod;
import com.greenify.enums.Status;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Builder
@AllArgsConstructor
public class OrdersStatusDto {
	
	
	private Long orderId;
	
    private LocalDate orderDate;
	
	private BigDecimal totalAmount;
		
	private Status status;
	
	private ShippingDetailDto shippingAddress;
		
	private List<OrderItemDto> orderItems;
	
	private PaymentMethod paymentMethod;
}
