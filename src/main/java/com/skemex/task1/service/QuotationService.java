package com.skemex.task1.service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Year;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skemex.task1.dto.PaymentDTO;
import com.skemex.task1.dto.QuotationDTO;
import com.skemex.task1.map.PaymentMapper;
import com.skemex.task1.map.QuotationMapper;
import com.skemex.task1.model.Payment;
import com.skemex.task1.model.Quotation;
import com.skemex.task1.repository.PaymentRepository;
import com.skemex.task1.repository.QuotationRepository;

@Service
public class QuotationService {

	private static final Double DAYS_OF_WEEK = 7D;

	@Autowired
	private QuotationRepository quotationRepository;
	@Autowired
	private PaymentRepository paymentRepository;

	public List<PaymentDTO> getPayments(QuotationDTO quotationDto) {
		Quotation quotation = QuotationMapper.toModel(quotationDto);
		quotation = quotationRepository.save(quotation);
		return getPayments(quotation);
	}

	public List<QuotationDTO> getQuotations() {
		return quotationRepository.findAll().stream().map(QuotationMapper::toDto).collect(Collectors.toList());
	}

	public Optional<Quotation> getQuotationById(long id) {
		return quotationRepository.findById(id);
	}

	private Double calculateInterest(Quotation quotation) {
		LocalDate localDate = LocalDate.now();
		int dayOfYear = Year.of(localDate.getYear()).length();
		Double days = DAYS_OF_WEEK * Double.valueOf(quotation.getTerms());
		Double timeOnYears = days / dayOfYear;
		return quotation.getAmount() * timeOnYears * (quotation.getRate() / 100);
	}

	private List<PaymentDTO> getPayments(Quotation quotation) {
		List<Payment> payments = new LinkedList<>();
		LocalDate localDate = LocalDate.now();
		Double interest = calculateInterest(quotation);
		IntStream.range(0, quotation.getTerms()).forEach(i -> payments.add(new Payment(quotation.getId(), i + 1,
				(quotation.getAmount() + interest) / quotation.getTerms(), Date.valueOf(localDate.plusWeeks(i)))));

		paymentRepository.saveAll(payments);

		return payments.stream().map(PaymentMapper::toDto).collect(Collectors.toList());
	}
}