<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/c.tld"				prefix="c"%>

<html xmlns="http://www.w3.org/1999/xhtml" lang="es" xml:lang="es"> 
<head>
	<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<meta name="GENERATOR" content="IBM Software Development Platform"/>
<meta content="no-cache" http-equiv="pragma"/>
<meta content="no-cache" http-equiv="cache-control"/>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/js/control.js"></script>
<script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/js/util.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/resetearcss.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/tipografias.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bienvenido.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/select.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bn-estilos.css" />    
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/slider2.css" />    
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/slider.css" />    
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap-chosen.css" /> 
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/jquery.datetimepicker.css" />

<script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/select.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap-chosen.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.datetimepicker.full.js"></script>
    
<script type="text/javascript" src="<%=request.getContextPath()%>/herramientas/calendario/calendar.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/herramientas/calendario/lang/calendar-es.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/herramientas/calendario/calendar-setup.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/herramientas/calendario/calendar-tas.css"/>            
    
<script language="javascript" type="text/javascript">

function obtenerDechaActual(){
var d = new Date();
var anio = d.getFullYear()+""; 
var mes = (d.getMonth()+1)+"";
var dia = d.getDate()+"";



if(mes.length=1){
	mes="0"+mes;
}
if(dia.length=1){
	dia="0"+dia;
}

var strDate = anio + "-" + mes + "-" + dia

return strDate;
}

function myIsNaN(o) {
return typeof(o) === 'number' && isNaN(o);
}

function validarInformacion(){
	
	var form = document.frmConsultaDetalle;	
	var chkComprasInternet = document.getElementById('chkComprasInternet').checked;
	var chkConsumoExtranjero = document.getElementById('chkConsumoExtranjero').checked;
	var chkNotificacionOperacion = document.getElementById('chkNotificacionOperacion').checked;

	var montoNotificacion = parseInt(form.montoNotificacion.value.trim());
	form.hddChkNotiSms.value=$('#chkNotiSms').prop('checked');
	form.montoNoti.value=montoNotificacion;
	var fechaActual = obtenerDechaActual();



	if(chkConsumoExtranjero == true){

	var fechaIda = $('#fechaIda').val();
	var diaIda = fechaIda.substring(8,10);
	var mesIda = fechaIda.substring(5,7);
	var anoIda = fechaIda.substring(0,4);
	fechaIda =  mesIda+ "/" +diaIda+ "/" +anoIda;
		
	if(fechaIda == "//" || fechaIda == "" || fechaIda == null){
	alert('Debe Ingresar una fecha de Ida');
			return false;
	}
	

	var fechaLlegada = $('#fechaVuelta').val();
	var diaLlegada = fechaLlegada.substring(8,10);
	var mesLlegada = fechaLlegada.substring(5,7);
	var anoLlegada = fechaLlegada.substring(0,4);
	fechaLlegada =  mesLlegada+ "/" +diaLlegada+ "/" +anoLlegada;
	if(fechaLlegada == "//" || fechaLlegada == "" || fechaLlegada == null){
	alert('Debe Ingresar una fecha de Vuelta');
			return false;
	}

	var diaAct = new Date();
	var anio = diaAct.getFullYear()+""; 
	var mes = (diaAct.getMonth()+1)+"";
	var dia = diaAct.getDate()+"";
	fechaActual =  mes+ "/" +dia+ "/" +anio;


	var d0 = new Date(fechaActual);
	var d1 = new Date(fechaIda);
	var d2 = new Date(fechaLlegada);

	if(chkConsumoExtranjero == true){
		if( ($('#cmbPaises').val() == null) ){		
			alert('Seleccione al menos un pais.');
			return false;
		}

		if( d1=='' || d2=='' ) {
			alert('Seleccione las fechas de ida y vuelta.');
			return false;
		}

		if(d1!='' || d2!='' ){
			if(d1>d2) {
				alert('La fecha de ida no puede ser mayor a la fecha de vuelta');
				return false;	
			}

			if(d1<d0) {
				alert('La fecha de ida no puede ser menor que la fecha Actual');
				return false;	
			}

			if(d2<d0) {
				alert('La fecha de vuelta no puede ser menor que la fecha Actual');
				return false;	
			}
		}
	} 
	}

	if(chkNotificacionOperacion == true) {
		if (myIsNaN(montoNotificacion) || montoNotificacion < 0) {
			alert('Ingrese el monto a notificar.');
			return false;
		} 

		if ($('#chkNotiEmail').prop('checked') == false && $('#chkNotiSms').prop('checked') == false) {
			alert('Seleccione al menos un medio para confirmar notificaciones.');
			return false;
		}
	}

	return true;
}

