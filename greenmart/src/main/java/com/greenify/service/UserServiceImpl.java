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
import com.greenify.dto.orderDtos.ShippingDetailDto;
import com.greenify.dto.userDtos.CompleteUserDetailsDto;
import com.greenify.dto.userDtos.UserDto;
import com.greenify.entities.ShippingAddress;
import com.greenify.entities.User;
import com.greenify.enums.Role;

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
	public int blockUser(Long userId) {
		
		return userDao.blockUser(userId);
		
	}

	@Override
	public int unblockUser(Long userId) {
		
		return userDao.unblockUser(userId);
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
		
		if(shippingAddresses!=null) {
			shippingAddresses.forEach(shippingAddress->{
				shippingDetailDtos.add(modelMapper.map(shippingAddress, ShippingDetailDto.class));
			});
		}
		return shippingDetailDtos;
	}

	
}
