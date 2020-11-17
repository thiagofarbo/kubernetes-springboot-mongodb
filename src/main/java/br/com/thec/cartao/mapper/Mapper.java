package br.com.thec.cartao.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import br.com.thec.cartao.domain.Cartao;
import br.com.thec.cartao.request.CartaoRequest;
import br.com.thec.cartao.request.CartaoRequestUpdate;
import br.com.thec.cartao.response.CartaoResponse;

@Component
public class Mapper {
	
	public CartaoResponse mapToCartaoResponse(final CartaoRequest cartao) {
		
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(cartao, CartaoResponse.class);
	}
	
	public Cartao mapToCartao(final CartaoRequest cartao) {
		
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(cartao, Cartao.class);
	}
	
	public Cartao mapToCartao(final CartaoResponse cartaoResponse) {
		
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(cartaoResponse, Cartao.class);
	}
	
	
	public CartaoResponse mapToModelResponse(final Cartao cartao) {
		
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(cartao, CartaoResponse.class);
	}
	
	
	public Cartao mapToModelUpdate(final CartaoRequestUpdate cartao) {
		
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(cartao, Cartao.class);
	}
	
//	public static <T> Page<T> toPageable(final List<T> list) {
//		
//		final Page<T> pageble = new PageImpl<>(list);	
//		
//		return pageble;
//	}

}
