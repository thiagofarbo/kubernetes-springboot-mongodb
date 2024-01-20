package com.thec.card.response;

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

import com.thec.card.enums.BrandEnum;
import com.thec.card.enums.CardStatusEnum;
import com.thec.card.enums.CardType;
import com.thec.card.enums.ProductType;
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
public class CardResponse implements Serializable{
	
		private static final long serialVersionUID = 758925024700917336L;

	@ApiModelProperty(value = "Card ID", notes = "Card status ID", required = true, example = "12", position = 1)
	private String id;

	@ApiModelProperty(value = "Cardholder's Name", notes = "Cardholder's Name", required = true, example = "João Pereira", position = 2)
	private String name;

	@ApiModelProperty(value = "Amount on the card", notes = "Amount on the card", required = true, example = "200.00", position = 3)
	private BigDecimal amonut;

	@ApiModelProperty(value = "Product type", notes = "Product type", required = true, example = "Meal", position = 4)
	private ProductType productType;

	@ApiModelProperty(value = "Card recharge date", notes = "Date of recharge for the card", required = true, example = "30/09/2019", position = 5)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDate rechargeDate;

	@ApiModelProperty(value = "Card expiration date", notes = "Date of expiration for the card", required = true, example = "30/09/2020", position = 6)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDate expirationDate;

	@ApiModelProperty(value = "Card status", notes = "Card status", required = true, example = "ACTIVE", position = 7)
	private CardStatusEnum status;

	@ApiModelProperty(value = "Card Limit", notes = "Card limit value", required = true, example = "1500.00", position = 8)
	private BigDecimal cardLimit;

	@ApiModelProperty(value = "Score", notes = "User score", required = true, example = "985", position = 9)
	private Integer score;

	@ApiModelProperty(value = "Salary", notes = "Applicant's salary for the card", required = true, example = "1500.00", position = 10)
	private BigDecimal salary;

	@ApiModelProperty(value = "Card Brand", notes = "Card brand", required = true, example = "VISA", position = 11)
	private BrandEnum cardBrand;

	@ApiModelProperty(value = "Card Type Description", notes = "Description of the card type", required = true, example = "Credit", position = 12)
	private CardType cardType;

	@ApiModelProperty(value = "Card Number", notes = "Card number", required = true, example = "1234567890123456", position = 13)
	private String cardNumber;

}
