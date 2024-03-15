/*
 * Creado el 03/04/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package pe.cosapi.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @author cosapi_ati04
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public interface Journal extends Serializable {
	/*
	 public Journal[] getArrayJournal(String numeroCuenta,String trx) throws Exception{
	 return DAOFactory.getSaraWebLogImpl().getArrayJournal(numeroCuenta, trx);
	 }
	 */public abstract BigDecimal getAmotra();

	/**
	 * @param amotra El amotra a establecer.
	 */
	public abstract void setAmotra(String amotra);

	public abstract String getNomben();
		/**
		 * @param amotra El amotra a establecer.
		 */
	public abstract void setNomben(String nomben);
	/**
	 * @return Devuelve codcur.
	 */
	public abstract String getCodcur();

	/**
	 * @param codcur El codcur a establecer.
	 */
	public abstract void setCodcur(String codcur);

	/**
	 * @return Devuelve coddoc.
	 */
	public abstract String getCoddoc();

	/**
	 * @param coddoc El coddoc a establecer.
	 */
	public abstract void setCoddoc(String coddoc);

	/**
	 * @return Devuelve codent.
	 */
	public abstract String getCodent();

	/**
	 * @param codent El codent a establecer.
	 */
	public abstract void setCodent(String codent);
	
	/**
	 * @return Devuelve codser.
	 */
	public abstract String getCodser();

	/**
	 * @param codser El codser a establecer.
	 */
	public abstract void setCodser(String codser);

	/**
	 * @return Devuelve idetracom.
	 */
	public abstract String getIdetracom();

	/**
	 * @param idetracom El idetracom a establecer.
	 */
	public abstract void setIdetracom(String idetracom);

	/**
	 * @return Devuelve idetrapro.
	 */
	public abstract String getIdetrapro();

	/**
	 * @param idetrapro El idetrapro a establecer.
	 */
	public abstract void setIdetrapro(String idetrapro);

	/**
	 * @return Devuelve msghst.
	 */
	public abstract String getMsghst();

	/**
	 * @param msghst El msghst a establecer.
	 */
	public abstract void setMsghst(String msghst);

	/**
	 * @return Devuelve numdoc.
	 */
	public abstract String getNumdoc();

	/**
	 * @param numdoc El numdoc a establecer.
	 */
	public abstract void setNumdoc(String numdoc);

	/**
	 * @return Devuelve numprdsrc.
	 */
	public abstract String getNumprdsrc();

	/**
	 * @param numprdsrc El numprdsrc a establecer.
	 */
	public abstract void setNumprdsrc(String numprdsrc);

	/**
	 * @return Devuelve numprdtar.
	 */
	public abstract String getNumprdtar();

	/**
	 * @param numprdtar El numprdtar a establecer.
	 */
	public abstract void setNumprdtar(String numprdtar);

	/**
	 * @return Devuelve numref.
	 */
	public abstract String getNumref();

	/**
	 * @param numref El numref a establecer.
	 */
	public abstract void setNumref(String numref);

	/**
	 * @return Devuelve tipprdsrc.
	 */
	public abstract String getTipprdsrc();

	/**
	 * @param tipprdsrc El tipprdsrc a establecer.
	 */
	public abstract void setTipprdsrc(String tipprdsrc);

	/**
	 * @return Devuelve tipprdtar.
	 */
	public abstract String getTipprdtar();

	/**
	 * @param tipprdtar El tipprdtar a establecer.
	 */
	public abstract void setTipprdtar(String tipprdtar);
	
	/**
	 * @return Devuelve amotxn.
	 */
	public BigDecimal getAmotxn();
	
	/**
	 * @param amotxn El amotxn a establecer.
	 */
	public void setAmotxn(String amotxn);
	
	/**
	 * @return Devuelve baltra.
	 */
	public BigDecimal getBaltra();
	
	/**
	 * @param baltra El baltra a establecer.
	 */
	public void setBaltra(String baltra);
	
	/**
	 * @return Devuelve cipadr.
	 */
	public String getCipadr();
	
	/**
	 * @param cipadr El cipadr a establecer.
	 */
	public void setCipadr(String cipadr);
	
	/**
	 * @return Devuelve codtra.
	 */
	public String getCodtra();
	
	/**
	 * @param codtra El codtra a establecer.
	 */
	public void setCodtra(String codtra);
	
	/**
	 * @return Devuelve codtrahst.
	 */
	public String getCodtrahst();
	
	/**
	 * @param codtrahst El codtrahst a establecer.
	 */
	public void setCodtrahst(String codtrahst);
	
	/**
	 * @return Devuelve datpro.
	 */
	public Timestamp getDatpro();
	
	/**
	 * @param datpro El datpro a establecer.
	 */
	public void setDatpro(Timestamp datpro);
	
	/**
	 * @return Devuelve horpro.
	 */
	public String getHorpro();
	
	/**
	 * @param horpro El horpro a establecer.
	 */
	public void setHorpro(String horpro);
	
	/**
	 * @return Devuelve msgerror.
	 */
	public String getMsgerror();
	
	/**
	 * @param msgerror El msgerror a establecer.
	 */
	public void setMsgerror(String msgerror);
	
	/**
	 * @return Devuelve numlog.
	 */
	public String getNumlog();
	
	/**
	 * @param numlog El numlog a establecer.
	 */
	public void setNumlog(String numlog);
	
	/**
	 * @return Devuelve typper.
	 */
	public String getTypper();
	
	/**
	 * @param typper El typper a establecer.
	 */
	public abstract void setTypper(String typper);
	
	public abstract String getConstancia();
	
	/**
	 * @param constancia El constancia a establecer.
	 */
	public abstract void setConstancia(String constancia);
	
	public abstract String getNumope();
	/**
	 * @param numope El numope a establecer.
	 */
	public abstract void setNumope(String numope);
	
	public abstract String getFlgcha();
	
	public abstract String getNrocta();
     
    public abstract void setNrocta(String nrocta);
    
    public abstract String getFlgcom();
     
    public abstract void setFlgcom(String flgcom);
     
    public abstract String getFlgerror();
     
    public abstract void setFlgerror(String flgerror);
     
    public abstract String getTyptar();
     
    public abstract void setTyptar(String typtar);
     
    public abstract void setAmotra(BigDecimal amotra);
     
    public abstract void setAmotxn(BigDecimal amotxn);
    
    public abstract void setBaltra(BigDecimal baltra);
    
}