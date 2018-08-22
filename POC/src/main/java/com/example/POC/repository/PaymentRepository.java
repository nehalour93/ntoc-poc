package com.example.POC.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.POC.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
	
	Payment findByPaymentId(int id);

	List<Payment> findAll();
	
	List<Payment> findAllBypaymentId(List<Payment> list);

	Payment save(Payment persisted);

	Payment getByPaymentId(int id);
	
	
	

}
