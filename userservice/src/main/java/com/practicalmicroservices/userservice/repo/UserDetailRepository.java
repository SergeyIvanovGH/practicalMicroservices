package com.practicalmicroservices.userservice.repo;

import com.practicalmicroservices.userservice.entity.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailRepository extends JpaRepository<UserDetail, Integer> {
	UserDetail findByUserId(String userId);
}
