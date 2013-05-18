package com.blogspot.javapeanuts.jarachis.restdao;

import java.io.Serializable;

import org.springframework.web.client.RestOperations;

public class RestDao<K extends Serializable, T> {
	private final RestOperations rest;
	private final String baseUrl;
	private final Class<K> keyClass;
	private final Class<T> typeClass;

	public RestDao(RestOperations rest, String baseUrl, Class<K> keyClass, Class<T> typeClass) {
		this.rest = rest;
		this.baseUrl = baseUrl;
		this.keyClass = keyClass;
		this.typeClass = typeClass;
	}

	public T getById(K id) {
		return rest.getForEntity(baseUrl + "/{id}", typeClass, id).getBody();
	}
}
