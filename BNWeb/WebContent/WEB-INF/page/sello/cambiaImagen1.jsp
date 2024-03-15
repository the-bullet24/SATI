<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/c.tld"	prefix="c"%>
<html xmlns="http://www.w3.org/1999/xhtml" lang="es" xml:lang="es"> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"/>
<meta name="GENERATOR" content="IBM Software Development Platform"/>
<meta content="no-cache" http-equiv="pragma"/>
<meta content="no-cache" http-equiv="cache-control"/>
<script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/js/control.js"></script>
<script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/js/util.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/resetearcss.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/tipografias.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/sello-seguridad.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/select.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bn-estilos.css" />    

<script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/select.js"></script>

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

<SCRIPT language="javascript">

	function chequear(id){
		jQuery("#selTarjeta"+id).attr("checked","checked");
	}
	function autenticar(){
		var form = document.frmLogin;
		var total=0;
		
		for(var i=0; i < form.selTarjeta.length; i++){
			if (form.selTarjeta[i].checked)
				total =total + 1;
		}
		
		if(total==0) {
			alert("Es necesario seleccionar una imagen como Sello de Seguridad...")
			return false;
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

		frmLogin.action="<%=request.getContextPath()%>/login.do?metodo=afiliaCambioSello";
		// Validando que sea DNI o Tarjeta
		
		frmLogin.HrTrx.value="0112";
		//alert("frmLogin.txtPassword.value="+frmLogin.txtPassword.value);
		frmLogin.submit();
	}

	function regresar(){

		frmLogin.action="<%=request.getContextPath()%>/login.do?metodo=volverCambiaCategoriaPost"; //muestraSello
		// Validando que sea DNI o Tarjeta
		
		frmLogin.HrTrx.value="0112";
		//alert("frmLogin.txtPassword.value="+frmLogin.txtPassword.value);
		frmLogin.submit();
	}

	/*
		Functions para Teclado
	*/
	function evalRanTable(valor){
		document.frmLogin.txtNumClave.value = evaluarTeclado6(document.frmLogin.txtNumClave.value,valor);
	}

	/*
		Functions para Limpiar la clave
	*/
	function cleanPass(){
		cleanPassword("txtNumClave");
	}

	function evalRanTable(valor){
		
			var longitud = parseInt("6");
			if($("#txtCoordenada").val().length < longitud){
			$("#txtCoordenada").val($("#txtCoordenada").val()+valor);
			}
	}
	
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
	 
	 });

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
	 	      
	     $("#limpiar").click(function(){
	     	$("#txtCoordenada").val("");
	     });
	 
	 });
	
	var enableLinkReenvio = true;
	function generarClaveSms(e){			
		var xmlhttp = new XMLHttpRequest();
		
		var form = document.frmLogin;
		var total=0;
		
		for(var i=0; i < form.selTarjeta.length; i++){
			if (form.selTarjeta[i].checked)
				total =total + 1;
		}
		
		if(total==0) {
			alert("Es necesario seleccionar una imagen como Sello de Seguridad...")
			return false;
		}

		
		var form = document.frmLogin;
		var total=0;
		
		for(var i=0; i < form.selTarjeta.length; i++){
			if (form.selTarjeta[i].checked)
				total =total + 1;
		}
		
		if(total==0) {
			alert("Es necesario seleccionar una imagen como Sello de Seguridad...")
			return false;
		}
		
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

	    xmlhttp.open("POST", "<%=request.getContextPath()%>/login.do?metodo=generarClaveSmsCambiaSello", true);
	    xmlhttp.send();
	    }
	}
	
	



</SCRIPT>


	<title>Banco de la Naci&oacute;n - Multired</title>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" /> 
    <meta http-equiv="Content-Language" content="es" />

<meta name="GENERATOR" content="Rational Application Developer">





<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>


