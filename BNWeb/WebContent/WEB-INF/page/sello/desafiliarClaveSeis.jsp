<html>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/c.tld"				prefix="c"%>
<head> 
<script language="JavaScript" src="<%=request.getContextPath()%>/js/util.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<title>Operaciones por Internet</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<style type="text/css">
<!--
.Estilo1 {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 14px;
	font-weight: bold;
}
a:link {
	font-family: sans-serif;
	font-size: 12px;
	font-weight: normal;
}
a:visited {
	font-family: sans-serif;
	font-size: 12px;
	font-weight: normal;
}
a:hover {
	font-family: sans-serif;
	font-size: 12px;
	font-weight: normal;

}
a:active {
	font-family: sans-serif;
	font-size: 12px;
	font-weight: normal;
}

a.footer:link {
	color: #FFFFFF;
	text-decoration: none;
}
a.footer:visited {
	color: #FFFFFF;
	text-decoration: none;
}
a.footer:hover {
	color: #FFFFFF;
	text-decoration: none;
}
a.footer:active {
	color: #FFFFFF;
	text-decoration: none;
}
-->
</style>
<link href="<%=request.getContextPath()%>/css/principal.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/estilos/style2.css" rel="stylesheet" type="text/css" />
<SCRIPT language="javascript">
	var referenciaEstilo;
	var capaVisible;
	var navegador;
	if (navigator.appName == "Netscape") {
	  referenciaEstilo = "";
	  capaVisible="show";
	  navegador = "Netscape"; }
	else {
	  referenciaEstilo = "style.";
	  capaVisible="visible";
	  navegador = "Explorer"; }
	
	function autenticar(){

		frmLogin.action="<%=request.getContextPath()%>/login.do?metodo=validaSello";
		// Validando que sea DNI o Tarjeta
		
		frmLogin.HrTrx.value="0112";
		//alert("frmLogin.txtPassword.value="+frmLogin.txtPassword.value);
		frmLogin.submit();
	}

	function deshabilitar(obj){
		target = obj;
	}

	function generar(){
		var form = document.frmTarjeta;
		// Validando que la clave de 6 digitos
		if (form.txtClaveInternet.value.length < 6){
			alert('Su Nueva Clave de Internet debe ser de 6 Digitos no menos');
			return;
		}

		// Validando que la clave de 6 digitos
		if (form.txtClaveInternet_.value.length < 6){
			alert('Su Clave de confirmaci�n debe ser de 6 Digitos no menos');
			return;
		}
		form.action="<%=request.getContextPath()%>/login.do";
		form.metodo.value = 'desafiliaClave6';
		form.HrTrx.value="9256";
		form.submit();
	}

	function deshabilitar(obj){
		target = obj;
	}

	/*
		Functions para Teclado
	*/
	function evalRanTable(valor){
		var radio = document.forms[0].btnClave;
		if (radio[0].checked==true)
			document.forms[0].elements['txtClaveInternet'].value = evaluarTeclado6(document.forms[0].elements['txtClaveInternet'].value,valor);
		if 	(radio[1].checked==true)
			document.forms[0].elements['txtClaveInternet_'].value = evaluarTeclado6(document.forms[0].elements['txtClaveInternet_'].value,valor);
	}

	/*
		Functions para Limpiar la clave
	*/
	function cleanPass(){
		var radio = document.forms[0].btnClave;
		if (radio[0].checked==true)
			cleanPassword("txtClaveInternet");
		if 	(radio[1].checked==true)
			cleanPassword("txtClaveInternet_");
	}

	function Ayuda(){
		ventana_secundaria1=window.open('<%=request.getContextPath()%>/login.do?metodo=ayuda',"BN","toolbar=0,directories=0,status=0,copyhistory=0,location=0,width=310,height=140, scrollbars=no, resizable=no,  top=" + ((screen.height/2)-(120))+", left="+((screen.width/2)-150));
		setTimeout("ventana_secundaria1.close();",25000);		
	}

</SCRIPT>

<style type="text/css"> 
#capa1{ 
position:absolute; 
z-index:1; 
visibility: hidden;
} 
</style>


</head>

