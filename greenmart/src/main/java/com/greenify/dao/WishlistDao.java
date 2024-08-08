package com.greenify.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greenify.entities.User;
import com.greenify.entities.Wishlist;

public interface WishlistDao extends JpaRepository<Wishlist,Long> {
	List<Wishlist> findByUser(User u);

}
