package com.greenify.dto.orderDtos;

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
public class OrderStatusCountDto {
	
	private Long pendingCount;

	private Long shippedCount;

	private Long deliveredCount;
}
