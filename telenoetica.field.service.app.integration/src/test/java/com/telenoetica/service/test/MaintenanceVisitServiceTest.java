package com.telenoetica.service.test;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.telenoetica.jpa.entities.Site;
import com.telenoetica.service.MaintenanceVisitService;
import com.telenoetica.service.SiteService;
import com.telenoetica.service.util.ServiceUtil;

public class MaintenanceVisitServiceTest extends BaseServiceTest {

  @Autowired
  private MaintenanceVisitService maintenanceVisitService;


  @Autowired
  private SiteService siteService;


  @Test
  public void testVisit() {
    maintenanceVisitService.getVisits();
  }

  @Test
  public void testFindBySite(){
    Site site = siteService.retrieve(5L);
    Date date = ServiceUtil.getDateInFormat("31/01/2013", "dd/MM/yyyy");
    maintenanceVisitService.findBySiteAndCreatedAtBetween(site, date);
  }
}
