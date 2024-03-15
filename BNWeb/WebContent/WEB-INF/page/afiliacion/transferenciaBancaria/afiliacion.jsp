<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld"				prefix="c"%>
<HTML>

<HEAD>
<script language="JavaScript" src="<%=request.getContextPath()%>/js/util.js"></script>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
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
<script language="javascript">

	function afiliar(){
		var form = document.forms[0];
		
		var tipoElemento = <c:out value="${tipoElemento.tipoElementoSeguridad}"></c:out>;
		
		// Validando que el Número de DNI no tenga caracteres que no sean números
		if (document.forms[0].cmbTipoDocIdent.value==""){
			alert('Debe seleccionar un tipo de documento');
			return;
		}

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

		// Validando que la cuenta destino sea ingresada
		if (validacampo("txtNumeroCuentaDestino")){ 
			alert('Es necesario ingresar la cuenta destino' ); return;}
		
		// Valida que se haya seleccionado un elemento radiobuttom cuenta propia
		if (validaRadios("rdnCuentaPropia")){
			alert('Es necesario seleccionar el campo cuenta propia');
			return;
		}
		// Validando que el nombre del beneficiario sea ingresado
		if (validacampo("txtNombreBeneficiario")){ 
			alert('Es necesario ingresar el nombre del beneficiario' ); return;}
		if (solocaracterespermitidos("txtNombreBeneficiario")){
			alert('Ingresar sólo letras a excepción de la ñ. No considerar tildes' ); 
			return;
		}		
	

		//Funcion para Completar Ceros.
		completCerosCajas();


		//Validando que la cuenta destino contena 11 digitos
		if (validalongitud("txtNumeroCuentaDestino","11")){
			alert('La cuenta destino debe ser de 11 Dígitos, no menos');
			return;
		}

		// document.forms[0].txtNumDoc.value = '00'+document.forms[0].txtNumDoc.value;

		/*if (autocomplementarCeros(document.forms[0].txtNumDoc)){
			return;
		}*/

		frmAfilTB.action = '<%=request.getContextPath()%>/AfilTransferenciaBancaria.do';
		frmAfilTB.submit();		
		form.imgContinuar.removeAttribute("onclick");
		form.imgContinuar.setAttribute("onclick", "");
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
		document.forms[0].elements['txtNumClave'].value = evaluarTeclado6(document.forms[0].elements['txtNumClave'].value,valor);
	}
	
	//@@@
	function completCerosCajas(){
		var frm 		= document.forms[0];
		var longitud1  	= frm.txtNumDoc.value.length;
		var longitud2  	= frm.txtNumeroCuentaDestino.value.length;
		if(longitud1< 8){
			frm.txtNumDoc.value = getCadenaCeros(8,longitud1) +  frm.txtNumDoc.value;
		}
		if(longitud2 < 11){
			frm.txtNumeroCuentaDestino.value = getCadenaCeros(11,longitud2) +  frm.txtNumeroCuentaDestino.value;
		}
	}

	function getCadenaCeros(total,diferencia){
		var i;
		var temp = total - diferencia;
		var cadena = '';
		for(i=0;i<temp;i++ ){
			cadena +='0';
		}
	  return cadena;
	}
	//@@@

	/*
		Functions para Limpiar la clave
	*/
	function cleanPass(){
		cleanPassword("txtNumClave");
	}


