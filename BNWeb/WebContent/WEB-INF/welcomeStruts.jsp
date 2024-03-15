<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<script language="javascript1.1">
 function ReloadCaptcha(){
 
	//var form = document.FrmCaptcha;
	//form.action="<%=request.getContextPath()%>/Welcome1.do?methodToCall=cargar";
	//form.submit();
	location.href=<%=request.getContextPath()%>/Welcome1.do?methodToCall=cargar";
 
}
	
</script>
<html:html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CAPTCHA</title>
        <link href="resources/css/stylesheet.css" rel="stylesheet" type="text/css" />       
        <script type="text/javascript" src="resources/js/init.js"></script>
    </head>
    <body style="background-color: white; margin: auto;" onload="fnReloadCaptcha()">
      
        <div class="box">
            <html:form styleId="FrmCaptcha" action="validar.do">
                <table class="captcha-box">
                	<tr>
															
								<td align="left" colspan="10">
										<div id="divATM"></div>
										<br>
								</td>
					</tr>	
                    <tr>
                        <td width="15%"><img alt="captcha"  id="captcha" name="captcha" src="resources/images/background.jpg" /></td>
                        <td width="85%"><input id="refresh" name="refresh" type="button"  value=""  class="refresh-button" onclick="ReloadCaptcha()" /></td>
                    </tr>
                    <tr>
                        <td><html:text property="captcha"  styleId="captcha" maxlength="5" styleClass="captcha-input"/></td>
                        <td> <html:errors property="captcha" /></td>
                    </tr>
                    <tr>
                        <td colspan="2" align="left"><html:submit value="validar" styleClass="validar-button" /> </td>
                    </tr>
                    <tr>
								<td class="info" colspan="2">
									<logic:messagesPresent message="true">
										<ul>
											<html:messages id="message" message="true">
												<li><bean:write name="message" /></li>
											</html:messages>
										</ul>
									</logic:messagesPresent>
								</td>
					</tr>
                </table>
                
            </html:form>
        </div>
    </body>
</html:html>
