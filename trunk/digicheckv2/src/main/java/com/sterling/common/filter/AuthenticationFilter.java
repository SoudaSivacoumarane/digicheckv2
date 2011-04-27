package com.sterling.common.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthenticationFilter implements Filter {	
	/** Nombre de variable de session para el usuario */
	private static final String USER_SESSION_NAME = "user";
	/** Accion de logout */
	private static final String LOGOUT_ACTION = "/index.xhtml";
	/** Accion de login */
	private static final String MAIN_ACTION = "/index.xhtml";
	/** Accion de error */
	private static final String ERROR_ACTION = "/error.xhtml";
	/** Accion de error */
	private static final String ERROR_404_ACTION = "/error_404.xhtml";
	/** Accion de error */
	private static final String ERROR_500_ACTION = "/error_500.xhtml";
	/** Accion de login */
	private static final String LOGIN_ACTION = "/loginUser.xhtml";
	/** Security actions exception */
	private Collection<String> securityActionsException;
		
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain filterChain) throws IOException, ServletException {
		//UserView userView = null;
		HttpServletRequest httpServletRequest = null;
		HttpServletResponse httpServletResponse = null;
		HttpSession httpSession =  null;
		String requestUri = null;
		
		String contextPath = null;
		
		
		httpServletRequest = (HttpServletRequest)servletRequest;
		httpServletResponse = (HttpServletResponse)servletResponse;
		contextPath = httpServletRequest.getContextPath();		
		httpSession =  httpServletRequest.getSession();
		//userView = (UserView)httpSession.getAttribute(USER_SESSION_NAME);
		requestUri= httpServletRequest.getServletPath();
		if(requestUri.startsWith("/javax.faces.resource")){
			requestUri = "/javax.faces.resource";
		}
		if( !securityActionsException.contains(requestUri) ){
		/*	if(  userView == null ){
				httpServletResponse.sendRedirect(new StringBuffer(contextPath).append(LOGOUT_ACTION).toString());
			}else{				
				filterChain.doFilter(servletRequest, servletResponse);
			}*/
		}else{
			if( requestUri.equals(MAIN_ACTION) ){
				//errorTmp = (String)httpSession.getAttribute(ERROR_MESSAGE);
				//httpSession.invalidate();
				//httpSession = httpServletRequest.getSession();
				//httpSession.setAttribute(ERROR_MESSAGE,errorTmp);
			}
			filterChain.doFilter(servletRequest, servletResponse);
		}
		
		
		
	}

	public void init(FilterConfig arg0) throws ServletException {
		securityActionsException = new ArrayList<String>(7);
		securityActionsException.add(LOGIN_ACTION);
		securityActionsException.add(LOGOUT_ACTION);
		securityActionsException.add(ERROR_ACTION);
		securityActionsException.add(ERROR_404_ACTION);
		securityActionsException.add(ERROR_500_ACTION);
		securityActionsException.add(MAIN_ACTION);
		securityActionsException.add("/javax.faces.resource");
		
	}	
}
