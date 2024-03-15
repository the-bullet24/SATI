package pe.cosapi.dao.impl;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import oracle.jdbc.OraclePreparedStatement;

import pe.bn.transferencia.action.TransferenciaMBAction;
import pe.com.bn.sati.common.LoggerSati;
import pe.cosapi.common.Constante;
import pe.cosapi.common.ConstanteSesion;
import pe.cosapi.common.DAOAbstract;
import pe.cosapi.common.ObjectUtil;
import pe.cosapi.dao.SaraWebLogDAO;
import pe.cosapi.domain.Journal;
import pe.cosapi.domain.impl.JournalImpl;

public class SaraWebLogIbatis extends DAOAbstract implements SaraWebLogDAO{
	
	private static LoggerSati log3 = LoggerSati.getInstance(SaraWebLogIbatis.class.getName());
	
	public Journal[] getArrayJournal(String numeroCuenta,String trx) throws Exception{
		Map input = new HashMap();
		input.put("numeroCuenta", numeroCuenta);
		input.put("codigoTransaccion", trx);
		
		List lstJournal = getSqlMapClientTemplate().queryForList("sqlMapSaraWebLog.getArrayJournal",input);
		return (Journal[])lstJournal.toArray(new Journal[0]);
	}
	
	public JournalImpl[] getArrayJournalByNroCuenta(String numeroCuenta) throws Exception{
		List lstJournal = getSqlMapClientTemplate().queryForList("sqlMapSaraWebLog.getArrayJournalByNroCuenta",numeroCuenta);
		return (JournalImpl[])lstJournal.toArray(new JournalImpl[0]);
	}
	
	public void setEliminaDatosDuplicados() throws Exception {
		getSqlMapClientTemplate().delete("sqlMapSaraWebLog.setEliminaDuplicados");
	}
	
	/* (sin Javadoc)
	 * @see pe.cosapi.dao.GeneralDAO#setFlagBloqueoAgenciaVirtual(boolean)
	 */
	public void setMueveDiarioElectronico() throws Exception {
		getSqlMapClientTemplate().insert("sqlMapSaraWebLog.setActualizaDiarioHistorico");
	}
	
	/* (sin Javadoc)
	 * @see pe.cosapi.dao.GeneralDAO#setFlagBloqueoAgenciaVirtual(boolean)
	 */
	public void setEliminaDiarioElectronico() throws Exception {
		getSqlMapClientTemplate().delete("sqlMapSaraWebLog.setBorraDiarioElectronico");
	}
	
