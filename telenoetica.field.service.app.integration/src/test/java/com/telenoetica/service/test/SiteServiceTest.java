package com.telenoetica.service.test;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.telenoetica.jpa.entities.Site;
import com.telenoetica.service.SiteService;

public class SiteServiceTest extends BaseServiceTest {
  
  @Autowired
  private SiteService siteService;
  
  @Test
  public void testSites(){
    
    List<Site> siteList = siteService.getSites();
    System.err.println("...Site List ="+siteList.size());
    
  }

}