function deshabilitar(formulario,idradio){ 
indice_marcado = 1; 
  document.forms[0].rdnCuentaPropia[indice_marcado].checked = true ;
    
    document.forms[0].rdnCuentaPropia[idradio].blur(); 
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

</script>
<TITLE>tran_int_ah.html</TITLE>
</HEAD>
<body  oncontextmenu="return false" onselectstart="return false" ondragstart="return false"  onKeyDown="return cancelRefresh(event)"  bgcolor="white" onload="limpiar();">
<html:form type="pe.bn.afiliacion.action.TransferenciaBancariaAction" action="/AfilTransferenciaBancaria.do"   method="POST" >
<input type="hidden" name="metodo" value="afiliar">
<input type="hidden" name="validar" value="true">
<input type="hidden" name="coordenada" 				value="<%=request.getSession().getAttribute("resultCoordenada") %>">

<TABLE width="99%" border="0" align="center">
	<TBODY>
		<TR align="left" valign="top">
			<TD>
			<DIV align="center"><SPAN class="text8centrn"><FONT size="2"
				face="Arial, Helvetica, sans-serif"><B><FONT color="#000000"><BR>
				AFILIACION TRANSFERENCIAS MISMO BANCO</FONT></B></FONT></SPAN></DIV>
			</TD>
			
		</TR>
		<TR align="left" valign="top">
			<TD>
			<DIV align="center"><SPAN class="text8centrn"><FONT color="black"
				size="2" face="Arial, Helvetica, sans-serif"><B><bean:write
				name="<%=pe.cosapi.common.ConstanteSesion.USUARIO_EN_SESION%>"
				property="nombre" /></B></FONT></SPAN></DIV>
			</TD>
			
		</TR>
		<TR align="left" valign="top">
				<TD height="11"></TD>
			</TR>
		<TR align="left" valign="top">
			<TD width="100%" height="929">
			<FORM>
			<P>
			</P>
			<CENTER>
			<TABLE border="0" cellspacing="1" cellpadding="0"  width="419" >
				<TBODY>
					<TR>
						<TD colspan="4" align="justify"><SPAN style="text-align: justify" class="texto">${mensajeafiliacion} </SPAN></TD>
								</TR>
					<TR>
							<TD height="20" colspan="4"><BR>
							</TD>
						</TR>
					<TR bgcolor="#C9242C">
						<TD colspan="4" height="20" align="center"><SPAN class="textizqn"><B><FONT color="white">Datos Personales del Titular</FONT></B></SPAN></TD>
					</TR>
		<TR  bgcolor="#e5e5de">
			<TD colspan="2" height="20" width="50%"><SPAN class="textizqn"><B>Tipo Doc. Identidad:</B></span></TD>
			<TD colspan="2" height="20" width="50%">
			<html:select property="cmbTipoDocIdent" onchange="limpiar()">
				<html:options collection="lstDocumento" property="codigo" labelProperty="descripcion" />
			</html:select>
			</TD>
		</TR>
		<TR bgcolor="#e5e5de">
			<TD colspan="2" height="20" width="50%"><SPAN class="textizqn"><B>Nro. Doc. Identidad:</B></span></TD>
			<TD colspan="2" height="20" width="50%"><html:text styleClass="textCampo" property="txtNumDoc" size="7" maxlength="10" onkeypress="return selectVal(event)" onkeyup="return selectVal(event)"/></TD>
		</TR>
		<TR bgcolor="#e5e5de">
			<TD colspan="2" height="20" width="50%"><SPAN class="textizqn"><B>Fecha Nacimiento:</B></span></TD>
			<TD colspan="2" height="20" width="50%"><html:text property="txtDia" size="1"  maxlength="2" onkeypress="return soloNumerosAll(event)" />
			<html:select property="cmbMes">
				<html:options collection="lstMes" property="codigoDetalleAyuda" labelProperty="descripcionDetalle" />
			</html:select>
			<html:text styleClass="textCampo"  property="txtAnio" size="2" maxlength="4"  onkeypress="return soloNumerosAll(event)"/></TD>
		</TR>
		<TR bgcolor="#e5e5de">
			<TD colspan="2" height="20" width="50%"><SPAN class="textizqn"><B>Sexo:</B></span></TD>
			<TD colspan="2" height="20" width="50%"><SPAN class="textizqn">
			<html:radio property="rdnSexo" value="M" />Masculino
			<html:radio property="rdnSexo"  value="F"/>Femenino</span></TD>
		</TR>
		<TR bgcolor="#e5e5de">
			<TD colspan="2" height="20" width="50%"><SPAN class="textizqn"><B>Email:</B></span></TD>
			<TD colspan="2" height="20" width="50%"><html:text property="txtMail" size="30" maxlength="60" /></TD>
		</TR>
					<TR bgcolor="#C9242C">
						<TD colspan="4" height="20" align="center"><SPAN class="textizqn"><FONT color="white"><B>Datos Cuenta Destino</B></FONT></span></TD>
					</TR>
					<TR bgcolor="#e5e5de">
						<TD colspan="2" height="20" width="50%" nowrap><SPAN class="textizqn"><B>Tipo Cuenta Destino:</B></SPAN></TD>
						<TD colspan="2" height="20" width="50%"><SPAN class="textizqn">
						<html:select property="cmbTipoCuentaDestino">
							<html:options collection="lstTipoCuenta" property="codigo" labelProperty="descripcion" />
						</html:select></SPAN></TD>
					</TR>
					<TR bgcolor="#e5e5de">
						<TD colspan="2" height="20" width="50%" nowrap><SPAN class="textizqn"><B>Nro. Cuenta Destino:</B></SPAN></TD>
						<TD colspan="2" height="20" width="50%"><SPAN class="textizqn"><html:text property="txtNumeroCuentaDestino" maxlength="11"  onkeypress="return soloNumerosAll(event)"/></SPAN></TD>
					</TR>
					<TR bgcolor="#e5e5de">
						<TD colspan="2" height="20" width="50%" nowrap><SPAN class="textizqn"><B>Cuenta
						Propia: (*)</B></SPAN></TD>
						<TD colspan="2" height="20" width="50%"><SPAN class="textizqn">
						<input type="radio" name="rdnCuentaPropia" value="S"/>Sí
						<input type="radio" name="rdnCuentaPropia" value="N"  checked="checked"/>No
						</SPAN></TD>
					</TR>
					<TR bgcolor="#e5e5de">
						<TD colspan="2" height="20" width="50%" nowrap><SPAN class="textizqn"><B>Nombre
						del Beneficiario: (**)</B></SPAN></TD>
						<TD colspan="2" height="20" width="50%"><SPAN class="textizqn"><html:text property="txtNombreBeneficiario" maxlength="60"/></SPAN></TD>
					</TR>
										
					<c:if test="${tipoElemento.tipoElementoSeguridad=='2'}">
					<TR bgcolor="#e5e5de">
						
						<TD colspan="2" height="20"  bgcolor="#e5e5de" width="140"></TD>
						<TD  height="20" colspan="4" bgcolor="#e5e5de" width="355"><span class="textizqn"><B>
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
						<TD colspan="8" height="20" width="40%" class="textizqn"  bgcolor="#e5e5de"><b><u>Nota:</u>
							</b><br>
								Tener en cuenta que los 6 dígitos cambian cada minuto por lo cual debe ingresar antes que la 
								barra de tiempo se haya consumido.
								</TD>
						
					</TR>
					</c:if>
					<TR>
						<TD colspan="4" class="texto" style="text-align: justify;">${mensajepieafmb}</TD>
					</TR>
					
					<TR>
						<TD align="center" class="texto" colspan="4">&nbsp;</TD>
					</TR>
					<TR>
						<TD align="center" class="texto" colspan="4">&nbsp;</TD>
					</TR>
					
		<logic:messagesPresent>
							
					<TR>
						<TD colspan="4">
							<table  border="0" cellspacing="1" width="100%" cellpadding="0" class="fondoMensajeError">
								<tr>
									<td class="textoMensajeError" align="center">
									<html:errors/>
									</td>
								</tr>
							</table>
						</TD>
					</TR>
					<TR>
						<TD align="center" class="texto" colspan="4">&nbsp;</TD>
					</TR>
					<TR>
						<TD align="center" class="texto" colspan="4">&nbsp;</TD>
					</TR>
		</logic:messagesPresent>

					<TR>
						<TD colspan="4" height="2" align="center">
								<IMG border="0"  name="imgContinuar" src="<%=request.getContextPath()%>/Images/<bean:write name="CARPETA"/>/img_afiliar.gif" width="66" height="20" onclick="afiliar();" style="cursor:hand">
						</TD>
					</TR>
					<TR>
						<TD height="20" colspan="2"></TD>
						<TD height="20" colspan="2"></TD>
					</TR>
					
				</TBODY>
			</TABLE></CENTER>
			</FORM>
			</TD>
		</TR>
	</TBODY>
</TABLE>
</html:form>
</BODY>
</HTML>