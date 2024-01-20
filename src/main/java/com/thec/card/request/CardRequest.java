package com.thec.card.request;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import com.thec.card.enums.CardType;
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
@ApiModel(value = "CardRequest", description = "Card Creation Request")
public class CardRequest implements Serializable {

	private static final long serialVersionUID = 4641788372072003805L;

	@JsonIgnore
	@ApiModelProperty(value = "Card ID", notes = "Card status ID", required = true, example = "12", position = 1)
	private String id;

	@ApiModelProperty(value = "Cardholder's Name", notes = "Cardholder's Name", required = true, example = "João Pereira", position = 2)
	private String name;

	private CardType cardType;

	@JsonIgnore
	@ApiModelProperty(value = "Card Number", notes = "Card number", required = true, example = "1234567890123456", position = 3)
	private String cardNumber;


}
