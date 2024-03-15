<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<html:html>
    <head>
    <link href="resources/css/stylesheet.css" rel="stylesheet" type="text/css" />       
        <script type="text/javascript" src="resources/js/init.js"></script>
    </head>
    
    
    <body style="background-color: white; margin: auto;" onload="fnReloadCaptcha()">
      <img alt="captcha"  id="captcha" name="captcha" src="resources/images/background.jpg" />
    </body>
</html:html>
