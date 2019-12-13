package com.practicalmicroservices.financialservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practicalmicroservices.financialservice.entity.BankAccountDetail;
import com.practicalmicroservices.financialservice.entity.ObligationDetails;
import com.practicalmicroservices.financialservice.service.FinancialServices;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.UUID;

@RestController
@RequestMapping(path = {"/PM/finance/"})
public class FinancialController {

	private static final Logger logger = LogManager.getLogger(FinancialController.class);
	@Resource
	FinancialServices financialService;
	@Resource
	ObjectMapper mapper;
	/**
	 * Method is responsible for adding new AccountDetails.
	 */
	public static final String ADD_ACCOUNT_DETAILS =
			"addAccountDetails(): ";

	@RequestMapping(method = RequestMethod.POST, value = "{userId}/account",
			produces = " application/json", consumes = "application/json")
	public ResponseEntity<String> addAccountDetails(@RequestBody BankAccountDetail accountDetail,
	                                                @PathVariable("userId") UUID userId) {

		logger.debug(ADD_ACCOUNT_DETAILS + " Account for user Id " +
				userId + " is creating.");
		accountDetail.setUserId(userId.toString());
		financialService.saveAccountDetail(accountDetail);
		return new ResponseEntity<>(ADD_ACCOUNT_DETAILS, HttpStatus.CREATED);
	}

	/**
	 * Method is responsible for creating a obligation Details.
	 */
	public static final String ADD_OBLIGATION_DETAILS = "addObligationDetails(): ";

	@RequestMapping(method = RequestMethod.POST, value = "{userId}/obligation",
			produces = " application/json", consumes = "application/json")
	public ResponseEntity<String> addObligationDetails(@RequestBody ObligationDetails obligationDetails,
	                                                   @PathVariable("userId") UUID userId) {

		logger.debug(ADD_OBLIGATION_DETAILS + " Creating user's obligation with Id" + userId + " and details :" + obligationDetails);
		obligationDetails.setUserId(userId.toString());

		financialService.saveObligation(obligationDetails);

		return new ResponseEntity<>(ADD_OBLIGATION_DETAILS, HttpStatus.CREATED);
	}

	/**
	 * Deleting Financial Detail of user
	 */
	public static final String DELETE_FINANCIAL_DETAILS = "deleteFinancialDetails(): ";

	@RequestMapping(method = RequestMethod.DELETE, value = "{userId}",
			produces = "application/json", consumes = "application/json")
	public ResponseEntity<String> deleteFinancialDetails(@PathVariable("userId") UUID userId) {
		logger.debug(DELETE_FINANCIAL_DETAILS + " deleting user with Id " + userId);

		financialService.deleteFinancialDetail(userId);

		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	/**
	 * Method is responsible for getting the account detail for given ID.
	 */
	public static final String GET_ACCOUNT_DETAILS = "getAccountDetails(): ";

	@RequestMapping(method = RequestMethod.GET, value = "{userId}/account",
			produces = " application/json", consumes = "application/json")
	public ResponseEntity<BankAccountDetail>
	getAccountDetails(@PathVariable("userId") UUID userId) {
		logger.debug(GET_ACCOUNT_DETAILS + " getting information for userId" + userId);
		BankAccountDetail objectToReturn = financialService.getAccountDetail(userId);
		if (objectToReturn == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<>(objectToReturn, HttpStatus.OK);
	}

	/**
	 * Method is responsible getting the Obligation Detail.
	 */
	public static final String GET_OBLIGATION_DETAILS = "getObligationDetails(): ";

	@RequestMapping(method = RequestMethod.GET, value = "{userId}/obligation",
			produces = " application/json", consumes = "application/json")
	public ResponseEntity<ObligationDetails> getObligationDetails(@PathVariable("userId") UUID userId) {

		logger.debug(GET_OBLIGATION_DETAILS + " getting Obligation Details for user Id:" + userId);
		ObligationDetails objectToReturn = financialService.getObligationDetail(userId);
		if (objectToReturn == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<>(objectToReturn, HttpStatus.OK);
	}
}
