package com.greenify.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.greenify.entities.Order;
import com.greenify.enums.Status;

public interface OrderDao extends JpaRepository<Order, Long> {

	@Query("SELECT o.status ,count(o) FROM Order o WHERE o.seller.sellerId = :sellerId GROUP BY o.status")
	List<List<String>> countOfOrderByStatusAndSellerId(@Param("sellerId")Long sellerId);
	
	
	@Query("SELECT o FROM Order  o WHERE o.seller.sellerId = :sellerId and o.status = :status ")
	List<Order> findAllOrdersBySellerIdAndStatus(@Param("sellerId")Long sellerId,@Param("status") Status status);
	
	
	@Query("SELECT o FROM Order  o WHERE o.status = :status ")
	List<Order> findAllOrdersByStatus(@Param("status") Status status);
	
	@Query("SELECT o.status ,count(o) FROM Order o GROUP BY o.status")
	List<List<String>> countOrderByStatus();

	
}
