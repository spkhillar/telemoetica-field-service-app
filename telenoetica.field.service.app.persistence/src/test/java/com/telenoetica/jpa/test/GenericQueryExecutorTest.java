package com.telenoetica.jpa.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import com.telenoetica.jpa.entities.Site;
import com.telenoetica.jpa.repositories.GenericQueryExecutorDAO;

public class GenericQueryExecutorTest extends BaseTest {

	@Autowired
	private GenericQueryExecutorDAO genericQueryExecutorDAO;

	@Test
	public void testGenericQuery() {
		/*
		 * List<User> userList =
		 * genericQueryExecutorDAO.executeQuery("from User where enabled=0"
		 * ,User.class); if(CollectionUtils.isNotEmpty(userList)){
		 * System.err.println("..User.."+userList.get(0)); }
		 */

		Page<Site> pages = genericQueryExecutorDAO.executeQuery("from Site",
				Site.class, 1, 10);
		printList(pages);

		pages = genericQueryExecutorDAO.executeQuery(
				"from Site where name like '%OY%'", Site.class, 2, 10);
		printList(pages);

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("state", "%OY%");
		pages = genericQueryExecutorDAO.executeQuery(
				"from Site where name like :state", Site.class, params, 2, 10);
		printList(pages);

	}

	public void printList(Page<?> data) {
		System.err.println("--------------------------------------------");
		System.err.println("...data..num=" + data.getNumber() + ":total="
				+ data.getTotalElements() + ":pages=" + data.getTotalPages());
		int i = 0;
		for (Object object : data) {
			i++;
			System.err.println(i + "...Data..." + object);
		}

	}
}
