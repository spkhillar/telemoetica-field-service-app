package com.telenoetica.jpa.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.telenoetica.jpa.repositories.DieselVendorDAO;

public class DieselVendorDAOTest extends BaseTest{

  @Autowired
  private DieselVendorDAO dieselVendorDAO;

  
  @Test
  public void testDieselDAO(){
    long count = dieselVendorDAO.count();
    System.err.println("..Count..."+count);
  }
}
