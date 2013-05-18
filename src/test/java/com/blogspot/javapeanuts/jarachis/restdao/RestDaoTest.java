package com.blogspot.javapeanuts.jarachis.restdao;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestOperations;

public class RestDaoTest {
	private static final String BASE_URL = "http://myhost.com/base/url/person";
	
	private static final class Person {
	}

	@Test
	public void getById() throws Exception {
		Long id = 19L;
		Person aPerson = new Person();
		RestOperations ops = mock(RestOperations.class);
		when(ops.getForEntity(BASE_URL + "/{id}", Person.class, id)).thenReturn(new ResponseEntity<RestDaoTest.Person>(aPerson, HttpStatus.OK));
		RestDao<Long, Person> dao = new RestDao<Long, Person>(ops, BASE_URL, Long.class, Person.class);
		Person loaded = dao.getById(id);
		verify(ops).getForEntity(BASE_URL + "/{id}", Person.class, id);
		assertEquals(aPerson, loaded);
	}
}
