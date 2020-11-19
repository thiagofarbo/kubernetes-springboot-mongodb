package br.com.thec.cartao.request;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
	
	@JsonIgnore
	@ApiModelProperty(value = "Id Cartão", notes = "Id do Status do cartão", required = true, example = "12", position = 1)
	private String id;
	
	@ApiModelProperty(value = "Nome do portador do cartão", notes = "Nome do portador do cartão", required = true, example = "João Pereira", position = 2)
	private String nome;
	
	private TipoCartao tipoCartao;
	
	@JsonIgnore
	@ApiModelProperty(value = "Numero do cartão", notes = "Numero do cartão", required = true, example = "1234567890123456", position = 3)
	private String numeroCartao;
	
}
