package com.greenify.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.greenify.dto.categoryDtos.CategoryDto;
import com.greenify.dto.categoryDtos.CategoryNameDto;
import com.greenify.service.CategoryService;

@RestController
@RequestMapping("api/category")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/add")
	@ResponseStatus(HttpStatus.CREATED)
	public CategoryDto addCategories(@RequestBody CategoryDto categoryDto) {
		return categoryService.addCategories(categoryDto);
	}
	
	@GetMapping("/getAllCategory")
	@ResponseStatus(HttpStatus.CREATED)
	List<CategoryNameDto> getAllCategories(){
		return categoryService.getAllCategories();
	}
}
