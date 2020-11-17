package br.com.thec.cartao.request;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.thec.cartao.enums.TipoCartao;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@ApiModel(value = "CartaoRequest", description = "Request para criacão do cartão")
public class CartaoRequest implements Serializable {

	private static final long serialVersionUID = 4641788372072003805L;

	@ApiModelProperty(value = "Nome do portador do cartão", notes = "Nome do portador do cartão", required = true, example = "João Pereira", position = 2)
	private String nome;
	
	private TipoCartao tipoCartao;
	
}
