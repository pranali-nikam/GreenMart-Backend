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
	@Query("UPDATE User u SET u.isBlocked = :isBlocked WHERE u.userId = :userId")
	int blockUnblockUser(@Param("userId")Long userId, @Param("isBlocked")Boolean isBlocked);
	
	@Modifying
	@Transactional
	@Query("UPDATE User u SET u.isBlocked = :isBlocked WHERE u.userId = (SELECT s.user.userId FROM Seller s WHERE s.sellerId = :sellerId )")
	int blockUnblockSeller(@Param("sellerId")Long sellerId, @Param("isBlocked")Boolean isBlocked);
	

    
    @Query("SELECT u FROM User u WHERE u.userId = (SELECT s.user.userId FROM Seller s WHERE s.sellerId = :sellerId)")
    User findBySellerId(Long sellerId);
    
	Optional<User> findByEmailAndPassword(String email, String pwd);

}
