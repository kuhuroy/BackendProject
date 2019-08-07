package com.col.test;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.col.dao.JobDAO;
import com.col.model.Job;

public class JobDAOTest {

	static JobDAO jobDAO;
	
	@BeforeClass
	public static void executeFirst() {
		AnnotationConfigApplicationContext context=
				new AnnotationConfigApplicationContext();
		context.scan("com.col");
		context.refresh();
		jobDAO=(JobDAO)context.getBean("jobDAO");
	}
	@Ignore
	@Test
	public void publishJobTest() {
		Job job=new Job();
		job.setJobDesc("Train Programming Skills");
		job.setJobProfile("Trainer");
		job.setPostDate(new Date());
		job.setQualification("Graduate,Post-Graduate");
		job.setSkills("C/C++,Java,JSP & Servlet, Oracle, Android"
				+ ", Spring & Hibernate, Pyhton, Angular JS, HTML5");
		job.setStatus("A");
		assertTrue("cannot add your job", jobDAO.publishJob(job));
	}
	@Test
	public void showListJobsTest() {
		List<Job> list=jobDAO.showListJobs();
		assertTrue("no data found", list.size()>0);
		for(Job job:list) {
			System.out.println(job.getJobProfile()+":::");
		}
	}
}
