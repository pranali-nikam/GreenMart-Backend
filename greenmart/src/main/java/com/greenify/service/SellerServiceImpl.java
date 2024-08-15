package com.greenify.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.greenify.dao.SellerDao;
import com.greenify.dao.UserDao;
import com.greenify.dto.sellerDtos.SellerDetailsDto;
import com.greenify.dto.sellerDtos.SellerDto;
import com.greenify.dto.sellerDtos.SellerProfileDto;
import com.greenify.dto.userDtos.ProfileDto;
import com.greenify.entities.Seller;
import com.greenify.entities.User;
import com.greenify.enums.Role;

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

		sellers.forEach(seller -> {
			SellerDetailsDto sellerDetailsDto = SellerDetailsDto.builder().sellerId(seller.getSellerId())
					.name(seller.getUser().getFirstName() + " " + seller.getUser().getLastName())
					.email(seller.getUser().getEmail()).mobileNumber(seller.getUser().getMobileNumber())
					.address(seller.getAddress()).gstinNumber(seller.getGstinNumber()).storeName(seller.getStoreName())
					.phone(seller.getPhone()).isBlocked(seller.getUser().getIsBlocked()).build();

			sellerDetailsDtoList.add(sellerDetailsDto);
		});

		return sellerDetailsDtoList;
	}

	@Override
	public int blockUnblockSeller(Long sellerId, Boolean isBlocked) {

		return userDao.blockUnblockSeller(sellerId, isBlocked);
	}

	@Override
	public SellerProfileDto getSellerProfile(Long sellerId) {

		User user = userDao.findBySellerId(sellerId);

		SellerProfileDto sellerProfileDto = SellerProfileDto.builder()
				.name(user.getFirstName() + " " + user.getLastName()).email(user.getEmail())
				.mobileNumber(user.getMobileNumber()).dob(user.getDob()).storeName(user.getSeller().getStoreName())
				.address(user.getSeller().getAddress()).phone(user.getSeller().getPhone())
				.gstinNumber(user.getSeller().getGstinNumber()).build();

		return sellerProfileDto;

	}

}