	/* (sin Javadoc)
	 * @see pe.cosapi.dao.SaraWebLogDAO#insertarJournal(pe.cosapi.domain.Journal)
	 */
	public void insertarJournal(Journal journal) throws Exception {
		
		Connection conn = this.getConnection();
		
		try {
			
			String insert  =
				"insert into "+ Constante.BN_SWB_LOG+"."+"tjoutra" +
				"  (numlog," +
				"   datpro," +
				"   horpro," +
				"   coddoc," +
				"   numdoc," +
				"	cipadr," +
				"	codtra," +
				"	codtrahst," +
				"	tipprdsrc," +
				"	numprdsrc," +
				"	tipprdtar," +
				"	numprdtar," +
				"	amotra," +
				"	codcur," +
				"	numref," +
				"	codent," +
				"	msghst," +
				"	idetrapro," +
				"	idetracom," +
				"	amotxn," +
				"	baltra," +
				"	msgerror," +
				"	typper," +
				"	clobcons," +
				"	numope," +
				"	nrocta," +
				"	flgcom," +
				"	nomben," +
				"	flgcha," +	
				"	codser," +
				"	flgerror)" +	
				"	values" +
				"	(?," +
				"	?," +
				"	?," +
				"	?," +
				"	?," +
				"	?," +
				"	?," +
				"	?," +
				"	?," +
				"	?," +
				"	?," +
				"	?," +
				"	?," +
				"	?," +
				"	?," +
				"	?," +
				"	?," +
				"	?," +
				"	?," +
				"	?," +
				"	?," +
				"	?," +
				"	?," +
				"	?," +
				"	?," +
				"	?," +
				"	?," +
				"	?," +
				"	?," +
				"	?," +
				"	?)";
		
			int i = 1;
			PreparedStatement ps = conn.prepareStatement(insert);
			ps.setString(i++,journal.getNumlog());
			ps.setTimestamp(i++,journal.getDatpro());
			ps.setString(i++,journal.getHorpro());
			ps.setString(i++,journal.getCoddoc());
			ps.setString(i++,journal.getNumdoc());
			ps.setString(i++,journal.getCipadr());
			ps.setString(i++,journal.getCodtra());
			ps.setString(i++,journal.getCodtrahst());
			ps.setString(i++,journal.getTipprdsrc());
			
			ps.setString(i++,journal.getNumprdsrc());
			ps.setString(i++,journal.getTipprdtar());
			ps.setString(i++,journal.getNumprdtar());
			ps.setBigDecimal(i++,journal.getAmotra());
			ps.setString(i++,journal.getCodcur());
			ps.setString(i++,journal.getNumref());
			ps.setString(i++,journal.getCodent());
			ps.setString(i++,journal.getMsghst());
			ps.setString(i++,journal.getIdetrapro());
			ps.setString(i++,journal.getIdetracom());
			ps.setBigDecimal(i++,journal.getAmotxn());
			ps.setBigDecimal(i++,journal.getBaltra());
			ps.setString(i++,journal.getMsgerror());
			ps.setString(i++,journal.getTypper());
		
			
			if(journal.getConstancia()!=null){
				File file = File.createTempFile("temp",".tmp");
				BufferedWriter out = new BufferedWriter(new FileWriter(file));
		        out.write(journal.getConstancia());
		        out.close();		
				FileInputStream fis = new FileInputStream(file);
				ps.setAsciiStream(i++, fis, fis.available());
				fis.close();
				file.delete();			
			}
			else{
				ps.setNull(i++, Types.CLOB);
			}
			ps.setString(i++,journal.getNumope());	
			if (journal.getNrocta() != null) {
			    journal.setNrocta(ObjectUtil.replaceChar(journal.getNrocta(),'-',""));
			}
			ps.setString(i++,journal.getNrocta());
			ps.setString(i++,ConstanteSesion.CODIGO_COMPANIA);
			ps.setString(i++,journal.getNomben());	
			ps.setString(i++,journal.getFlgcha());
			ps.setString(i++,journal.getCodser());
			ps.setString(i++,journal.getFlgerror());
			
			ps.execute();

			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			conn.close();
		}
		
	}
	
	public void insertarJournal1(Journal journal)  throws Exception {
		
	
			
			String insert  =
				"insert into "+ Constante.BN_SWB_LOG+"."+"tjoutra" +
				"  (numlog," +
				"   datpro," +
				"   horpro," +
				"	cipadr," +
				"	codtra," +
				"	numprdsrc," +
				"	clobcons," +
				"	nrocta," +
				"	nomben," +
				"	msghst," +
				"	flgerror) " +	
				"	values" +
				"	(?," +
				"	?," +
				"	?," +
				"	?," +
				"	?," +
				"	?," +
				"	?," +
				"	?," +
				"	?," +
				"	?," +
				"	?)";
			Connection conn = this.getConnection();
			int i = 1;
			PreparedStatement ps = conn.prepareStatement(insert);

			ps.setString(i++,journal.getNumlog());
			ps.setTimestamp(i++,journal.getDatpro());
			ps.setString(i++,journal.getHorpro());
			ps.setString(i++,journal.getCipadr());
			ps.setString(i++,journal.getCodtra());
						
			ps.setString(i++,journal.getNumprdsrc());
			
			InputStream is = new ByteArrayInputStream(journal.getConstancia().getBytes());
			ps.setAsciiStream(i++, is, is.available());
			ps.setString(i++,journal.getNrocta());
			
			ps.setString(i++,journal.getNomben());	
			ps.setString(i++,journal.getMsghst());
			ps.setString(i++,journal.getFlgerror());
			
			ps.execute();
			
			conn.close();
			
	
		
	}
	
	
	public void insertarJournal2(Journal journal)  throws Exception {
		
		
		
		String insert  =
			"insert into "+ Constante.BN_SWB_LOG+"."+"tjoutra" +
			"  (numlog," +
			"   datpro," +
			"   horpro," +
			"	cipadr," +
			"	codtra," +
			"	numprdsrc," +
			"	nrocta," +
			"	nomben," +
			"	msghst," +
			"	flgerror) " +	
			"	values" +
			"	(?," +
			"	?," +
			"	?," +
			"	?," +
			"	?," +
			"	?," +
			"	?," +
			"	?," +
			"	?," +
			"	?)";
		Connection conn = this.getConnection();
		int i = 1;
		PreparedStatement ps = conn.prepareStatement(insert);

		ps.setString(i++,journal.getNumlog());
		ps.setTimestamp(i++,journal.getDatpro());
		ps.setString(i++,journal.getHorpro());
		ps.setString(i++,journal.getCipadr());
		ps.setString(i++,journal.getCodtra());
					
		ps.setString(i++,journal.getNumprdsrc());
		
		ps.setString(i++,journal.getNrocta());
		
		ps.setString(i++,journal.getNomben());	
		ps.setString(i++,journal.getMsghst());
		ps.setString(i++,journal.getFlgerror());
		
		ps.execute();
		
		conn.close();
		

	
}

	
	
