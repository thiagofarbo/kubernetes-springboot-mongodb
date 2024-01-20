package com.thec.card.event;

import javax.servlet.http.HttpServletResponse;

public class ResourceCreatedEvent<K,T>{

	private K id;
	private T resource;
	private HttpServletResponse response;
	
	
	public ResourceCreatedEvent(T recurso) {
		this.resource = recurso;
	}
	
	public ResourceCreatedEvent(K id, T recurso, HttpServletResponse response, String queue) {
		this.id = id;
		this.resource = recurso;
		this.response = response;
	}

	public K getId() {
		return id;
	}

	public void setId(K id) {
		this.id = id;
	}

	public T getResource() {
		return resource;
	}

	public void setResource(T resource) {
		this.resource = resource;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}
}
