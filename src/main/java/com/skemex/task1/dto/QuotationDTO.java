package com.skemex.task1.dto;

public class QuotationDTO {

	public QuotationDTO() {

	}

	public QuotationDTO(Double amount, Integer terms, Double rate) {
		super();
		this.amount = amount;
		this.terms = terms;
		this.rate = rate;
	}

	public QuotationDTO(Long id, Double amount, Integer terms, Double rate) {
		super();
		this.id = id;
		this.amount = amount;
		this.terms = terms;
		this.rate = rate;
	}

	private Long id;
	private Double amount;
	private Integer terms;
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
		return "Quotation [id=" + id + ", amount=" + amount + ", terms=" + terms + ", rate=" + rate + "]";
	}

}
