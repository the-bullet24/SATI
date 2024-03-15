<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
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
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bienvenido.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/select.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bn-estilos.css" />    

<script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/select.js"></script>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/select.js"></script>
    


<SCRIPT language="javascript">
	function continuar(){
		var form = document.frmPagoFacturas;

		if(form.cboCategoria.value=="00"){
			alert("Seleccione una Categoria...");
			return;
		}

//		form.action="<%=request.getContextPath()%>/pagoFacturasWS.do?metodo=verPagoFacturas";
		form.action="<%=request.getContextPath()%>/login.do?metodo=cambiaCategoria";

		form.submit();

		document.frmPagoFacturas.imgContinuar.removeAttribute("onclick");
		document.frmPagoFacturas.imgContinuar.setAttribute("onclick", "");
	}

	function desafiliar(){
		var form = document.frmPagoFacturas;
		<logic:equal name="hidServicio" value="11">
			document.forms[0].action ="<%=request.getContextPath()%>/desafFacturas.do?metodo=iniciar";
		</logic:equal>
		document.forms[0].hidServicio.value='<%=(java.lang.String)request.getSession().getAttribute("hidServicio")%>';
		document.forms[0].submit();
	}

	function validaNroDocumento(e){
		return soloAlfanumerico(e);
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

     

                    <html:form name="frmPagoFacturas" type="pe.bn.login.action.form.LoginForm" action="/login.do" method="POST" style="padding:0px;">
					<input type="hidden" name="hidServicio">
					<input type="hidden" name="metodo">
					<input type="hidden" name="transaccion" value="PF02">
					<input type="hidden" name="tipoFacturas" value="0156">		
							
					<p><c:out value='${msgSello1}' escapeXml="false" /></p>
					
                        
                       						
                        
                        <div class="" style="margin:0 auto 40px !important; width:250px;">
                            
                            <select class="tipo-documento select select-grande" id="cboCategoria" name="cboCategoria" style="margin:0 auto;">
									<OPTION value='00'selected>Seleccione....&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</OPTION>
									<option value='01'>Artefactos</option>
									<option value='02'>Educación</option>
									<option value='03'>Entretenimiento</option>
									<option value='04'>Finanzas</option>
									<option value='05'>Seguridad</option>
									<option value='06'>Varios</option> 
								</select>

                        </div>
						<div class="boton">
                        <input name="imgContinuar" type="button" value="CONTINUAR" onclick="javascript:return continuar();"  />
						</div>
                    </html:form>
						
                	<br />
                
           
			
		 <logic:messagesPresent>
			<div class="cysErrorMsg">
				<html:errors/>
			</div>
		</logic:messagesPresent> 	
          

        

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