<body oncontextmenu="return false" onselectstart="return false" ondragstart="return false"  onKeyDown="return cancelRefresh(event)">
<table width="100" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#E1E1E1">
	<tr bgcolor="#E5E5DE">
		<td height="70" valign="bottom" bgcolor="#FFFFFF" class="Estilo1 Estilo2"><img src="<%=request.getContextPath()%>/Images/01/logo_bn.jpg" width="280" height="50" class="img_logotipo" alt="Logotipo del Banco de la Naci�n"> </td>
	</tr>
	<tr>
	<td>
		<table width="900"  border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#E22028">
			<tr>
				<td height="1" colspan="3" bgcolor="#FFFFFF"><h1 class="ti0">Validaci�n De Clave de Internet</h1></td>
			</tr>
			<tr>
				<td width="253" height="211" valign="bottom">
				<div align="left">
					<table width="100" height="270" border="0" cellpadding="0" cellspacing="0" class="img_izquierda">
						<tr>
							<td height="100" colspan="2">&nbsp;</td>
						</tr>
					</table>
				</div>
				</td>
				<td width="500">
					<html:form name="frmTarjeta" type="pe.bn.login.action.form.LoginForm" action="/login.do" method="POST">
					<INPUT type="hidden" name="transaccion" value="LG01">
					<INPUT type="hidden" name="HrTrx" value="0112">
					<INPUT type="hidden" name="metodo" value="">
					<center>
					<TABLE width="425" border="1" cellspacing="1" bordercolor="#FFCC00" bgcolor="#FFFFFF">
						<TR>
							<TD align="center" bgcolor="#FFCC00" class="texto">
								<DIV align="center"><b><SPAN class="texto"><c:out value='${msgSello6}' escapeXml="false" /></SPAN></b></DIV>
							</TD>
						</TR>
					</TABLE>
					<TABLE border="0" align="center" width="419">
						<TR>
							<TD width="45%" align="left" bgcolor="#e5e5de" class="textizqn"><INPUT
								type="radio" name="btnClave" checked
								onclick="deshabilitar(txtClaveInternet)"> Clave internet:</TD>
							<TD width="20%" align="CENTER" bgcolor="#e5e5de"><INPUT type="password"
								name="txtClaveInternet" size="6" maxlength="6" class="textizq8"
								readonly="readonly"></TD>
							<TD width="35%" align="left" bgcolor="#e5e5de" rowspan="2" width="212"><jsp:include
								page="/WEB-INF/page/sistema/teclado.jsp"></jsp:include></TD>
						</TR>
		
						<TR>
							<TD width="45%"  align="left" bgcolor="#e5e5de" class="textizqn"><INPUT
								type="radio" name="btnClave"
								onclick="deshabilitar(txtClaveInternet_)">Confirmaci�n clave
							internet:</TD>
							<TD width="20%"  align="CENTER" bgcolor="#e5e5de"><INPUT type="password"
								name="txtClaveInternet_" size="6" maxlength="6" class="textizq8"
								readonly="readonly"></TD>
						</TR>
						<logic:messagesPresent>
							<TR>
								<TD colspan="4">
								<TABLE width="100%" border="0" cellspacing="1" cellpadding="0"
									class="fondoMensajeError">
									<TR>
										<TD class="textoMensajeError" align="center"><html:errors /></TD>
									</TR>
								</TABLE>
								</TD>
							</TR>
						</logic:messagesPresent>
		
						<TR>
							<TD colspan="4" align="center" height="57"><BR>
							<IMG border="0"
								src='<%=request.getContextPath()%>/Images/<bean:write name="CARPETA"/>/img_aceptar.gif'
								width="66" height="20" onclick="javascript:generar();"></TD>
						</TR>
						<TR>
							<TD align="center" colspan="3"><BR>
							</TD>
						</TR>
						<TR>
							<TD align="justify" colspan="3" class="texto">${mensajedesafiliacionInf}</TD>
						</TR>
					</TABLE>
					</center>
					<div >
						<center> <center> 
					</div>

					</html:form>
				</td>
				<td width="518">
					<div align="right"> </div>
				</td>
			</tr>
			<tr bgcolor="#ADADAD">
				<td height="16" colspan="3" bgcolor="#ADADAD">
					<table width="200" border="0" align="right" cellpadding="0" cellspacing="0">
					<tr>
						<td width="77" bgcolor="#7D7E82"></td>
						<td width="18" bgcolor="#656668"><div align="center"><img src="Images/01/v1.gif" width="12" height="12"></div></td>
						<td width="105" height="22" bgcolor="#656668" class="texto_oclave"><a class="footer" href="javascript:Ayuda();" title="Abre ventana emergente">Olvid� mi clave</a> </td>
					</tr>
					</table>
				</td>
			</tr>
		</table>
	</td>
	<tr>
		<td>
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="25%"></td>
					<td width="50%" height="38">
						<div align="center"><span class="Estilo3">Banco de la Naci&oacute;n &copy; - 2008 Todos los Derechos Reservados<BR>Resoluci�n recomendada 1024 x 768 
						</span>
						</div>
					</td>
					<td width="25%" align="right">
						<table width="200" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="77"></td>
								<td width="18"><div align="center"><img src="Images/01/v1.gif" width="12" height="12"></div></td>
								<td width="105"><A href="javascript:window.close();">Cerrar&nbsp;Ventana</A></td>	
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>

<map name="Map"><area shape="rect" coords="3,163,70,183" href="../sistema/contenido.htm" target="_parent">
</map></body>
</html>
