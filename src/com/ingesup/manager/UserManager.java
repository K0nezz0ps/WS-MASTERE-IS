package com.ingesup.manager;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;

import com.ingesup.hibernate.HibernateUtilMastere;
import com.ingesup.model.User;

public class UserManager {
	
	/**
	 * Return the id of a user with a given User mail, as a User
	 * @return
	 */
	public static User getUser(String mail){
		
		try {
			Query query = HibernateUtilMastere.getSession().createQuery("from User where mail=:uMail");
			query.setParameter("uMail", mail);
			
			User idUser = (User) query.uniqueResult();

			return idUser;
			
		} catch (HibernateException e){
			e.printStackTrace();
			return null;
		}
		
	}

}
