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
	public static final String addAccountDetails =
			"addAccountDetails(): ";

	@RequestMapping(method = RequestMethod.POST, value = "{userId}/account",
			produces = " application/json", consumes = "application/json")
	public ResponseEntity<String> addAccountDetails(@RequestBody BankAccountDetail accountDetail,
	                                                @PathVariable("userId") UUID userId) {

		logger.debug(addAccountDetails + " Account for user Id " +
				userId + " is creating.");
		accountDetail.setUserId(userId.toString());
		financialService.saveAccountDetail(accountDetail);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	/**
	 * Method is responsible for creating a obligation Details.
	 */
	public static final String addObligationDetails =
			"addObligationDetails(): ";

	@RequestMapping(method = RequestMethod.POST, value = "{userId}/obligation",
			produces = " application/json", consumes = "application/json")
	public ResponseEntity<String> addObligationDetails(@RequestBody ObligationDetails obligationDetails,
	                                                   @PathVariable("userId") UUID userId) {

		logger.debug(addObligationDetails + " Creating user's obligation with Id" + userId + " and details :" + obligationDetails);
		obligationDetails.setUserId(userId.toString());

		financialService.saveObligation(obligationDetails);

		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	/**
	 * Deleting Financial Detail of user
	 */
	public static final String deleteFinancialDetails =
			"deleteFinancialDetails(): ";

	@RequestMapping(method = RequestMethod.DELETE, value = "{userId}",
			produces = "application/json", consumes = "application/json")
	public ResponseEntity<String> deleteFinancialDetails(@PathVariable("userId") UUID userId) {
		logger.debug(deleteFinancialDetails + " deleting user with Id " + userId);

		financialService.deleteFinancialDetail(userId);

		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	/**
	 * Method is responsible for getting the account detail for given ID.
	 */
	public static final String getAccountDetails =
			"getAccountDetails(): ";

	@RequestMapping(method = RequestMethod.GET, value = "{userId}/account",
			produces = " application/json", consumes = "application/json")
	public ResponseEntity<BankAccountDetail>
	getAccountDetails(@PathVariable("userId") UUID userId) {
		logger.debug(getAccountDetails + " getting information for userId" + userId);
		BankAccountDetail objectToReturn = financialService.getAccountDetail(userId);
		if (objectToReturn == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<>(objectToReturn, HttpStatus.OK);
	}

	/**
	 * Method is responsible getting the Obligation Detail.
	 */
	public static final String getObligationDetails =
			"getObligationDetails(): ";

	@RequestMapping(method = RequestMethod.GET, value = "{userId}/obligation",
			produces = " application/json", consumes = "application/json")
	public ResponseEntity<ObligationDetails>
	getObligationDetails(@PathVariable("userId") UUID userId) {

		logger.debug(getObligationDetails + " getting Obligation Details for user Id:" + userId);
		ObligationDetails objectToReturn = financialService.getObligationDetail(userId);
		if (objectToReturn == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<>(objectToReturn, HttpStatus.OK);
	}
}
