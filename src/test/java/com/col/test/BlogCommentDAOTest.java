package com.col.test;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.col.dao.BlogCommentDAO;
import com.col.model.BlogComment;

public class BlogCommentDAOTest {

	static BlogCommentDAO blogCommentDAO;
	
	@BeforeClass
	public static void executeFirst() {
		AnnotationConfigApplicationContext context
		=new AnnotationConfigApplicationContext();
		context.scan("com.col");
		context.refresh();
		blogCommentDAO=(BlogCommentDAO)context.getBean("blogCommentDAO");
	}
	
	@Ignore
	@Test
	public void addBlogCommentTest() {
		BlogComment comment=new BlogComment();
		comment.setCommentText("So...True");
		comment.setCommentDate(new Date().toString());
		comment.setBlogId(2);
		comment.setUserName("orchid#");
		assertTrue("cannot add the comment", blogCommentDAO.addComment(comment));
	}

	@Test
	public void listBlogCommentsTest() {
		List<BlogComment> list=blogCommentDAO.listComments();
		assertTrue("cannot retrieve th comments", list.size()>0);
		for(BlogComment comment:list) {
			System.out.println(comment.getUserName());
			System.out.println(comment.getCommentText());
		}
	}
}
