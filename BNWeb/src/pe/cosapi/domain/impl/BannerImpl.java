package pe.cosapi.domain.impl;

import java.io.Serializable;
import java.sql.Timestamp;

import pe.cosapi.common.DAOFactory;
import pe.cosapi.domain.Banner;

public class BannerImpl implements Banner, Serializable {
	private String 		typper;
	private Timestamp 	datbeg;
	private Timestamp 	datend;
	private String 		filnam;
	
	
	public Banner getBannerByDate(String tipopersona,Timestamp fecha) throws Exception{
		return DAOFactory.getGeneraDAO().getBannerByDate(tipopersona, fecha);
	}
	/* (non-Javadoc)
	 * @see pe.cosapi.domain.impl.Banner#getDatbeg()
	 */
	public Timestamp getDatbeg() {
		return datbeg;
	}
	/* (non-Javadoc)
	 * @see pe.cosapi.domain.impl.Banner#setDatbeg(java.lang.String)
	 */
	public void setDatbeg(Timestamp datbeg) {
		this.datbeg = datbeg;
	}
	/* (non-Javadoc)
	 * @see pe.cosapi.domain.impl.Banner#getDatend()
	 */
	public Timestamp getDatend() {
		return datend;
	}
	/* (non-Javadoc)
	 * @see pe.cosapi.domain.impl.Banner#setDatend(java.lang.String)
	 */
	public void setDatend(Timestamp datend) {
		this.datend = datend;
	}
	/* (non-Javadoc)
	 * @see pe.cosapi.domain.impl.Banner#getFilnam()
	 */
	public String getFilnam() {
		return filnam;
	}
	/* (non-Javadoc)
	 * @see pe.cosapi.domain.impl.Banner#setFilnam(java.lang.String)
	 */
	public void setFilnam(String filnam) {
		this.filnam = filnam;
	}
	/* (non-Javadoc)
	 * @see pe.cosapi.domain.impl.Banner#getTypper()
	 */
	public String getTypper() {
		return typper;
	}
	/* (non-Javadoc)
	 * @see pe.cosapi.domain.impl.Banner#setTypper(java.lang.String)
	 */
	public void setTypper(String typper) {
		this.typper = typper;
	}
		
}
