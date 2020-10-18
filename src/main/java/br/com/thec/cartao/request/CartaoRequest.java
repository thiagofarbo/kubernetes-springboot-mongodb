package br.com.thec.cartao.request;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import br.com.thec.cartao.enums.StatusCartaoEnum;
import br.com.thec.cartao.enums.TipoProduto;
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
@ApiModel(value = "CartaoRequest", description = "Request para atualização do cartão")
public class CartaoRequest implements Serializable{

	private static final long serialVersionUID = 4641788372072003805L;

	@ApiModelProperty(value = "Nome do portador do cartao", notes = "Nome do portador do cartao", required = true, example = "João Pereira", position = 2)
	private String nome;
	
	@ApiModelProperty(value = "Valor que está no cartão", notes = "Valor que está no cartão", required = true, example = "200.00", position = 3)
	private BigDecimal valor;
	
	@ApiModelProperty(value = "tipo do produto", notes = "tipo do produto", required = true, example = "REFEICAO", position = 4)
	private TipoProduto tipoProduto;
	
	@ApiModelProperty(value = "Recarga para o cartão", notes = "Data de recarga para o catão", required = true, example = "30/09/2019", position = 5)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDate dataRecarga;
	
	@ApiModelProperty(value = "Data expiração do catão", notes = "Data de expiração para o catão", required = true, example = "30/09/2020", position = 6)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDate dataExpiracao;
	
	@ApiModelProperty(value = "Status do catão", notes = "Status do catão", required = true, example = "ATIVO", position = 7)
	private StatusCartaoEnum status;
	
}
