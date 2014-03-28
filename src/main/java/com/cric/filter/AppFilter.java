package com.cric.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class AppFilter
 */
public class AppFilter implements Filter {

    /**
     * Default constructor. 
     */
    public AppFilter() {
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		String url = ((HttpServletRequest)request).getRequestURI();
		
		if(skip(url)){
			chain.doFilter(request, response);
			return;
		}
		
		HttpSession s = ((HttpServletRequest)request).getSession();
		
		if( s.getAttribute("uname") == null ){
			((HttpServletResponse)response).sendRedirect("login.htm");
		}
		chain.doFilter(request, response);
	}
	
	private boolean skip(String url){
		if(url.endsWith("login.htm") 
		|| url.endsWith("validate.htm")
		|| url.endsWith(".css")
		|| url.endsWith(".js")
		){
			return true;
		}
		return false;
	}
	
	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}
	

}
