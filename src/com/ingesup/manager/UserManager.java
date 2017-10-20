package com.ingesup.manager;

import javax.persistence.Query;
import org.hibernate.HibernateException;
import com.ingesup.hibernate.HibernateUtil;
import com.ingesup.model.User;

public class UserManager {
	
	public static User get(User user) {
		
		try {
			
			Query query = HibernateUtil.getSession().createQuery("from User where email=:email and password=:password");
			query.setParameter("email", user.getEmail().toLowerCase());
			query.setParameter("password", user.getPassword());
			
			User aliveUser = null;
			
			try {
				aliveUser = (User) query.getSingleResult();
			} catch (Exception e){
				e.printStackTrace();
			}

			return aliveUser;
		} catch (HibernateException e){
			e.printStackTrace();
			return null;
		}
	}

}
