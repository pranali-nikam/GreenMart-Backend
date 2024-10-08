package com.greenify.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.greenify.dto.orderDtos.OrderItemStatusCountDto;
import com.greenify.dto.orderDtos.OrderItemStatusDto;
import com.greenify.dto.orderDtos.OrderStatusCountDto;
import com.greenify.dto.orderDtos.OrdersStatusDto;
import com.greenify.dto.productDtos.ProductDetailsDto;
import com.greenify.dto.productDtos.ProductPartialUpdateDto;
import com.greenify.dto.productDtos.ProductUpdateDto;
import com.greenify.dto.sellerDtos.SellerDto;
import com.greenify.dto.sellerDtos.SellerProfileDto;
import com.greenify.enums.Status;
import com.greenify.service.OrderItemService;
import com.greenify.service.OrderService;
import com.greenify.service.ProductService;
import com.greenify.service.SellerService;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/sellers")
public class SellerController {

	@Autowired
	private SellerService sellerService;

	@Autowired
	private ProductService productService;

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private OrderItemService orderItemService;

	@PostMapping("/register")
	@ResponseStatus(HttpStatus.CREATED)
	public SellerDto registerSeller(@RequestBody SellerDto sellerDto) {
		return sellerService.registerSeller(sellerDto);
	}

	@GetMapping("/produtsCount/{sellerId}")
	@ResponseStatus(HttpStatus.OK)
	public Long countProductsBySeller(@PathVariable Long sellerId) {
		return productService.countProductsBySeller(sellerId);
	}

	@PatchMapping("/update/{productId}")
	public ResponseEntity<Object> updateProduct(@RequestBody List<ProductUpdateDto> productUpdateDtos,
			@PathVariable("productId") Long productId) {

		ProductPartialUpdateDto productPartialUpdateDto = productService.updateProductDetails(productUpdateDtos,
				productId);

		if (productPartialUpdateDto != null) {
			return new ResponseEntity<Object>(productPartialUpdateDto, HttpStatus.OK);
		}

		return new ResponseEntity<Object>("Product Not Found for Id : " + productId, HttpStatus.NOT_FOUND);
	}

	@GetMapping("/getProductsBySellerId/{sellerId}")
	@ResponseStatus(HttpStatus.OK)
	public List<ProductDetailsDto> getAllProductsBySellerId(@PathVariable Long sellerId) {
		return productService.getAllProductsBySellerId(sellerId);
	}

	@GetMapping("/countOfStatus/{sellerId}")
	@ResponseStatus(HttpStatus.OK)
	public OrderItemStatusCountDto countOrdersByStatusAndSellerId(@PathVariable Long sellerId) {

		return orderItemService.countOrdersByStatusAndSellerId(sellerId);

	}

	@GetMapping("/getOrdersByStatus/{sellerId}")
	@ResponseStatus(HttpStatus.OK)
	public List<OrderItemStatusDto> getOrdersByStatusAndSellerId(@PathVariable Long sellerId, String status) {
		return orderItemService.getOrdersByStatusAndSellerId(sellerId, Status.valueOf(status.toUpperCase()));
	}

	
	@GetMapping("/getSellerProfile/{sellerId}")
	@ResponseStatus(HttpStatus.OK)
	public  SellerProfileDto getSellerProfile(@PathVariable Long sellerId) {
		return sellerService.getSellerProfile(sellerId);
	}
	


	@PatchMapping("/updateOrderByStatus")
	@ResponseStatus(HttpStatus.OK)
	public void updateOrderByStatus(@RequestParam Long orderId, @RequestParam String status) {
		orderItemService.updateOrderByStatus(orderId, Status.valueOf(status.toUpperCase()));
	}
}
