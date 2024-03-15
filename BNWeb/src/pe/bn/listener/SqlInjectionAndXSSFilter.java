package pe.bn.listener;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * @author reddy
 *  This class goes through the Request parameters and looks for
 *  any suspicious inputs which could be a SQL injected or some java
 *  script some user is trying to inject.
 */

public class SqlInjectionAndXSSFilter implements Filter {
 private static Logger LOGGER = LogManager.getLogger(SqlInjectionAndXSSFilter.class);
 private static Map<String,String>  excludeFieldsMap = new HashMap<String,String>();
 
 public void init(FilterConfig config) throws ServletException {
  //Looking for sql-xss-exclusion-filter.properties
  String propertiesFilePath = config.getInitParameter("properties_file");
  if (propertiesFilePath == null || propertiesFilePath.length() == 0) {
   LOGGER.warn("The properties_file parameter in web.xml for SqlInjectionAndXSSFilter is not specified.");

  } else {
    
      //If you  want to inspect every request parameter
      Properties props = new Properties();
      try {
   props.load(getClass().getClassLoader().getResourceAsStream(propertiesFilePath));

   Iterator<Object> itr = props.keySet().iterator();   

       while (itr.hasNext()) {
        String key = (String)itr.next();
        String value = (String)props.get(key);
        LOGGER.debug("Adding key="+key +" value="+value);
        excludeFieldsMap.put(key,value);
       }

       

       //Send this map to SqlInjectionAndXSSRequestWrapper. I made it static intentionally.
       SqlInjectionAndXSSRequestWrapper.excludeFieldsMap = excludeFieldsMap;
       
            } catch (IOException e) {
                LOGGER.fatal("Could not load properties file: " + propertiesFilePath, e);
                throw new ServletException("Could not load properties file: " + propertiesFilePath);
            }
  }
    }
 
 public void destroy() {



 }



 public void doFilter(ServletRequest request, ServletResponse response,

   FilterChain chain) throws IOException, ServletException {
	
   chain.doFilter(new SqlInjectionAndXSSRequestWrapper((HttpServletRequest) request),

    response);

 }
 
 

}


