package pe.cosapi.domain.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import pe.cosapi.common.DAOFactory;
import pe.cosapi.domain.Journal;

public class JournalImpl implements Journal, Serializable {

	private String 	numlog;
	private Timestamp datpro;
	private String 	horpro;
	private String 	cipadr;
	private String 	codtra;
	private String 	codtrahst;
	private BigDecimal 	amotxn;
	private BigDecimal 	baltra;
	private String 	msgerror;
	private String 	typper;
	private String nomben;

	//Atributos calculados desde el vector mascara
	private String 	coddoc	;
	private String 	nrocta;
	private String 	numdoc	;
	private String 	tipprdsrc	;
	private String 	numprdsrc	;
	private String 	tipprdtar	;
	private String 	numprdtar	;
	private BigDecimal 	amotra	;
	private String 	codcur	;
	private String 	numref	;
	private String 	codent	;
	private String 	codser	;
	private String 	msghst	;
	private String 	idetrapro	;
	private String 	idetracom	;
	
	private String constancia;
	private String numope;
	private String flgcha;
	
	private String flgcom;
	private String typtar;
	private String flgerror;
	
	public Journal[] getArrayJournal(String numeroCuenta,String trx) throws Exception{
		return DAOFactory.getSaraWebLogImpl().getArrayJournal(numeroCuenta, trx);
	}
	
	public JournalImpl[] getArrayJournalByNroCuenta(String numeroCuenta) throws Exception{
		return DAOFactory.getSaraWebLogImpl().getArrayJournalByNroCuenta(numeroCuenta);
	}
	
	/**
	 * @return Devuelve amotra.
	 */
	public BigDecimal getAmotra() {
		return amotra;
	}
	/**
	 * @param amotra El amotra a establecer.
	 */
	public void setAmotra(String amotra) {
		
		this.amotra = new BigDecimal(amotra);
		
	}
	/**
	 * @return Devuelve codcur.
	 */
	public String getCodcur() {
		return codcur;
	}
	/**
	 * @param codcur El codcur a establecer.
	 */
	public void setCodcur(String codcur) {
		this.codcur = codcur;
	}
	/**
	 * @return Devuelve coddoc.
	 */
	public String getCoddoc() {
		return coddoc;
	}
	/**
	 * @param coddoc El coddoc a establecer.
	 */
	public void setCoddoc(String coddoc) {
		this.coddoc = coddoc;
	}
	/**
	 * @return Devuelve codent.
	 */
	public String getCodent() {
		return codent;
	}
	/**
	 * @param codent El codent a establecer.
	 */
	public void setCodent(String codent) {
		this.codent = codent;
	}
	/**
	 * @return Devuelve idetracom.
	 */
	public String getIdetracom() {
		return idetracom;
	}
	/**
	 * @param idetracom El idetracom a establecer.
	 */
	public void setIdetracom(String idetracom) {
		this.idetracom = idetracom;
	}
	/**
	 * @return Devuelve idetrapro.
	 */
	public String getIdetrapro() {
		return idetrapro;
	}
	/**
	 * @param idetrapro El idetrapro a establecer.
	 */
	public void setIdetrapro(String idetrapro) {
		this.idetrapro = idetrapro;
	}
	/**
	 * @return Devuelve msghst.
	 */
	public String getMsghst() {
		return msghst;
	}
	/**
	 * @param msghst El msghst a establecer.
	 */
	public void setMsghst(String msghst) {
		this.msghst = msghst;
	}
	/**
	 * @return Devuelve numdoc.
	 */
	public String getNumdoc() {
		return numdoc;
	}
	/**
	 * @param numdoc El numdoc a establecer.
	 */
	public void setNumdoc(String numdoc) {
		this.numdoc = numdoc;
	}
	/**
	 * @return Devuelve numprdsrc.
	 */
	public String getNumprdsrc() {
		return numprdsrc;
	}
	/**
	 * @param numprdsrc El numprdsrc a establecer.
	 */
	public void setNumprdsrc(String numprdsrc) {
		this.numprdsrc = numprdsrc;
	}
	/**
	 * @return Devuelve numprdtar.
	 */
	public String getNumprdtar() {
		return numprdtar;
	}
	/**
	 * @param numprdtar El numprdtar a establecer.
	 */
	public void setNumprdtar(String numprdtar) {
		this.numprdtar = numprdtar;
	}
	/**
	 * @return Devuelve numref.
	 */
	public String getNumref() {
		return numref;
	}
	/**
	 * @param numref El numref a establecer.
	 */
	public void setNumref(String numref) {
		this.numref = numref;
	}
	/**
	 * @return Devuelve tipprdsrc.
	 */
	public String getTipprdsrc() {
		return tipprdsrc;
	}
	/**
	 * @param tipprdsrc El tipprdsrc a establecer.
	 */
	public void setTipprdsrc(String tipprdsrc) {
		this.tipprdsrc = tipprdsrc;
	}
	/**
	 * @return Devuelve tipprdtar.
	 */
	public String getTipprdtar() {
		return tipprdtar;
	}
	/**
	 * @param tipprdtar El tipprdtar a establecer.
	 */
	public void setTipprdtar(String tipprdtar) {
		this.tipprdtar = tipprdtar;
	}
	
	//////////////////////////////////////////////////
	
