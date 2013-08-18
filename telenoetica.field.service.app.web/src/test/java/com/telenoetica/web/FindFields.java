package com.telenoetica.web;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.telenoetica.jpa.entities.MaintenanceVisit;

public class FindFields {

  @Test
  public void testGetFields(){
    Field[] fields = getAllFields(MaintenanceVisit.class);
    
    for (Field field : fields) {
      System.out.println("..."+field.getName());
    }
  }
  
  @SuppressWarnings("rawtypes")
  public static Field[] getAllFields(Class klass) {
    List<Field> fields = new ArrayList<Field>();
    fields.addAll(Arrays.asList(klass.getDeclaredFields()));
    if (klass.getSuperclass() != null) {
      fields.addAll(Arrays.asList(getAllFields(klass.getSuperclass())));
    }
    return fields.toArray(new Field[] {});
  }

}
