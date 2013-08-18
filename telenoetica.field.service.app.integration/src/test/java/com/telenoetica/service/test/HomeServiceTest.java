package com.telenoetica.service.test;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.telenoetica.jpa.entities.Client;
import com.telenoetica.jpa.entities.Fault;
import com.telenoetica.jpa.entities.MaintenanceVisitCategory;
import com.telenoetica.jpa.entities.Site;
import com.telenoetica.jpa.entities.Spare;
import com.telenoetica.service.AndroidHomeService;
import com.telenoetica.service.ClientService;
import com.telenoetica.service.FaultService;
import com.telenoetica.service.MaintenanceVisitCategoryService;
import com.telenoetica.service.SiteService;
import com.telenoetica.service.SpareService;
import com.telenoetica.util.model.HomeAndroidObject;

public class HomeServiceTest extends BaseServiceTest {

  @Autowired
  private FaultService faultService;

  @Autowired
  private SiteService siteService;

  @Autowired
  private ClientService clientService;

  @Autowired
  private SpareService spareService;

  @Autowired
  private MaintenanceVisitCategoryService maintenanceVisitCategoryService;

  @Autowired
  private AndroidHomeService androidHomeService;

  @Test
  public void testAll(){
    List<Fault> faults = faultService.getFaults();
    System.err.println("..Fault..."+faults.size());

    List<Site> sites = siteService.getSites();
    System.err.println("..sites..."+sites.size());

    List<Client> clients = clientService.getClients();
    System.err.println("..clients..."+clients.size());

    List<Spare> spares = spareService.getSpares();
    System.err.println("..spares..."+spares.size());

    List<MaintenanceVisitCategory> maintenanceCategories = maintenanceVisitCategoryService.getCategories();
    System.err.println("..maintenanceCategories..."+maintenanceCategories.size());
  }

  @Test
  public void testAndroidHome(){
    Date start = new Date();
    HomeAndroidObject object = androidHomeService.getAndroidHomeObject();

    System.err.println("...."+object);

    Date end = new Date();
    long total = end.getTime()-start.getTime();

    System.err.println(total+"-----End-----"+new Date());

  }
}
