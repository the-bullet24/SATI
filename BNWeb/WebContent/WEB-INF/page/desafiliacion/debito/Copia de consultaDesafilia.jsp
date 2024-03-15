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
		
		 contador=0; 
		 
		 
		 
		

		for (i=0;i<document.frmDesafiliacion.optSecuencia.length;i++) 
        {   if (document.frmDesafiliacion.optSecuencia[i].checked==true)  
            { 
           
            contador++;
            }
            
         }
                  
         if (document.frmDesafiliacion.optSecuencia.checked==true)  
             contador=1; 

           if (contador==1)
           { 
           
         
           
           	for (i=0;i<document.frmDesafiliacion.optSecuencia.length;i++) {
           			 if (document.frmDesafiliacion.optSecuencia[i].checked==true)  {
           			  var flag1 = document.frmDesafiliacion.optSecuencia[i].value;
             
            			 var elem = flag1.split('-');
							v = elem[3];
				

		  					if (v == '6'){
							alert('El registro aún se encuentra en proceso, seleccione otro registro');
							return;
			}
           			 
           			 }
           	
           	}
          
          	document.frmDesafiliacion.action = '<%=request.getContextPath()%>/AfilDebitoAutomatico.do?metodo=desafiliacion';
			document.frmDesafiliacion.submit();	
			}	
		   else
		    { 
		    
		    
		    alert("Debe de seleccionar solo un registro para desafiliar");
		    
		    }
			
		

	}
	
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
		frmAfilDebito.action='<%=request.getContextPath()%>/AfilDebitoAutomatico.do?metodo=regresar';
		frmAfilDebito.submit();
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
<input type="hidden" name="metodo" value="consultaAfiliacion">

