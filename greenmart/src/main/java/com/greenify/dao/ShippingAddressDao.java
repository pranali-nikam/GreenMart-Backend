package com.greenify.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.greenify.entities.ShippingAddress;

public interface ShippingAddressDao extends JpaRepository<ShippingAddress, Long>{
	
	@Query("SELECT s FROM ShippingAddress s WHERE s.user.userId = :userId")
	List<ShippingAddress> findAllByUserId(@Param("userId") Long userId);
	
}
