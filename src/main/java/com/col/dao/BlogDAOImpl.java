package com.col.dao;

import java.util.List;


import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.col.model.Blog;

@Repository("blogDAO")
@Transactional
@Component
public class BlogDAOImpl implements BlogDAO{

	@Autowired
	SessionFactory sessionFactory; 
	public boolean addBlog(Blog blog) {
		try {
			sessionFactory.getCurrentSession().save(blog);
			return true;
		}
		catch(Exception e) {
			return false;
		}
	}

	public boolean deleteBlog(Blog blog) {
		
		try {
			sessionFactory.getCurrentSession().delete(blog);
			return true;
		}
		catch(Exception e) {
			return false;
		}
	}

	public boolean updateBlog(Blog blog) {
		try {
			sessionFactory.getCurrentSession().update(blog);
			return true;
		}
		catch(Exception e) {
			return false;
		}
	}

	public List<Blog> listBlog() {
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("from blog");
		List<Blog> blogList=query.list();
		session.close();
		return blogList;
	}

	public Blog getBlog(int blogId) {
		Session session=sessionFactory.openSession();
		Blog blog=(Blog)session.get(Blog.class, blogId);
		session.close();
		return blog;
	}

	public boolean incrementLikes(int blogId) {
		try {
			Blog blog=this.getBlog(blogId);
			blog.setLikes(blog.getLikes()+1);
			sessionFactory.getCurrentSession().update(blog);
			return true;
		}catch(Exception e) {
			return false;
		}
	}

	public boolean incrementDislikes(int blogId) {
		
		try {
			Blog blog=this.getBlog(blogId);
			blog.setDislikes(blog.getDislikes()+1);
			sessionFactory.getCurrentSession().update(blog);
			return true;
		}catch(Exception e) {
			return false;
		}
	}

	public boolean approvedBlog(Blog blog) {
		try {
			blog.setStatus("A");
			sessionFactory.getCurrentSession().update(blog);
			return true;
		}catch(Exception e) {
			return false;
		}
	}

	public boolean rejectBlog(Blog blog) {
		try {
			blog.setStatus("NA");
			sessionFactory.getCurrentSession().update(blog);
			return true;
		}catch(Exception e) {
			return false;
		}
	}
	
}
