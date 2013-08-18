package com.telenoetica.service.test;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.telenoetica.jpa.entities.AndroidApplicationDownloadHistory;
import com.telenoetica.service.AndroidApplicationDownloadHistoryService;

public class AndroidApplicationDownloadHistoryServiceTest extends BaseServiceTest {

  @Autowired
  private AndroidApplicationDownloadHistoryService androidApplicationDownloadHistoryService;

  @Test
  public void test(){
    AndroidApplicationDownloadHistory androidApplicationDownloadHistory = androidApplicationDownloadHistoryService.createOrUpdateDownloadForUser("shiv", "root");
    Assert.assertNotNull(androidApplicationDownloadHistory);

    androidApplicationDownloadHistory = androidApplicationDownloadHistoryService.createOrUpdateDownloadForUser("shiv-1234", "root");

  }
}
