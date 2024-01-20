package com.thec.card.enums;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;

@Getter
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public enum BrandEnum {
	
	VISA("Visa"),
	MASTERCARD("Master Card");
	
	private String description;
	
	BrandEnum(String descricao) {
		this.description = descricao;
	}
	
	public String getDescription() {
		return description;
	}

}
