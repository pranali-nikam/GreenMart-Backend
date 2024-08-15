package com.greenify.service;

import java.util.List;

import com.greenify.dto.sellerDtos.SellerDetailsDto;
import com.greenify.dto.sellerDtos.SellerDto;
import com.greenify.dto.sellerDtos.SellerProfileDto;


public interface SellerService {
	
	public SellerDto registerSeller(SellerDto sellerDto);
	
	public List<SellerDetailsDto> getSellerDetails();
	
	public int blockUnblockSeller(Long sellerId, Boolean isBlocked);
		
	public SellerProfileDto getSellerProfile(Long sellerId);
}
