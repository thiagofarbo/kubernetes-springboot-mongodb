package com.thec.card.enums;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;

@Getter
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public enum CardStatusEnum {

	ACTIVE("Active"),
	INACTIVE("Inactive"),
	BLOCKED("Blocked"),
	LOSS("Loss");

	private String descricao;
	
	CardStatusEnum(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
