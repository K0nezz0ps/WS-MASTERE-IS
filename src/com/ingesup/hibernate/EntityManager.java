package com.ingesup.hibernate;

import java.util.List;
import org.hibernate.HibernateException;

public abstract class EntityManager {
	
	public static <T> T create(T object) {
		
		try{

			HibernateUtilMastere.getSession().save(object);
			HibernateUtilMastere.getSession().refresh(object);

			return null;
			
		} catch (HibernateException e){
			e.printStackTrace();
			return null;
		} finally {
			HibernateUtilMastere.cleanHibernateExchange();
		}
	}
	
	public static <T> void create(List<T> listObject){

		try {

			for(T obj : listObject)
				HibernateUtilMastere.getSession().save(obj);

			return;
			
		} catch(HibernateException e){
			e.printStackTrace();
			return;
		} finally {
			HibernateUtilMastere.cleanHibernateExchange();
		}
		
	}
	
	public static <T> void update(T object){
		
		try {
			
			HibernateUtilMastere.getSession().update(object);

		} catch (HibernateException e){
			e.printStackTrace();
			return;
		} finally {
			HibernateUtilMastere.cleanHibernateExchange();
		}
	}

	public static <T> void update(List<T> listObject){
		
		try {
			
			for(T obj : listObject)
				HibernateUtilMastere.getSession().update(obj);

		} catch (HibernateException e){
			e.printStackTrace();
			return;
		} finally {
			HibernateUtilMastere.cleanHibernateExchange();
		}
	}
	
	public static <T> void delete(T object) {

		try {
			HibernateUtilMastere.getSession().delete(object);
		} catch (HibernateException e){
			e.printStackTrace();
			return;
		} finally {
			HibernateUtilMastere.cleanHibernateExchange();
		}
	}

}
