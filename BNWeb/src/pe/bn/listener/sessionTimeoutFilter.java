package pe.bn.listener;

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

public class sessionTimeoutFilter implements Filter {
	
	private FilterConfig filterConfig = null;

	
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
}
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)throws IOException, ServletException {
			
		filterChain.doFilter(request, response);

	

	}
	
	public void destroy() {

	}
}
