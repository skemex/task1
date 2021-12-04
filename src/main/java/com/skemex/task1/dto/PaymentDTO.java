package com.skemex.task1.dto;

import java.util.Date;

public class PaymentDTO {

	public PaymentDTO() {

	}

	public PaymentDTO(Integer paymentNumber, Double amount, Date paymentDate) {
		super();
		this.paymentNumber = paymentNumber;
		this.amount = amount;
		this.paymentDate = paymentDate;
	}

	private Integer paymentNumber;
	private Double amount;
	private Date paymentDate;

	public Integer getPaymentNumber() {
		return paymentNumber;
	}

	public void setPaymentNumber(Integer paymentNumber) {
		this.paymentNumber = paymentNumber;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	@Override
	public String toString() {
		return "Payment [paymentNumber=" + paymentNumber + ", amount=" + amount + ", paymentDate=" + paymentDate + "]";
	}

}
