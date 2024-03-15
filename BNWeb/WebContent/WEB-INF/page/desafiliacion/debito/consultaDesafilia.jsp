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
<script language="JavaScript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/resetearcss.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/tipografias.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bn-estilos.css" />  
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/select.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/select.js"></script><SCRIPT language="javascript">


	function desafiliar(){
		var checkboxes = document.frmDesafiliacion.optSecuencia;
		
		if(checkboxes == undefined){
		alert('No existen registros para desafiliar');
		return;
		}
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
          
          	document.frmDesafiliacion.action = '<%=request.getContextPath()%>/AfilDebitoAutomatico.do?metodo=comprobarDesafil';
			document.frmDesafiliacion.submit();	
			}	
		   else
		    { 
		    
		    
		    alert("Debe de seleccionar solo un registro para desafiliar");
		    
		    }
			
		

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
<div id="contenidos-informativos">
	<h2>DESAFILIACIÓN DÉBITO AUTOMÁTICO</h2>
	<p>
		Marcar la casilla del servicio que desea desafiliar, las desafiliaciones sólo están 
					disponibles para los servicios afiliados en el día y los servicios afiliados confirmados.
	</p>	
	<p>Importante: El Horario de atención es de 6:00 am a 9:00 pm, no incluye domingos. En caso realice una afiliación o desafiliación en días feriados no laborables, se procesará el primer día útil siguiente.</p>
		<br/>
		<br/>
		<center>
		<div id="consulta-saldo">
		<logic:notEmpty name="afiliacionesReg">
				
			<TABLE cellspacing="0" cellpadding="0" width="600">
			
			
					<TR >
						<TD colspan="6" align="center" class="tituloTabla">SERVICIOS AFILIADOS DEL DÍA</TD>
					</TR>
					<TR>
					
						<TD  align="center" class="tituloCelda" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</TD>
					
						<TD  align="center" class="tituloCelda" width="256">ENTIDAD</TD>
						<TD align="center" width="226" class="tituloCelda">SERVICIO</TD>
						<TD align="center" class="tituloCelda" width="226">SUMINISTRO</TD>
						<TD align="center" class="tituloCelda" width="150">ESTADO</TD>
						<TD align="center" class="tituloCelda" width="100">FECHA</TD>
					</TR>
	
				
				
							<logic:iterate id="afil" name="afiliacionesReg" >
							
									<logic:equal name="afil" property="flagEstado" value="8">
								
									<TR align="center">
									
									
									<TD width="30" align="center" class="detalleCelda"><INPUT type="checkbox"
											name="optSecuencia"
											value="<bean:write name="afil" property="empresa"/>-<bean:write name="afil" property="servicio"/>-<bean:write name="afil" property="numSuministro"/>-<bean:write name="afil" property="flagEstado"/>-<bean:write name="afil" property="tipoDoc"/>-<bean:write name="afil" property="nroDoc"/>-<bean:write name="afil" property="tipoTel"/>-<bean:write name="afil" property="nroTel"/>-<bean:write name="afil" property="email"/>-<bean:write name="afil" property="via"/>-<bean:write name="afil" property="tope"/>-<bean:write name="afil" property="maximo"/>">
									</TD>
									<TD align="center"  width="226"  class="detalleCelda"><bean:write name="afil"
									property="empresaMostrar" /><INPUT type="hidden"  name="flag" value="<bean:write name="afil" property="flagEstado"/>"/>
									</TD>
										<TD align="center"  width="226"  class="detalleCelda"><bean:write name="afil"
									property="serviciomostrar" /> 
									</TD>
										<TD align="center"  width="226"  class="detalleCelda"><bean:write name="afil"
									property="numSuministro" />
									</TD>
									<TD align="center" width="150"  class="detalleCelda"><bean:write name="afil"
									property="flagMostrarEstado" />
									</TD>
									<TD align="right"  width="100"  class="detalleCelda"><bean:write name="afil"
									property="fecha" /> 
									</TD>
																	
										</TR>
										</logic:equal>
							</logic:iterate>
										
					</TABLE>
				
		</logic:notEmpty>
		<logic:notEmpty name="afiliacionesTram">
		<TABLE cellspacing="0" cellpadding="0" width="600">
			
					<BR/>
						<TR>
						<TD colspan="6" align="center" class="tituloTabla">SERVICIOS AFILIADOS EN TRÁMITE</TD>
					</TR>
					<TR>
					
						<TD  align="center" class="tituloCelda" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</TD>
					
						<TD  align="center" width="256" class="tituloCelda">ENTIDAD</TD>
						<TD align="center" width="226" class="tituloCelda">SERVICIO</TD>
						<TD align="center" width="226" class="tituloCelda">SUMINISTRO</TD>
						<TD align="center" width="150" class="tituloCelda">ESTADO</TD>
						<TD align="center" width="100" class="tituloCelda">FECHA</TD>
					</TR>
		
							<logic:iterate id="afil" name="afiliacionesTram" >
							
									<logic:equal name="afil" property="flagEstado" value="6">
								
									<TR align="center">
									
									
									<TD width="30" align="center" class="detalleCelda" ></TD>
									<TD align="center"  width="226" class="detalleCelda"><bean:write name="afil"
									property="empresaMostrar" /><INPUT type="hidden"  name="flag" value="<bean:write name="afil" property="flagEstado"/>"/>
									</TD>
										<TD align="center"  width="226" class="detalleCelda"><bean:write name="afil"
									property="serviciomostrar" /> 
									</TD>
										<TD align="center"  width="226" class="detalleCelda"><bean:write name="afil"
									property="numSuministro" /> 
									</TD>
									<TD align="center"  width="150" class="detalleCelda"><bean:write name="afil"
									property="flagMostrarEstado" /> 
									</TD>
									<TD align="right"  width="100" class="detalleCelda"><bean:write name="afil"
									property="fecha" />
									</TD>
																	
										</TR>
										</logic:equal>
							</logic:iterate>
										
					</TABLE>
				
		</logic:notEmpty>
		
			<logic:notEmpty name="afiliaciones">
		<TABLE cellspacing="0" cellpadding="0"  width="600">
			
					<BR/>
						<TR>
						<TD colspan="6" align="center" class="tituloTabla">SERVICIOS AFILIADOS CONFIRMADOS</TD>
					</TR>
					<TR>
					
						<TD  align="center" class="tituloCelda">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</TD>
					
						<TD  align="center" width="256" class="tituloCelda">ENTIDAD</TD>
						<TD align="center" width="226" class="tituloCelda">SERVICIO</TD>
						<TD align="center" width="226" class="tituloCelda">SUMINISTRO</TD>
						<TD align="center" width="150" class="tituloCelda">ESTADO</TD>
						<TD align="center" width="100" class="tituloCelda">FECHA</TD>
					</TR>
			
			
				
							<logic:iterate id="afil" name="afiliaciones" >
							
									<logic:equal name="afil" property="flagEstado" value="9">
								
									<TR align="center">
									
									
									<TD width="30" align="center" class="detalleCelda"><INPUT type="checkbox"
											name="optSecuencia"
											value="<bean:write name="afil" property="empresa"/>-<bean:write name="afil" property="servicio"/>-<bean:write name="afil" property="numSuministro"/>-<bean:write name="afil" property="flagEstado"/>-<bean:write name="afil" property="tipoDoc"/>-<bean:write name="afil" property="nroDoc"/>-<bean:write name="afil" property="tipoTel"/>-<bean:write name="afil" property="nroTel"/>-<bean:write name="afil" property="email"/>-<bean:write name="afil" property="via"/>-<bean:write name="afil" property="tope"/>-<bean:write name="afil" property="maximo"/>">
									</TD>
									<TD align="center"  width="226" class="detalleCelda"><bean:write name="afil"
									property="empresaMostrar" /> <INPUT type="hidden"  name="flag" value="<bean:write name="afil" property="flagEstado"/>"/>
									</TD>
										<TD align="center"  width="226" class="detalleCelda"><bean:write name="afil"
									property="serviciomostrar" /> 
									</TD>
										<TD align="center"  width="226" class="detalleCelda"><bean:write name="afil"
									property="numSuministro" />
									</TD>
									<TD align="center"  width="150" class="detalleCelda"><bean:write name="afil"
									property="flagMostrarEstado" />
									</TD>
									<TD align="center"  width="100" class="detalleCelda"><bean:write name="afil"
									property="fecha" />
									</TD>
																	
										</TR>
										</logic:equal>
							</logic:iterate>
										
					</TABLE>
				
		</logic:notEmpty>
		
		
	
	
 
   <logic:messagesPresent>
	<p class="importante"><html:errors/></p>
	</logic:messagesPresent>
	         					
	<br/>
	</div>
	<div class="boton1">
		<input type="button" value="CONTINUAR" onclick="javascript:desafiliar();"/>
		</div>  
	</center>
	</div>
</form>
</BODY>
</html>
