package com.greenify.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.greenify.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@Table(name = "users")
public class User extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	
	@Column(nullable = false)
	private String firstName;
	
	@Column(nullable = false)
	private String lastName;
	
	@Column(nullable = false,unique = true)
	private String email;
	
	@Column(nullable = false)
	private String password;
	
	@Column(nullable = false)
	private LocalDate dob;
	
	@Column(length = 10,nullable = false)
	private String mobileNumber;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Role role;
	
	@OneToOne(mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval = true)
	private Seller seller;
	
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval = true)
	private List<Order> orders;
	
	@Column(nullable = false)
	private Boolean isBlocked = false;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ShippingAddress> shippingAddress;
}
