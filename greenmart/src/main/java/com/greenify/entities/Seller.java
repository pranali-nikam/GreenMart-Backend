package com.greenify.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@Table(name="sellers")
public class Seller extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long sellerId;
	
	@OneToOne
	@JoinColumn(name="user_id",nullable = false)
	private User user;
	
	@Column(nullable = false)
	private String storeName;
	
	@Column(nullable = false)
	private String address;
	
	@Column(nullable = false)
	private String phone;
	
	@Column(nullable = false,unique = true)
	private String gstinNumber;
	
	@OneToMany(mappedBy = "seller",cascade = CascadeType.ALL,orphanRemoval = true)
	private List<Product> product;
	
	@OneToMany(mappedBy = "seller",cascade = CascadeType.ALL,orphanRemoval = true)
	private List<Order> order;

	
	
}
