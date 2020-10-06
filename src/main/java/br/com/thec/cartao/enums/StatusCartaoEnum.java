package br.com.thec.cartao.enums;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;

@Getter
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public enum StatusCartaoEnum {
	
	ATIVO("Ativo"),
	INATIVO("Inativo"),
	BLOQUEADO("Bloqueado"),
	PERDA("Perda");
	
	private String descricao;
	
	StatusCartaoEnum(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
