package com.thec.card.request;

import com.thec.card.enums.ProductType;
import com.thec.card.enums.CardStatusEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class CardRequestUpdate implements Serializable {


	private static final long serialVersionUID = -6387200333570878289L;

	@JsonIgnore
	@ApiModelProperty(value = "Card ID", notes = "Card status ID", required = true, example = "12", position = 1)
	private String id;

	@ApiModelProperty(value = "Cardholder's Name", notes = "Cardholder's Name", required = true, example = "João Pereira", position = 2)
	private String name;

	@ApiModelProperty(value = "Amount on the card", notes = "Amount on the card", required = true, example = "200.00", position = 3)
	private BigDecimal amount;

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



}
