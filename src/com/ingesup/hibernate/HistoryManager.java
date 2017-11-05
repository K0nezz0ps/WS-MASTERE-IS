package com.ingesup.hibernate;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;

import com.ingesup.model.History;

@SuppressWarnings({"deprecation","unchecked","rawtypes"})
public class HistoryManager {
	
	/**
	 * Create a new History line in database
	 * @param historyItem
	 */
	public static void create(History historyItem){
		
		try {
			
			HibernateUtilMastere.getSession().save(historyItem);
			HibernateUtilMastere.getSession().getTransaction().commit();
		} catch(HibernateException e){
			e.printStackTrace();
		}
		
	}

	/**
	 * Return the most recent line of history for each machine, as a java.util.List
	 * @return
	 */
	public static List<History> getRecentList(){
		
		try {
			
			Query query = HibernateUtilMastere.getSession().createQuery("FROM History h WHERE h.dateEvent = ( SELECT MAX( h1.dateEvent ) FROM History h1 WHERE h1.id_machine = h.id_machine)");
			
			List<History> list = (List<History>) query.list();
			
			return list;
			
		} catch (HibernateException e){
			e.printStackTrace();
			return null;
		}
		
	}
	public static Integer getlastidhistory(int id){
		
		try {
			
			Query query = HibernateUtilMastere.getSession().createQuery("SELECT max(id) FROM History WHERE id_machine="+id);
			
			return (Integer) query.getSingleResult();
			
		} catch (HibernateException e){
			e.printStackTrace();
			return null;
		}
		
	}
	
}
