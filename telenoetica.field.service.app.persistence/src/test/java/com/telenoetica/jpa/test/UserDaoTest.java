package com.telenoetica.jpa.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.telenoetica.jpa.entities.User;
import com.telenoetica.jpa.repositories.UserDAO;

public class UserDaoTest extends BaseTest{

  @Autowired
  private UserDAO userDao;
  
  @Test
  public void testUser(){
    
    User user = userDao.findOne(1L);
    
    System.err.println("..Users..."+user);
  }
  
}
