/*
 * Creado el 12/02/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package pe.bn.consulta.facade.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import pe.bn.afiliacion.domain.Afiliacion;
import pe.bn.afiliacion.domain.impl.AfiliacionImpl;
import pe.bn.consulta.dao.impl.MontoImpl;
import pe.bn.consulta.domain.Monto;
import pe.bn.consulta.facade.SeleccionMontoFacade;
import pe.com.bn.sati.common.LoggerSati;
import pe.cosapi.domain.Usuario;
import pe.cosapi.sarabank.bean.Transaction;



/**
 * @author Mily Dolores B.
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class SeleccionMontoFacadeImpl implements SeleccionMontoFacade {
	private static LoggerSati log3 = LoggerSati.getInstance(SeleccionMontoFacadeImpl.class.getName());
  
	public Monto guardarMontoMaximo(String transaccion, Monto monto,  String remoteAddress, Usuario usuario,HttpServletRequest request) throws Exception{
	

		Transaction t = new Transaction(transaccion);
		Vector valores = new Vector();
		Vector valor = new Vector();

		
		valor = new Vector();
				
		valor.addElement("transaccion");
		valor.addElement(transaccion);
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("montoMaximo");
		valor.addElement(monto.getMontoMaximo());
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("canal");
		valor.addElement("1");
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("nrocuenta");
		valor.addElement(monto.getCuenta().getNumeroProducto());
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("tipoElemento");
		valor.addElement(monto.getTipoElemento());
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("nroTarjeta");
		valor.addElement(usuario.getTarjeta().getNumero());
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("tipoCliente");
		valor.addElement("1");
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("nroElemento");
		valor.addElement(monto.getCodigoElemento());
		valores.add(valor);
		
	
		Vector ctas=new Vector();
		Vector v=new Vector();
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement(remoteAddress);
		ctas.addElement(v);
		t.setValues(valores);
		t.setCuentas(ctas);	
		MontoImpl seleccionMonto = new MontoImpl();
		seleccionMonto.setMontoMaximo(monto.getMontoMaximo());
		seleccionMonto.setDesMontoMaximo(monto.getDesMontoMaximo());
		seleccionMonto.guardarMontoMaximo(t,valores,ctas,usuario);
		
		return (Monto)seleccionMonto;
		
	}
	
	
 
} 
