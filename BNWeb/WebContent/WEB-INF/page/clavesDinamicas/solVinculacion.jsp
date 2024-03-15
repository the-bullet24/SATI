<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld"	prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld"	prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld"	prefix="logic"%>
<html:html>
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<script language="JavaScript" src="<%=request.getContextPath()%>/js/util.js"></script>
<link href="<%=request.getContextPath()%>/estilos/style.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/estilos/style1.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="<%=request.getContextPath()%>/js/util.js"></script>
<style type="text/css">
<!--
body {
	background-color: #EFEFEF;
}
-->
</style>
<style>
<!--
.texto {
font-family: Arial, Helvetica, sans-serif; 
font-size: 12px; 
font-style: normal; 
}
-->
</style>
<SCRIPT language="javascript">

	function continuar(){
		var form = document.forms[0];
		if(document.forms[0].cmbTipoDocBenef.value=="001"){ // DNI 001 - 8 - N
			document.forms[0].txtNroDoc.maxLength="8";
		} else if(document.forms[0].cmbTipoDocBenef.value=="002"){ // FFPP 002 - 9 -  A
			document.forms[0].txtNroDoc.maxLength="9";
			
		} else if(document.forms[0].cmbTipoDocBenef.value=="003"){ // FFAA 003 - 10 - A 
			document.forms[0].txtNroDoc.maxLength="10";
			
		} else if(document.forms[0].cmbTipoDocBenef.value=="004"){ // C.E. 004 - 8 - N
			document.forms[0].txtNroDoc.maxLength="8";
		} else if(document.forms[0].cmbTipoDocBenef.value=="006"){ // PASS 006 - 7 - N
			document.forms[0].txtNroDoc.maxLength="7";
		} else if(document.forms[0].cmbTipoDocBenef.value=="007"){ // P.N. 007 - 15 - A
			document.forms[0].txtNroDoc.maxLength="15";
			
		} else if(document.forms[0].cmbTipoDocBenef.value=="008"){ // L.M. 008 - 10 - N
			document.forms[0].txtNroDoc.maxLength="10";
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
		if (validacampo("txtApellidoPaterno")){ 
			alert('Es necesario ingresar el apellido paterno del Representante' ); return;}
		if (solocaracterespermitidos2("txtApellidoPaterno")){
			alert('El apellido paterno del Representante presenta caracteres inválidos' ); 
			return;
		}
		if (validacampo("txtApellidoMaterno")){ 
			alert('Es necesario ingresar el apellido materno del Representante' ); return;}
		if (solocaracterespermitidos2("txtApellidoMaterno")){
			alert('El apellido materno del Representante presenta caracteres inválidos' ); 
			return;
		}
		if (validacampo("txtNombre")){ 
			alert('Es necesario ingresar el nombre del Representante' ); return;}
		if (solocaracterespermitidos2("txtNombre")){
			alert('El nombre del Representante presenta caracteres inválidos' ); 
			return;
		}

		if (validacampo("cmbTipoDocBenef")){
			alert('Es necesario seleccionar un Tipo de Documento del Representante');
			return;
		}

		if (validacampo("txtNroDoc")){
			alert('Es necesario ingresar el Número de documento del Representante');
			return;
		}
			
		if (validalongitud("txtNroDoc",document.forms[0].txtNroDoc.maxLength)){
			alert('Su número de documento debe ser de '+ document.forms[0].txtNroDoc.maxLength +' Digitos, no menos');
			return;
		}

		if(document.forms[0].cmbTipoDocBenef.value=="001"){ // DNI 001 - 8 - N
			if (trim(document.forms[0].txtNroDoc.value) == "00000000"){
				alert('El número de documento ingresado no es correcto');
				return;
			}
		} else if(document.forms[0].cmbTipoDocBenef.value=="002"){ // FFPP 002 - 9 -  A
			if (trim(document.forms[0].txtNroDoc.value) == "000000000"){
				alert('El número de documento ingresado no es correcto');
				return;
			}
		} else if(document.forms[0].cmbTipoDocBenef.value=="003"){ // FFAA 003 - 10 - A 
			if (trim(document.forms[0].txtNroDoc.value) == "0000000000"){
				alert('El número de documento ingresado no es correcto');
				return;
			}
		} else if(document.forms[0].cmbTipoDocBenef.value=="004"){ // C.E. 004 - 8 - N
			if (trim(document.forms[0].txtNroDoc.value) == "00000000"){
				alert('El número de documento ingresado no es correcto');
				return;
			}
		} else if(document.forms[0].cmbTipoDocBenef.value=="006"){ // PASS 006 - 7 - N
			if (trim(document.forms[0].txtNroDoc.value) == "0000000"){
				alert('El número de documento ingresado no es correcto');
				return;
			}
		} else if(document.forms[0].cmbTipoDocBenef.value=="007"){ // P.N. 007 - 15 - A
			if (trim(document.forms[0].txtNroDoc.value) == "000000000000000"){
				alert('El número de documento ingresado no es correcto');
				return;
			}
		} else if(document.forms[0].cmbTipoDocBenef.value=="008"){ // L.M. 008 - 10 - N
			if (trim(document.forms[0].txtNroDoc.value) == "0000000000"){
				alert('El número de documento ingresado no es correcto');
				return;
			}
		} 
		if(document.forms[0].chkAceptar.checked==false){
				alert('Debe de Aceptar las condiciones');
				return;
		}
		
		if (form.txtNumClave.value.length < 6){
			alert('Su Clave de Internet debe ser de 6 Digitos no menos');
			return;
		}
		
		form.action="<%=request.getContextPath()%>/claveDinamica.do?metodo=continuarVinc";
		form.submit();
		form.imgContinuar.removeAttribute("onclick");
		form.imgContinuar.setAttribute("onclick", "");
	}

	function selectVal(e){// DOCUMENTO - LONGITUD - NUMERICO/ALFANUNMERICO

		if(document.forms[0].cmbTipoDocBenef.value=="001"){ // DNI 001 - 8 - N
			document.forms[0].txtNroDoc.maxLength="8";
			return soloNumerosAll(e);
		} else if(document.forms[0].cmbTipoDocBenef.value=="002"){ // FFPP 002 - 9 -  A
			document.forms[0].txtNroDoc.maxLength="9";
			
		} else if(document.forms[0].cmbTipoDocBenef.value=="003"){ // FFAA 003 - 10 - A 
			document.forms[0].txtNroDoc.maxLength="10";
			
		} else if(document.forms[0].cmbTipoDocBenef.value=="004"){ // C.E. 004 - 15 - A
			document.forms[0].txtNroDoc.maxLength="15";
		} else if(document.forms[0].cmbTipoDocBenef.value=="006"){ // PASS 006 - 15 - A
			document.forms[0].txtNroDoc.maxLength="15";
		} else if(document.forms[0].cmbTipoDocBenef.value=="007"){ // P.N. 007 - 15 - A
			document.forms[0].txtNroDoc.maxLength="15";
		} else if(document.forms[0].cmbTipoDocBenef.value=="008"){ // L.M. 008 - 10 - N
			document.forms[0].txtNroDoc.maxLength="10";
			return soloNumerosAll(e);
		} 
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

	function selectValCampos(e){

		if(document.forms[0].cmbTipoDocIdent.value=="001"){ 		// DNI 1 - 8 - N
			document.forms[0].txtNumDoc.maxLength="8";
			return soloNumerosAll(e);
		}else if(document.forms[0].cmbTipoDocIdent.value=="003"){  // Carnet EXTRANJERIA  3 - 8 - N
			document.forms[0].txtNumDoc.maxLength="10";
		}
	}

	function upperCase(x)
	{
	var y=document.getElementById(x).value
	document.getElementById(x).value=y.toUpperCase()
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


		function limpiar(){// DOCUMENTO - LONGITUD - NUMERICO/ALFANUNMERICO
		if(document.forms[0].cmbTipoDocBenef.value=="001"){// DNI 001 - 8 - N
			document.forms[0].txtNroDoc.maxLength="8";
			document.forms[0].txtNroDoc.size="7";
		} else if(document.forms[0].cmbTipoDocBenef.value=="002"){ // FFPP 002 - 9 -  A
			document.forms[0].txtNroDoc.maxLength="9";
			document.forms[0].txtNroDoc.size="8";
		} else if(document.forms[0].cmbTipoDocBenef.value=="003"){ // FFAA 003 - 10 - A 
			document.forms[0].txtNroDoc.maxLength="10";
			document.forms[0].txtNroDoc.size="9";
		} else if(document.forms[0].cmbTipoDocBenef.value=="004"){ // C.E. 004 - 15 - a
			document.forms[0].txtNroDoc.maxLength="15";
			document.forms[0].txtNroDoc.size="14";
		} else if(document.forms[0].cmbTipoDocBenef.value=="006"){ // PASS 006 - 15 - A
			document.forms[0].txtNroDoc.maxLength="15";
			document.forms[0].txtNroDoc.size="14";
		} else if(document.forms[0].cmbTipoDocBenef.value=="007"){ // P.N. 007 - 15 - A
			document.forms[0].txtNroDoc.maxLength="15";
			document.forms[0].txtNroDoc.size="14";
		} else if(document.forms[0].cmbTipoDocBenef.value=="008"){ // L.M. 008 - 10 - N
			document.forms[0].txtNroDoc.maxLength="10";
			document.forms[0].txtNroDoc.size="9";
		} 
	
	}
	
	/*
		Functions para Limpiar la clave
	*/
	function cleanPass(){
		cleanPassword("txtNumClave");
	}
	
	/*
		Functions para Teclado
	*/

	function evalRanTable(valor){
		document.forms[0].elements['txtNumClave'].value = evaluarTeclado6(document.forms[0].elements['txtNumClave'].value,valor);
	}
	
</SCRIPT>
<TITLE>tran_int_ah.html</TITLE>
</HEAD>
<body onselectstart="return false" ondragstart="return false" oncontextmenu="return false"  onKeyDown="return cancelRefresh(event);">
<html:form action="/claveDinamica.do" method="post">
<input type="hidden" name="metodo">
<INPUT type="hidden" name="transaccion" value="TL00">
<TABLE width="100%" border="0" align="center">
	<TBODY>
		<TR align="left" valign="top">
			<TD>
			<DIV align="center"><BR>
			<SPAN class="text8centrn"><FONT size="2"
				face="Arial, Helvetica, sans-serif"><B><FONT color="#000000">SOLICITUD DE AFILIACIÓN A LA TARJETA DE COORDENADAS</FONT></B></FONT></SPAN></DIV>
			</TD>
		</TR>
		<TR align="left" valign="top">
			<TD>
			<DIV align="center"><SPAN class="text8centrn"><FONT color="black"
				size="2" face="Arial, Helvetica, sans-serif"><B>
					<bean:write name="<%=pe.cosapi.common.ConstanteSesion.USUARIO_EN_SESION%>" property="nombre"/>
				</B></FONT></SPAN></DIV>
			</TD>
		</TR>
		<TR align="left" valign="top">
			<TD height="20">&nbsp;</TD>
		</TR>
		<TR align="left" valign="top">
			<TD width="100%" height="733">
			<CENTER>
			<TABLE border="0" width="419" cellspacing="1" cellpadding="0" style="font-size: 2ex">
				<TBODY>
					<TR bgcolor="#e5e5de">
						
					</TR>
					<TR>
						<TD colspan="2" align="justify"><SPAN style="text-align: justify" class="texto">${mensajeSolcCabecera} </SPAN></TD>
					</TR>
					<TR>
						<TD>&nbsp;</TD>
					</TR>
					<TR>
						<TD colspan="2" align="justify"><SPAN style="text-align: justify" class="texto">${mensajeSolcCabeceraInfo} </SPAN></TD>
					</TR>
					<TR>
						<TD>&nbsp;</TD>
					</TR>
					<TR>
						<TD colspan="2" align="justify"><SPAN style="text-align: justify" class="texto">${mensajeSolcCabeceraInfo1} </SPAN></TD>
					</TR>
					<TR>
						<TD>&nbsp;</TD>
					</TR>
								
					<TR bgcolor="#e5e5de">
						<TD colspan="5" ALIGN="CENTER"><BR>
							<b class="textizqn">Condiciones Generales</b><BR>
						<TEXTAREA style="text-align: justify; font-stretch: normal" rows="12" cols="70" name="TXTUNO0" class="texto">${mensajeCondicion}
						</TEXTAREA><BR>
						<INPUT type="checkbox" name="chkAceptar" value="S" class="textizqn"> <B class="textizqn">Acepto	condiciones</B><BR><BR></TD>
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
						<TD colspan="2" height="20" align="center"><SPAN class="textizqn"><B><FONT color="white">Datos del Representante</FONT></B></SPAN></TD>
					</TR>
					<TR bgcolor="#e5e5de">
						<TD height="20" width="200"><SPAN class="textizqn"><B>Apellido Paterno:</B></SPAN></TD>
						<TD height="20" width="216"><SPAN class="textizqn"><html:text onkeyup="upperCase(this.id)" styleId ="txtApellidoPaternoBenef" property="txtApellidoPaterno" size="38" maxlength="20" /></SPAN></TD>
					</TR>
						<TR bgcolor="#e5e5de">
						<TD height="20" width="200"><SPAN class="textizqn"><B>Apellido Materno:</B></SPAN></TD>
						<TD height="20" width="216"><SPAN class="textizqn"><html:text onkeyup="upperCase(this.id)" styleId ="txtApellidoMaternoBenef" property="txtApellidoMaterno" size="38" maxlength="20" /></SPAN></TD>
					</TR>
					<TR bgcolor="#e5e5de">
						<TD height="20" width="200"><SPAN class="textizqn"><B>Nombres:</B></SPAN></TD>
						<TD height="20" width="216"><SPAN class="textizqn"><html:text onkeyup="upperCase(this.id)" styleId ="txtNombreBenef" property="txtNombre" size="38" maxlength="20" /></SPAN></TD>
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
						<TD height="20" width="216"><SPAN class="textizqn"><html:text property="txtNroDoc" size="20" maxlength="20" onkeypress="return selectVal(event)" /></SPAN></TD>
					</TR>
					<TR bgcolor="#e5e5de">
						<TD  height="20" width="50%" nowrap><FONT face="Arial" size="2"><B></B></FONT><SPAN class="textizqn"><B>Clave Internet:</B></SPAN><BR><html:password property="txtNumClave" size="6" maxlength="6" readonly="true" /></TD>
						<TD colspan="2" height="20" width="50%">
						<jsp:include page="/WEB-INF/page/sistema/teclado.jsp" ></jsp:include></TD>
					</TR>
					
						
					<logic:messagesPresent>
						<TR>
							<TD colspan="3" width="100%" align="center">
								<table  border="0" cellspacing="1" cellpadding="0" class="fondoMensajeError" width="100%">
									<tr>
										<td class="textoMensajeError" align="center" width="100%">
											<html:errors/>
										</td>
									</tr>
								</table>
							</TD>
						</TR>
					</logic:messagesPresent>
					<TR>
						<TD colspan="3" height="24" width="100%"><br><span class="texto">${mensajepiePagoGiro}</span><br></TD>
					</TR>
					<TR>
						<TD colspan="3" align="center" height="31">
			&nbsp;&nbsp;&nbsp;&nbsp;
			<IMG border="0" name="imgContinuar" src="<%=request.getContextPath()%>/Images/<bean:write name="CARPETA"/>/img_continuar.gif" onclick="javascript:continuar();">
					
						</TD>
					</TR>

				</TBODY>
			</TABLE>
			</CENTER>
			</TD>
		</TR>
	</TBODY>
</TABLE>

</html:form>
</BODY>
</html:html>
