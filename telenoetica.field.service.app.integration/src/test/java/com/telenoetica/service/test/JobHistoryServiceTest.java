package com.telenoetica.service.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.telenoetica.service.JobHistoryService;

public class JobHistoryServiceTest extends BaseServiceTest {
  
  @Autowired
  private JobHistoryService jobHistoryService;

  @Test
  public void test(){
    jobHistoryService.retrieve(1L);
  }
}
