<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld"	prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld"	prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld"	prefix="logic"%>
<HTML>
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<script language="JavaScript" src="<%=request.getContextPath()%>/js/util.js"></script>
<TITLE>deb_aut_db.html</TITLE>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/resetearcss.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/tipografias.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bn-estilos.css" />  
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/select.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/select.js"></script>

<SCRIPT language="javascript">
	function imprimir(){
		window.open('<%=request.getContextPath()%>/util.do?metodo=solicitarRefrendo&variableSesion=<%=pe.cosapi.common.ConstanteSesion.AFILIAR%>&idObjeto=refrendoAfiliacionBancaCelTrans',"BN","toolbar=0,location=0,width=530,height=500, scrollbars=yes, resizable=yes,  top=" + ((screen.height/2)-(500/2))+", left="+((screen.width/2)-(380/2)));
	}
	
	function enviar(){
		window.open('<%=request.getContextPath()%>/util.do?metodo=verMail&variableSesion=<%=pe.cosapi.common.ConstanteSesion.AFILIAR%>&idObjeto=mailAfiliacionBancaCelTransferencia',"BN","toolbar=0,location=0,width=600,height=580, scrollbars=yes, resizable=yes,  top=" + ((screen.height/2)-(500/2))+", left="+((screen.width/2)-(460/2)));
	}

function Verificar()
 {
if (window.event && window.event.keyCode == 116) {
    window.event.keyCode = 8;
  }
  
  if (window.event && window.event.keyCode == 8) {
    //window.event.cancelBubble = true;
   // window.event.returnValue = false;
    return false;
  }

var pressedKey = String.fromCharCode(event.keyCode).toLowerCase();  
  if (event.ctrlKey && (pressedKey == "n" || pressedKey == "r" || pressedKey == "u" ||  
    pressedKey == "q" || pressedKey == "w" || pressedKey == "i" ||   
    pressedKey == "o" || pressedKey == "p" || pressedKey == "a" ||  
    pressedKey == "h"))  
  {   alert("desabilitado");
      return false;
  }

 }
 
 function verPdf(){
	
		var form = document.frmAfiliacion;

      	form.action="<%=request.getContextPath()%>/util.do";
		form.metodo.value = 'verPDF';
		form.idObjeto.value = 'refrendoAfiliacionBancaCelTrans';
		form.variableSesion.value = '<%=pe.cosapi.common.ConstanteSesion.AFILIAR%>';
		form.titulo.value = 'AFILIACION DE FRECUENTES BANCA CEL';
		document.frmAfiliacion.submit();
}

</SCRIPT>
<TITLE>deb_aut_db.html</TITLE>
</HEAD>

<body  oncontextmenu="return false" onselectstart="return false" ondragstart="return false"  onKeyDown="return cancelRefresh(event)" >
<form name="frmAfiliacion" method="post" >
<input type="hidden" name="metodo">
<input type="hidden" name="idObjeto">
<input type="hidden" name="titulo">
<input type="hidden" name="variableSesion"></form>
<div id="contenidos-informativos">
<h2>AFILIACION DE OPERACIONES DE BANCA CELULAR</h2>
	<p class="mensaje">${mensajecabeceraafbc}</p>
		<TABLE class="constancia">
			
		<caption class="titulo-constancia">
		CONSTANCIA DE AFILIACION
	    </caption>


								<tr>
									<TD>Nro.Cuenta Ahorro MN:</TD>
									<TD><bean:write name="<%=pe.cosapi.common.ConstanteSesion.AFILIAR%>" property="cuenta.cuentaformateada"/></TD>
								</tr>	
								
								
								<tr>
									<TD>Operador Telefono Afiliado:</TD>
									<TD><bean:write name="<%=pe.cosapi.common.ConstanteSesion.AFILIAR%>" property="operadorOrigen"/></TD>
								</tr>
								<tr>
									<TD>N&uacute;mero Telefono Afiliado:</TD>
									<TD><bean:write name="<%=pe.cosapi.common.ConstanteSesion.AFILIAR%>" property="numeroCelular"/></TD>
								</tr>	
								
								<tr>
									<TD>Tipo de Operación:</TD>
									<TD><bean:write name="<%=pe.cosapi.common.ConstanteSesion.AFILIAR%>" property="mostrarTipoAfil"/></TD>
								</tr>
								<tr>
									<TD>N&uacute;mero Cuenta Destino:</TD>
									<TD><bean:write name="<%=pe.cosapi.common.ConstanteSesion.AFILIAR%>" property="mostrarNumAfil"/></TD>
								</tr>
								<tr>
									<TD>Alias de la Operaci&oacute;n:</TD>
									<TD><bean:write name="<%=pe.cosapi.common.ConstanteSesion.AFILIAR%>" property="aliasOperacion"/></TD>
								</tr>
								<tr>
									<TD>Correo:</TD>
									<TD><bean:write name="<%=pe.cosapi.common.ConstanteSesion.AFILIAR%>" property="email"/></TD>
								</tr>
								
								<tr>
									<TD>Fecha:</TD>
									<TD><bean:write name="<%=pe.cosapi.common.ConstanteSesion.AFILIAR%>" property="fecha"/></TD>
								</tr>
								<tr>
									<TD>Hora:</TD>
									<TD><bean:write name="<%=pe.cosapi.common.ConstanteSesion.AFILIAR%>" property="hora"/></TD>
								</tr>
								<tr>
									<TD>Codigo Operacion:</TD>
									<TD><bean:write name="<%=pe.cosapi.common.ConstanteSesion.AFILIAR%>" property="nroOperacion"/></TD>
								</tr>
								
						
						</TABLE>
						
		<div id="botones" class="limpiar">
         <a href="javascript:imprimir();" id="imprimir" style="margin-left:125px;">IMPRIMIR</a>
         <a href="javascript:enviar();" id="enviar">ENVIAR</a>
         <a href="javascript:verPdf();" id="descargar">DESCARGAR</a>
     </div>	 
	</div>
				
<P><BR></P></BODY>
</HTML>

