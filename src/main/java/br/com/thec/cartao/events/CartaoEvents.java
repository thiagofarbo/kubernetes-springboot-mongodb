package br.com.thec.cartao.events;

import java.util.Date;

import br.com.thec.cartao.domain.Cartao;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CartaoEvents {

	private Cartao model;
	private Date when;	
}
