package com.telenoetica.service.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.telenoetica.service.RoutineVisitService;

public class RoutineVisitServiceTest extends BaseServiceTest {

  @Autowired
  private RoutineVisitService routineVisitService;
  
  @Test
  public void testVisit(){
    routineVisitService.getVisits();
  }
  
}
