package pe.cosapi.domain.impl;

import java.io.Serializable;
import java.math.BigDecimal;

import pe.cosapi.common.Constante;
import pe.cosapi.common.DAOFactory;
import pe.cosapi.domain.MsgComunication;

public class MsgComunicationImpl implements MsgComunication, Serializable {
	private String 		codDic; 
	private String 		txtMsgCom;
	private String 		numBegPos;
	private String 		numLonMsg;
	private String 		txtFor;
	private BigDecimal 	numLonDic;
	private String 		ideDic;
	
	public MsgComunication[] getMsgComunication(String trx) throws Exception{
		//if(Constante.VER_LOG) System.out.println("trx="+trx);
		return DAOFactory.getGeneraDAO().getMsgComunication(trx);
	}
	
	/* (non-Javadoc)
	 * @see pe.cosapi.domain.impl.MsgComunication#getCodDic()
	 */
	public String getCodDic() {
		return codDic;
	}
	/* (non-Javadoc)
	 * @see pe.cosapi.domain.impl.MsgComunication#setCodDic(java.lang.String)
	 */
	public void setCodDic(String codDic) {
		this.codDic = codDic;
	}
	/* (non-Javadoc)
	 * @see pe.cosapi.domain.impl.MsgComunication#getIdeDic()
	 */
	public String getIdeDic() {
		return ideDic;
	}
	/* (non-Javadoc)
	 * @see pe.cosapi.domain.impl.MsgComunication#setIdeDic(java.lang.String)
	 */
	public void setIdeDic(String ideDic) {
		this.ideDic = ideDic;
	}
	/* (non-Javadoc)
	 * @see pe.cosapi.domain.impl.MsgComunication#getNumBegPos()
	 */
	public String getNumBegPos() {
		return numBegPos;
	}
	/* (non-Javadoc)
	 * @see pe.cosapi.domain.impl.MsgComunication#setNumBegPos(java.lang.String)
	 */
	public void setNumBegPos(String numBegPos) {
		this.numBegPos = numBegPos;
	}
	/* (non-Javadoc)
	 * @see pe.cosapi.domain.impl.MsgComunication#getNumLonDic()
	 */
	public BigDecimal getNumLonDic() {
		return numLonDic;
	}
	/* (non-Javadoc)
	 * @see pe.cosapi.domain.impl.MsgComunication#setNumLonDic(java.math.BigDecimal)
	 */
	public void setNumLonDic(BigDecimal numLonDic) {
		this.numLonDic = numLonDic;
	}
	/* (non-Javadoc)
	 * @see pe.cosapi.domain.impl.MsgComunication#getNumLonMsg()
	 */
	public String getNumLonMsg() {
		return numLonMsg;
	}
	/* (non-Javadoc)
	 * @see pe.cosapi.domain.impl.MsgComunication#setNumLonMsg(java.lang.String)
	 */
	public void setNumLonMsg(String numLonMsg) {
		this.numLonMsg = numLonMsg;
	}
	/* (non-Javadoc)
	 * @see pe.cosapi.domain.impl.MsgComunication#getTxtFor()
	 */
	public String getTxtFor() {
		return txtFor;
	}
	/* (non-Javadoc)
	 * @see pe.cosapi.domain.impl.MsgComunication#setTxtFor(java.lang.String)
	 */
	public void setTxtFor(String txtFor) {
		this.txtFor = txtFor;
	}
	/* (non-Javadoc)
	 * @see pe.cosapi.domain.impl.MsgComunication#getTxtMsgCom()
	 */
	public String getTxtMsgCom() {
		return txtMsgCom;
	}
	/* (non-Javadoc)
	 * @see pe.cosapi.domain.impl.MsgComunication#setTxtMsgCom(java.lang.String)
	 */
	public void setTxtMsgCom(String txtMsgCom) {
		this.txtMsgCom = txtMsgCom;
	}
	
	
}
