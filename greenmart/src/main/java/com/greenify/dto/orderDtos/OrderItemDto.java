package com.greenify.dto.orderDtos;

import java.math.BigDecimal;

import com.greenify.dto.productDtos.ProductOrderDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class OrderItemDto {

	private int quantity;

	private BigDecimal price;

	private ProductOrderDto product;
}
