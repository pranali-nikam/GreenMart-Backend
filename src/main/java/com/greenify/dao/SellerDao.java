package com.greenify.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greenify.entities.Seller;

public interface SellerDao extends JpaRepository<Seller, Long> {


}
