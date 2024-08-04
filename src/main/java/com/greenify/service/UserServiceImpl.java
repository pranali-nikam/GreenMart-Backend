package com.greenify.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.greenify.dao.UserDao;
import com.greenify.dto.userDtos.UserDto;
import com.greenify.entities.Role;
import com.greenify.entities.User;

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

	
}
