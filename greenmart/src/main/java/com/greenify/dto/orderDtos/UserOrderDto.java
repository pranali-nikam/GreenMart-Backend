package com.greenify.dto.orderDtos;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.greenify.dto.userDtos.ProfileDto;
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
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserOrderDto {
	    private String productName;
		
		private String description;
		
		private BigDecimal price;
		
		private String imageUrl;
		
		private int quantity;
		
		private Long orderId;
		
	    private LocalDate orderDate;
		
		private BigDecimal totalAmount;
			
		private Status status;
		
}
