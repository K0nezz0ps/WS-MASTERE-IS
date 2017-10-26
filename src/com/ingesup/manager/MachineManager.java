package com.ingesup.manager;

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
			
			Query query = HibernateUtilMastere.getSession().createQuery("from Machine join Room on Machine.id_room=Room.id where Room.id_park=:parkId");
			query.setParameter("parkId", parkId);
			
			List<Machine> machineList = query.list();
			
			return machineList;
		} catch(HibernateException e){
			e.printStackTrace();
			return null;
		}
		
	}

}
