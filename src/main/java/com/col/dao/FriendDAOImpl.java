package com.col.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.col.model.Friend;
import com.col.model.UserDetails;

@Repository("friendDAO")
@Transactional
@Component
public class FriendDAOImpl implements FriendDAO{

	@Autowired
	SessionFactory sessionFactory;
	
	public List<Friend> showFriendList(String userName) {
		Session session=sessionFactory.openSession();
		Query query=session.createSQLQuery("select * from friend "
				+ "where (username='"+userName+"' or friendusername='"+userName+"')"
				+ "and status='A'");
		//query.setParameter("uname", userName);
		//query.setParameter("funame", userName);
		List<Friend> friendList=(List<Friend>)query.list();
		session.close();
		return friendList;
	}

	public List<Friend> showPendingFriendList(String userName) {
		Session session=sessionFactory.openSession();
		Query query=session.createSQLQuery("select * from friend "
				+ "where (friendusername='"+userName+"' and atatus='P')");
		//query.setParameter("funame", userName);
		List<Friend> pendingFriendList=(List<Friend>)query.list();
		session.close();
		return pendingFriendList;
	}

	public List<UserDetails> showSuggestedFriend(String userName) {
		Session session=sessionFactory.openSession();
		Query query=session.createSQLQuery("select username from userdetails where username not in(select friendusername from "
				+ "friend where username='"+userName+"')and username not in(select username from friend "
						+ "where friendusername='"+userName+"' and status='A')and username!='"+userName+"'");
		List<String> userList=(List<String>)query.list();
		ArrayList<UserDetails> list=new ArrayList<UserDetails>();
		int count=0;
		while(count<userList.size()) {
			String username1=userList.get(count).toString().trim();
			System.out.println("username:"+username1);
			UserDetails user=session.load(UserDetails.class, username1);
			list.add(user);
			count++;
		}
		session.close();
		return list;
	}

	public boolean sendFriendRequest(Friend friend) {
		try {
			friend.setStatus("P");
			sessionFactory.getCurrentSession().save(friend);
			return true;
		}catch(Exception e) {
			return false;
		}
	}

	public boolean acceptFriendRequest(int friendId) {
		try {
			Session session=sessionFactory.openSession();
			Friend friend=session.get(Friend.class, friendId);
			session.close();
			friend.setStatus("A");
			sessionFactory.getCurrentSession().update(friend);
			return true;
		}catch(Exception e) {
			return false;
		}
	}

	public boolean deleteFriendRequest(int friendId) {
		try {
			Session session=sessionFactory.openSession();
			Friend friend=session.get(Friend.class, friendId);
			session.close();
			sessionFactory.getCurrentSession().delete(friend);
			return true;
		}catch(Exception e) {
			return false;
		}
	}

}
