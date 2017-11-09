package com.ingesup.hibernate;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import com.ingesup.model.Machine;

@SuppressWarnings({"deprecation","unchecked","rawtypes"})
public class MachineManager {
	
	/**
	 * Intert in database the given machine
	 * @param machine
	 */
	public static void create(Machine machine){
		
		try {
			
			String queryString = "INSERT INTO MACHINE (id, machineIp, ram, cpu, storage, id_room) VALUES (" + machine.getId() + ", " + "\"" + machine.getMachineIp() + "\"" + ", " + machine.getRam() + ", " + machine.getCpu() + ", \"[]\", " + machine.getId_room() + ")" ;
			
			Query query = HibernateUtilMastere.getSession().createSQLQuery(queryString);
			
			query.executeUpdate();
			
		} catch(HibernateException e){
			e.printStackTrace();
		} finally {
			HibernateUtilMastere.cleanHibernateExchange();
		}
		
	}
	
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
	
	/**
	 * Return the machine attached to the given id
	 * @param id
	 * @return
	 */
	public static Machine getById(Integer id){
		
		try {
			
			Query query = HibernateUtilMastere.getSession().createQuery("from Machine where id=:machineId");
			query.setParameter("machineId", id);
			
			Machine machine = (Machine) query.uniqueResult();
			
			return machine;
		} catch (HibernateException e){
			e.printStackTrace();
			return null;
		}

	}
	
	/**
	 * Return the whole list of machine attached to the given park id
	 * @param parkId
	 * @return
	 */
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
	
	public static Integer getLast() {
		
		try{
			
			Query query = HibernateUtilMastere.getSession().createQuery("select max(id) from Machine");
			
			Integer machine = (Integer) query.uniqueResult();
			
			return machine;
			
		} catch(HibernateException e){
			e.printStackTrace();
			
			return null;
		} finally {
			HibernateUtilMastere.cleanHibernateExchange();
		}
		
	}

}
