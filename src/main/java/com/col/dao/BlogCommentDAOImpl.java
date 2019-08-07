package com.col.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.col.model.Blog;
import com.col.model.BlogComment;

@Repository("blogCommentDAO")
@Transactional
@Component
public class BlogCommentDAOImpl implements BlogCommentDAO{

	@Autowired
	SessionFactory sessionFactory;
	
	public boolean addComment(BlogComment blogComment) {
		try {
			sessionFactory.getCurrentSession().save(blogComment);
			return true;
		}
		catch(Exception e) {
			return false;
		}
	}

	public boolean deleteComment(BlogComment blogComment) {
		try {
			sessionFactory.getCurrentSession().delete(blogComment);
			return true;
		}
		catch(Exception e) {
			return false;
		}
	}

	public BlogComment getBlogComment(int blogCommentId) {
		Session session=sessionFactory.openSession();
		BlogComment blogComment=(BlogComment)session.get(BlogComment.class, blogCommentId);
		session.close();
		return blogComment;
	}

	public List<BlogComment> listComments() {
		/*Session session=sessionFactory.openSession();
		Query query=session.createSQLQuery("select commenttext from blogcomment where blogid="+blogId);
		//query.setParameter("blogId",blogId);
		System.out.println("hello");
		List<BlogComment> blogCommentsList=query.list();
		session.close();
		return blogCommentsList;*/
		String hql="from blogcomment";
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<BlogComment> list=query.list();
		System.out.println("hello2");
		return list;
	}
	
}
