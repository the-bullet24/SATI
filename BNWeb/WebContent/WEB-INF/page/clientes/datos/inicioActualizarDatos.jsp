<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld"	prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld"	prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld"	prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld"				prefix="c"%>
<HTML>
<HEAD>
<TITLE>Banco de la Nación</TITLE>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<script language="JavaScript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
<script language="JavaScript" src="<%=request.getContextPath()%>/js/util.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/select.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/resetearcss.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/tipografias.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bn-estilos.css" />  

<style type="text/css">
	/*a:link{color:#4f4f4f;font-weight: bold;cursor: auto;}*/
	a:link{color:#c51416;font-weight: bold;cursor: auto; font-size:13px; font-family: Arial Narrow;}
	a:hover{color:#FF3C3C;text-decoration: underline;}	
	a:link:active{color:#4f4f4f;}
	a:visited:active{color:#4f4f4f;}
</style>

<script language="javascript">

	function continuar(){
		var form = document.frmActualizacionDatos;
		
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
		} else if(tipoElemento == '6') {
			if (validacampo("txtCoordenada")){
			alert('Es necesario ingresar los 8 dígitos del token');
		
			return;
			}
			if (validalongitud("txtCoordenada","8")){
				alert('El pin del Token es de 8 dígitos, no menos');
			
				return;
			}
		} else{
			if (validacampo("txtCoordenada")){
			alert('Es necesario ingresar el valor de la coordenada');
			
			return;
			}
			if (validalongitud("txtCoordenada","2")){
				alert('El valor de la coordenada es de 2 dígitos, no menos');
				
				return;
			}
		}
				
		form.action="<%=request.getContextPath()%>/AfilDatosClientes.do?metodo=consultaDatos";
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
		
	    xmlhttp.open("POST", '<%=request.getContextPath()%>/AfilDatosClientes.do?metodo=generarClaveSmsActualizacion', true);
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
 	var flagActDatos = <c:out value="${USUARIO_SESION.flagActualizaDatoHost}"></c:out>; 
     
     if(flagActDatos == "1"){
         alert("Antes de realizar alguna operaci?n requerimos que actualice sus datos.");
     }
	 
	 
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
 
 });
</script>


</HEAD>

<body oncontextmenu="return false" onselectstart="return false" ondragstart="return false" onKeyDown="return cancelRefresh(event)">
<form  name="frmActualizacionDatos" method="post">
<input type="hidden" name="metodo" >

	<div id="contenidos-informativos">
        <h2>ACTUALIZACI&Oacute;N DE DATOS</h2>
		<div class="fila limpiar"></DIV>
		<div class="fila limpiar"></DIV>
		<div class="fila limpiar"></DIV>
		<div class="fila limpiar">
		
		</div>
		<br/>
		<div class="fila limpiar">
		<label for="cliente" class="cerrar">${mensajeCabeceraInicio} </label></div>
		<br/><br/>
		<div>
			<table>
				<c:if test="${tipoElemento.tipoElementoSeguridad=='5'}">
				  	<tr>
						<td class="ingreso">
					
					        <%@ page import="java.util.Map"%>
							<%@ page import="pe.cosapi.system.TecladoUtil"%>
							<%@ page import="pe.cosapi.common.ConstanteSesion"%>
							<%						
								Map mapa  = (Map)request.getSession().getAttribute(ConstanteSesion.MAP_VALUES);
								TecladoUtil teclado = new TecladoUtil();
								teclado.asignar(mapa,request);
								
							%>
						    <div class="fila limpiar">
			                        <label for="clave" style="width: 140px;">Utilice el teclado virtual para ingresar el Token:</label>
			                        <div id="botones-clave">
			                            <div class="boton-clave" onclick="evalRanTable('m');"><span class="dax" ><%=teclado.getAlt_0()%></span></div>
			                            <div class="boton-clave" onclick="evalRanTable('n');" ><%=teclado.getAlt_1()%></div>
			                            <div class="boton-clave" onclick="evalRanTable('p');" ><%=teclado.getAlt_2()%></div>
			                            <div class="boton-clave" onclick="evalRanTable('i');" ><%=teclado.getAlt_3()%></div>
			                            <div class="boton-clave" onclick="evalRanTable('j');" ><%=teclado.getAlt_4()%></div>
			                            <div class="boton-clave" onclick="evalRanTable('k');" ><%=teclado.getAlt_5()%></div>
			                            <div class="boton-clave" onclick="evalRanTable('a');" ><%=teclado.getAlt_6()%></div>
			                            <div class="boton-clave" onclick="evalRanTable('y');" ><%=teclado.getAlt_7()%></div>
			                            <div class="boton-clave" onclick="evalRanTable('x');" ><%=teclado.getAlt_8()%></div>
			                            <div class="boton-clave" onclick="evalRanTable('t');" ><%=teclado.getAlt_9()%></div>
			                            <div class="boton-clave limpiar" id="limpiar">LIMPIAR</div>
			                        </div>
									<input type="hidden" value="<%=teclado.getRandomEncript()%>"  name="hdnValue">
			                </div>
		            	</td>

		               
									
						<td class="ingreso"><img border="0" src="<%=request.getContextPath()%>/imagenes/bn/toke.jpg" style="float: left;"/>
				            <div id="campo-clave">                     	
			                    <p style="width: 124px;">Ingresar los 6 dígitos del TOKEN</p>
			                    <input type="password" name="txtCoordenada" id="txtCoordenada" maxlength="6"  readonly="readonly" onkeypress="return soloNumerosAll(event)" style="margin: 0px 10px;width: 150px;"/>   
			                </div>
						</td>
					</tr>
				</c:if>

				<c:if test="${tipoElemento.tipoElementoSeguridad=='6'}">
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
                                    <c:if test="${USUARIO_SESION.flagActualizaDatoHost=='0'}">
                                    <a href="javascript:" onclick="generarClaveSms(event)" style="cursor:pointer; display: inline-block;" id="lnkGenerarClave"> 
                                        <u>Reenviar c&oacute;digo &nbsp;<img border="0" src="<%=request.getContextPath()%>/imagenes/bn/refresh_24_px.png" width="12px" height="12px"></u> 
                                    </a>                            
                                    </c:if>
                                    <c:if test="${USUARIO_SESION.flagActualizaDatoHost=='1'}">
                                    <a href="javascript:" onclick="generarClaveSms(event)" style="cursor:pointer; display: inline-block;" id="lnkGenerarClave"> 
                                        <u>Generar c&oacute;digo &nbsp;<img border="0" src="<%=request.getContextPath()%>/imagenes/bn/refresh_24_px.png" width="12px" height="12px"></u> 
                                    </a>                            
                                    </c:if>		                                    	
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
							<a style="font-weight:bold;font-family: Arial Narrow;font-size:14px; text-align:center;">&#191;Nunca lleg&oacute; el C&oacute;digo&#63;</a>
							<img border="0" src="<%=request.getContextPath()%>/imagenes/bn/ios_icon_info_blue28x28.png" style="float:center;" width="18px" height="18px"> 					
						</div>		
						
					</td>					
				</tr>
				</c:if>

				<c:if test="${tipoElemento.tipoElementoSeguridad=='2'}">				
					<tr>
						<td colspan="2"  class="ingreso"  style="width:592px;" ><p><u>Ejemplo:</u>
							Al solicitarle la coordenada <strong>6 - F</strong>, deberás buscar la fila correspondiente al <strong>número
							6</strong> y la columna de la <strong>letra  F</strong>, en la unión de ambos, obtendrás un número, éste número deberás ingresarlo para aprobar la operación.</p>
						</td>
					</tr>
					
				</c:if>

				<c:if test="${tipoElemento.tipoElementoSeguridad=='5'}">
					<tr>
						<td colspan="2" class="ingreso" style="width:592px;"><p><u>Nota:</u>
							<br> 
							Tener en cuenta que los 6 dígitos cambian cada minuto por lo cual debe ingresar antes que la 
							barra de tiempo se haya consumido.</p>
						</td>
					</tr>
				</c:if>
				
				<c:if test="${tipoElemento.tipoElementoSeguridad=='6'}">
					<tr>
						<td colspan="2" class="ingreso" style="width:592px;"><p><u>Nota:</u>
							<br> 
							Para confirmar la operaci&oacute;n deber&aacute;s ingresar la Clave Din&aacute;mica Digital enviada a tu dispositivo telef&oacute;nico vinculado.</p>
						</td>
					</tr>
				</c:if>
			</table>
		</div>
		
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
	
	<div class="boton">
			<input type="button" value="CONTINUAR" name="boton" onclick="javascript:continuar();"/>
	</div> 
	
	</div>
</form>
</BODY>
</HTML>