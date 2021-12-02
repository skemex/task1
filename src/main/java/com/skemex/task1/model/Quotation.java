package com.skemex.task1.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "quotation")
public class Quotation {

	public Quotation() {

	}

	public Quotation(Double amount, Integer terms, Double rate) {
		super();
		this.amount = amount;
		this.terms = terms;
		this.rate = rate;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "amount")
	private Double amount;

	@Column(name = "terms")
	private Integer terms;

	@Column(name = "rate")
	private Double rate;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Integer getTerms() {
		return terms;
	}

	public void setTerms(Integer terms) {
		this.terms = terms;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	@Override
	public String toString() {
		return "SimpleInterestRequest [id=" + id + ", amount=" + amount + ", terms=" + terms + ", rate=" + rate + "]";
	}

}
