package com.skemex.task1.model;

import java.io.Serializable;

public class PaymentId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3131511486477311439L;

	private Long quotationId;

	private Integer paymentNumber;

	public PaymentId() {

	}

	public PaymentId(Long quotationId, Integer paymentNumber) {
		super();
		this.quotationId = quotationId;
		this.paymentNumber = paymentNumber;
	}

	public Long getQuotationId() {
		return quotationId;
	}

	public void setQuotationId(Long quotationId) {
		this.quotationId = quotationId;
	}

	public Integer getPaymentNumber() {
		return paymentNumber;
	}

	public void setPaymentNumber(Integer paymentNumber) {
		this.paymentNumber = paymentNumber;
	}

}
