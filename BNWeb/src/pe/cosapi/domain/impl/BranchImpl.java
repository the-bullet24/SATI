package pe.cosapi.domain.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Calendar;

import pe.cosapi.common.DAOFactory;
import pe.cosapi.common.ObjectUtil;
import pe.cosapi.domain.Branch;

public class BranchImpl implements Branch, Serializable {
	
	private String  codbra;
	private String  numsum;
	private String  flgblkbra;
	private BigDecimal dayprgtra;
	private String  bandef;
	private String  houini;
	private String  houfin;
	private String  banpna;
	private String  banpju;
	
	
	public Branch getBranch(String codbra) throws Exception{
		return DAOFactory.getGeneraDAO().getBranch(codbra);
	}
	
	/* (non-Javadoc)
	 * @see pe.cosapi.domain.impl.Branch#isBlockAgency()
	 */
	public boolean isBlockAgency() {
		boolean flag = false;
		Timestamp ts = new Timestamp(Calendar.getInstance().getTime().getTime());
		
		String[] arrTimeBefore 	= ObjectUtil.getArrayStrings(this.houini, ":");
		Calendar calBefore 		= ObjectUtil.getCalendar(ObjectUtil.getYear(), ObjectUtil.getMonth(), ObjectUtil.getDay(), Integer.parseInt(arrTimeBefore[0]), Integer.parseInt(arrTimeBefore[1]));
		Timestamp tsBefore 		= new Timestamp(calBefore.getTime().getTime());
		
		String[] arrTimeAfter 	= ObjectUtil.getArrayStrings(this.houfin, ":");
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
	 * @see pe.cosapi.domain.impl.Branch#getBandef()
	 */
	public String getBandef() {
		return bandef;
	}
	/* (non-Javadoc)
	 * @see pe.cosapi.domain.impl.Branch#setBandef(java.lang.String)
	 */
	public void setBandef(String bandef) {
		this.bandef = bandef;
	}
	/* (non-Javadoc)
	 * @see pe.cosapi.domain.impl.Branch#getBanpju()
	 */
	public String getBanpju() {
		return banpju;
	}
	/* (non-Javadoc)
	 * @see pe.cosapi.domain.impl.Branch#setBanpju(java.lang.String)
	 */
	public void setBanpju(String banpju) {
		this.banpju = banpju;
	}
	/* (non-Javadoc)
	 * @see pe.cosapi.domain.impl.Branch#getBanpna()
	 */
	public String getBanpna() {
		return banpna;
	}
	/* (non-Javadoc)
	 * @see pe.cosapi.domain.impl.Branch#setBanpna(java.lang.String)
	 */
	public void setBanpna(String banpna) {
		this.banpna = banpna;
	}
	/* (non-Javadoc)
	 * @see pe.cosapi.domain.impl.Branch#getCodbra()
	 */
	public String getCodbra() {
		return codbra;
	}
	/* (non-Javadoc)
	 * @see pe.cosapi.domain.impl.Branch#setCodbra(java.lang.String)
	 */
	public void setCodbra(String codbra) {
		this.codbra = codbra;
	}
	/* (non-Javadoc)
	 * @see pe.cosapi.domain.impl.Branch#getDayprgtra()
	 */
	public BigDecimal getDayprgtra() {
		return dayprgtra;
	}
	/* (non-Javadoc)
	 * @see pe.cosapi.domain.impl.Branch#setDayprgtra(java.math.BigDecimal)
	 */
	public void setDayprgtra(BigDecimal dayprgtra) {
		this.dayprgtra = dayprgtra;
	}
	/* (non-Javadoc)
	 * @see pe.cosapi.domain.impl.Branch#getFlgblkbra()
	 */
	public String getFlgblkbra() {
		return flgblkbra;
	}
	/* (non-Javadoc)
	 * @see pe.cosapi.domain.impl.Branch#setFlgblkbra(java.lang.String)
	 */
	public void setFlgblkbra(String flgblkbra) {
		this.flgblkbra = flgblkbra;
	}
	/* (non-Javadoc)
	 * @see pe.cosapi.domain.impl.Branch#getHoufin()
	 */
	public String getHoufin() {
		return houfin;
	}
	/* (non-Javadoc)
	 * @see pe.cosapi.domain.impl.Branch#setHoufin(java.lang.String)
	 */
	public void setHoufin(String houfin) {
		this.houfin = houfin;
	}
	/* (non-Javadoc)
	 * @see pe.cosapi.domain.impl.Branch#getHouini()
	 */
	public String getHouini() {
		return houini;
	}
	/* (non-Javadoc)
	 * @see pe.cosapi.domain.impl.Branch#setHouini(java.lang.String)
	 */
	public void setHouini(String houini) {
		this.houini = houini;
	}
	/* (non-Javadoc)
	 * @see pe.cosapi.domain.impl.Branch#getNumsum()
	 */
	public String getNumsum() {
		return numsum;
	}
	/* (non-Javadoc)
	 * @see pe.cosapi.domain.impl.Branch#setNumsum(java.lang.String)
	 */
	public void setNumsum(String numsum) {
		this.numsum = numsum;
	}
	
}
