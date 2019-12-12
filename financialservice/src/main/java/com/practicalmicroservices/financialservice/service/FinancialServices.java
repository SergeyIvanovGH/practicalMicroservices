package com.practicalmicroservices.financialservice.service;

import com.practicalmicroservices.financialservice.entity.BankAccountDetail;
import com.practicalmicroservices.financialservice.entity.ObligationDetails;
import com.practicalmicroservices.financialservice.repo.BankAccountDetailRepository;
import com.practicalmicroservices.financialservice.repo.ObligationRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.UUID;

@Service
@Transactional
public class FinancialServices {

	@Resource
	BankAccountDetailRepository accountDetailRepo;
	@Resource
	ObligationRepository obligationRepo;

	public void saveAccountDetail(BankAccountDetail accountDetail) {
		accountDetailRepo.save(accountDetail);
		System.out.println("AccountDetails Saved!");
	}
	public void saveObligation(ObligationDetails obligationDetails) {
		obligationRepo.save(obligationDetails);
		System.out.println("Obligation Details Saved!");
	}

	public ObligationDetails getObligationDetail(UUID userId) {
		return obligationRepo.findByUserId(userId.toString());
	}
	public BankAccountDetail getAccountDetail(UUID userId) {
		return accountDetailRepo.findByUserId(userId.toString());
	}

	public void deleteFinancialDetail(UUID userId) {
		BankAccountDetail accountObject =
				accountDetailRepo.findByUserId(userId.toString());
		accountObject.setDeletedOn(new Date());
		accountDetailRepo.saveAndFlush(accountObject);
		ObligationDetails obligationObject =
				obligationRepo.findByUserId(userId.toString());
		obligationObject.setDeletedOn(new Date());
		obligationRepo.saveAndFlush(obligationObject);
	}
}