	public void actualizarJournal(Journal journal) throws Exception {
						
		Connection conn = this.getConnection();
		
		try {
			
//			System.out.println(journal.toString());
//			log3.debug("", journal.toString(), "1");
			String update = "update "+Constante.BN_SWB_LOG+"."+"tjoutra " +
			"set coddoc    = ?," +
			" numdoc    = ?," +
			" cipadr    = ?," +
			" codtra    = ?," +
			" codtrahst = ?," +
			" tipprdsrc = ?," +
			" numprdsrc = ?," +
			" tipprdtar = ?," +
			" numprdtar = ?," +
			" amotra    = ?," +
			" codcur    = ?," +
			" numref    = ?," +
			" codent    = ?," +
			" msghst    = ?," +
			" idetrapro = ?," +
			" idetracom = ?," +
			" amotxn    = ?," +
			" baltra    = ?," +
			" msgerror  = ?," +
			" typper    = ?," +
			" clobcons  = ?," +
			" numope    = ?," +
			" flgcom    = ?," +
			" flgcha    = ?," +
			" nomben    = ?," +
			" nrocta    = ?," +//cambiando
			" flgerror  = ?, " +
			" codser  = ? " +
			" where numlog = ?";		
	
	int i = 1;
	PreparedStatement ps = conn.prepareStatement(update);
	
	ps.setString(i++,journal.getCoddoc());
	//log.info("journal.getCoddoc()*--->" +journal.getCoddoc());
	ps.setString(i++,journal.getNumdoc());
	//log.info("journal.getNumdoc()*--->" +journal.getNumdoc());
	ps.setString(i++,journal.getCipadr());
	//log.info("journal.getCipadr()*--->" +journal.getCipadr());
	ps.setString(i++,journal.getCodtra());
	//log.info("journal.getCodtra()*--->" +journal.getCodtra());
	ps.setString(i++,journal.getCodtrahst());
	//log.info("journal.getCodtrahst()*--->" +journal.getCodtrahst());
	ps.setString(i++,journal.getTipprdsrc());
	//log.info("journal.getTipprdsrc()*--->" +journal.getTipprdsrc());
	ps.setString(i++,journal.getNumprdsrc());
	//log.info("journal.getNumprdsrc()*--->" +journal.getNumprdsrc());
	ps.setString(i++,journal.getTipprdtar());
	//log.info("journal.getTipprdtar()*--->" +journal.getTipprdtar());
	ps.setString(i++,journal.getNumprdtar());
	//log.info("journal.getNumprdtar()*--->" +journal.getNumprdtar());
	ps.setBigDecimal(i++,journal.getAmotra());
	//log.info("journal.getAmotra()*--->" +journal.getAmotra());
	ps.setString(i++,journal.getCodcur());
	//log.info("journal.getCodcur()*--->" +journal.getCodcur());
	ps.setString(i++,journal.getNumref());
	//log.info("journal.getNumref()*--->" +journal.getNumref());
	ps.setString(i++,journal.getCodent());
	//log.info("journal.getCodent()*--->" +journal.getCodent());
	ps.setString(i++,journal.getMsghst());
	//log.info("journal.getMsghst()*--->" +journal.getMsghst());
	ps.setString(i++,journal.getIdetrapro());
	//log.info("journal.getIdetrapro()*--->" +journal.getIdetrapro());
	ps.setString(i++,journal.getIdetracom());
	//log.info("journal.getIdetracom()*--->" +journal.getIdetracom());
	ps.setBigDecimal(i++,journal.getAmotxn());
	//log.info("journal.getAmotxn()*--->" +journal.getAmotxn());
	ps.setBigDecimal(i++,journal.getBaltra());
	//log.info("journal.getBaltra()*--->" +journal.getBaltra());
	ps.setString(i++,journal.getMsgerror());
	//log.info("journal.getMsgerror()*--->" +journal.getMsgerror());
	ps.setString(i++,journal.getTypper());
	//log.info("journal.getTypper()*--->" +journal.getTypper());

	
	if(journal.getConstancia()!=null){
		File file = File.createTempFile("temp",".tmp");
		BufferedWriter out = new BufferedWriter(new FileWriter(file));
        out.write(journal.getConstancia());
        out.close();		
		FileInputStream fis = new FileInputStream(file);
		ps.setAsciiStream(i++, fis, fis.available());
		fis.close();
		file.delete();	
		
//		if(journal.getCodtrahst().equals("")){}
//	
//		  String dateFormat="HHmmss.SSS";
//		  java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(dateFormat);
//		
//		byte[] byteArray = journal.getConstancia().getBytes("UTF-8");
//		
//		printWithName(byteArray,journal.getNumprdsrc()+journal.getCodtrahst()+sdf.format(java.util.Calendar.getInstance().getTime()));
//		
	}
	else{
		ps.setNull(i++, Types.CLOB);
	}
	ps.setString(i++,journal.getNumope());	
	//log.info("journal.getNumope()*--->" +journal.getNumope());
	ps.setString(i++,journal.getFlgcom());
	//log.info("journal.getFlgcom()*--->" +journal.getFlgcom());
	ps.setString(i++,journal.getFlgcha());
	//log.info("journal.getFlgcha()*--->" +journal.getFlgcha());
	ps.setString(i++,journal.getNomben());
	//log.info("journal.getNomben()*--->" +journal.getNomben());
	if (journal.getNrocta() != null) {
	    journal.setNrocta(ObjectUtil.replaceChar(journal.getNrocta(),'-',""));
	}
	ps.setString(i++,journal.getNrocta());
	//log.info("journal.getNrocta()*--->" +journal.getNrocta());
	ps.setString(i++,journal.getFlgerror());
	
	//log.info("journal.getCodser()*--->" +journal.getCodser());
	ps.setString(i++,journal.getCodser());
	//log.info("journal.getFlgerror()*--->" +journal.getFlgerror());
	ps.setString(i++,journal.getNumlog());
	//log.info("journal.getNumlog()*--->" +journal.getNumlog());
	ps.execute();
	
	/**
	if(log.isDebugEnabled())
	{
		if(Constante.VER_LOG) 
		    log.info("Intentando actualizar "+journal.getNumlog());
	}
	*/
	conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			conn.close();
		}
		
	}

	/* (sin Javadoc)
	 * @see pe.cosapi.dao.SaraWebLogDAO#getSecuencia()
	 */
	public Long getSecuencia() throws Exception {		
		return (Long)getSqlMapClientTemplate().queryForObject("sqlMapSaraWebLog.getSecuencia");
	}
	
	private void printWithName(byte[] trama,String nombre){
	    File f = null;
	    
	    if (Constante.CONSTANTE_OS.equals("1")){
		    //String archivo = "/home/logs/"+nombre+"__"+ObjectUtil.getCodigoTRX()+".txt";
	        String archivo = "/home/constancias/"+nombre+".txt";
			f = new File(archivo);
		}
		else if (Constante.CONSTANTE_OS.equals("2") || Constante.CONSTANTE_OS.equals("3")){
		    String archivo = "/home/constancias/"+nombre+".txt";
		    f = new File(archivo);
		}
		try {
			FileOutputStream st = new FileOutputStream(f);
			st.write(trama);
			st.close();
			f = null;
			st = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}