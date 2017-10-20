package com.ingesup.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ingesup.hibernate.HibernateUtil;

public class HibernateFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2) throws IOException, ServletException {

		HttpServletRequest request   = (HttpServletRequest) arg0;
		
		// 1. Getting the Session value that define if a session is alive
		boolean isConnected = request.getSession().getAttribute("isConnected") != null ? (boolean) request.getSession().getAttribute("isConnected") : false;
		
		// 2. If no user connected, redirect to the login page
		if( !isConnected ){
			((HttpServletResponse) arg1).sendRedirect("/WS-MASTERE-IS/auth/login");
			return;
		}
		
		// 3. Use the next filter in the FilterChain
		arg2.doFilter(arg0, arg1);
		
		// 4. Commit & Close Transac+Session
		this.cleanHibernateExchange();
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}
	
	/**
	 * Function that commit Transaction & close Session
	 */
	public void cleanHibernateExchange() {
		try{
			if(HibernateUtil.getSession().getTransaction().isActive())
				HibernateUtil.getSession().getTransaction().commit();
		} catch (Exception e){
			HibernateUtil.getSession().getTransaction().rollback();
			e.printStackTrace();
		} finally {
			if(HibernateUtil.getSession().isOpen())
				HibernateUtil.getSession().close();
		}
	}

}
