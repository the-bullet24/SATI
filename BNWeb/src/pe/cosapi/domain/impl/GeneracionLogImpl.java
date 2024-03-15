/*
 * Creado el 26/02/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de codigo - Plantillas de código
 */
package pe.cosapi.domain.impl;

import java.io.Serializable;
import java.math.BigDecimal;

import pe.cosapi.common.ArrayRuleException;
import pe.cosapi.common.Constante;
import pe.cosapi.common.ConstanteError;
import pe.cosapi.common.DAOFactory;
import pe.cosapi.common.ObjectUtil;
import pe.cosapi.domain.GeneracionLog;
import pe.cosapi.domain.Itf;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author cosapi_ati04
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class GeneracionLogImpl extends OperacionImpl  implements GeneracionLog,Serializable {
	
	
	
	private Long p_F32_NUMLOG;				//F32_NUMLOG
	private String fechaLog;			//F32_FECHALOG
	private Date p_F32_FECHA_CRONO_OP;		//F32_FECHA_CRONO_OP
	private String p_F32_CANAL;				//F32_CANAL
	private String p_F32_IP_DIRECCION;			//F32_IP_DIRECCION
	private String p_F32_MAC;					//F32_MAC
	private String p_F32_NUM_TRANS;			//F32_NUM_TRANS
	private Long p_F32_CIC_CLTE;				//F32_CIC_CLTE
	private String p_F32_TIPO_DOCUMENTO;		//F32_TIPO_DOCUMENTO
	private String p_F32_NUM_DOCUMENTO;		//F32_NUM_DOCUMENTO
	private String p_F32_TIPO_TARJETA;			//F32_TIPO_TARJETA
	private String p_F32_NUM_TARJETA;			//F32_NUM_TARJETA
	private String p_F32_ESTADO;				//F32_ESTADO
	private String p_F32_FLAG_ERROR;			//F32_FLAG_ERROR
	private String p_F32_COD_APP;				//F32_COD_APP
	private String p_F32_COD_RET;				//F32_COD_RET
	private String p_F32_TOKEN;				//F32_TOKEN
	private String p_F32_EMAIL_OUT;				//F32_EMAIL_OUT
	private String p_F32_BENEF_OUT;				//F32_BENEF_OUT
	private String p_F32_TIPO_TOKEN;			//F32_TIPO_TOKEN
	private String p_F32_FECHA_LOGICA;			//F32_FECHA_LOGICA
	private String p_F32_CONSTANCIA_SATI;		//F32_CONSTANCIA_SATI
	
	private String pc_coderr;			//pc_coderr OUT VARCHAR2,
	private String pc_msgerr;			//   pc_msgerr OUT VARCHAR2
	
	
	/**
	 * 
	 */
	public GeneracionLogImpl() {

		// TODO Apendice de constructor generado automáticamente
	}



	public String getFechaLog() {
		return fechaLog;
	}


	public void setFechaLog(String fechaLog) {
		this.fechaLog = fechaLog;
	}


	



	@Override
	public String getNumLog() {
		// TODO Auto-generated method stub
		return null;
	}











	@Override
	public String getFechaCronoOp() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String getCicClte() {
		// TODO Auto-generated method stub
		return null;
	}


	public Date getP_F32_FECHA_CRONO_OP() {
		return p_F32_FECHA_CRONO_OP;
	}



	public void setP_F32_FECHA_CRONO_OP(Date p_F32_FECHA_CRONO_OP) {
		this.p_F32_FECHA_CRONO_OP = p_F32_FECHA_CRONO_OP;
	}

	public String getP_F32_CANAL() {
		return p_F32_CANAL;
	}

	public void setP_F32_CANAL(String p_F32_CANAL) {
		this.p_F32_CANAL = p_F32_CANAL;
	}


	public String getP_F32_IP_DIRECCION() {
		return p_F32_IP_DIRECCION;
	}







	public void setP_F32_IP_DIRECCION(String p_F32_IP_DIRECCION) {
		this.p_F32_IP_DIRECCION = p_F32_IP_DIRECCION;
	}







	public String getP_F32_MAC() {
		return p_F32_MAC;
	}







	public void setP_F32_MAC(String p_F32_MAC) {
		this.p_F32_MAC = p_F32_MAC;
	}







	public String getP_F32_NUM_TRANS() {
		return p_F32_NUM_TRANS;
	}







	public void setP_F32_NUM_TRANS(String p_F32_NUM_TRANS) {
		this.p_F32_NUM_TRANS = p_F32_NUM_TRANS;
	}



	public Long getP_F32_CIC_CLTE() {
		return p_F32_CIC_CLTE;
	}



	public void setP_F32_CIC_CLTE(Long p_F32_CIC_CLTE) {
		this.p_F32_CIC_CLTE = p_F32_CIC_CLTE;
	}

	
	public Long getP_F32_NUMLOG() {
		return p_F32_NUMLOG;
	}



	public void setP_F32_NUMLOG(Long p_F32_NUMLOG) {
		this.p_F32_NUMLOG = p_F32_NUMLOG;
	}




	public String getP_F32_TIPO_DOCUMENTO() {
		return p_F32_TIPO_DOCUMENTO;
	}







	public void setP_F32_TIPO_DOCUMENTO(String p_F32_TIPO_DOCUMENTO) {
		this.p_F32_TIPO_DOCUMENTO = p_F32_TIPO_DOCUMENTO;
	}







	public String getP_F32_NUM_DOCUMENTO() {
		return p_F32_NUM_DOCUMENTO;
	}







	public void setP_F32_NUM_DOCUMENTO(String p_F32_NUM_DOCUMENTO) {
		this.p_F32_NUM_DOCUMENTO = p_F32_NUM_DOCUMENTO;
	}







	public String getP_F32_TIPO_TARJETA() {
		return p_F32_TIPO_TARJETA;
	}







	public void setP_F32_TIPO_TARJETA(String p_F32_TIPO_TARJETA) {
		this.p_F32_TIPO_TARJETA = p_F32_TIPO_TARJETA;
	}







	public String getP_F32_NUM_TARJETA() {
		return p_F32_NUM_TARJETA;
	}







	public void setP_F32_NUM_TARJETA(String p_F32_NUM_TARJETA) {
		this.p_F32_NUM_TARJETA = p_F32_NUM_TARJETA;
	}







	public String getP_F32_ESTADO() {
		return p_F32_ESTADO;
	}







	public void setP_F32_ESTADO(String p_F32_ESTADO) {
		this.p_F32_ESTADO = p_F32_ESTADO;
	}







	public String getP_F32_FLAG_ERROR() {
		return p_F32_FLAG_ERROR;
	}







	public void setP_F32_FLAG_ERROR(String p_F32_FLAG_ERROR) {
		this.p_F32_FLAG_ERROR = p_F32_FLAG_ERROR;
	}







	public String getP_F32_COD_APP() {
		return p_F32_COD_APP;
	}







	public void setP_F32_COD_APP(String p_F32_COD_APP) {
		this.p_F32_COD_APP = p_F32_COD_APP;
	}







	public String getP_F32_COD_RET() {
		return p_F32_COD_RET;
	}







	public void setP_F32_COD_RET(String p_F32_COD_RET) {
		this.p_F32_COD_RET = p_F32_COD_RET;
	}







	public String getP_F32_TOKEN() {
		return p_F32_TOKEN;
	}







	public void setP_F32_TOKEN(String p_F32_TOKEN) {
		this.p_F32_TOKEN = p_F32_TOKEN;
	}







	public String getP_F32_EMAIL_OUT() {
		return p_F32_EMAIL_OUT;
	}







	public void setP_F32_EMAIL_OUT(String p_F32_EMAIL_OUT) {
		this.p_F32_EMAIL_OUT = p_F32_EMAIL_OUT;
	}







	public String getP_F32_BENEF_OUT() {
		return p_F32_BENEF_OUT;
	}







	public void setP_F32_BENEF_OUT(String p_F32_BENEF_OUT) {
		this.p_F32_BENEF_OUT = p_F32_BENEF_OUT;
	}







	public String getP_F32_TIPO_TOKEN() {
		return p_F32_TIPO_TOKEN;
	}







	public void setP_F32_TIPO_TOKEN(String p_F32_TIPO_TOKEN) {
		this.p_F32_TIPO_TOKEN = p_F32_TIPO_TOKEN;
	}





	public String getP_F32_FECHA_LOGICA() {
		return p_F32_FECHA_LOGICA;
	}



	public void setP_F32_FECHA_LOGICA(String p_F32_FECHA_LOGICA) {
		this.p_F32_FECHA_LOGICA = p_F32_FECHA_LOGICA;
	}



	@Override
	public String getIpDireccion() {
		// TODO Auto-generated method stub
		return null;
	}







	@Override
	public String getMac() {
		// TODO Auto-generated method stub
		return null;
	}







	@Override
	public String getNumTrans() {
		// TODO Auto-generated method stub
		return null;
	}







	@Override
	public String getTipoDocumento() {
		// TODO Auto-generated method stub
		return null;
	}







	@Override
	public String getNumDocumento() {
		// TODO Auto-generated method stub
		return null;
	}







	@Override
	public String getTipoTarjeta() {
		// TODO Auto-generated method stub
		return null;
	}







	@Override
	public String getNumTarjeta() {
		// TODO Auto-generated method stub
		return null;
	}







	@Override
	public String getEstado() {
		// TODO Auto-generated method stub
		return null;
	}







	@Override
	public String getFlagError() {
		// TODO Auto-generated method stub
		return null;
	}







	@Override
	public String getCodApp() {
		// TODO Auto-generated method stub
		return null;
	}







	@Override
	public String getCodRet() {
		// TODO Auto-generated method stub
		return null;
	}







	@Override
	public String getToken() {
		// TODO Auto-generated method stub
		return null;
	}







	@Override
	public String getEmail() {
		// TODO Auto-generated method stub
		return null;
	}







	@Override
	public String getBenef() {
		// TODO Auto-generated method stub
		return null;
	}







	public String getPc_coderr() {
		return pc_coderr;
	}







	public void setPc_coderr(String pc_coderr) {
		this.pc_coderr = pc_coderr;
	}







	public String getPc_msgerr() {
		return pc_msgerr;
	}







	public void setPc_msgerr(String pc_msgerr) {
		this.pc_msgerr = pc_msgerr;
	}



	@Override
	public String getFechaLogica() {
		// TODO Auto-generated method stub
		return null;
	}



	public String getP_F32_CONSTANCIA_SATI() {
		return p_F32_CONSTANCIA_SATI;
	}



	public void setP_F32_CONSTANCIA_SATI(String p_F32_CONSTANCIA_SATI) {
		this.p_F32_CONSTANCIA_SATI = p_F32_CONSTANCIA_SATI;
	}

	
	
	
	
	
	
	


	
	
	
	
}
