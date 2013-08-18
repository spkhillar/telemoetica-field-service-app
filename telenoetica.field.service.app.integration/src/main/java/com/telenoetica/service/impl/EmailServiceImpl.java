/*
 * Copyright (C) 2013 Telenoetica, Inc. All rights reserved
 */
package com.telenoetica.service.impl;

import java.io.File;
import java.text.SimpleDateFormat;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.telenoetica.service.EmailService;
import com.telenoetica.service.mail.EmailTemplate;

/**
 * The Class EmailServiceImpl.
 * 
 * @author Shiv Prasad Khillar
 */
@Service("emailService")
public class EmailServiceImpl implements EmailService {

	/** The mail sender. */
	@Autowired
	private JavaMailSender mailSender;

	/**
	 * Send email.
	 * 
	 * @param emailTemplate
	 *            the email template
	 * @see com.telenoetica.service.EmailService#sendEmail(com.telenoetica.service.mail.EmailTemplate)
	 */
	@Override
	public void sendEmail(final EmailTemplate emailTemplate) {

		Assert.notEmpty(emailTemplate.getTo(), "To Email Address not found");
		Assert.hasText(emailTemplate.getText(), "Body is null or Empty");
		Assert.hasText(emailTemplate.getSubject(), "Subject is null or empty");
		File attachementFile = null;
		String[] bccArray = null;
		String[] ccArray = null;
		String[] toArray = new String[emailTemplate.getTo().size()];
		emailTemplate.getTo().toArray(toArray);

		if (CollectionUtils.isNotEmpty(emailTemplate.getCc())) {
			ccArray = new String[emailTemplate.getCc().size()];
			emailTemplate.getCc().toArray(ccArray);
		}

		if (CollectionUtils.isNotEmpty(emailTemplate.getBcc())) {
			bccArray = new String[emailTemplate.getBcc().size()];
			emailTemplate.getBcc().toArray(bccArray);
		}

		if (StringUtils.isNotBlank(emailTemplate.getAttachmentFileName())) {
			attachementFile = new File(emailTemplate.getAttachmentFileName());
		}

		sendEmail(emailTemplate.getText(), emailTemplate.getSubject(), toArray,
				ccArray, bccArray, attachementFile);
	}

	/**
	 * Send email.
	 * 
	 * @param emailBody
	 *            the email body
	 * @param subject
	 *            the subject
	 * @param toArray
	 *            the to array
	 * @param ccArray
	 *            the cc array
	 * @param bccArray
	 *            the bcc array
	 * @param attachementFile
	 *            the attachement file
	 */
	private void sendEmail(final String emailBody, final String subject,
			final String[] toArray, final String[] ccArray,
			final String[] bccArray, final File attachementFile) {

		mailSender.send(new MimeMessagePreparator() {
			@Override
			public void prepare(final MimeMessage mimeMessage)
					throws MessagingException {
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage,
						true, "UTF-8");
				message.setTo(toArray);
				message.setSubject(subject);
				message.setText(emailBody, true);
				if (attachementFile != null) {
					SimpleDateFormat formatter = new SimpleDateFormat("yyyyMM");
					String today = formatter.format(new java.util.Date());
					String attachment_name = subject + "_" + today;
					message.addAttachment(attachment_name, attachementFile);
				}
				if (ArrayUtils.isNotEmpty(ccArray)) {
					message.setCc(ccArray);
				}
				if (ArrayUtils.isNotEmpty(bccArray)) {
					message.setBcc(bccArray);
				}

			}
		});
	}
}
