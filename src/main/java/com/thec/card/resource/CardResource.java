package com.thec.card.resource;

import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletResponse;

import com.thec.card.event.CardCreatedEvent;
import com.thec.card.exception.ErrorInfo;
import com.thec.card.exception.GlobalExceptionHandler;
import com.thec.card.response.CardResponse;
import com.thec.card.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.thec.card.domain.Card;
import com.thec.card.request.CardRequest;
import com.thec.card.request.CardRequestUpdate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api")
@Api(value = "/card", produces = "application/json;charset=UTF-8", tags = { "Cards" })
@ApiOperation(value = "cards", notes = "Card API", response = CardResource.class)
@ApiResponses({ @ApiResponse(code = 400, message = GlobalExceptionHandler.GLOBAL_MESSAGE_400, response = ErrorInfo.class),
		@ApiResponse(code = 404, message = GlobalExceptionHandler.GLOBAL_MESSAGE_404, response = ErrorInfo.class),
		@ApiResponse(code = 500, message = GlobalExceptionHandler.GLOBAL_MESSAGE_500, response = ErrorInfo.class) })
public class CardResource {
	
	@Autowired
	private CardService cardService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Value("${queue}")	
	private String queue;
	
	@Value("${dead.letter.queue}")
	private String deadLetter;
	
//	@Autowired
//	private CartaoCacheService cacheService;
	
	static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
	
	@ResponseBody
	@PostMapping("/cards")
	private ResponseEntity<CardResponse> create(@RequestBody final CardRequest card, HttpServletResponse response){
		publisher.publishEvent(new CardCreatedEvent(card, response, "cartao-queue"));
		return ResponseEntity.ok(this.cardService.createCard(card));
	}
	
	@GetMapping("/cards/{id}")
//	@HystrixCommand(fallbackMethod= "getCartaoFromCache")
	public ResponseEntity<CardResponse> findCardById(@PathVariable final String id){
		return ResponseEntity.ok(this.cardService.findById(id));
	}
	
	@GetMapping("/cards")
	public ResponseEntity<Page<Card>> listCards(@RequestParam(defaultValue = "0") final int page, @RequestParam(defaultValue = "50") final int size){
		return ResponseEntity.ok(this.cardService.listCards(page, size));
	}
	
	@PutMapping("/cards/{id}")
	public ResponseEntity<CardResponse> updateCard(@RequestBody final CardRequestUpdate cartaoRequest, @PathVariable final String id){
		return ResponseEntity.ok(this.cardService.updateCards(cartaoRequest, id));
	}
	
	@PatchMapping("/cards/{id}")
	public ResponseEntity<CardRequestUpdate> updateStatus(@RequestBody final CardRequestUpdate cartaoRequest, @PathVariable final String id){
		return ResponseEntity.ok(this.cardService.updateStatusCard(cartaoRequest, id));
	}
	
	@DeleteMapping("/cards/{id}")
	public ResponseEntity<CardResponse> delete(@PathVariable final String id){
		return ResponseEntity.ok(this.cardService.deleteCard(id));
	}
	
//	public ResponseEntity<CartaoResponse> getCartaoFromCache(final String id) {
//		return  ResponseEntity.ok(this.cacheService.getCartaoFromCache(id));
//	}
}