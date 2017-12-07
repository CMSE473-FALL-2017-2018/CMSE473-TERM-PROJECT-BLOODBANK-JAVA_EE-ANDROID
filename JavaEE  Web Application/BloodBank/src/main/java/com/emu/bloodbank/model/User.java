package com.emu.bloodbank.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="User")
public class User{
	
	@Id
	@NotNull @Size(min=2, max=50)
	@Column(name = "userEmail")
	private String userEmail;
	
	@NotEmpty
	private String userPassword;
	
	@NotEmpty
	private String userName;
	
	@NotNull
	private int birthYear;
	
	@NotEmpty
	private String sex;
	
	@NotEmpty
	private String bloodType;
	
	@NotEmpty
	private String city;
	
	@NotEmpty
	private String donor;

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(int birthYear) {
		this.birthYear = birthYear;
	}

	public String getBloodType() {
		return bloodType;
	}

	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getDonor() {
		return donor;
	}

	public void setDonor(String donor) {
		this.donor = donor;
	}
	
	@Override
	public String toString() {
		String userInfo = "e-mail: " + this.userEmail + "\npassword: " + this.userPassword + "\nname: " + this.userName
				+ "\nbirth year: " + this.birthYear + "\nblood type: " + this.bloodType + 
				"\ncity: " + this.city + "\nsex: " + this.sex + "\ndonor: " + this.donor;
		
		return userInfo;
	}

	
}
