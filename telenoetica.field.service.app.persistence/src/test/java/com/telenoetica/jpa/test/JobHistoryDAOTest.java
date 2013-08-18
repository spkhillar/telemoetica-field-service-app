package com.telenoetica.jpa.test;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.time.DateUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.telenoetica.jpa.entities.JobHistory;
import com.telenoetica.jpa.entities.JobStatus;
import com.telenoetica.jpa.repositories.GenericQueryExecutorDAO;
import com.telenoetica.jpa.repositories.JobHistoryDAO;

public class JobHistoryDAOTest extends BaseTest{

	@Autowired
	private JobHistoryDAO jobHistoryDAO;

	@Autowired
	private GenericQueryExecutorDAO genericQueryExecutorDAO;

	@Test
	public void testJobHistory(){

		Date date = new Date();
		JobHistory jobHistory = new JobHistory("TestJob", "JOB Description", date, null, JobStatus.RUNNING);

		JobHistory jobHistory1 = jobHistoryDAO.save(jobHistory);

		JobHistory jobHistory2 = jobHistoryDAO.findOne(jobHistory1.getId());

		System.err.println(jobHistory2.getId()+"..jobHistory2..."+jobHistory2.getStartTime());

		jobHistory2.setJobStatus(JobStatus.COMPLETED);
		date = DateUtils.addMinutes(date, 5);
		jobHistory2.setEndTime(date);
		jobHistory1 = jobHistoryDAO.saveAndFlush(jobHistory2);
		System.err.println(jobHistory1.getId()+"..jobHistory1 after update..."+jobHistory1.getEndTime());

		String ejbql ="from JobHistory where date(startTime) = :param0";

		Map<String, Object> param = new HashMap<String, Object>(1);
		param.put("param0", date);

		List<JobHistory> dateList = genericQueryExecutorDAO.executeProjectedQuery(ejbql,param);

		System.err.println("..Date.."+dateList.get(0));

	}
}
