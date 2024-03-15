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
	function afiliar(){
		var form = document.forms[0];
		if(document.forms[0].cmbTipoDocBenef.value=="001"){ // DNI 001 - 8 - N
			document.forms[0].txtNroDocBenef.maxLength="8";
		} else if(document.forms[0].cmbTipoDocBenef.value=="002"){ // FFPP 002 - 9 -  A
			document.forms[0].txtNroDocBenef.maxLength="9";
			
		} else if(document.forms[0].cmbTipoDocBenef.value=="003"){ // FFAA 003 - 10 - A 
			document.forms[0].txtNroDocBenef.maxLength="10";
			
		} else if(document.forms[0].cmbTipoDocBenef.value=="004"){ // C.E. 004 - 8 - N
			document.forms[0].txtNroDocBenef.maxLength="8";
		} else if(document.forms[0].cmbTipoDocBenef.value=="006"){ // PASS 006 - 7 - N
			document.forms[0].txtNroDocBenef.maxLength="7";
		} else if(document.forms[0].cmbTipoDocBenef.value=="007"){ // P.N. 007 - 15 - A
			document.forms[0].txtNroDocBenef.maxLength="15";
			
		} else if(document.forms[0].cmbTipoDocBenef.value=="008"){ // L.M. 008 - 10 - N
			document.forms[0].txtNroDocBenef.maxLength="10";
		} 
		if (document.forms[0].cmbTipoDocIdent.value == ""){
			alert('Seleccionar un Tipo de Documento...');
			return;
		}

		// Validando que el Número de DNI no tenga caracteres que no sean números
		if (validacampo("txtNumDoc")){ 
			alert('Es necesario ingresar el número de documento' ); return;}

		if(document.forms[0].cmbTipoDocIdent.value=="001"){ // DNI 001 - 8 - N
			if (validalongitud("txtNumDoc","8")){
				alert('El número de DNI debe ser de 8 Digitos, no menos');
				return;
			}

			if (document.forms[0].txtNumDoc.value.length > 8){
				alert('El número de DNI debe ser de 8 Digitos');
				return;
			}
		}

		if(document.forms[0].cmbTipoDocIdent.value=="003"){ // DNI 001 - 8 - N
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
		
		// valida si se ingresò la direcciòn
		if (validacampo("txtMail")){ 
			alert('Es necesario ingresar la dirección de e-mail' ); return;}
		// Valida el formato de la direcciòn de e-mail
		if(validarEmail(document.forms[0].txtMail.value)==false){
			alert('La dirección de e-mail es incorrecta');
			return;
		}

		// Valida apellidos y nombres
		if (validacampo("txtApellidoPaternoBenef")){ 
			alert('Es necesario ingresar el apellido paterno del beneficiario' ); return;}
		if (solocaracterespermitidos2("txtApellidoPaternoBenef")){
			alert('El apellido paterno del beneficiario presenta caracteres inválidos' ); 
			return;
		}
		if (validacampo("txtApellidoMaternoBenef")){ 
			alert('Es necesario ingresar el apellido materno del beneficiario' ); return;}
		if (solocaracterespermitidos2("txtApellidoMaternoBenef")){
			alert('El apellido materno del beneficiario presenta caracteres inválidos' ); 
			return;
		}
		if (validacampo("txtNombreBenef")){ 
			alert('Es necesario ingresar el nombre del beneficiario' ); return;}
		if (solocaracterespermitidos2("txtNombreBenef")){
			alert('El nombre del beneficiario presenta caracteres inválidos' ); 
			return;
		}

		if (validacampo("cmbTipoDocBenef")){
			alert('Es necesario seleccionar un Tipo de Documento del Beneficiario');
			return;
		}

		if (validacampo("txtNroDocBenef")){
			alert('Es necesario ingresar el Número de documento del Beneficiario');
			return;
		}
			
		if (validalongitud("txtNroDocBenef",document.forms[0].txtNroDocBenef.maxLength)){
			alert('Su número de documento debe ser de '+ document.forms[0].txtNroDocBenef.maxLength +' Digitos, no menos');
			return;
		}

		if(document.forms[0].cmbTipoDocBenef.value=="001"){ // DNI 001 - 8 - N
			if (trim(document.forms[0].txtNroDocBenef.value) == "00000000"){
				alert('El número de documento ingresado no es correcto');
				return;
			}
		} else if(document.forms[0].cmbTipoDocBenef.value=="002"){ // FFPP 002 - 9 -  A
			if (trim(document.forms[0].txtNroDocBenef.value) == "000000000"){
				alert('El número de documento ingresado no es correcto');
				return;
			}
		} else if(document.forms[0].cmbTipoDocBenef.value=="003"){ // FFAA 003 - 10 - A 
			if (trim(document.forms[0].txtNroDocBenef.value) == "0000000000"){
				alert('El número de documento ingresado no es correcto');
				return;
			}
		} else if(document.forms[0].cmbTipoDocBenef.value=="004"){ // C.E. 004 - 8 - N
			if (trim(document.forms[0].txtNroDocBenef.value) == "00000000"){
				alert('El número de documento ingresado no es correcto');
				return;
			}
		} else if(document.forms[0].cmbTipoDocBenef.value=="006"){ // PASS 006 - 7 - N
			if (trim(document.forms[0].txtNroDocBenef.value) == "0000000"){
				alert('El número de documento ingresado no es correcto');
				return;
			}
		} else if(document.forms[0].cmbTipoDocBenef.value=="007"){ // P.N. 007 - 15 - A
			if (trim(document.forms[0].txtNroDocBenef.value) == "000000000000000"){
				alert('El número de documento ingresado no es correcto');
				return;
			}
		} else if(document.forms[0].cmbTipoDocBenef.value=="008"){ // L.M. 008 - 10 - N
			if (trim(document.forms[0].txtNroDocBenef.value) == "0000000000"){
				alert('El número de documento ingresado no es correcto');
				return;
			}
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
		
		var indice = document.forms[0].cmbTipoDocBenef.selectedIndex;
		var destipo = document.forms[0].cmbTipoDocBenef.options[indice].text;
		document.forms[0].txtdestipdoc.value=destipo;
		document.forms[0].action = "<%=request.getContextPath()%>/AfilTelegiros.do?metodo=afiliar";
		document.forms[0].submit();
		form.imgContinuar.removeAttribute("onclick");
		form.imgContinuar.setAttribute("onclick", "");
	}
	function upperCase(x)
	{
	var y=document.getElementById(x).value
	document.getElementById(x).value=y.toUpperCase()
	}

	function selectValCampos(e){

		if(document.forms[0].cmbTipoDocIdent.value=="001"){ 		// DNI 1 - 8 - N
			document.forms[0].txtNumDoc.maxLength="8";
			return soloNumerosAll(e);
		}else if(document.forms[0].cmbTipoDocIdent.value=="003"){  // Carnet EXTRANJERIA  3 - 8 - N
			document.forms[0].txtNumDoc.maxLength="10";
		}
	}

	function limpiarCampos(){
		if(document.forms[0].cmbTipoDocIdent.value=="001"){ 		// DNI 1 - 8 - N
			document.forms[0].txtNumDoc.maxLength="8";
			document.forms[0].txtNumDoc.size="7";
		}else if(document.forms[0].cmbTipoDocIdent.value=="003"){ 	// Carnet EXTRANJERIA  3 - 15 - A
			document.forms[0].txtNumDoc.maxLength="10";
			document.forms[0].txtNumDoc.size="9";		
		}
		document.forms[0].txtNumDoc.value="";
		document.forms[0].txtNumDoc.focus();
	}

	// Validando Longitus y tipo de dato de los Tipo de Documento
	function selectVal(e){// DOCUMENTO - LONGITUD - NUMERICO/ALFANUNMERICO

		if(document.forms[0].cmbTipoDocBenef.value=="001"){ // DNI 001 - 8 - N
			document.forms[0].txtNroDocBenef.maxLength="8";
			return soloNumerosAll(e);
		} else if(document.forms[0].cmbTipoDocBenef.value=="002"){ // FFPP 002 - 9 -  A
			document.forms[0].txtNroDocBenef.maxLength="9";
			
		} else if(document.forms[0].cmbTipoDocBenef.value=="003"){ // FFAA 003 - 10 - A 
			document.forms[0].txtNroDocBenef.maxLength="10";
			
		} else if(document.forms[0].cmbTipoDocBenef.value=="004"){ // C.E. 004 - 15 - A
			document.forms[0].txtNroDocBenef.maxLength="15";
		} else if(document.forms[0].cmbTipoDocBenef.value=="006"){ // PASS 006 - 15 - A
			document.forms[0].txtNroDocBenef.maxLength="15";
		} else if(document.forms[0].cmbTipoDocBenef.value=="007"){ // P.N. 007 - 15 - A
			document.forms[0].txtNroDocBenef.maxLength="15";
		} else if(document.forms[0].cmbTipoDocBenef.value=="008"){ // L.M. 008 - 10 - N
			document.forms[0].txtNroDocBenef.maxLength="10";
			return soloNumerosAll(e);
		} 
	}

	/*
		Functions para Teclado
	*/
	function evalRanTable(valor){
		frmAfilTelegiro.txtNumClave.value  = evaluarTeclado6(frmAfilTelegiro.txtNumClave.value,valor);
	}

	/*
		Functions para Limpiar la clave
	*/
	function cleanPass(){
		cleanPassword("txtNumClave");
	}
	function limpiar(){// DOCUMENTO - LONGITUD - NUMERICO/ALFANUNMERICO
		if(document.forms[0].cmbTipoDocBenef.value=="001"){// DNI 001 - 8 - N
			document.forms[0].txtNroDocBenef.maxLength="8";
			document.forms[0].txtNroDocBenef.size="7";
		} else if(document.forms[0].cmbTipoDocBenef.value=="002"){ // FFPP 002 - 9 -  A
			document.forms[0].txtNroDocBenef.maxLength="9";
			document.forms[0].txtNroDocBenef.size="8";
		} else if(document.forms[0].cmbTipoDocBenef.value=="003"){ // FFAA 003 - 10 - A 
			document.forms[0].txtNroDocBenef.maxLength="10";
			document.forms[0].txtNroDocBenef.size="9";
		} else if(document.forms[0].cmbTipoDocBenef.value=="004"){ // C.E. 004 - 15 - a
			document.forms[0].txtNroDocBenef.maxLength="15";
			document.forms[0].txtNroDocBenef.size="14";
		} else if(document.forms[0].cmbTipoDocBenef.value=="006"){ // PASS 006 - 15 - A
			document.forms[0].txtNroDocBenef.maxLength="15";
			document.forms[0].txtNroDocBenef.size="14";
		} else if(document.forms[0].cmbTipoDocBenef.value=="007"){ // P.N. 007 - 15 - A
			document.forms[0].txtNroDocBenef.maxLength="15";
			document.forms[0].txtNroDocBenef.size="14";
		} else if(document.forms[0].cmbTipoDocBenef.value=="008"){ // L.M. 008 - 10 - N
			document.forms[0].txtNroDocBenef.maxLength="10";
			document.forms[0].txtNroDocBenef.size="9";
		} 
		cleanPassword("txtNroDocBenef");
		frmAfilTelegiro.txtNroDocBenef.focus();
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
<body oncontextmenu="return false" onselectstart="return false" ondragstart="return false"  onKeyDown="return cancelRefresh(event)"  bgcolor="white" onload="limpiarCampos();">
<html:form type="pe.bn.afiliacion.action.form.AfiliacionTelegiroForm" action="/AfilTelegiros.do" method="post">
<input type="hidden" name="validar" value="true">
<TABLE width="100%" border="0" align="center">
	<TBODY>
		<TR align="left" valign="top">
			<TD>
			<DIV align="center"><SPAN class="text8centrn"><FONT size="2"
				face="Arial, Helvetica, sans-serif"><B><FONT color="#000000"><BR>
				AFILIACION DE GIROS</FONT></B></FONT></SPAN></DIV>
			</TD>
		</TR>
		<TR align="left" valign="top">
			<TD height="37">
			<DIV align="center"><SPAN class="text8centrn"><FONT color="black"
				size="2" face="Arial, Helvetica, sans-serif"><B>
				<bean:write name="<%=pe.cosapi.common.ConstanteSesion.USUARIO_EN_SESION%>" property="nombre"/>
			</B></FONT></SPAN></DIV>
			</TD>
		</TR>
		<TR align="left" valign="top">
			<TD width="100%" align="center" height="698">
			<CENTER><TABLE border="0" cellspacing="1" cellpadding="0" width="419">
				<TBODY>
					<TR>
						<TD colspan="2" align="justify"><SPAN style="text-align: justify" class="texto">${mensajecabeceraAfTel} </SPAN></TD>
								</TR>
					<TR>
							<TD height="20" colspan="2"><BR>
							</TD>
						</TR>
					<TR bgcolor="#C9242C">
						<TD colspan="2" height="20" align="center" ><SPAN class="textizqn"><B><FONT color="white">Datos Personales del Titular</FONT></B></SPAN></TD>
					</TR>						
					<TR bgcolor="#e5e5de">
						<TD height="15" width="200"><SPAN class="textizqn"><B>Tipo Doc. Identidad:</B></SPAN></TD>
						<TD height="15" width="216"><SPAN class="textizqn">
						<html:select property="cmbTipoDocIdent" style="background-color:#FEFEE9" onchange="limpiarCampos()">
							<html:options collection="lstDocumento" property="codigo" labelProperty="descripcion" />
						</html:select>
						</SPAN></TD>
					</TR>
					<TR bgcolor="#e5e5de">
						<TD height="20" width="200"><SPAN class="textizqn"><B>Nro. Doc. Identidad:</B></SPAN></TD>
						<TD height="20" width="216"><SPAN class="textizqn"><html:text property="txtNumDoc" size="7" maxlength="10" onkeypress="return selectValCampos(event)" onkeyup="return selectValCampos(event)" /></SPAN></TD>
					</TR>
					<TR bgcolor="#e5e5de">
						<TD height="20" width="200"><SPAN class="textizqn"><B>Fecha de Nacimiento:</B></SPAN></TD>
						<TD height="20" width="216"><SPAN class="textizqn"><html:text property="txtDia" size="2" maxlength="2" onkeypress="return soloNumerosAll(event)"/> 
						<html:select property="cmbMes" style="background-color:#FEFEE9">
							<html:options collection="lstMes" property="codigoDetalleAyuda" labelProperty="descripcionDetalle" />
						</html:select>
						<html:text property="txtAnio" size="4" maxlength="4"  onkeypress="return soloNumerosAll(event)"/></SPAN></TD>
					</TR>
					<TR bgcolor="#e5e5de">
						<TD height="20" width="200"><SPAN class="textizqn"><B>Sexo:</B></SPAN></TD>
						<TD height="20" width="216"><SPAN class="textizqn">
						<html:radio property="rdnSexo" value="M" />Masculino
						<html:radio property="rdnSexo"  value="F"/>Femenino</SPAN></TD>
					</TR>
					<TR bgcolor="#e5e5de">
						<TD height="20" width="200"><SPAN class="textizqn"><B>Email:</B></SPAN></TD>
						<TD height="20" width="216"><SPAN class="textizqn"><html:text property="txtMail" size="38" maxlength="60" /></SPAN></TD>
					</TR>
					<TR bgcolor="#C9242C">
						<TD colspan="2" height="20" align="center"><SPAN class="textizqn"><B><FONT color="white">Datos Beneficiario del Giro</FONT></B></SPAN></TD>
					</TR>
					<TR bgcolor="#e5e5de">
						<TD height="20" width="200"><SPAN class="textizqn"><B>Apellido Paterno:</B></SPAN></TD>
						<TD height="20" width="216"><SPAN class="textizqn"><html:text onkeyup="upperCase(this.id)" styleId ="txtApellidoPaternoBenef" property="txtApellidoPaternoBenef" size="38" maxlength="60" /></SPAN></TD>
					</TR>
					<TR bgcolor="#e5e5de">
						<TD height="20" width="200"><SPAN class="textizqn"><B>Apellido Materno:</B></SPAN></TD>
						<TD height="20" width="216"><SPAN class="textizqn"><html:text onkeyup="upperCase(this.id)" styleId ="txtApellidoMaternoBenef" property="txtApellidoMaternoBenef" size="38" maxlength="60" /></SPAN></TD>
					</TR>
					<TR bgcolor="#e5e5de">
						<TD height="20" width="200"><SPAN class="textizqn"><B>Nombres:</B></SPAN></TD>
						<TD height="20" width="216"><SPAN class="textizqn"><html:text onkeyup="upperCase(this.id)" styleId ="txtNombreBenef" property="txtNombreBenef" size="38" maxlength="60" /></SPAN></TD>
					</TR>
					<TR bgcolor="#e5e5de">
						<TD height="20" width="200"><SPAN class="textizqn"><B>Tipo Documento:</B></SPAN></TD>
						<TD height="20" width="216"><SPAN class="textizqn">
						<html:select property="cmbTipoDocBenef" style="background-color:#FEFEE9" onchange="limpiar()">
							<html:options collection="lstDocumentoBen" property="codigo" labelProperty="descripcion" />
						</html:select>
						</SPAN></TD>
					</TR>
					<TR bgcolor="#e5e5de">
						<TD height="20" width="200"><SPAN class="textizqn"><B>Nro. de Documento:</B></SPAN></TD>
						<TD height="20" width="216"><SPAN class="textizqn"><html:text property="txtNroDocBenef" size="20" maxlength="20" onkeypress="return selectVal(event)" /></SPAN></TD>
					</TR>
					<c:if test="${tipoElemento.tipoElementoSeguridad=='2'}">
					<TR bgcolor="#e5e5de">
						<TD>&nbsp;</TD>
						<TD height="20" width="216" bgcolor="#e5e5de"><span class="textizqn"><B>
						Ingresar la Siguiente Coordenada </B></span><LABEL><B>&nbsp; </B><FONT size="+1"><SPAN
							style="color: #a80000"><B><c:if test="${resultCoord.coordConcat eq null}"><BR/>No disponible</c:if>
							<c:if test="${resultCoord.coordConcat ne null}"><BR/><c:out value="${resultCoord.coordConcat}"/></c:if>
							
							</B></SPAN></FONT></LABEL><BR>
						<INPUT type="password" name="txtCoordenada" size="6" maxlength="2" <c:if test="${resultCoord.coordConcat eq null}"> readonly="readonly"</c:if> onkeypress="return soloNumerosAll(event)"><BR>
						
						
						<FONT size="-1" face="Arial"><FONT size="-1" face="Arial"><B></B></font></font></TD>
					</TR>
					</c:if>
					<TR>
							<TD height="20" colspan="2" bgcolor="#e5e5de"><BR>
							</TD>
					</TR>
					
					<c:if test="${tipoElemento.tipoElementoSeguridad=='5'}">
					
						<TR bgcolor="#E5E5DE">
						
					
						<TD height="20" width="200" bgcolor="#e5e5de"><span class="textizqn"><B>
						Ingresar los 6 dígitos del TOKEN </B></span><LABEL><B>&nbsp; </B><FONT size="+1"><SPAN
							style="color: #a80000"><B><BR/>
							
							</B></SPAN></FONT></LABEL>
						<INPUT type="password" name="txtCoordenada" size="10" maxlength="6"  onkeypress="return soloNumerosAll(event)"><BR>
						
						
							<TD height="20" width="216" bgcolor="#e5e5de"><img
								border="0" src="<%=request.getContextPath()%>/Images/token_p.gif" 
								></TD>
						</TR>
					</c:if>
					
					<c:if test="${tipoElemento.tipoElementoSeguridad=='2'}">
						<TR>
						<TD colspan="2" height="20" width="40%" class="textizqn"  bgcolor="#e5e5de"><b><u>Ejemplo:</u>
							</b><br>
								Al solicitarle la coordenada <B>6 - F</B>, deberás buscar la fila correspondiente al <b>número
							6</b> y la columna de la <B>letra  F</B>, en la unión de ambos, obtendrás un número, éste número deberás ingresarlo para aprobar la operación.</TD>
						
						</TR>
					</c:if>
					<c:if test="${tipoElemento.tipoElementoSeguridad=='5'}">
					<TR>
						<TD colspan="3" height="20" width="40%" class="textizqn"  bgcolor="#e5e5de"><b><u>Nota:</u>
							</b><br>
								Tener en cuenta que los 6 dígitos cambian cada minuto por lo cual debe ingresar antes que la 
								barra de tiempo se haya consumido.
								</TD>
						
					</TR>
					</c:if>

				</TBODY>
			</TABLE></CENTER><TABLE border="0" cellpadding="0" cellspacing="1" width="405">
					<TBODY>
						<TR>
							<TD align="justify" colspan="2" height="16"><br><SPAN class="texto">${mensajepieAfTel}</SPAN></TD>
						</TR>
						<logic:messagesPresent>
							<TR>
								<TD colspan="2" height="55">
								<TABLE border="0" cellspacing="1" cellpadding="0"
									class="fondoMensajeError">
									<TR>
										<TD class="textoMensajeError" align="left" width="400"><html:errors />
										</TD>
									</TR>
								</TABLE>
								</TD>
							</TR>
						</logic:messagesPresent>

					</TBODY>
				</TABLE>
				<BR>
				<BR>
			<IMG border="0" name="imgContinuar" src="<%=request.getContextPath()%>/Images/<bean:write name="CARPETA"/>/img_afiliar.gif" width="66" height="20" onclick="afiliar();" style="cursor:hand">
			<BR>
			
			</TD>
		</TR>
	</TBODY>
</TABLE>

<html:hidden property="txtdestipdoc" />

</html:form>
</BODY>
</html:html>
