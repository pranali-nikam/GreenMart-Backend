package com.greenify.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.greenify.dao.UserDao;
import com.greenify.dto.userDtos.CompleteUserDetailsDto;
import com.greenify.dto.userDtos.UserDto;
import com.greenify.entities.User;
import com.greenify.enums.Role;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;
	
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

	
}
