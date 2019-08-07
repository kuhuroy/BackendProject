package com.col.test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.col.dao.UserDetailsDAO;
import com.col.model.UserDetails;

public class UserDAOTest {

	static UserDetailsDAO userDAO;
	
	@BeforeClass
	public static void executeFirst() {
		AnnotationConfigApplicationContext context=
				new AnnotationConfigApplicationContext();
		context.scan("com.col");
		context.refresh();
		userDAO=(UserDetailsDAO)context.getBean("userDAO");
	}
	
	
	@Test
	public void registerUserTest() {
		UserDetails user=new UserDetails();
		user.setUserName("Theblackpearl");
		user.setPassword("Banik@123");
		user.setName("Minakshi Banik");
		user.setEmailId("banik.minakshi@gmail.com");
		user.setMobileNo("8109889468");
		user.setIsOnline("A");
		user.setRole("ROLE_USER");
		user.setStatus("A");
		assertTrue("cannot insert a data", userDAO.registerUser(user));
	}
	
	@Ignore
	@Test
	public void checkUserTest() {
		UserDetails user=userDAO.checkUser("bluesky", "Kuhu@123");
		assertNotNull("User cannot be found", user);
		System.out.println("name:"+user.getName()+":::");
		System.out.println("emailid:"+user.getEmailId()+":::");
		System.out.println("role:"+user.getRole()+":::");
	}
	@Ignore
	@Test
	public void updateProfileTest() {
		UserDetails user=userDAO.getUser("orchid#");
		user.setPassword("Bibhu@123");
		assertTrue("no such user found", userDAO.updateProfile(user));
	}
}
