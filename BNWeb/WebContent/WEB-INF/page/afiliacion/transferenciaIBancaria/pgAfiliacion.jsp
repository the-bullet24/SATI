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

.textCampo {
text-align: center;
}
}
-->
</STYLE>
<SCRIPT language="javascript">
	function afiliar(){
		var form = document.forms[0];
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

		// Validando que el Número de DNI no tenga caracteres que no sean números
		if (validacampo("txtCuentaCCI")){ 
			alert('Es necesario ingresar el número CCI' ); return;}

		if (validarSiNumero(document.forms[0].txtCuentaCCI.value)){
			alert('El número de CCI solo acepta números...');
			return;
		}		

		if (validalongitud("txtCuentaCCI","20")){
			alert('El número CCI debe ser de 20 Digitos, no menos');
			return;
		}
		// Validando que no sean cuenta de Mismo Banco
		var numCuenta  = document.forms[0].txtCuentaCCI.value;
		var numcta = numCuenta.substring(0,3)
		if (numcta == "018"){
			alert('Ingrese un CCI de otro banco');
			document.forms[0].txtCuentaCCI.focus();
			return;
		}
		// Valida que se haya seleccionado un elemento radiobuttom cuenta propia
		if (validaRadios("rdnCuentaPropia")){
			alert('Es necesario seleccionar el campo cuenta propia');
			return;
		}
		if (validacampo("txtNombreBenef")){ 
			alert('Es necesario ingresar el nombre del beneficiario' ); return;}
		if (solocaracterespermitidos("txtNombreBenef")){
			alert('El nombre del beneficiario presenta caracteres inválidos' ); 
			return;
		}
		
	
		document.forms[0].action = "<%=request.getContextPath()%>/AfilTransferenciaInterBancaria.do?metodo=afiliar";
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

	/*
		Functions para Teclado
	*/
	function evalRanTable(valor){
		document.forms[0].txtNumClave.value  = evaluarTeclado6(document.forms[0].txtNumClave.value,valor);
	}

	/*
		Functions para Limpiar la clave
	*/
	function cleanPass(){
		cleanPassword("txtNumClave");
	}

	function Ayuda(){
		ventana_secundaria1=window.open('<%=request.getContextPath()%>/AfilTransferenciaInterBancaria.do?metodo=ayuda',"BN","toolbar=0,location=0,width=310,height=95,status=0, scrollbars=no resizable=no,  top=" + ((screen.height/2)-(170/2))+", left="+((screen.width/2)-(280/2)));
		setTimeout("ventana_secundaria1.close();",15000);	
	
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
<body  oncontextmenu="return false" onselectstart="return false" ondragstart="return false"  onKeyDown="return cancelRefresh(event)"  bgcolor="white" onload="limpiar();">
<html:form type="pe.bn.afiliacion.action.form.AfiliacionTransferenciaInterBancariaForm" action="/AfilTransferenciaInterBancaria.do" method="post">
<input type="hidden" name="validar"		value="true">
<input type="hidden" name="coordenada"	value="<%=request.getSession().getAttribute("resultCoordenada") %>">
<TABLE width="99%" border="0" align="center">
	<TBODY>
		
		<TR align="left" valign="top">
			<TD>
			<DIV align="center"><BR>
				<SPAN class="text8centrn"><FONT size="2"
				face="Arial, Helvetica, sans-serif"><B><FONT color="#000000">AFILIACION TRANSFERENCIAS INTERBANCARIAS</FONT></B></FONT></SPAN></DIV>
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
			<TD width="100%" align="center" height="648">
			<CENTER><TABLE border="0" cellspacing="1" cellpadding="0" width="419">
				<TBODY>
					<TR>
						<TD colspan="4" height="20" width="100%"><SPAN style="text-align: justify" class="texto">${mensajeafiliacion} </SPAN></TD>
					</TR>
					<TR>
						<TD colspan="4" height="20" width="100%"><BR></TD>
					</TR>
					<TR bgcolor="#C9242C">
						<TD colspan="4" height="20" align="center" width="100%"><SPAN class="textizqn"><B><FONT color="white">Datos Personales del Titular</FONT></B></SPAN></TD>
					</TR>
					<TR bgcolor="#e5e5de">
						<TD colspan="2" height="20" width="45%"><SPAN class="textizqn"><B>Tipo Doc. Identidad:</B></SPAN></TD>
						<TD colspan="2" height="20" width="55%"><SPAN class="textizqn">
						<html:select property="cmbTipoDocIdent" style="background-color:#FEFEE9" onchange="limpiar()">
							<html:options collection="lstDocumento" property="codigo" labelProperty="descripcion" />
						</html:select>
						</SPAN></TD>
					</TR>
					<TR bgcolor="#e5e5de">
						<TD colspan="2" height="20" width="45%" nowrap><SPAN class="textizqn"><B>Nro. Doc. Identidad:</B></SPAN></TD>
						<TD colspan="2" height="20" width="55%"><SPAN class="textizqn"><html:text onkeyup="upperCase(this.id)" property="txtNumDoc" size="7" maxlength="10" onkeypress="return selectVal(event)"/></SPAN></TD>
					</TR>
					<TR bgcolor="#e5e5de">
						<TD colspan="2" height="20" width="45%" nowrap><SPAN class="textizqn"><B>Fecha de Nacimiento:</B></SPAN></TD>
						<TD colspan="2" height="20" width="55%"><SPAN class="textizqn"><html:text  styleClass="textCampo" property="txtDia" size="1" maxlength="2" onkeypress="return soloNumerosAll(event)"/> 
						<html:select property="cmbMes" style="background-color:#FEFEE9" >
							<html:options collection="lstMes" property="codigo" labelProperty="descripcion" />
						</html:select>
						<html:text property="txtAnio" size="2" maxlength="4"   styleClass="textCampo" onkeypress="return soloNumerosAll(event)"/></SPAN></TD>
					</TR>
					<TR bgcolor="#e5e5de">
						<TD colspan="2" height="20" width="45%" nowrap><SPAN class="textizqn"><B>Sexo:</B></SPAN></TD>
						<TD colspan="2" height="20" width="55%"><SPAN class="textizqn">
						<html:radio property="rdnSexo" value="M" />Masculino
						<html:radio property="rdnSexo"  value="F"/>Femenino</SPAN></TD>
					</TR>
					<TR bgcolor="#e5e5de">
						<TD colspan="2" height="20" width="45%" nowrap><SPAN class="textizqn"><B>Email:</B></SPAN></TD>
						<TD colspan="2" height="20" width="55%"><SPAN class="textizqn"><html:text property="txtMail" size="35" maxlength="60" /></SPAN></TD>
					</TR>
					<TR bgcolor="#C9242C">
						<TD colspan="4" height="20" align="center" width="100%"><SPAN class="textizqn"><FONT
								color="white"><B>Datos Cuenta Destino</B></FONT></SPAN></TD>
					</TR>
					<TR bgcolor="#e5e5de">
						<TD colspan="2" height="20" width="45%"><SPAN class="textizqn"><B>Nro. CCI:</B></SPAN></TD>
						<TD colspan="2" height="20" width="55%"><SPAN class="textizqn"><html:text property="txtCuentaCCI" size="20" maxlength="20" onkeypress="return soloNumerosAll(event)" />
						<A href="javascript:Ayuda()"><IMG border="0" src="<%=request.getContextPath()%>/Images/img_ayuda.gif"  width="17" height="17"></A></SPAN></TD>
					</TR>
					<TR bgcolor="#e5e5de">
						<TD colspan="2" height="20" width="45%"><SPAN class="textizqn"><B>Cuenta Propia: (*)</B></SPAN></TD>
						<TD colspan="2" height="20" width="55%"><SPAN class="textizqn"><html:radio property="rdnCuentaPropia" value="S" />Si
						<html:radio property="rdnCuentaPropia" value="N" />No</SPAN>
						</TD>
					</TR>
					<TR bgcolor="#e5e5de">
						<TD colspan="2" height="20" width="45%"><SPAN class="textizqn"><B>Nombres del Beneficiario: (**)</B></SPAN></TD>
						<TD colspan="2" height="20" width="55%"><SPAN class="textizqn"><html:text property="txtNombreBenef" size="35" maxlength="20"/></SPAN></TD>
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
					<TR>
							<TD height="20" colspan="8" bgcolor="#e5e5de"><BR>
							</TD>
					</TR>
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
					
					<TR>
						<TD colspan="4" height="20" width="100%"><span class="texto">${mensajepieafiltransfint}</span></TD>
					</TR>
					<logic:messagesPresent>
					<TR>
						<TD colspan="4" height="20" width="100%">
							<table width="100%" border="0" cellspacing="0" cellpadding="0" class="fondoMensajeError">
								<tr>
									<td class="textoMensajeError" align="left">
										<br><html:errors/>
									</td>
								</tr>
							</table>
						</TD>
					</TR>
					</logic:messagesPresent>
				</TBODY>
			</TABLE>
			</CENTER>
			<BR>
			<BR>
				<IMG border="0" name="imgContinuar" src='<%=request.getContextPath()%>/Images/<bean:write name="CARPETA"/>/img_afiliar.gif' width="66" height="20" onclick="afiliar();" style="cursor:hand">
			</TD>
			
		</TR>
	</TBODY>
</TABLE>
</html:form>
<CENTER></CENTER>
</BODY>
</html:html>
