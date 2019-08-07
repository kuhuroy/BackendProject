package com.col.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.col.dao.FriendDAO;
import com.col.model.Friend;
import com.col.model.UserDetails;

public class FriendDAOTest {

	static FriendDAO friendDAO;
	
	@BeforeClass
	public static void executeFirst() {
		AnnotationConfigApplicationContext context
		=new AnnotationConfigApplicationContext();
		context.scan("com.col");
		context.refresh();
		friendDAO=(FriendDAO)context.getBean("friendDAO");
	}
	
	@Ignore
	@Test
	public void sendFriendRequestTest() {
		Friend friend=new Friend();
		friend.setUserName("orchid#");
		friend.setFriendUserName("bluesky");
		assertTrue("no such friend found", friendDAO.sendFriendRequest(friend));
	}
	
	@Ignore
	@Test
	public void acceptFriendRequestTest() {
		assertTrue("cannot be accepted", friendDAO.acceptFriendRequest(21));
	}
	
	@Ignore
	@Test
	public void showFriendListTest() {
		List<Friend> friendList=friendDAO.showFriendList("bluesky");
		assertTrue("no friendlist found",friendList.size()>0);
		for(Friend f:friendList) {
			System.out.println(f.getUserName());
			System.out.println(f.getFriendUserName());
		}
	}
	@Ignore
	@Test
	public void showPendingFriendListTest() {
		List<Friend> pendingFriendList=friendDAO.showPendingFriendList("bluesky");
		assertTrue("no pending friends are there", pendingFriendList.size()>0);
		for(Friend f:pendingFriendList) {
			System.out.println(f.getUserName());
			System.out.println(f.getFriendUserName());
		}
	}
	//@Ignore
	@Test
	public void showSuggestedFriendTest() {
		List<UserDetails> list=friendDAO.showSuggestedFriend("orchid#");
		assertTrue("no suggested friend list", list.size()>0);
		for(UserDetails user:list) {
			System.out.println(user.getUserName());
			System.out.println(user.getName());
		}
	}
}
