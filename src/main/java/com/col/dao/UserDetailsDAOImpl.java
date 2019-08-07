package com.col.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.col.model.UserDetails;

@Repository("userDAO")
@Transactional
@Component
public class UserDetailsDAOImpl implements UserDetailsDAO{

	@Autowired
	SessionFactory sessionFactory;
	
	public boolean registerUser(UserDetails user) {
		try {
			sessionFactory.getCurrentSession().save(user);
			return true;
		}
		catch(Exception e) {
			return false;
		}
		
	}

	public boolean updateProfile(UserDetails user) {
		try {
			sessionFactory.getCurrentSession().update(user);
			return true;
		}
		catch(Exception e) {
			return false;
		}
		
	}

	public UserDetails getUser(String userName) {
		Session session=sessionFactory.openSession();
		UserDetails user=session.get(UserDetails.class, userName);
		session.close();
		return user;
	}

	public UserDetails checkUser(String userName, String password) {
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("from UserDetails "
				+ "where username=:uname and password=:passwd");
		query.setParameter("uname", userName);
		query.setParameter("passwd", password);
		List<UserDetails> userList=query.list();
		if(userList.size()>0) {
			UserDetails user=userList.get(0);
			return user;
		}else {
			return null;
		}
	}

}
