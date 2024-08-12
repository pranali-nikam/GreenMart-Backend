package com.greenify.dto.categoryDtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class CategoryNameDto {
	
	private Long CategoryId;
	private String categoryName;
}
