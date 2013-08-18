package com.telenoetica.jpa.test;

import static org.junit.Assert.fail;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.telenoetica.jpa.entities.DieselVisit;
import com.telenoetica.jpa.entities.Site;
import com.telenoetica.jpa.repositories.DieselVisitDAO;
import com.telenoetica.jpa.repositories.SiteDAO;

public class DieselVisitDaoTest extends BaseTest {

	@Autowired
	private DieselVisitDAO dao;

	@Autowired
	private SiteDAO siteDAO;

	@Test
	public void test() {

		List<Site> siteLiest = siteDAO.findAll();
		Date startDate;
		Date endDate;
		Calendar currentDate = Calendar.getInstance(); // Get the current date
		endDate = currentDate.getTime();
		currentDate.set(Calendar.DAY_OF_MONTH, Calendar.getInstance()
				.getActualMinimum(Calendar.DAY_OF_MONTH));
		startDate = currentDate.getTime();
		List<DieselVisit> result = dao.findBySiteAndCreatedAtBetween(
				siteLiest.get(1), startDate, endDate);
		System.out.println("RESULT     : " + result.get(0));
		fail("Not yet implemented");
	}
}
