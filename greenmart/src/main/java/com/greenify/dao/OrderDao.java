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

	@Query("SELECT o.status ,count(o) FROM Order o WHERE o.seller.sellerId = :sellerId GROUP BY o.status")
	List<List<String>> countOfOrderByStatusAndSellerId(@Param("sellerId") Long sellerId);

	@Query("SELECT o FROM Order  o WHERE o.seller.sellerId = :sellerId and o.status = :status ")
	List<Order> findAllOrdersBySellerIdAndStatus(@Param("sellerId") Long sellerId, @Param("status") Status status);

	@Query("SELECT o FROM Order o WHERE o.status = :status ")
	Page<Order> findAllOrdersByStatus(Pageable pageable,@Param("status") Status status);

	@Query("SELECT o.status ,count(o) FROM Order o GROUP BY o.status")
	List<List<String>> countOrderByStatus();

	@Modifying
	@Transactional
	@Query("UPDATE Order o SET o.status = :status WHERE o.orderId = :orderId")
	void updateOrderStatus(@Param("orderId") Long orderId, Status status);

}
