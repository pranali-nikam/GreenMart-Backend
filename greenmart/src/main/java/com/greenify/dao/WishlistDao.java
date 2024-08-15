package com.greenify.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.greenify.entities.Wishlist;

public interface WishlistDao extends JpaRepository<Wishlist,Long> {
	
	@Query("SELECT w FROM Wishlist w WHERE w.user.userId = :userId")
	Wishlist findByUser(@Param("userId")Long userId);
	

}
