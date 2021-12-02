package com.skemex.task1.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skemex.task1.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
	List<Payment> findAll();
	Optional<Payment> findById(Long id);
}
