package com.ingesup.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ingesup.hibernate.HibernateUtilMastere;
import com.ingesup.hibernate.UserManager;

public class AuthenticationFilter implements Filter {

	/**
	 * Empty override function
	 */
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	/**
	 * Authentication Filter that check if the current connected user is authorized to continue
	 * If not connected, then the user is redirected to the AUTH Login Page
	 */
	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2) throws IOException, ServletException {

		HttpServletRequest request   = (HttpServletRequest) arg0;
		
		// 1. Checking if the current connected user can continue here
		if(!isValidSession(request)){
			// 1.1 Redirect to login if necessary 
			((HttpServletResponse) arg1).sendRedirect("/WS-CNS-AUTH/login");
			return;
		}
		
		// 2. Use the next filter in the FilterChain
		arg2.doFilter(arg0, arg1);
		
		// 3. Commit & Close Transac+Session
		this.cleanHibernateExchange();
		
	}

	/**
	 * Empty override function
	 */
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
	
	/**
	 * Check if the current session is authorized to continue
	 * Return the accorded boolean
	 * @param request
	 * @return
	 */
	public static boolean isValidSession(HttpServletRequest request) {
		
		// 1. Getting cookies
		Cookie[] cookieList = request.getCookies();
		
		// 2. Trying to find the "isConnected" cookie
		Cookie sessionCookie  = null;
		Cookie emailCookie    = null;
		
		if(cookieList != null)
			// ForEach cookie, trying to get the interested ones 
			for(Cookie currentCookie : cookieList){		
				switch(currentCookie.getName()){
					case "isConnected": sessionCookie = currentCookie;
										break;
					case "userEmail"  : emailCookie   = currentCookie;
										break;
					default: break;
				}
			}
		
		// 3. Checking that each previous wanted cookies are not null, then, checking if the user attached to the email & password exist
		if( sessionCookie == null 
				|| !sessionCookie.getValue().equals("true") 
				|| emailCookie == null 
				|| UserManager.getUser(emailCookie.getValue()) == null)
			return false;
		
		return true;
	}

}
