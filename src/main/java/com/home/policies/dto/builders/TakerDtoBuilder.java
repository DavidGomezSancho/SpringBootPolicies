package com.home.policies.dto.builders;

import com.home.policies.dto.TakerDto;

public class TakerDtoBuilder {
	
	private Long id;
	private String name;
	private String surname1;
	private String surname2;
	private String telephone;
	private String email;
	
	public TakerDtoBuilder() {
		this.name = new String();
		this.surname1 = new String();
		this.surname2 = new String();
		this.telephone = new String();
		this.email = new String();
	}
	
	public TakerDtoBuilder withId(Long id) {
		this.id=id;
		return this;
	}
	
	public TakerDtoBuilder withName(String name) {
		this.name=name;
		return this;
	}
	
	public TakerDtoBuilder withSurname1(String surname1) {
		this.surname1=surname1;
		return this;
	}
	
	public TakerDtoBuilder WithSurname2(String surname2) {
		this.surname2=surname2;
		return this;
	}
	
	public TakerDtoBuilder withTelephone(String telephone) {
		this.telephone=telephone;
		return this;
	}
	
	public TakerDtoBuilder withEmail(String email) {
		this.email=email;
		return this;
	}
	
	public TakerDto build() {
		TakerDto takerDto = new TakerDto();
		takerDto.setEmail(email);
		takerDto.setName(name);
		takerDto.setSurname1(surname1);
		takerDto.setSurname2(surname2);
		takerDto.setTelephone(telephone);
		takerDto.setId(id);
		return takerDto;
	}
}
