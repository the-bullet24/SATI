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
<TITLE>deb_auto_ah.html</TITLE>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/resetearcss.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/tipografias.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bn-estilos.css" />  
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/select.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/select.js"></script>

<SCRIPT language="javascript">

	function regresar(){
		frmAfilDebito.action='<%=request.getContextPath()%>/AfilDebitoAutomatico.do?metodo=regresar';
		frmAfilDebito.submit();
		/*
		history.go(-1)
		*/
	}
	
	
	 function jsCargarDatos(valor0,valor1,valor2,valor3,valor4,valor5,valor6,valor7,valor8,valor9,valor10,valor11) {
		
		document.forms[0].flag.value='0';
		document.forms[0].txtEntidad.value=valor0;
		document.forms[0].txtServicio.value=valor1;
		document.forms[0].txtSuministro.value=valor2;
		document.forms[0].txtFecha.value=valor4;
		document.forms[0].txtEstado.value=valor3;
		document.forms[0].codEntidad.value=valor7;
		document.forms[0].codServicio.value=valor8;
		document.forms[0].numSuministro.value=valor2;
		document.forms[0].email.value=valor9;
		document.forms[0].tipoDoc.value=valor10;
		document.forms[0].numDoc.value=valor11;
		
		if(valor5=='S'){
			document.forms[0].rdnMontoMaximoDebito[0].disabled=false;
			document.forms[0].rdnMontoMaximoDebito[1].disabled=false;
			document.forms[0].rdnMontoMaximoDebito[0].checked=true;
			document.forms[0].rdnMontoMaximoDebito[0].disabled=true;
			document.forms[0].rdnMontoMaximoDebito[1].disabled=true;
			document.forms[0].txtMaximo.value="";
		
		}else{
				if(valor5=='N'){
				document.forms[0].rdnMontoMaximoDebito[0].disabled=false;
				document.forms[0].rdnMontoMaximoDebito[1].disabled=false;
				document.forms[0].rdnMontoMaximoDebito[1].checked=true;
				document.forms[0].rdnMontoMaximoDebito[0].disabled=true;
				document.forms[0].rdnMontoMaximoDebito[1].disabled=true;
				document.forms[0].txtMaximo.value=valor6;
				document.forms[0].txtMaximo.readOnly=true ; 
				
				}
		}
		
		
		
		
		//frmAfilDebito.action='<%=request.getContextPath()%>/AfilDebitoAutomatico.do?metodo=modificar';
		//frmAfilDebito.submit();
		
	}
	
	
		function jsCargarDatosMod(valor0,valor1,valor2,valor3,valor4,valor5,valor6,valor7,valor8,valor9,valor10,valor11,valor12,valor13,valor14,valor15) {

		
		document.forms[0].flag.value='1';
		document.forms[0].txtEntidad.value=valor0;
		document.forms[0].txtServicio.value=valor1;
		document.forms[0].txtSuministro.value=valor2;
		document.forms[0].txtFecha.value=valor4;
		document.forms[0].txtEstado.value=valor3;
		document.forms[0].codEntidad.value=valor7;
		document.forms[0].codServicio.value=valor8;
		document.forms[0].numSuministro.value=valor2;
		document.forms[0].email.value=valor9;
		document.forms[0].tipoDoc.value=valor10;
		document.forms[0].numDoc.value=valor11;
		document.forms[0].flagEstado.value=valor12;
		document.forms[0].tipoTel.value=valor13;
		document.forms[0].numTel.value=valor14;
		document.forms[0].via.value=valor15;
		document.forms[0].flagTope.value=valor5;
		document.forms[0].rdnMontoMaximoDebito[0].checked=false;
		document.forms[0].rdnMontoMaximoDebito[1].checked=false;
		document.forms[0].txtMaximo.value="";
	
		//document.forms[0].rdnMontoMaximoDebito[0].disabled=false;
		//document.forms[0].rdnMontoMaximoDebito[1].disabled=false;
	
		var tipo = '';
				
		if(valor5=='S'){
			//document.forms[0].rdnMontoMaximoDebito[0].checked=true;
			//document.forms[0].txtMaximo.value="";
			//document.forms[0].txtMaximo.readOnly=true ; 
			tipo = 'Sin Tope';
			document.forms[0].txtMontoActual.value=tipo;
			document.forms[0].montoTope.value='';
			
			
			
		
		}else{
				if(valor5=='N'){
				//document.forms[0].txtMaximo.disabled=false;
				//document.forms[0].rdnMontoMaximoDebito[1].checked=true;
				//document.forms[0].txtMaximo.value=valor6;
				document.forms[0].txtMaximo.readOnly=false ; 
				tipo = 'Con Tope';
				document.forms[0].txtMontoActual.value=tipo+'/'+valor6;
				document.forms[0].montoTope.value=valor6;
				
				
				}
		}
		
	
		
	}
	
		function modificar(){
		
			document.frmAfilDebito.boton.disabled = true;
			
			if('<c:out value="${afiliaciones}"/>' == '' && '<c:out value="${afiliacionesReg}"/>' == ''){
				alert('No existen registros para modificar');
				document.frmAfilDebito.boton.disabled = false;
				return;
			
			}
									
			if(document.forms[0].flag.value=='0'){
				
				alert('Seleccione el registro a modificar');
				document.frmAfilDebito.boton.disabled = false;
				return;
				
			}
			
						
				
			if (validaRadios("rdnMontoMaximoDebito")){
			alert('Seleccionar el tipo de monto máximo a debitar (Sin Tope o Con Tope)');
			document.frmAfilDebito.boton.disabled = false;
			return;
			}
			
			if(document.forms[0].rdnMontoMaximoDebito[0].checked==true){
														
					if(document.forms[0].flagTope.value=='S'){
							alert('El monto máximo debe ser distinto al original');
							document.frmAfilDebito.boton.disabled = false;
							return;
					}
			}
			
		
			if(document.forms[0].rdnMontoMaximoDebito[1].checked==true){
					
			if (validacampo("txtMaximo")){
			alert('Es necesario ingresar el Monto Maximo');
			document.frmAfilDebito.boton.disabled = false;
			return;
				}
				
			
			
				
			decallowed = 2; 
			fieldName=document.forms[0].txtMaximo;
			fieldValue = document.forms[0].txtMaximo.value;
			
			if (isNaN(fieldValue) || fieldValue == "")
			{
				alert("El monto ingresado no es válido, ingrese nuevamente el monto");
				document.frmAfilDebito.boton.disabled = false;
				fieldName.select();
				
				return;
			} 
			else
			{
				
					n = parseInt(fieldValue)
			
					if ( n>'9999' ) {
					alert("El monto ingresado debe ser menor");
					fieldName.focus();
					document.frmAfilDebito.boton.disabled = false;
					return;
					}
					
									
				
				
				if (fieldValue.indexOf('.') == -1) fieldValue += ".";
					dectext = fieldValue.substring(fieldValue.indexOf('.')+1, fieldValue.length);
						if (dectext.length > decallowed)
							{ 
								alert ("Introduzca un numero con un máximo de " + decallowed + " decimales");
								fieldName.select();
								document.frmAfilDebito.boton.disabled = false;
								return;
							}
						
					}
					
					
			var tope = parseFloat(document.forms[0].montoTope.value);
			var max = parseFloat(document.forms[0].txtMaximo.value);
			
		
			
			if(tope==max){
			alert('El monto máximo debe ser distinto al original');
			document.frmAfilDebito.boton.disabled = false;						
			return;
			}
					
					
			}
			
		
		frmAfilDebito.action = '<%=request.getContextPath()%>/AfilDebitoAutomatico.do?metodo=comprobarModific';
		frmAfilDebito.submit();	
		
		}
	
	
	function limpiarRadio(valor){
	
		if(valor == 'T'){
		
		document.forms[0].txtMaximo.disabled=true;
		document.forms[0].txtMaximo.value="";
		document.forms[0].txtMaximo.readOnly=true ; 
		}
		
		else{
		document.forms[0].txtMaximo.readOnly=false ; 
		document.forms[0].txtMaximo.disabled=false;
		document.forms[0].txtMaximo.value="";
		}
		
	}

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
	
	
	
	function inicio(){
	document.forms[0].flag.value='0';
	}
	
	
