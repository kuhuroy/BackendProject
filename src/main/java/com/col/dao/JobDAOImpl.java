package com.col.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.col.model.Job;

@Repository("jobDAO")
@Transactional
public class JobDAOImpl implements JobDAO{

	@Autowired
	SessionFactory sessionFactory;
	
	public boolean publishJob(Job job) {
		try {
			sessionFactory.getCurrentSession().save(job);
			return true;
		}catch(Exception e) {
			return false;
		}
	}

	public boolean updateJob(Job job) {
		try {
			sessionFactory.getCurrentSession().update(job);
			return true;
		}catch(Exception e) {
			return false;
		}
	}

	public boolean deleteJob(Job job) {
		try {
			sessionFactory.getCurrentSession().delete(job);
			return true;
		}catch(Exception e) {
			return false;
		}
	}

	public List<Job> showListJobs() {
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("from job");
		List<Job> list=query.list();
		session.close();
		return list;
	}

	public Job getJob(int jobId) {
		Session session=sessionFactory.openSession();
		Job job=session.get(Job.class, jobId);
		session.close();
		return job;
	}
	
}
