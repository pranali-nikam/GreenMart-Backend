package com.greenify.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.greenify.dao.SellerDao;
import com.greenify.dao.UserDao;
import com.greenify.dto.sellerDtos.SellerDetailsDto;
import com.greenify.dto.sellerDtos.SellerDto;
import com.greenify.entities.Role;
import com.greenify.entities.Seller;
import com.greenify.entities.User;

@Service
@Transactional
public class SellerServiceImpl implements SellerService {
	
	@Autowired
	private SellerDao sellerDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public SellerDto registerSeller(SellerDto sellerDto) {
        Seller seller = modelMapper.map(sellerDto, Seller.class);

		User user = seller.getUser();
        if (user.getUserId() == null) {
            user.setRole(Role.SELLER);
        }
        user.setSeller(seller);
        
        userDao.save(user);
        
		return modelMapper.map(seller, SellerDto.class);
	}

	@Override
	public List<SellerDetailsDto> getSellerDetails() {
		List<Seller> sellerList = sellerDao.findAll();
		return mapSellerToSellerDetailsDTO(sellerList);
	}
	
	private List<SellerDetailsDto> mapSellerToSellerDetailsDTO(List<Seller> sellers) {
		
		List<SellerDetailsDto> sellerDetailsDtoList = new ArrayList<>();
		
		sellers.forEach(seller ->{
			SellerDetailsDto sellerDetailsDto = SellerDetailsDto.builder()
			.name(seller.getUser().getFirstName() + " " + seller.getUser().getLastName())
			.email(seller.getUser().getEmail())
			.mobileNumber(seller.getUser().getMobileNumber())
			.address(seller.getAddress())
			.gstinNumber(seller.getGstinNumber())
			.storeName(seller.getStoreName())
			.phone(seller.getPhone())
			.build();
			
			sellerDetailsDtoList.add(sellerDetailsDto);
		});
		
		return sellerDetailsDtoList;	
	}

}
