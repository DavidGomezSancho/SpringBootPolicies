package com.home.policies.model.entities.builders;

import com.home.policies.model.entities.Taker;

public class TakerBuilder {
	
	private Long id;
	private String name;
	private String surname1;
	private String surname2;
	private String telephone;
	private String email;
	
	public TakerBuilder() {
		this.name = new String();
		this.surname1 = new String();
		this.surname2 = new String();
		this.telephone = new String();
		this.email = new String();
	}
	
	public TakerBuilder withId(Long id) {
		this.id=id;
		return this;
	}
	
	public TakerBuilder withName(String name) {
		this.name=name;
		return this;
	}
	
	public TakerBuilder withSurname1(String surname1) {
		this.surname1=surname1;
		return this;
	}
	
	public TakerBuilder WithSurname2(String surname2) {
		this.surname2=surname2;
		return this;
	}
	
	public TakerBuilder withTelephone(String telephone) {
		this.telephone=telephone;
		return this;
	}
	
	public TakerBuilder withEmail(String email) {
		this.email=email;
		return this;
	}
	
	public Taker build() {
		Taker taker = new Taker();
		taker.setEmail(email);
		taker.setId(id);
		taker.setName(name);
		taker.setSurname1(surname1);
		taker.setSurname2(surname2);
		taker.setTelephone(telephone);
		return taker;
	}
}
