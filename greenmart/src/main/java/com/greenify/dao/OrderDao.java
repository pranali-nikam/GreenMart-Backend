package com.greenify.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greenify.entities.Order;

public interface OrderDao extends JpaRepository<Order, Long> {

}
