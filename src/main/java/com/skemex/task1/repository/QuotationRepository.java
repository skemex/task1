package com.skemex.task1.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skemex.task1.model.Quotation;

public interface QuotationRepository extends JpaRepository<Quotation, Long> {
	List<Quotation> findAll();
	Optional<Quotation> findById(Long id);
}
