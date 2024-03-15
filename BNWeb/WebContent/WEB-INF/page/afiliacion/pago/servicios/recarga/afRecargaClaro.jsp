<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld"	prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld"	prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld"	prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld"				prefix="c"%>
<html:html>
<HEAD>
<script language="JavaScript" src="<%=request.getContextPath()%>/js/util.js"></script>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
<SCRIPT language="javascript">


	function desafiliar(){
		frmAfilPagoServicio.action = '<%=request.getContextPath()%>/desafiliar.do';
		frmAfilPagoServicio.submit();
	}
	function mostrarejemplo(){
		if(document.forms[0].cmbServicio.value==""){
			document.getElementById("msjegeneral").style.display = 'none';
		  if(document.forms[0].cmbServicio.value=="T" || document.forms[0].cmbServicio.value=="Y"){
			 if(document.getElementById("msjeotro").style.display != 'none'){
								document.getElementById("msjeotro").style.display = 'none';
			 }
		  }
			
		}
		else if(document.forms[0].cmbServicio.value=="U" || document.forms[0].cmbServicio.value=="L" || document.forms[0].cmbServicio.value=="T" || document.forms[0].cmbServicio.value=="C"){
		  document.getElementById("msjegeneral").style.display = 'block';
		  if(document.forms[0].cmbServicio.value=="T" || document.forms[0].cmbServicio.value=="Y"){
			 if(document.getElementById("msjeotro").style.display != 'none'){
								document.getElementById("msjeotro").style.display = 'none';
			 }
		  }
		}
		else{
			document.getElementById("msjeotro").style.display = 'block';
			if(document.getElementById("msjegeneral").style.display != 'none'){
								document.getElementById("msjegeneral").style.display = 'none';
			}
		}
	}

	function afiliar(){
		var form = document.forms[0];
		
		// Validando que el Número de DNI no tenga caracteres que no sean números
		if (validacampo("txtNumDoc")){ 
			alert('Es necesario ingresar el número de documento' ); return;}

		if(document.forms[0].cmbTipoDoc.value=="001"){ // DNI 001 - 8 - N
			if (validalongitud("txtNumDoc","8")){
				alert('El número de DNI debe ser de 8 Digitos, no menos');
				return;
			}

			if (document.forms[0].txtNumDoc.value.length > 8){
				alert('El número de DNI debe ser de 8 Digitos');
				return;
			}
		}

		if(document.forms[0].cmbTipoDoc.value=="003"){ // DNI 001 - 8 - N
			if (validalongitud("txtNumDoc","10")){
				alert('El número de Carnet de Extranjería debe ser de 10 Digitos, no menos');
				return;
			}
		}

		// Validando que el Día no tenga caracteres que no sean números
		if(!validarFecha(document.forms[0].txtDia.value,document.forms[0].cmbMes.value,document.forms[0].txtAnio.value)){
			return;
		}
		// Valida que se haya seleccionado un elemento radiobuttom sexo
		if (validaRadios("rdnSexo")){
			alert('Es necesario seleccionar el campo sexo');
			return;
		}
		// Valida si se ingresò la direcciòn
		if (validacampo("txtMail")){ 
			alert('Es necesario ingresar la dirección de e-mail' ); return;}
		// Valida el formato de la direcciòn de e-mail
		if(validarEmail(document.forms[0].txtMail.value)==false){
			alert('La dirección de e-mail es incorrecta');
			return;
		}
		// Valida si se selecciono un servicio
		if (validacampo("cmbServicio")){ 
			alert('Es necesario seleccionar un servicio' ); return;}
		
		// Valida  que se ingrese el numero de servicio
		if (validacampo("txtNumServicio")){ 
			alert('Es necesario que ingrese el número del servicio' ); return;}

		// Valida  que el numero de servicio sea de 9 digitos
		if (validalongitud("txtNumServicio","9")){
			alert('El Nro. Celular debe ser de 9 Dígitos, no menos');
			return;
		}
		
		var tipoElemento = <c:out value="${tipoElemento.tipoElementoSeguridad}"></c:out>;
		
			if(tipoElemento == '5')
		{
			if (validacampo("txtCoordenada")){
			alert('Es necesario ingresar los 6 dígitos del token');
			return;
			}
			if (validalongitud("txtCoordenada","6")){
				alert('El pin del Token es de 6 dígitos, no menos');
				return;
			}
		}else{
			if (validacampo("txtCoordenada")){
			alert('Es necesario ingresar el valor de la coordenada');
			return;
			}
			if (validalongitud("txtCoordenada","2")){
				alert('El valor de la coordenada es de 2 dígitos, no menos');
				return;
			}
		}

		// Validando que no sean cuenta de Mismo Banco
		var numTelCel  = document.forms[0].txtNumServicio.value;
		var cPrimerNumero = numTelCel.substring(0,1)
		if (cPrimerNumero != "9"){
			alert('El primer dígito del Num. Celular debe ser 9');
			return;
		}

		// Valida  que se ingrese el nombre del servicio
		if (validacampo("txtNombreServicio")){ 
			alert('Es necesario que ingrese el nombre del servicio' ); return;}

		if (solocaracterespermitidos("txtNombreServicio")){
			alert('Ingresar sólo letras a excepción de la ñ. No considerar tildes' ); 
			return;
		}
		
		// Validando que la clave de INTERNET sea ingresada
		if (validacampo("txtClave")){ 
			alert('Es necesario ingresar su Clave de Internet' ); return;}
		if (validalongitud("txtClave","6")){
			alert('Su clave de internet debe ser de 6 Digitos, no menos');
			return;
		}
		
		frmAfilPagoServicio.action="<%=request.getContextPath()%>/afilRegarcaCla.do?metodo=afiliar";
		frmAfilPagoServicio.validar.value="true";
		frmAfilPagoServicio.submit();
		form.imgContinuar.removeAttribute("onclick");
		form.imgContinuar.setAttribute("onclick", "");
		
	}

	function selectVal(e){

		if(document.forms[0].cmbTipoDoc.value=="001"){ 		// DNI 1 - 8 - N
			document.forms[0].txtNumDoc.maxLength="8";
			return soloNumerosAll(e);
		}else if(document.forms[0].cmbTipoDoc.value=="003"){  // Carnet EXTRANJERIA  3 - 8 - N
			document.forms[0].txtNumDoc.maxLength="10";
		}
	}

	function limpiar(){
		if(document.forms[0].cmbTipoDoc.value=="001"){ 		// DNI 1 - 8 - N
			document.forms[0].txtNumDoc.maxLength="8";
			document.forms[0].txtNumDoc.size="7";
		}else if(document.forms[0].cmbTipoDoc.value=="003"){ 	// Carnet EXTRANJERIA  3 - 15 - A
			document.forms[0].txtNumDoc.maxLength="10";
			document.forms[0].txtNumDoc.size="9";		
		}
		document.forms[0].txtNumDoc.value="";
		document.forms[0].txtNumDoc.focus();
	}
	
	/*
		Functions para Teclado
	*/
	function evalRanTable(valor){
		frmAfilPagoServicio.txtClave.value  = evaluarTeclado6(frmAfilPagoServicio.txtClave.value,valor);
	}

	/*
		Functions para Limpiar la clave
	*/
	function cleanPass(){
		cleanPassword("txtClave");
	}

	function validarSiNumero(numero){
		var textoStr =  numero.toString() // transformo a string todo el campo
		var tiene = 0
		for(var i = 0;i < numero.length;i++){ // recorro caracter potr caracter
			var oneChar = textoStr.charAt(i)
			if (!/^([0-9])*$/.test(oneChar)){ // busco un caracter que no sea Numerico
				tiene = 1
			}
		}


		if (tiene == 1){ // controlo si existe o no caracter que no sea numerico.
			return true
		} else {
			return false
		} 
	}

