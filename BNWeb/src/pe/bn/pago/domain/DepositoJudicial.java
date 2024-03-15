package pe.bn.pago.domain;

public interface DepositoJudicial {
	
	public abstract String getHora();
	
	public abstract void setHora(String hora);
	
	public abstract void setFecha(String fecha);
	
	public abstract String getFecha();
	
	public abstract void setNumero(String numero);
	
	public abstract String getNumero();
	
	public abstract void setNumUnico(String numUnico);
	
	public abstract String getNumUnico();
	
	public abstract void setNombreDemandado1(String nombreDemandado1);
	
	public abstract String getNombreDemandado1();
		
	public abstract void setNombreDemandado2(String nombreDemandado2);
	
	public abstract String getNombreDemandado2();
	
	public abstract void setNombreDemandante1(String nombreDemandante1);
	
	public abstract String getNombreDemandante1();
	
	public abstract void setNombreDemandante2(String nombreDemandante2);
	
	public abstract String getNombreDemandante2();
	
	public abstract void setNombreCorte1(String nombreCorte1);
	
	public abstract String getNombreCorte1();
	
	public abstract void setNombreCorte2(String nombreCorte2);
	
	public abstract String getNombreCorte2();
	
	public abstract void setNombreJuzgado(String nombreJuzgado);
	
	public abstract String getNombreJuzgado();
	
	public abstract void setNombreDelito(String nombreDelito);
	
	public abstract String getNombreDelito();
	
	public abstract void setCodJuzgadoPJ(String codJuzgadoPJ);
	
	public abstract String getCodJuzgadoPJ();
	
	public abstract void setCodUnicoExp(String codUnicoExp);
	
	public abstract String getCodUnicoExp();
	
	public abstract void setTipoDocumento(String tipoDocumento);
	
	public abstract String getTipoDocumento();
	
	public abstract void setNumDocumento(String numDocumento);
	
	public abstract String getNumDocumento();
	
	public abstract void setNombreBeneficiario1(String nombreBeneficiario1);
	
	public abstract String getNombreBeneficiario1();
	
	public abstract void setNombreBeneficiario2(String nombreBeneficiario2);
	
	public abstract String getNombreBeneficiario2();
	
	public abstract void setDescMoneda(String descMoneda);
	
	public abstract String getDescMoneda();
	
	public abstract void setImporteLiq(String importeLiq);
	
	public abstract String getImporteLiq();
	
	public abstract void setCodMoneda(String codMoneda);
	
	public abstract String getCodMoneda();
	
	public abstract void setDescTipoDocumento(String descTipoDocumento);
	
	public abstract String getDescTipoDocumento();

}
