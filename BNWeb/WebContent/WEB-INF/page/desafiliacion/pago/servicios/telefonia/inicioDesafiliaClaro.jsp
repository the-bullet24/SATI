<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld"	prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld"	prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld"	prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld"				prefix="c"%>
<HTML>
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script language="JavaScript" src="<%=request.getContextPath()%>/js/util.js"></script>
<META name="GENERATOR" content="IBM Software Development Platform">
<TITLE>tran_int_ah.html</TITLE>
<link href="<%=request.getContextPath()%>/estilos/style.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/estilos/style1.css" rel="stylesheet" type="text/css">
<style type="text/css">
<!--
body {
	background-color: #EFEFEF;
}
-->
</style>
<STYLE>
<!--
.texto {
font-family: Arial, Helvetica, sans-serif; 
font-size: 11px; 
font-style: normal; 
}
-->
</STYLE>
<script language="JavaScript" src="<%=request.getContextPath()%>/js/util.js"></script>
<SCRIPT language="javascript">

	function desafiliar(){
		var checkboxes = document.frmDesafiliacion.optSecuencia;
		var cadena = "";
		var longitudCheck = checkboxes.length;

		if (longitudCheck == undefined) {
			if (checkboxes.checked) {
				cadena = checkboxes.value+"¬";
			}
		}
		else{
			for (var x=0; x < checkboxes.length; x++) {
				if (checkboxes[x].checked) {
					if (cadena.length == 0) {
						cadena = checkboxes[x].value;
					} else {
						cadena = cadena +"¬"+checkboxes[x].value;
					}
				}
			}
		}

		if (cadena.length == 0){
			alert('Seleccionar al menos un registro a Desafiliar...' ); return;
		}

		document.frmDesafiliacion.listDesafilia.value = cadena;
		// Validando que la clave de Internet sea de 6 digitos
		if (validacampo("txtNumClave")){ 
			alert('Es necesario ingresar su Clave de Internet' ); return;}
		if (validalongitud("txtNumClave","6")){
			alert('Su clave de internet debe ser de 6 Digitos, no menos');
			return;
		}

		frmDesafiliacion.action='<%=request.getContextPath()%>/desafTelefoniaClaro.do?metodo=finDesafiliar';
		frmDesafiliacion.submit();

	}

	/*
		Functions para Teclado
	*/
	function evalRanTable(valor){
		document.frmDesafiliacion.txtNumClave.value = evaluarTeclado6(document.frmDesafiliacion.txtNumClave.value,valor);
	}

	/*
		Functions para Limpiar la clave
	*/
	function cleanPass(){
		cleanPassword("txtNumClave");
	}
	
	function regresar(){
		frmDesafiliacion.action='<%=request.getContextPath()%>/desafTelefoniaClaro.do?metodo=regresar';
		frmDesafiliacion.submit();
		/*
		history.go(-1)
		*/
	}
	
</SCRIPT>

</HEAD>
<body oncontextmenu="return false" onselectstart="return false" ondragstart="return false"  onKeyDown="return cancelRefresh(event)"  bgcolor="white">
<form name="frmDesafiliacion" method="post">
<input type="hidden" name="metodo">
<input type="hidden" name="listDesafilia">
<input type="hidden" name="descripdesa">
<input type="hidden" name="transaccion" value="PS00">

