package com.greenify.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.greenify.dao.SellerDao;
import com.greenify.dao.UserDao;
import com.greenify.dto.SellerDto;
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

}
