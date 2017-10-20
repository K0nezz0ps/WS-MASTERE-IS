package com.ingesup.manager;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;

import com.ingesup.hibernate.HibernateUtil;
import com.ingesup.model.Machine;

public class MachineManager {
	
	/**
	 * Return the attached Machine for the given IP
	 * @param ip
	 * @return
	 */
	public static Machine getByIp(String ip){
		
		try {
			
			Query query = HibernateUtil.getSession().createQuery("from Machine where machineIp=:machineIp");
			query.setParameter("machineIp", ip);
			
			Machine machine = (Machine) query.uniqueResult();
			
			return machine;
		} catch (HibernateException e){
			e.printStackTrace();
			return null;
		}

	}

}
