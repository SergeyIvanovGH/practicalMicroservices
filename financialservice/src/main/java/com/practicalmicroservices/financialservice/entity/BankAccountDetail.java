package com.practicalmicroservices.financialservice.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "bank_account_details")
public class BankAccountDetail implements Serializable {

	private static final long serialVersionUID = 4804278804874617196L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	/**
	 * User Id , unique for every user
	 */
	@Column(name = "user_id", nullable = false, unique = true, length =
			36)
	private String userId;
	/**
	 * Bank Name of User
	 */
	@Column(name = "bank_name", nullable = false, length = 100)
	private String bankName;
	/**
	 * Account number of User
	 */
	@Column(name = "account_number", nullable = false, unique = true,
			length = 20)
	private String accountNumber;
	/**
	 * Account holder Name of account
	 */
	@Column(name = "account_holders_name", nullable = false, length =
			250)
	private String accountHolderName;
	/**
	 * Any National Financial system code of bank
	 */
	@Column(name = "fsc", nullable = false, length = 20)
	private String fsc;
	/**
	 * Account Type of user
	 */
	@Column(name = "account_type", nullable = false)
	private Integer accountType;
	/**
	 * Date on which Entry is created
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_on", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
	private Date createdOn = new Date();
	/**
	 * Date on which Entry is deleted.
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "deleted_on", columnDefinition = "DATETIME DEFAULT NULL")
	private Date deletedOn;

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

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountHolderName() {
		return accountHolderName;
	}

	public void setAccountHolderName(String accountHolderName) {
		this.accountHolderName = accountHolderName;
	}

	public String getFsc() {
		return fsc;
	}

	public void setFsc(String fsc) {
		this.fsc = fsc;
	}

	public Integer getAccountType() {
		return accountType;
	}

	public void setAccountType(Integer accountType) {
		this.accountType = accountType;
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
		return "BankAccountDetail [" +
				"id=" + id +
				", userId='" + userId + '\'' +
				", bankName='" + bankName + '\'' +
				", accountNumber='" + accountNumber + '\'' +
				", accountHolderName='" + accountHolderName + '\'' +
				", fsc='" + fsc + '\'' +
				", accountType=" + accountType +
				", createdOn=" + createdOn +
				", deletedOn=" + deletedOn +
				']';
	}
}
