package com.epam.trapeznikau.testwebserversocket;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static io.restassured.response.Response.*;

public class TestWebserverSocket {

	@BeforeClass
	public static void setUpRestAssured() {
		String port = System.getProperty("server.port");
		if (port == null) {
			RestAssured.port = Integer.valueOf(8020);
		} else {
			RestAssured.port = Integer.valueOf(port);
		}

		String basePath = System.getProperty("server.base");
		if (basePath == null) {
			basePath = "/test/";
		}

		RestAssured.basePath = basePath;
		String baseHost = System.getProperty("server.host");
		if (baseHost == null) {
			baseHost = "http://localhost";
		}
		RestAssured.baseURI = baseHost;
	}

	@Test
	public void testGetMethodCheckStatusCode() {
		Response rpBook = given()
				.when().get("http://localhost/book")
				.thenReturn();
		Assert.assertEquals(rpBook.getStatusCode(), 200);
	}

	@Test
	public void testGetMethodCheckBadStatusCode() {
		Response rpBook = given()
				.when().get("http://localhost/book1")
				.thenReturn();
		System.out.println(rpBook.getStatusCode());
		Assert.assertEquals(rpBook.getStatusCode(), 400);
	}

	@Test
	public void testPutMethodCheck() {
		Response rpBook = given()
					.accept("application/json")
					.contentType("application/json")
					.body("{\"id\":\"7\",\"title\":\"HarryPotter\",\"author\":\"J.K.Rowling\",\"price\":\"400\"}\n\r")
				.when().put("http://localhost/book")
				.thenReturn();

		Assert.assertEquals(rpBook.getStatusCode(), 200);
	}

	@Test
	public void testDeleteMethodCheckBadRequest() {
		Response rpBook = given()
					.accept("application/json")
					.contentType("application/json")
					.body("{\"id\":\"7\",\"title\":\"HarryPotter1\",\"author\":\"J.K.Rowling\",\"price\":\"400\"}\n\r")
				.when().delete("http://localhost/book")
				.thenReturn();

		Assert.assertEquals(rpBook.getStatusCode(), 400);
	}

	@Test
	public void testPostMethodCheckOKRequest() {
		Response rpBook = given()
					.accept("application/json")
					.contentType("application/json")
					.body("{\"id\":\"7\",\"title\":\"HarryPotter1\",\"author\":\"J.K.Rowling\",\"price\":\"400\"}\n\r")
				.when().post("http://localhost/book")
				.thenReturn();

		Assert.assertEquals(rpBook.getStatusCode(), 200);
	}

	@Test
	public void testDeleteMethodCheckOKRequest() {
		Response rpBook = given()
					.accept("application/json")
					.contentType("application/json")
					.body("{\"id\":\"7\",\"title\":\"HarryPotter1\",\"author\":\"J.K.Rowling\",\"price\":\"400\"}\n\r")
				.when().delete("http://localhost/book")
				.thenReturn();

		Assert.assertEquals(rpBook.getStatusCode(), 200);
	}

}
