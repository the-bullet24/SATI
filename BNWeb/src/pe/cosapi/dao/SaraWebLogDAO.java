/*
 * Creado el 21/03/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package pe.cosapi.dao;

import pe.cosapi.domain.Journal;
import pe.cosapi.domain.impl.JournalImpl;

public interface SaraWebLogDAO {
	public abstract Journal[] getArrayJournal(String numeroCuenta, String trx) throws Exception;

	public abstract JournalImpl[] getArrayJournalByNroCuenta(String numeroCuenta) throws Exception;
	 
	public void insertarJournal(Journal journal) throws Exception;
	
	public void insertarJournal1(Journal journal) throws Exception;
	
	public void insertarJournal2(Journal journal) throws Exception;

	public void actualizarJournal(Journal journal) throws Exception;

	public Long getSecuencia() throws Exception;
	
	public void setEliminaDatosDuplicados() throws Exception;
    
    public void setMueveDiarioElectronico() throws Exception;
    
    public void setEliminaDiarioElectronico() throws Exception;
}