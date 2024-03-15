<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld"	prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld"	prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld"	prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld"				prefix="c"%>
<HTML>
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/resetearcss.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/tipografias.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bn-estilos.css" />  
<script language="JavaScript" src="<%=request.getContextPath()%>/js/util.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/select.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/proyecto.js"></script>

<style type="text/css">
	/*a:link{color:#4f4f4f;font-weight: bold;cursor: auto;}*/
	a:link{color:#c51416;font-weight: bold;cursor: auto; font-size:13px; font-family: Arial Narrow;}
	a:hover{color:#FF3C3C;text-decoration: underline;}	
	a:link:active{color:#4f4f4f;}
	a:visited:active{color:#4f4f4f;}
</style>

<SCRIPT language="javascript">
	$(document).ready(
	 function(){ 
	 	    
	 	 var tipoElemento = <c:out value="${tipoElemento.tipoElementoSeguridad}"></c:out>; 
	 	    
	 	 
	 	 
	     $("#limpiar").click(function(){
	     	$("#txtCoordenada").val("");
	     });
	     
	     if (tipoElemento == 5) {
          $('#txtCoordenada').attr('maxlength', '6');
         } else if (tipoElemento == 6) {
          $('#txtCoordenada').attr('maxlength', '8');
         }	
		 
		 $('.tooltip').click(function() {
			$('#dvHelpMessage').toggle();
		});
		
		
		
	 });
	

	function anularAfiliacionClaveSms(){
		var form = document.frmActivarClaveSms;
		form.action="<%=request.getContextPath()%>/claveSMSDesafilia.do?metodo=iniciarDesafiliacion";
		form.submit();
	
	}
	
	function activar(){
		var form = document.frmActivarClaveSms;
	
		if(form.chkAceptar.checked == false){
			alert('Tiene que Aceptar las Condiciones Generales');
			return;
		} else if(form.txtCoordenada.value.trim() == ''){
			alert('Tiene que ingresar la Clave Dinámica Digital');
			return;
		}
		

		form.action="<%=request.getContextPath()%>/claveSMSActiva.do?metodo=activarClaveSms";
		form.submit();
		

		
	
	}


	function evalRanTable(valor){
	
		var tipoElemento = <c:out value="${tipoElemento.tipoElementoSeguridad}"></c:out>;
		if(tipoElemento=='5')var longitud = parseInt("6");
		if(tipoElemento=='6')var longitud = parseInt("8");
		if(tipoElemento=='7')var longitud = parseInt("8");
		
		if($("#txtCoordenada").val().length < longitud){
			$("#txtCoordenada").val($("#txtCoordenada").val()+valor);
		}
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
		
	    xmlhttp.open("POST", '<%=request.getContextPath()%>/claveSMSMigra.do?metodo=reGenerarClaveSmsActivacion', true);
		xmlhttp.send();
		}
	}
</SCRIPT>

<style type="text/css">
	/*a:link{color:#4f4f4f;font-weight: bold;cursor: auto;}*/
	a:link{color:#c51416;font-weight: bold; cursor: auto; font-size:13px; font-family: Arial Narrow;}
	
	a:hover{color:#FF3C3C;text-decoration: underline;}	
	a:link:active{color:#4f4f4f;}
	a:visited:active{color:#4f4f4f;}
</style>

<TITLE>tran_int_ah.html</TITLE>
</HEAD>
<body onselectstart="return false" ondragstart="return false" oncontextmenu="return false"  onKeyDown="return cancelRefresh(event);">
<form name="frmActivarClaveSms" method="post" >
<input type="hidden" name="metodo">
<INPUT type="hidden" name="transaccion" value="AD01">


<div id="contenidos-informativos">
	<h2 style="font-weight:bolder;">ACTIVACI&Oacute;N DE CLAVE DIN&AACUTE;MICA DIGITAL</h2>


	<div style="width:auto;">
		<table cellspacing="20">
			<tr>
				<td class="iz" colspan="3">
					<p style="text-align:left; font: 13px/23px arial;padding: 0px 0px 0px 0px;">Para confirmar la afiliaci&oacute;n, deber&aacute; ingresar el c&oacute;digo que hemos enviado al celular que afili&oacute;.</p>
				</td>					
			</tr>
			<tr>
				<td class="iz" width="268 px">
					<label style="text-align: left !important;padding: 0px 0px 0px 0px;">Celular Afiliado a Clave Din&aacute;mica Digital:</label>
				</td>
				<td class="der" colspan="3">
					<label style="font: 13px/23px arial; text-align: left !important;padding: 0px 0px 0px 0px;">
						<c:out value="${tipoElemento.numberMobile}"></c:out>
					</label>
				</td>
			</tr>
							
			<tr>
				<td class="iz" width="268 px">
					<label style="text-align: left !important;padding: 0px 0px 0px 0px;">Operador:</label>
				</td>
				<td class="der" colspan="3">
					<label style="font: 13px/23px arial; text-align: left !important;padding: 0px 0px 0px 0px;">
						<c:out value="${tipoElemento.desOperatorMobile}"></c:out>
					</label>
				</td>
			</tr>

			<tr>						
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
										<u>Reenviar c&oacute;digo &nbsp;<img border="0" src="<%=request.getContextPath()%>/imagenes/bn/refresh_24_px.png" width="12px" height="12px"></u> 
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
					<div class="clear cincopx"></div>
						<div class="tooltip">
							<a style="font-weight:bold;font-family: Arial Narrow;font-size:14px; text-align:left;">&#191;Nunca lleg&oacute; el C&oacute;digo&#63;</a>
							<img border="0" src="<%=request.getContextPath()%>/imagenes/bn/ios_icon_info_blue28x28.png"  width="18px" height="18px"> 						
					</div>	
				</td>					
			</tr>
		
		</table>
	</div>

	<div id="dvHelpMessage" class="cysErrorMsg"  style="display: none; text-align:left !important;">
		<span style="line-height:17px;font-weight:bold;color:#000000;">
				&#191;Nunca lleg&oacute; el c&oacute;digo&#63; Es probable que tengas problemas de conectividad de wifi y/o datos m&oacute;viles o has cambiado de dispositivo telef&oacute;nico. 
				Si es as&iacute;, ac&eacute;rcate a nuestras agencias para solicitar una nueva afiliaci&oacute;n.
		</span>
	</div>

	<logic:messagesPresent>
		<div class="cysErrorMsg" id="cysErrorMsg">
			<html:errors/>
		</div>
	</logic:messagesPresent>		

	
	<div style="text-align:center; margin-top:20px;">
		<textarea style="text-align: justify; font-stretch: normal; font-size:11px"  rows="12" cols="100" name="TXTUNO0" class="textarea" readonly="readonly">
			<c:out value='${mensajeCondicionesCLVSMS1}${mensajeCondicionesCLVSMS2}${mensajeCondicionesCLVSMS3}${mensajeCondicionesCLVSMS4}' escapeXml="false" />
		</textarea><br/>

	</div><br/>

	

	<div style="text-align:center; margin-top:20px;">
		<input type="checkbox" name="chkAceptar" id="chkAceptar" value="S" class="textizqn"/><span class="span">Acepto condiciones</span>
	</div>
	
	<div id="botones-ini-act" class="botonl" style="text-align:center;margin-top:40px;">
		<input type="button" onclick="javascript:anularAfiliacionClaveSms();" style="width:190px;font: 13px/24px 'daxcompact-mediumregular';" 
		id="anular" value="ANULAR AFILIACION CLAVE DINAMICA DIGITAL"/>
		<input type="button" onclick="javascript:activar();" id="activarClaveSms" name="activarClaveSms" value="ACTIVAR CLAVE DINAMICA DIGITAL"/>
	</div>	
	
	</div>


</form>
</BODY>
</HTML>
