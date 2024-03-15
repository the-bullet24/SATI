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
		window.open('<%=request.getContextPath()%>/util.do?metodo=solicitarRefrendo&variableSesion=<%=pe.cosapi.common.ConstanteSesion.AFILIAR%>&idObjeto=refrendoAfiliacionBancaCelTasa',"BN","toolbar=0,location=0,width=630,height=730, scrollbars=yes, resizable=yes,  top=" + ((screen.height/2)-(390/2))+", left="+((screen.width/2)-(380/2)));
	}
	
	function enviar(){
		window.open('<%=request.getContextPath()%>/util.do?metodo=verMail&variableSesion=<%=pe.cosapi.common.ConstanteSesion.AFILIAR%>&idObjeto=mailAfiliacionBancaCelTasa',"BN","toolbar=0,location=0,width=630,height=730, scrollbars=yes, resizable=yes,  top=" + ((screen.height/2)-(530/2))+", left="+((screen.width/2)-(460/2)));
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

</SCRIPT>
<TITLE>deb_aut_db.html</TITLE>
</HEAD>

<body  oncontextmenu="return false" onselectstart="return false" ondragstart="return false"  onKeyDown="return cancelRefresh(event)"  bgcolor="white">

<div id="contenidos-informativos">
<h2>AFILIACION DE OPERACIONES DE BANCA CELULAR</h2>
	<p class="mensaje">${mensajecabeceraafbc}</p>
		<TABLE class="constancia">
			
		<caption class="titulo-constancia">
			CONSTANCIA DE GIRO
	    </caption>
	    

								<tr>
									<TD>Nro.Cuenta Ahorro MN:</TD>
									<TD height="20" width="55%"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.AFILIAR%>" property="cuenta.cuentaformateada"/></TD>
								</tr>	
								<tr>
									<TD>Alias de la Cuenta:</TD>
									<TD height="20" width="55%"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.AFILIAR%>" property="alias"/></TD>
								</tr>	
								
								<tr>
									<TD>Operador Telefono Afiliado:</TD>
									<TD height="20" width="55%"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.AFILIAR%>" property="operadorOrigen"/></TD>
								</tr>
								<tr>
									<TD>N&uacute;mero Telefono Afiliado:</TD>
									<TD height="20" width="55%"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.AFILIAR%>" property="numeroCelular"/></TD>
								</tr>	

							
								<tr>
									<TD>Tipo de Operación:</TD>
									<TD height="20" width="55%"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.AFILIAR%>" property="mostrarTipoAfil"/></TD>
								</tr>
								<tr>
									<TD>Correo:</TD>
									<TD height="20" width="55%"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.AFILIAR%>" property="email"/></TD>
								</tr>
								
								<tr>
									<TD>Fecha:</TD>
									<TD height="20" width="55%"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.AFILIAR%>" property="fecha"/></TD>
								</tr>
								<tr>
									<TD>Hora:</TD>
									<TD height="20" width="55%"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.AFILIAR%>" property="hora"/></TD>
								</tr>
								<tr>
									<TD>Codigo Operacion:</TD>
									<TD height="20" width="55%"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.AFILIAR%>" property="nroOperacion"/></TD>
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

