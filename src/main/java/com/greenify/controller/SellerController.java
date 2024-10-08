package com.greenify.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.greenify.dto.productDtos.ProductDetailsDto;
import com.greenify.dto.productDtos.ProductDto;
import com.greenify.dto.productDtos.ProductUpdateDto;
import com.greenify.dto.sellerDtos.SellerDto;
import com.greenify.entities.Product;
import com.greenify.service.ProductService;
import com.greenify.service.SellerService;

@RestController
@RequestMapping("api/sellers")
public class SellerController {

	@Autowired
	private SellerService sellerService;
	
	@Autowired
	private ProductService productService;
	
	@PostMapping("/register")
	@ResponseStatus(HttpStatus.CREATED)
	public SellerDto registerSeller(@RequestBody SellerDto sellerDto) {
		return sellerService.registerSeller(sellerDto);
	}
	
	
	@GetMapping("/getProducts")
	@ResponseStatus(HttpStatus.OK)
	public List<ProductDetailsDto> getAllProducts() {
		return productService.getAllProducts();
	}
	
	@GetMapping("/produtsCount/{sellerId}")
	@ResponseStatus(HttpStatus.OK)
	public Long countProductsBySeller(@PathVariable Long sellerId) {
		return productService.countProductsBySeller(sellerId);
	}
	
	@PatchMapping("/update/{productId}")
	public ResponseEntity<Object> updateProduct(@RequestBody List<ProductUpdateDto> productUpdateDtos, @PathVariable("productId") Long productId){
		
		ProductDto productDto = productService.updateProductDetails(productUpdateDtos, productId);
		
		if(productDto!=null) {
			return new ResponseEntity<Object>(productDto, HttpStatus.OK);
		}
		
		return new ResponseEntity<Object>("Product Not Found for Id : "+productId,HttpStatus.NOT_FOUND);
	}
}
