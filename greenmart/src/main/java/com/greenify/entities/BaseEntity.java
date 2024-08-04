package com.greenify.entities;

import java.time.LocalDate;

import javax.persistence.MappedSuperclass;


@MappedSuperclass
public class BaseEntity {

	private LocalDate createdAt;
	private LocalDate updatedAt;
}

