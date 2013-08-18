package com.telenoetica.service.test;

import org.springframework.test.context.ContextConfiguration;

import com.telenoetica.jpa.test.BaseTest;

@ContextConfiguration(locations = {
"classpath:applicationContext-service.xml" },inheritLocations=true)
public class BaseServiceTest extends BaseTest {

}
