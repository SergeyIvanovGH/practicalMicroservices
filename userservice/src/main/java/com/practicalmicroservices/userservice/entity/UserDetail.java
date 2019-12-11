package com.practicalmicroservices.userservice.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "user_details")
public class UserDetail implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 4804278804874617196L;
	/**
	 * unique Id for each record.
	 */
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id")
	private int id;
	/**
	 * User Id , unique for every user
	 */
	@Column(name = "user_id", nullable = false, unique = true, length = 36)
	private String userId;
	/**
	 * First Name of User
	 */
	@Column(name = "first_name", nullable = false, unique = true, length = 250)
	private String firstName;
	/**
	 * Last Name of User
	 */
	@Column(name = "last_name", nullable = false, unique = true, length = 250)
	private String lastName;
	/**
	 * Middle Name of user
	 */
	@Column(name = "middle_name", nullable = false, unique = true, length = 250)
	private String middleName;
	/**
	 * Legal id Of user( could be social security number etc)
	 */
	@Column(name = "legal_id", nullable = false, unique = true, length = 20)
	private String legalId;
	/**
	 * Gender of user
	 */
	@Column(name = "gender", nullable = false, unique = true, length = 20)
	private String gender;
	/**
	 * Date of birth of user
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_of_birth", columnDefinition = "TIMESTAMP", length = 0)
	private Date dateOfBirth;
	/**
	 * Date on which user is created
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_on", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", length = 0)
	private Date createdOn;
	/**
	 * Date on which user is deleted.
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "deleted_on", columnDefinition = "TIMESTAMP DEFAULT NULL", length = 0)
	private Date deletedOn;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLegalId() {
		return legalId;
	}

	public void setLegalId(String legalId) {
		this.legalId = legalId;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getDeletedOn() {
		return deletedOn;
	}

	public void setDeletedOn(Date deletedOn) {
		this.deletedOn = deletedOn;
	}

	@Override
	public String toString() {
		return "UserDetail [" +
				"id=" + id +
				", userId='" + userId + '\'' +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", middleName='" + middleName + '\'' +
				", legalId='" + legalId + '\'' +
				", gender='" + gender + '\'' +
				", dateOfBirth=" + dateOfBirth +
				", createdOn=" + createdOn +
				", deletedOn=" + deletedOn +
				']';
	}
}
