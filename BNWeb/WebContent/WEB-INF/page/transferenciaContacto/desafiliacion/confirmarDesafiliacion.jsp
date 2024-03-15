<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
<%@ taglib uri="/WEB-INF/struts-html.tld"	prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld"	prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld"	prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld"				prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"/>
<meta name="GENERATOR" content="IBM Software Development Platform"/>
<meta content="no-cache" http-equiv="pragma"/>
<meta content="no-cache" http-equiv="cache-control"/>
<script language="JavaScript" src="<%=request.getContextPath()%>/js/util.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/resetearcss.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/tipografias.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bn-estilos.css" />  
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/select.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/select.js"></script>
<title>tran_int_ah.html</title>
<SCRIPT language="javascript">
		
	function continuar(){
		var form = document.forms[0];
		frmContactoTransf.metodo.value  = 'constanciaDesafiliacion';
		frmContactoTransf.action='<%=request.getContextPath()%>/trasferenciaContacto.do';
		frmContactoTransf.submit();			
	}
	
	function cancelar(){
							
		frmContactoTransf.metodo.value  = 'iniciarDesafiliacion';		
		frmContactoTransf.action='<%=request.getContextPath()%>/trasferenciaContacto.do';
				
		frmContactoTransf.submit();
	}
	
</SCRIPT>

</HEAD>
<body onselectstart="return false" 
ondragstart="return false" 
oncontextmenu="return false"  
onKeyDown="return cancelRefresh(event);">

<html:form type="pe.bn.afiliacion.action.form.AfiliacionDatosContactoForm" 
action="/trasferenciaContacto.do" method="POST" >

<input type="hidden" name="metodo">
<input type="hidden" name="transaccion" value="">

<div id="contenidos-informativos">
	<div id="consulta-datos">
		</br>
		</br>
		<h2>DESAFILIACIÓN A TRANSFERENCIAS INTERBANCARIA A CONTACTOS</h2>
		</br>
		</br>			
		<center>
	
			
			</br>
		<div style=" 		
					     width: 230px;
					     height: 100px;
					     
					     " >
			<span style="font-family:Arial Narrow;
					     font-size:20px;
					     text-align:center; 
					     ">
				¿Est&aacute;s seguro de desafiliarte? Al hacerlo, ya no podr&aacute;s recibir transferencias de otros bancos a trav&eacute;s de tu n&uacute;mero de celular.
	  		</span>
	  	</div>
			
			</br>
			</br>
		<div class="boton">	
			<input type="button" value="CANCELAR" id="boton" onclick="javascript:cancelar();"/>
			<input type="button" value="SI, DESAFILIAR" id="boton" onclick="javascript:continuar();"/>
		</div> 
		
			
		</center>

	</div>
</div>

</br>
	<logic:messagesPresent>
			<div class="cysErrorMsg" style="text-align: left;">
				<html:errors/>
			</div>
	</logic:messagesPresent>

</html:form>
</BODY>
</HTML>
