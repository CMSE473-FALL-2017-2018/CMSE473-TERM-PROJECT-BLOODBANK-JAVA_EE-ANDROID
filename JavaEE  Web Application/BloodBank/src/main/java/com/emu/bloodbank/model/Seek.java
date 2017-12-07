package com.emu.bloodbank.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="Seek")
public class Seek {
	
	@Id
	@GeneratedValue
	private int seekID;
	
	@NotNull @Size(min=2, max=50)
	@Column(name = "seekerName")
	private String seekerName;
	
	@NotEmpty
	private String seekerEmail;
	
	@NotBlank
	private String bloodType;
	
	@NotEmpty
	private String city;	
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private Date seekDate;

	public int getSeekID() {
		return seekID;
	}
	public void setSeekID(int seekID) {
		this.seekID = seekID;
	}
	public String getSeekerEmail() {
		return seekerEmail;
	}
	public void setSeekerEmail(String seekerEmail) {
		this.seekerEmail = seekerEmail;
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
	public String getSeekerName() {
		return seekerName;
	}
	public void setSeekerName(String seekerName) {
		this.seekerName = seekerName;
	}
	public Date getSeekDate() {
		return seekDate;
	}
	public void setSeekDate(Date seekDate) {
		this.seekDate = seekDate;
	}

}
