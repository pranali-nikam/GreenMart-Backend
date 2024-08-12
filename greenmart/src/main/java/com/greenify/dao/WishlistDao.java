package com.greenify.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.greenify.entities.Wishlist;

public interface WishlistDao extends JpaRepository<Wishlist,Long> {
	
	@Query("SELECT w FROM Wishlist w WHERE w.user.userId = :userId")
	List<Wishlist> findByUser(@Param("userId")Long userId);

}
