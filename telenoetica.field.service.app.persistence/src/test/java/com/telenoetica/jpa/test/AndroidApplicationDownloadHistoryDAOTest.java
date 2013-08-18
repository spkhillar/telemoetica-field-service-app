package com.telenoetica.jpa.test;


import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.telenoetica.jpa.entities.AndroidApplicationDownloadHistory;
import com.telenoetica.jpa.entities.User;
import com.telenoetica.jpa.repositories.AndroidApplicationDownloadHistoryDAO;
import com.telenoetica.jpa.repositories.UserDAO;

public class AndroidApplicationDownloadHistoryDAOTest extends BaseTest {

  @Autowired
  private AndroidApplicationDownloadHistoryDAO androidApplicationDownloadHistoryDAO;

  @Autowired
  private UserDAO userDao;

  @Test
  public void test1(){
    User user = userDao.findOne(1L);
    AndroidApplicationDownloadHistory androidApplicationDownloadHistory = new AndroidApplicationDownloadHistory("dev-1234-567", user);
    AndroidApplicationDownloadHistory savedEntity = androidApplicationDownloadHistoryDAO.save(androidApplicationDownloadHistory);
    Assert.assertTrue((savedEntity.getId() != null && savedEntity.getId().longValue()>0 ));
    System.err.println("...test...."+savedEntity.getId());
  }
}
