package com.greenify.dto;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class CartDto {
	
	@NotNull
	private Long userId;
	
	@NotNull
	private Long productId;
	
	@NotNull
	private int quantity;

}
