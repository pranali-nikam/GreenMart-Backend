package com.greenify.dto.orderDtos;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

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
public class ShowOrderDto {
	private LocalDate orderDate;
	
	private BigDecimal totalAmount;
			
	private ShippingDetailDto shippingAddress;
		
	private List<ShowOrderItemsDto> orderItems;
	
	private PaymentDetailDto paymentDetails;
	
	private String productName;
}
