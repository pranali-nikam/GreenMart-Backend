package com.greenify.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greenify.entities.Category;

public interface CategoryDao extends JpaRepository<Category, Long> {

}
