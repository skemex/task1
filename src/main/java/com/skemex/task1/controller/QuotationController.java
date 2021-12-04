package com.skemex.task1.controller;

import java.util.List;
import java.util.Optional;

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

import com.skemex.task1.dto.PaymentDTO;
import com.skemex.task1.dto.QuotationDTO;
import com.skemex.task1.map.QuotationMapper;
import com.skemex.task1.model.Quotation;
import com.skemex.task1.service.QuotationService;

@RestController
public class QuotationController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private QuotationService quotationService;

	@PostMapping("/quotation")
	public ResponseEntity<?> createQuotation(@RequestBody QuotationDTO quotation) {
		try {
			Optional<String> opCheck = checkAmount(quotation.getAmount())
					.or(() -> checkTerms(quotation.getTerms()).or(() -> checkRate(quotation.getRate())));
			if (opCheck.isPresent()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(opCheck.get());
			}

			List<PaymentDTO> payments = quotationService.getPayments(quotation);

			if (payments.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(payments, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error: %s", e.getCause());
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

	@GetMapping("/quotation")
	public ResponseEntity<List<QuotationDTO>> getQuotations() {
		List<QuotationDTO> quotations = quotationService.getQuotations();
		if (quotations.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(quotations, HttpStatus.OK);
		}
	}

	@GetMapping("/quotation/{id}")
	public ResponseEntity<QuotationDTO> getQuotationById(@PathVariable("id") long id) {
		Optional<Quotation> opQuotation = quotationService.getQuotationById(id);
		if (opQuotation.isPresent()) {
			QuotationDTO quotationDto = QuotationMapper.toDto(opQuotation.get());
			return new ResponseEntity<>(quotationDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}