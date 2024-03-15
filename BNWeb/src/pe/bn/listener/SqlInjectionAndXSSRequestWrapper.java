package pe.bn.listener;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * @author reddy
 * Used by the filter to remove any potential code sent as input parameter which has
 * XSS (Cross site scripting) and
 * More at :
 * http://ha.ckers.org/xss.html
 * http://www.symantec.com/connect/articles/detection-sql-injection-and-cross-site-scripting-attacks  
 *
 */

public class SqlInjectionAndXSSRequestWrapper extends HttpServletRequestWrapper {

 private static Logger logger = LogManager.getLogger(SqlInjectionAndXSSRequestWrapper.class);


 //The filter will set this map

 public static  Map<String,String>  excludeFieldsMap = new HashMap<String,String>();

 

 public SqlInjectionAndXSSRequestWrapper(HttpServletRequest servletRequest) {

  super(servletRequest);

 }



 public String[] getParameterValues(String parameter) {

  String[] values = super.getParameterValues(parameter);

  if (values == null) {

   return null;

  }

  int count = values.length;

  String[] encodedValues = new String[count];

  for (int i = 0; i < count; i++) {

   encodedValues[i] = replaceXSSAndSqlInjection(values[i],parameter);

  }

  return encodedValues;

  /*

  for (int i = 0; i < count; i++) {

   checkXSSAndSqlInjectionPresence(parameter,values[i]);

  }

  return values;

  */

 }



 public String getParameter(String parameter) {

  String value = super.getParameter(parameter);

  if (value == null) {

   return null;

  }

  return replaceXSSAndSqlInjection(value,parameter);

  //checkXSSAndSqlInjectionPresence(parameter,value);

  //return value;

 }



 public String getHeader(String name) {

  String value = super.getHeader(name);

  if (value == null){

   return null;

  }

  return replaceXSSAndSqlInjection(value,name);

 }

 

 /**

  * If its finds any XSS injection it will replace the values with html equivalent and
  * and for SQL injection it will replace few sql key words (insert, delete...etc)
  * @param value
  * @return
  */

 public static String replaceXSSAndSqlInjection(String value, String fieldName) {

  //The key and value will be same

  //Eg:jsf_tree_64=jsf_tree_64  - See sql-xss-exclusion-filter.properties

  //SO here we ignore any fields that we feel should not be inspeacted by this filter..

  String fieldToExclude = excludeFieldsMap.get(fieldName);

  if((fieldToExclude != null) &&(fieldToExclude.equalsIgnoreCase(fieldName))){

   logger.debug("The field name:"+ fieldName +" should not be inspected by SqlInjectionAndXSSFilter.");

   return value;

  }

  

  String orgValue = new String(value);

  //No < and > as it could be for some sql.

  value = value.replaceAll("<", "&lt;").replaceAll(">", "&gt;");

  //No () brackets as part of data....

  value = value.replaceAll("\\(", "&#40;").replaceAll("\\)", "&#41;");

  //Handle any apostrophe.  Can a name have this ??

  value = value.replaceAll("'", "&#39;");

  //Any java script stuff.

  value = value.replaceAll("eval\\((.*)\\)", "");

  value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']","\"\"");

  value = value.replaceAll("script","");



  /*

   * This signature first looks out for the = sign or its hex equivalent (%3D).
   * It then allows for zero or more non-newline characters,
   * and then it checks for the single-quote, the double-dash or the semi-colon.
   * Detect either the hex equivalent of the single-quote, the single-quote itself or
   * the presence of the double-dash. These are SQL characters for MS SQL Server and Oracle,
   * which denote the beginning of a comment, and everything that follows is ignored.
   * See more info at
   * http://www.symantec.com/connect/articles/detection-sql-injection-and-cross-site-scripting-attacks
   * Regex for detecting SQL Injection meta-characters
   */

  value = value.replaceAll("/((\\%3D)|(=))[^\\n]*((\\%27)|(\')|(\\-\\-)|(\\%3B)|(;))/i","");

  

  /*

   * Regex for detecting SQL Injection with the UNION keyword
   *
   * (\%27)|(\') - the single-quote and its hex equivalent
   union - the keyword union
   */

  value = value.replaceAll("/((\\%27)|(\'))union/ix","");

  /*
   */

  value = value.replaceAll("/\\w*((\\%27)|(\\'))((\\%6F)|o|(\\%4F))((\\%72)|r|(\\%52))/ix",""); 

  
  value = value.replaceAll("insert|update|delete|having|drop|(\'|%27).(and|or).(\'|%27)|(\'|%27).%7C{0,2}|%7C{2}","");  

 
  value = value.replaceAll("/((\\%3C)|<)((\\%69)|i|(\\%49))((\\%6D)|m|(\\%4D))((\\%67)|g|(\\%47))[^\n]+((\\%3E)|>)/I","");

  

  if(logger.isDebugEnabled()){

   //Lets print only if debug is enable and if the values got changed.

   //Add as safety net

   if((orgValue != null) &&(value!= null) &&(!orgValue.equalsIgnoreCase(value))){

    logger.debug(" Value was changed by Filter from :"+ orgValue);

    logger.debug("          TO:"+ value);

   }

  }

  return value;

 }
}