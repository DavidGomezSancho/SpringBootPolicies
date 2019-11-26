package com.home.policies.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import io.swagger.annotations.ApiModelProperty;

public class PolicyDto {
	
	@ApiModelProperty(required = false, hidden = true)
	private Long id;
	private String product;
	private Long premium;
	
	@ApiModelProperty(value = "signedDate", example = "29-02-1999")
	@JsonFormat(pattern="dd-MM-yyyy")
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate signedDate;
	
	@ApiModelProperty(value = "finishContractDate", example = "20-11-2000")
	@JsonFormat(pattern="dd-MM-yyyy")
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate finishContractDate;
	private TakerDto taker;
	private VehicleDto vehicle;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public Long getPremium() {
		return premium;
	}
	public void setPremium(Long premium) {
		this.premium = premium;
	}
	public LocalDate getSignedDate() {
		return signedDate;
	}
	public void setSignedDate(LocalDate signedDate) {
		this.signedDate = signedDate;
	}
	public LocalDate getFinishContractDate() {
		return finishContractDate;
	}
	public void setFinishContractDate(LocalDate finishContractDate) {
		this.finishContractDate = finishContractDate;
	}
	public TakerDto getTaker() {
		return taker;
	}
	public void setTaker(TakerDto taker) {
		this.taker = taker;
	}
	public VehicleDto getVehicle() {
		return vehicle;
	}
	public void setVehicle(VehicleDto vehicle) {
		this.vehicle = vehicle;
	}
	@Override
	public String toString() {
		return "PolicyDto [id=" + id + ", product=" + product + ", premium=" + premium + ", signedDate=" + signedDate
				+ ", finishContractDate=" + finishContractDate + ", taker=" + taker + ", vehicle=" + vehicle + "]";
	}
}
