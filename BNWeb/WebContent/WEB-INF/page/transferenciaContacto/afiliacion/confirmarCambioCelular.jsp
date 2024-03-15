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
		var celularUsu = <c:out value="${celular}"></c:out>;
		console.log("celularUsu:::"+celularUsu);
		
		var form = document.frmIniciarCambio;		
		form.action="<%=request.getContextPath()%>/trasferenciaContacto.do?metodo=verificarCelular";
		form.submit();
		
		
	}
	
	
	function cancelar(){
		var form = document.frmIniciarCambio;
		form.action="<%=request.getContextPath()%>/consulta.do?metodo=iniciar";
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
<form name="frmIniciarCambio" method="post" >
<input type="hidden" name="metodo">
<input type="hidden" name="transaccion" value="">

<div id="contenidos-informativos">
	<div id="consulta-datos">
		</br>
		</br>
		<h2>CAMBIO DE CELULAR REGISTRADO</h2>
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
				¿Est&aacute;s seguro de cambiar tu n&uacute;mero de celular?, al darle click en  continuar se actualizar&aacute; tus n&uacute;mero de celular registrado.			
	  		</span>
	  	</div>
			
			</br>
			</br>
		<div class="boton">	
			<input type="button" value="CANCELAR" id="boton" onclick="javascript:cancelar();"/>
			<input type="button" value="SI, CONTINUAR" id="boton" onclick="javascript:continuar();"/>
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
