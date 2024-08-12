package com.greenify.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.greenify.dao.ProductDao;
import com.greenify.dao.UserDao;
import com.greenify.dao.WishlistDao;
import com.greenify.dto.WishlistDetailsDto;
import com.greenify.dto.WishlistDto;
import com.greenify.dto.productDtos.ProductDetailsDto;
import com.greenify.entities.Product;
import com.greenify.entities.User;
import com.greenify.entities.Wishlist;
import com.greenify.exceptions.BusinessException;

@Service
@Transactional

public class WishlistServiceImpl implements WishListService {
	
	@Autowired
	private WishlistDao wishlistDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ProductDao productDao;

	@Override
	public List<WishlistDetailsDto> getWishlistByUserId(Long userId) {
	
	 List<Wishlist> wishlistItems = wishlistDao.findByUser(userId);
		
	 List<WishlistDetailsDto> wishlistDetailsDtos = new ArrayList<>();

	 wishlistItems.forEach(wishlist -> {
		 WishlistDetailsDto wishlistDetailsDto = WishlistDetailsDto.builder()
				 .productName(wishlist.getProduct().getProductName())
				 .description(wishlist.getProduct().getDescription())
				 .categoryName(wishlist.getProduct().getCategory().getCategoryName())
				 .imageUrl(wishlist.getProduct().getImageUrl())
				 .price(wishlist.getProduct().getPrice())
				 .stock(wishlist.getProduct().getStock())
				 .build();
		 wishlistDetailsDtos.add(wishlistDetailsDto);
	 });
	  

	   return wishlistDetailsDtos;
	}

	@Override
	public void addProductToWishlist(WishlistDto wishlistDto,Long userId) {
		 User user = userDao.findById(userId)
				     .orElseThrow(()-> new BusinessException("User not found"));
		 
		 Product product = productDao.findById(wishlistDto.getProductId())
				           .orElseThrow(()-> new BusinessException("Product not found"));
			
		 Wishlist wishlist = new Wishlist();
		 wishlist.setUser(user);
		 wishlist.setProduct(product);
		 wishlistDao.save(wishlist);
		
	}

	@Override
	public void removeProductFromWishlist(WishlistDto wishlistDto,Long userId) {
		
		
		List<Wishlist> wishlistOpt = wishlistDao.findByUser(userId).stream()
                .filter(wishlist -> wishlist.getProduct().getProductId().equals(wishlistDto.getProductId()))
                .collect(Collectors.toList());
                
                if(!wishlistOpt.isEmpty()) {
                	wishlistDao.deleteAll(wishlistOpt);
                }else {
                	throw new BusinessException("Product not found in the user's wishlist");
                }
				
		
	}

}
