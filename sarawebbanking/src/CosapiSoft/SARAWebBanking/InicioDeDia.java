package CosapiSoft.SARAWebBanking;

import pe.cosapi.common.Constante;

/**
 * This type was created in VisualAge.
 */

public class InicioDeDia {
	private java.util.Vector grid = null; //Procesos que se estan ejecutando
	//
	private InicioSesion login;
	//
	private String error = "";
	private String codbra = "";
	private String fecpro = "";
	//
	private int dayprgope = 0;
	private int dayprgtra = 0;
	private int dayprgali = 0;
	private int dayprgmsg = 0;
	//
	private String idSesion = "inicio_de_dia_automatico";
	public static boolean done = true;
/**
 * AyudaDeCampo constructor comment.
 */
public InicioDeDia() {
	super();
}
public void clearGrid() {
	grid = null;
}
protected void consultaDeSaldos() throws Exception {
	/*	System.out.println("");
	System.out.println("Consulta de Saldos");
	System.out.println("");
	String str[] = {//
	//	"0000099700002381", //
	//	"0000029360002785", //
	//	"0000099760000051", //
	//	"0000029300014825", //
	//	"0000099700000237", //
	//	"0000099770000158", //
	//	"0000029300011833", //
	//	"0000029600012506", //
	"0000099700000690", // cta aprobada
	"0000099760000051", //
	"0000029370006776"}; //
	for (int i = 0; i < str.length; i++) {
	if (hasSaldoDisponible(str[i], "000").equals(""))
	System.out.println("Cuenta : " + str[i] + " - Tiene Saldo Disponible");
	else
	System.out.println("Cuenta : " + str[i] + " - NO Tiene Saldo Disponible");
	}*/
	JDatabase jd = new JDatabase();
	JQuery db = new JQuery(jd.getConnection());
	try {
		String query = //
		"Select numdoc, coddoc, paydes, numprdsrc, amoval, datnexpay, numdaycon, stapay " + //
		"From tprgpay " + //
		"Where stapay = '1' Or stapay = '0'";
		db.setQuery(query);
		db.executeQuery();
		java.util.Vector tprgpay = new java.util.Vector(200, 200);
		while (db.getResultSet().next()) {
			java.util.Vector rows = new java.util.Vector(8);
			rows.addElement(db.stringValue(1));
			rows.addElement(db.stringValue(2));
			rows.addElement(db.stringValue(3));
			rows.addElement(db.stringValue(4));
			rows.addElement(db.stringValue(5));
			rows.addElement(db.stringValue(6));
			rows.addElement(db.stringValue(7));
			rows.addElement(db.stringValue(8));
			tprgpay.addElement(rows);
		}
		CosapiSoft.SARAWebBanking.Date pay;
		for (int i = 0; i < tprgpay.size(); i++) {
			String numdoc = ((java.util.Vector) tprgpay.elementAt(i)).elementAt(0).toString();
			String coddoc = ((java.util.Vector) tprgpay.elementAt(i)).elementAt(1).toString();
			String paydes = ((java.util.Vector) tprgpay.elementAt(i)).elementAt(2).toString();
			String numprdsrc = ((java.util.Vector) tprgpay.elementAt(i)).elementAt(3).toString();
			String amoval = ((java.util.Vector) tprgpay.elementAt(i)).elementAt(4).toString();
			String datnexpay = ((java.util.Vector) tprgpay.elementAt(i)).elementAt(5).toString();
			String numdaycon = ((java.util.Vector) tprgpay.elementAt(i)).elementAt(6).toString();
			String stapay = ((java.util.Vector) tprgpay.elementAt(i)).elementAt(7).toString();
			//System.out.println("consultaDeSaldos 3 - tprgpay(" + i + ") = " + ((java.util.Vector) tprgpay.elementAt(i)).toString());
			pay = CosapiSoft.SARAWebBanking.Date.valueOf(datnexpay, "");
			pay.minusDays(new Integer(numdaycon).intValue());
			numprdsrc = Encrypt.unencrypt(numprdsrc, "NHBJD!|SS&XH#YSNO", "PLHRW");
			amoval = Encrypt.unencrypt(amoval, "NHBJD!|SS&XH#YSNO", "PLHRW");
			if (getFecpro().equals(pay.toString())) {
				String msgHost = hasSaldoDisponible(numprdsrc, amoval);
				String check = msgHost.substring(0, 5);
				msgHost = msgHost.substring(5);
				String str = Mensajes.getMessage(Mensajes.SE_REALIZO_CONSULTA_SALDO_PARA_VERIFCAR_FONDOS);
				java.util.StringTokenizer token = new java.util.StringTokenizer(str, "&");
				//				int pos = str.indexOf('&');
				//				int pos1 = str.indexOf('&', pos + 1);
				if (stapay.equals("0")) {
					query = //
					"Insert into tcusmsg values (" + //
					"'" + coddoc + "', " + //
					"'" + numdoc + "', " + //
					"'" + Mensajes.getMessage(Mensajes.SE_REALIZO_CONSULTA_SALDO) + "', " + //
					//					"'" + str.substring(0, pos) + " " + paydes + str.substring(pos + 1, pos1) + datnexpay + " " + Mensajes.getMessage(Mensajes.ESTADO_DE_PAGO_INACTIVA) + "', " + //
					"'" + token.nextElement() + " " + paydes + token.nextElement() + datnexpay + ((check.equals("**B**")) ? (token.nextElement() + msgHost + token.nextElement()) : ".") + " " + Mensajes.getMessage(Mensajes.ESTADO_DE_PAGO_INACTIVA) + "', " + //
					"'" + ((check.equals("**M**")) ? msgHost : "") + "', " + //
					"'0', " + //
					"'" + getFecpro() + "')";
					db.setQuery(query);
					db.executeUpdate();
				} else {
					if (stapay.equals("1")) {
						query = //
						"Insert into tcusmsg values (" + //
						"'" + coddoc + "', " + //
						"'" + numdoc + "', " + //
						"'" + Mensajes.getMessage(Mensajes.SE_REALIZO_CONSULTA_SALDO) + "', " + //
						"'" + token.nextElement() + " " + paydes + token.nextElement() + datnexpay + ((check.equals("**B**")) ? (token.nextElement() + msgHost + token.nextElement()) : ".") + "', " + //
						//						"'" + str.substring(0, pos) + " " + paydes + str.substring(pos + 1, pos1) + datnexpay + "', " + //
						"'" + ((check.equals("**M**")) ? msgHost : "") + "', " + //
						"'0', " + //
						"'" + getFecpro() + "')";
						db.setQuery(query);
						db.executeUpdate();
					}
				}
			}
		}
	} catch (Exception e) {
		error += " - " + Mensajes.getMessage(Mensajes.ERROR_CONSULTA_DE_SALDO);
		throw new Exception("...\t" + getClass().getName() + " - consultaDeSaldos() --> Exception : " + e.getMessage() + "...\t");
	} finally {
		db.close();
		jd.close();
	}
}
/**
 * This method was created in VisualAge.
 * @return String
 * @param numprdsrc java.lang.String
 * @exception java.lang.Exception The exception description.
 */
private synchronized String ejecutaPago(String numdoc, String numprdsrc, String numprdtar, String tipprdtar, String amoval) throws Exception {
	try {
		String trama = "";
		String tarjeta = "";
		int caracterAceptacion = 21;
		String tramaLlegada = "";
		int count = 0;
		// Transacción 0120 - Consulta de Datos Personales
		trama += "AH06"; // TAREA
		trama += "00"; // FILLER
		trama += "0120"; // CODIGO TRANSACCION
		trama += "00"; // FILLER
		trama += "2222"; // TOTAL - 4545 - 9032 - 2222
		trama += "1"; // JORNADA
		trama += "16"; // CANAL - 16
		trama += "00"; // MODO DE OPERACION
		trama += "000"; // USUARIO
		trama += "00"; // PERFIL
		trama += "000000"; // CONSECUTIVO
		trama += "00"; // VERSION
		trama += "000000"; // FILLER
		trama += CString.completeToBeginWithNChars(numprdsrc.trim(), 16, '0'); // NUMERO DE CUENTA - 16
		trama += "01"; // INDICADOR DE TRANSACCION
		tramaLlegada = sendHost(trama);
		if (tramaLlegada != null && tramaLlegada.charAt(caracterAceptacion - 1) == 'B') {
			// System.out.println("Transacción 0120 exitosa");
			/*			System.out.println("FILLER = %" + tramaLlegada.substring(0, 2) + "%");
			System.out.println("TOTAL = %" + tramaLlegada.substring(2, 2 + 4) + "%");
			System.out.println("CONSECUTIVO = %" + tramaLlegada.substring(6, 6 + 6) + "%");
			System.out.println("FILLER = %" + tramaLlegada.substring(12, 12 + 8) + "%");
			System.out.println("CARACTER = %" + tramaLlegada.substring(20, 20 + 1) + "%");
			System.out.println("ULT. MSG = %" + tramaLlegada.substring(21, 21 + 1) + "%");
			System.out.println("NRO: LINEAS = %" + tramaLlegada.substring(22, 22 + 2) + "%");
			System.out.println("COD: MSG = %" + tramaLlegada.substring(24, 24 + 4) + "%");
			System.out.println("FILLER = %" + tramaLlegada.substring(28, 28 + 6) + "%");
			System.out.println("NIT = %" + tramaLlegada.substring(34, 34 + 16) + "%");
			System.out.println("NATUR. JURIDICA = %" + tramaLlegada.substring(50, 50 + 2) + "%");
			System.out.println("NOMBRE = %" + tramaLlegada.substring(52, 52 + 30) + "%");
			System.out.println("1ER APELLIDO = %" + tramaLlegada.substring(82, 82 + 20) + "%");
			System.out.println("2DO APELLIDO = %" + tramaLlegada.substring(102, 102 + 20) + "%");
			System.out.println("OBJETIVO = %" + tramaLlegada.substring(122, 122 + 2) + "%");
			System.out.println("DIRECCION = %" + tramaLlegada.substring(124, 124 + 40) + "%");
			System.out.println("EXTRACTO = %" + tramaLlegada.substring(164, 164 + 2) + "%");
			System.out.println("FECHA DE APERTURA = %" + tramaLlegada.substring(166, 166 + 10) + "%");
			System.out.println("TELEFONO = %" + tramaLlegada.substring(176, 176 + 18) + "%");
			System.out.println("SUBPRODUCTO = %" + tramaLlegada.substring(194, 194 + 4) + "%");
			System.out.println("MANEJO = %" + tramaLlegada.substring(198, 198 + 2) + "%");
			System.out.println("INDICADOR RETENCION = %" + tramaLlegada.substring(200, 200 + 2) + "%");
			System.out.println("TIPO DE EMPRESA = %" + tramaLlegada.substring(202, 202 + 2) + "%");
			System.out.println("CLASE DE EMPRESA = %" + tramaLlegada.substring(204, 204 + 2) + "%");
			System.out.println("SEXO = %" + tramaLlegada.substring(206, 206 + 1) + "%");
			System.out.println("ACTIVIDAD ECONOM. = %" + tramaLlegada.substring(207, 207 + 5) + "%");
			System.out.println("MEDIOS TRANSAC. = %" + tramaLlegada.substring(212, 212 + 16 * 6) + "%");
			System.out.println("CUENTA = %" + tramaLlegada.substring(308, 308 + 16) + "%");
			System.out.println("VIGENCIA = %" + tramaLlegada.substring(324, 324 + 2) + "%");
			System.out.println("FILLER = %" + tramaLlegada.substring(326, 326 + 56) + "%");
			System.out.println("TARJETA = %" + tramaLlegada.substring(382, 382 + 12) + "%");
			System.out.println("ESTADO TARJETA = %" + tramaLlegada.substring(394, 394 + 2) + "%");
			System.out.println("INDIC. 2DA CLAVE = %" + tramaLlegada.substring(396, 396 + 1) + "%");
			System.out.println("FILLER = %" + tramaLlegada.substring(397) + "%");*/
			tarjeta = tramaLlegada.substring(382, 382 + 12).trim(); //383
			// System.out.println("Tarjeta : " + tarjeta);
		} else {
			// System.out.println("Transacción 0120 NO exitosa");
			/*			System.out.println("FILLER = %" + tramaLlegada.substring(0, 2) + "%");
			System.out.println("TOTAL = %" + tramaLlegada.substring(2, 2 + 4) + "%");
			System.out.println("CONSECUTIVO = %" + tramaLlegada.substring(6, 6 + 6) + "%");
			System.out.println("FILLER = %" + tramaLlegada.substring(12, 12 + 8) + "%");
			System.out.println("CARACTER = %" + tramaLlegada.substring(20, 20 + 1) + "%");
			System.out.println("ULT. MSG = %" + tramaLlegada.substring(21, 21 + 1) + "%");
			System.out.println("NRO: LINEAS = %" + tramaLlegada.substring(22, 22 + 2) + "%");
			System.out.println("COD: MSG = %" + tramaLlegada.substring(24, 24 + 4) + "%");
			System.out.println("FILLER = %" + tramaLlegada.substring(28, 28 + 6) + "%");*/
			// System.out.println("MSG = %" + tramaLlegada.substring(34) + "%");
			String mensaje = "**M**" + tramaLlegada.substring(34, 34 + 40);
			return mensaje;
		}
		//
		// Transacción 5682 - Transferencia entre Productos
		trama = "";
		trama += "AH11"; // TAREA
		trama += "00"; // FILLER
		trama += "5682"; // CODIGO TRANSACCION
		trama += "00"; // FILLER
		trama += "2222"; // TOTAL - 4545 - 9032 - 2222
		trama += "1"; // JORNADA
		trama += "16"; // CANAL - 16
		trama += "09"; // MODO DE OPERACION
		trama += "000"; // USUARIO
		trama += "00"; // PERFIL
		trama += "000000"; // CONSECUTIVO
		trama += "00"; // VERSION
		trama += "000000"; // FILLER
		// System.out.println("Tarjeta Debito : " + CString.completeToBeginWithNChars(tarjeta.trim(), 16, '0'));
		trama += CString.completeToBeginWithNChars(tarjeta.trim(), 16, '0'); // TARJETA DEBITO
		trama += ((numprdsrc.charAt(9) == '6') ? "01" : "03"); // INDICADOR ORIGEN
		trama += CString.completeToBeginWithNChars(amoval, 18, '0'); // VALOR
		// System.out.println("Valor : " + CString.completeToBeginWithNChars(amoval, 18, '0'));
		if (tipprdtar.equals("0560")) {
			trama += "01"; // INDICADOR DESTINO - Cuenta Corriente
		} else {
			if (tipprdtar.equals("0550")) {
				trama += "02"; // INDICADOR DESTINO - Da Renta - Cuenta de Ahorros
			} else {
				if (tipprdtar.equals("0570")) {
					trama += "03"; // INDICADOR DESTINO - Fijo Diario - Cuenta de Ahorros
				} else {
					if (tipprdtar.equals("0180")) {
						trama += "04"; // INDICADOR DESTINO - F.M.
					} else {
						if (tipprdtar.equals("0130")) {
							trama += "05"; // INDICADOR DESTINO - Crediexpress - Credito de Consumo
						} else {
							if (tipprdtar.equals("0120")) {
								trama += "05"; // INDICADOR DESTINO - Tarjetas de Credito - Credito de Consumo
							} else {
								if (tipprdtar.equals("0140")) {
									trama += "05"; // INDICADOR DESTINO - Zuana Beach - Credito de Consumo
								} else {
									if (tipprdtar.equals("0810")) {
										trama += "07"; // INDICADOR DESTINO - Titulo Da BuenaVida
									} else {
										trama += "06"; // INDICADOR DESTINO - Otros Bancos
									}
								}
							}
						}
					}
				}
			}
		}
		trama += CString.completeToBeginWithNChars(numprdtar.trim(), 16, '0'); // CUENTA DESTINO
		// System.out.println("Cuenta Destino : " + CString.completeToBeginWithNChars(numprdtar.trim(), 16, '0'));
		trama += CString.fillToBeginWithNChars("", 37, '0'); // BANDA
		trama += CString.fillToBeginWithNChars("", 16, '0'); // PRIMERA CLAVE
		trama += "0000000"; // TALON
		trama += CString.fillToBeginWithNChars("", 16, '0'); // NUMERO DE CEDULA O NIT
		trama += CString.completeToBeginWithNChars(numprdsrc.trim(), 16, '0'); // CUENTA ORIGEN
		// System.out.println("Cuenta Origen : " + CString.completeToBeginWithNChars(numprdsrc.trim(), 16, '0'));
		trama += "0004"; // MOTIVO NOTA DEBITO
		trama += "0000"; // CODIGO BANCO
		trama += "1"; // INDICADOR CLAVE DE TRANSFERENCIA
		trama += CString.fillToBeginWithNChars("", 16, '0'); // CLAVE TRANSFERENCIA
		trama += "0"; // FILA MATRIZ
		trama += "0"; // COLUMNA MATRIZ
		trama += "0"; // INDICADOR EMPRESARIAL
		trama += CString.fillToBeginWithNChars("", 16, '0'); // REFERENCIA EMPRESA
		trama += "00"; // TIPO DE IDENTIFICACION
		tramaLlegada = "";
		tramaLlegada = sendHost(trama);
		//
		if (tramaLlegada != null && tramaLlegada.charAt(caracterAceptacion - 1) == 'B') {
			// System.out.println("Transacción 5682 exitosa");
			/*			System.out.println("FILLER = %" + tramaLlegada.substring(0, 2) + "%");
			System.out.println("TOTAL = %" + tramaLlegada.substring(2, 2 + 4) + "%");
			System.out.println("CONSECUTIVO = %" + tramaLlegada.substring(6, 6 + 6) + "%");
			System.out.println("FILLER = %" + tramaLlegada.substring(12, 12 + 8) + "%");
			System.out.println("CARACTER = %" + tramaLlegada.substring(20, 20 + 1) + "%");
			System.out.println("ULT. MSG = %" + tramaLlegada.substring(21, 21 + 1) + "%");
			System.out.println("NRO: LINEAS = %" + tramaLlegada.substring(22, 22 + 2) + "%");
			System.out.println("COD: MSG = %" + tramaLlegada.substring(24, 24 + 4) + "%");
			System.out.println("FILLER = %" + tramaLlegada.substring(28, 28 + 6) + "%");
			System.out.println("TALON = %" + tramaLlegada.substring(34, 34 + 6) + "%");
			System.out.println("VALOR = %" + tramaLlegada.substring(40, 40 + 18) + "%");
			System.out.println("MSG = %" + tramaLlegada.substring(58) + "%");*/
			String talon = tramaLlegada.substring(34, 34 + 6).trim();
			String valor = tramaLlegada.substring(40, 40 + 18).trim();
			// System.out.println("Talón : " + talon + " Valor : " + valor);
			String mensaje = "**B**" + talon;
			return mensaje;
		} else {
			// System.out.println("Transacción 5682 NO exitosa");
			/*			System.out.println("FILLER = %" + tramaLlegada.substring(0, 2) + "%");
			System.out.println("TOTAL = %" + tramaLlegada.substring(2, 2 + 4) + "%");
			System.out.println("CONSECUTIVO = %" + tramaLlegada.substring(6, 6 + 6) + "%");
			System.out.println("FILLER = %" + tramaLlegada.substring(12, 12 + 8) + "%");
			System.out.println("CARACTER = %" + tramaLlegada.substring(20, 20 + 1) + "%");
			System.out.println("ULT. MSG = %" + tramaLlegada.substring(21, 21 + 1) + "%");
			System.out.println("NRO: LINEAS = %" + tramaLlegada.substring(22, 22 + 2) + "%");
			System.out.println("COD: MSG = %" + tramaLlegada.substring(24, 24 + 4) + "%");
			System.out.println("FILLER = %" + tramaLlegada.substring(28, 28 + 6) + "%");*/
			// System.out.println("MSG = %" + tramaLlegada.substring(34) + "%");
			String mensaje = "**M**" + tramaLlegada.substring(34, 34 + 40);
			return mensaje;
		}
	} catch (Exception e) {
		throw new Exception("...\t" + getClass().getName() + " - ejecutaPago(" + numdoc + "," + numprdsrc + "," + numprdtar + "," + tipprdtar + "," + amoval + ") --> Exception : " + e.getMessage() + "...\t");
	}
}
protected void ejecutaPagoPersonalizado() throws Exception {
	/*	System.out.println("");
	System.out.println("Ejecución de Calendarización");
	System.out.println("");
	String numprdsrc[] = {//
	//"0000099760000051", //
	//	"0000029360002785", //
	//	"0000029300014825", //
	//	"0000099700002381", //
	//	"0000099770000158", //
	//	"0000029300014452", //
	//	"0000099700002964", //
	//	"0000029370006776", //
	//	"0000099760000325", //
	//	"0000029370006784", //
	//"0000029300011833", //
	//"0000029600012506", //
	//	"5471300004104983", //
	//	"3000010001864324", //
	"0000099700000690"}; // Cta orig. aprobada
	String numprdtar[][] = {//
	//	{"3000010001863425", "0130", "932400"}, //
	//	{"0000029360002785", "0560","9365900"}, //
	//	{"0000029300014825", "0550", "2544655"}, //
	//	{"0000099700000237", "0570","33100"}, //
	//	{"0000099770000158", "0550","116400"}, //
	//	{"0000029300011833", "0570", "262400"}, //
	//	{"0000000100004802", "0810","3235400"}, //
	//	{"0000029300014452", "0550", "945400"}, //
	{"0000099760000051", "0560", "688500"}, // cta dest. aprob.
	//	{"0000099700002964","0550","545321"}, //
	{"0000029370006776", "0570", "1487900"} // cta dest. aprob.
	//	{"0000099760000325","0560","2485"}, //
	//	{"0000029370006784","0570","8955"}, //
	//	{"0000000010000473", "0810","4400"}, //
	//	{"0000000010000474", "0810", "398759"}, //
	//	{"3000010001862997", "0130","314648"}, //
	//	{"0000029300012506", "0560","758155"}, //
	//	{"0000099700000690", "0550", "454500"}
	};
	int k = 1;
	for (int i = 0; i < numprdsrc.length; i++) {
	for (int j = 0; j < numprdtar.length; j++, k++) {
	System.out.println("");
	System.out.println("Pago Nro: " + k);
	System.out.println("");
	if (!numprdsrc[i].equals(numprdtar[j][0])) {
	boolean sw = ejecutaPago("", numprdsrc[i], numprdtar[j][0], numprdtar[j][1], numprdtar[j][2]).equals(""); // Ejecucion del Pago programado
	if (sw) {
	System.out.println("Pago Personalizado se ejecutó con éxito");
	} else {
	System.out.println("Pago Personalizado NO se ejecutó con éxito");
	}
	}
	}
	}
	consultaDeSaldos(null);*/
	JDatabase jd = new JDatabase();
	JQuery db = new JQuery(jd.getConnection());
	try {
		String query = //
		"Select numdoc, coddoc, paydes, codent, codcur, idepay, " + //
		"numprdsrc, numprdtar, codser, txtdat1, txtdat2, amoval, " + //
		"datbegpay, datnexpay, ideper, numper, numrep, numdayinf, " + //
		"numdaycon, stapay, tipprdtar, numpayexe " + //
		"From tprgpay " + //
		"Where stapay = '1' And datnexpay = '" + getFecpro() + "'";
		db.setQuery(query);
		db.executeQuery();
		java.util.Vector tprgpay = new java.util.Vector(200, 200);
		while (db.getResultSet().next()) {
			java.util.Vector rows = new java.util.Vector(22);
			rows.addElement(db.stringValue(1));
			rows.addElement(db.stringValue(2));
			rows.addElement(db.stringValue(3));
			rows.addElement(db.stringValue(4));
			rows.addElement(db.stringValue(5));
			rows.addElement(db.stringValue(6));
			rows.addElement(db.stringValue(7));
			rows.addElement(db.stringValue(8));
			rows.addElement(db.stringValue(9));
			rows.addElement(db.stringValue(10));
			rows.addElement(db.stringValue(11));
			rows.addElement(db.stringValue(12));
			rows.addElement(db.stringValue(13));
			rows.addElement(db.stringValue(14));
			rows.addElement(db.stringValue(15));
			rows.addElement(db.stringValue(16));
			rows.addElement(db.stringValue(17));
			rows.addElement(db.stringValue(18));
			rows.addElement(db.stringValue(19));
			rows.addElement(db.stringValue(20));
			rows.addElement(db.stringValue(21));
			rows.addElement(db.stringValue(22));
			tprgpay.addElement(rows);
		}
		CosapiSoft.SARAWebBanking.Date pay;
		for (int i = 0; i < tprgpay.size(); i++) {
			String numdoc = ((java.util.Vector) tprgpay.elementAt(i)).elementAt(0).toString();
			String coddoc = ((java.util.Vector) tprgpay.elementAt(i)).elementAt(1).toString();
			String paydes = ((java.util.Vector) tprgpay.elementAt(i)).elementAt(2).toString();
			String codent = ((java.util.Vector) tprgpay.elementAt(i)).elementAt(3).toString();
			String codcur = ((java.util.Vector) tprgpay.elementAt(i)).elementAt(4).toString();
			String idepay = ((java.util.Vector) tprgpay.elementAt(i)).elementAt(5).toString();
			String numprdsrc = ((java.util.Vector) tprgpay.elementAt(i)).elementAt(6).toString();
			String numprdtar = ((java.util.Vector) tprgpay.elementAt(i)).elementAt(7).toString();
			String codser = ((java.util.Vector) tprgpay.elementAt(i)).elementAt(8).toString();
			String txtdat1 = ((java.util.Vector) tprgpay.elementAt(i)).elementAt(9).toString();
			String txtdat2 = ((java.util.Vector) tprgpay.elementAt(i)).elementAt(10).toString();
			String amoval = ((java.util.Vector) tprgpay.elementAt(i)).elementAt(11).toString();
			String datbegpay = ((java.util.Vector) tprgpay.elementAt(i)).elementAt(12).toString();
			String datnexpay = ((java.util.Vector) tprgpay.elementAt(i)).elementAt(13).toString();
			String ideper = ((java.util.Vector) tprgpay.elementAt(i)).elementAt(14).toString();
			String numper = ((java.util.Vector) tprgpay.elementAt(i)).elementAt(15).toString();
			String numrep = ((java.util.Vector) tprgpay.elementAt(i)).elementAt(16).toString();
			String numdayinf = ((java.util.Vector) tprgpay.elementAt(i)).elementAt(17).toString();
			String numdaycon = ((java.util.Vector) tprgpay.elementAt(i)).elementAt(18).toString();
			String stapay = ((java.util.Vector) tprgpay.elementAt(i)).elementAt(19).toString();
			String tipprdtar = ((java.util.Vector) tprgpay.elementAt(i)).elementAt(20).toString();
			String numpayexe = ((java.util.Vector) tprgpay.elementAt(i)).elementAt(21).toString();
			numprdsrc = Encrypt.unencrypt(numprdsrc, "NHBJD!|SS&XH#YSNO", "PLHRW");
			numprdtar = Encrypt.unencrypt(numprdtar, "NHBJD!|SS&XH#YSNO", "PLHRW");
			amoval = Encrypt.unencrypt(amoval, "NHBJD!|SS&XH#YSNO", "PLHRW");
			if (getFecpro().equals(datnexpay) && stapay.equals("1")) {
				String msgHost = ejecutaPago(numdoc, numprdsrc, numprdtar, tipprdtar, amoval.trim() + "00"); // Ejecucion del Pago programado
				String check = msgHost.substring(0, 5);
				msgHost = msgHost.substring(5);
				String str = Mensajes.getMessage(Mensajes.SE_REALIZO_EJECUCION_PAGO_RESULTADO);
				java.util.StringTokenizer token = new java.util.StringTokenizer(str, "&");
				//				int pos = str.indexOf('&');
				//				int pos1 = str.indexOf('&', pos + 1);
				query = //
				"Insert into tcusmsg values (" + //
				"'" + coddoc + "', " + //
				"'" + numdoc + "', " + //
				"'" + Mensajes.getMessage(Mensajes.SE_REALIZO_EJECUCION_PAGO) + "', " + //
				//				"'" + str.substring(0, pos) + getFecpro() + str.substring(pos + 1, pos1) + ((sw) ? "Exitoso" : "Fallido") + "', " + //
				"'" + token.nextElement() + getFecpro() + token.nextElement() + ((check.equals("**B**")) ? ("Exitoso - Nro de Talón : " + msgHost) : "No Exitoso") + token.nextElement() + "', " + //
				"'" + ((check.equals("**M**")) ? msgHost : "") + "', " + //
				"'0', " + //
				"'" + getFecpro() + "')";
				db.setQuery(query);
				db.executeUpdate();
				pay = CosapiSoft.SARAWebBanking.Date.valueOf(datnexpay, "");
				int num_per = new Integer(numper).intValue();
				int num_payexe = new Integer(numpayexe).intValue();
				num_payexe++;
				int days = 0;
				if (ideper.equals("1"))
					days = num_per;
				if (ideper.equals("2"))
					days = num_per * 7;
				if (ideper.equals("3")) {
					pay = CosapiSoft.SARAWebBanking.Date.valueOf(datbegpay, "");
					pay.addMonths(new Integer(num_per * num_payexe).intValue());
				} else {
					pay.addDays(days);
				}
				int num_rep = (new Integer(numrep).intValue());
				if (num_rep == num_payexe)
					stapay = "2";
				query = //
				"Update tprgpay set " + //
				"datnexpay = '" + pay.toString() + "', " + //
				"stapay = '" + stapay + "', " + //
				"numpayexe = " + num_payexe + " " + //
				"Where coddoc = '" + coddoc + "' " + //
				"And numdoc = '" + numdoc + "'";
				db.setQuery(query);
				db.executeUpdate();
				query = //
				"Insert into hprgpay values(" + //
				"'" + numdoc + "', " + //
				"'" + coddoc + "', " + //
				"'" + paydes + "', " + //
				"'" + codent + "', " + //
				"'" + codcur + "', " + //
				"'" + idepay + "', " + //
				"'" + numprdsrc + "', " + //
				"'" + numprdtar + "', " + //
				"'" + codser + "', " + //
				"'" + txtdat1 + "', " + //
				"'" + txtdat2 + "', " + //
				"'" + amoval + "', " + //
				"'" + datbegpay + "', " + //
				"'" + pay.toString() + "', " + //
				"'" + ideper + "', " + //
				"" + numper + ", " + //
				"" + numrep + ", " + //
				"" + numdayinf + ", " + //
				"" + numdaycon + ", " + //
				"'" + stapay + "', " + //
				"'" + tipprdtar + "', " + //
				"" + num_payexe + " " + //
				")";
				db.setQuery(query);
				db.executeUpdate();
			}
		}
	} catch (Exception e) {
		error += " - " + Mensajes.getMessage(Mensajes.ERROR_EJECUCION_PAGOS_PERSONALIZADOS);
		throw new Exception("...\t" + getClass().getName() + " - ejecutaPagoProgramado() - Msg : " + Mensajes.getMessage(Mensajes.ERROR_EJECUCION_PAGOS_PERSONALIZADOS) + " --> Exception : " + e.getMessage() + "...\t");
	} finally {
		db.close();
		jd.close();
	}
}
protected boolean existeCodbra(JQuery db, String cod) throws Exception {
	try {
		String query = //
		"Select codbra, datpro, dayprgope, dayprgtra, dayprgali, dayprgmsg " + //
		"From "+Constante.ESQUEMA1+".tbrainf " + //
		"Where codbra = '" + cod.trim() + "'";
		db.setQuery(query);
		db.executeQuery();
		if (!db.getResultSet().next()) {
			return false;
		}
		codbra = db.stringValue(1);
		fecpro = db.stringValue(2);
		dayprgope = db.intValue(3);
		dayprgtra = db.intValue(4);
		dayprgali = db.intValue(5);
		dayprgmsg = db.intValue(6);
		return true;
	} catch (Exception e) {
		throw new Exception("...\t" + getClass().getName() + " - existeCodbra --> Exception : " + e.getMessage() + "...\t");
	}
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getCodbra() {
	if (codbra == null)
		codbra = "";
	codbra = codbra.trim();
	if (!codbra.equals(""))
		codbra = CString.completeToBeginWithNChars(codbra, 4, '0');
	return codbra.trim();
}
/**
 * This method was created in VisualAge.
 * @return int
 */
public int getDayprgali() {
	return dayprgali;
}
/**
 * This method was created in VisualAge.
 * @return int
 */
public int getDayprgmsg() {
	return dayprgmsg;
}
/**
 * This method was created in VisualAge.
 * @return int
 */
public int getDayprgope() {
	return dayprgope;
}
/**
 * This method was created in VisualAge.
 * @return int
 */
public int getDayprgtra() {
	return dayprgtra;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getError() {
	return error;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getFecpro() {
	CString str = new CString(fecpro);
	str.arrange();
	return str.toString();
}
/**
 * This method was created in VisualAge.
 * @return java.util.Vector
 */
public java.util.Vector getGrid() {
	if (grid == null)
		grid = new java.util.Vector(10, 10);
	return grid;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getIdSesion() {
	return idSesion;
}
/**
 * This method was created in VisualAge.
 * @return CosapiSoft.SARAWebBanking.InicioSesion
 */
public InicioSesion getLogin() {
	return login;
}
/**
 * This method was created in VisualAge.
 * @return String
 * @param db CosapiSoft.SARAWebBanking.JQuery
 * @param numprdsrc java.lang.String
 * @param amoval java.lang.String
 * @exception java.lang.Exception The exception description.
 */
private String hasSaldoDisponible(String numprdsrc, String amoval) throws Exception {
	try {
		/*
		ctacte :
		0000029360002785
		0000099760000051
		ctaahr
		0000029300014825
		0000099700000237
		0000099770000158
		0000029300011833
		0000029600012506
		0000099700000690
		*/
		String trama = "";
		int posCtacte = 9;
		int caracterAceptacion = 21;
		boolean ctacte = false;
		ctacte = (numprdsrc.length() > 6 && numprdsrc.charAt(posCtacte - 1) == '6');
		if (ctacte) { // Transacción para una Cuenta Corriente
			// System.out.println("Cuenta Corriente : ");
			trama += "BC04"; // TAREA
			trama += "00"; // FILLER
			trama += "5100"; // CODIGO TRANSACCION
			trama += "00"; // FILLER
			trama += "2222"; // TOTAL - 4545 - 9032 - 2222
			trama += "1"; // JORNADA
			trama += "16"; // CANAL - 16
			trama += "00"; // MODO DE OPERACION
			trama += "000"; // USUARIO
			trama += "00"; // PERFIL
			trama += "000000"; // CONSECUTIVO
			trama += "00"; // VERSION
			trama += "000000"; // FILLER
			trama += "01"; // ORIGEN
			trama += CString.completeToBeginWithNChars(numprdsrc.trim(), 16, '0'); // CUENTA - 16
			trama += "000000"; // TALON
		} else { // Transacción para una Cuenta de Ahorros
			// System.out.println("Cuenta de Ahorros : ");
			trama += "AH04"; // TAREA
			trama += "00"; // FILLER
			trama += "5110"; // CODIGO TRANSACCION
			trama += "00"; // FILLER
			trama += "2222"; // TOTAL - 4545 - 9032 - 2222
			trama += "1"; // JORNADA
			trama += "16"; // CANAL - 16
			trama += "01"; // MODO DE OPERACION
			trama += "000"; // USUARIO
			trama += "00"; // PERFIL
			trama += "000000"; // CONSECUTIVO
			trama += "00"; // VERSION
			trama += "000000"; // FILLER
			trama += CString.completeToBeginWithNChars(numprdsrc.trim(), 16, '0'); // CUENTA - 16
		}
		String tramaLlegada = "";
		tramaLlegada = sendHost(trama);
		String saldo = "0";
		if (tramaLlegada != null && tramaLlegada.charAt(caracterAceptacion - 1) == 'B') { // Transacción Exitosa
			if (ctacte) {
/*				System.out.println("FILLER = %" + tramaLlegada.substring(0, 2) + "%");
				System.out.println("TOTAL = %" + tramaLlegada.substring(2, 2 + 4) + "%");
				System.out.println("CONSECUTIVO = %" + tramaLlegada.substring(6, 6 + 6) + "%");
				System.out.println("FILLER = %" + tramaLlegada.substring(12, 12 + 8) + "%");
				System.out.println("CARACTER = %" + tramaLlegada.substring(20, 20 + 1) + "%");
				System.out.println("ULT. MSG = %" + tramaLlegada.substring(21, 21 + 1) + "%");
				System.out.println("NRO: LINEAS = %" + tramaLlegada.substring(22, 22 + 2) + "%");
				System.out.println("COD: MSG = %" + tramaLlegada.substring(24, 24 + 4) + "%");
				System.out.println("FILLER = %" + tramaLlegada.substring(28, 28 + 6) + "%");
				System.out.println("FILLER = %" + tramaLlegada.substring(34, 34 + 20) + "%");
				System.out.println("DISP: EFECT = %" + tramaLlegada.substring(54, 54 + 20) + "%");
				System.out.println("FILLER = %" + tramaLlegada.substring(74, 74 + 20) + "%");
				System.out.println("SALDO CANJE = %" + tramaLlegada.substring(94, 94 + 20) + "%");
				System.out.println("FILLER = %" + tramaLlegada.substring(114, 114 + 20) + "%");
				System.out.println("USADO SOBREGIRO = %" + tramaLlegada.substring(134, 134 + 20) + "%");
				System.out.println("FILLER = %" + tramaLlegada.substring(154, 154 + 20) + "%");
				System.out.println("DISP: SOBREGIRO = %" + tramaLlegada.substring(174, 174 + 20) + "%");
				System.out.println("FILLER = %" + tramaLlegada.substring(194, 194 + 20) + "%");
				System.out.println("INT. SOBREG. CAUSADOS = %" + tramaLlegada.substring(214, 214 + 20) + "%");
				System.out.println("FILLER = %" + tramaLlegada.substring(234, 234 + 20) + "%");
				System.out.println("DIAS EN SOBREG. = %" + tramaLlegada.substring(254) + "%");*/
				saldo = tramaLlegada.substring(55 - 1, 55 - 1 + 20).trim();
			} else {
/*				System.out.println("FILLER = %" + tramaLlegada.substring(0, 2) + "%");
				System.out.println("TOTAL = %" + tramaLlegada.substring(2, 2 + 4) + "%");
				System.out.println("CONSECUTIVO = %" + tramaLlegada.substring(6, 6 + 6) + "%");
				System.out.println("FILLER = %" + tramaLlegada.substring(12, 12 + 8) + "%");
				System.out.println("CARACTER = %" + tramaLlegada.substring(20, 20 + 1) + "%");
				System.out.println("ULT. MSG = %" + tramaLlegada.substring(21, 21 + 1) + "%");
				System.out.println("NRO: LINEAS = %" + tramaLlegada.substring(22, 22 + 2) + "%");
				System.out.println("COD: MSG = %" + tramaLlegada.substring(24, 24 + 4) + "%");
				System.out.println("FILLER = %" + tramaLlegada.substring(28, 28 + 6) + "%");
				System.out.println("FILLER = %" + tramaLlegada.substring(34, 34 + 19) + "%");
				System.out.println("SALDO TOTAL = %" + tramaLlegada.substring(53, 53 + 21) + "%");
				System.out.println("FILLER = %" + tramaLlegada.substring(74, 74 + 19) + "%");
				System.out.println("SALDO DISPON. = %" + tramaLlegada.substring(93, 93 + 21) + "%");
				System.out.println("FILLER = %" + tramaLlegada.substring(114, 114 + 19) + "%");
				System.out.println("SALDO CANJE LOCAL = %" + tramaLlegada.substring(133, 133 + 21) + "%");
				System.out.println("FILLER = %" + tramaLlegada.substring(154, 154 + 19) + "%");
				System.out.println("SALDO CANJE PLAZA. = %" + tramaLlegada.substring(173) + "%");*/
				saldo = tramaLlegada.substring(93, 93 + 21).trim();
			}
			CString s = new CString(saldo.trim());
			s.deleteAll(',');
			s.deleteAll('$');
			double monto1 = new Double(amoval.trim()).doubleValue();
			double monto2 = new Double(s.toString().trim()).doubleValue();
			// System.out.println("Saldo : " + saldo + ((monto2 > monto1) ? " >" : " <=") + " Monto : " + amoval);
			if (monto2 > monto1) {
				return "**B**Saldo suficiente - Saldo : " + saldo + " ";
			} else {
				String mensaje = "**B**Saldo insuficiente - Saldo : " + saldo;
				return mensaje;
			}
		} else { // Transacción NO Exitosa
/*			System.out.println("FILLER = %" + tramaLlegada.substring(0, 2) + "%");
			System.out.println("TOTAL = %" + tramaLlegada.substring(2, 2 + 4) + "%");
			System.out.println("CONSECUTIVO = %" + tramaLlegada.substring(6, 6 + 6) + "%");
			System.out.println("FILLER = %" + tramaLlegada.substring(12, 12 + 8) + "%");
			System.out.println("CARACTER = %" + tramaLlegada.substring(20, 20 + 1) + "%");
			System.out.println("ULT. MSG = %" + tramaLlegada.substring(21, 21 + 1) + "%");
			System.out.println("NRO: LINEAS = %" + tramaLlegada.substring(22, 22 + 2) + "%");
			System.out.println("COD: MSG = %" + tramaLlegada.substring(24, 24 + 4) + "%");
			System.out.println("FILLER = %" + tramaLlegada.substring(28, +28 + 6) + "%");*/
			// System.out.println("MSG = %" + tramaLlegada.substring(34) + "%");
			String mensaje = "**M**" + tramaLlegada.substring(34, 34 + 40);
			return mensaje;
		}
	} catch (Exception e) {
		throw new Exception("...\t" + getClass().getName() + " - hasSaldoDisponible(" + numprdsrc + "," + amoval + ") --> Exception : " + e.getMessage() + "...\t");
	}
}
/**
 * This method was created in VisualAge.
 */
public void init() throws Exception {
	CosapiSoft.SARAWebBanking.JDatabase jd = new CosapiSoft.SARAWebBanking.JDatabase(CosapiSoft.SARAWebBanking.InicioSesion.driver, CosapiSoft.SARAWebBanking.InicioSesion.url, CosapiSoft.SARAWebBanking.InicioSesion.userid, CosapiSoft.SARAWebBanking.InicioSesion.password);
	CosapiSoft.SARAWebBanking.JQuery db = new CosapiSoft.SARAWebBanking.JQuery(jd.getConnection());
	try {
		CosapiSoft.SARAWebBanking.Date pay;
		for (int i = 0; i < 5000; i++) {
			int m = new java.util.Random(i * 919).nextInt() % 12 + 1;
			m = (m < 1) ? 1 : m;
			int d = new java.util.Random((i + 1) * 1759).nextInt() % 27 + 1;
			d = (d < 1) ? 1 : d;
			String datbegpay = "1999" + ((m < 10) ? ("0" + m) : "" + m) + ((d < 10) ? ("0" + d) : "" + d);
			pay = CosapiSoft.SARAWebBanking.Date.valueOf(datbegpay, "");
			int numper = new java.util.Random(3187 * i).nextInt() % 4 + 1;
			numper = (numper < 1) ? 1 : numper;
			int ideper = (i % 3) + 1;
			if (ideper == 3)
				pay.addMonths(numper);
			else
				if (ideper == 2)
					pay.addDays(numper * 7);
				else {
					numper *= (i % 10);
					pay.addDays(numper);
				}
			String datnexpay = pay.toString();
			int numrep = new java.util.Random(199 * (i + 1)).nextInt() % 24 + 5;
			numrep = (numrep < 1) ? 5 : numrep;
			String query = //
			"Insert into tprgpay values(" + //
			"'38572702143" + i + "', " + //
			"'0" + ((i % 9) + 1) + "', " + //
			"'paydes " + i + "', " + //
			"'00001', " + //
			"'001', " + //
			"'1', " + //
			"'3465468468349797', " + //
			"'3479324165481154', " + //
			"'01', " + //
			"'txtdat1', " + //
			"'txtdat2', " + //
			"'6756756754', " + //

			"'" + datbegpay + "', " + //
			"'" + datnexpay + "', " + //

			"'" + ideper + "', " + //
			"" + numper + ", " + //
			"" + numrep + ", " + //
			"" + ((i % 10) + 1) + ", " + //
			"" + ((i % 7) + 1) + ", " + //
			"'1', " + //
			"'" + (((i % 2) == 1) ? "Cuenta_de_Ahorros" : "Cuenta_Corriente") + "', " + //
			"0 " + //
			")";
			db.setQuery(query);
			db.executeUpdate();
			// System.out.println("Query(" + (i + 1) + ") = " + query);
		}
	} catch (Exception e) {
		// System.out.println(e.getMessage());
	} finally {
		db.close();
		jd.close();
	}
}
/**
 * This method was created in VisualAge.
 */
public void loadSucursalVirtual() throws Exception {
	try {
		if (!CosapiSoft.SARAWebManager.InformacionDeAgencias.existeOficinaVirtual()) {
			throw new Exception("...\t" + getClass().getName() + " - loadSucursalVirtual() - Msg: No existe Sucursal Virtual " + "...\t");
		}
		CosapiSoft.SARAWebManager.InformacionDeAgencias tbrainf = new CosapiSoft.SARAWebManager.InformacionDeAgencias();
		tbrainf.loadInfBranch();
		setCodbra(tbrainf.getCodbra());
		
		setDayprgtra(new Integer(tbrainf.getDayprgtra()).intValue());
		
	} catch (Exception e) {
		throw new Exception("...\t" + getClass().getName() + " - loadSucursalVirtual() --> Exception : " + e.getMessage() + "...\t");
	}
}
protected void lockBranch(JQuery db, boolean state) throws Exception {
	try {
		String query = //
		"Update "+Constante.ESQUEMA1+".tbrainf set " + //
		"flgblkbra = '" + ((state) ? "1" : "0") + "' " + //
		"Where codbra = '" + getCodbra() + "' ";
		db.setQuery(query);
		db.executeUpdate();
	} catch (Exception e) {
		java.util.StringTokenizer str = new java.util.StringTokenizer(Mensajes.getMessage(Mensajes.ERROR_BLOQUEO_DESBLOQUEO_SUCURSAL_VIRTUAL), "&");
		String msg = str.nextElement() + ((state) ? "Bloqueo" : "Desbloqueo") + str.nextElement();
		error += " - " + msg;
		throw new Exception("...\t" + getClass().getName() + " - lockBranch(" + state + ") - Msg : " + msg + " --> Exception : " + e.getMessage() + "...\t");
	}
}
/**
 * This method was created in VisualAge.
 * @param args java.lang.String[]
 */
public static void main(String args[]) {
	try {
		//		CosapiSoft.SARAWebManager.InformacionDeAgencias tbrainf = new CosapiSoft.SARAWebManager.InformacionDeAgencias();
		//		tbrainf.loadInfBranch();
		InicioDeDia idia = new InicioDeDia();
		/*		int m = new java.util.Random(3791).nextInt() % 12 + 1;
		m = (m < 1) ? 2 : m;
		int d = new java.util.Random(4519).nextInt() % 27 + 1;
		d = (d < 1) ? 2 : d;
		String datpro = "1999" + ((m < 10) ? ("0" + m) : "" + m) + ((d < 10) ? ("0" + d) : "" + d);
		idia.setFecpro(datpro);
		idia.setCodbra(tbrainf.getCodbra());
		idia.setDayprgope(new Integer(tbrainf.getDayprgope()).intValue());
		idia.setDayprgtra(new Integer(tbrainf.getDayprgtra()).intValue());
		idia.setDayprgali(new Integer(tbrainf.getDayprgali()).intValue());
		idia.setDayprgmsg(new Integer(tbrainf.getDayprgmsg()).intValue());
		javax.servlet.http.HttpServletResponse res = null;
		javax.servlet.http.HttpServletRequest req = null;*/
		//		idia.init();
		idia.start();
	} catch (Exception e) {
		e.printStackTrace();
	}
}
/**
 * This method was created in VisualAge.
 * @param db CosapiSoft.SARAWebBanking.JQuery
 * @exception java.lang.Exception The exception description.
 */
protected void mantTablasHist(JQuery db) throws Exception {
	try {
		CosapiSoft.SARAWebBanking.Date log = CosapiSoft.SARAWebBanking.Date.valueOf(getFecpro(), "");
		log.minusDays(getDayprgope());
		String query = "Delete From hlogope Where datpro < '" + log.toString() + "'";
		db.setQuery(query);
		db.executeUpdate();
		log = CosapiSoft.SARAWebBanking.Date.valueOf(getFecpro(), "");
		log.minusDays(getDayprgtra());
		query = "Delete From "+Constante.ESQUEMA2+".hjoutra Where datpro < '" + log.toString() + "'";
		db.setQuery(query);
		db.executeUpdate();
		log = CosapiSoft.SARAWebBanking.Date.valueOf(getFecpro(), "");
		log.minusDays(getDayprgmsg());
		query = "Delete From hcusmsg Where datmsg < '" + log.toString() + "'";
		db.setQuery(query);
		db.executeUpdate();
		log = CosapiSoft.SARAWebBanking.Date.valueOf(getFecpro(), "");
		log.minusDays(getDayprgali());
		query = "Delete From halidat Where datali < '" + log.toString() + "'";
		db.setQuery(query);
		db.executeUpdate();
	} catch (Exception e) {
		error += " - " + Mensajes.getMessage(Mensajes.ERROR_MANTENIMIENTO_TABLAS_HISTORICAS);
		throw new Exception("...\t" + getClass().getName() + " - mantTablasHist() - Msg : " + Mensajes.getMessage(Mensajes.ERROR_MANTENIMIENTO_TABLAS_HISTORICAS) + " --> Exception : " + e.getMessage() + "...\t");
	}
}
protected void mensajesPreventivos() throws Exception {
	JDatabase jd = new JDatabase();
	JQuery db = new JQuery(jd.getConnection());
	try {
		String query = //
		"Select numdoc, coddoc, paydes, datnexpay, numdayinf, stapay " + //
		"From tprgpay " + //
		"Where stapay = '1' Or stapay = '0'";
		db.setQuery(query);
		db.executeQuery();
		java.util.Vector tprgpay = new java.util.Vector(200, 200);
		while (db.getResultSet().next()) {
			java.util.Vector rows = new java.util.Vector(6);
			rows.addElement(db.stringValue(1));
			rows.addElement(db.stringValue(2));
			rows.addElement(db.stringValue(3));
			rows.addElement(db.stringValue(4));
			rows.addElement(db.stringValue(5));
			rows.addElement(db.stringValue(6));
			tprgpay.addElement(rows);
		}
		CosapiSoft.SARAWebBanking.Date pay;
		for (int i = 0; i < tprgpay.size(); i++) {
			String numdoc = ((java.util.Vector) tprgpay.elementAt(i)).elementAt(0).toString();
			String coddoc = ((java.util.Vector) tprgpay.elementAt(i)).elementAt(1).toString();
			String paydes = ((java.util.Vector) tprgpay.elementAt(i)).elementAt(2).toString();
			String datnexpay = ((java.util.Vector) tprgpay.elementAt(i)).elementAt(3).toString();
			String numdayinf = ((java.util.Vector) tprgpay.elementAt(i)).elementAt(4).toString();
			String stapay = ((java.util.Vector) tprgpay.elementAt(i)).elementAt(5).toString();
			pay = CosapiSoft.SARAWebBanking.Date.valueOf(datnexpay, "");
			pay.minusDays(new Integer(numdayinf).intValue());
			if (getFecpro().equals(pay.toString())) {
				String str1 = Mensajes.getMessage(Mensajes.FALTAN_N_DIAS_PARA_EJECUTARSE_EL_PAGO);
				int pos1 = str1.indexOf('&');
				String str2 = Mensajes.getMessage(Mensajes.FALTAN_N_DIAS_PARA_EJECUTARSE_EL_PAGO_PERSONALIZADO);
				int pos2_1 = str2.indexOf('&');
				int pos2_2 = str2.indexOf('&', pos2_1 + 1);
				int pos2_3 = str2.indexOf('&', pos2_2 + 1);
				if (stapay.equals("0")) {
					query = //
					"Insert into tcusmsg values (" + //
					"'" + coddoc + "', " + //
					"'" + numdoc + "', " + //
					"'" + str1.substring(0, pos1) + numdayinf + str1.substring(pos1 + 1) + "', " + //
					"'" + str2.substring(0, pos2_1) + numdayinf + str2.substring(pos2_1 + 1, pos2_2) + paydes + str2.substring(pos2_2 + 1, pos2_3) + datnexpay + ". " + Mensajes.getMessage(Mensajes.ESTADO_DE_PAGO_INACTIVA) + "', " + //
					"'', " + //
					"'0', " + //
					"'" + getFecpro() + "')";
					db.setQuery(query);
					db.executeUpdate();
				} else {
					if (stapay.equals("1")) {
						query = //
						"Insert into tcusmsg values (" + //
						"'" + coddoc + "', " + //
						"'" + numdoc + "', " + //
						"'" + str1.substring(0, pos1) + numdayinf + str1.substring(pos1 + 1) + "', " + //
						"'" + str2.substring(0, pos2_1) + numdayinf + str2.substring(pos2_1 + 1, pos2_2) + paydes + str2.substring(pos2_2 + 1, pos2_3) + datnexpay + "', " + //
						"'', " + //
						"'0', " + //
						"'" + getFecpro() + "')";
						db.setQuery(query);
						db.executeUpdate();
					}
				}
			}
		}
	} catch (Exception e) {
		error += " - " + Mensajes.getMessage(Mensajes.ERROR_GENERACION_MSG_PREVENTIVOS);
		throw new Exception("...\t" + getClass().getName() + " - mensajesPreventivos() - Msg : " + Mensajes.getMessage(Mensajes.ERROR_GENERACION_MSG_PREVENTIVOS) + " --> Exception: " + e.getMessage() + "...\t");
	} finally {
		db.close();
		jd.close();
	}
}
/**
 * This method was created in VisualAge.
 * @exception java.lang.Exception The exception description.
 */
protected void moveTcusmsgToHcusmsg() throws Exception {
	JDatabase jd = new JDatabase();
	JQuery db = new JQuery(jd.getConnection());
	try {
		String query = //
		"Select coddoc, numdoc, shtdes, londes, txtmen, idesta, datmsg " + //
		"From tcusmsg " + //
		"Where idesta = '1'";
		java.util.Vector table = new java.util.Vector(300, 300);
		db.setQuery(query);
		db.executeQuery();
		while (db.getResultSet().next()) {
			java.util.Vector row = new java.util.Vector(10);
			row.addElement(db.stringValue(1));
			row.addElement(db.stringValue(2));
			row.addElement(db.stringValue(3));
			row.addElement(db.stringValue(4));
			row.addElement(db.stringValue(5));
			row.addElement(db.stringValue(6));
			row.addElement(db.stringValue(7));
			table.addElement(row);
		}
		for (int i = 0; i < table.size(); i++) {
			java.util.Vector row = (java.util.Vector) table.elementAt(i);
			query = //
			"Insert into hcusmsg values (" + //
			"'" + row.elementAt(0).toString() + "', " + //
			"'" + row.elementAt(1).toString() + "', " + //
			"'" + row.elementAt(2).toString() + "', " + //
			"'" + row.elementAt(3).toString() + "', " + //
			"'" + row.elementAt(4).toString() + "', " + //
			"'" + row.elementAt(5).toString() + "', " + //
			"'" + row.elementAt(6).toString() + "') ";
			db.setQuery(query);
			db.executeUpdate();
		}
		query = "Delete From tcusmsg Where idesta = '1'";
		db.setQuery(query);
		db.executeUpdate();
	} catch (Exception e) {
		error += " - " + Mensajes.getMessage(Mensajes.ERROR_MOVER_DATOS_HIST_MSG);
		throw new Exception("...\t" + getClass().getName() + " - moveTcusmsgToHcusmsg() - Msg : " + Mensajes.getMessage(Mensajes.ERROR_MOVER_DATOS_HIST_MSG) + " --> Exception: " + e.getMessage() + "...\t");
	} finally {
		db.close();
		jd.close();
	}
}
/**
 * This method was created in VisualAge.
 * @exception java.lang.Exception The exception description.
 */
protected void moveTjoutraToHjoutra() throws Exception {
	JDatabase jd = new JDatabase();
	JQuery db = new JQuery(jd.getConnection());
	try {
		String query = //
		"Select numlog, datpro, horpro, coddoc, numdoc, " + //
		"cipadr, codchn, modope, codtra, codtrahst, " + //
		"tipprdsrc, numprdsrc, tipprdtar, numprdtar, amotra, codcur, " + //
		"numref, codent, msghst, idetrapro, idetracom " + //
		"From "+Constante.ESQUEMA2+".tjoutra";
		java.util.Vector table = new java.util.Vector(300, 300);
		db.setQuery(query);
		db.executeQuery();
		while (db.getResultSet().next()) {
			java.util.Vector row = new java.util.Vector(22);
			row.addElement(db.stringValue(1));
			row.addElement(db.stringValue(2));
			row.addElement(db.stringValue(3));
			row.addElement(db.stringValue(4));
			row.addElement(db.stringValue(5));
			row.addElement(db.stringValue(6));
			row.addElement(db.stringValue(7));
			row.addElement(db.stringValue(8));
			row.addElement(db.stringValue(9));
			row.addElement(db.stringValue(10));
			row.addElement(db.stringValue(11));
			row.addElement(db.stringValue(12));
			row.addElement(db.stringValue(13));
			row.addElement(db.stringValue(14));
			row.addElement(db.stringValue(15));
			row.addElement(db.stringValue(16));
			row.addElement(db.stringValue(17));
			row.addElement(db.stringValue(18));
			row.addElement(db.stringValue(19));
			row.addElement(db.stringValue(20));
			row.addElement(db.stringValue(21));
			table.addElement(row);
		}
		for (int i = 0; i < table.size(); i++) {
			java.util.Vector row = (java.util.Vector) table.elementAt(i);
			query = //
			"Insert into "+Constante.ESQUEMA2+".hjoutra values (" + //
			"'" + row.elementAt(0).toString() + "', " + //
			"'" + row.elementAt(1).toString() + "', " + //
			"'" + row.elementAt(2).toString() + "', " + //
			"'" + row.elementAt(3).toString() + "', " + //
			"'" + row.elementAt(4).toString() + "', " + //
			"'" + row.elementAt(5).toString() + "', " + //
			"'" + row.elementAt(6).toString() + "', " + //
			"'" + row.elementAt(7).toString() + "', " + //
			"'" + row.elementAt(8).toString() + "', " + //
			"'" + row.elementAt(9).toString() + "', " + //
			"'" + row.elementAt(10).toString() + "', " + //
			"'" + row.elementAt(11).toString() + "', " + //
			"'" + row.elementAt(12).toString() + "', " + //
			"'" + row.elementAt(13).toString() + "', " + //
			"'" + row.elementAt(14).toString() + "', " + //
			"'" + row.elementAt(15).toString() + "', " + //
			"'" + row.elementAt(16).toString() + "', " + //
			"'" + row.elementAt(17).toString() + "', " + //
			"'" + row.elementAt(18).toString() + "', " + //
			"'" + row.elementAt(19).toString() + "', " + //
			"'" + row.elementAt(20).toString() + "') ";
			db.setQuery(query);
			db.executeUpdate();
		}
		query = "Delete from "+Constante.ESQUEMA2+".tjoutra";
		db.setQuery(query);
		db.executeUpdate();
	} catch (Exception e) {
		error += " - " + Mensajes.getMessage(Mensajes.ERROR_MOVER_DATOS_HIST_TRX);
		throw new Exception("...\t" + getClass().getName() + " - moveTjoutraToHjoutra() - Msg : " + Mensajes.getMessage(Mensajes.ERROR_MOVER_DATOS_HIST_TRX) + " --> Exception: " + e.getMessage() + "...\t");
	} finally {
		db.close();
		jd.close();
	}
}
/**
 * This method was created in VisualAge.
 * @exception java.lang.Exception The exception description.
 */
protected void moveTlogopeToHlogope() throws Exception {
	JDatabase jd = new JDatabase();
	JQuery db = new JQuery(jd.getConnection());
	try {
		String query = //
		"Select datpro, txtmod, codusr, horpro, txtope, txttab, bfrrcd, aftrcd " + //
		"From "+Constante.ESQUEMA1+".tlogope";
		java.util.Vector table = new java.util.Vector(300, 300);
		db.setQuery(query);
		db.executeQuery();
		while (db.getResultSet().next()) {
			java.util.Vector row = new java.util.Vector(10);
			row.addElement(db.stringValue(1));
			row.addElement(db.stringValue(2));
			row.addElement(db.stringValue(3));
			row.addElement(db.stringValue(4));
			row.addElement(db.stringValue(5));
			row.addElement(db.stringValue(6));
			row.addElement(db.stringValue(7));
			row.addElement(db.stringValue(8));
			table.addElement(row);
		}
		for (int i = 0; i < table.size(); i++) {
			java.util.Vector row = (java.util.Vector) table.elementAt(i);
			query = //
			"Insert into hlogope values (" + //
			"'" + row.elementAt(0).toString() + "', " + //
			"'" + row.elementAt(1).toString() + "', " + //
			"'" + row.elementAt(2).toString() + "', " + //
			"'" + row.elementAt(3).toString() + "', " + //
			"'" + row.elementAt(4).toString() + "', " + //
			"'" + row.elementAt(5).toString() + "', " + //
			"'" + row.elementAt(6).toString() + "', " + //
			"'" + row.elementAt(7).toString() + "') ";
			db.setQuery(query);
			db.executeUpdate();
		}
		query = "Delete from "+Constante.ESQUEMA1+".tlogope";
		db.setQuery(query);
		db.executeUpdate();
		db.close();
		jd.close();
	} catch (Exception e) {
		error += " - " + Mensajes.getMessage(Mensajes.ERROR_MOVER_DATOS_HIST_OPE);
		throw new Exception("...\t" + getClass().getName() + " - moveTlogopeToHlogope() - Msg: " + Mensajes.getMessage(Mensajes.ERROR_MOVER_DATOS_HIST_OPE) + " --> Exception: " + e.getMessage() + "..\t");
	} finally {
		db.close();
		jd.close();
	}
}
public String nextMensaje(int pos) {
	return getGrid().elementAt(pos).toString();
}
protected void revertirTcusmsg(JQuery db) throws Exception {
	try {
		String query = //
		"Delete from tcusmsg " + //
		"Where datmsg = '" + getFecpro() + "'";
		db.setQuery(query);
		db.executeQuery();
	} catch (Exception e) {
		throw new Exception("...\t" + getClass().getName() + " - revertirTcusmsg() --> Exception: " + e.getMessage() + "...\t");
	}
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 * @param dic java.lang.String
 * @param data java.util.Vector
 */
private synchronized String sendHost(String tramaEnvio) throws Exception {
	CString string = new CString(tramaEnvio);
	string.replace(' ', '^');
	tramaEnvio = string.toString();
	String str = "**00002**";
	String tramaLlegada = "";
	java.io.File f = new java.io.File("/home/user01/prg.c/" + getIdSesion());
	try {
		String prog = "/home/user01/prg.c/sna70 " + getIdSesion() + " " + tramaEnvio;
		// System.out.println("Trama de envio : " + tramaEnvio);
//		System.out.println("Programa : " + prog);
		while (str.equals("**00002**")) {
			// Ejecuta programa en C
			Process p = Runtime.getRuntime().exec(prog);
			p.waitFor();
			// Lee archivo generado por el programa en C
			java.io.FileReader fr = new java.io.FileReader(f);
			char a[] = new char[new Long(f.length()).intValue() + 1];
			fr.read(a);
			tramaLlegada = new String(a);
			if (tramaLlegada != null && tramaLlegada.length() > 21) {
				// System.out.println(((tramaLlegada.charAt(20) == 'B') ? "Transacción Exitosa" : "Transacción Fallida"));
			}
			str = tramaLlegada.substring(0, 9);
			if (str.equals("**00001**")) {
				throw new Exception("...NO HAY COMUNICACION CON HOST...");
			}
		}
		// System.out.println("Trama de llegada : " + tramaLlegada);
		return tramaLlegada;
	} catch (Exception e) {
		throw new Exception("...\t" + getClass().getName() + " - sendHost(" + tramaEnvio + ") --> ERROR AL ENVIAR TRAMA A HOST: " + e.getMessage() + "...\t");
	} finally {
		f.delete();
	}
}
/**
 * This method was created in VisualAge.
 * @exception CosapiSoft.SARAWebBanking.Mail.TransportException The exception description.
 */
/*private synchronized void sendMail(Exception ex) throws CosapiSoft.SARAWebBanking.Mail.TransportException {
	// Create Message and Transport objects
	CosapiSoft.SARAWebBanking.Mail.Message msg = new CosapiSoft.SARAWebBanking.Mail.Message();
	//	Transport tr = new Transport( "yourSmtpHost.net", 25 );
	CosapiSoft.SARAWebBanking.Mail.Transport tr = new CosapiSoft.SARAWebBanking.Mail.Transport("interdesa", 25);

	// Set the FROM address
	//	msg.setFrom( new Address( "Your Name", "your@address.com" ) );
	msg.setFrom(new CosapiSoft.SARAWebBanking.Mail.Address("Inicio de Día", "operad1@interdesa"));

	// Set the TO recipient
	msg.setRecipient(CosapiSoft.SARAWebBanking.Mail.RecipientType.TO, new CosapiSoft.SARAWebBanking.Mail.Address("Administrador del Inicio de Día", "operad2@interdesa"));

	// Set the CC recipient
	msg.setRecipient(CosapiSoft.SARAWebBanking.Mail.RecipientType.CC, new CosapiSoft.SARAWebBanking.Mail.Address("Alexander Bonilla", "abonilla@interdesa"));

	// Set the SUBJECT
	msg.setSubject("Error durante la ejecución del Inicio de Día");

	// Set the message TEXT
	msg.setText("Error durante la ejecución del Inicio de Día : " + ex.getMessage());

	// Send the email. Must catch TransportException Exception.
	try {
		System.out.println("Enviando Mail - Inicio de Día");
		tr.send(msg);
		System.out.println("Mail enviado!!! - Inicio de Día");
	} catch (CosapiSoft.SARAWebBanking.Mail.TransportException te) {
		System.out.println("TransportException: " + te.getMessage());
		throw new CosapiSoft.SARAWebBanking.Mail.TransportException("...\t" + getClass().getName() + " - sendMail()" + " --> TransportException: " + te.getMessage() + "...\t");
	}
}
*//**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setCodbra(String newValue) {
	this.codbra = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue int
 */
public void setDayprgali(int newValue) {
	this.dayprgali = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue int
 */
public void setDayprgmsg(int newValue) {
	this.dayprgmsg = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue int
 */
public void setDayprgope(int newValue) {
	this.dayprgope = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue int
 */
public void setDayprgtra(int newValue) {
	this.dayprgtra = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setError(String newValue) {
	this.error = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setFecpro(String newValue) {
	this.fecpro = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.util.Vector
 */
public void setGrid(java.util.Vector newValue) {
	this.grid = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setIdSesion(String newValue) {
	this.idSesion = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue CosapiSoft.SARAWebBanking.InicioSesion
 */
public void setLogin(InicioSesion newValue) {
	this.login = newValue;
}
/**
 * This method was created in VisualAge.
 * @exception java.lang.Exception The exception description.
 */
public void start() throws Exception {
	done = false;
	if (Mensajes.message.size() == 0)
		Mensajes.loadMessages();
	JDatabase jd = new JDatabase();
	JQuery db = new JQuery(jd.getConnection());
	try {
		error = "";
		loadSucursalVirtual(); //
		revertirTcusmsg(db);
		// System.out.println("Eliminando Mensajes, en caso ya se halla hecho un Inicio de Día previo - " + CString.toDate() + " - " + CString.toTime());
		lockBranch(db, true);
		// System.out.println("Bloqueo de SARA Home Banking - " + CString.toDate() + " - " + CString.toTime());
		//
		Date date = new Date();
		setFecpro(date.toString());
		updateDatpro(db, getFecpro());
		// System.out.println("Actualizando la Fecha de Proceso - " + CString.toDate() + " - " + CString.toTime());
		//
		mensajesPreventivos();
		// System.out.println("Generando Mensajes Preventivos - " + CString.toDate() + " - " + CString.toTime());
		//
		consultaDeSaldos();
		// System.out.println("Realizando Consulta de Saldos - " + CString.toDate() + " - " + CString.toTime());
		//
		ejecutaPagoPersonalizado();
		// System.out.println("Ejecutándose los Pagos Personalizados - " + CString.toDate() + " - " + CString.toTime());
		//
		moveTcusmsgToHcusmsg();
		// System.out.println("Pasando la tabla de Mensajes a su respectivo histórico - " + CString.toDate() + " - " + CString.toTime());
		//
		moveTjoutraToHjoutra();
		// System.out.println("Pasando la tabla de Transacciones a su respectivo histórico - " + CString.toDate() + " - " + CString.toTime());
		//
		moveTlogopeToHlogope();
		// System.out.println("Pasando la tabla de Operaciones a su respectivo histórico - " + CString.toDate() + " - " + CString.toTime());
		//
		mantTablasHist(db);
		// System.out.println("Realizando el mantenimiento de las tablas históricas - " + CString.toDate() + " - " + CString.toTime());
		//
		lockBranch(db, false);
		// System.out.println("Desbloqueo de SARA Home Banking - " + CString.toDate() + " - " + CString.toTime());
	} catch (Exception e) {
		// System.out.println("...\t" + getClass().getName() + " - start() --> " + e.getMessage() + "...\t");
		/*try {
			sendMail(e);
		} catch (CosapiSoft.SARAWebBanking.Mail.TransportException te) {
			throw new Exception("..." + getClass().getName() + " - start() --> Exception : " + e.getMessage() + " - TransportException : " + te.getMessage());
		}*/
		throw new Exception("...\t" + getClass().getName() + " - start() --> " + e.getMessage() + "...\t");
	} finally {
		done = true;
		db.close();
		jd.close();
	}
}
/**
 * This method was created in VisualAge.
 * @exception java.lang.Exception The exception description.
 */
public void start(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse res) throws Exception {
	JDatabase jd = new JDatabase();
	JQuery db = new JQuery(jd.getConnection());
	try {
		error = "";
		revertirTcusmsg(db);
		getGrid().addElement("Eliminando Mensajes, en caso ya se halla hecho un Inicio de Día previo");
		// System.out.println("Eliminando Mensajes, en caso ya se halla hecho un Inicio de Día previo - " + CString.toDate() + " - " + CString.toTime());
		res.sendRedirect("../.." + JspServlet.INICIO_DE_DIA3_JSP);
		//((com.sun.server.http.HttpServiceResponse) res).callPage(JspServlet.INICIO_DE_DIA3_JSP, req);
		res.sendRedirect(JspServlet.INICIO_DE_DIA3_JSP);
		//
		//getGrid().addElement("--- Cargando datos a tprgpay ---");
		//		res.sendRedirect("../../SARAWebBanking/InicioDeDia/InicioDeDia3.jsp");
		//		init();
		//
		getGrid().addElement(Mensajes.getMessage(Mensajes.BLOQUEO_SARA_HOME_BANKING));
		// System.out.println("Bloqueo de SARA Home Banking - " + CString.toDate() + " - " + CString.toTime());
		//((com.sun.server.http.HttpServiceResponse) res).callPage(JspServlet.INICIO_DE_DIA3_JSP, req);
		res.sendRedirect("../.." + JspServlet.INICIO_DE_DIA3_JSP);
		//		lockBranch(db, true);
		//
		getGrid().addElement(Mensajes.getMessage(Mensajes.ACTUALIZACION_FECHA_PROCESO));
		// System.out.println("Actualizando la Fecha de Proceso - " + CString.toDate() + " - " + CString.toTime());
		//((com.sun.server.http.HttpServiceResponse) res).callPage(JspServlet.INICIO_DE_DIA3_JSP, req);
		res.sendRedirect("../.." + JspServlet.INICIO_DE_DIA3_JSP);
		updateDatpro(db, getFecpro());
		//
		getGrid().addElement(Mensajes.getMessage(Mensajes.GENERACION_DE_MENSAJES_PREVENTIVOS));
		// System.out.println("Generando Mensajes Preventivos - " + CString.toDate() + " - " + CString.toTime());
		//((com.sun.server.http.HttpServiceResponse) res).callPage(JspServlet.INICIO_DE_DIA3_JSP, req);
		res.sendRedirect("../.." + JspServlet.INICIO_DE_DIA3_JSP);
		mensajesPreventivos();
		//
		getGrid().addElement(Mensajes.getMessage(Mensajes.CONSULTA_DE_SALDO));
		// System.out.println("Realizando Consulta de Saldos - " + CString.toDate() + " - " + CString.toTime());
		//((com.sun.server.http.HttpServiceResponse) res).callPage(JspServlet.INICIO_DE_DIA3_JSP, req);
		res.sendRedirect("../.." + JspServlet.INICIO_DE_DIA3_JSP);
		consultaDeSaldos();
		//
		getGrid().addElement(Mensajes.getMessage(Mensajes.EJECUCION_PAGOS_PERSONALIZADOS));
		// System.out.println("Ejecutándose los Pagos Personalizados - " + CString.toDate() + " - " + CString.toTime());
		//((com.sun.server.http.HttpServiceResponse) res).callPage(JspServlet.INICIO_DE_DIA3_JSP, req);
		res.sendRedirect("../.." + JspServlet.INICIO_DE_DIA3_JSP);
		ejecutaPagoPersonalizado();
		//
		getGrid().addElement(Mensajes.getMessage(Mensajes.PROCES_LOG_DE_MENSAJES));
		// System.out.println("Pasando la tabla de Mensajes a su respectivo histórico - " + CString.toDate() + " - " + CString.toTime());
		//((com.sun.server.http.HttpServiceResponse) res).callPage(JspServlet.INICIO_DE_DIA3_JSP, req);
		res.sendRedirect("../.." + JspServlet.INICIO_DE_DIA3_JSP);
		moveTcusmsgToHcusmsg();
		//
		getGrid().addElement(Mensajes.getMessage(Mensajes.PROCES_LOG_DE_TRANSACCIONES));
		// System.out.println("Pasando la tabla de Transacciones a su respectivo histórico - " + CString.toDate() + " - " + CString.toTime());
		//((com.sun.server.http.HttpServiceResponse) res).callPage(JspServlet.INICIO_DE_DIA3_JSP, req);
		res.sendRedirect("../.." + JspServlet.INICIO_DE_DIA3_JSP);
		moveTjoutraToHjoutra();
		//
		getGrid().addElement(Mensajes.getMessage(Mensajes.PROCES_LOG_DE_OPERACIONES));
		// System.out.println("Pasando la tabla de Operaciones a su respectivo histórico - " + CString.toDate() + " - " + CString.toTime());
		//((com.sun.server.http.HttpServiceResponse) res).callPage(JspServlet.INICIO_DE_DIA3_JSP, req);
		res.sendRedirect("../.." + JspServlet.INICIO_DE_DIA3_JSP);
		moveTlogopeToHlogope();
		//
		getGrid().addElement(Mensajes.getMessage(Mensajes.MANTENIMIENTO_DE_TABLAS_HISTORICAS));
		// System.out.println("Realizando el mantenimiento de las tablas históricas - " + CString.toDate() + " - " + CString.toTime());
		//((com.sun.server.http.HttpServiceResponse) res).callPage(JspServlet.INICIO_DE_DIA3_JSP, req);
		res.sendRedirect("../.." + JspServlet.INICIO_DE_DIA3_JSP);
		mantTablasHist(db);
		//
		getGrid().addElement(Mensajes.getMessage(Mensajes.MANTENIMIENTO_DE_TABLAS_TEMPORALES));
		// System.out.println("Mantenimiento de Tablas Temporales - " + CString.toDate() + " - " + CString.toTime());
		//((com.sun.server.http.HttpServiceResponse) res).callPage(JspServlet.INICIO_DE_DIA3_JSP, req);
		//
		getGrid().addElement(Mensajes.getMessage(Mensajes.DESBLOQUEO_SARA_HOME_BANKING));
		// System.out.println("Desbloqueo de SARA Home Banking - " + CString.toDate() + " - " + CString.toTime());
		//((com.sun.server.http.HttpServiceResponse) res).callPage(JspServlet.INICIO_DE_DIA3_JSP, req);
		res.sendRedirect("../.." + JspServlet.INICIO_DE_DIA3_JSP);
		lockBranch(db, false);
		//
		getGrid().addElement(Mensajes.getMessage(Mensajes.FINALIZACION_INICIO_DE_DIA));
		// System.out.println("Finalización del Inicio de Día - " + CString.toDate() + " - " + CString.toTime());
		//((com.sun.server.http.HttpServiceResponse) res).callPage(JspServlet.INICIO_DE_DIA3_JSP, req);
		res.sendRedirect("../.." + JspServlet.INICIO_DE_DIA3_JSP);
	} catch (Exception e) {
		// System.out.println("...\t" + getClass().getName() + " - start() --> " + e.getMessage() + "...\t");
		/*try {
			sendMail(e);
		} catch (CosapiSoft.SARAWebBanking.Mail.TransportException te) {
			throw new Exception("...\t" + getClass().getName() + " - start() --> Exception : " + e.getMessage() + " - TransportException : " + te.getMessage() + "...\t");
		}*/
	} finally {
		db.close();
		jd.close();
	}
}
protected void updateDatpro(JQuery db, String datpro) throws Exception {
	try {
		String query = //
		"Update "+Constante.ESQUEMA1+".tbrainf set " + //
		"datpro = '" + datpro + "' " + //
		"Where codbra = '" + getCodbra() + "' ";
		db.setQuery(query);
		db.executeUpdate();
	} catch (Exception e) {
		error += " - " + Mensajes.getMessage(Mensajes.ERROR_MODIFICAR_FECHA_DE_PROCESO);
		throw new Exception("...\t" + getClass().getName() + " - updateDatpro(" + datpro + ") - Msg : " + Mensajes.getMessage(Mensajes.ERROR_MODIFICAR_FECHA_DE_PROCESO) + " --> Exception : " + e.getMessage() + "...\t");
	}
}
}