<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld"	prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld"	prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld"	prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld"				prefix="c"%>
<HTML>
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">
<META name="GENERATOR" content="IBM Software Development Platform">
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
	window.open('<%=request.getContextPath()%>/util.do?metodo=solicitarRefrendo&variableSesion=<%=pe.cosapi.common.ConstanteSesion.CONFIGURACION_TARJETA%>&idObjeto=refrendoConfTarjetaMN',"BN","toolbar=0,location=0,width=520,height=450, scrollbars=no, resizable=yes,  top=" + ((screen.height/2)-(440/2))+", left="+((screen.width/2)-(430/2)));
}

function enviar(){
	window.open('<%=request.getContextPath()%>/util.do?metodo=verMail&variableSesion=<%=pe.cosapi.common.ConstanteSesion.CONFIGURACION_TARJETA%>&idObjeto=mailConfTarjetaMN',"mail","toolbar=0,location=0,width=520,height=530, scrollbars=no, resizable=yes,  top=" + ((screen.height/2)-(600/2))+", left="+((screen.width/2)-(460/2)));
}

function verPdf(){	
		var form = document.frmReporteConfTarjeta;
      	form.action="<%=request.getContextPath()%>/util.do";
		form.metodo.value = 'verPDF';
		form.idObjeto.value = 'refrendoConfTarjetaMN';
		form.variableSesion.value = '<%=pe.cosapi.common.ConstanteSesion.CONFIGURACION_TARJETA%>';
		form.titulo.value = 'CONFIGURACION DE TARJETAS';
		document.frmReporteConfTarjeta.submit();
}	
    	
</SCRIPT>
<style type="text/css">
	.msgPiePagina{
		color:#c51416;
		font-weight:bold;
		font-size:12px; 
		font-family: Arial Narrow; 
		text-align:center;
	}
</style>
<TITLE>tran_int_ah.html</TITLE>
</HEAD>


