package com.greenify.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.greenify.entities.Seller;

public interface SellerDao extends JpaRepository<Seller, Long> {

}