<TABLE width="100%" border="0" align="center">
	<TBODY>
		<TR align="left" valign="top">
			<TD>
			<DIV align="center"><SPAN class="text8centrn"><FONT size="2"
				face="Arial, Helvetica, sans-serif"><B><FONT color="#000000">
				<bean:write name="TITULO"/>
			</FONT></B></FONT></SPAN></DIV>
			</TD>
		</TR>
		<TR align="left" valign="top">
			<TD height="35">
			<DIV align="center"><SPAN class="text8centrn"><FONT color="black"
				size="2" face="Arial, Helvetica, sans-serif"><B> <bean:write
				name="<%=pe.cosapi.common.ConstanteSesion.USUARIO_EN_SESION%>"
				property="nombre" ignore="true"/> </B></FONT></SPAN></DIV>
			</TD>
		</TR>
		<TR align="left" valign="top">
			<TD width="100%">
			<CENTER>
		
			<TABLE cellspacing="0" cellpadding="0" border="0" width="435">
				<TBODY>
					<TR bgcolor="#c9242C">
						<TD colspan="3" align="center" bgcolor="#CC0000"><font size="2" color="white" face="Arial, Helvetica, sans-serif" ><B>LISTA DE AFILIACIONES</B></FONT></TD>
					</TR>
					<TR bgcolor="#c9242C">
						<TD width="30" align="center" bgcolor="#E5E5DE"><B><SPAN class="estiloDetalle"><FONT
							color="white" face="Arial" size="-1"> </FONT></SPAN></B></TD>
						<TD align="center" bgcolor="#E5E5DE" width="200"><B><SPAN class="estiloDetalle"><FONT
							color="black" face="Arial" size="-1"><bean:write name="DES_SUBTITULO1" /></FONT></SPAN></B></TD>
						<TD align="center" bgcolor="#E5E5DE" width="189"><B><SPAN class="estiloDetalle"><FONT
							color="black" face="Arial" size="-1"><bean:write name="DES_SUBTITULO2" /></FONT></SPAN></B></TD>
					</TR>
			</TBODY>
			</TABLE>
					<logic:notEmpty name="listaDesafiliacion">
					<div id="scroll3" style="width:435;height:210px;overflow:auto;background-color:#e5e5de;">
					<TABLE border="1" width="418" bordercolor="white">
						<%int i = 0; %>
						<logic:iterate name="listaDesafiliacion" id="desafiliacion">
							<TR bgcolor="#e5e5de" align="center">
								<TD width="30" align="center" bgcolor="white"><INPUT type="checkbox"
									name="optSecuencia"
									value="<bean:write name="desafiliacion" property="tipoAfiliacion"/>-<bean:write name="desafiliacion" property="nroTarjeta"/>-<bean:write name="desafiliacion" property="secuencia"/>">
									</TD>
								<TD align="left" bgcolor="white" width="226"><FONT face="Arial" size="2"><SPAN
									class="estiloDetalle"><bean:write name="desafiliacion"
									property="descripcion" /> </SPAN></FONT></TD>
								<logic:equal name="desafiliacion" property="tipoAfiliacion" value="TRAM">
									<TD align="left" bgcolor="white" width="152"><FONT face="Arial" size="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<SPAN
									class="estiloDetalle"><bean:write name="desafiliacion"
									property="numSerFormat" /></SPAN></FONT>
									</TD>
								</logic:equal>
								<logic:notEqual name="desafiliacion" property="tipoAfiliacion" value="TRAM">
									<TD align="left" bgcolor="white" width="152"><FONT face="Arial" size="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<SPAN
									class="estiloDetalle"><bean:write name="desafiliacion"
									property="numeroServicio" /> </SPAN></FONT>
									</TD>
								</logic:notEqual>
								
							</TR>
						</logic:iterate>
					</TABLE>
					</div>	
					</logic:notEmpty>
					<logic:empty name="listaDesafiliacion">
					<table width="435">
						<TR>
							<TD align="center" colspan="3"><SPAN class="textizqn"> <B>No
							existen afiliaciones asociadas</B> </SPAN></TD>
						</TR>
						<TR>
							<TD align="center" colspan="3"><br><br>
								<IMG border="0" src="<%=request.getContextPath()%>/Images/<bean:write name="CARPETA"/>/img_regresar.gif" onclick="javascript:regresar();">
							</TD>
						</TR>
					</table>
					</logic:empty>
		<logic:notEmpty name="listaDesafiliacion">
			<TABLE cellspacing="0" cellpadding="0" border="0" width="435">
				<TR bgcolor="#e5e5de" align="center"> 
					<TD align="CENTER">
						<FONT class="texto"><B>Clave Internet:</B></FONT><input type="password" name="txtNumClave" size="6" maxlength="6" readonly="readonly">
					</TD>
				</TR>
				<TR bgcolor="#e5e5de" align="center">
					<TD align="CENTER">
						<jsp:include page="/WEB-INF/page/sistema/teclado.jsp" ></jsp:include>
					</TD>
				</TR>
				<TR>
						<TD colspan="3" align="center"><BR>
						<br>
							<IMG border="0" src='<%=request.getContextPath()%>/Images/<bean:write name="CARPETA"/>/img_desafiliar.gif' onclick="javascript:desafiliar();"></TD>
					</TR>
			</TABLE>
		</logic:notEmpty>
			<TABLE border="0"  cellspacing="1" cellpadding="0" width="419">
				<TBODY>
		<logic:messagesPresent>
			<TR>
				<TD colspan="3" align="center">
					<CENTER><TABLE border="0" cellspacing="1" cellpadding="0" class="fondoMensajeError" width="100%">
						<TR>
							<TD class="textoMensajeError" align="center">
								<html:errors />
							</TD>
						</TR>
					</TABLE></CENTER>
				</TD>
			</TR>
		</logic:messagesPresent>
					
					<TR>
						<TD align="center" colspan="3"><BR></TD>
					</TR>
					<TR>
						<TD align="center" colspan="3"><FONT size="-2" face="Arial"></FONT></TR>
				</TBODY>
			</TABLE>
			</TD>
		</TR>
	</TBODY>
</TABLE>
</form>
</BODY>
</html>
