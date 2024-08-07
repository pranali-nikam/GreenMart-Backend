package com.greenify.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.greenify.entities.Order;

public interface OrderDao extends JpaRepository<Order, Long> {

	@Query("SELECT o.status ,count(o) FROM Order o WHERE o.seller.sellerId = :sellerId GROUP BY o.status")
	List<List<String>> countOfOrderByStatus(@Param("sellerId")Long sellerId);
	
}
