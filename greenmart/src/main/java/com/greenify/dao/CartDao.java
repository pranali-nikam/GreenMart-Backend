package com.greenify.dao;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.greenify.entities.Cart;
import com.greenify.entities.Product;
import com.greenify.entities.User;


public interface CartDao extends JpaRepository<Cart,Long>{
	
	List<Cart> findByUser(User user);
	
	Optional<Cart> findByUserAndProduct(User user,Product product);
	
	}
