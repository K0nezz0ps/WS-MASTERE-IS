package com.ingesup.filter;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import com.ingesup.hibernate.HibernateUtilAuth;
import com.ingesup.hibernate.HibernateUtilMastere;

public class HibernateFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2) throws IOException, ServletException {

		HttpServletRequest request   = (HttpServletRequest) arg0;
		
		// 1. Getting the Session value that define if a session is alive
		Cookie[] cookieList = request.getCookies();
		
		Cookie requestCookie = null;
		if(cookieList != null)
			for(Cookie currentCookie : cookieList){
				if(currentCookie.getName().equals("isConnected"))
					requestCookie = currentCookie;
			}
		
		// 2. If no user connected, redirect to the login page
		if( requestCookie == null || !requestCookie.getValue().equals("true") ){
			((HttpServletResponse) arg1).sendRedirect("/WS-CNS-AUTH/auth/login");
			return;
		}
		
		this.cleanHibernateExchange();
		
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
			if(HibernateUtilMastere.getSession().getTransaction().isActive())
				HibernateUtilMastere.getSession().getTransaction().commit();
		} catch (Exception e){
			HibernateUtilMastere.getSession().getTransaction().rollback();
			e.printStackTrace();
		} finally {
			if(HibernateUtilMastere.getSession().isOpen())
				HibernateUtilMastere.getSession().close();
		}
	}

}
