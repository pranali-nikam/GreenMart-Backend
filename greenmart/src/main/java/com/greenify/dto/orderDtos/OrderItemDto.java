package com.greenify.dto.orderDtos;

import java.math.BigDecimal;

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
public class OrderItemDto {

	private int quantity;

	private BigDecimal price;

	private Long productId;
	
	private Long sellerId;

	private Status status;

}
