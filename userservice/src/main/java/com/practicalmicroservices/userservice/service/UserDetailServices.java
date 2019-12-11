package com.practicalmicroservices.userservice.service;

import com.practicalmicroservices.userservice.entity.Address;
import com.practicalmicroservices.userservice.entity.UserDetail;
import com.practicalmicroservices.userservice.repo.AddressRepository;
import com.practicalmicroservices.userservice.repo.UserDetailRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.UUID;

@Service
@Transactional
public class UserDetailServices {

	@Resource
	AddressRepository addressRepo;

	@Resource
	UserDetailRepository userRepo;

	public void saveAddress(Address address) {
		addressRepo.save(address);
		System.out.println("User Saved!");
	}

	public void saveUser(UserDetail userDetail) {
		userRepo.save(userDetail);
		System.out.println("User Saved!");
	}

	public Address getAddress(UUID userId) {

		return addressRepo.findByUserId(userId.toString());
	}

	public UserDetail getUser(UUID userId) {
		UserDetail userObjectToRetrun = userRepo.findByUserId(userId.toString());
		System.out.println("User Saved!");

		return userObjectToRetrun;
	}

	public void deleteUser(UUID userId) {
		Address addressObject = addressRepo.findByUserId(userId.toString());
		addressObject.setDeletedOn(new Date());
		addressRepo.saveAndFlush(addressObject);

		UserDetail userObject = userRepo.findByUserId(userId.toString());
		userObject.setDeletedOn(new Date());
		userRepo.saveAndFlush(userObject);

		System.out.println("User Deleted!");
	}
}
