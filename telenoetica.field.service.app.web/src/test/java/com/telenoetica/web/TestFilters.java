package com.telenoetica.web;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import com.telenoetica.jpa.entities.User;
import com.telenoetica.web.util.JqGridFilter;
import com.telenoetica.web.util.JqGridFilter.Rule;
import com.telenoetica.web.util.JqGridFilterQueryBuilder;

public class TestFilters {
	  @Test
	  public void testFilters() {

	    ArrayList<Rule> ruleList = new ArrayList<Rule>();

	    Rule jqRrule = new Rule("", "id", "eq", "23");
	    ruleList.add(jqRrule);
	    jqRrule = new Rule("", "userName", "eq", "shiv");
	    ruleList.add(jqRrule);
	    jqRrule = new Rule("", "createdAt", "ne", "shiv");
	    ruleList.add(jqRrule);
	    jqRrule = new Rule("", "enabled", "gt", "true");
	    ruleList.add(jqRrule);
	    jqRrule = new Rule("", "version", "lt", "0");
	    ruleList.add(jqRrule);
	    jqRrule = new Rule("", "firstName", "bw", "akshat");
	    ruleList.add(jqRrule);
	    jqRrule = new Rule("", "lastName", "ew", "khillar");
	    ruleList.add(jqRrule);
	    jqRrule = new Rule("", "lastName", "cn", "khillar123");
	    ruleList.add(jqRrule);

	    jqRrule = new Rule("", "roleId", "eq", "1");
	    ruleList.add(jqRrule);

	    JqGridFilter jqgridFilter = new JqGridFilter();
	    jqgridFilter.setRules(ruleList);
	    jqgridFilter.setGroupOp("AND");
	    Map<String, Object> paramObject = new LinkedHashMap<String, Object>();
	    String groupOperator = " " +jqgridFilter.getGroupOp() + " ";
	    List<String> predicateString = new ArrayList<String>();
	    List<Rule> excludedRules =
	        JqGridFilterQueryBuilder.getJpqlPredicate(jqgridFilter, new String[] { "roleId" }, paramObject,predicateString, User.class,groupOperator);

	   // System.out.println("EXCLUDED:::"+excludedRules.get(0));
	    
	    System.out.println(paramObject.size()+"..MAP.."+paramObject);
	    
	    System.out.println("..predicate string"+predicateString.get(0));
	    List<String> excludedPridicates = new ArrayList<String>(); 
	    int paramSize = paramObject.size();
	    String exeRule = null;
	    for (Rule rule : excludedRules) {
	      Rule newRule = new Rule(null, "userRole.role.id", rule.getOp(), rule.getData());
	      exeRule = JqGridFilterQueryBuilder.createRuleString(newRule, paramObject, paramSize, Long.class);
	      excludedPridicates.add(exeRule);
	    }

	    String predicateFirstPart = predicateString.get(0);
	    String predicateSecondPart = StringUtils.join(excludedPridicates,groupOperator);
	    String finalPredicate = predicateFirstPart + groupOperator + predicateSecondPart;
	    System.out.println("..Final"+finalPredicate);

	    
	    System.out.println(paramObject.size()+"..MAP.."+paramObject);
	  }

}
