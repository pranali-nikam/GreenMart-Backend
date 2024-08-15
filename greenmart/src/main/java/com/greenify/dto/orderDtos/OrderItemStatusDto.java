package com.greenify.dto.orderDtos;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.greenify.enums.PaymentMethod;

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
public class OrderItemStatusDto {

	
private Long orderId;
	
    private LocalDate orderDate;
				
	private ShippingDetailDto shippingAddress;
			
	private String paymentMethod;
	
	private OrderItemDto orderItem;
	
}
