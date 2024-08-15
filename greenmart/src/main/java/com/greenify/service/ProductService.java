package com.greenify.service;

import java.util.List;

import com.greenify.dto.productDtos.ProductDetailsDto;
import com.greenify.dto.productDtos.ProductDto;
import com.greenify.dto.productDtos.ProductPartialUpdateDto;
import com.greenify.dto.productDtos.ProductSearchDto;
import com.greenify.dto.productDtos.ProductUpdateDto;

public interface ProductService {
	
	public ProductDto addProduct(ProductDto productDto,Long sellerId);
	
	public List<ProductDetailsDto> getAllProducts();
	
	public Long countProductsBySeller(Long sellerId);
	
	public Long countAllProduct();
	
	public void deleteProducts(Long productId);
	
	public ProductPartialUpdateDto updateProductDetails(List<ProductUpdateDto> productUpdateDto, Long productId);
	
	public List<ProductDetailsDto> getAllProductsBySellerId(Long sellerId);

    public List<ProductSearchDto> getAllProductByCategoryId(Long categoryId);
    
    public List<ProductSearchDto> getProductByProductName(String productName);
    
    public ProductDetailsDto getProductByProductId(Long productId);

}
