package com.col.dao;

import java.util.List;

import com.col.model.BlogComment;

public interface BlogCommentDAO {
	boolean addComment(BlogComment blogComment);
	boolean deleteComment(BlogComment blogComment);
	BlogComment getBlogComment(int blogCommentId);
	List<BlogComment> listComments();
}
