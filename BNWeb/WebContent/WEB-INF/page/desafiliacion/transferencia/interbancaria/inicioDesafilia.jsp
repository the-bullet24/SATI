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
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/resetearcss.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/tipografias.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bn-estilos.css" />  
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/select.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/select.js"></script>

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
	
		frmDesafiliacion.action='<%=request.getContextPath()%>/desafTransfInterBancaria.do?metodo=finDesafiliar';
		frmDesafiliacion.submit();

	}

	function regresar(){
		frmDesafiliacion.action='<%=request.getContextPath()%>/desafTransfInterBancaria.do?metodo=regresar';
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
<div id="contenidos-informativos">
<h2><bean:write name="TITULO"/></h2>
			<div id="consulta-saldo">
			<center>
			<TABLE cellspacing="0" cellpadding="0"  width="435">
				<TBODY>
					<TR>
								<TD colspan="3" align="center" ><h3>LISTA DE FRECUENTES</h3></TD>
					</TR>
					<TR>
						<TD width="30" align="center" class="tituloTabla" >&nbsp;&nbsp;</TD>
						<TD align="center"  width="200" class="tituloTabla"><bean:write name="DES_SUBTITULO1" /></TD>
						<TD align="center"  width="189" class="tituloTabla"><bean:write name="DES_SUBTITULO2" /></TD>
					</TR>
			</TBODY>
			</TABLE>
					<logic:notEmpty name="listaDesafiliacion">
					<div id="scroll3">
					<TABLE width="435" cellspacing="0" cellpadding="0">
						<%int i = 0; %>
						<logic:iterate name="listaDesafiliacion" id="desafiliacion">
							<TR  align="center">
								<TD width="30" align="center" class="detalleCelda"><INPUT type="checkbox"
									name="optSecuencia"
									value="<bean:write name="desafiliacion" property="tipoAfiliacion"/>-<bean:write name="desafiliacion" property="nroTarjeta"/>-<bean:write name="desafiliacion" property="secuencia"/>">
									</TD>
								<TD align="left" width="226" class="detalleCelda"><bean:write name="desafiliacion" property="descripcion" /> </TD>
								<logic:equal name="desafiliacion" property="tipoAfiliacion" value="TRAM">
									<TD align="left" class="detalleCelda" width="152">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="desafiliacion"
									property="numSerFormat" />
									</TD>
								</logic:equal>
								<logic:notEqual name="desafiliacion" property="tipoAfiliacion" value="TRAM">
									<TD align="left" class="detalleCelda" width="152">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="desafiliacion" property="numeroServicio" />
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
							<TD colspan="3">
							<p class="mensaje">No existen registros frecuentes asociados</p>
							</TD>
						</TR>
					
					</table>
					</logic:empty>
					
				</center>
			</div>
			<logic:messagesPresent>
			<div class="cysErrorMsg">
				<html:errors/>
			</div>
			
			</logic:messagesPresent>
		
			<logic:notEmpty name="listaDesafiliacion">
	
			<div class="boton1">
	
			<input type="button" value="DESAFILIAR" onclick="javascript:desafiliar();"/>
			</div>    
	</logic:notEmpty> 
		</div>
</form>
</BODY>
</html>
