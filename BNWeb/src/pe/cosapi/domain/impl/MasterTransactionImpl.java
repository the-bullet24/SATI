package pe.cosapi.domain.impl;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Calendar;

import pe.cosapi.common.DAOFactory;
import pe.cosapi.common.ObjectUtil;
import pe.cosapi.domain.MasterTransaction;

public class MasterTransactionImpl implements MasterTransaction, Serializable {
	private String codTransaccion;
	private String codGrupo;
	private String nomTransaccion;
	private String guiaProc;
	private String flaglogOperacion;
	private String horaInicio;
	private String horaFin;
	private String flagActTransaccion;
	
	
	public MasterTransaction getTransactionById(String codTrx) throws Exception{
		return DAOFactory.getGeneraDAO().getTransactionById(codTrx);
	}
	
	public void cargarData()throws Exception{
		MasterTransaction masterTransaction = DAOFactory.getGeneraDAO().getTransactionById(codTransaccion);
		
		if (masterTransaction == null)
			return ;
		
		this.codTransaccion 	= masterTransaction.getCodTransaccion();
		this.codGrupo 			= masterTransaction.getCodGrupo();
		this.nomTransaccion 	= masterTransaction.getNomTransaccion();
		this.guiaProc 			= masterTransaction.getGuiaProc();
		this.flaglogOperacion 	= masterTransaction.getFlaglogOperacion();
		this.horaInicio 		= masterTransaction.getHoraInicio();
		this.horaFin 			= masterTransaction.getHoraFin();
		this.flagActTransaccion = masterTransaction.getFlagActTransaccion();
	}
	
	public boolean isHoraAtencion() {
		boolean flag = false;
		Timestamp ts = new Timestamp(Calendar.getInstance().getTime().getTime());
		
		String[] arrTimeBefore 	= ObjectUtil.getArrayStrings(this.horaInicio, ":");
		Calendar calBefore 		= ObjectUtil.getCalendar(ObjectUtil.getYear(), ObjectUtil.getMonth(), ObjectUtil.getDay(), Integer.parseInt(arrTimeBefore[0]), Integer.parseInt(arrTimeBefore[1]));
		Timestamp tsBefore 		= new Timestamp(calBefore.getTime().getTime());
		
		String[] arrTimeAfter 	= ObjectUtil.getArrayStrings(this.horaFin, ":");
		Calendar calAfter 		= ObjectUtil.getCalendar(ObjectUtil.getYear(), ObjectUtil.getMonth(), ObjectUtil.getDay(), Integer.parseInt(arrTimeAfter[0]), Integer.parseInt(arrTimeAfter[1]));
		Timestamp tsAfter 		= new Timestamp(calAfter.getTime().getTime());
		
		if (ts.equals(tsBefore) || tsAfter.equals(ts))
			return true;
		
		if (ts.after(tsBefore) && tsAfter.after(ts)){
			return true;
		}
		
		return flag;
	}
	
	/* (non-Javadoc)
	 * @see pe.cosapi.domain.impl.MasterTransaction#getCodGrupo()
	 */
	public String getCodGrupo() {
		return codGrupo;
	}
	/* (non-Javadoc)
	 * @see pe.cosapi.domain.impl.MasterTransaction#setCodGrupo(java.lang.String)
	 */
	public void setCodGrupo(String codGrupo) {
		this.codGrupo = codGrupo;
	}
	/* (non-Javadoc)
	 * @see pe.cosapi.domain.impl.MasterTransaction#getCodTransaccion()
	 */
	public String getCodTransaccion() {
		return codTransaccion;
	}
	/* (non-Javadoc)
	 * @see pe.cosapi.domain.impl.MasterTransaction#setCodTransaccion(java.lang.String)
	 */
	public void setCodTransaccion(String codTransaccion) {
		this.codTransaccion = codTransaccion;
	}
	/* (non-Javadoc)
	 * @see pe.cosapi.domain.impl.MasterTransaction#getFlagActTransaccion()
	 */
	public String getFlagActTransaccion() {
		return flagActTransaccion;
	}
	/* (non-Javadoc)
	 * @see pe.cosapi.domain.impl.MasterTransaction#setFlagActTransaccion(java.lang.String)
	 */
	public void setFlagActTransaccion(String flagActTransaccion) {
		this.flagActTransaccion = flagActTransaccion;
	}
	/* (non-Javadoc)
	 * @see pe.cosapi.domain.impl.MasterTransaction#getFlaglogOperacion()
	 */
	public String getFlaglogOperacion() {
		return flaglogOperacion;
	}
	/* (non-Javadoc)
	 * @see pe.cosapi.domain.impl.MasterTransaction#setFlaglogOperacion(java.lang.String)
	 */
	public void setFlaglogOperacion(String flaglogOperacion) {
		this.flaglogOperacion = flaglogOperacion;
	}
	/* (non-Javadoc)
	 * @see pe.cosapi.domain.impl.MasterTransaction#getGuiaProc()
	 */
	public String getGuiaProc() {
		return guiaProc;
	}
	/* (non-Javadoc)
	 * @see pe.cosapi.domain.impl.MasterTransaction#setGuiaProc(java.lang.String)
	 */
	public void setGuiaProc(String guiaProc) {
		this.guiaProc = guiaProc;
	}
	/* (non-Javadoc)
	 * @see pe.cosapi.domain.impl.MasterTransaction#getHoraFin()
	 */
	public String getHoraFin() {
		return horaFin;
	}
	/* (non-Javadoc)
	 * @see pe.cosapi.domain.impl.MasterTransaction#setHoraFin(java.lang.String)
	 */
	public void setHoraFin(String horaFin) {
		this.horaFin = horaFin;
	}
	/* (non-Javadoc)
	 * @see pe.cosapi.domain.impl.MasterTransaction#getHoraInicio()
	 */
	public String getHoraInicio() {
		return horaInicio;
	}
	/* (non-Javadoc)
	 * @see pe.cosapi.domain.impl.MasterTransaction#setHoraInicio(java.lang.String)
	 */
	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}
	/* (non-Javadoc)
	 * @see pe.cosapi.domain.impl.MasterTransaction#getNomTransaccion()
	 */
	public String getNomTransaccion() {
		return nomTransaccion;
	}
	/* (non-Javadoc)
	 * @see pe.cosapi.domain.impl.MasterTransaction#setNomTransaccion(java.lang.String)
	 */
	public void setNomTransaccion(String nomTransaccion) {
		this.nomTransaccion = nomTransaccion;
	}
}
