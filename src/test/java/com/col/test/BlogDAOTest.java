package com.col.test;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.col.dao.BlogDAO;
import com.col.model.Blog;

public class BlogDAOTest {

	static BlogDAO blogDAO;
	@BeforeClass
	public static void executeFirst()
	{
		AnnotationConfigApplicationContext context
		=new AnnotationConfigApplicationContext();
		context.scan("com.col");
		context.refresh();
		blogDAO=(BlogDAO)context.getBean("blogDAO");
		//System.out.println("blogdao obj created==========");
	}
	
	@Test
	public void addBlogTest() {
		Blog blog=new Blog();
		//blog.setBlogId(1001);
		blog.setBlogName("codeData");
		blog.setBlogContent("Programming with relavent information");
		blog.setCreateDate(new Date());
		blog.setLikes(10);
		blog.setDislikes(0);
		blog.setStatus("A");
		blog.setUserName("orchid#");
		assertTrue("Problem in adding the blog", blogDAO.addBlog(blog));
	}

}
