package com.telenoetica.service.test;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.telenoetica.jpa.entities.CallOutVisit;
import com.telenoetica.jpa.entities.Site;
import com.telenoetica.jpa.entities.User;
import com.telenoetica.jpa.repositories.SiteDAO;
import com.telenoetica.jpa.repositories.UserDAO;
import com.telenoetica.service.CallOutVisitService;

public class CallOutVisitServiceTest extends BaseServiceTest{

  @Autowired
  private CallOutVisitService callOutVisitService;

  @Autowired
  private SiteDAO siteDao;

  @Autowired
  private UserDAO userDAO;

  @Test
  public void test(){

    User user = userDAO.findOne(1L);
    Site site = siteDao.findOne(6L);

    CallOutVisit callOutVisit = new CallOutVisit();
    callOutVisit.setUser(user);
    callOutVisit.setSite(site);
    callOutVisit.setTimeComplainReceived(new Date());
    callOutVisit.settimeFaultResolved(new Date());
    callOutVisit.setTimeReachedToSite(new Date());

    callOutVisit = callOutVisitService.saveOrUpdate(callOutVisit);

    System.err.println("...Saved..."+callOutVisit);

    callOutVisit.setAccessCode("BBBBB");

    callOutVisitService.saveOrUpdate(callOutVisit);

    callOutVisit = callOutVisitService.retrieve(callOutVisit.getId());



    System.err.println("...Retrieved..."+callOutVisit);

    callOutVisitService.getCallOutVisits(1);
  }
}
