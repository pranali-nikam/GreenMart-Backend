package com.greenify.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.greenify.dao.ShippingAddressDao;
import com.greenify.dao.UserDao;
import com.greenify.dto.LoginDto;
import com.greenify.dto.SignInRequest;
import com.greenify.dto.orderDtos.ShippingDetailDto;
import com.greenify.dto.sellerDtos.SellerDto;
import com.greenify.dto.userDtos.CompleteUserDetailsDto;
import com.greenify.dto.userDtos.ProfileDto;
import com.greenify.dto.userDtos.UserDto;
import com.greenify.entities.ShippingAddress;
import com.greenify.entities.User;
import com.greenify.enums.Role;
import com.greenify.exceptions.BusinessException;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private ShippingAddressDao shippingAddressDao;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDto registerUser(UserDto userDto) {
		User user = modelMapper.map(userDto, User.class);
		user.setRole(Role.CUSTOMER);
		User savedUser = userDao.save(user);
		return modelMapper.map(savedUser, UserDto.class);
	}

	@Override
	public List<CompleteUserDetailsDto> getUsers() {
		List<CompleteUserDetailsDto> userDtoList = new ArrayList<>();

		List<User> userList = userDao.findAllByRole(Role.CUSTOMER);
		userList.forEach(user -> {
			CompleteUserDetailsDto completeUserDetailsDto = modelMapper.map(user, CompleteUserDetailsDto.class);
			userDtoList.add(completeUserDetailsDto);
		});
		return userDtoList;
	}

	@Override
	public int blockUnblockUser(Long userId, Boolean isBlocked) {

		return userDao.blockUnblockUser(userId, isBlocked);

	}

	@Override
	public ShippingDetailDto addShippingAddress(ShippingDetailDto shippingDetailDto, Long userId) {
		ShippingAddress shippingAddress = modelMapper.map(shippingDetailDto, ShippingAddress.class);

		User user = new User();
		user.setUserId(userId);

		shippingAddress.setUser(user);

		return modelMapper.map(shippingAddressDao.save(shippingAddress), ShippingDetailDto.class);
	}

	@Override
	public List<ShippingDetailDto> getShippingAddress(Long userId) {
		List<ShippingAddress> shippingAddresses = shippingAddressDao.findAllByUserId(userId);

		List<ShippingDetailDto> shippingDetailDtos = new ArrayList<ShippingDetailDto>();

		if (shippingAddresses != null) {
			shippingAddresses.forEach(shippingAddress -> {
				shippingDetailDtos.add(modelMapper.map(shippingAddress, ShippingDetailDto.class));
			});
		}
		return shippingDetailDtos;
	}

	@Override
	public ProfileDto getUserProfile(Long userId) {
		User user = userDao.findById(userId).get();
		List<ShippingAddress> shippingAddressList = user.getShippingAddress();

		if (!shippingAddressList.isEmpty()) {
			ShippingAddress shippingAddress = shippingAddressList.get(0);
			ProfileDto profileDto = ProfileDto.builder().name(user.getFirstName() + " " + user.getLastName())
					.mobileNumber(user.getMobileNumber()).email(user.getEmail()).dob(user.getDob())
					.shippingDetail(ShippingDetailDto.builder().addressLine1(shippingAddress.getAddressLine1())
							.addressLine2(shippingAddress.getAddressLine2()).city(shippingAddress.getCity())
							.country(shippingAddress.getCountry()).state(shippingAddress.getState())
							.zipcode(shippingAddress.getZipcode()).build())
					.build();
			return profileDto;

		}

		ProfileDto profileDto = ProfileDto.builder().name(user.getFirstName() + " " + user.getLastName())
				.mobileNumber(user.getMobileNumber()).email(user.getEmail()).dob(user.getDob())
				.shippingDetail(ShippingDetailDto.builder().build()).build();
		return profileDto;

	}

	@Override
	public LoginDto authenticateUser(SignInRequest request) {

		User userEntity = userDao.findByEmailAndPassword(request.getEmail(), request.getPassword())
				.orElseThrow(() -> new BusinessException("Invalid Email Or Password"));

		if (userEntity.getRole() == Role.valueOf("CUSTOMER")) {

			return LoginDto.builder()
					.userDto(UserDto.builder().email(userEntity.getEmail()).firstName(userEntity.getFirstName())
							.lastName(userEntity.getLastName()).dob(userEntity.getDob())
							.mobileNumber(userEntity.getMobileNumber()).password(userEntity.getPassword()).build())
					.isBlocked(userEntity.getIsBlocked()).role(userEntity.getRole()).id(userEntity.getUserId()).build();

		} else if (userEntity.getRole() == Role.valueOf("SELLER")) {

			return LoginDto.builder().sellerDto(SellerDto.builder()

					.user(UserDto.builder().email(userEntity.getEmail()).firstName(userEntity.getFirstName())
							.lastName(userEntity.getLastName()).dob(userEntity.getDob())
							.mobileNumber(userEntity.getMobileNumber()).password(userEntity.getPassword()).build())
					.address(userEntity.getSeller().getAddress()).gstinNumber(userEntity.getSeller().getGstinNumber())
					.storeName(userEntity.getSeller().getStoreName()).phone(userEntity.getSeller().getPhone()).build())
					.isBlocked(userEntity.getIsBlocked()).role(userEntity.getRole())
					.id(userEntity.getSeller().getSellerId()).build();
		} else {
			return LoginDto.builder()
					.profileDto(ProfileDto.builder().name(userEntity.getFirstName() + " " + userEntity.getLastName())
							.dob(userEntity.getDob()).email(userEntity.getEmail())
							.mobileNumber(userEntity.getMobileNumber()).build())
					.isBlocked(userEntity.getIsBlocked()).role(userEntity.getRole()).id(userEntity.getUserId()).build();
		}
	}

	@Override
	public Long getCountofUsers() {
		return userDao.countofUsers();
	}

	@Override
	public Long getCountofSellers() {
		return userDao.countofSellers();
	}

}
