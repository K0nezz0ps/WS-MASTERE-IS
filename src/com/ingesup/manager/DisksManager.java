package com.ingesup.manager;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;

import com.ingesup.hibernate.HibernateUtilMastere;
import com.ingesup.model.Disks;

public class DisksManager {
	/**
	 * Create a new History line in database
	 * @param historyItem
	 */
	public static void create(Disks disk){
		
		try {
			
			HibernateUtilMastere.getSession().save(disk);
			HibernateUtilMastere.getSession().getTransaction().commit();
		} catch(HibernateException e){
			e.printStackTrace();
		}
		
	}

	/**
	 * Return the most recent line of history for each machine, as a java.util.List
	 * @return
	 */
	public static List<Disks> getHitoryDisks(int id_history){
		
		try {
			
			Query query = HibernateUtilMastere.getSession().createQuery("SELECT * FROM disks where id_history="+id_history);
			
			List<Disks> list = (List<Disks>) query.list();
			
			return list;
			
		} catch (HibernateException e){
			e.printStackTrace();
			return null;
		}
		
	}
}
