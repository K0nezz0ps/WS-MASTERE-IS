package com.ingesup.manager;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import com.ingesup.hibernate.HibernateUtilMastere;
import com.ingesup.model.Machine;

public class MachineManager {
	
	/**
	 * Return the attached Machine for the given IP
	 * @param ip
	 * @return
	 */
	public static Machine getByIp(String ip){
		
		try {
			
			Query query = HibernateUtilMastere.getSession().createQuery("from Machine where machineIp=:machineIp");
			query.setParameter("machineIp", ip);
			
			Machine machine = (Machine) query.uniqueResult();
			
			return machine;
		} catch (HibernateException e){
			e.printStackTrace();
			return null;
		}

	}
	
	public static List<Machine> getAll(Integer parkId) {
		
		try {
		
			Query query = HibernateUtilMastere.getSession().createQuery("from Machine as m where m.id_room in (select r.id from Room as r where r.id_park=:parkId)");
			query.setParameter("parkId", parkId);
			
			List<Machine> machineList = (List<Machine>) query.list();
			
			return machineList;
		} catch(HibernateException e){
			e.printStackTrace();
			return null;
		}
		
	}
	
	/**
	 * Return the whole list of machine attached to the given room ids list
	 * @param roomIdsList
	 * @return
	 */
	public static List<Machine> getAllByRoomIds(List<Integer> roomIdsList) {
		
		try {
			
			Query query = HibernateUtilMastere.getSession().createQuery("from Machine where id_room in :roomIdsList");
			query.setParameterList("roomIdsList", roomIdsList);
			
			List<Machine> machineList = query.list();
			
			return machineList;
			
		} catch (HibernateException e){
			e.printStackTrace();
			return null;
		} finally {
			HibernateUtilMastere.cleanHibernateExchange();
		}

	}

}
