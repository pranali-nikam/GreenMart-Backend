package com.greenify.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.greenify.dao.CartDao;
import com.greenify.dao.ProductDao;
import com.greenify.dao.UserDao;
import com.greenify.dto.CartDto;
import com.greenify.dto.productDtos.ProductQuantityDto;
import com.greenify.entities.Cart;
import com.greenify.entities.Product;
import com.greenify.entities.User;
import com.greenify.exceptions.BusinessException;

@Service
@Transactional
public class CartServiceImpl implements CartService{
	
	@Autowired
	private CartDao cartDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ProductDao productDao;
	

	@Override
	public void addProductToCart(CartDto cartDto) {
	 User user = userDao.findById(cartDto.getUserId())
			 .orElseThrow(()->new BusinessException("User Not Found"));
	 
	 Product product = productDao.findById(cartDto.getProductId())
			 .orElseThrow(()->new BusinessException("Product not Found"));
	 
	 Optional<Cart>	cartOpt = cartDao.findByUserAndProduct(user, product); 
		 
	   if(cartOpt.isPresent()) {
		   Cart cart = cartOpt.get();
		   cart.setQuantity(cart.getQuantity() + cartDto.getQuantity());
		   cartDao.save(cart);
	   } else {
		   Cart newCart = new Cart();
		   newCart.setUser(user);
		   newCart.setProduct(product);
		   newCart.setQuantity(cartDto.getQuantity());
		   cartDao.save(newCart);
	   }
	}

	@Override
	public List<ProductQuantityDto> getProductsInCart(Long userId){
		User user = userDao.findById(userId)
				.orElseThrow(()->new BusinessException("User not found"));
		
		List<Cart> cartItems = cartDao.findByUser(user);
		return cartItems.stream()
				.map(cart -> {
					ProductQuantityDto dto = new ProductQuantityDto();
					dto.setProductId(cart.getProduct().getProductId());
					dto.setProductName(cart.getProduct().getProductName());
					dto.setQuantity(cart.getQuantity());
					return dto;
				})
				.collect(Collectors.toList());
		
	}
	

	@Override
	public void updateProductQuantity(CartDto cartDto) {
		User user = userDao.findById(cartDto.getUserId())
				.orElseThrow(() -> new BusinessException("User not found"));
		
		Product product = productDao.findById(cartDto.getProductId())
				.orElseThrow(()-> new BusinessException("Product not found"));
		
		Cart cart = cartDao.findByUserAndProduct(user, product)
				.orElseThrow(()-> new BusinessException("Product not found in the user's cart"));
		
		cart.setQuantity(cartDto.getQuantity());
		cartDao.save(cart);
		
		
	}

	@Override
	public void removeProductFromCart(CartDto cartDto) {
		User user = userDao.findById(cartDto.getUserId())
				.orElseThrow(()-> new BusinessException("User not found"));
		
		Product product = productDao.findById(cartDto.getProductId())
				.orElseThrow(()-> new BusinessException("Product not found"));
		
		Cart cart = cartDao.findByUserAndProduct(user, product)
				.orElseThrow(()-> new BusinessException("Product not found in the user's cart"));
		
		cartDao.delete(cart);
	}

}
