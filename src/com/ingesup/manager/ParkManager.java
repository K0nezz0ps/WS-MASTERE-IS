package com.ingesup.manager;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ingesup.hibernate.HibernateUtilMastere;
import com.ingesup.model.Park;
import com.ingesup.model.User;

public class ParkManager {

	public static Park get(String parkName){
		
		try {
			
			Query query = HibernateUtilMastere.getSession().createQuery("from Park where name=:parkName");
			query.setParameter("parkName", parkName);
			
			Park getPark = (Park) query.uniqueResult();
			
			return getPark;
			
		} catch(HibernateException e){
			e.printStackTrace();
			return null;
		} finally {
			HibernateUtilMastere.cleanHibernateExchange();
		}
		
	}
	
	/**
	 * Create a new Park line in database
	 * @param parkItem
	 */
	public static void create(Park parkItem){
		
		try {
			
			HibernateUtilMastere.getSession().save(parkItem);

		} catch(HibernateException e){
			e.printStackTrace();
		} finally {
			HibernateUtilMastere.cleanHibernateExchange();
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
	
	/**
	 * Return the Park attached to the given id, else null
	 * @param parkId
	 * @return
	 */
	public static Park getPark(Integer parkId){
		
		try {
			
			Query query = HibernateUtilMastere.getSession().createQuery("from Park where id=:parkId");
			query.setParameter("parkId", parkId);
			
			Park park = (Park) query.uniqueResult();
			
			return park;
			
		} catch(HibernateException e){
			e.printStackTrace();
			return null;
		} finally {
			HibernateUtilMastere.cleanHibernateExchange();
		}
		
	}
}
