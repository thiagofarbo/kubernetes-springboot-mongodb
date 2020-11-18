package br.com.thec.cartao.resource;

import static br.com.thec.cartao.exception.GlobalExceptionHandler.MENSAGEM_GLOBAL_400;
import static br.com.thec.cartao.exception.GlobalExceptionHandler.MENSAGEM_GLOBAL_404;
import static br.com.thec.cartao.exception.GlobalExceptionHandler.MENSAGEM_GLOBAL_500;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
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

import br.com.thec.cartao.domain.Cartao;
import br.com.thec.cartao.event.CartaoCriadoEvent;
import br.com.thec.cartao.exception.ErrorInfo;
import br.com.thec.cartao.request.CartaoRequest;
import br.com.thec.cartao.request.CartaoRequestUpdate;
import br.com.thec.cartao.response.CartaoResponse;
import br.com.thec.cartao.service.CartaoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api")
@Api(value = "/cartões", produces = "application/json;charset=UTF-8", tags = { "Cartões" })
@ApiOperation(value = "cartões", notes = "API Cartões", response = CartaoResource.class)
@ApiResponses({ @ApiResponse(code = 400, message = MENSAGEM_GLOBAL_400, response = ErrorInfo.class),
		@ApiResponse(code = 404, message = MENSAGEM_GLOBAL_404, response = ErrorInfo.class),
		@ApiResponse(code = 500, message = MENSAGEM_GLOBAL_500, response = ErrorInfo.class) })
public class CartaoResource {
	
	@Autowired
	private CartaoService cartaoService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@ResponseBody
	@PostMapping("/cartoes")
	private ResponseEntity<CartaoResponse> criar(@RequestBody final CartaoRequest cartao, HttpServletResponse response){
		
		publisher.publishEvent(new CartaoCriadoEvent(cartao, response));
		
		return ResponseEntity.ok(this.cartaoService.criarCartao(cartao));
	}
	
	@GetMapping("/cartoes/{id}")
	public ResponseEntity<CartaoResponse> consultarCartao(@PathVariable final String id){
		return ResponseEntity.ok(this.cartaoService.consultarCartao(id));
	}
	
	@GetMapping("/cartoes")
	public ResponseEntity<Page<Cartao>> listarCartoes(@RequestParam(defaultValue = "0") final int page, @RequestParam(defaultValue = "50") final int size){
		return ResponseEntity.ok(this.cartaoService.listarCartoes(page, size));
	}
	
	@PutMapping("/cartoes/{id}")
	public ResponseEntity<CartaoResponse> atualizarCartao(@RequestBody final CartaoRequestUpdate cartaoRequest, @PathVariable final String id){
		return ResponseEntity.ok(this.cartaoService.atualizarCartao(cartaoRequest, id));
	}
	
	@PatchMapping("/cartoes/{id}")
	public ResponseEntity<CartaoRequestUpdate> atualizarStatus(@RequestBody final CartaoRequestUpdate cartaoRequest, @PathVariable final String id){
		return ResponseEntity.ok(this.cartaoService.atualizarStatusCartao(cartaoRequest, id));
	}
	
	@DeleteMapping("/cartoes/{id}")
	public ResponseEntity<CartaoResponse> deletar(@PathVariable final String id){
		return ResponseEntity.ok(this.cartaoService.excluirCartao(id));
	}
}