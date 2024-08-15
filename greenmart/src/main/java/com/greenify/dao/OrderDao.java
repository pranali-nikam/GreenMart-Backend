package com.greenify.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.greenify.entities.Order;
import com.greenify.enums.Status;

public interface OrderDao extends JpaRepository<Order, Long> {
	
	@Query("SELECT o FROM Order o WHERE o.user.userId = :userId")
	List<Order> findAllOrderByUserId(Long userId);
	
}
