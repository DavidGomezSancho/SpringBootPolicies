package com.home.policies.dto;

import io.swagger.annotations.ApiModelProperty;

public class TakerDto {
	
	@ApiModelProperty(required = false)
	private Long id;
	private String name;
	private String surname1;
	private String surname2;
	private String telephone;
	@ApiModelProperty(value = "email", example = "example@domainexample.com")
	private String email;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname1() {
		return surname1;
	}
	public void setSurname1(String surname1) {
		this.surname1 = surname1;
	}
	public String getSurname2() {
		return surname2;
	}
	public void setSurname2(String surname2) {
		this.surname2 = surname2;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
