package com.practicalmicroservices.financialservice.repo;

import com.practicalmicroservices.financialservice.entity.ObligationDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ObligationRepository extends JpaRepository<ObligationDetails, Integer> {
	ObligationDetails findByUserId(String userId);
}
