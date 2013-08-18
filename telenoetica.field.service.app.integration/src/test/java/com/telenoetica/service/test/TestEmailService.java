package com.telenoetica.service.test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.telenoetica.service.EmailService;
import com.telenoetica.service.mail.EmailTemplate;

public class TestEmailService extends BaseServiceTest {

	@Autowired
	private EmailService emailService;

	@Test
	public void mailTest() throws IOException {

		File f = new File("dummy.txt");
		// System.err.println("...File.."+f.getAbsolutePath());
		List<String> toAddress = new ArrayList<String>();
		toAddress.add("shivprasad.khillar@gmail.com");
		toAddress.add("satyamit2001@gmail.com");
		EmailTemplate emailTemplate = new EmailTemplate(toAddress,
				"Test Email", "Routine Visit");
		emailTemplate.setAttachmentFileName(f.getAbsolutePath());
		emailService.sendEmail(emailTemplate);
	}

}
