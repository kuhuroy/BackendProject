package com.col.dao;

import java.util.List;

import com.col.model.Blog;

public interface BlogDAO {
	public boolean addBlog(Blog blog);
	public boolean deleteBlog(Blog blog);
	public boolean updateBlog(Blog blog);
	public List<Blog> listBlog();
	Blog getBlog(int blogId);
	public boolean incrementLikes(int blogId);
	public boolean incrementDislikes(int blogId);
	public boolean approvedBlog(Blog blog);
	public boolean rejectBlog(Blog blog);
}