</SCRIPT>
</HEAD>
<body oncontextmenu="return false" onselectstart="return false" ondragstart="return false"  onKeyDown="return cancelRefresh(event)"  bgcolor="white" onload="limpiar();">
<html:form type="pe.bn.afiliacion.action.form.AfiliacionPgServiciosForm" method="POST"  action="/afilRegarcaCla.do">
<input type="hidden" name="validar" value="false">
<input type="hidden" name="transaccion" value="AS01">
<input type="hidden" name="codAf" value="00438">

<TABLE width="99%" border="0" align="center">
	<TBODY>
		<TR align="left" valign="top">
			<TD>
			<DIV align="center"><BR>
				<SPAN class="text8centrn"><FONT size="2"
				face="Arial, Helvetica, sans-serif"><B><FONT color="#000000"><c:out value='${tituloAfiliacion}' escapeXml="false" /></FONT></B></FONT></SPAN></DIV>
			</TD>
		</TR>
		<TR align="left" valign="top">
			<TD height="33">
			<DIV align="center"><SPAN class="text8centrn"><FONT color="black"
				size="2" face="Arial, Helvetica, sans-serif"><B> <bean:write
				name="<%=pe.cosapi.common.ConstanteSesion.USUARIO_EN_SESION%>"
				property="nombre" /> </B></FONT></SPAN></DIV>
			</TD>
		</TR>
		<TR align="left" valign="top">
			<TD width="100%" align="center" height="874">
			<CENTER></CENTER>
			<CENTER>
			<TABLE border="0" cellspacing="1" cellpadding="0" width="419">
				<TBODY>
					<TR>
						<TD colspan="4" align="justify"><SPAN style="text-align: justify" class="texto"><c:out value='${mensajeAfiliacion}' escapeXml="false" /></SPAN></TD>
					</TR>
					<TR>
						<TD height="20" colspan="4"><BR></TD>
					</TR>
					<TR bgcolor="#C9242C">
						<TD colspan="4" height="20" align="center"><B><SPAN class="textizqn"><FONT color="white"> Datos Personales del Titular</FONT></SPAN></B></TD>
					</TR>
					<TR bgcolor="#e5e5de">
						<TD colspan="2" height="20"><SPAN class="textizqn"><B>Tipo Doc. Identidad:</B></SPAN></TD>
						<TD colspan="2" height="20"><html:select property="cmbTipoDoc" onchange="limpiar()"><html:options collection="lstDocumento" property="codigo" labelProperty="descripcion" /></html:select></TD>
					</TR>
					<TR bgcolor="#e5e5de">
						<TD colspan="2" height="20"><SPAN class="textizqn"><B>Nro. Doc. Identidad:</B></SPAN></TD>
						<TD colspan="2" height="20" nowrap><html:text  property="txtNumDoc" size="7" maxlength="10" onkeypress="return soloNumerosAll(event)" onkeypress="return selectVal(event)" onkeyup="return selectVal(event)"/></TD>
					</TR>
					<TR bgcolor="#e5e5de">
						<TD colspan="2" height="20"><SPAN class="textizqn"><B>Fecha Nacimiento:</B></SPAN></TD>
						<TD colspan="2" height="20" width="227"><html:text property="txtDia" size="2" maxlength="2" onkeypress="return soloNumerosAll(event)"/>&nbsp;<html:select property="cmbMes"><html:options collection="lstMes" property="codigoDetalleAyuda"	labelProperty="descripcionDetalle" /></html:select>&nbsp;<html:text property="txtAnio" size="4" maxlength="4" onkeypress="return soloNumerosAll(event)"/></TD>
					</TR>
					<TR bgcolor="#e5e5de">
						<TD colspan="2" width="189"><SPAN class="textizqn"><B>Sexo:</B></SPAN></TD>
						<TD colspan="2" width="227"><html:radio property="rdnSexo" value="M" /><SPAN class="textizqn">Masculino</SPAN><html:radio property="rdnSexo" value="F" /><SPAN class="textizqn">Femenino</SPAN></TD>
					</TR>
					<TR bgcolor="#e5e5de">
						<TD colspan="2" height="20" width="189"><SPAN class="textizqn"><B>Email:</B></SPAN></TD>
						<TD colspan="2" height="20" width="227"><html:text property="txtMail" size="38" maxlength="60" /></TD>
					</TR>
					<TR bgcolor="#C9242C">
						<TD colspan="4" height="20" align="center"><B><SPAN class="textizqn"><FONT color="white">Servicio a Afiliar</FONT></SPAN></B></TD>
					</TR>
					<TR bgcolor="#e5e5de">
						<TD colspan="2" height="20" width="189"><SPAN class="textizqn"><B>Servicio:</B></SPAN></TD>
						<TD colspan="2" height="20" width="227"><html:select property="cmbServicio" style="width: 150" onchange="mostrarejemplo()"><html:options collection="lstServicio" property="codigoDetalleAyuda" labelProperty="descripcionDetalle"/></html:select></TD>
					</TR>
					<c:if test="${codServicioDet == 'XYZ00'}">
					<TR bgcolor="#e5e5de">
						<TD colspan="2" height="20" width="189"><SPAN class="textizqn"><B>Localidad :</B></SPAN></TD>
						<TD colspan="2" height="20" width="227">
							<SELECT name="cmbLocalidad" class="textizqn">
							
							
							<logic:iterate name="lstLocalidad" id="localidad">
									<OPTION
										value="<bean:write name='localidad' property='codigoDetalleAyuda'/><bean:write name='localidad' property='codigoDetalleAyuda1'/>"><bean:write name="localidad" property="descripcionDetalle" /> </OPTION>
							</logic:iterate>
							
						</SELECT>
					</TR>
					</c:if>
					<c:if test="${codServicioDet == 'XYZ00'}">
					<TR bgcolor="#e5e5de" style="display: none">
						<TD colspan="2" height="20" width="189"><SPAN class="textizqn"><B>Localidad :</B></SPAN></TD>
						<TD colspan="2" height="20" width="227"><select name="cmbLocalidad" class="textizqn">
								<option value="00">Lima Callao</option></select></TD>
					</TR>
					</c:if>
					
					<TR bgcolor="#e5e5de">
						<TD colspan="2" height="20" width="189"><SPAN class="textizqn"><B><c:out value='${nomafil}' escapeXml="false" /> :</B></SPAN></TD>
						<TD colspan="2" height="20" width="227"><html:text property="txtNumServicio" size="9" maxlength="9"  onkeypress="return soloNumerosAll(event)"/></TD>
					</TR>
					<TR bgcolor="#e5e5de" id="msjegeneral" style="display: none">
								<TD height="20" colspan="4"  class="texto"><SPAN style="text-align: left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<c:out value='${ejemplo}' escapeXml="false" /></SPAN></TD>
							</TR>
						
						<logic:present name="ejemplo2">
							<TR bgcolor="#e5e5de" id="msjeotro" style="display: none">
								<TD height="20" colspan="4"  class="texto"><SPAN style="text-align: left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<c:out value='${ejemplo2}' escapeXml="false" /></SPAN></TD>
							</TR>
						</logic:present>
					
						<TR bgcolor="#e5e5de">
						<TD colspan="2" height="20" width="189"><SPAN class="textizqn"><B>Nombre para recordar <BR>
							&nbsp;el&nbsp;servicio :</B></SPAN></TD>
						<TD colspan="2" height="20" width="227"><html:text property="txtNombreServicio" size="38" maxlength="38" /></TD>
					</TR>
			
				
					<c:if test="${tipoElemento.tipoElementoSeguridad=='2'}">
					<TR bgcolor="#e5e5de">
						
						<TD colspan="2" height="20"  bgcolor="#e5e5de" width="140"></TD>
						<TD  height="20" colspan="2" bgcolor="#e5e5de" width="40%"><span class="textizqn"><B>
						Ingresar la Siguiente Coordenada </B></span><BR/><LABEL><B>&nbsp; </B><FONT size="+1"><SPAN
							style="color: #a80000"><B><c:if test="${resultCoord.coordConcat eq null}">No disponible</c:if>
							<c:if test="${resultCoord.coordConcat ne null}"><c:out value="${resultCoord.coordConcat}"/></c:if>
							
							</B></SPAN></FONT></LABEL><BR>
						<INPUT type="password" name="txtCoordenada" size="6" maxlength="2" <c:if test="${resultCoord.coordConcat eq null}"> readonly="readonly"</c:if> onkeypress="return soloNumerosAll(event)"><BR>
						
						<FONT size="-1" face="Arial"><FONT size="-1" face="Arial"><B></B></font></font></TD>
					</TR>
					</c:if>
					<c:if test="${tipoElemento.tipoElementoSeguridad=='5'}">
											
						<TR bgcolor="#E5E5DE">
										
						<TD  height="20" colspan="2" bgcolor="#e5e5de" width="355"><span class="textizqn"><B>
						Ingresar los 6 dígitos del TOKEN </B></span><LABEL><B>&nbsp; </B><FONT size="+1"><SPAN
							style="color: #a80000"><B><BR/>
							
							</B></SPAN></FONT></LABEL>
						<INPUT type="password" name="txtCoordenada" size="10" maxlength="6"  onkeypress="return soloNumerosAll(event)"><BR>
						
						
							<TD colspan="2" height="20"  bgcolor="#e5e5de" width="140"><img
								border="0" src="<%=request.getContextPath()%>/Images/token_p.gif" 
								></TD>
					</TR>
					</c:if>
					
					<TR>
							<TD height="20" colspan="8" bgcolor="#e5e5de"><BR>
							</TD>
					</TR>
					<c:if test="${tipoElemento.tipoElementoSeguridad=='2'}">
					<TR>
						<TD colspan="8" height="20" width="40%" class="textizqn"  bgcolor="#e5e5de"><b><u>Ejemplo:</u>
							</b><br>
								Al solicitarle la coordenada <B>6 - F</B>, deberás buscar la fila correspondiente al <b>número
							6</b> y la columna de la <B>letra  F</B>, en la unión de ambos, obtendrás un número, éste número deberás ingresarlo para aprobar la operación.</TD>
						
					</TR>
					</c:if>
					<c:if test="${tipoElemento.tipoElementoSeguridad=='5'}">
					<TR>
						<TD colspan="6" height="20" width="40%" class="textizqn"  bgcolor="#e5e5de"><b><u>Nota:</u>
							</b><br>
								Tener en cuenta que los 6 dígitos cambian cada minuto por lo cual debe ingresar antes que la 
								barra de tiempo se haya consumido.
								</TD>
						
					</TR>
					</c:if>
					<TR>
							<TD height="20" colspan="4"><BR>
							</TD>
						</TR>
					<logic:messagesPresent>
						<TR>
							<TD colspan="4">
							<TABLE border="0" cellspacing="1" cellpadding="0" class="fondoMensajeError">
								<TR>
									<TD class="textoMensajeError" align="left" width="419"><html:errors /></TD>
								</TR>
							</TABLE>
							</TD>
						</TR>
						<TR>
						<TD height="20" colspan="4"><BR><BR></TD>

						</TR>
					</logic:messagesPresent>
				</TBODY>
			</TABLE>
			</CENTER><IMG border="0" name="imgContinuar" src='<%=request.getContextPath()%>/Images/01/img_afiliar.gif' height="20" onclick="afiliar();" style="cursor:hand"> <BR>
		
			</TD>
			
		</TR>
	</TBODY>
</TABLE>
<P><BR>
</P>
</html:form>
</BODY>
</html:html>
