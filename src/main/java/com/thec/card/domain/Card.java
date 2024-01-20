package com.thec.card.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import com.thec.card.enums.CardStatusEnum;
import com.thec.card.enums.CardType;
import com.thec.card.enums.ProductType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "card")
public class Card implements Serializable {

	private static final long serialVersionUID = 7682266561520420887L;
	
	@Id
	private String id;

	private String name;

	private BigDecimal amount;

	private ProductType productType;
    
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDate rechargeDate;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDate expirationDate;

	private CardStatusEnum status;
	
	private BigDecimal cardLimit;
	
	private CardType cardType;

	private String cardNumber;
}
