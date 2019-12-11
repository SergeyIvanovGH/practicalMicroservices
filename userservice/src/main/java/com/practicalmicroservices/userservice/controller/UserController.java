package com.practicalmicroservices.userservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practicalmicroservices.userservice.entity.Address;
import com.practicalmicroservices.userservice.entity.UserDetail;
import com.practicalmicroservices.userservice.service.UserDetailServices;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.UUID;

@RestController
@RequestMapping(path = "/PM/user/")
public class UserController {

	private static final Logger LOGGER = LogManager.getLogger(UserController.class);
	@Resource
	UserDetailServices userService;
	@Resource
	ObjectMapper mapper;

	public static final String CREATE_USER = "createUser(): ";
	public static final String DELETE_USER = "deleteUser(): ";
	public static final String CREATE_USER_ADDRESS = "createUserAddress(): ";
	public static final String GET_USER = "getUser(): ";
	public static final String GET_ADDRESS = "getAddress(): ";

	@RequestMapping(method = RequestMethod.POST, value = "{userId}",
			produces = "application/json", consumes = "application/json")
	public ResponseEntity<String> createUser(@RequestBody UserDetail userDetail,
	                                         @PathVariable("userId") UUID userId) {

		LOGGER.debug(CREATE_USER + " creating user with Id " + userId + " and details : " + userDetail);
		userDetail.setUserId(userId.toString());
		userService.saveUser(userDetail);

		return new ResponseEntity<>("User created", HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.POST, value = "{userId}/address",
			produces = "application/json", consumes = "application/json")
	public ResponseEntity<String> createUserAddress(@RequestBody Address address,
	                                                @PathVariable("userId") UUID userId) {

		LOGGER.debug(CREATE_USER_ADDRESS + " Address for user Id " + userId + " is updated as " + address);
		address.setUserId(userId.toString());
		userService.saveAddress(address);
		return new ResponseEntity<>("User address created", HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "{userId}",
			produces = "application/json", consumes = "application/json")
	public ResponseEntity<String> deleteUser(@RequestBody UserDetail userDetail,
	                                         @PathVariable("userId") UUID userId) {

		LOGGER.debug(DELETE_USER + " deleting user with Id " + userId);
		userService.deleteUser(userId);
		return new ResponseEntity<>(DELETE_USER, HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.GET, value = "{userId}",
			produces = "application/json", consumes = "application/json")
	public ResponseEntity<UserDetail> getUser(@PathVariable("userId") UUID userId) {

		LOGGER.debug(GET_USER + " getting information for userId " + userId);
		UserDetail objectToReturn = userService.getUser(userId);
		if (objectToReturn == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<>(objectToReturn, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "{userId}/address",
			produces = "application/json", consumes = "application/json")
	public ResponseEntity<Address> getAddress(@PathVariable("userId") UUID userId) {

		LOGGER.debug(GET_ADDRESS + " getting address for user Id: " + userId);
		Address objectToReturn = userService.getAddress(userId);
		if (objectToReturn == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<>(objectToReturn, HttpStatus.OK);
	}
}
