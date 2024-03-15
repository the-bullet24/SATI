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
.textCampo {
text-align: center;
}
-->
</STYLE>
<SCRIPT language="javascript">
	function afiliar(){
		
		var form = document.forms[0];
		// Validando que el Número de DNI 
		if (validacampo("txtNumDoc")){ 
			alert('Es necesario ingresar el número de documento' ); 
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
			alert('Es necesario ingresar el apellido paterno' ); return;}
		if (solocaracterespermitidos("txtApellidoPaternoBenef")){
			alert('El apellido paterno presenta caracteres inválidos.' ); 
			return;
		}
		if (validacampo("txtApellidoMaternoBenef")){ 
			alert('Es necesario ingresar el apellido materno' ); return;}
		if (solocaracterespermitidos("txtApellidoMaternoBenef")){
			alert('El apellido materno presenta caracteres inválidos.' ); 
			return;
		}
		if (validacampo("txtNombreBenef")){ 
			alert('Es necesario ingresar el nombre' ); return;}
		if (solocaracterespermitidos("txtNombreBenef")){
			alert('El nombre presenta caracteres inválidos.' ); 
			return;
		}
		// Validando que seleccione  un banco destino
		if (validacampo("cmbBancoDestino")){ 
			alert('Es necesario seleccionar un banco destino' ); return;}
		// Validando que seleccione  un tipo de tarjeta
		if (validacampo("cmbTipoTarjeta")){ 
			alert('Es necesario seleccionar un tipo de tarjeta' ); return;}		
		// Validando que ingrese el numero de tarjeta
		if (validacampo("txtNroTarjeta")){ 
			alert('Es necesario ingresar el numero de la tarjeta de otro banco' ); return;}

		if (validarSiNumero(document.forms[0].txtNroTarjeta.value)){
			document.forms[0].txtNroTarjeta.focus();			
			alert('El número de la tarjeta solo acepta números...'); 
			return;
		}
		
		if (validalongitud("txtNroTarjeta","15")){
			alert('El número de número de la tarjeta debe ser de 15 o 16 Dígitos, no menos');
			return;
		}
		// Vaidando que ingrese el nombre de la transferencia
		if (validacampo("txtNombreTransferencia")){
			alert('Es necesario ingresar el nombre de la tarjeta');
			return;
		}
		if (solocaracterespermitidos2("txtNombreTransferencia")){
			alert('El nombre de la tarjeta presenta caracteres inválidos' ); 
			return;
		}
	
		
		// Validando que no sean Tarjeta de Mismo Banco
		numTarjeta  = document.forms[0].txtNroTarjeta.value;
		numListCard = numTarjeta.substring(0,4)
		if (numListCard == "4214" ||  numListCard == "8018"){
			alert('Ingrese un Número de Tarjeta de Otro Banco');
			return;
		}
		// Rellenando a 16 dígitos con "0" si es de longitud 15
		if (document.forms[0].txtNroTarjeta.value.length == 15){
			document.forms[0].txtNroTarjeta.value = "0"+document.forms[0].txtNroTarjeta.value;
		}

		document.forms[0].action = "<%=request.getContextPath()%>/AfilOtroBanco.do?metodo=afiliar";
		document.forms[0].submit();
		form.imgContinuar.removeAttribute("onclick");
		form.imgContinuar.setAttribute("onclick", "");
	}

	function upperCase(x)
	{
	var y=document.forms[0].txtNumDoc.value
	document.forms[0].txtNumDoc.value=y.toUpperCase()
	}

	function selectVal(e){

		if(document.forms[0].cmbTipoDocIdent.value=="001"){ 		// DNI 1 - 8 - N
			document.forms[0].txtNumDoc.maxLength="8";
			return soloNumerosAll(e);
		}else if(document.forms[0].cmbTipoDocIdent.value=="003"){  // Carnet EXTRANJERIA  3 - 8 - N
			document.forms[0].txtNumDoc.maxLength="10";
		}
	}

	function limpiar(){
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
<body oncontextmenu="return false" onselectstart="return false" ondragstart="return false" onKeyDown="return cancelRefresh(event)" bgcolor="white" onload="limpiar();">
<html:form type="pe.bn.afiliacion.action.form.AfiliacionOtroBancoForm" action="/AfilOtroBanco.do" method="post">
<input type="hidden" name="validar" 	value="true">
<input type="hidden" name="coordenada"	value="<%=request.getSession().getAttribute("resultCoordenada") %>">

<TABLE width="100%" border="0" align="center">
	<TBODY>
		<TR align="left" valign="top">
			<TD>
			<DIV align="center"><SPAN class="text8centrn"><FONT size="2"
				face="Arial, Helvetica, sans-serif"><B><FONT color="#000000"><BR>
				AFILIACION DE TARJETAS DE CREDITO DE OTRO BANCO</FONT></B></FONT></SPAN></DIV>
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
			<TD>&nbsp;</TD>
		</TR>
		
		<TR align="left" valign="top">
			<TD width="100%" align="center">
			<CENTER>
			
			
			</CENTER>
			<CENTER><TABLE border="0" cellspacing="1" cellpadding="0" width="419">
				<TBODY>
					<TR>
						<TD colspan="4" height="20" align="center"><SPAN class="texto">${mensajecabeceraafiliaciontarjetasotrosbancos}</SPAN></TD>
					</TR>
					<TR>
							<TD height="20" colspan="4"><BR></TD>
					</TR>
					<TR bgcolor="#C9242C">
						<TD colspan="4" height="20" align="center" ><SPAN class="textizqn"><B><FONT color="white">Datos Personales del Titular</FONT></B></SPAN></TD>
					</TR>
					<TR bgcolor="#e5e5de">
						<TD colspan="2" height="20" nowrap width="176"><SPAN class="textizqn"><B>Tipo Doc. Identidad:</B></span></TD>
						<TD colspan="2" height="20" nowrap width="240">
						<html:select property="cmbTipoDocIdent" onchange="limpiar()">
							<html:options collection="lstDocumento" property="codigo" labelProperty="descripcion" />
						</html:select>
						</TD>
					</TR>
					<TR bgcolor="#e5e5de">
						<TD colspan="2" height="20" nowrap width="176"><SPAN class="textizqn"><B>Nro. Doc. Identidad:</B></span></TD>
						<TD colspan="2" height="20" nowrap width="240"><html:text onkeyup="upperCase(this.id)" property="txtNumDoc" size="7" maxlength="10" onkeypress="return selectVal(event)" /></TD>
					</TR>
					<TR bgcolor="#e5e5de">
						<TD colspan="2" height="20" nowrap width="176"><SPAN class="textizqn"><B>Fecha Nacimiento:</B></span></TD>
						<TD colspan="2" height="20" nowrap width="240"><html:text  styleClass="textCampo" property="txtDia" size="1"  maxlength="2" onkeypress="return soloNumerosAll(event)"/>
						<html:select property="cmbMes">
							<html:options collection="lstMes" property="codigo" labelProperty="descripcion" />
						</html:select><html:text  styleClass="textCampo" property="txtAnio" size="2" maxlength="4"  onkeypress="return soloNumerosAll(event)"/></TD>
					</TR>
					<TR bgcolor="#e5e5de">
						<TD colspan="2" height="20" nowrap width="176"><SPAN class="textizqn"><B>Sexo:</B></span></TD>
						<TD colspan="2" height="20" nowrap width="240"><SPAN class="textizqn">
						<html:radio property="rdnSexo" value="M" />Masculino
						<html:radio property="rdnSexo"  value="F"/>Femenino</span></TD>
					</TR>
					<TR bgcolor="#e5e5de">
						<TD colspan="2" height="20" nowrap width="176"><SPAN class="textizqn"><B>Email:</B></span></TD>
						<TD colspan="2" height="20" nowrap width="240"><html:text property="txtMail" size="30" maxlength="60" /></TD>
					</TR>
					<TR bgcolor="#C9242C">
						<TD colspan="4" height="20" align="center" nowrap><SPAN class="textizqn"><B><FONT color="white">Tarjeta Otro Banco</FONT></B></SPAN></TD>
					</TR>
					<TR bgcolor="#e5e5de">
						<TD colspan="2" height="20" nowrap width="176"><SPAN class="textizqn"><B>Apellido Paterno: (*)</B></SPAN></TD>
						<TD colspan="2" height="20" nowrap width="240"><SPAN class="textizqn"><html:text property="txtApellidoPaternoBenef" size="30" maxlength="20" /></SPAN></TD>
					</TR>
					<TR bgcolor="#e5e5de">
						<TD colspan="2" height="20" nowrap width="176"><SPAN class="textizqn"><B>Apellido Materno: (*)</B></SPAN></TD>
						<TD colspan="2" height="20" nowrap width="240"><SPAN class="textizqn"><html:text property="txtApellidoMaternoBenef" size="30" maxlength="20" /></SPAN></TD>
					</TR>
					<TR bgcolor="#e5e5de">
						<TD colspan="2" height="20" nowrap width="176"><SPAN class="textizqn"><B>Nombres: (*)</B></SPAN></TD>
						<TD colspan="2" height="20" nowrap width="240"><SPAN class="textizqn"><html:text property="txtNombreBenef" size="30" maxlength="18" /></SPAN></TD>
					</TR>
					<TR bgcolor="#e5e5de">
						<TD colspan="2" height="20" nowrap width="176"><SPAN class="textizqn"><B>Banco Destino:</B></SPAN></TD>
						<TD colspan="2" height="20" nowrap width="240"><SPAN class="textizqn">
						<html:select property="cmbBancoDestino" style="background-color:#FEFEE9">
							<html:options collection="lstBancoDestino" property="codigo" labelProperty="descripcion" />
						</html:select>
						</SPAN></TD>
					</TR>

					<TR bgcolor="#e5e5de">
						<TD colspan="2" height="20" nowrap width="176"><SPAN class="textizqn"><B>Tipo de Tarjeta</B></SPAN></TD>
						<TD colspan="2" height="20" nowrap width="240"><SPAN class="textizqn">
						<html:select property="cmbTipoTarjeta" style="background-color:#FEFEE9">
							<html:options collection="lstTipoTarjeta" property="codigo" labelProperty="descripcion" />
						</html:select>
						</SPAN></TD>
					</TR>

					<TR bgcolor="#e5e5de">
						<TD colspan="2" height="20" nowrap width="176"><SPAN class="textizqn"><B>Nro. de Tarjeta:</B></SPAN></TD>
						<TD colspan="2" height="20" nowrap width="240"><SPAN class="textizqn"><html:text property="txtNroTarjeta" size="16" maxlength="16"  onkeypress="return soloNumerosAll(event)"/></SPAN></TD>
					</TR>

					<TR bgcolor="#e5e5de">
						<TD colspan="2" height="20" nowrap width="176"><SPAN class="textizqn"><B>Nombre de Tarjeta:</B></SPAN></TD>
						<TD colspan="2" height="20" nowrap width="240"><SPAN class="textizqn"><html:text property="txtNombreTransferencia" size="30" maxlength="45"/></SPAN></TD>
					</TR>

						<c:if test="${tipoElemento.tipoElementoSeguridad=='2'}">
					<TR bgcolor="#e5e5de">
						
						<TD colspan="2" height="20"  bgcolor="#e5e5de" width="140"></TD>
						<TD  height="20" colspan="2" bgcolor="#e5e5de" width="40%"><span class="textizqn"><B>
						Ingresar la Siguiente Coordenada </B></span><LABEL><B>&nbsp; </B><FONT size="+1"><SPAN
							style="color: #a80000"><BR/><B><c:if test="${resultCoord.coordConcat eq null}">No disponible</c:if>
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
							<TD height="20" colspan="6" bgcolor="#e5e5de"><BR>
							</TD>
					</TR>
					<c:if test="${tipoElemento.tipoElementoSeguridad=='2'}">
					<TR>
						<TD colspan="6" height="20" width="40%" class="textizqn"  bgcolor="#e5e5de"><b><u>Ejemplo:</u>
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

				</TBODY>
			</TABLE></CENTER>
			<BR>
				<TABLE border="0" cellpadding="0" cellspacing="1" width="430">
					<TBODY>
						<TR>
							<TD colspan="4" width="430"><SPAN class="texto">
							${mensajecaracteresafiliaciontarjetasotrosbancos}
							</SPAN></TD>
						</TR>

						<logic:messagesPresent>
							<TR>
								<TD colspan="4" width="430">
								<TABLE border="0" cellspacing="1" cellpadding="0"
									class="fondoMensajeError">
									<TR>
										<TD class="textoMensajeError" align="left" width="430"><html:errors />
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
</html:form>
</BODY>
</html:html>
