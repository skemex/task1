package com.skemex.task1.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "payment")
@IdClass(PaymentId.class)
public class Payment {

	public Payment() {

	}

	public Payment(Long quotationId, Integer paymentNumber, Double amount, Date paymentDate) {
		super();
		this.quotationId = quotationId;
		this.paymentNumber = paymentNumber;
		this.amount = amount;
		this.paymentDate = paymentDate;
	}

	@Id
	@Column(name = "quotation_id")
	private Long quotationId;

	@Id
	@Column(name = "payment_number")
	private Integer paymentNumber;

	@Column(name = "amount")
	private Double amount;

	@Column(name = "payment_date")
	private Date paymentDate;

	public long getQuotationId() {
		return quotationId;
	}

	public void setQuotationId(long quotationId) {
		this.quotationId = quotationId;
	}

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
		return "Payments [quotationId=" + quotationId + ", paymentNumber=" + paymentNumber + ", amount=" + amount
				+ ", paymentDate=" + paymentDate + "]";
	}

}
