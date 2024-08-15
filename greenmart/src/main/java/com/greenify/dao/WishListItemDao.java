package com.greenify.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greenify.entities.WishListItem;

public interface WishListItemDao extends JpaRepository<WishListItem, Long> {

}