function regresar(){

	var form = document.frmConsultaDetalle;
	form.metodo.value = 'iniciar';
	form.action = "<%=request.getContextPath()%>/configuracionTarjetas.do";
	form.submit();
}

function continuar(){

	if(validarInformacion() == true ){
		var form = document.frmConsultaDetalle;
		form.metodo.value ='confirmarConfiguracionTarjeta';
		form.paisesSel.value = convertirArrayPaisesToString();
		form.action="<%=request.getContextPath()%>/configuracionTarjetas.do";
		form.submit();		
	}

}

function convertirArrayPaisesToString(){
	var paisesSeleccionado = $('#cmbPaises').val();
	var paises = "";
	if(paisesSeleccionado!=null){
		paisesSeleccionado.forEach(function(pais, index) {
	    	paises += pais + ",";
		});
	}
	return paises;
}


function precargarComboPaises(){
	
	console.log('<c:out value="${paises}" ></c:out>');

	var paises = '<c:out value="${paises}" ></c:out>';
	var str_array = paises.split(",");
	
	console.log(str_array);	
	
	$("#cmbPaises").val(null);			
	$("#cmbPaises").val(str_array);
	$("#cmbPaises").trigger('chosen:updated');
}


function cambiarEtiquetaActivado(ctrl1, ctrl2, ctrl3){
	
	var check = document.getElementById(ctrl1).checked;
	var acordeon = document.getElementById(ctrl3);

	document.getElementById(ctrl2).innerHTML = "ACTIVADO";
	document.getElementById(ctrl2).style.color = "#ba1113";
	if(acordeon!=null){		
		$("#"+ctrl3).find("*").prop("disabled", false);

		if(ctrl3 == 'consumoExtranjero'){
			$('#cmbPaises').prop('disabled', false).trigger("chosen:updated");
		}
	}

	if(check==false){
		document.getElementById(ctrl2).innerHTML = "DESACTIVADO";
		document.getElementById(ctrl2).style.color = "darkslategray";
		if(acordeon!=null){
			$("#"+ctrl3).find("*").prop("disabled", true);

			if(ctrl3 == 'consumoExtranjero'){
				$('#cmbPaises').prop('disabled', true).trigger("chosen:updated");
			}
		}	
		
	}
	

}

$(document).ready(
	 	function(){ 

	 		$('[type="checkbox"]').change(function(){
  
			    if(this.checked && this.name =='chkNotiEmail'){
			       $('[name="chkNotiEmail"]').not(this).prop('checked', false);
			    }    
	 	    });
	 		
	 		//mostrarComprasInternet();
	 		//mostrarConsumoExtranjero();
	 		//mostrarNotificacionOperacion();

	 		precargarComboPaises();

			

	 		if('<c:out value="${cardDetalleResponse.cashDispositionSettings.available}"/>' == '1'){
	 		cambiarEtiquetaActivado('chkDisposicion', 'spnDisposicion', '');
	 		}

	 		if('<c:out value="${cardDetalleResponse.shoppingInternetSettings.available}"/>' == '1'){
	 		cambiarEtiquetaActivado('chkComprasInternet','spnActCompras','');
	 		}

	 		if('<c:out value="${cardDetalleResponse.shoppingAbroadSettings.available}"/>' == '1'){
	 		cambiarEtiquetaActivado('chkConsumoExtranjero','spnActConsumoExt','consumoExtranjero');
	 		}

	 		if('<c:out value="${cardDetalleResponse.notificationSettings.available}"/>' == '1'){
	 		cambiarEtiquetaActivado('chkNotificacionOperacion','spnActNotificacion','notificacionOperacion');
	 		}

	 		
	 		$('.chosen-select').chosen({
    			disable_search_threshold: 10,
    			no_results_text: 'El pais consultado no se encuentra.',
    			width: '320px',		
    			search_contains: true,
    			max_selected_options: 5
  			});
  			
  			
  			
});


