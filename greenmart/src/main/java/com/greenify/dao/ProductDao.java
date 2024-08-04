package com.greenify.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.greenify.entities.Product;

public interface ProductDao extends JpaRepository<Product, Long> {
	
	
    @Query("SELECT COUNT(p) FROM Product p WHERE p.seller.sellerId = :sellerId")
	Long countProductsBySellerId(@Param("sellerId")Long sellerId);
    
    @Query("SELECT p FROM Product p WHERE p.seller.sellerId = :sellerId")
    List<Product> findAllBySellerId(@Param("sellerId")Long sellerId);

}