</SCRIPT>

</HEAD>
<body oncontextmenu="return false" onselectstart="return false" ondragstart="return false"  onKeyDown="return cancelRefresh(event)"  bgcolor="white" onload="inicio();">
<html:form type="pe.bn.afiliacion.action.AfiliacionDebitoAutomaticoAction" action="/AfilDebitoAutomatico.do" method="POST" >
<input type="hidden" name="metodo">
<input type="hidden" name="metodo" value="modificar">
<input type="hidden" name="codEntidad" >
<input type="hidden" name="codServicio" >
<input type="hidden" name="numSuministro" >
<input type="hidden" name="email" >
<input type="hidden" name="tipoDoc" >
<input type="hidden" name="numDoc" >
<input type="hidden" name="flagEstado" >
<input type="hidden" name="via" >
<input type="hidden" name="tipoTel" >
<input type="hidden" name="numTel" >
<input type="hidden" name="flag" >
<input type="hidden" name="flagTope" >
<input type="hidden" name="montoTope" >

<center>
<div id="contenidos-informativos">
	<h2>MODIFICACIÓN DE MONTO DÉBITO AUTOMÁTICO</h2>
	<p>Seleccione el servicio afiliado que desea modificar del listado que se encuentra en la parte inferior. Modifique el Monto Máximo Débito y seleccione continuar.</p>
	<p>Importante: El Horario de atención es de 6:00 am a 9:00 pm, no incluye domingos. En caso realice una afiliación o desafiliación en días feriados no laborables, se procesará el primer día útil siguiente.</p>
	<div id="consulta-datos">
	
		<TABLE width="100%" border="0" align="center">

				
				<TR  >
					<TD colspan="7">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</TD>
				</TR>
				<TR  >
					<TD>Entidad:</TD>
					<TD><html:text property="txtEntidad" value="" size="15" styleClass="input-chico" readonly="true" disabled="true" ></html:text></TD>
				
					<TD>Servicio:</TD>
					<TD ><html:text property="txtServicio" value=""  size="20" styleClass="input-chico" readonly="true" disabled="true" ></html:text></TD>
					<TD style="width:140px;">Suministro:</TD>
					<TD ><html:text property="txtSuministro" value="" size="15"  styleClass="input-chico3" readonly="true" disabled="true" ></html:text></TD>
				</TR>
				<TR  >
					
				
					<TD>Fecha:</TD>
					<TD ><html:text property="txtFecha" value=""  size="15" styleClass="input-chico" readonly="true" disabled="true" ></html:text></TD>
				
					<TD>Estado:</TD>
					<TD ><html:text property="txtEstado" value=""  size="20" styleClass="input-chico" readonly="true" disabled="true"></html:text></TD>
					<TD style="width:140px;">Monto Actual:</TD>
					<TD ><html:text property="txtMontoActual" value="" size="15" styleClass="input-chico3" readonly="true" disabled="true" ></html:text></TD>
				</TR>
			
				<TR >
					<TD colspan="6" style="width:592px;"><B>La Modificación es exclusiva para el campo Monto Máximo a debitar.</B></TD>
				</TR>
		
				<TR >
				
						<TD colspan="2">Monto Máximo Débito:</TD>
						<TD colspan="3"  style="width:250px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<html:radio property="rdnMontoMaximoDebito" value="T" onclick="limpiarRadio(this.value);">Sin Tope</html:radio> 
						<html:radio property="rdnMontoMaximoDebito" value="M" onclick="limpiarRadio(this.value);">Con Tope</html:radio></TD>
						<td align="left"><html:text property="txtMaximo"   styleClass="input-chico" maxlength="7" onkeypress="return permitedecimales(event)" /></td>			
				</TR>
				<TR >
						<TD colspan="6" align="left">&nbsp;</TD>
				</TR>
				
			</TABLE>
	
	</div>
	
			
	<div id="consulta-saldo">
			<logic:notEmpty name="afiliacionesReg">
		
		
		<TABLE cellspacing="0" cellpadding="0" width="620">
			
					<TR >
						<TD colspan="7" align="center" class="tituloTabla">SERVICIOS AFILIADOS DEL DÍA</TD>
					</TR>
					<TR>
					
						<TD  align="center"  width="125"  class="tituloCelda">
							ENTIDAD</TD>
						<TD align="center"  width="125"  class="tituloCelda">
							SERVICIO</TD>
						<TD align="center"  width="150"  class="tituloCelda">
							SUMINISTRO</TD>
						<TD align="center"  width="70"  class="tituloCelda">
							&nbsp;&nbsp;&nbsp;&nbsp;ESTADO</TD>
						<TD align="center"  width="70"  class="tituloCelda">
							&nbsp;&nbsp;&nbsp;&nbsp;FECHA</TD>
						<TD  align="center"  width="80"  class="tituloCelda">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</TD>
					</TR>
		
						
			
				
							<logic:iterate id="afil" name="afiliacionesReg" >
							
									<logic:equal name="afil" property="flagEstado" value="8">
								
									<TR  align="center">
									
								
									<TD align="center"  width="125" class="detalleCelda"><bean:write name="afil"
									property="empresaMostrar" /> <INPUT type="hidden"  name="flag" value="<bean:write name="afil" property="flagEstado"/>"/>
									</TD>
										<TD align="center"  width="125" class="detalleCelda"><bean:write name="afil"
									property="serviciomostrar" />
									</TD>
										<TD align="center"  width="150" class="detalleCelda">
									<bean:write name="afil"
									property="numSuministro" />
									</TD>
									<TD align="center"  width="70" class="detalleCelda">
									<bean:write name="afil"
									property="flagMostrarEstado" />
									</TD>
									<TD align="center"  width="70" class="detalleCelda">
									<bean:write name="afil"
									property="fecha" />
									</TD>
									<TD align="center"  width="80" class="detalleCelda"><a href="#" onclick="jsCargarDatosMod('<c:out value="${afil.empresaMostrar}" />','<c:out value="${afil.serviciomostrar}"/>',
									'<c:out value="${afil.numSuministro}"/>','<c:out value="${afil.flagMostrarEstado}"/>','<c:out value="${afil.fecha}"/>',
									'<c:out value="${afil.tope}"/>','<c:out value="${afil.maximo}"/>','<c:out value="${afil.empresa}"/>',
									'<c:out value="${afil.servicio}"/>','<c:out value="${afil.email}"/>','<c:out value="${afil.tipoDoc}"/>','<c:out value="${afil.nroDoc}"/>',
									'<c:out value="${afil.flagEstado}"/>','<c:out value="${afil.tipoTel}"/>','<c:out value="${afil.nroTel}"/>',
									'<c:out value="${afil.via}"/>')"><img align="center" border="0" src="Images/edit.png" title="Selecciona para modificar" >Modificar</a></TD>
									
															
										</TR>
										</logic:equal>
							</logic:iterate>
										
					</TABLE>
				
		</logic:notEmpty>
	
		
		
		<logic:notEmpty name="afiliaciones">
		
		<TABLE cellspacing="0" cellpadding="0" border="0" width="620">
			
					<BR/>
						<TR>
						<TD colspan="6" align="center" class="tituloTabla">SERVICIOS AFILIADOS CONFIRMADOS</TD>
					</TR>
									
					<TR bgcolor="#c9242C">
																
						<TD  align="center"  width="125"  class="tituloCelda">
							ENTIDAD</TD>
						<TD align="center"  width="125"  class="tituloCelda">
							SERVICIO</TD>
						<TD align="center"  width="150"  class="tituloCelda">
							SUMINISTRO</TD>
						<TD align="center"  width="70"  class="tituloCelda">
							ESTADO</TD>
						<TD align="center"  width="70"  class="tituloCelda">
							FECHA</TD>
						<TD  align="center"  width="80"  class="tituloCelda">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</TD>
					</TR>
					
						<logic:iterate id="afil" name="afiliaciones" >
							
									<logic:equal name="afil" property="flagEstado" value="9">
								
									<TR  align="center">
									
								
									<TD align="center"  width="125" class="detalleCelda">
									<bean:write name="afil"
									property="empresaMostrar" /><INPUT type="hidden"  name="flag" value="<bean:write name="afil" property="flagEstado"/>"/>
									</TD>
										<TD align="center"  width="125" class="detalleCelda">
									<bean:write name="afil"
									property="serviciomostrar" />
									</TD>
										<TD align="center"  width="150" class="detalleCelda">
									<bean:write name="afil"
									property="numSuministro" />
									</TD>
									<TD align="center"  width="70" class="detalleCelda">
									<bean:write name="afil"
									property="flagMostrarEstado" />
									</TD>
									<TD align="center"  width="70" class="detalleCelda">
									<bean:write name="afil"
									property="fecha" />
									</TD>
									<TD class="detalleCelda" width="70"><a href="#" class="" onclick="jsCargarDatosMod('<c:out value="${afil.empresaMostrar}" />','<c:out value="${afil.serviciomostrar}"/>',
									'<c:out value="${afil.numSuministro}"/>','<c:out value="${afil.flagMostrarEstado}"/>','<c:out value="${afil.fecha}"/>',
									'<c:out value="${afil.tope}"/>','<c:out value="${afil.maximo}"/>','<c:out value="${afil.empresa}"/>',
									'<c:out value="${afil.servicio}"/>','<c:out value="${afil.email}"/>','<c:out value="${afil.tipoDoc}"/>','<c:out value="${afil.nroDoc}"/>',
									'<c:out value="${afil.flagEstado}"/>','<c:out value="${afil.tipoTel}"/>','<c:out value="${afil.nroTel}"/>',
									'<c:out value="${afil.via}"/>')"><img align="center" border="0" src="Images/edit.png"  title="Selecciona para modificar">Modificar</a></TD>
									
																	
										</TR>
										</logic:equal>
							</logic:iterate>
										
					</TABLE>
				
		</logic:notEmpty>
			
			
 
   <logic:messagesPresent>
	<p class="importante"><html:errors/></p>
	</logic:messagesPresent>

	</div>
		<div class="boton1">
		<input type="button" value="CONTINUAR" name="boton" onclick="javascript:modificar();"/>
		</div>   
		   					
	<br/>
	</div>
</center>
</html:form>
</BODY>
</html>
