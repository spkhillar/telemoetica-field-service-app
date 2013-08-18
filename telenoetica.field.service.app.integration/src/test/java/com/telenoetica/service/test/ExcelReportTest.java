package com.telenoetica.service.test;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.telenoetica.jpa.entities.User;
import com.telenoetica.service.UserService;
import com.telenoetica.service.excel.ExcelFillerService;
import com.telenoetica.service.excel.ExcelLayoutService;
import com.telenoetica.service.util.ExcelRendererModel;
import com.telenoetica.service.util.ExcelWriter;
import com.telenoetica.service.util.ServiceUtil;

public class ExcelReportTest extends BaseServiceTest {

  @Autowired
  private UserService userService;

  @Resource(name="defaultExcelLayoutService")
  private ExcelLayoutService excelLayoutService;
  
  @Resource(name="defaultExcelLayoutFillerService")
  private ExcelFillerService excelFillerService;
  
  @Test
  public void testExcelReport() {

    // 1. Create new workbook
    HSSFWorkbook workbook = new HSSFWorkbook();

    // 2. Create new worksheet
    HSSFSheet worksheet = workbook.createSheet("users");

    //3.create coulmn headers
    @SuppressWarnings("serial")
    List<String> excelColumns = new ArrayList<String>() {
      {
        add("User Name");
        add("First Name");
        add("Last Name");
        add("Email");
        add("Enabled");
        add("Role");
        add("Created Date");
      }
    };
    //step 5 get entities
    List<User> userList =  userService.findALL();

    //step 6 populate values as per the headings
    List<List<Object>> targetValues = new ArrayList<List<Object>>();
    for (User user : userList) {
      List<Object> values = new ArrayList<Object>();
      values.add(ServiceUtil.checkAndReturnValue(user.getUserName()));
      values.add(ServiceUtil.checkAndReturnValue(user.getFirstName()));
      values.add(ServiceUtil.checkAndReturnValue(user.getLastName()));
      values.add(ServiceUtil.checkAndReturnValue(user.getEmail()));
      values.add(ServiceUtil.checkAndReturnValue(user.getEnabled()));
      values.add(ServiceUtil.checkAndReturnValue(user.getUserRole().getRole().getName()));
      values.add(ServiceUtil.checkAndReturnValue(user.getCreatedAt()));
      targetValues.add(values);
    }
    
    //step 7 initialize renderer model
    ExcelRendererModel excelRendererModel = new ExcelRendererModel(worksheet, 5000, excelColumns, "Users");
    
    //step 8 invoke layout service
    excelLayoutService.buildReport(excelRendererModel);
    
    //step 9 fill report content
    excelFillerService.fillReport(excelRendererModel, targetValues);
    
    //step 10 write report
    ExcelWriter.write("myReport.xls", workbook);
  }

}
