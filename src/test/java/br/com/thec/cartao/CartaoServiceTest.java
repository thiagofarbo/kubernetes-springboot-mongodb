package br.com.thec.cartao;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import br.com.thec.cartao.domain.Cartao;
import br.com.thec.cartao.domain.exception.BadRequestCustom;
import br.com.thec.cartao.domain.exception.NoContentCustom;
import br.com.thec.cartao.domain.exception.NotFoundCustom;
import br.com.thec.cartao.enums.BandeiraEnum;
import br.com.thec.cartao.enums.StatusCartaoEnum;
import br.com.thec.cartao.enums.TipoCartao;
import br.com.thec.cartao.enums.TipoProduto;
import br.com.thec.cartao.mapper.Mapper;
import br.com.thec.cartao.repository.CartaoRepository;
import br.com.thec.cartao.request.CartaoRequest;
import br.com.thec.cartao.request.CartaoRequestUpdate;
import br.com.thec.cartao.response.CartaoResponse;
import br.com.thec.cartao.service.CartaoService;

@RunWith(MockitoJUnitRunner.class)
public class CartaoServiceTest {
	
	private final Integer PAGE = 1;

	private final Integer SIZE = 50;

	@Mock
	private Mapper mapper;

	@Mock
	private CartaoRepository cartaoRepository;

	@InjectMocks
	private CartaoService cartaoService;

//	@Test
//	public void salvarCartaoTest() {
//		
//			CartaoRequest cartaoRequest = this.builderCartaoRequest();
//			
//			when(cartaoRepository.save(any(Cartao.class))).thenReturn(builderCartao());
//			
//			when(mapper.mapToCartao(any(CartaoResponse.class))).thenReturn(builderCartao());
//			
//			when(mapper.mapToModelResponse(any(Cartao.class))).thenReturn(builderCartaoResponse());
//			
//			CartaoResponse cartao = cartaoService.criarCartao(cartaoRequest);
//			
//			assertNotNull(cartao);
//	}
	
	@Test
	public void consultarCartao() {
		
		when(cartaoRepository.findById(any())).thenReturn(Optional.of(builderCartao()));
		
		when(mapper.mapToModelResponse(any(Cartao.class))).thenReturn(builderCartaoResponse());
		
		CartaoResponse cartao = cartaoService.consultarCartao("1");

		assertNotNull(cartao);
	}
	
	@Test(expected = NotFoundCustom.class)
	public void consultarCartaoComIdCartaoInexistente() {
		
		CartaoResponse cartao = cartaoService.consultarCartao(null);

		assertNotNull(cartao);
	}
	
	@Test
	public void listarCartaoTest() {
		
		Page<Cartao> pageCartoes = new PageImpl<>(Arrays.asList(builderCartao()));
		
		when(cartaoRepository.findAll(PageRequest.of(PAGE, SIZE))).thenReturn(pageCartoes);
		
		Page<Cartao> cartoes = cartaoService.listarCartoes(PAGE,SIZE);
		
		assertFalse(cartoes.isEmpty());

	}
	
	@Test(expected = NoContentCustom.class)
	public void listarCartoesComRetornoVazioTest() {
		
		Page<Cartao> pageCartoes = new PageImpl<>(Arrays.asList());
		
		when(cartaoRepository.findAll(PageRequest.of(PAGE, SIZE))).thenReturn(pageCartoes);
		
		Page<Cartao> cartoes = cartaoService.listarCartoes(PAGE, SIZE);
		
		assertTrue(cartoes.getContent().isEmpty());

	}
	
	@Test
	public void atualizarCartao() {
		
		CartaoRequestUpdate cartaoRequest = this.builderCartaoRequestUpdate();
		
		Cartao builderCartao = this.builderCartao();
		
		when(cartaoRepository.findById(any())).thenReturn(Optional.of(builderCartao));
		
		when(cartaoRepository.save(any(Cartao.class))).thenReturn(builderCartao);
		
		when(mapper.mapToModelUpdate(any(CartaoRequestUpdate.class))).thenReturn(builderCartao());
		
		cartaoService.atualizarCartao(cartaoRequest, "1");

		verify(cartaoRepository, times(1)).findById(any());
        verify(cartaoRepository, times(1)).save(any(Cartao.class));

	}
	
