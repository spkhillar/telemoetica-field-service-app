package com.telenoetica.jpa.test;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
    "classpath:applicationContext-property-initializer-test.xml",
    "classpath:applicationContext-data-source-test.xml",
		"classpath:applicationContext-liquibase.xml",
		"classpath:spring-data-jpa-config.xml" })
public class BaseTest extends AbstractTransactionalJUnit4SpringContextTests{

}
