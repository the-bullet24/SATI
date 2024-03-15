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

<style type="text/css">
	/*a:link{color:#4f4f4f;font-weight: bold;cursor: auto;}*/
	a:link{color:#c51416;font-weight: bold;cursor: auto; font-size:13px; font-family: Arial Narrow;}
	a:hover{color:#FF3C3C;text-decoration: underline;}	
	a:link:active{color:#4f4f4f;}
	a:visited:active{color:#4f4f4f;}
</style>

<script language="JavaScript" src="<%=request.getContextPath()%>/js/util.js"></script>
<SCRIPT language="javascript">

	function desafiliar(){
		var checkboxes = document.frmAfilCelular.optSecuencia;
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
		
		
		

		document.frmAfilCelular.listDesafilia.value = cadena;
		
			var tipoElemento = '';
		<c:if test="${tipoElemento.tipoElementoSeguridad!=null}">
		
		 tipoElemento = <c:out value="${tipoElemento.tipoElementoSeguridad}"></c:out>;
		</c:if>	
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
		} else if(tipoElemento == '6') {
			if (validacampo("txtCoordenada")){
			alert('Es necesario ingresar los 8 dígitos del token');
			return;
			}
			if (validalongitud("txtCoordenada","8")){
				alert('El pin del Token es de 8 dígitos, no menos');
				return;
			}
		} else {
				if(tipoElemento == '2')
			{
			if (validacampo("txtCoordenada")){
			alert('Es necesario ingresar el valor de la coordenada');
			return;
			}
			if (validalongitud("txtCoordenada","2")){
				alert('El valor de la coordenada es de 2 dígitos, no menos');
				return;
			}
		  }
		}
		
		 contador=0; 
	
		for (i=0;i<document.frmAfilCelular.optSecuencia.length;i++) 
        {   if (document.frmAfilCelular.optSecuencia[i].checked==true)  
            { 
           
            contador++;
            }
            
         }
                  
         if (document.frmAfilCelular.optSecuencia.checked==true)  
             contador=1; 

           if (contador==1)
           { 
             
          	document.frmAfilCelular.action = '<%=request.getContextPath()%>/AfilBancaCelular.do?metodo=desafiliar';
			document.frmAfilCelular.submit();	
			}	
		   else
		    { 
			    alert("Debe de seleccionar solo un registro para desafiliar");
		    
		    }
		
	}
	
	function mostrar(){
		var form = document.frmAfilCelular;
				
						if (validalongitud("txtNumeroServicio","9")){
								alert('El número del Celular debe contener 9 Digitos, no menos');
								return;
							}
						var numTelCel  = form.txtNumeroServicio.value;
						var cPrimerNumero = numTelCel.substring(0,1)
							if (cPrimerNumero != "9"){
							alert('El primer dígito del Num. Celular debe ser 9');
							return;
							}
							
							
	
		form.action="<%=request.getContextPath()%>/AfilBancaCelular.do?metodo=mostrarDesafiliacion";
		form.submit();
	
	}
	
	var enableLinkReenvio = true;
	function generarClaveSms(e){		
				
				var xmlhttp = new XMLHttpRequest();

		if(enableLinkReenvio == true){	
			enableLinkReenvio = false;  
	    	xmlhttp.onreadystatechange = function() {			
        		if (this.readyState == 4 && this.status == 200) {        		
        			$('#lnkGenerarClave').addClass('disabled');   
        			enableLinkReenvio = false;         
        			if ($("#lnkGenerarClave").hasClass('disabled')) {
            			setTimeout(function() {
                		$('#lnkGenerarClave').removeClass('disabled');
                		enableLinkReenvio = true;
            			},60000);
        			} 
       			}       		
	       		if(this.readyState == 4 && this.status == "404"){	
	       			enableLinkReenvio = true;  
	       			var posicion = this.responseText.indexOf("<!");
	       			if(posicion!=-1){	       			
        				alert(this.responseText.substr(0, posicion-1));
        			} else {
        				alert(this.responseText);
        			}
        		}       
        		if(this.status == 408){
        			enableLinkReenvio = true;  
					window.location.reload();
        		}     
    		};
		
	    xmlhttp.open("POST", '<%=request.getContextPath()%>/AfilBancaCelular.do?metodo=reGenerarClaveSms', true);
	    xmlhttp.send();
	    }
	}
	
	function evalRanTable(valor){
	
		var tipoElemento = <c:out value="${tipoElemento.tipoElementoSeguridad}"></c:out>;
		if(tipoElemento=='5')var longitud = parseInt("6");
		if(tipoElemento=='6')var longitud = parseInt("8");
		
		if($("#txtCoordenada").val().length < longitud){
			$("#txtCoordenada").val($("#txtCoordenada").val()+valor);
		}
	}
	
	$(document).keydown(function(evt) { 
 		var evt = (evt) ? evt : ((event) ? event : null);
        var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null);
            
  		if ( evt.keyCode == 8 && node.type == 'password' ) {
        	return false;
        }
    });
	
	$(document).ready(
	 	function(){ 
	 	     var tipoElemento = <c:out value="${tipoElemento.tipoElementoSeguridad}"></c:out>;  
	     

	 	 if (tipoElemento == 5) {
          $('#txtCoordenada').attr('maxlength', '6');
        } else if (tipoElemento == 6) {
          $('#txtCoordenada').attr('maxlength', '8');
        }	

		$("#limpiar").click(function(){
     		$("#txtCoordenada").val("");
		});

		$('.tooltip').click(function() {
			$('#dvHelpMessage').toggle();
		});
	 
	 myApp.select.init();
	 
	 });
	
</SCRIPT>

</HEAD>
<body oncontextmenu="return false" onselectstart="return false" ondragstart="return false"  onKeyDown="return cancelRefresh(event)"  bgcolor="white" >
<html:form type="pe.bn.afiliacion.action.AfiliacionBancaCelularAction"  action="/AfilBancaCelular.do" method="POST" >
<input type="hidden" name="listDesafilia">
<input type="hidden" name="numCelular" value="<bean:write name="<%=pe.cosapi.common.ConstanteSesion.AFIL_BANCA_CELULAR%>" property="numeroCelular" />">
<input type="hidden" name="operador" value="<bean:write name="<%=pe.cosapi.common.ConstanteSesion.AFIL_BANCA_CELULAR%>" property="mostrarOperador" />">
<input type="hidden" name="aliasCuenta" value ="<bean:write name="<%=pe.cosapi.common.ConstanteSesion.AFIL_BANCA_CELULAR%>" property="alias" />">

<div id="contenidos-informativos">
	<h2>DESAFILIACIÓN DE OPERACIONES DE BANCA CELULAR</h2>
	<p>${mensajeCabeceraDesafiliacion}</p>
		<div id="consulta-datos">
			
				    <div class="formEstandar">
				    	<div class="izq" style="width: 350px;">
		 					<label style="margin: 5px;">Número de Celular:</label>
		 					<html:text styleClass="input-chico" style="margin: 5px;"property="txtNumeroServicio"  maxlength="9" onkeypress="return soloNumerosAll(event)" style="margin: 5px;"/>
						</div>
						<div class="der" style="width: 150px;">
							<div class="boton1" style="margin: -4px 0px 10px; padding: 0px;">
													<input type="button" value="CONSULTAR" onclick="javascript:mostrar();"/>
							</div>   
				    	</div>
				    	<div class="clear cincopx"></div>
				    </div>	
			
			</div>

	<div class="clear"></div>
	<div id="consulta-saldo">
		<CENTER>
		
			<logic:notEmpty name="consultaDesafiliar">
		<TABLE>
	
				<TR>
                                <TD class="fila-izquierda">Cuenta:</TD>
                                <TD class="fila-derecha"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.AFIL_BANCA_CELULAR%>" property="cuenta.cuentaFormateada" /> </TD>
                </TR>
				
			
				<TR>
								<TD class="fila-izquierda">Operador:</TD>
								<TD class="fila-derecha"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.AFIL_BANCA_CELULAR%>" property="mostrarOperador" /> </TD>
				</TR>
				<TR>
								<TD class="fila-izquierda">Celular:</TD>
								<TD class="fila-derecha"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.AFIL_BANCA_CELULAR%>" property="numeroCelular" /> </TD>
				</TR>
				
				<TR>
								<TD>&nbsp;</TD>
				</TR>
			</TABLE>
			
			<TABLE cellspacing="0" cellpadding="0" width="460">
								<TBODY>
									
									<TR>
										<TD class="tituloTabla"  width="5" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</TD>
					
										<TD class="tituloTabla" width="5">N°&nbsp;&nbsp;&nbsp;&nbsp;</TD>										
										<TD class="tituloTabla" width="125">SERVICIO</TD>
										<TD class="tituloTabla" width="125">ALIAS</TD>
										<TD class="tituloTabla" width="250">REFERENCIA</TD>
										
									</TR>
							</TBODY>
			
					
			
				
							<logic:iterate id="consulta" name="consultaDesafiliar" >
									
																
									<TR align="center">
									<TD width="5" align="center" class="detalleCelda"><INPUT type="checkbox"
											name="optSecuencia"
											value="<bean:write name="consulta" property="servicio"/>-<bean:write name="consulta" property="aliasServicio"/>-<bean:write name="consulta" property="numReferencia" />">
									</TD>
									<TD align="center" class="detalleCelda" width="5" ><bean:write name="consulta" property="secuencia" />
									</TD>
									<TD align="center" class="detalleCelda" width="125" ><bean:write name="consulta" property="servicio" /> 
									</TD>
									<TD align="center" class="detalleCelda" width="125"><bean:write name="consulta" property="aliasServicio" /> 
									</TD>
									<TD align="center" class="detalleCelda" width="250"><bean:write name="consulta" property="numReferencia" /> 
									</TD>														
									</TR>
								
							</logic:iterate>
										
					</TABLE>
	
		
		<div class="fila limpiar">
			&nbsp;
		</div>
		<div id="consulta-datos">
			<c:if test="${tipoElemento.tipoElementoSeguridad=='2'}">
		<div class="fila limpiar">
			<label>Ingresar la Siguiente Coordenada</label>
			<input type="password" name="txtCoordenada" class="input-chico txtCoordenada"  maxlength="2" <c:if test="${resultCoord.coordConcat eq null}"> readonly="readonly" </c:if> onkeypress="return soloNumerosAll(event)"/>
			<div class="coordenada">
				<c:if test="${resultCoord.coordConcat eq null}">No disponible</c:if>
				<c:if test="${resultCoord.coordConcat ne null}">&nbsp;&nbsp;<c:out value="${resultCoord.coordConcat}"/></c:if>
			</div>
			<div class="clear"></div>
		</div>
		</c:if>
		<c:if test="${tipoElemento.tipoElementoSeguridad=='5'}">
		<div class="fila limpiar">
			<label>Ingresar los 6 dígitos del TOKEN </label>
			<input type="password" name="txtCoordenada" class="input-chico" size="10" maxlength="6"  onkeypress="return soloNumerosAll(event)"/>
			<img border="0" src="<%=request.getContextPath()%>/imagenes/bn/toke.jpg"/>
		</div>
		<tr>
				<td class="ingreso">
							<br/>
							<br/>
						</td>
						<td class="ingreso"></td>
		</tr>
		</c:if>
		
		
		<c:if test="${tipoElemento.tipoElementoSeguridad=='6'}">
		<div class="fila limpiar">
			<div>
				<table>
					<tr colspan="1">

					<td width="268 px">
				
					<table>
						<tr>
							
		
					<%@ page import="java.util.Map"%>
					<%@ page import="pe.cosapi.system.TecladoUtil"%>
					<%@ page import="pe.cosapi.common.ConstanteSesion"%>
					<%
						Map mapa2 = (Map) request.getSession().getAttribute(
						ConstanteSesion.MAP_VALUES);
						TecladoUtil teclado2 = new TecladoUtil();
						teclado2.asignar(mapa2, request);
					%>
					<td class="ingreso" style="width: auto !important;">
						<div class="fila limpiar">
							<div id="botones-clave">
								<div class="boton-clave" onclick="evalRanTable('m');">
									<span class="dax"><%=teclado2.getAlt_0()%></span>
								</div>
								<div class="boton-clave" onclick="evalRanTable('n');"><%=teclado2.getAlt_1()%></div>
								<div class="boton-clave" onclick="evalRanTable('p');"><%=teclado2.getAlt_2()%></div>
								<div class="boton-clave" onclick="evalRanTable('i');"><%=teclado2.getAlt_3()%></div>
								<div class="boton-clave" onclick="evalRanTable('j');"><%=teclado2.getAlt_4()%></div>
								<div class="boton-clave" onclick="evalRanTable('k');"><%=teclado2.getAlt_5()%></div>
								<div class="boton-clave" onclick="evalRanTable('a');"><%=teclado2.getAlt_6()%></div>
								<div class="boton-clave" onclick="evalRanTable('y');"><%=teclado2.getAlt_7()%></div>
								<div class="boton-clave" onclick="evalRanTable('x');"><%=teclado2.getAlt_8()%></div>
								<div class="boton-clave" onclick="evalRanTable('t');"><%=teclado2.getAlt_9()%></div>
								<div class="boton-clave limpiar" id="limpiar">LIMPIAR</div>
							</div>
							<input type="hidden" value="<%=teclado2.getRandomEncript()%>" name="hdnValue">
						</div>
					</td>
		
					<td>
					<div style="height: 140px!important;float: left;">
					<table>
						<tbody>
							<tr>
								<td>
									<label style="text-align: left;font: 11px/23px arial;width: 130px;padding-top: 8px;">Ingresa aqu&iacute; el c&oacute;digo.</label>
								</td>
							</tr>
		
							<tr>
								<td>
									<div class="clear cincopx"></div>
									<div id="campo-clave">
										<input type="password" name="txtCoordenada" id="txtCoordenada" maxlength="8"
										readonly="readonly" onkeypress="return soloNumerosAll(event)" style="width: 120px;">
									</div>
								</td>
							</tr>
		
							<tr>
								<td>
									<div class="clear cincopx"></div>
									<a href="javascript:" onclick="generarClaveSms(event)" style="cursor:pointer; display: inline-block;" id="lnkGenerarClave"> 
										<u>Generar Clave Din&aacute;mica Digital &nbsp;<img border="0" src="<%=request.getContextPath()%>/imagenes/bn/refresh_24_px.png" width="12px" height="12px"></u> 
									</a>							
								</td>
							</tr>
						</tbody>
					</table>
					</div>
					</td>
					
					</tr>
					</table>
					
					</td>
					
					<td>
						<div style="height: 140px!important;float: left;">
							<img border="0" src="<%=request.getContextPath()%>/imagenes/bn/clve_sms_ico_mdpi.png" style="float: left; padding-top: 15px;">
						</div>
					</td>
				</tr>
				
				<tr>
					<td class="iz" colspan="4">
						
						<div class="tooltip">
							<u style="color: #273C4E; cursor:pointer; font-family: Arial Narrow;font-size:13px;font-weight:bold;">
								&#191;Nunca lleg&oacute; el C&oacute;digo&#63;
								<img border="0" src="<%=request.getContextPath()%>/imagenes/bn/ios_icon_info_blue28x28.png" style="float:right;margin-top:-3px;" width="18px" height="18px"> 
							</u>										
						</div>	
					</td>					
				</tr>
				</table>
			
			</div>
				     
           
		
		<div class="fila limpiar">
		</c:if>
		
		<c:if test="${tipoElemento.tipoElementoSeguridad=='2'}">
		<div class="fila limpiar">
			<p><b><u>Ejemplo:</u>
			</b><br/>
			Al solicitarle la coordenada 6 - F, deberás buscar la fila correspondiente al <b>número
			6</b> y la columna de la letra  F, en la unión de ambos, obtendrás un número, éste número deberás ingresarlo para aprobar la operación.</p>
		</div>
		</c:if>
		<c:if test="${tipoElemento.tipoElementoSeguridad=='5'}">
		<div class="fila limpiar">
			<p><b><u>Nota:</u>
			</b><br/>Tener en cuenta que los 6 dígitos cambian cada minuto por lo cual debe ingresar antes que la 
			barra de tiempo se haya consumido.</p>
		</div>
		</c:if>	
		
		<c:if test="${tipoElemento.tipoElementoSeguridad=='6'}">
		<div class="fila limpiar">
			<p><b><u>Nota:</u>
			</b><br/>Para confirmar la operaci&oacute;n deber&aacute;s ingresar la Clave Din&aacute;mica Digital enviada a tu dispositivo telef&oacute;nico vinculado.</p>
		</div>
		</c:if>	
	
	</logic:notEmpty>	
	
	<p>${mensajePieDesafiliacion}</p>
	</div>
		<logic:empty name="consultaDesafiliar">
			<p class="mensaje">No existen registros asociados.</p>
			
			</logic:empty>
			</div>	
	<logic:notEmpty name="consultaDesafiliar">
	
	<div class="boton1">
	
		<input type="button" value="DESAFILIAR" onclick="javascript:desafiliar();"/>
	</div>  
	</logic:notEmpty>
	<br/>
	<div class="clear cincopx"></div>
			
		</br>
		<div id="dvHelpMessage" class="cysErrorMsg"  style="display: none; text-align:left !important;">
			<span style="line-height:17px;font-weight:bold;color:#000000;">
					&#191;Nunca lleg&oacute; el c&oacute;digo&#63; Es probable que tengas problemas de conectividad de wifi y/o datos m&oacute;viles o has cambiado de dispositivo telef&oacute;nico. 
					Si es as&iacute;, ac&eacute;rcate a nuestras agencias para solicitar una nueva afiliaci&oacute;n.
			</span>
		</div>
	<logic:messagesPresent>
	<div class="cysErrorMsg">
		<html:errors/>
	</div>
	</logic:messagesPresent>	



<script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-funciones.js"></script>
<script type="text/javascript">
   $(document).ready(function(){
		myApp.select.init();
	});
</script>	
</html:form>

</BODY>
</html>