<body  oncontextmenu="return false" onselectstart="return false" ondragstart="return false"  onKeyDown="return cancelRefresh(event)">
<form name="frmReporteConfTarjeta" method="post" >
<input type="hidden" name="idObjeto">
<input type="hidden" name="variableSesion">
<input type="hidden" name="titulo">
<input type="hidden" name="metodo">
<div id="contenidos-informativos">
	<h2>CONFIGURACI&Oacute;N DE TARJETAS</h2>
	
		<TABLE class="constancia2">
			
		<caption class="titulo-constancia">
			CONSTANCIA DE OPERACI&Oacute;N
	    </caption>
	    
				<TBODY>
					<TR >
						<c:if test="${cardSaveRequest.typeCard=='1'}">
						<TD style="width: 240px;">Tarjeta d&eacute;bito configurada:</TD>
						</c:if>
						<c:if test="${cardSaveRequest.typeCard=='2'}">
						<TD style="width: 240px;">Tarjeta cr&eacute;dito configurada:</TD>
						</c:if>
						<TD style="width: 335px;"> 
						  <c:out value="${cardSaveRequest.obfuscatedNumberCard}" />
						</TD>						
					</TR>

					<c:if test="${cardSaveRequest.typeCard=='1'}">
					<c:if test="${cardSaveRequest.transferSettings.available=='1'}">
					<TR >
						<TD style="width: 240px;"> Transferencia, giros y retiro sin tarjeta:</TD>
						<TD style="width: 335px;"> 
							<label style="margin-left:1px; font-family:Arial; font-size:12px;width: 335px;">
						  		<c:if test="${cardSaveRequest.transferSettings.status=='1'}">
									<span style="color:black; ">ACTIVADO</span>
								</c:if>
								<c:if test="${cardSaveRequest.transferSettings.status=='0'}">
									<span style="color:black; ">DESACTIVADO</span>
								</c:if> 
							</label>
						</TD>
					</TR>
					</c:if>
					</c:if>

					<c:if test="${cardSaveRequest.typeCard=='2'}">
					<c:if test="${cardSaveRequest.cashDispositionSettings.available=='1'}">
					<TR >
						<TD style="width: 240px;"> Disposicion de efectivo:</TD>
						<TD style="width: 335px;"> 
							<label style="margin-left:1px; font-family:Arial; font-size:12px;width: 335px;">
						  		<c:if test="${cardSaveRequest.cashDispositionSettings.status=='1'}">
									<span style="color:black; ">ACTIVADO</span>
								</c:if>
								<c:if test="${cardSaveRequest.cashDispositionSettings.status=='0'}">
									<span style="color:black; ">DESACTIVADO</span>
								</c:if> 
							</label>
						</TD>
					</TR>
					</c:if>
					</c:if>


					<c:if test="${cardSaveRequest.shoppingInternetSettings.available=='1'}">
						<c:if test="${cardSaveRequest.typeCard=='1'}">	
							<TR >						
								<c:if test="${cardSaveRequest.shoppingInternetSettings.status=='1'}">
									<TD style="height: 50px;line-height: 26px; width: 240px;vertical-align: baseline;">  Compras por internet:</TD>
									<TD style="height: auto;line-height: 5px; width: 335px;"> 
									    <label style="margin-left:1px; font-family:Arial;  ">      
									        <span style="color:black; ">ACTIVADO</span>     
									    </label></br>
									    

									    <c:if test="${cardSaveRequest.shoppingInternetSettings.meansNotification=='1'}">
											<label style="margin-left:1px; font-family:Arial; width:auto; color: black;">
												Email: <c:out value="${cardSaveRequest.shoppingInternetSettings.associatedEmail}" /> 					 
											</label>
										</c:if>
										<c:if test="${cardSaveRequest.shoppingInternetSettings.meansNotification=='2'}">
											<label style="margin-left:1px; font-family:Arial; width:auto; color: black;">
												Mensaje SMS: <c:out value="${cardSaveRequest.shoppingInternetSettings.desOperator}" /> 
															 <c:out value="${cardSaveRequest.shoppingInternetSettings.associatedPhone}" />
											</label><br>
										</c:if>
										<c:if test="${cardSaveRequest.shoppingInternetSettings.meansNotification=='3'}">
											<label style="margin-left:1px; font-family:Arial; width:auto; color: black;">
												Email: <c:out value="${cardSaveRequest.shoppingInternetSettings.associatedEmail}" /> 											   
											</label><br>
											<label style="margin-left:1px; font-family:Arial; width:auto;color: black;">
												Mensaje SMS: <c:out value="${cardSaveRequest.shoppingInternetSettings.desOperator}" /> -
															 <c:out value="${cardSaveRequest.shoppingInternetSettings.associatedPhone}" />
											</label><br>
										</c:if>  
									</TD>
								</c:if>

								<c:if test="${cardSaveRequest.shoppingInternetSettings.status=='0'}">
									<TD style="height: 27px;line-height: 25px; width: 240px;">  Compras por internet:</TD>
									<TD style="height: 27px;width: 335px;"> 
									    <label style="margin-left:1px; font-family:Arial;  ">
									        <span style="color:black; ">DESACTIVADO</span>
									    </label>
									</TD>
								</c:if>
							</TR>
						</c:if>

						<c:if test="${cardSaveRequest.typeCard=='2'}">
							<c:if test="${cardSaveRequest.shoppingInternetSettings.status=='1'}">
									<TD style="line-height: 26px; width: 240px;vertical-align: baseline;">  Compras por internet:</TD>
									<TD style="line-height: 5px; width: 335px;"> 
									    <label style="margin-left:1px; font-family:Arial;  ">      
									        <span style="color:black; ">ACTIVADO</span>     
									    </label>
									</TD>
							</c:if>
							<c:if test="${cardSaveRequest.shoppingInternetSettings.status=='0'}">
									<TD style="line-height: 25px; width: 240px;">  Compras por internet:</TD>
									<TD style="width: 335px;"> 
									    <label style="margin-left:1px; font-family:Arial;  ">
									        <span style="color:black; ">DESACTIVADO</span>
									    </label>
									</TD>
							</c:if>
						</c:if>
					</c:if>

					<c:if test="${cardSaveRequest.shoppingAbroadSettings.available=='1'}">
						<TR >						
							<c:if test="${cardSaveRequest.shoppingAbroadSettings.status=='1'}">
								<TD style="height: 70px;line-height: 26px; width: 240px; vertical-align: baseline;">  Consumo en el extranjero:</TD>
								<TD style="height: auto;line-height: 5px;width: 335px;">  
									<label style="margin-left:1px; font-family:Arial; ">
										<span style="color:black; ">ACTIVADO</span>								
									</label></br>							
									<label style="margin-left:1px; font-family:Arial; color: #000000;width: 100%;">
										Ida:  <c:out value="${cardSaveRequest.shoppingAbroadSettings.dateDeparture}" /> - Vuelta:  <c:out value="${cardSaveRequest.shoppingAbroadSettings.dateReturn}" />
									</label></br>
									<label style="margin-left:1px; font-family:Arial; color: #000000;width: 100%;">
										En Paises: <c:out value="${cardSaveRequest.shoppingAbroadSettings.countriesStr}" />
									</label>
								</TD>
							</c:if>

							<c:if test="${cardSaveRequest.shoppingAbroadSettings.status=='0'}">
								<TD style="height: 27px;line-height: 25px; width: 240px;"> Consumo en el extranjero:</TD>
								<TD style="height: 27px;width: 335px;"> 
								    <label style="margin-left:1px; font-family:Arial;  ">
								        <span style="color:black; ">DESACTIVADO</span>
								    </label>
								</TD>
							</c:if>
							
						</TR>
					</c:if>

					<c:if test="${cardSaveRequest.notificationSettings.available=='1'}">
						<TR >
							<c:if test="${cardSaveRequest.notificationSettings.status=='1'}">
								<TD style="height: 70px;line-height: 26px; width: 240px;vertical-align: baseline;">   
									Notificaci&oacute;n por operaci&oacute;n:
								</TD>
								<TD style="height: auto;line-height: 5px;width: 335px;">  
									<label style="margin-left:1px; font-family:Arial; ">									
											<span style="color:black; ">ACTIVADO</span>																
									</label></br>								
									<label style="margin-left:1px; font-family:Arial; color: #000000;">
										A partir de:  
										<c:if test="${cardSaveRequest.notificationSettings.typeMoney=='S'}">
											<span style="color:black; vertical-align:baseline">S/</span>
										</c:if>
										<c:if test="${cardSaveRequest.notificationSettings.typeMoney=='D'}">
											<span style="color:black; vertical-align:baseline">USD</span>
										</c:if> 
   										<c:out value="${cardSaveRequest.notificationSettings.amount}" />
									</label></br>

									<c:if test="${cardSaveRequest.notificationSettings.meansNotification=='1'}">
										<label style="margin-left:1px; font-family:Arial; width:auto; color: black;">
											Email: <c:out value="${cardSaveRequest.notificationSettings.associatedEmail}" />				 
										</label><br>
									</c:if>
									<c:if test="${cardSaveRequest.notificationSettings.meansNotification=='2'}">
										<label style="margin-left:1px; font-family:Arial;width:auto; color: black;">
											Mensaje SMS: <c:out value="${cardSaveRequest.notificationSettings.desOperator}" /> -
														 <c:out value="${cardSaveRequest.notificationSettings.associatedPhone}" />
										</label><br>
									</c:if>
									<c:if test="${cardSaveRequest.notificationSettings.meansNotification=='3'}">
										<label style="margin-left:1px; font-family:Arial;  width:auto; color: black;">
											Email: <c:out value="${cardSaveRequest.notificationSettings.associatedEmail}" /> 											   
										</label><br>
										<label style="margin-left:1px; font-family:Arial; width:auto; color: black;">
											Mensaje SMS: <c:out value="${cardSaveRequest.notificationSettings.desOperator}" /> -
														 <c:out value="${cardSaveRequest.notificationSettings.associatedPhone}" />
										</label><br>
									</c:if>
									
								</TD>
							</c:if>						

							<c:if test="${cardSaveRequest.notificationSettings.status=='0'}">
								<TD style="height: 27px;line-height: 25px; width: 240px;"> Notificaci&oacute;n por operaci&oacute;n:</TD>
								<TD style="height: 27px;width: 335px;"> 
								    <label style="margin-left:1px; font-family:Arial;  ">
								        <span style="color:black; ">DESACTIVADO</span>
								    </label>
								</TD>
							</c:if>
						</TR>
					</c:if>
					<TR >
						<TD style="width: 240px;"> Fecha de operaci&oacute;n:</TD>
						<TD style="height: 30px;width: 335px;"> 
							<c:out value="${dateOperation}" />   				
						</TD>
					</TR>
					
					</TBODY>
				</table>

		<div id="botones" class="limpiar" style="margin-top:50px;">
			<a href="javascript:imprimir();" id="imprimir" style="margin-left:125px;">IMPRIMIR</a>
			<a href="javascript:enviar();" id="enviar">ENVIAR</a>
			<a href="javascript:verPdf();" id="descargar">DESCARGAR</a>
     	</div>	 
	</div>
				
</form>
</BODY>

</HTML>