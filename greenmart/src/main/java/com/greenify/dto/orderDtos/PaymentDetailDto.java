package com.greenify.dto.orderDtos;

import java.math.BigDecimal;


import com.greenify.enums.PaymentMethod;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@ToString
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class PaymentDetailDto {

	private BigDecimal amount;

    private PaymentMethod paymentMethod;
}
