package com.greenify.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.greenify.entities.ShippingAddress;
import com.greenify.entities.User;
import com.greenify.enums.Role;

public interface UserDao extends JpaRepository<User, Long> {
	
	List<User> findAllByRole(Role role);
	
	
	@Modifying
	@Transactional
	@Query("UPDATE User u SET u.isBlocked = true WHERE u.userId = :userId")
	int blockUser(Long userId);
	
	@Modifying
	@Transactional
	@Query("UPDATE User u SET u.isBlocked = false WHERE u.userId = :userId")
	int unblockUser(Long userId);
	
	@Modifying
	@Transactional
	@Query("UPDATE User u SET u.isBlocked = true WHERE u.userId = (SELECT s.user.userId FROM Seller s WHERE s.sellerId = :sellerId )")
	int blockSeller(@Param("sellerId")Long sellerId);
	
	@Modifying
	@Transactional
	@Query("UPDATE User u SET u.isBlocked = false WHERE u.userId = (SELECT s.user.userId FROM Seller s WHERE s.sellerId = :sellerId )")
	int unblockSeller(@Param("sellerId")Long sellerId);

	
	
	

}
