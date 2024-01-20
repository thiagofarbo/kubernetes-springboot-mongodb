package com.thec.card;

import com.thec.card.cache.CacheConfig;
import com.thec.card.domain.Card;
import com.thec.card.domain.exception.BadRequestCustom;
import com.thec.card.domain.exception.NoContentCustom;
import com.thec.card.domain.exception.NotFoundCustom;
import com.thec.card.domain.generator.Generator;
import com.thec.card.enums.BrandEnum;
import com.thec.card.enums.CardType;
import com.thec.card.enums.ProductType;
import com.thec.card.enums.CardStatusEnum;
import com.thec.card.mapper.Mapper;
import com.thec.card.redis.repository.RedisRepository;
import com.thec.card.repository.CardRepository;
import com.thec.card.request.CardRequest;
import com.thec.card.request.CardRequestUpdate;
import com.thec.card.response.CardResponse;
import com.thec.card.service.CardService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class CardServiceTest {
	
	private final Integer PAGE = 1;

	private final Integer SIZE = 50;

	@Mock
	private Mapper mapper;

	@Mock
	private Generator generator;

	@Mock
	private RedisRepository redisRepository;

	@Mock
	private CacheConfig config;

	@Mock
	private CardRepository cartaoRepository;

	@InjectMocks
	private CardService cartaoService;

	@Value("${hash.key}")
	private String hashKey;

	@Test
	public void salvarCartaoTest() {

			CardRequest cartaoRequest = this.builderCartaoRequest();

			when(cartaoRepository.save(any(Card.class))).thenReturn(builderCard());

			when(generator.cardGenerator()).thenReturn(UUID.randomUUID().toString());

			when(mapper.ToCard(any(CardResponse.class))).thenReturn(builderCard());

			when(mapper.toCardResponse(any(Card.class))).thenReturn(builderCartaoResponse());

			CardResponse cartao = cartaoService.createCard(cartaoRequest);

			assertNotNull(cartao);
	}
	
	@Test
	public void consultarCartao() {
		
		when(cartaoRepository.findById(any())).thenReturn(Optional.of(builderCard()));
		
		when(mapper.toCardResponse(any(Card.class))).thenReturn(builderCartaoResponse());
		
		CardResponse cartao = cartaoService.findById("1");

		assertNotNull(cartao);
	}
	
	@Test(expected = NotFoundCustom.class)
	public void consultarCartaoComIdCartaoInexistente() {
		
		CardResponse cartao = cartaoService.findById(null);

		assertNotNull(cartao);
	}
	
	@Test
	public void listarCartaoTest() {
		
		Page<Card> pageCartoes = new PageImpl<>(Arrays.asList(builderCard()));
		
		when(cartaoRepository.findAll(PageRequest.of(PAGE, SIZE))).thenReturn(pageCartoes);
		
		Page<Card> cartoes = cartaoService.listCards(PAGE,SIZE);
		
		assertFalse(cartoes.isEmpty());

	}
	
	@Test(expected = NoContentCustom.class)
	public void listarCartoesComRetornoVazioTest() {
		
		Page<Card> pageCartoes = new PageImpl<>(Arrays.asList());
		
		when(cartaoRepository.findAll(PageRequest.of(PAGE, SIZE))).thenReturn(pageCartoes);
		
		Page<Card> cartoes = cartaoService.listCards(PAGE, SIZE);
		
		assertTrue(cartoes.getContent().isEmpty());

	}
	
	@Test
	public void atualizarCartao() {
		
		CardRequestUpdate cartaoRequest = this.builderCartaoRequestUpdate();
		
		Card builderCartao = this.builderCard();
		
		when(cartaoRepository.findById(any())).thenReturn(Optional.of(builderCartao));
		
		when(cartaoRepository.save(any(Card.class))).thenReturn(builderCartao);
		
		when(mapper.toCardUpdate(any(CardRequestUpdate.class))).thenReturn(builderCard());
		
		cartaoService.updateCards(cartaoRequest, "1");

		verify(cartaoRepository, times(1)).findById(any());
        verify(cartaoRepository, times(1)).save(any(Card.class));

	}
	
	@Test(expected = NotFoundCustom.class)
	public void atualizarCartaoComIdCartaoInexistente() {
		
		CardRequestUpdate cartaoRequest = this.builderCartaoRequestUpdate();
		
		cartaoService.updateCards(cartaoRequest, null);
	}
	
	@Test
	public void excluirCartao() {
		
		Card builderCartao = this.builderCard();
		
		when(cartaoRepository.findById(any())).thenReturn(Optional.of(builderCartao));
		
		CardResponse response = cartaoService.deleteCard("1");
		
		Assert.assertNotNull(response);
	}
	
	@Test(expected = NotFoundCustom.class)
	public void excluirCartaoComCartaoInexistente() {
		
		Optional<Card> cartao = Optional.empty();
		
		when(cartaoRepository.findById(any())).thenReturn(cartao);
		
		CardResponse response = cartaoService.deleteCard("1");
		
		Assert.assertNotNull(response);
	}
	
	@Test
	public void atualizarStatusCartao() {
		
		when(cartaoRepository.findById(any())).thenReturn(Optional.of(builderCard()));
		
		when(cartaoRepository.save(any(Card.class))).thenReturn(builderCard());
		
		cartaoService.updateStatusCard(builderCartaoRequestUpdate(), "1");
		
		verify(cartaoRepository, times(1)).findById(any());
        verify(cartaoRepository, times(1)).save(any(Card.class));
	}
	
	@Test(expected = BadRequestCustom.class)
	public void atualizarStatusCartaoNull() {
		
		when(cartaoRepository.findById(any())).thenReturn(Optional.of(builderCard()));
		
		CardRequestUpdate cartaoRequest = CardRequestUpdate.builder()
			.name("O'Connel")
			.amount(new BigDecimal(100))
			.productType(ProductType.FOOD)
			.status(null)
			.expirationDate(LocalDate.now().plusDays(365))
			.rechargeDate(LocalDate.now().plusDays(25)).build();
		
		cartaoService.updateStatusCard(cartaoRequest, "1");
	}
	
	private Card builderCard() {
		
		return Card.builder()
		.id(UUID.randomUUID().toString())
		.name("O'Connel2")
		.rechargeDate(LocalDate.now().plusDays(25))
		.expirationDate(LocalDate.now().plusDays(365))
		.cardLimit(BigDecimal.TEN)
		.cardType(CardType.CREDIT)
		.status(CardStatusEnum.INACTIVE).build();
	}
	
	private CardRequestUpdate builderCartaoRequestUpdate() {
		
		return CardRequestUpdate.builder()
			.name("O'Connel")
			.amount(new BigDecimal(100))
			.productType(ProductType.FOOD)
			.status(CardStatusEnum.INACTIVE)
			.expirationDate(LocalDate.now().plusDays(365))
			.rechargeDate(LocalDate.now().plusDays(25)).build();
	}
	
	private CardRequest builderCartaoRequest() {
		
		return CardRequest.builder()
			.name("O'Connel")
			.cardType(CardType.CREDIT)
			.build();
	}
	
	private CardResponse builderCartaoResponse() {
		
		return CardResponse.builder()
			.name("O'Connel")
			.cardBrand(BrandEnum.VISA)
			.expirationDate(LocalDate.now().plusDays(365))
			.rechargeDate(LocalDate.now().plusDays(25))
			.status(CardStatusEnum.INACTIVE)
			.cardLimit(BigDecimal.valueOf(1500.00))
			.cardType(CardType.CREDIT)
			.build();
	}

	private HashOperations<String, Object, Object> hashOperations(){
		final Card card = builderCard();
		HashOperations<String, Object, Object> hashOperations = config.redisTemplate().opsForHash();
		hashOperations.put(hashKey, card.getId(), card);
		return hashOperations;
	}

	@Before
	public void setUp(){
		ReflectionTestUtils.setField(cartaoService, "hashKey", "Cartao");
	}
}
