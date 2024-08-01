package com.greenify.service;

import java.util.List;

import com.greenify.dto.productDtos.ProductDetailsDto;
import com.greenify.dto.productDtos.ProductDto;
import com.greenify.dto.productDtos.ProductUpdateDto;
import com.greenify.entities.Product;

public interface ProductService {
	
	public ProductDto addProduct(ProductDto productDto,Long sellerId);
	
	public List<ProductDetailsDto> getAllProducts();
	
	public Long countProductsBySeller(Long sellerId);
	
	public Long countAllProduct();
	
	public void deleteProducts(Long productId);
	
	public ProductDto updateProductDetails(List<ProductUpdateDto> productUpdateDto, Long productId);
}
