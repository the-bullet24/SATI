package pe.cosapi.domain.impl;

import java.io.Serializable;
import java.math.BigDecimal;

import pe.cosapi.common.DAOFactory;
import pe.cosapi.domain.MasterLimits;

public class MasterLimitsImpl implements MasterLimits, Serializable {
	private String 		tipoPersona;
	private String 		codTransaccion;
	private BigDecimal 	limInfOperacion;
	private BigDecimal 	limSupOperacion;
	private BigDecimal 	limInfDiario;
	private BigDecimal 	limSupDiario;
	private String 		codMoneda;
	
	public MasterLimitsImpl (){
	}
	
	public MasterLimitsImpl getLimitsById(String tipPersona,String codTrx) throws Exception{
		return DAOFactory.getGeneraDAO().getLimitsById(tipPersona, codTrx);
	}
	
	public void cargarData()throws Exception{
		MasterLimitsImpl mLimits = DAOFactory.getGeneraDAO().getLimitsById(tipoPersona, codTransaccion);
		if (mLimits == null)
			return;
		this.tipoPersona	 = mLimits.getTipoPersona();
		this.codTransaccion  = mLimits.getCodTransaccion();
		this.limInfOperacion = mLimits.getLimInfOperacion();
		this.limSupOperacion = mLimits.getLimSupOperacion();
		this.limInfDiario	 = mLimits.getLimInfDiario();
		this.limSupDiario	 = mLimits.getLimSupDiario();
	}
	
	public MasterLimitsImpl (String tipPersona,String codTrx) throws Exception{
		
		this.tipoPersona 	= tipPersona;
		this.codTransaccion = codTrx;
		MasterLimitsImpl limits_ =  DAOFactory.getGeneraDAO().getLimitsById(tipoPersona, codTransaccion);
		if (limits_==null)
			return;
		
		this.limInfOperacion = limits_.getLimInfOperacion();
		this.limSupOperacion = limits_.getLimSupOperacion();
		this.limInfDiario	 = limits_.getLimInfDiario();
		this.limSupDiario	 = limits_.getLimSupDiario();
	}
	
	/* (non-Javadoc)
	 * @see pe.cosapi.domain.MasterLimits#getCodMoneda()
	 */
	public String getCodMoneda() {
		return codMoneda;
	}
	/* (non-Javadoc)
	 * @see pe.cosapi.domain.MasterLimits#setCodMoneda(java.lang.String)
	 */
	public void setCodMoneda(String codMoneda) {
		this.codMoneda = codMoneda;
	}
	/* (non-Javadoc)
	 * @see pe.cosapi.domain.MasterLimits#getCodTransaccion()
	 */
	public String getCodTransaccion() {
		return codTransaccion;
	}
	/* (non-Javadoc)
	 * @see pe.cosapi.domain.MasterLimits#setCodTransaccion(java.lang.String)
	 */
	public void setCodTransaccion(String codTransaccion) {
		this.codTransaccion = codTransaccion;
	}
	/* (non-Javadoc)
	 * @see pe.cosapi.domain.MasterLimits#getLimInfDiario()
	 */
	public BigDecimal getLimInfDiario() {
		return limInfDiario;
	}
	/* (non-Javadoc)
	 * @see pe.cosapi.domain.MasterLimits#setLimInfDiario(java.math.BigDecimal)
	 */
	public void setLimInfDiario(BigDecimal limInfDiario) {
		this.limInfDiario = limInfDiario;
	}
	/* (non-Javadoc)
	 * @see pe.cosapi.domain.MasterLimits#getLimInfOperacion()
	 */
	public BigDecimal getLimInfOperacion() {
		return limInfOperacion;
	}
	/* (non-Javadoc)
	 * @see pe.cosapi.domain.MasterLimits#setLimInfOperacion(java.math.BigDecimal)
	 */
	public void setLimInfOperacion(BigDecimal limInfOperacion) {
		this.limInfOperacion = limInfOperacion;
	}
	/* (non-Javadoc)
	 * @see pe.cosapi.domain.MasterLimits#getLimSupDiario()
	 */
	public BigDecimal getLimSupDiario() {
		return limSupDiario;
	}
	/* (non-Javadoc)
	 * @see pe.cosapi.domain.MasterLimits#setLimSupDiario(java.math.BigDecimal)
	 */
	public void setLimSupDiario(BigDecimal limSupDiario) {
		this.limSupDiario = limSupDiario;
	}
	/* (non-Javadoc)
	 * @see pe.cosapi.domain.MasterLimits#getLimSupOperacion()
	 */
	public BigDecimal getLimSupOperacion() {
		return limSupOperacion;
	}
	/* (non-Javadoc)
	 * @see pe.cosapi.domain.MasterLimits#setLimSupOperacion(java.math.BigDecimal)
	 */
	public void setLimSupOperacion(BigDecimal limSupOperacion) {
		this.limSupOperacion = limSupOperacion;
	}
	/* (non-Javadoc)
	 * @see pe.cosapi.domain.MasterLimits#getTipoPersona()
	 */
	public String getTipoPersona() {
		return tipoPersona;
	}
	/* (non-Javadoc)
	 * @see pe.cosapi.domain.MasterLimits#setTipoPersona(java.lang.String)
	 */
	public void setTipoPersona(String tipoPersona) {
		this.tipoPersona = tipoPersona;
	}
	
	
}

