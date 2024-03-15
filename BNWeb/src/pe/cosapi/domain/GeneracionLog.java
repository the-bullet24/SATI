
package pe.cosapi.domain;

import java.io.Serializable;


public interface GeneracionLog extends Operacion,  Serializable {
	
	
	public String getNumLog();				//F32_NUMLOG
	public String getFechaLog();			//F32_FECHALOG
	public String getFechaCronoOp();		//F32_FECHA_CRONO_OP
	public String getCanal();				//F32_CANAL
	public String getIpDireccion();			//F32_IP_DIRECCION
	public String getMac();					//F32_MAC
	public String getNumTrans();			//F32_NUM_TRANS
	public String getCicClte();				//F32_CIC_CLTE
	public String getTipoDocumento();		//F32_TIPO_DOCUMENTO
	public String getNumDocumento();		//F32_NUM_DOCUMENTO
	public String getTipoTarjeta();			//F32_TIPO_TARJETA
	public String getNumTarjeta();			//F32_NUM_TARJETA
	public String getEstado();				//F32_ESTADO
	public String getFlagError();			//F32_FLAG_ERROR
	public String getCodApp();				//F32_COD_APP
	public String getCodRet();				//F32_COD_RET
	public String getToken();				//F32_TOKEN
	public String getEmail();				//F32_EMAIL_OUT
	public String getBenef();				//F32_BENEF_OUT
	public String getTipoToken();			//F32_TIPO_TOKEN
	public String getFechaLogica();			//F32_FECHA_LOGICA
		
}