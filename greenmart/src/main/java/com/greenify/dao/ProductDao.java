package com.greenify.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.greenify.dto.productDtos.ProductSearchDto;
import com.greenify.entities.Category;
import com.greenify.entities.Product;

public interface ProductDao extends JpaRepository<Product, Long> {
	
	
    @Query("SELECT COUNT(p) FROM Product p WHERE p.seller.sellerId = :sellerId")
	Long countProductsBySellerId(@Param("sellerId")Long sellerId);
    
    @Query("SELECT p FROM Product p WHERE p.seller.sellerId = :sellerId")
    List<Product> findAllBySellerId(@Param("sellerId")Long sellerId);
    
    @Query("SELECT p.stock FROM Product p WHERE p.productId = :productId")
   	Integer findStockById(@Param("productId")Long productId);
    
    @Modifying
	@Transactional
    @Query("UPDATE Product p SET p.stock = :stockCount WHERE p.productId = :productId")
    void decrementStock(@Param("productId")Long productId,@Param("stockCount") Integer stockCount);
    
    List<Product> findByCategory_CategoryId(Long categoryId);

    
    
}
