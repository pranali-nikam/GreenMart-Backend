package com.greenify.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.greenify.dao.CategoryDao;
import com.greenify.dto.categoryDtos.CategoryDto;
import com.greenify.dto.categoryDtos.CategoryNameDto;
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

	@Override
	public List<CategoryNameDto> getAllCategories() {
		List<CategoryNameDto> categoryNameDtoList = new ArrayList<>();
		List<Category> categoryList = categoryDao.findAll();
		
		categoryList.forEach(category ->{
			CategoryNameDto categoryNamedto = modelMapper.map(category,CategoryNameDto.class);
			categoryNameDtoList.add(categoryNamedto);
		});
		
		return categoryNameDtoList;
	}

}