<TABLE width="100%" border="0" align="center">
	<TBODY>
		<TR align="left" valign="top">
			<TD>
			<DIV align="center"><BR>
			<SPAN class="text8centrn"><FONT size="2"
				face="Arial, Helvetica, sans-serif"><B><FONT color="#000000">DESAFILIACIÓN DÉBITO AUTOMÁTICO </FONT></B></FONT></SPAN></DIV>
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
		<TR>
			<TD>
					<DIV align="center"><SPAN class="text8centrn"><B>MARCAR LA CASILLA DEL SERVICIO QUE DESEA DESAFILIAR, LAS DESAFILIACIONES SÓLO ESTÁN 
					<BR>DISPONIBLES PARA LOS SERVICIOS AFILIADOS EN EL DÍA Y LOS SERVICIOS AFILIADOS CONFIRMADOS</B></SPAN></DIV>
			</TD>
		</TR>
		<TR align="left" valign="top">
			<TD width="100%">
			<CENTER>
			
			<logic:notEmpty name="afiliacionesReg">
			<TABLE cellspacing="0" cellpadding="0" border="0" width="600">
				<TBODY>
					<BR/>
						<TR bgcolor="#c9242C">
						<TD colspan="6" align="center" bgcolor="#CC0000"><font size="2" color="white" face="Arial, Helvetica, sans-serif" ><B>SERVICIOS AFILIADOS DEL DÍA</B></FONT></TD>
					</TR>
					<TR bgcolor="#c9242C">
					
						<TD  align="center" bgcolor="#E5E5DE" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</TD>
					
						<TD  align="left" bgcolor="#E5E5DE" width="256"><B><SPAN class="estiloDetalle"><FONT
							color="black" face="Arial" size="-1">ENTIDAD</FONT></SPAN></B></TD>
						<TD align="left" bgcolor="#E5E5DE" width="226"><B><SPAN class="estiloDetalle"><FONT
							color="black" face="Arial" size="-1">SERVICIO</FONT></SPAN></B></TD>
						<TD align="left" bgcolor="#E5E5DE" width="226"><B><SPAN class="estiloDetalle"><FONT
							color="black" face="Arial" size="-1">SUMINISTRO</FONT></SPAN></B></TD>
						<TD align="left" bgcolor="#E5E5DE" width="150"><B><SPAN class="estiloDetalle"><FONT
							color="black" face="Arial" size="-1">ESTADO</FONT></SPAN></B></TD>
						<TD align="left" bgcolor="#E5E5DE" width="100"><B><SPAN class="estiloDetalle"><FONT
							color="black" face="Arial" size="-1">FECHA</FONT></SPAN></B></TD>
					</TR>
			</TBODY>
			</TABLE>
			</logic:notEmpty>
			
				<logic:notEmpty name="afiliacionesReg">
						
					<div id="scroll3" style="width:600;height:100px;overflow:auto;background-color:#e5e5de;">
				
					<TABLE border="1" width="600" bordercolor="white">
				
							<logic:iterate id="afil" name="afiliacionesReg" >
							
									<logic:equal name="afil" property="flagEstado" value="8">
								
									<TR bgcolor="#e5e5de" align="center">
									
									
									<TD width="30" align="center" bgcolor="white"><INPUT type="checkbox"
											name="optSecuencia"
											value="<bean:write name="afil" property="empresa"/>-<bean:write name="afil" property="servicio"/>-<bean:write name="afil" property="numSuministro"/>-<bean:write name="afil" property="flagEstado"/>-<bean:write name="afil" property="tipoDoc"/>-<bean:write name="afil" property="nroDoc"/>-<bean:write name="afil" property="tipoTel"/>-<bean:write name="afil" property="nroTel"/>-<bean:write name="afil" property="email"/>-<bean:write name="afil" property="via"/>">
									</TD>
									<TD align="left" bgcolor="white" width="226"><FONT face="Arial" size="2"><SPAN
									class="estiloDetalle"><bean:write name="afil"
									property="empresaMostrar" /> </SPAN></FONT><INPUT type="hidden"  name="flag" value="<bean:write name="afil" property="flagEstado"/>"/>
									</TD>
										<TD align="left" bgcolor="white" width="226"><FONT face="Arial" size="2"><SPAN
									class="estiloDetalle"><bean:write name="afil"
									property="serviciomostrar" /> </SPAN></FONT>
									</TD>
										<TD align="left" bgcolor="white" width="226"><FONT face="Arial" size="2"><SPAN
									class="estiloDetalle"><bean:write name="afil"
									property="numSuministro" /> </SPAN></FONT>
									</TD>
									<TD align="left" bgcolor="white" width="150"><FONT face="Arial" size="2"><SPAN
									class="estiloDetalle"><bean:write name="afil"
									property="flagMostrarEstado" /> </SPAN></FONT>
									</TD>
									<TD align="left" bgcolor="white" width="100"><FONT face="Arial" size="2"><SPAN
									class="estiloDetalle"><bean:write name="afil"
									property="fecha" /> </SPAN></FONT>
									</TD>
																	
										</TR>
										</logic:equal>
							</logic:iterate>
										
					</TABLE>
					</div>	
		</logic:notEmpty>
	
		<logic:notEmpty name="afiliacionesTram">
		<TABLE cellspacing="0" cellpadding="0" border="0" width="600">
				<TBODY>
					<BR/>
						<TR bgcolor="#c9242C">
						<TD colspan="6" align="center" bgcolor="#CC0000"><font size="2" color="white" face="Arial, Helvetica, sans-serif" ><B>SERVICIOS AFILIADOS EN TRÁMITE</B></FONT></TD>
					</TR>
					<TR bgcolor="#c9242C">
					
						<TD  align="center" bgcolor="#E5E5DE" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</TD>
					
						<TD  align="left" bgcolor="#E5E5DE" width="256"><B><SPAN class="estiloDetalle"><FONT
							color="black" face="Arial" size="-1">ENTIDAD</FONT></SPAN></B></TD>
						<TD align="left" bgcolor="#E5E5DE" width="226"><B><SPAN class="estiloDetalle"><FONT
							color="black" face="Arial" size="-1">SERVICIO</FONT></SPAN></B></TD>
						<TD align="left" bgcolor="#E5E5DE" width="226"><B><SPAN class="estiloDetalle"><FONT
							color="black" face="Arial" size="-1">SUMINISTRO</FONT></SPAN></B></TD>
						<TD align="left" bgcolor="#E5E5DE" width="150"><B><SPAN class="estiloDetalle"><FONT
							color="black" face="Arial" size="-1">ESTADO</FONT></SPAN></B></TD>
						<TD align="left" bgcolor="#E5E5DE" width="100"><B><SPAN class="estiloDetalle"><FONT
							color="black" face="Arial" size="-1">FECHA</FONT></SPAN></B></TD>
					</TR>
			</TBODY>
			</TABLE>
			</logic:notEmpty>
			<logic:notEmpty name="afiliacionesTram">
						
					<div id="scroll4" style="width:600;height:100px;overflow:auto;background-color:#e5e5de;">
				
					<TABLE border="1" width="600" bordercolor="white">
				
							<logic:iterate id="afil" name="afiliacionesTram" >
							
									<logic:equal name="afil" property="flagEstado" value="6">
								
									<TR bgcolor="#e5e5de" align="center">
									
									
									<TD width="30" align="center" bgcolor="white"></TD>
									<TD align="left" bgcolor="white" width="226"><FONT face="Arial" size="2"><SPAN
									class="estiloDetalle"><bean:write name="afil"
									property="empresaMostrar" /> </SPAN></FONT><INPUT type="hidden"  name="flag" value="<bean:write name="afil" property="flagEstado"/>"/>
									</TD>
										<TD align="left" bgcolor="white" width="226"><FONT face="Arial" size="2"><SPAN
									class="estiloDetalle"><bean:write name="afil"
									property="serviciomostrar" /> </SPAN></FONT>
									</TD>
										<TD align="left" bgcolor="white" width="226"><FONT face="Arial" size="2"><SPAN
									class="estiloDetalle"><bean:write name="afil"
									property="numSuministro" /> </SPAN></FONT>
									</TD>
									<TD align="left" bgcolor="white" width="150"><FONT face="Arial" size="2"><SPAN
									class="estiloDetalle"><bean:write name="afil"
									property="flagMostrarEstado" /> </SPAN></FONT>
									</TD>
									<TD align="left" bgcolor="white" width="100"><FONT face="Arial" size="2"><SPAN
									class="estiloDetalle"><bean:write name="afil"
									property="fecha" /> </SPAN></FONT>
									</TD>
																	
										</TR>
										</logic:equal>
							</logic:iterate>
										
					</TABLE>
					</div>	
		</logic:notEmpty>

		<logic:notEmpty name="afiliaciones">
		<TABLE cellspacing="0" cellpadding="0" border="0" width="600">
				<TBODY>
					<BR/>
						<TR bgcolor="#c9242C">
						<TD colspan="6" align="center" bgcolor="#CC0000"><font size="2" color="white" face="Arial, Helvetica, sans-serif" ><B>SERVICIOS AFILIADOS CONFIRMADOS</B></FONT></TD>
					</TR>
					<TR bgcolor="#c9242C">
					
						<TD  align="center" bgcolor="#E5E5DE" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</TD>
					
						<TD  align="left" bgcolor="#E5E5DE" width="256"><B><SPAN class="estiloDetalle"><FONT
							color="black" face="Arial" size="-1">ENTIDAD</FONT></SPAN></B></TD>
						<TD align="left" bgcolor="#E5E5DE" width="226"><B><SPAN class="estiloDetalle"><FONT
							color="black" face="Arial" size="-1">SERVICIO</FONT></SPAN></B></TD>
						<TD align="left" bgcolor="#E5E5DE" width="226"><B><SPAN class="estiloDetalle"><FONT
							color="black" face="Arial" size="-1">SUMINISTRO</FONT></SPAN></B></TD>
						<TD align="left" bgcolor="#E5E5DE" width="150"><B><SPAN class="estiloDetalle"><FONT
							color="black" face="Arial" size="-1">ESTADO</FONT></SPAN></B></TD>
						<TD align="left" bgcolor="#E5E5DE" width="100"><B><SPAN class="estiloDetalle"><FONT
							color="black" face="Arial" size="-1">FECHA</FONT></SPAN></B></TD>
					</TR>
			</TBODY>
			</TABLE>
			</logic:notEmpty>
			
			<logic:notEmpty name="afiliaciones">
						
					<div id="scroll4" style="width:600;height:150px;overflow:auto;background-color:#e5e5de;">
				
					<TABLE border="1" width="600" bordercolor="white">
				
							<logic:iterate id="afil" name="afiliaciones" >
							
									<logic:equal name="afil" property="flagEstado" value="9">
								
									<TR bgcolor="#e5e5de" align="center">
									
									
									<TD width="30" align="center" bgcolor="white"><INPUT type="checkbox"
											name="optSecuencia"
											value="<bean:write name="afil" property="empresa"/>-<bean:write name="afil" property="servicio"/>-<bean:write name="afil" property="numSuministro"/>-<bean:write name="afil" property="flagEstado"/>-<bean:write name="afil" property="tipoDoc"/>-<bean:write name="afil" property="nroDoc"/>-<bean:write name="afil" property="tipoTel"/>-<bean:write name="afil" property="nroTel"/>-<bean:write name="afil" property="email"/>-<bean:write name="afil" property="via"/>">
									</TD>
									<TD align="left" bgcolor="white" width="226"><FONT face="Arial" size="2"><SPAN
									class="estiloDetalle"><bean:write name="afil"
									property="empresaMostrar" /> </SPAN></FONT><INPUT type="hidden"  name="flag" value="<bean:write name="afil" property="flagEstado"/>"/>
									</TD>
										<TD align="left" bgcolor="white" width="226"><FONT face="Arial" size="2"><SPAN
									class="estiloDetalle"><bean:write name="afil"
									property="serviciomostrar" /> </SPAN></FONT>
									</TD>
										<TD align="left" bgcolor="white" width="226"><FONT face="Arial" size="2"><SPAN
									class="estiloDetalle"><bean:write name="afil"
									property="numSuministro" /> </SPAN></FONT>
									</TD>
									<TD align="left" bgcolor="white" width="150"><FONT face="Arial" size="2"><SPAN
									class="estiloDetalle"><bean:write name="afil"
									property="flagMostrarEstado" /> </SPAN></FONT>
									</TD>
									<TD align="left" bgcolor="white" width="100"><FONT face="Arial" size="2"><SPAN
									class="estiloDetalle"><bean:write name="afil"
									property="fecha" /> </SPAN></FONT>
									</TD>
																	
										</TR>
										</logic:equal>
							</logic:iterate>
										
					</TABLE>
					</div>	
		</logic:notEmpty>

			

		
		
			<TABLE cellspacing="0" cellpadding="0" border="0" width="600">
				<BR/>
				<TR bgcolor="#e5e5de" > 
				
				<TD colspan="6" align="center" width="600">
						<FONT class="texto"><B>Clave Internet:</B></FONT><input type="password" name="txtNumClave" size="6" maxlength="6" readonly="readonly">
					</TD>
				<TD height="20" colspan="2" bgcolor="#e5e5de" width="34"></TD>
				</TR>
				<TR bgcolor="#e5e5de" align="center">
			
					<TD height="20" colspan="6" width="600">
						<jsp:include page="/WEB-INF/page/sistema/teclado.jsp" ></jsp:include>
					</TD>
					<TD height="20" colspan="2" bgcolor="#e5e5de" width="34"></TD>
				</TR>
				<TR bgcolor="#e5e5de" align="center" >
					<TD colspan="8">&nbsp;</TD>
				</TR>
				<c:if test="${tipoElemento.tipoElementoSeguridad=='2'}">
				<TR bgcolor="#e5e5de">
						
					
						<TD  height="20" colspan="6" align="center" bgcolor="#e5e5de" width="600"><span class="textizqn"><B>
						Ingresar la Siguiente Coordenada </B></span><BR/><LABEL><B>&nbsp; </B><FONT size="+1"><SPAN
							style="color: #a80000"><B><c:if test="${resultCoord.coordConcat eq null}">No disponible</c:if>
							<c:if test="${resultCoord.coordConcat ne null}"><c:out value="${resultCoord.coordConcat}"/></c:if>
							
							</B></SPAN></FONT></LABEL><BR>
						<INPUT type="password" name="txtCoordenada" size="6" maxlength="2" <c:if test="${resultCoord.coordConcat eq null}"> readonly="readonly"</c:if> onkeypress="return soloNumerosAll(event)"><BR>
						
						
						<FONT size="-1" face="Arial"><FONT size="-1" face="Arial"><B></B></font></font></TD>
						<TD height="20" colspan="2" bgcolor="#e5e5de" width="34"></TD>
					</TR>
					</c:if>
					<c:if test="${tipoElemento.tipoElementoSeguridad=='5'}">
											
					<TR bgcolor="#E5E5DE">
						
						  <TD height="20" colspan="8" bgcolor="#e5e5de">
						  
						  		<TABLE>
						  					<TR>
						  								<TD  height="20" colspan="4" bgcolor="#e5e5de" width="600"><span class="textizqn"><B>
						Ingresar los 6 dígitos del TOKEN </B></span><LABEL><B>&nbsp; </B><FONT size="+1"><SPAN
							style="color: #a80000"><B><BR/>
							
							</B></SPAN></FONT></LABEL>
						<INPUT type="password" name="txtCoordenada" size="10" maxlength="6"  onkeypress="return soloNumerosAll(event)"><BR></TD>
						
						
							<TD colspan="2" height="20"  bgcolor="#e5e5de" width="140"><img
								border="0" src="<%=request.getContextPath()%>/Images/token_p.gif" 
								></TD>
						  					</TR>
						  		</TABLE>
						  </TD>
					
					</TR>
					</c:if>
					
					<TR>
							<TD height="20" colspan="8" bgcolor="#e5e5de"></TD>
					</TR>
					<c:if test="${tipoElemento.tipoElementoSeguridad=='2'}">
					<TR>
						<TD colspan="8" height="20" class="textizqn"  bgcolor="#e5e5de"><b><u>Ejemplo:</u>
							</b><br>
								Al solicitarle la coordenada <B>6 - F</B>, deberás buscar la fila correspondiente al <b>número
							6</b> y la columna de la <B>letra  F</B>, en la unión de ambos, obtendrás un número, éste número deberás ingresarlo para aprobar la operación.</TD>
						
					</TR>
					</c:if>
					<c:if test="${tipoElemento.tipoElementoSeguridad=='5'}">
						<TR>
						<TD colspan="8" height="20"  class="textizqn"  bgcolor="#e5e5de"><b><u>Nota:</u>
							</b><br>
								Tener en cuenta que los 6 dígitos cambian cada minuto por lo cual debe ingresar antes que la 
								barra de tiempo se haya consumido.
								</TD>
						
					</TR>
					</c:if>
				<TR>
						<TD colspan="8" align="center"><BR>
						<br>
							<IMG border="0" src='<%=request.getContextPath()%>/Images/<bean:write name="CARPETA"/>/img_desafiliar.gif' onclick="javascript:desafiliar();"></TD>
					</TR>
			</TABLE>

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
