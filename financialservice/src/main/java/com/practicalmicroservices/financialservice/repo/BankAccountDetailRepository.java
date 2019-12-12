package com.practicalmicroservices.financialservice.repo;

import com.practicalmicroservices.financialservice.entity.BankAccountDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountDetailRepository extends JpaRepository<BankAccountDetail, Integer> {
	BankAccountDetail findByUserId(String userId);
}
