package com.greenify.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.greenify.dao.ProductDao;
import com.greenify.dto.productDtos.ProductDetailsDto;
import com.greenify.dto.productDtos.ProductDto;
import com.greenify.dto.productDtos.ProductUpdateDto;
import com.greenify.entities.Category;
import com.greenify.entities.Product;
import com.greenify.entities.Seller;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ProductDto addProduct(ProductDto productDto,Long sellerId) {
		Product product = modelMapper.map(productDto, Product.class);
		
		Category category = new Category();
		category.setCategoryId(productDto.getCategoryId());
		product.setCategory(category);
		
		Seller seller = new Seller();
		seller.setSellerId(sellerId);
		product.setSeller(seller);
		
		Product savedProduct = productDao.save(product);
		ProductDto dto = modelMapper.map(savedProduct, ProductDto.class);
		dto.setCategoryId(savedProduct.getCategory().getCategoryId());
		return dto;
	}

	@Override
	public List<ProductDetailsDto> getAllProducts() {
		List<ProductDetailsDto> productDetailsDtoList = new ArrayList<>();
	
		 List<Product> productList = productDao.findAll();
		 productList.forEach(product ->{
			ProductDetailsDto  productDetailsDto = ProductDetailsDto.builder()
			 .productName(product.getProductName())
			 .price(product.getPrice())
			 .description(product.getDescription())
			 .stock(product.getStock())
			 .categoryName(product.getCategory().getCategoryName())
			 .imageUrl(product.getImageUrl())
			 .build();
			productDetailsDtoList.add(productDetailsDto);
		 });
		return productDetailsDtoList;
	}

	@Override
	public Long countProductsBySeller(Long sellerId) {
		
		return productDao.countProductsBySellerId(sellerId);
	}

	@Override
	public Long countAllProduct() {
		return productDao.count();

	}

	@Override
	public void deleteProducts(Long productId) {
		
		 productDao.deleteById(productId);
	}

	@Override
	public ProductDto updateProductDetails(List<ProductUpdateDto> productUpdateDto, Long productId) {
		
		Optional<Product> optionalProduct = productDao.findById(productId);
		Product product = optionalProduct.orElse(null);
		
		if(product!=null) {
			
		productUpdateDto.forEach(dto -> {
			if("productName".equals(dto.getFieldName())) {
				product.setProductName(dto.getValue());
			}
		});
		
		return modelMapper.map(productDao.save(product), ProductDto.class);
		}
		
		return null;
	}
	
	

}
