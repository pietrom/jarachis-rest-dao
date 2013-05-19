package com.blogspot.javapeanuts.jarachis.restdao;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestOperations;

public class RestDaoTest {
	private static final String BASE_URL = "http://myhost.com/base/url/person";
	private static final Long ID = 19L;

	private static final class Person {
	}

	@Test
	public void getById() throws Exception {
		Person aPerson = new Person();
		RestOperations ops = mock(RestOperations.class);
		when(ops.getForEntity(BASE_URL + "/{id}", Person.class, ID)).thenReturn(new ResponseEntity<RestDaoTest.Person>(aPerson, HttpStatus.OK));
		RestDao<Long, Person> dao = new RestDao<Long, Person>(ops, BASE_URL, Long.class, Person.class);
		Person loaded = dao.getById(ID);
		verify(ops).getForEntity(BASE_URL + "/{id}", Person.class, ID);
		assertEquals(aPerson, loaded);
	}
}
