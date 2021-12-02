package com.skemex.task1.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Year;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.skemex.task1.model.Payment;
import com.skemex.task1.model.Quotation;
import com.skemex.task1.repository.PaymentRepository;
import com.skemex.task1.repository.QuotationRepository;

@RestController
public class QuotationController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private static final Double DAYS_OF_WEEK = 7D;

	@Autowired
	QuotationRepository quotationRepository;
	@Autowired
	PaymentRepository paymentRepository;

	@PostMapping("/quotation")
	public ResponseEntity<?> createQuotation(@RequestBody Quotation quotation) {
		try {
			Optional<String> opCheck = checkAmount(quotation.getAmount())
					.or(() -> checkTerms(quotation.getTerms()).or(() -> checkRate(quotation.getRate())));
			if (opCheck.isPresent()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(opCheck.get());
			}

			Quotation quota = quotationRepository.save(quotation);

			List<Payment> payments = getPayments(quota);

			if (payments.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} else {
				paymentRepository.saveAll(payments);
			}
			return new ResponseEntity<>(payments, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	private Optional<String> checkTerms(Integer terms) {
		if (terms < 4 || terms > 52) {
			return Optional.of("The terms (weeks) should be between 4 and 52");
		} else {
			return Optional.empty();
		}
	}

	private Optional<String> checkAmount(Double amount) {
		if (amount < 1 || amount > 999999.00) {
			return Optional.of("The amount should be between $1.00 and $999,999.00");
		} else {
			return Optional.empty();
		}
	}

	private Optional<String> checkRate(Double rate) {
		if (rate < 1 || rate > 100) {
			return Optional.of("The rate should be between 1.00 and 100.00");
		} else {
			return Optional.empty();
		}
	}

	@GetMapping("/quotation/{id}")
	public ResponseEntity<Quotation> getQuotationById(@PathVariable("id") long id) {
		Optional<Quotation> quotation = quotationRepository.findById(id);

		if (quotation.isPresent()) {
			return new ResponseEntity<>(quotation.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	private Double calculateInterest(Quotation quotation) {
		LocalDate localDate = LocalDate.now();
		int dayOfYear = Year.of(localDate.getYear()).length();
		Double days = DAYS_OF_WEEK * Double.valueOf(quotation.getTerms());
		Double timeOnYears = days / dayOfYear;
		return quotation.getAmount() * timeOnYears * (quotation.getRate() / 100);
	}

	private List<Payment> getPayments(Quotation quotation) {
		List<Payment> payments = new LinkedList<>();
		LocalDate localDate = LocalDate.now();
		Double interest = calculateInterest(quotation);
		IntStream.range(0, quotation.getTerms()).forEach(i -> payments.add(new Payment(quotation.getId(), i + 1,
				(quotation.getAmount() + interest) / quotation.getTerms(), Date.valueOf(localDate.plusWeeks(i)))));
		return payments;
	}
}