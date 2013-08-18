package com.telenoetica.service.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;

public class TestPasswordEncoder extends BaseServiceTest {

	@Autowired
	private ShaPasswordEncoder shaPasswordEncoder;

	@Test
	public void testPassword() {
		String encodedPassword = shaPasswordEncoder.encodePassword("root",
				"root");
		System.err.println("..encodedPassword.." + encodedPassword);
	}
}
