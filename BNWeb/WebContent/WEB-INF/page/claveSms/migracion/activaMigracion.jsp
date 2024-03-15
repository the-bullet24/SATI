<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld"	prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld"	prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld"	prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld"				prefix="c"%>
<HTML>
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<script language="JavaScript" src="<%=request.getContextPath()%>/js/util.js"></script>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/resetearcss.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/tipografias.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bn-estilos.css" />  
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.js"></script>

<style type="text/css">
	/*a:link{color:#4f4f4f;font-weight: bold;cursor: auto;}*/
	a:link{color:#c51416;font-weight: bold;cursor: auto; font-size:13px; font-family: Arial Narrow;}
	a:hover{color:#FF3C3C;text-decoration: underline;}	
	a:link:active{color:#4f4f4f;}
	a:visited:active{color:#4f4f4f;}
</style>

<script language="javascript">
	$(document).ready(function(){ 
	
	var tipoElemento = <c:out value="${tipoElemento.tipoElementoSeguridad}"></c:out>; 
	
	
	
		
	 	 
		$('#limpiar').click(function() {
			$('#txtCoordenada').val('');
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

	/*$('.tooltip').on( "click", function() {
		$('#dvHelpMessage').toggle();
	});*/

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
	    xmlhttp.open("POST", '<%=request.getContextPath()%>/claveSMSMigra.do?metodo=reGenerarClaveSmsMigracion', true);
		xmlhttp.send();
		} 

	}

	/*function mostrarTooltip(){
		//$('#dvHelpMessage').attr('style','display:block');
		//$('#dvHelpMessage').attr('style','display:none');
		$('#dvHelpMessage').toggle();

	}*/

	function validarMigracion(){
		var form = document.frmMigrarActivacion;
		if(form.txtCoordenada.value == ''){
			alert('Aseg\u00FArese de ingresar la Clave Din&aacute;mica Digital');
			return;
		}
		form.boton.disabled = true;
		form.action="<%=request.getContextPath()%>/claveSMSMigra.do?metodo=validarClaveSmsMigracion";
		form.submit();		

	}

	function evalRanTable(valor){
		var longitud = parseInt("8");
		if($("#txtCoordenada").val().length < longitud){
			$("#txtCoordenada").val($("#txtCoordenada").val()+valor);
		}
	}
</script>

<style type="text/css">
	/*a:link{color:#4f4f4f;font-weight: bold;cursor: auto;}*/
	a:link{color:#c51416;font-weight: bold; cursor: auto; font-size:13px; font-family: Arial Narrow;}
	
	a:hover{color:#FF3C3C;text-decoration: underline;}	
	a:link:active{color:#4f4f4f;}
	a:visited:active{color:#4f4f4f;}
</style>

<title>tran_int_ah.html</title>
</head>

<body oncontextmenu="return false" onselectstart="return false" ondragstart="return false"  onKeyDown="return cancelRefresh(event)">
<form name="frmMigrarActivacion" method="post">
	<input type="hidden" name="mensajeHidden" value="<%=request.getSession().getAttribute("mensajeHidden") %>">
	<input type="hidden" name="transaccion" value="AD01">
	<input type="hidden" name="hdnDesOperador" value=<bean:write name="desOperador"/> />
	<input type="hidden" name="hdnNroCelular" value=<bean:write name="nroCelular"/> />
	<div id="contenidos-informativos">
		<h2 style="font-weight:bolder;">MIGRACI&Oacute;N A CLAVE DIN&Aacute;MICA DIGITAL</h2>
   
		<div style="width:auto;">
			<table cellspacing="20">
				<tr>
					<td class="iz" colspan="3">
						<p style="text-align:left; font: 13px/23px arial;padding: 0px 0px 0px 0px;">Hemos enviado un c&oacute;digo de verificaci&oacute;n por SMS. Ingrese el c&oacute;digo para confirmar que es su celular.</p>
					</td>					
				</tr>
				<tr>
					<td class="iz" width="268 px">
						<label style="text-align: left !important;padding: 0px 0px 0px 0px;">Celular Afiliado a Clave Din&aacute;mica Digital:</label>
					</td>
					<td class="der" colspan="3">
						<label style="font: 13px/23px arial; text-align: left !important;padding: 0px 0px 0px 0px;">
							<bean:write name="nroCelular"/>
						</label>
					</td>
				</tr>
						
				<tr>
					<td class="iz" width="268 px;padding: 0px 0px 0px 0px;">
						<label style="text-align: left !important;" >Operador:</label>
					</td>
					<td class="der" colspan="3">
						<label style="font: 13px/23px arial; text-align: left !important;padding: 0px 0px 0px 0px;">
							<bean:write name="desOperador"/>
						</label>
					</td>
				</tr>

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
						
						<div class="tooltip">
							<u style="color: #273C4E; cursor:pointer; font-family: Arial Narrow;font-size:13px;font-weight:bold;">
								&#191;Nunca lleg&oacute; el C&oacute;digo&#63;
								<img border="0" src="<%=request.getContextPath()%>/imagenes/bn/ios_icon_info_blue28x28.png" style="float:right;margin-top:-3px;" width="18px" height="18px"> 
							</u>										
						</div>	
					</td>					
				</tr>

			</table>

			<div class="clear cincopx"></div>
			
			</br>
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
			</br>
			
			
			
			
			<div id="botones" class="boton" style="margin-top:50px">
				<input type="button"  id="boton" name="boton"  value="SIGUIENTE" onclick="javascript:validarMigracion();"/>
			</div> 
		</div>


		<div class="cysInfoMsg" style="display:none">
		</div>
		
	</div>
	
</form>

</body>
</html>
	