package com.greenify.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.greenify.dao.ProductDao;
import com.greenify.dao.UserDao;
import com.greenify.dao.WishlistDao;
import com.greenify.dto.WishlistDto;
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
	public List<WishlistDto> getWishlistByUserId(Long userId) {
	 User user = userDao.findById(userId)
			            .orElseThrow(() -> new BusinessException("User Not Found"));
	 
	 List<Wishlist> wishlistItems = wishlistDao.findByUser(user);
	 
		return wishlistItems.stream()
				             .map(wishlist -> {
				            	 WishlistDto dto = new WishlistDto();
				            	 dto.setUserId(user.getUserId());
				            	 dto.setProductId(wishlist.getProduct().getProductId());
				            	 return dto;
				             })
				             .collect(Collectors.toList());
	}

	@Override
	public void addProductToWishlist(WishlistDto wishlistDto) {
		 User user = userDao.findById(wishlistDto.getUserId())
				     .orElseThrow(()-> new BusinessException("User not found"));
		 
		 Product product = productDao.findById(wishlistDto.getProductId())
				           .orElseThrow(()-> new BusinessException("Product not found"));
			
		 Wishlist wishlist = new Wishlist();
		 wishlist.setUser(user);
		 wishlist.setProduct(product);
		 wishlistDao.save(wishlist);
		
	}

	@Override
	public void removeProductFromWishlist(WishlistDto wishlistDto) {
		User user = userDao.findById(wishlistDto.getUserId())
				    .orElseThrow(()-> new BusinessException("User not found"));
		
		Optional<Wishlist> wishlistOpt = wishlistDao.findByUser(user).stream()
                .filter(wishlist -> wishlist.getProduct().getProductId().equals(wishlistDto.getProductId()))
                .findFirst();
                
                if(wishlistOpt.isPresent()) {
                	wishlistDao.delete(wishlistOpt.get());
                }else {
                	throw new BusinessException("Product not found in the user's wishlist");
                }
				
		
	}

}