</script>
<style>
tr td{
	vertical-align:middle !important;
}
</style>
</head>
<body oncontextmenu="return false" onselectstart="return false" ondragstart="return false"  onKeyDown="return cancelRefresh(event)" >
							   		       
	<form name="frmConsultaDetalle" method="post" autocomplete="off">
		<input type="hidden" name="metodo"/>
		<input type="hidden" name="paisesSel"/>
		<input type="hidden" name="montoNoti"/>
		<input type="hidden" name="hidCuenta"/>
		<input type="hidden" name="transaccion"/>
		<input type="hidden" name="hidConsulta"/>
		<input type="hidden" name="hidMoneda"/>
		<input type="hidden" name="HrTrx"/>
		<input type="hidden" name="hidNroPrestamo"/>
		<input type="hidden" name="hddChkNotiSms"/>
		<br/>
		
		<div id="contenidos-informativos">
			<h2>CONFIGURACI&Oacute;N DE TARJETA</h2>
			<div id="cuentas">
				
				<!--<table width="100%" border="0" align="center">
				<tbody>
				<tr align="left" valign="top">
					<td width="100%">-->
						
						<table class="table-cuentas" width="100%" border="0" cellpadding="0" cellspacing="1">
						<tbody>
						<tr>
							<td colspan="5" align="center" class="texto" height="20">&nbsp;</td>
						</tr>
						
						
						
						<tr>
							<td colspan="4" align="center" class="tituloTabla"><c:out value="${tarjetaSeleccionada.cardName}"/></td>
						</tr>	
						<tr>
							<td width="25%" colspan="1" align="center" class="tituloCelda"><strong>TARJETA</strong></td>
							<td width="60%" colspan="2" align="center" class="tituloCelda"><strong>TITULAR / ENTIDAD</strong></td>
							<td width="15%" colspan="2" align="center" class="tituloCelda"><strong>TIPO</strong></td>
						</tr>
						

						
						<tr>
							<td colspan="1" class="detalleCelda"><c:out value="${tarjetaSeleccionada.numCard}"/></td>
							<td class="detalleCelda" colspan="2">
							&nbsp;<c:out value="${tarjetaSeleccionada.associatedEntity}"/>
							</td>
							<c:if test="${tarjetaSeleccionada.provider=='1'}">
							<td class="detalleCelda" colspan="2">
								<img border="0" src="<%=request.getContextPath()%>/imagenes/bn/visa.png" />
							</td>
							</c:if>
							
							<c:if test="${tarjetaSeleccionada.provider=='2'}">
							<td class="detalleCelda" colspan="2">
								<img border="0" src="<%=request.getContextPath()%>/imagenes/bn/mastercard.png" />
							</td>
							</c:if>							

							
						</tr>
						
						
						
						<tr>
							<td height="25"><br/></td>
							<td height="30"></td>
							<td height="20"></td>
							<td height="25" align="right"></td>
						</tr>
						
						</tbody>
						</table>
						
						<table class="table-cuentas" width="100%" border="0" cellpadding="0" cellspacing="1" style="border-spacing: 0px;border: 1px solid #c5c1c1">
						<tbody>
						<tr>
							<td colspan="4" align="center" class="tituloTabla">ACTIVAR O DESACTIVAR</td>
						</tr>
						
						
						<c:if test="${cardDetalleResponse.cashDispositionSettings.available=='1'}">
						<tr>
							<td style="width: 62%;font: 12px 'Arial';" colspan="1" class="detalleCelda">DISPOSICI&Oacute;N DE EFECTIVO</td>
							<td class="detalleCelda" align="center" colspan="2" style="font: 12px 'Arial';">
								<span style="color:#d40909; font-weight:bold" id="spnDisposicion">ACTIVADO</span>
							</td>							

							<td class="detalleCelda" align="right" colspan="" style="padding-left: 77px;">
								<label class="switch" style="width: 59px;">
								  <input onclick="javascript:cambiarEtiquetaActivado('chkDisposicion', 'spnDisposicion','')" type="checkbox" name="chkDisposicion" id="chkDisposicion" tabindex="0" 
								  		<c:if test="${cardDetalleResponse.cashDispositionSettings.status=='1'}">
											checked
										</c:if>
										<c:if test="${cardDetalleResponse.cashDispositionSettings.status=='0'}">
											unchecked
										</c:if> 
    							  />
								  <span class="slider round" style="width:57px; height: 33px"></span>
								</label>
							</td>
						</tr>
						</c:if>

						<c:if test="${cardDetalleResponse.shoppingInternetSettings.available=='1'}">
						<tr>
							<td style="width: 62%;font: 12px 'Arial';" colspan="1" class="detalleCelda">COMPRAS POR INTERNET</td>
							<td style="width: 30%;" class="detalleCelda" align="center" colspan="2" style="font: 12px 'Arial';">						
								<span style="color:#d40909; font-weight:bold" id="spnActCompras" >
									<c:out value="${cardDetalleResponse.shoppingInternetSettings.lblActivado}" />
								</span>
							</td>
							<td class="detalleCelda" align="right" colspan="" style="padding-left: 77px;">
								<label class="switch" style="width: 59px;">
								  <input onclick="javascript:cambiarEtiquetaActivado('chkComprasInternet','spnActCompras','comprasInternet')" type="checkbox" name="chkComprasInternet" id="chkComprasInternet" tabindex="0" 
								  <c:if test="${cardDetalleResponse.shoppingInternetSettings.status=='1'}">
											checked
										</c:if>
										<c:if test="${cardDetalleResponse.shoppingInternetSettings.status=='0'}">
											unchecked
										</c:if> 
    							  />
								  <span class="slider round" style="width:57px; height: 33px"></span>
								</label>
							</td>
						</tr>
						</c:if>
						

						<c:if test="${cardDetalleResponse.shoppingAbroadSettings.available=='1'}">
						<tr>
							<td style="width: 62%;font: 12px 'Arial';" colspan="1" class="detalleCelda">CONSUMO EN EL EXTRANJERO</td>
							<td style="width: 30%;" class="detalleCelda" align="center" colspan="2" style="font: 12px 'Arial';">
								<span style="color:#d40909; font-weight:bold" id="spnActConsumoExt" >
									<c:out value="${cardDetalleResponse.shoppingAbroadSettings.lblActivado}" />
								</span>
							</td>							

							<td class="detalleCelda" align="right" colspan="" style="padding-left: 77px;">
								<label class="switch" style="width: 59px;">
								  <input onclick="javascript:cambiarEtiquetaActivado('chkConsumoExtranjero','spnActConsumoExt','consumoExtranjero')" type="checkbox" name="chkConsumoExtranjero" id="chkConsumoExtranjero" tabindex="0" 
								  <c:if test="${cardDetalleResponse.shoppingAbroadSettings.status=='1'}">
											checked
										</c:if>
										<c:if test="${cardDetalleResponse.shoppingAbroadSettings.status=='0'}">
											unchecked
										</c:if> 
    							  />
								  <span class="slider round" style="width:57px; height: 33px"></span>
								</label>
							</td>
						</tr>
						
						<tr id="consumoExtranjero" style="display:">
							<td style="font: 12px 'Arial';padding-top: 10px;padding-left:79px;padding-right: 20px;padding-bottom: 10px;" colspan="4">
						      <table >
						        
						         	<tr>
						               <td colspan="1" >Consumo en el extranjero (Puedes registrar hasta 5 paises) </td>
						               <td style="padding-bottom: 10px;">
						                  <select id="cmbPaises" name="cmbPaises" data-placeholder="Seleccionar paises"  class="chosen-select" multiple="true" width="198px"  >
												<logic:iterate id="pais" name="listaPaisesConfTarjetas" >
													<OPTION value='<bean:write name="pais" property="code"/>'> 
													<bean:write name="pais" property="description" />
										    		</OPTION>
										  		</logic:iterate>
							   		       </select>
						               </td>
						            </tr>
						            
						        	<tr>
						               <td colspan="1">Fecha de ida</td>
						               <td colspan="1" style="padding-bottom: 10px;">
						                  <input type="date" name="fechaIda" id="fechaIda" maxlength="10" value=<c:out value="${cardDetalleResponse.shoppingAbroadSettings.dateDeparture}" />  />
						                  			               
						               </td>
					               	</tr>
					
					               	<tr>
						               <td colspan="1">Fecha de vuelta</td>
						               <td colspan="1" >
						                  <input type="date" name="fechaVuelta" id="fechaVuelta" maxlength="10" value=<c:out value="${cardDetalleResponse.shoppingAbroadSettings.dateReturn}" /> />
						               	</td>
					               	</tr>
						         
						      </table>
						   	</td>
						</tr>
						
						</c:if>




						<c:if test="${cardDetalleResponse.notificationSettings.available=='1'}">
						<tr>
							<td style="font: 12px 'Arial';;width: 62%;" colspan="1" class="detalleCelda">NOTIFICACI&Oacute;N POR OPERACI&Oacute;N</td>
							<td style="font: 12px 'Arial';;width: 30%;" class="detalleCelda" align="center" colspan="2">								
								<span style="color:#d40909; font-weight:bold" id="spnActNotificacion" >
									<c:out value="${cardDetalleResponse.notificationSettings.lblActivado}" />
								</span>
							</td>							

							<td class="detalleCelda" align="right" colspan="" style="padding-left: 77px;">
								<label class="switch" style="width: 59px;">
								  <input onclick="javascript:cambiarEtiquetaActivado('chkNotificacionOperacion','spnActNotificacion','notificacionOperacion')" type="checkbox" name="chkNotificacionOperacion" id="chkNotificacionOperacion" tabindex="0" 
								  		<c:if test="${cardDetalleResponse.notificationSettings.status=='1'}">
											checked
										</c:if>
										<c:if test="${cardDetalleResponse.notificationSettings.status=='0'}">
											unchecked
										</c:if> 
    							  />
								  <span class="slider round" style="width:57px; height: 33px"></span>
								</label>
							</td>
						</tr>
						
						<tr id="notificacionOperacion" style="display:">
							<td style="color: #000000;height: 18px;font: 12px 'Arial'; padding-left: 0px" colspan="4" align="center">
						      <table style="width:79%">
						         <tbody>
						         	<tr>
						               <td colspan="1" style="width:50%;">
										&nbsp;
						               </td>
	   			                    </tr>
						         	<tr>
						               <td colspan="1">
						               	Notificar a partir de ( S/ ):  
						               </td>
						               <td colspan="2" style="width:50%; padding-left: 0px;">
						               	
						               	<input type="text" name="montoNotificacion" id="montoNotificacion" 
						               		class="input-grande txtCoordenada"  maxlength="5" onkeypress="return filterFloat(event,this);"
						               		value=<c:out value="${cardDetalleResponse.notificationSettings.amount}"/>
						               		/>
						               </td>
	   			                    </tr>
	   			                    <tr>
						               <td colspan="1" style="width:50%;">
										&nbsp;
						               </td>
	   			                    </tr>
						            <tr>
						               <td rowspan="2" style="
						                  padding-right:52px;
						                  ">Selecciona un medio de notificaci&oacute;n: </td>
						               <td style="padding-left:11px;padding-top: 7px;padding-bottom: 7px;">
						                  <table style="border: 1px solid #c5c5c5; width:249px;padding-top: 5px;padding-bottom: 5px;padding-left: 4px;line-height: 17px;">
						                     <tbody>
						                        <tr>
						                           <td style="width: 140px;">Email</td>
						                           <td rowspan="2" style="width: 30px">						                           	
						                           	<input type="checkbox" id="chkNotiEmail" name="chkNotiEmail" 
						                           		<c:if test="${cardDetalleResponse.notificationSettings.meansNotification=='1'}">checked</c:if>
					                              		<c:if test="${cardDetalleResponse.notificationSettings.meansNotification=='3'}">checked</c:if>
						                           	/>
						                           </td>
						                        </tr>
						                        <tr>
						                           <td style="width: 200px; font-weight: bold"><c:out value="${datosPersona.correoPersonal}"/></td>
						                        </tr>
						                     </tbody>
						                  </table>
						               </td>
						            </tr>
						            <tr>
						               <td style="padding-left:11px;padding-top: 7px;padding-bottom: 14px;">
						                  <table style="border: 1px solid #c5c5c5; width:249px;padding-top: 5px;padding-bottom: 5px;padding-left: 4px;line-height: 17px;">
						                     <tbody>
						                        <tr>
						                           <td style="width: 140px;">Mensaje SMS</td>
						                           <td rowspan="2" style="width:30px">
						                           		<input type="checkbox" id="chkNotiSms" name="chkNotiEmail" 
						                           		<c:if test="${cardDetalleResponse.notificationSettings.meansNotification=='2'}">checked</c:if>
					                              		<c:if test="${cardDetalleResponse.notificationSettings.meansNotification=='3'}">checked</c:if>
						                           		/>
						                           </td>
						                        </tr>
						                        <tr>
						                           <td style="width:200px; font-weight: bold"><b><c:out value="${tipoElemento.numberMobile}"/></b></td>
						                        </tr>
						                     </tbody>
						                  </table>
						               </td>
						            </tr>
						         </tbody>
						      </table>
						   </td>
						</tr>
						
						</c:if>
						
						
						
						
						
						</tbody>
						</table>
						

						<table class="table-cuentas" width="100%" border="0" cellpadding="0" cellspacing="1">
						<tbody>
						<tr>
						<td>


						</td>
						</tr>
						
						
						
						<tr>
							<td height="20"><br/></td>
							<td height="20"></td>
							<td height="20"></td>
							<td height="20"></td>
							<td height="20" align="right"></td>
						</tr>
						
						
						
						<tr>
							<td colspan="5"><hr/></td>
						</tr>
						
						
					
						</tbody>
						
						</table>
						<br/>
					<!--</td>	
				</tr>
				</tbody>
				</table>-->
				
	
				<script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-funciones.js"></script>

			</div>

			<logic:messagesPresent>
			<div class="cysErrorMsg" id="cysErrorMsg" style="text-align:left !important;">
				<html:errors/>
			</div>
			</logic:messagesPresent>
			
			<div id="botones" class="boton" >		
				<input type="button" onclick="javascript:regresar();" id="boton-ret" value="REGRESAR"/>
				<input type="button" value="CONTINUAR" id="boton" onclick="javascript:continuar();"/>		
			</div>	
	
		</div>
		
		
		


		
		
	</form>
	
</body>
</html>


