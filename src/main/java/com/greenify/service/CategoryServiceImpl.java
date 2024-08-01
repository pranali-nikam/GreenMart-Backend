package com.greenify.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.greenify.dao.CategoryDao;
import com.greenify.dto.categoryDtos.CategoryDto;
import com.greenify.entities.Category;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryDao categoryDao;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDto addCategories(CategoryDto categoryDto) {
		Category category = modelMapper.map(categoryDto, Category.class);
		categoryDao.save(category);
		return modelMapper.map(category, CategoryDto.class);
	}

}
