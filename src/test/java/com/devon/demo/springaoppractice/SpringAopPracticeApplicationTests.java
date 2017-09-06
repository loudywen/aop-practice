package com.devon.demo.springaoppractice;

import com.devon.demo.pojointercept.Login;
import com.devon.demo.pojointercept.LoginFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringAopPracticeApplicationTests {

	@Autowired
	private Service service;

	@Test
	public void shouldApplyCustomAnnotation() throws InterruptedException {
		service.serve();
	}

	@Test
	public void pojoInterceptTest(){
		Login login = LoginFactory.createLogin();
		login.login("user", "secret");
		//login.logout();
	}
}
