package com.greenify.dto.userDtos;

import java.util.List;

import com.greenify.entities.Order;
import com.greenify.entities.ShippingAddress;
import com.greenify.entities.Wishlist;

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
public class ProfileDto {
	private String name;
	
	private String mobileNumber;
	
	private List<Order> orders;
	
	private List<ShippingAddress> shippingAddress;
	
	private Wishlist wishlist;
}
