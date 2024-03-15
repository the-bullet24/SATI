package pe.cosapi.domain;

import java.io.Serializable;
import java.math.BigDecimal;

public interface MsgComunication extends Serializable {

	public abstract String getCodDic();

	public abstract void setCodDic(String codDic);

	public abstract String getIdeDic();

	public abstract void setIdeDic(String ideDic);

	public abstract String getNumBegPos();

	public abstract void setNumBegPos(String numBegPos);

	public abstract BigDecimal getNumLonDic();

	public abstract void setNumLonDic(BigDecimal numLonDic);

	public abstract String getNumLonMsg();

	public abstract void setNumLonMsg(String numLonMsg);

	public abstract String getTxtFor();

	public abstract void setTxtFor(String txtFor);

	public abstract String getTxtMsgCom();

	public abstract void setTxtMsgCom(String txtMsgCom);

}