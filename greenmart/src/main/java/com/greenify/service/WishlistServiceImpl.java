package com.greenify.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.greenify.dao.ProductDao;
import com.greenify.dao.UserDao;
import com.greenify.dao.WishListItemDao;
import com.greenify.dao.WishlistDao;
import com.greenify.dto.WishlistDetailsDto;
import com.greenify.dto.WishlistDto;
import com.greenify.dto.productDtos.ProductDetailsDto;
import com.greenify.entities.Product;
import com.greenify.entities.User;
import com.greenify.entities.WishListItem;
import com.greenify.entities.Wishlist;
import com.greenify.exceptions.BusinessException;

@Service
@Transactional

public class WishlistServiceImpl implements WishListService {

	@Autowired
	private WishlistDao wishlistDao;

	@Autowired
	private WishListItemDao wishlistItemDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private ProductDao productDao;

	@Override
	public List<WishlistDetailsDto> getWishlistByUserId(Long userId) {

		User user = userDao.findById(userId).orElseThrow(() -> new BusinessException("User not found"));

		Wishlist wishlist = user.getWishlist();

		if (wishlist == null) {
			throw new BusinessException("Wishlist is empty");
		}

		List<WishlistDetailsDto> wishlistDetailsDtos = new ArrayList<>();

		for (WishListItem wishlistItem : wishlist.getWishlistItems()) {

			Product product = wishlistItem.getProduct();

			wishlistDetailsDtos.add(WishlistDetailsDto.builder().productId(product.getProductId())
					.productName(product.getProductName()).categoryName(product.getCategory().getCategoryName())
					.description(product.getDescription()).price(product.getPrice()).stock(product.getStock())
					.imageUrl(getImagePath(product.getImageUrl())).build());
		}

		return wishlistDetailsDtos;
	}

	@Override
	public String addProductToWishlist(WishlistDto wishlistDto, Long userId) {

		Product product = productDao.findById(wishlistDto.getProductId()).orElseThrow();
		User user = userDao.findById(userId).orElseThrow();

		Wishlist wishlist = user.getWishlist();
		if (wishlist == null) {
			wishlist = new Wishlist();
			user.setWishlist(wishlist);
			wishlist.setUser(user); // Set the back-reference
		}
		for (WishListItem item : wishlist.getWishlistItems()) {
			if (item.getProduct().equals(product)) {
				throw new BusinessException("Product already in Wishlist");
			}
		}

		WishListItem wishlistItem = new WishListItem();
		wishlistItem.setProduct(product);
		wishlist.addNewItem(wishlistItem);

		wishlistDao.save(wishlist);

		return "Item added in wishlist";

	}

	@Override
	public String removeProductFromWishlist(Long productId, Long userId) {

		User customer = userDao.findById(userId).orElseThrow(() -> new BusinessException("User not found"));

		Wishlist wishlist = customer.getWishlist();
		if (wishlist == null) {
			throw new RuntimeException("Wishlist not found");
		}

		WishListItem itemToDelete = wishlist.getWishlistItems().stream()
				.filter(w -> w.getProduct().getProductId().equals(productId)).findFirst().orElse(null);

		if (itemToDelete != null) {
			wishlist.getWishlistItems().remove(itemToDelete);
			wishlistItemDao.deleteById(itemToDelete.getWishListItemId());
		} else {
			throw new BusinessException("Product not found in wishlist");
		}

		return "Item deleted from Wishlist";

	}

	private String getImagePath(String imageName) {
		return ServletUriComponentsBuilder.fromCurrentContextPath().path("/uploads/images/").path(imageName)
				.toUriString();
	}
}
