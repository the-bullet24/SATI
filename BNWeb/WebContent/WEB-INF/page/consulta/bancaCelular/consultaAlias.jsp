<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld"	prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld"	prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld"	prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld"				prefix="c"%>
<HTML>
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<TITLE>deb_auto_ah.html</TITLE>
<script language="JavaScript" src="<%=request.getContextPath()%>/js/util.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/resetearcss.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/tipografias.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bn-estilos.css" />  
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/select.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/select.js"></script>

<SCRIPT language="javascript">

function imprimir(){
		window.open('<%=request.getContextPath()%>/util.do?metodo=solicitarRefrendo&variableSesion=<%=pe.cosapi.common.ConstanteSesion.AFIL_BANCA_CELULAR%>&idObjeto=refrendoConsultaAliasBancaCelular',"BN","toolbar=0,location=0,width=480,height=530, scrollbars=yes, resizable=yes, top=" + ((screen.height/2)-(450/2)-10)+", left="+((screen.width/2)-(350/2)));
	}

function enviar(){
		window.open('<%=request.getContextPath()%>/util.do?metodo=verMail&variableSesion=<%=pe.cosapi.common.ConstanteSesion.AFIL_BANCA_CELULAR%>&idObjeto=mailConsultaAliasBancaCelular',"BN","toolbar=0,location=0,width=480,height=530, scrollbars=yes, resizable=yes,  top=" + ((screen.height/2)-(450/2))+", left="+((screen.width/2)-(460/2)));
	}

function verPdf(){
	var form = document.frmAfilCelular;
      			
      			form.action="<%=request.getContextPath()%>/util.do";
				form.metodo.value = 'verPDF';
				form.idObjeto.value = 'refrendoConsultaAliasBancaCelular';
				form.variableSesion.value = '<%=pe.cosapi.common.ConstanteSesion.BANCA_CELULAR%>';
				form.titulo.value = 'CONSULTA DE AFILIACIONES';
				document.frmAfilCelular.submit();
		
    	}	
	
function mostrar(){
		var form = document.frmAfilCelular;
		document.frmAfilCelular.boton1.disabled = true;
				
						if (validalongitud("txtNumeroServicio","9")){
								alert('El número del Celular debe contener 9 Digitos, no menos');
								document.frmAfilCelular.boton1.disabled = false;
								return;
							}
						var numTelCel  = form.txtNumeroServicio.value;
						var cPrimerNumero = numTelCel.substring(0,1)
							if (cPrimerNumero != "9"){
							alert('El primer dígito del Num. Celular debe ser 9');
							document.frmAfilCelular.boton1.disabled = false;
							return;
							}
							
							
	
		form.action="<%=request.getContextPath()%>/AfilBancaCelular.do?metodo=mostrarConsulta";
		form.submit();
	
	}
</SCRIPT>

</HEAD>
<body oncontextmenu="return false" onselectstart="return false" ondragstart="return false"  onKeyDown="return cancelRefresh(event)"  bgcolor="white" >
<form name="frmAfilCelular" method="post">
<html:form type="pe.bn.afiliacion.action.AfiliacionBancaCelularAction"  action="/AfilBancaCelular.do" method="POST" >
<input type="hidden" name="metodo">
<input type="hidden" name="idObjeto">
<input type="hidden" name="variableSesion">
<input type="hidden" name="titulo">
<div id="contenidos-informativos">
	<h2>CONSULTA DE AFILIACIONES A BANCA CELULAR</h2>
	

	<p>${mensajeCabeceraConsultAlias}</p>
	<br/>
			<div id="consulta-datos">
			
			    <div class="formEstandar">
				    	<div class="izq" style="width: 350px;">
		 					<label style="margin: 5px;">Número de Celular:</label>
		 					<html:text styleClass="input-chico" style="margin: 5px;"property="txtNumeroServicio"  maxlength="9" onkeypress="return soloNumerosAll(event)" style="margin: 5px;"/>
						</div>
						<div class="der" style="width: 150px;">
							<div class="boton1" style="margin: -4px 0px 10px; padding: 0px;">
													<input type="button" value="CONSULTAR" id="boton1" onclick="javascript:mostrar();"/>
							</div>   
				    	</div>
				    	<div class="clear cincopx"></div>
				    </div>	
					
					</div>
				<div class="clear cincopx"></div>
			<div id="consulta-saldo">	
			
				<logic:notEmpty name="consultaAlias">
					<TABLE>
				
			
					<TR>
						<TD align="center">
										<TABLE>
											<TBODY>
													<TR>
														<TD class="fila-izquierda">Cuenta:</TD>
														<TD class="fila-derecha">
														<bean:write name="<%=pe.cosapi.common.ConstanteSesion.AFIL_BANCA_CELULAR%>" property="cuenta.cuentaFormateada" /> </TD>
													</TR>
												
													<TR>
														<TD class="fila-izquierda">Operador:</TD>
														<TD class="fila-derecha">
														
														<bean:write name="<%=pe.cosapi.common.ConstanteSesion.AFIL_BANCA_CELULAR%>" property="mostrarOperador" /> </TD>
													</TR>
													<TR>
														<TD class="fila-izquierda">Celular:</TD>
														<TD class="fila-derecha">
														<bean:write name="<%=pe.cosapi.common.ConstanteSesion.AFIL_BANCA_CELULAR%>" property="numeroCelular" /> </TD>
													</TR>
													<TR>
														<TD colspan="2">&nbsp;</TD>
													</TR>
										</TBODY></TABLE>
							</TD>
					</TR>
					<TR align="left" valign="top">
						<TD width="100%">
							<CENTER>
						
							<TABLE cellspacing="0" cellpadding="0" width="460">
								<TBODY>
									
									<TR>
										<TD  align="center" class="tituloTabla" width="10" style="width: 50px;">N°</TD>										
										<TD  align="center" class="tituloTabla" width="125">SERVICIO</TD>
										<TD align="center" class="tituloTabla" width="125">ALIAS</TD>
										<TD align="center" class="tituloTabla" width=200">REFERENCIA</TD>
										
									</TR>
									
							</TBODY>
			
				
							<logic:iterate id="consulta" name="consultaAlias" >
							
																
									<TR align="center">
									
									<TD align="center" class="detalleCelda" width="10"> <bean:write name="consulta"
									property="secuencia" />
									</TD>
									
									<TD align="center" class="detalleCelda" width="125"> <bean:write name="consulta"
									property="servicio" />
									</TD>
									<TD align="center" class="detalleCelda" width="125"><bean:write name="consulta"
									property="aliasServicio" />
									</TD>
									<TD align="center" class="detalleCelda" width="200"> <bean:write name="consulta"
									property="numReferencia" /> 
									</TD>
								
									
															
										</TR>
									
							</logic:iterate>
										
					</TABLE>
	
	</CENTER>
	</TD></TR>
	
</TABLE>
</logic:notEmpty>

	<logic:empty name="consultaAlias">
			<p class="mensaje">No existen registros asociados. </p>
			
			</logic:empty>
	<p>${mensajePieConsultAlias}</p>
	</div>
		<logic:notEmpty name="consultaAlias">
		
	
	<div id="botones" class="limpiar">
					        <a href="javascript:imprimir();" id="imprimir" style="margin-left:125px;">IMPRIMIR</a>
        					<a href="javascript:enviar();" id="enviar">ENVIAR</a>
         					<a href="javascript:verPdf();" id="descargar">DESCARGAR</a>
					     </div>	
	</logic:notEmpty> 
	</div>
	

</html:form>
</form>
</BODY>
</html>
