package com.greenify.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "shipping_address")
@Setter
@Getter
@NoArgsConstructor
@ToString
@Builder
@AllArgsConstructor
public class ShippingAddress extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long shippingId;
	
	@Column(nullable = false)
	private String addressLine1;
	
	@Column(nullable = false)
	private String addressLine2;
	
	@Column(nullable = false,length = 40)
	private String state;
	
	@Column(nullable = false, length = 40)
	private String country;
	
	@Column(nullable = false, length = 40)
	private String city;
	
	@Column(nullable = false,length = 10)
	private String mobileNumber;
	
	@Column(nullable = false,length = 6)
	private String zipcode;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
}
