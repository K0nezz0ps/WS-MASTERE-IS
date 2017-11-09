package com.ingesup.hibernate;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.ingesup.model.Room;

@SuppressWarnings({"deprecation","unchecked","rawtypes"})
public class RoomManager {
	
	/**
	 * Return the room attached to the given id
	 * @param roomId
	 * @return
	 */
	public static Room get(Integer roomId){
		
		try {
			Query query = HibernateUtilMastere.getSession().createQuery("from Room where id=:roomId");
			query.setParameter("roomId", roomId);
			
			Room currentRoom = (Room) query.uniqueResult();
			
			return currentRoom;
			
		} catch(HibernateException e){
			e.printStackTrace();
			return null;
		} finally {
			HibernateUtilMastere.cleanHibernateExchange();
		}
	}
	
	/**
	 * Return the whole list of room attached to the given park id
	 * @param parkId
	 * @return
	 */
	public static List<Room> getAllRoom(Integer parkId){
		
		try {
			
			Query query = HibernateUtilMastere.getSession().createQuery("from Room where id_park=:parkId");
			query.setParameter("parkId", parkId);
			
			List<Room> roomList = query.list();
			
			return roomList;
			
		} catch(HibernateException e){
			e.printStackTrace();
			return null;
		} finally {
			HibernateUtilMastere.cleanHibernateExchange();
		}
	}
	
	/**
	 * Delete the room attached to the given roomId Integer
	 * @param roomId
	 */
	public static void delete(Integer roomId){
		
		try{
			
			Query query = HibernateUtilMastere.getSession().createQuery("from Room where id=:id");
			query.setParameter("id", roomId);
			
			Room currentRoom = (Room) query.uniqueResult();
			
			if(currentRoom != null)
				HibernateUtilMastere.getSession().delete(currentRoom);
			
		} catch(HibernateException e){
			
		} finally {
			HibernateUtilMastere.cleanHibernateExchange();
		}
		
	}

}
