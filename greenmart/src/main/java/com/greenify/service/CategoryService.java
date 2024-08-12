package com.greenify.service;

import java.util.List;

import com.greenify.dto.categoryDtos.CategoryDto;
import com.greenify.dto.categoryDtos.CategoryNameDto;

public interface CategoryService {

	public CategoryDto addCategories(CategoryDto categoryDto);
	
	public List<CategoryNameDto> getAllCategories();
}
