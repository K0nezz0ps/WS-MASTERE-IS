package com.ingesup.manager;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.ingesup.hibernate.HibernateUtilMastere;
import com.ingesup.model.Park;
import com.ingesup.model.User;

public class ParkManager {

	/**
	 * Create a new Park line in database
	 * @param parkItem
	 */
	public static void create(Park parkItem){
		
		try {
			
			HibernateUtilMastere.getSession().save(parkItem);
			
		} catch(HibernateException e){
			e.printStackTrace();
		}
		
	}
	
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
	
	/**
	 * Return the list park with a given User id, as a java.util.List
	 * @return
	 */
	public static List<Park> getParkListWithUserId(int id){
		
		try {
			Query query = HibernateUtilMastere.getSession().createQuery("from Park where id_user=:uId");
			query.setParameter("uId", id);
			
			List<Park>list = (List<Park>) query.list();

			return list;
			
		} catch (HibernateException e){
			e.printStackTrace();
			return null;
		}
		
	}
}