	@Test(expected = NotFoundCustom.class)
	public void atualizarCartaoComIdCartaoInexistente() {
		
		CartaoRequestUpdate cartaoRequest = this.builderCartaoRequestUpdate();
		
		cartaoService.atualizarCartao(cartaoRequest, null);
	}
	
	@Test
	public void excluirCartao() {
		
		Cartao builderCartao = this.builderCartao();
		
		when(cartaoRepository.findById(any())).thenReturn(Optional.of(builderCartao));
		
		CartaoResponse response = cartaoService.excluirCartao("1");
		
		Assert.assertNotNull(response);
	}
	
	@Test(expected = NotFoundCustom.class)
	public void excluirCartaoComCartaoInexistente() {
		
		Optional<Cartao> cartao = Optional.empty();
		
		when(cartaoRepository.findById(any())).thenReturn(cartao);
		
		CartaoResponse response = cartaoService.excluirCartao("1");
		
		Assert.assertNotNull(response);
	}
	
	@Test
	public void atualizarStatusCartao() {
		
		when(cartaoRepository.findById(any())).thenReturn(Optional.of(builderCartao()));
		
		when(cartaoRepository.save(any(Cartao.class))).thenReturn(builderCartao());
		
		cartaoService.atualizarStatusCartao(builderCartaoRequestUpdate(), "1");
		
		verify(cartaoRepository, times(1)).findById(any());
        verify(cartaoRepository, times(1)).save(any(Cartao.class));
	}
	
	@Test(expected = BadRequestCustom.class)
	public void atualizarStatusCartaoNull() {
		
		when(cartaoRepository.findById(any())).thenReturn(Optional.of(builderCartao()));
		
		CartaoRequestUpdate cartaoRequest = CartaoRequestUpdate.builder()
			.nome("O'Connel")
			.valor(new BigDecimal(100))
			.tipoProduto(TipoProduto.ALIMENTACAO)
			.status(null)
			.dataExpiracao(LocalDate.now().plusDays(365))
			.dataRecarga(LocalDate.now().plusDays(25)).build();
		
		cartaoService.atualizarStatusCartao(cartaoRequest, "1");
	}
	
	private Cartao builderCartao() {
		
		return Cartao.builder()
		.id(UUID.randomUUID().toString())
		.nome("O'Connel2")
		.dataRecarga(LocalDate.now().plusDays(25))	
		.dataExpiracao(LocalDate.now().plusDays(365))
		.limiteCartao(BigDecimal.TEN)
		.tipoCartao(TipoCartao.CREDITO)
		.status(StatusCartaoEnum.INATIVO).build();
	}
	
	private CartaoRequestUpdate builderCartaoRequestUpdate() {
		
		return CartaoRequestUpdate.builder()
			.nome("O'Connel")
			.valor(new BigDecimal(100))
			.tipoProduto(TipoProduto.ALIMENTACAO)
			.status(StatusCartaoEnum.INATIVO)
			.dataExpiracao(LocalDate.now().plusDays(365))
			.dataRecarga(LocalDate.now().plusDays(25)).build();
	}
	
	private CartaoRequest builderCartaoRequest() {
		
		return CartaoRequest.builder()
			.nome("O'Connel")
			.tipoCartao(TipoCartao.CREDITO)
			.build();
	}
	
	private CartaoResponse builderCartaoResponse() {
		
		return CartaoResponse.builder()
			.nome("O'Connel")
			.bandeira(BandeiraEnum.VISA)
			.dataExpiracao(LocalDate.now().plusDays(365))
			.dataRecarga(LocalDate.now().plusDays(25))
			.status(StatusCartaoEnum.INATIVO)
			.limiteCartao(BigDecimal.valueOf(1500.00))
			.tipoCartao(TipoCartao.CREDITO)
			.build();
		
	}
}
