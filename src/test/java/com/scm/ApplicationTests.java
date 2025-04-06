package com.scm;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class ApplicationTests {

	@Test
	void contextLoads() {
	}

//	@Autowired
//	private EmailService service;
//
//	@Test
//	void sendEmailTest() {
//		service.sendEmail("connectprem4@gmail.com", "Just managing the emails",
//				"this is scm project working on email service");
//	}

	@Test
	void testUnits() {


		int result=40;

		List<String>  list = List.of("basant","diksha","prem");

//		assertThat(result).isEqualTo(50);

		assertThat(list).asList().hasSize(3);




	}

}