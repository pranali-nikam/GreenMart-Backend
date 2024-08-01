package com.greenify.service;

import java.util.List;

import com.greenify.dto.sellerDtos.SellerDetailsDto;
import com.greenify.dto.sellerDtos.SellerDto;


public interface SellerService {
	
	public SellerDto registerSeller(SellerDto sellerDto);
	
	public List<SellerDetailsDto> getSellerDetails();
}