	/**
	 * @return Devuelve amotxn.
	 */
	public BigDecimal getAmotxn() {
		return amotxn;
	}
	/**
	 * @param amotxn El amotxn a establecer.
	 */
	public void setAmotxn(String amotxn) {
		this.amotxn = new BigDecimal(amotxn);
	}
	/**
	 * @return Devuelve baltra.
	 */
	public BigDecimal getBaltra() {
		return baltra;
	}
	/**
	 * @param baltra El baltra a establecer.
	 */
	public void setBaltra(String baltra) {
		this.baltra = new BigDecimal(baltra);
	}
	/**
	 * @return Devuelve cipadr.
	 */
	public String getCipadr() {
		return cipadr;
	}
	/**
	 * @param cipadr El cipadr a establecer.
	 */
	public void setCipadr(String cipadr) {
		this.cipadr = cipadr;
	}
	/**
	 * @return Devuelve codtra.
	 */
	public String getCodtra() {
		return codtra;
	}
	/**
	 * @param codtra El codtra a establecer.
	 */
	public void setCodtra(String codtra) {
		this.codtra = codtra;
	}
	/**
	 * @return Devuelve codtrahst.
	 */
	public String getCodtrahst() {
		return codtrahst;
	}
	/**
	 * @param codtrahst El codtrahst a establecer.
	 */
	public void setCodtrahst(String codtrahst) {
		this.codtrahst = codtrahst;
	}
	/**
	 * @return Devuelve datpro.
	 */
	public Timestamp getDatpro() {
		return datpro;
	}
	/**
	 * @param datpro El datpro a establecer.
	 */
	public void setDatpro(Timestamp datpro) {
		this.datpro = datpro;
	}
	/**
	 * @return Devuelve horpro.
	 */
	public String getHorpro() {
		return horpro;
	}
	/**
	 * @param horpro El horpro a establecer.
	 */
	public void setHorpro(String horpro) {
		this.horpro = horpro;
	}
	/**
	 * @return Devuelve msgerror.
	 */
	public String getMsgerror() {
		return msgerror;
	}
	/**
	 * @param msgerror El msgerror a establecer.
	 */
	public void setMsgerror(String msgerror) {
		this.msgerror = msgerror;
	}
	/**
	 * @return Devuelve numlog.
	 */
	public String getNumlog() {
		return numlog;
	}
	/**
	 * @param numlog El numlog a establecer.
	 */
	public void setNumlog(String numlog) {
		this.numlog = numlog;
	}
	/**
	 * @return Devuelve typper.
	 */
	public String getTypper() {
		return typper;
	}
	/**
	 * @param typper El typper a establecer.
	 */
	public void setTypper(String typper) {
		this.typper = typper;
	}
	/**
	 * @return Devuelve constancia.
	 */
	public String getConstancia() {
		return constancia;
	}
	/**
	 * @param constancia El constancia a establecer.
	 */
	public void setConstancia(String constancia) {
		this.constancia = constancia;
	}
	/**
	 * @return Devuelve numope.
	 */
	public String getNumope() {
		return numope;
	}
	/**
	 * @param numope El numope a establecer.
	 */
	public void setNumope(String numope) {
		this.numope = numope;
	}
	/**
	 * @return Devuelve flgcha.
	 */
	public String getFlgcha() {
		return flgcha;
	}
	/**
	 * @param flgcha El flgcha a establecer.
	 */
	public void setFlgcha(String flgcha) {
		this.flgcha = flgcha;
	}
    public String getNrocta() {
        return nrocta;
    }
    public void setNrocta(String nrocta) {
        this.nrocta = nrocta;
    }
    
    
    public String getFlgcom() {
        return flgcom;
    }
    public void setFlgcom(String flgcom) {
        this.flgcom = flgcom;
    }
    public String getFlgerror() {
        return flgerror;
    }
    public void setFlgerror(String flgerror) {
        this.flgerror = flgerror;
    }
    public String getTyptar() {
        return typtar;
    }
    public void setTyptar(String typtar) {
        this.typtar = typtar;
    }
    public void setAmotra(BigDecimal amotra) {
        this.amotra = amotra;
    }
    public void setAmotxn(BigDecimal amotxn) {
        this.amotxn = amotxn;
    }
    public void setBaltra(BigDecimal baltra) {
        this.baltra = baltra;
    }
	/**
	 * @return Returns the nomben.
	 */
	public String getNomben() {
		return nomben;
	}
	/**
	 * @param nomben The nomben to set.
	 */
	public void setNomben(String nomben) {
		this.nomben = nomben;
	}
	
	
	
	/**
	 * @return Returns the codser.
	 */
	public String getCodser() {
		return codser;
	}
	/**
	 * @param codser The codser to set.
	 */
	public void setCodser(String codser) {
		this.codser = codser;
	}
	
	@Override
	public String toString() {
		return "JournalImpl [datpro=" + datpro
				+ ", horpro=" + horpro + ", codtra="
				+ codtra + ", codtrahst=" + codtrahst + ", amotxn=" + amotxn
				+ ", baltra=" + baltra + ", msgerror=" + msgerror + ", typper="
				+ typper + ", coddoc=" + coddoc
				+ ", numdoc=" + numdoc + ", tipprdsrc="
				+ tipprdsrc +  ", tipprdtar="
				+ tipprdtar + ", amotra=" + amotra
				+ ", codcur=" + codcur + ", numref=" + numref + ", codent="
				+ codent + ", codser=" + codser 
				+ ", idetrapro=" + idetrapro + ", idetracom=" + idetracom
				+ ", numope=" + numope
				+ ", flgcha=" + flgcha + ", flgcom=" + flgcom + ", typtar="
				+ typtar + ", flgerror=" + flgerror + "]";
	}
	
}
