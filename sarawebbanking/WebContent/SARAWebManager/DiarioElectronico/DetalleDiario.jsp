<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<HTML>
<HEAD>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK href="../../theme/Master.css" rel="stylesheet"
	type="text/css">
<TITLE>Mensaje de Host</TITLE>
</HEAD>
<BODY background="/sarawebbanking/images/fondo1.JPG" vlink="#0000ff" alink="#0000ff">
<%String texto= request.getParameter("valor");
String flag= request.getParameter("flg");
 %>
<table align=center>
<tr  align="center">

<td><strong>MENSAJE DE<%=flag %></strong></td>

</tr>
<tr>

<td><TEXTAREA name="texto" rows="20" cols="80"><%=texto %></TEXTAREA></td>
</tr>
</table>


</BODY>
</HTML>
