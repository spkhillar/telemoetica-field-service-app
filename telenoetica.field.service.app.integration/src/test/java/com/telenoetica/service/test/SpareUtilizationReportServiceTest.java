package com.telenoetica.service.test;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.telenoetica.jpa.entities.Site;
import com.telenoetica.jpa.repositories.SiteDAO;
import com.telenoetica.service.SpareUtilizationReportService;

public class SpareUtilizationReportServiceTest extends BaseServiceTest {

  @Autowired
  private SpareUtilizationReportService service;

  @Autowired
  private SiteDAO siteDAO;

  @Test
  public void test() {

    List<Site> siteList = siteDAO.findAll();
    try {
      service.createNewReport(null);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
