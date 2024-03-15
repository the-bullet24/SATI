<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld"	prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld"	prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld"	prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld"				prefix="c"%>
<HTML>
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">

<meta name="GENERATOR" content="IBM Software Development Platform"/>
<meta content="no-cache" http-equiv="pragma"/>
<meta content="no-cache" http-equiv="cache-control"/>
<script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/js/control.js"></script>


<script language="JavaScript" src="<%=request.getContextPath()%>/js/util.js"></script>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/resetearcss.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/tipografias.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bn-estilos.css" />  

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/select.css" />

<script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/select.js"></script>

<SCRIPT language="javascript">
		
	function continuar(){
		var form = document.frmIniciarAfiliacion;
		form.action="<%=request.getContextPath()%>/trasferenciaContacto.do?metodo=cuentaEnlazar";
		form.submit();
	}
	
	
	function datosAfiliados(){
		var form = document.frmIniciarAfiliacion;
		form.action="<%=request.getContextPath()%>/trasferenciaContacto.do?metodo=datosAfiliacion";
		form.submit();
	}
	
	<%-- function anularAfiliacionClaveSms(){
		var form = document.frmIniciaActivarClaveSms;
		form.action="<%=request.getContextPath()%>/claveSMSDesafilia.do?metodo=iniciarDesafiliacion";
		form.submit();
	}
	 --%>
	
<%-- 	function activarClaveSms(){
		var form = document.frmIniciaActivarClaveSms;

		if($('[name="typeToken"]').val()=='7'){
			var url="<%=request.getContextPath()%>/claveSMSActiva.do?metodo=mostrarActivarClaveSms";
			parent.frames.Cuerpo.location.href=url;	
			
		}else{
			form.action="<%=request.getContextPath()%>/claveSMSActiva.do?metodo=mostrarActivarClaveSms";
			form.submit();
		}
	} --%>

</SCRIPT>
<TITLE>tran_int_ah.html</TITLE>
</HEAD>
<body onselectstart="return false" 
ondragstart="return false" 
oncontextmenu="return false"  
onKeyDown="return cancelRefresh(event);">
<form name="frmIniciarAfiliacion" method="post" >
<input type="hidden" name="metodo">
<input type="hidden" name="transaccion" value="">

<div id="contenidos-informativos">
	<div id="consulta-datos">
		</br>
		</br>
		<div style="text-align:center;LINE-HEIGHT:30px;">
			<span style="width:230px; font-family:daxcompact-regularregular;
					     font-weight:bold; font-size:30px;text-align:center;">
				Af&iacute;liate a transferencias
	  		</span>
	  		</br>
	  		<span style="width:230px; font-family:daxcompact-regularregular;
					     font-weight:bold; font-size:30px;text-align:center;">
				interbancarias a contactos
	  		</span>
	  	</div>
		</br>
		</br>			
		<center>
			<div class="formEstandar">
				<table cellspacing="0" cellpadding="0">
					<tr>
						<td align="center" >
							<img border="0" src="<%=request.getContextPath()%>/imagenes/bn/contacto_ico_3.png" 
							width="200px" 
							height="200px"/>
						</td>
					</tr>

					
				</table>

				<div class="clear cincopx"></div>

			
			</div>
			
			</br>
		<div style="text-align:center;LINE-HEIGHT:20px;">
			<span style="width:100px; font-family:daxcompact-regularregular; font-weight:bold;
					     font-size:15px;text-align:center;">
				Al afiliarte podr&aacute;s recibir transferencias				
	  		</span></br>
	  		<span style="width:100px; font-family:daxcompact-regularregular; font-weight:bold;
					     font-size:15px;text-align:center;">
				
				interbancarias a tu n&uacute;mero de celular
	  		</span>
	  	</div>
			
			</br>
			</br>
		<div class="boton">	
			
			<input type="button" value="SIGUIENTE" id="boton" onclick="javascript:continuar();"/>
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

</form>
</BODY>
</HTML>
