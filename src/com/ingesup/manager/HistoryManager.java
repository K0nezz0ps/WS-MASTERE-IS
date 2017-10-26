package com.ingesup.manager;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;

import com.ingesup.hibernate.HibernateUtil;
import com.ingesup.model.History;

public class HistoryManager {
	
	/**
	 * Create a new History line in database
	 * @param historyItem
	 */
	public static void create(History historyItem){
		
		try {
			
			HibernateUtil.getSession().save(historyItem);
			
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
			
			Query query = HibernateUtil.getSession().createQuery("select h.* from Machine m join History h on h.id_machine=m.id and h.dateEvent=(select max(h1.dateEvent) from History h1 where h1.id_machine=h.id_machine)");
			
			List<History> list = (List<History>) query.list();
			
			return list;
			
		} catch (HibernateException e){
			e.printStackTrace();
			return null;
		}
		
	}
}
