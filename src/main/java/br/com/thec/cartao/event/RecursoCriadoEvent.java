package br.com.thec.cartao.event;

import javax.servlet.http.HttpServletResponse;

public class RecursoCriadoEvent <K,T>{

	private K id;
	private T recurso;
	private HttpServletResponse response;
	
	
	public RecursoCriadoEvent(T recurso) {
		this.recurso = recurso;
	}
	
	public RecursoCriadoEvent(K id, T recurso, HttpServletResponse response, String queue) {
		this.id = id;
		this.recurso = recurso;
		this.response = response;
	}

	public K getId() {
		return id;
	}

	public void setId(K id) {
		this.id = id;
	}

	public T getRecurso() {
		return recurso;
	}

	public void setRecurso(T recurso) {
		this.recurso = recurso;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}
}
