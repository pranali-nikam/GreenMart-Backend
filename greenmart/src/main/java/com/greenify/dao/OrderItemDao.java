package com.greenify.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.greenify.entities.OrderItem;
import com.greenify.enums.Status;

public interface OrderItemDao extends JpaRepository<OrderItem, Long> {

	@Query("SELECT o.status ,count(o) FROM OrderItem o WHERE o.seller.sellerId = :sellerId GROUP BY o.status")
	List<List<String>> countOfOrderItemByStatusAndSellerId(@Param("sellerId") Long sellerId);

	@Query("SELECT o FROM OrderItem  o WHERE o.seller.sellerId = :sellerId and o.status = :status ")
	List<OrderItem> findAllOrderItemBySellerIdAndStatus(@Param("sellerId") Long sellerId, @Param("status") Status status);

	@Query("SELECT o FROM OrderItem o WHERE o.status = :status ")
	Page<OrderItem> findAllOrderItemByStatus(Pageable pageable,@Param("status") Status status);

	@Modifying
	@Transactional
	@Query("UPDATE OrderItem o SET o.status = :status WHERE o.orderItemID = :orderItemID")
	void updateOrderStatus(@Param("orderItemID") Long orderItemID, Status status);
}
