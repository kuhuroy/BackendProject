package com.col.dao;

import com.col.model.UserDetails;

public interface UserDetailsDAO {
	public boolean registerUser(
			UserDetails user);
	public boolean updateProfile(UserDetails user);
	public UserDetails getUser(String userName);
	public UserDetails checkUser(String userName,
			String password);
}
