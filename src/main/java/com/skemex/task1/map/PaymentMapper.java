package com.skemex.task1.map;

import com.skemex.task1.dto.PaymentDTO;
import com.skemex.task1.model.Payment;

public class PaymentMapper {

	public static PaymentDTO toDto(final Payment payment) {
		return new PaymentDTO(payment.getPaymentNumber(), payment.getAmount(), payment.getPaymentDate());
	}

	public static Payment toModel(final PaymentDTO paymentDto) {
		return new Payment(null, paymentDto.getPaymentNumber(), paymentDto.getAmount(), paymentDto.getPaymentDate());
	}

}
