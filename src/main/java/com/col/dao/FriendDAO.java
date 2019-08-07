package com.col.dao;

import java.util.List;

import com.col.model.Friend;
import com.col.model.UserDetails;

public interface FriendDAO {
	public List<Friend> showFriendList(String userName);
	public List<Friend> showPendingFriendList(String userName);
	public List<UserDetails> showSuggestedFriend(String userName);
	public boolean sendFriendRequest(Friend friend);
	public boolean acceptFriendRequest(int friendId);
	public boolean deleteFriendRequest(int friendId);
}
