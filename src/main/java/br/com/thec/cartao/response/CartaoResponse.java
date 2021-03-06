package br.com.thec.cartao.response;

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

import br.com.thec.cartao.enums.BandeiraEnum;
import br.com.thec.cartao.enums.StatusCartaoEnum;
import br.com.thec.cartao.enums.TipoCartao;
import br.com.thec.cartao.enums.TipoProduto;
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
public class CartaoResponse  implements Serializable{
	
		private static final long serialVersionUID = 758925024700917336L;
	
		@ApiModelProperty(value = "Id Cartão", notes = "Id do Status do cartão", required = true, example = "12", position = 1)
		private String id;
		
		@ApiModelProperty(value = "Nome do portador do cartao", notes = "Nome do portador do cartao", required = true, example = "João Pereira", position = 2)
		private String nome;
		
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
		
		@ApiModelProperty(value = "Limite catão", notes = "Valor de limite do catão", required = true, example = "1500.00", position = 8)
		private BigDecimal limiteCartao;
		
		@ApiModelProperty(value = "Score", notes = "Score usuario", required = true, example = "985", position = 9)
		private Integer score;
		
		@ApiModelProperty(value = "Salario", notes = "Salario do requisitante do cartao", required = true, example = "1500.00", position = 10)
		private BigDecimal salario;
		
		@ApiModelProperty(value = "Bandeira cartao", notes = "Bandeira do cartao", required = true, example = "VISA", position = 11)
		private BandeiraEnum bandeira;
		
		@ApiModelProperty(value = "Descricao tipo cartão", notes = "Descricao do tipo do cartão", required = true, example = "Crédito", position = 12)
		private TipoCartao tipoCartao;
		
		@ApiModelProperty(value = "Numero do cartão", notes = "Numero do cartão", required = true, example = "1234567890123456", position = 13)
		private String numeroCartao;

}
