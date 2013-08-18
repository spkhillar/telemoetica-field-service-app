package com.telenoetica.jpa.test;

import org.springframework.beans.factory.annotation.Autowired;

import com.telenoetica.jpa.repositories.CallOutVisitDAO;


public class CallOutVisitDAOTest extends BaseTest{

	@Autowired
	private CallOutVisitDAO callOutVisitDAO;
	
	@org.junit.Test
	public void test1(){
		System.out.println("..Hello...");
		callOutVisitDAO.count();
		
	}
	
}
