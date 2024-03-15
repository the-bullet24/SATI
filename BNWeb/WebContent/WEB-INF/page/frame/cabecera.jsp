
<%pe.cosapi.domain.TipoCambio tipo = ((pe.cosapi.domain.impl.UsuarioImpl)request.getSession().getAttribute(pe.cosapi.common.ConstanteSesion.USUARIO_EN_SESION)).getTipoCambio();  %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" 	prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" 	prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" 	prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld"				prefix="c"%>

<html>
<HEAD>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">
<script language="JavaScript" src="<%=request.getContextPath()%>/js/util.js"></script>
<link href="<%=request.getContextPath()%>/css/principal2.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/estilos/style.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/css/principal2.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/multired-virtual.css" /><META name="GENERATOR" content="IBM Software Development Platform">
<META http-equiv="Content-Style-Type" content="text/css">
<SCRIPT language="javascript">

function cerrar(){
	document.frmCabecera.action="<%=request.getContextPath()%>/inicio.do?metodo=logout";
	document.frmCabecera.target = "_parent";
	document.frmCabecera.submit();
}
function validaImagen(){
		var form = document.frmCabecera;
		form.action="<%=request.getContextPath()%>/login.do?metodo=validaSelloSeguridad";
		//form.metodo.value = 'validaClaveSeis';
		//form.HrTrx.value="9256";
		form.submit();
		
		}
</SCRIPT>
<TITLE>Banco de la Nación</TITLE>
</HEAD>

<body>

      <div id="contenedor">
		<form method="post" id="form" action="<%=request.getContextPath()%>/login.do" name="form">
           <div id="cabecera" class="limpiar">
          
               <div id="multired-virtual">
                 <h1 class="subtituloH1">
                  <b>Bienvenido/a : <bean:write name="<%=pe.cosapi.common.ConstanteSesion.USUARIO_EN_SESION%>" property="nombre" /></b>     
          	 <div id="cont_sello_seguridad">
             <c:if test="${txtImageSelloThumb!=''}">
			 <img src="<%=request.getContextPath()%><c:out value="${txtImageSelloThumb}"></c:out>" alt="Sello de Seguridad" width="54" height="54"/>
             </c:if>
             </div>	
             </h1>
                 
                    <%-- <img src="<%=request.getContextPath()%>/imagenes/multired-virtual.png" alt="Multired Virtual - Banco de la Naci&oacute;n" />--%>

                </div>

                <div id="logo-bn">
                            Dolar compra: <span><strong><%= tipo.getCompraMuestra() %></strong></span> 
                        Dolar Venta: <span><strong><%= tipo.getVentaMuestra() %></span>    
                    <a title="Ir al Inicio" >
                        <img src="<%=request.getContextPath()%>/imagenes/logo-banco-nacion.png" alt="Logotipo del Banco de la Naci&oacute;n" />
                        <!--Banco de la Naci&oacute;n, el banco de todos -->
                    </a>
                </div>
                
            
           </div>
           
           
           </form></div>
           
           
</BODY>
</html>
