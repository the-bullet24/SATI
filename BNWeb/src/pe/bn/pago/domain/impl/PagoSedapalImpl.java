/*
 * Creado el 12/03/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package pe.bn.pago.domain.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import pe.bn.afiliacion.domain.Afiliacion;
import pe.bn.pago.domain.PagoSedapal;
import pe.cosapi.common.ArrayRuleException;
import pe.cosapi.common.Constante;
import pe.cosapi.common.ConstanteError;
import pe.cosapi.common.FacadeFactory;
import pe.cosapi.common.ObjectUtil;
import pe.cosapi.domain.Cuenta;
import pe.cosapi.domain.Usuario;
import pe.cosapi.domain.impl.PagoImpl;
import pe.cosapi.domain.impl.ReciboImpl;
import pe.cosapi.sarabank.bean.Transaction;

/**
 * @author cosapi_ati02
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class PagoSedapalImpl extends PagoImpl implements Serializable, PagoSedapal{
	private String hora;
	private String fecha;
	private Cuenta cuenta;
	private ReciboImpl recibo;
	private String nroSuministro;
	private String nroChequeo;
	private String cliente;
	private String direccion;
	private String distrito;
	private String codServicio;
	private String codEntidad;
	private List recibos;
	private Afiliacion afiliacion;
	
	/**
	 * @return Devuelve cuenta.
	 */
	public Cuenta getCuenta() {
		return cuenta;
	}
	/**
	 * @param cuenta El cuenta a establecer.
	 */
	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}
	/**
	 * @return Devuelve fechaEmisión.
	 */
	/**
	 * @return Devuelve nroSuministro.
	 */
	public String getNroSuministro() {
		return nroSuministro;
	}
	/**
	 * @param nroSuministro El nroSuministro a establecer.
	 */
	public void setNroSuministro(String nroSuministro) {
		this.nroSuministro = nroSuministro;
	}
	
	public void getPago(Transaction t, Usuario usuario) throws Exception{
		this.recibos = new ArrayList();
		Vector resutado = procesar(t);
		Vector recibs= new Vector();
		HashMap mapaRecibos = new HashMap();
		List ids = new ArrayList();
		List idsl = new ArrayList();
		for (Iterator iter = resutado.iterator(); iter.hasNext();) {
			Vector element = (Vector) iter.next();
			String recibillo="";
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_1)){
				String recibos = element.elementAt(6).toString();
				//System.out.println("Todos los recibos-->"+recibos);
				for(int i=0; i < recibos.length(); ){
					recibillo="";
					int j = 0;
					recibillo = recibos.substring(i,i+=40);//28408950022000061000000000000918401
					//System.out.println("Recibo-->"+recibillo);
					//System.out.println("recibillo.substring(10,18)-->" +recibillo.substring(10,18));
					if(!recibillo.substring(10,18).trim().equals("00000000")){
						//System.out.println("entró al if");
						if(!recibillo.trim().equals("")){
							ReciboImpl recibo = new ReciboImpl();
							recibo.setNumRecibo(recibillo.substring(j,j+=10));
							recibo.setFecha(ObjectUtil.stringTramaToTimestamp(recibillo.substring(j,j+=8)));
							//System.out.println("importe-->" +recibillo.substring(j,j+15));
							recibo.setImporte(ObjectUtil.tramaToBigDecimal(recibillo.substring(j,j+=15)));
							recibo.setMoneda(Constante.MONEDA_SOL);
							recibs.addElement(recibo);
							mapaRecibos.put(recibo.getNumRecibo(),recibo);
							idsl.add(recibo.getNumRecibo());
						}
					}
				}
			}
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_2)){
				String recibos = element.elementAt(6).toString();

				for(int i=0; i < recibos.length(); ){
					int j = 0;
					String recibito = recibos.substring(i,i+=40);					
					ids.add(recibito.substring(j,j+=10));
				}
			}
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_3)){
				this.setCliente(element.elementAt(6).toString().trim());
			}
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_4)){
				this.setDireccion(element.elementAt(6).toString().trim());
			}
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_5)){
				this.setDistrito(element.elementAt(6).toString().trim());
			}
		}
		//ids= map de recibos pagados
		//remueve los recibos ya pagados comparandolos con el map de recibos pagados
		/**for(int i = 0 ; i < ids.size(); i++){
			if(mapaRecibos.containsKey(ids.get(i).toString())){
				mapaRecibos.remove(ids.get(i).toString());
			}
		}**/
		for(int i = 0 ; i < ids.size(); i++){
			for(int h=0;h<recibs.size();h++){
				ReciboImpl rcb=(ReciboImpl)recibs.elementAt(h);
				if(rcb.getNumRecibo().trim().equals(ids.get(i).toString().trim())){
					recibs.removeElementAt(h);
				}
			}
		}
		//idsl contiene solamente los numeros de recibos
		//adiciona solamente los recibos que no estan pagados
		/**for(int i = 0 ; i < idsl.size(); i++){
			ReciboImpl recibo = (ReciboImpl)mapaRecibos.get(idsl.get(i).toString());
			if(recibo!=null){
				this.recibos.add(recibo);
			}
		}**/
		for(int i = 0 ; i < recibs.size(); i++){
			ReciboImpl rcb=(ReciboImpl)recibs.elementAt(i);	
			this.recibos.add(rcb);
		}
		if(getArrayRuleException()!=null) 
			throw getArrayRuleException();
	}
	
	public void getPagov2(Transaction t, Usuario usuario) throws Exception{
		this.recibos = new ArrayList();
		Vector resutado = procesar(t);
		Vector recibs= new Vector();
		HashMap mapaRecibos = new HashMap();
		List ids = new ArrayList();
		List idsl = new ArrayList();
		for (Iterator iter = resutado.iterator(); iter.hasNext();) {
			Vector element = (Vector) iter.next();
			String recibillo="";
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_1)){
				String recibos = element.elementAt(6).toString();
			
				for(int i=0; i < recibos.length(); ){
					recibillo="";
					int j = 0;
					recibillo = recibos.substring(i,i+=55);//28408950022000061000000000000918401
										
						if(!recibillo.trim().equals("")){
							ReciboImpl recibo = new ReciboImpl();
							recibo.setNumRecibo(recibillo.substring(j+=4,j+=12).trim());
							recibo.setFecha(ObjectUtil.stringTramaToTimestampConver(recibillo.substring(j,j+=10)));
							recibo.setImporte(ObjectUtil.tramaToBigDecimal(recibillo.substring(j+=14,j+=15)));
							recibo.setMoneda(Constante.MONEDA_SOL);
							recibs.addElement(recibo);
							mapaRecibos.put(recibo.getNumRecibo(),recibo);
							idsl.add(recibo.getNumRecibo());
						}
					}
			}
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_2)){
				String recibos = element.elementAt(6).toString();

				for(int i=0; i < recibos.length(); ){
					int j = 0;
					String recibito = recibos.substring(i,i+=40);					
					ids.add(recibito.substring(j,j+=10));
				}
			}
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_3)){
				this.setCliente(element.elementAt(6).toString().trim());
			}
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_4)){
				this.setDireccion(element.elementAt(6).toString().trim());
			}
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_5)){
				this.setDistrito(element.elementAt(6).toString().trim());
			}
		}
		//ids= map de recibos pagados
		//remueve los recibos ya pagados comparandolos con el map de recibos pagados
		/**for(int i = 0 ; i < ids.size(); i++){
			if(mapaRecibos.containsKey(ids.get(i).toString())){
				mapaRecibos.remove(ids.get(i).toString());
			}
		}**/
		for(int i = 0 ; i < ids.size(); i++){
			for(int h=0;h<recibs.size();h++){
				ReciboImpl rcb=(ReciboImpl)recibs.elementAt(h);
				if(rcb.getNumRecibo().trim().equals(ids.get(i).toString().trim())){
					recibs.removeElementAt(h);
				}
			}
		}
		//idsl contiene solamente los numeros de recibos
		//adiciona solamente los recibos que no estan pagados
		/**for(int i = 0 ; i < idsl.size(); i++){
			ReciboImpl recibo = (ReciboImpl)mapaRecibos.get(idsl.get(i).toString());
			if(recibo!=null){
				this.recibos.add(recibo);
			}
		}**/
		for(int i = 0 ; i < recibs.size(); i++){
			ReciboImpl rcb=(ReciboImpl)recibs.elementAt(i);	
			this.recibos.add(rcb);
		}
		if(getArrayRuleException()!=null) 
			throw getArrayRuleException();
	}

	public void pagar(Transaction t, Usuario usuario) throws Exception{
		String importeSinFormato = ObjectUtil.replaceChar(this.getRecibo().getImporte(),',',"");
		//importeSinFormato = ObjectUtil.replaceChar(importeSinFormato,'.',"");
		BigDecimal importeX = ObjectUtil.deFormatearMonto(importeSinFormato);
		//FacadeFactory.getGeneralFacade().validarLimiteImporteHost(usuario,this.getCuenta(),importeX);
		Vector resutado = procesar(t);
		for (Iterator iter = resutado.iterator(); iter.hasNext();) {
			Vector element = (Vector) iter.next();
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_1)){
				setNroOperacion(element.elementAt(6).toString());
			}
			
		}
		t.setObjeto(this);
		generarLog(t,usuario,Constante.REFRENDO_PAGO_SEDAPAL);		
		if(getArrayRuleException()!=null) throw getArrayRuleException();
	}
	/**
	 * @return Devuelve nroChequeo.
	 */
	public String getNroChequeo() {
		return nroChequeo;
	}
	/**
	 * @param nroChequeo El nroChequeo a establecer.
	 */
	public void setNroChequeo(String nroChequeo) {
		this.nroChequeo = nroChequeo;
	}
	/**
	 * @return Devuelve recibos.
	 */
	public List getRecibos() {
		return recibos;
	}
	/**
	 * @param recibos El recibos a establecer.
	 */
	public void setRecibos(List recibos) {
		this.recibos = recibos;
	}
	/**
	 * @return Devuelve cliente.
	 */
	public String getCliente() {
		return cliente;
	}
	/**
	 * @param cliente El cliente a establecer.
	 */
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	/**
	 * @return Devuelve direccion.
	 */
	public String getDireccion() {
		return direccion;
	}
	/**
	 * @param direccion El direccion a establecer.
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	/**
	 * @return Devuelve distrito.
	 */
	public String getDistrito() {
		return distrito;
	}
	/**
	 * @param distrito El distrito a establecer.
	 */
	public void setDistrito(String distrito) {
		this.distrito = distrito;
	}
	/**
	 * @return Devuelve recibo.
	 */
	public ReciboImpl getRecibo() {
		return recibo;
	}
	/**
	 * @param recibo El recibo a establecer.
	 */
	public void setRecibo(ReciboImpl recibo) {
		this.recibo = recibo;
	}
	/**
	 * @return Devuelve afiliacion.
	 */
	public Afiliacion getAfiliacion() {
		return afiliacion;
	}
	/**
	 * @param afiliacion El afiliacion a establecer.
	 */
	public void setAfiliacion(Afiliacion afiliacion) {
		this.afiliacion = afiliacion;
	}
	/**
	 * @return Devuelve fecha.
	 */
	public String getFecha() {
		return fecha;
	}
	/**
	 * @param fecha El fecha a establecer.
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	/**
	 * @return Devuelve hora.
	 */
	public String getHora() {
		return hora;
	}
	/**
	 * @param hora El hora a establecer.
	 */
	public void setHora(String hora) {
		this.hora = hora;
	}

	
	
	/**
	 * @return Returns the codEntidad.
	 */
	public String getCodEntidad() {
		return codEntidad;
	}
	/**
	 * @param codEntidad The codEntidad to set.
	 */
	public void setCodEntidad(String codEntidad) {
		this.codEntidad = codEntidad;
	}
	/**
	 * @return Returns the codServicio.
	 */
	public String getCodServicio() {
		return codServicio;
	}
	/**
	 * @param codServicio The codServicio to set.
	 */
	public void setCodServicio(String codServicio) {
		this.codServicio = codServicio;
	}
}
