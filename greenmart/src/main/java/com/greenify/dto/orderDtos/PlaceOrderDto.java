package com.greenify.dto.orderDtos;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.greenify.enums.Status;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;



@Setter
@Getter
@ToString
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class PlaceOrderDto {

	private LocalDate orderDate;
	
	private BigDecimal totalAmount;
		
	private Status status;
	
	private ShippingDetailDto shippingAddress;
		
	private List<OrderItemDto> orderItems;
	
	private PaymentDetailDto paymentDetails;
	
	private Long sellerId;
		
 
}
