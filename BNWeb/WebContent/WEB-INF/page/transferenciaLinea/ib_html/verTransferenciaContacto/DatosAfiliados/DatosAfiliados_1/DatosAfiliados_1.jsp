<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
<%@ taglib uri="/WEB-INF/struts-html.tld"	prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld"	prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld"	prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld"				prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"/>
<meta name="GENERATOR" content="IBM Software Development Platform"/>
<meta content="no-cache" http-equiv="pragma"/>
<meta content="no-cache" http-equiv="cache-control"/>


    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"/>
    <title>Document</title>

    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/verTransferenciaContacto/AfiliacionDeCelular/AfiliacionCelular_2/AfiliacionCelular_2.css">



<script language="JavaScript" src="<%=request.getContextPath()%>/js/util.js"></script>






<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/resetearcss.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/tipografias.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bn-estilos.css" />  
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/select.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/select.js"></script>
<title>tran_int_ah.html</title>
<script language="javascript">

	function continuar(){
	
		var form = document.frmTransferenciaIB;
		document.frmTransferenciaIB.boton.disabled=true;
		
		/*
		var CelularAfi = <c:out value="${CelularAfi}"></c:out>;
		console.log("CelularAfi:::"+CelularAfi);
		
		var CelularTrans = <c:out value="${CelularTrans}"></c:out>;
		console.log("CelularTrans:::"+CelularTrans);			
			
		var destinoCombo = form.cboEntidadDestino.value;
		console.log("destinoCombo:::"+destinoCombo);		
		
		var codEntidad = destinoCombo.split("-", 1);
		console.log("codEntidad:::"+codEntidad);
		
		if (codEntidad == "0018") {
			if(CelularAfi==CelularTrans){
			alert("El nmero de celular destino no pueden ser el mismo al nmero de celular afiliado.");
			document.frmTransferenciaIB.boton.disabled=false;							
			return;
			}
		}
		*/
		
		
		
		var valorLimiteYP = <c:out value="${valorLimiteYP}"></c:out>;
		var valorMinimoYP = <c:out value="${valorMinimoYP}"></c:out>;
		
		console.log("valorLimiteYP:::"+valorLimiteYP);
		console.log("valorMinimoYP:::"+valorMinimoYP);
		
		//console.log("form.cboEntidadDestino.value:::"+form.cboEntidadDestino.value);
		
		
		if(form.cboEntidadDestino.value==""){
			alert("Seleccione una entidad destino");
			document.frmTransferenciaIB.boton.disabled=false;							
			return;
		}
			
		
		if(form.txtMonto.value == ''||isNaN(form.txtMonto.value)){
			alert('Ingrese un monto de transferencia vlido');	
			document.frmTransferenciaIB.boton.disabled=false;		
			return;			
		}
		
		let mon = parseFloat(form.txtMonto.value);
		let max = parseFloat(valorLimiteYP);
		let min = parseFloat(valorMinimoYP);
		
		/* mon = mon.toFixed(2);	
	    max = max.toFixed(2);
	    min = min.toFixed(2); */
		
		if(mon < min ){
			alert('Ingrese un monto de transferencia vlido');	
			document.frmTransferenciaIB.boton.disabled=false;		
			return;			
		}	
		
	 	var destinoCombo = form.cboEntidadDestino.value;
		console.log("destinoCombo:::"+destinoCombo);		 
		
		//var codEntidad = destinoCombo.split("-", 1);
		
		//if (codEntidad == "0901" || codEntidad == "0902" ) {
		
		
					
	            
		
		if(mon > max){
			alert('Puedes enviar hasta S/ 500.00 cada vez por esta entidad destino.');	
			document.frmTransferenciaIB.boton.disabled=false;		
			return;			
		}	
		
		//}	
		
		if(checkDecimals(form.txtMonto, form.txtMonto.value)){
			if(form.chkAceptar.checked == false){
				alert('Tiene que Aceptar las Condiciones Generales');
				document.frmTransferenciaIB.boton.disabled=false;				
				return;
			}
				
			form.action="<%=request.getContextPath()%>/transferenciaInterbancariaLinea.do?metodo=continuarTransferenciaContacto";
			form.submit();	
			
		}
						
		
	}
	
	function regresar(){
		var form = document.frmTransferenciaIB;
		form.action="<%=request.getContextPath()%>/transferenciaInterbancariaLinea.do";
		form.submit();
	}
	
	function checkDecimals(fieldName, fieldValue) {
		decallowed = 2;  // how many decimals are allowed?
		if (fieldValue.indexOf('.') == -1) fieldValue += ".";
		dectext = fieldValue.substring(fieldValue.indexOf('.')+1, fieldValue.length);
		
		if (dectext.length > decallowed)
		{
			alert ("Debe introducir un nmero con " + decallowed + " decimales.");
			fieldName.select();
			document.frmTransferenciaIB.boton.disabled=false;
			return false;
		} else {
			return true;
		}
	}
</script>
</head>
<body onselectstart="return false" 
ondragstart="return false" 
oncontextmenu="return false"  
onkeydown="return cancelRefresh(event);">

 <div id="maindiv">
    <h1 style="font-family: 'daxcompact-mediumregular'; font-size: 20px;margin-bottom: 19px;">Confirma el cambio de tu n&uacute;mero celular</h1>
        
        <div class="container">
            <p>Para continuar con el proceso de afiliaci&oacute;n te hemos enviado un c&oacute;digo de verificaci&oacute;n SMS al nuevo n&uacute;mero celular.</p>

            <div class="CelularContainer">
                 <h2>947 058 652</h2>
            </div>
        </div>

    <form id="CodeValidation" name="CodeValidation">
        <label style="font-family: 'daxcompact-mediumregular';  for="code">C&oacute;digo de verificaci&oacute;n</label>
        <div class="codeContainer">
            <input type="password" name="code" id="code" required maxlength="6">
            <a href="">Reenviar c&oacute;digo</a>
        </div>
    </form>
    </div>


<script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>   
<script type="text/javascript" src="<%=request.getContextPath()%>/js/verTransferenciaContacto/DatosAfiliados/DatosAfiliados_1/DatosAfiliados_1.js"></script>


</body>
</html>
