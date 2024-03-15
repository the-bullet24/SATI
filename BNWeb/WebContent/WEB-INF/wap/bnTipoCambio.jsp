
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<html:html>
<HEAD>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<TITLE></TITLE>
</HEAD>
<%pe.cosapi.domain.TipoCambio tipoCambio = (pe.cosapi.domain.TipoCambio)request.getSession().getAttribute(pe.cosapi.common.ConstanteSesion.CONSULTA); %>
<BODY>
<P>Tipo de Cambio</P>
<P>Compra: S/ <%=tipoCambio.getCompra() %></P>
<P>Venta: S/ <%=tipoCambio.getVenta() %></P>
<P>Impuestos: S/ <%=tipoCambio.getImpuesto() %></P>
<P>Pasaporte: S/ <%=tipoCambio.getPasaporte() %></P>
</BODY>
</html:html>