<body style="background:#FFF;">


	<div id="contenidos-informativos">
			<h2>Cambio de Sello de Seguridad</h2>

     

                    <html:form name="frmLogin" type="pe.bn.login.action.form.LoginForm" action="/login.do" method="POST">
		            <INPUT type="hidden" name="transaccion" value="LG01">
					<INPUT type="hidden" name="HrTrx" value="0112">
					<INPUT type="hidden" name="txtNumeroTarjeta" value="${txtNumTarVal}">
					<input type="hidden" name="metodo">
          
            <div id="sello-seguridad">
                
                <div id="sello-seguridad-contenido" class="cambia-sello">

                    
					<!-- 
                    <p>Usted tiene que selecionar la imagen que usted configur&oacute; como Sello de Seguridad para poder ingresar a Multired Virtual. Al primer intento errado no podr&aacute; ingresar a Multired Virtual por 24 horas. Al segundo intento errado tendr&aacute; que volver a afiliarse al sello de seguridad. En caso se requiera realizar transacciones por internet deber&aacute; acercarse a la oficina m&aacute;s pr&oacute;xima para afiliarse a la clave de 6 d&iacute;gitos.</p>
					-->
					<p><c:out value='${msgSello2}' escapeXml="false" /></p>
                    <div id="sellos">
                        <ul class="limpiar">
                            <li><div onclick="chequear('1');" style="background:url(<%=request.getContextPath()%><%=request.getAttribute("listCategoria")%>Tarjeta1.png);" ><input id="selTarjeta1" name="selTarjeta" value="1"  type="radio" /></div></li>
                            <li><div onclick="chequear('2');" style="background:url(<%=request.getContextPath()%><%=request.getAttribute("listCategoria")%>Tarjeta2.png);" ><input id="selTarjeta2" name="selTarjeta" value="2"  type="radio" /></div></li>
                            <li><div onclick="chequear('3');" style="background:url(<%=request.getContextPath()%><%=request.getAttribute("listCategoria")%>Tarjeta3.png);" ><input id="selTarjeta3" name="selTarjeta" value="3"  type="radio" /></div></li>
                            <li><div onclick="chequear('4');" style="background:url(<%=request.getContextPath()%><%=request.getAttribute("listCategoria")%>Tarjeta4.png);" ><input id="selTarjeta4" name="selTarjeta" value="4"  type="radio" /></div></li>
                            <li style="margin:0;"><div onclick="chequear('5');" style="background:url(<%=request.getContextPath()%><%=request.getAttribute("listCategoria")%>Tarjeta5.png);" ><input id="selTarjeta5" name="selTarjeta" value="5"  type="radio" /></div></li>
                        </ul>
                        <ul class="limpiar">
                            <li><div onclick="chequear('6');" style="background:url(<%=request.getContextPath()%><%=request.getAttribute("listCategoria")%>Tarjeta6.png);" ><input id="selTarjeta6" name="selTarjeta" value="6"  type="radio" /></div></li>
                            <li><div onclick="chequear('7');" style="background:url(<%=request.getContextPath()%><%=request.getAttribute("listCategoria")%>Tarjeta7.png);" ><input id="selTarjeta7" name="selTarjeta" value="7"  type="radio" /></div></li>
                            <li><div onclick="chequear('8');" style="background:url(<%=request.getContextPath()%><%=request.getAttribute("listCategoria")%>Tarjeta8.png);" ><input id="selTarjeta8" name="selTarjeta" value="8"  type="radio" /></div></li>
                            <li><div onclick="chequear('9');" style="background:url(<%=request.getContextPath()%><%=request.getAttribute("listCategoria")%>Tarjeta9.png);" ><input id="selTarjeta9" name="selTarjeta" value="9"  type="radio" /></div></li>
                            <li style="margin:0;"><div onclick="chequear('10');" style="background:url(<%=request.getContextPath()%><%=request.getAttribute("listCategoria")%>Tarjeta10.png);" ><input id="selTarjeta10" name="selTarjeta" value="10"  type="radio" /></div></li>
                        </ul>
                    </div>
	
					<table>
			<c:if test="${tipoElemento.tipoElementoSeguridad=='2'}">
			<tr>				
				<td>&nbsp;</td>
				<td>
				<div class="izq_coordenada">Ingresar la Siguiente Coordenada</div>
					<input type="password" name="txtCoordenada" class="input-chico txtCoordenada"  maxlength="2" <c:if test="${resultCoord.coordConcat eq null}"> readonly="readonly" </c:if> onkeypress="return soloNumerosAll(event)"/>
					<div class="coordenada">
						<c:if test="${resultCoord.coordConcat eq null}">No disponible</c:if>
						<c:if test="${resultCoord.coordConcat ne null}"><c:out value="${resultCoord.coordConcat}"/></c:if>
					</div>
					
					<div class="clear"></div>
					
				</td>
			</tr>
			</c:if>	
						
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
				
			
			</c:if>
			
			<c:if test="${tipoElemento.tipoElementoSeguridad=='2'}">
		
			<tr>
				
				<td colspan="2"><p>Ejemplo:
					<br/>Al solicitarle la coordenada<strong>6 - F</strong>, deberás buscar la fila correspondiente al <strong>número
					6</strong> y la columna de la <strong>letra  F</strong>, en la unión de ambos, obtendrás un número, éste número deberás ingresarlo para aprobar la operación.</p></td>			
			</tr>
			</c:if>
			<c:if test="${tipoElemento.tipoElementoSeguridad=='5'}">
		
			<tr>
				<td colspan="2"><p>Nota:
					<br/>Tener en cuenta que los 6 dígitos cambian cada minuto por lo cual debe ingresar antes que la 
					barra de tiempo se haya consumido.</p>
				</td>				
			</tr>
			</c:if>
			
			<c:if test="${tipoElemento.tipoElementoSeguridad=='6'}">
		
			<tr>
				<td colspan="4"><p>Nota:
					<br/>Para confirmar la operaci&oacute;n deber&aacute;s ingresar la Clave Din&aacute;mica Digital enviada a tu dispositivo telef&oacute;nico vinculado.</p>
				</td>				
			</tr>
			</c:if>
			
					</table>


                    <div id="botones" class="limpiar">
                        <div class="regresar" onclick="regresar();">
                            <img src="imagenes/flecha-atras.jpg" alt="Atras" />REGRESAR
                        </div>
                        <div onclick="autenticar();">
                            CONTINUAR <img src="imagenes/fecha-siguente.jpg" alt="Siguiente" />
                        </div>
                    </div>

                </div>
                
            </div>
			</html:form>
						
                	<br />
                
           
			
		 <logic:messagesPresent>
			<div class="cysErrorMsg">
				<html:errors/>
			</div>
		</logic:messagesPresent> 	
		
		<div class="clear cincopx"></div>
			
</br>
<div id="dvHelpMessage" class="cysErrorMsg"  style="display: none; text-align:left !important;">
<span style="line-height:17px;font-weight:bold;color:#000000;">
		&#191;Nunca lleg&oacute; el c&oacute;digo&#63; Es probable que tengas problemas de conectividad de wifi y/o datos m&oacute;viles o has cambiado de dispositivo telef&oacute;nico. 
		Si es as&iacute;, ac&eacute;rcate a nuestras agencias para solicitar una nueva afiliaci&oacute;n.
</span>
</div>	
          

        

    </div>

    <script type="text/javascript" src="js/bn-funciones.js"></script>
    <script type="text/javascript">
      $(document).ready(function(){
		myApp.select.init();
		myApp.home.init();
	});
    </script>
    
   
</body>
</html>
