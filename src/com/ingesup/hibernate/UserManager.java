package com.ingesup.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import com.ingesup.model.User;

@SuppressWarnings({"deprecation","rawtypes"})
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
	
	public static User getUser(String mail, String password){
		
		try{
			Query query = HibernateUtilMastere.getSession().createQuery("from User where mail=:mail and password=:password");
			query.setParameter("mail", mail);
			query.setParameter("password", password);
			
			User currentUser = (User) query.uniqueResult();
			
			return currentUser;
		} catch(HibernateException e){
			e.printStackTrace();
			return null;
		}
		
	}

}
