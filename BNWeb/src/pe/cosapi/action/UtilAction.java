/*
 * Creado el 02/03/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de codigo - Plantillas de codigo
 */
package pe.cosapi.action;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Serializable;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import pe.bn.afiliacion.domain.Afiliacion;
import pe.bn.cldinamica.action.form.CardConfSaveResponse;
import pe.bn.cldinamica.action.form.CardRequestSave;
import pe.bn.cldinamica.action.form.CardResponse;
import pe.bn.cldinamica.action.form.CardSaveRequest;
import pe.bn.cldinamica.action.form.DataInvitaciones;
import pe.bn.cldinamica.action.form.DataListas;
import pe.bn.cldinamica.action.form.DataTDC;
import pe.bn.cldinamica.action.form.InfoCardResponse;
import pe.bn.cldinamica.action.form.InvitacionesResponse;
import pe.bn.cldinamica.action.form.MensajesActDesTDC;
import pe.bn.cldinamica.action.form.MensajesTDC;
import pe.bn.cldinamica.action.form.ProductoResponse;
import pe.bn.cldinamica.action.form.SaldoResponse;
import pe.bn.listener.ConstanteParametros;
import pe.bn.listener.Parametro;
import pe.bn.listener.Util;
import pe.bn.pago.domain.PagoTasas;
import pe.com.bn.common.Funciones;
import pe.com.bn.common.sati.bean.CardRequest;
import pe.com.bn.common.sati.bean.InfoCardRequest;
import pe.com.bn.common.sati.bean.Parameters;
import pe.com.bn.common.sati.bean.TokenSmsActivacionRequest;
import pe.com.bn.common.sati.bean.TokenSmsMigracionRequest;
import pe.com.bn.common.sati.bean.TokenSmsRequest;
import pe.com.bn.sati.common.LoggerSati;
import pe.cosapi.action.form.MailForm;
import pe.cosapi.common.ArrayRuleException;
import pe.cosapi.common.Constante;
import pe.cosapi.common.ConstanteError;
import pe.cosapi.common.ConstanteSesion;
import pe.cosapi.common.Mail;
import pe.cosapi.common.MailFactory;
import pe.cosapi.common.ObjectUtil;
import pe.cosapi.common.Refrendo;
import pe.cosapi.common.RefrendoFactory;
import pe.cosapi.domain.Usuario;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.html.simpleparser.HTMLWorker;
import com.lowagie.text.html.simpleparser.StyleSheet;
import com.lowagie.text.pdf.PdfWriter;

/**
 * @author cosapi_ati04
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class UtilAction extends GrandActionAbstract implements Serializable {
	private static LoggerSati log3 = LoggerSati.getInstance(UtilAction.class
			.getName());

	//Logger logger = Logger.getLogger(UtilAction.class);
	//static Map map;

	/* (sin Javadoc)
	 * @see pe.cosapi.common.GrandActionAbstract#iniciar(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward iniciar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Apendice de metodo generado automaticamente
		return null;
	}

	public ActionForward solicitarRefrendo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String idObjeto = request.getParameter("idObjeto");

		String variableSesion = request.getParameter("variableSesion");
		Refrendo refrendo = RefrendoFactory.getRefrendoInstance(idObjeto);
		Usuario usuario = (Usuario) request.getSession().getAttribute(
				ConstanteSesion.USUARIO_EN_SESION);
		Object objeto = request.getSession().getAttribute(variableSesion);
		HashMap map = new HashMap(cargarVar(request, objeto));
		refrendo.procesar(map);
		String resultado = refrendo.getResultado();

		int inicio = resultado.lastIndexOf("<BODY>") + 6;
		resultado = resultado.substring(inicio);
		int fin = resultado.lastIndexOf("</BODY>");
		resultado = resultado.substring(0, fin);
		request.setAttribute("texto", resultado);
		return mapping.findForward("refrendo");
	}

	public ActionForward verConstancia(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String idObjeto = request.getParameter("idObjeto");
		String variableSesion = request.getParameter("variableSesion");
		Refrendo refrendo = RefrendoFactory.getRefrendoInstance(idObjeto);
		Usuario usuario = (Usuario) request.getSession().getAttribute(
				ConstanteSesion.USUARIO_EN_SESION);
		Object objeto = request.getSession().getAttribute(variableSesion);
		HashMap map = new HashMap(cargarVar(request, objeto));
		refrendo.procesar(map);
		String resultado = refrendo.getResultado();

		int inicio = resultado.lastIndexOf("<BODY>") + 6;
		resultado = resultado.substring(inicio);
		int fin = resultado.lastIndexOf("</BODY>");
		resultado = resultado.substring(0, fin);
		request.setAttribute("texto", resultado);
		return mapping.findForward("constancia");
	}

	public ActionForward verMail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String idObjeto = request.getParameter("idObjeto");

		String variableSesion = request.getParameter("variableSesion");
		Mail mail = MailFactory.getMailInstance(idObjeto);
		Usuario usuario = (Usuario) request.getSession().getAttribute(
				ConstanteSesion.USUARIO_EN_SESION);
		Object objeto = request.getSession().getAttribute(variableSesion);

		//map.put("usuario",usuario);

		HashMap map = new HashMap(cargarVar(request, objeto));

		MailForm mailForm = (MailForm) form;
		mailForm.setMailDetalle(map);
		request.getSession().setAttribute(ConstanteSesion.MAIL_CONSTANCIA,
				mailForm);

		String resultado = ObjectUtil.replaceIlegalCharacterToSendMail(mail
				.getCuerpo(map));
		int inicio = resultado.lastIndexOf("<BODY>") + 6;
		resultado = resultado.substring(inicio);
		int fin = resultado.lastIndexOf("</BODY>");
		resultado = resultado.substring(0, fin);
		request.setAttribute("texto", resultado);
		request.setAttribute("idObjeto", idObjeto);
		request.setAttribute("variableSesion", variableSesion);
		request.setAttribute("asunto", mail.getSubject());

		return mapping.findForward("mail");
	}

	public ActionForward enviarMail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String idObjeto = request.getParameter("idObjeto");
		String variableSesion = request.getParameter("variableSesion");

		Mail mail = MailFactory.getMailInstance(idObjeto);
		Usuario usuario = (Usuario) request.getSession().getAttribute(
				ConstanteSesion.USUARIO_EN_SESION);
		Object objeto = request.getSession().getAttribute(variableSesion);

		//map.put("usuario",usuario);

		HashMap map = new HashMap(cargarVar(request, objeto));

		MailForm mailForm = (MailForm) form;

		mail.setTo(mailForm.getTo());
		mail.setCC(mailForm.getCc());
		String resultado = ObjectUtil.replaceIlegalCharacterToSendMail(mail
				.getCuerpo(map));
		int inicio = resultado.lastIndexOf("<BODY>") + 6;
		resultado = resultado.substring(inicio);
		int fin = resultado.lastIndexOf("</BODY>");
		resultado = resultado.substring(0, fin);
		request.setAttribute("texto", resultado);
		request.setAttribute("idObjeto", idObjeto);
		request.setAttribute("variableSesion", variableSesion);
		request.setAttribute("asunto", mail.getSubject());
		request.setAttribute("to", mail.getTo());
		request.setAttribute("cc", mail.getCC());

		try {
			//mailForm.setMailDetalle(map);
			ConstanteSesion.MAIL_DETALLE = map;
			//System.out.println("***--- UTILACTION.ENVIARMAIL - ConstanteSesion.MAIL_DETALLE:"+ConstanteSesion.MAIL_DETALLE);

			//System.out.println("***--- UTILACTION.ENVIARMAIL - map:"+map);
			request.getSession().setAttribute(ConstanteSesion.MAIL_CONSTANCIA,
					mailForm);

			mail.sendMailConstancia(map, request);
		} catch (Exception e) {
			log3.error(e, Constante.ERR_LOGICA_NEGOCIO, "");
			e.printStackTrace();
			//throw new ArrayRuleException(ConstanteError.GENERICO,"VERIFIQUE LA(S) DIRECCION(ES) DE CORREO INGRESADA(S) O INTENTE MAS TARDE");
			throw new ArrayRuleException(ConstanteError.GENERICO,
					"Verifique la(s) direccion(es) de correo ingresada(s) o intente mas tarde");
		}
		return mapping.findForward("mailEnviado");
	}

	public ActionForward enviarMail1(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String idObjeto = request.getParameter("idObjeto");
		String variableSesion = request.getParameter("variableSesion");

		Mail mail = MailFactory.getMailInstance(idObjeto);
		//Usuario usuario = (Usuario)request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		Object objeto = request.getSession().getAttribute(variableSesion);

		//map.put("usuario",usuario);

		HashMap map = new HashMap(cargarVar(request, objeto));

		MailForm mailForm = (MailForm) form;

		mail.setTo(mailForm.getTo());
		mail.setCC(mailForm.getCc());
		mail.setNota(ObjectUtil.replaceIlegalCharacterToSendMail(request
				.getParameter("nota")));

		String resultado = ObjectUtil.replaceIlegalCharacterToSendMail(mail
				.getCuerpo(map));
		int inicio = resultado.lastIndexOf("<BODY>") + 6;
		resultado = resultado.substring(inicio);
		int fin = resultado.lastIndexOf("</BODY>");
		resultado = resultado.substring(0, fin);

		request.setAttribute("texto", resultado);
		request.setAttribute("idObjeto", idObjeto);
		request.setAttribute("variableSesion", variableSesion);
		request.setAttribute("asunto", mail.getSubject());
		request.setAttribute("to", mail.getTo());
		request.setAttribute("cc", mail.getCC());

		try {
			//mailForm.setMailDetalle(map);
			ConstanteSesion.MAIL_DETALLE = map;
			//System.out.println("***--- UTILACTION.ENVIARMAIL - ConstanteSesion.MAIL_DETALLE:"+ConstanteSesion.MAIL_DETALLE);

			//System.out.println("***--- UTILACTION.ENVIARMAIL - map:"+map);
			request.getSession().setAttribute(ConstanteSesion.MAIL_CONSTANCIA,
					mailForm);

			mail.sendMailConstancia(map, request);
		} catch (Exception e) {
			log3.error(e, Constante.ERR_LOGICA_NEGOCIO, "");
			e.printStackTrace();
			//throw new ArrayRuleException(ConstanteError.GENERICO,"VERIFIQUE LA(S) DIRECCION(ES) DE CORREO INGRESADA(S) O INTENTE MAS TARDE");
			throw new ArrayRuleException(ConstanteError.GENERICO,
					"Verifique la(s) direccion(es) de correo ingresada(s) o intente mas tarde");
		}
		return mapping.findForward("mailEnviado");
	}

	public ActionForward verPDF(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String idObjeto = request.getParameter("idObjeto");
		String variableSesion = request.getParameter("variableSesion");
		String titulo = request.getParameter("titulo");
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		try {

			Refrendo refrendo = RefrendoFactory.getRefrendoInstance(idObjeto);

			Object objeto = request.getSession().getAttribute(variableSesion);
			HashMap map = new HashMap(cargarVar(request, objeto));
			refrendo.procesar(map);
			String ficheroHTML = refrendo.getResultado();

			//Document document = new Document();
			Document document = new Document(PageSize.A4, 100, 100, 50, 50);
			document.addAuthor("Banco de la Nacion");
			PdfWriter pdfWriter = PdfWriter.getInstance(document, baos);
			document.open();

			String contenido = ficheroHTML.replaceAll(
					Constante.PAGO_TASAS_OSCE_CADENA,
					Constante.PAGO_TASAS_OSCE_REEMPLAZAR_CADENA);
			contenido = contenido.replaceAll(
					Constante.PAGO_TASAS_OSCE_CADENA_HR,
					Constante.PAGO_TASAS_OSCE_REEMPLAZAR_CADENA_HR);
			contenido = contenido.replaceAll(
					Constante.PAGO_TASAS_OSCE_CADENA_HR2,
					Constante.PAGO_TASAS_OSCE_REEMPLAZAR_CADENA_HR);
			Reader htmlreader = new StringReader(contenido);

			//Se agrega imagen a la cabecera
			Image grafico1 = Image.getInstance(this.getClass().getClassLoader()
					.getResource("/Images/logo.jpg"));

			grafico1.setAlignment(Element.ALIGN_RIGHT);
			grafico1.scalePercent(60);
			document.add(grafico1);

			StyleSheet styles = new StyleSheet();

			//				        
			styles.loadTagStyle("span", "size", "8pt");
			styles.loadTagStyle("p", "size", "8pt");
			styles.loadTagStyle("td", "size", "10pt");
			//styles.loadTagStyle("body", "font-family", "calibri"); 
			styles.loadTagStyle("body", "font-size", "10px");
			styles.loadTagStyle("html", "font-size", "10px");
			styles.loadTagStyle("span", "font-size", "8px");
			styles.loadTagStyle("p", "font-size", "8px");
			styles.loadTagStyle("body", "text-align", "justify");

			ArrayList arrayElementList = HTMLWorker.parseToList(htmlreader,
					styles);

			for (int i = 0; i < arrayElementList.size(); ++i) {
				Element e = (Element) arrayElementList.get(i);
				document.add(e);

			}

			document.close();
			byte[] bs = baos.toByteArray();

			String nomArchivo = "CONSTANCIA_" + titulo.replaceAll(" ", "_")
					+ ".pdf";

			response.setHeader("Content-disposition", "attachment; filename="
					+ nomArchivo);
			//response.setHeader("Content-disposition", "attachment; filename=" +"CONSTANCIA "+titulo+ ".pdf");
			response.setContentLength(baos.size());
			ServletOutputStream outStream = response.getOutputStream();
			baos.writeTo(outStream);
			outStream.flush();
			outStream.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (baos != null) {
				baos.reset();
			}

		}

		return null;
		//return mapping.findForward("constancia");

	}

	public static Map cargarVar(HttpServletRequest request, Object objeto) {
		HashMap map = new HashMap();
		Usuario usuario = (Usuario) request.getSession().getAttribute(
				ConstanteSesion.USUARIO_EN_SESION);
		map.put("usuario", usuario);
		map.put("param", objeto);
		if (request.getSession().getAttribute("mensajeItf") != null) {
			map.put("mensajeItf", request.getSession().getAttribute(
					"mensajeItf"));
		} else {
			map.put("mensajeItf", "");
		}
		if (request.getSession().getAttribute("cuenta") != null) {
			map.put("cuenta", request.getSession().getAttribute("cuenta"));
		} else {
			map.put("cuenta", "");
		}
		if (request.getSession().getAttribute("mensajeMonto") != null) {
			map.put("mensajeMonto", request.getSession().getAttribute(
					"mensajeMonto"));
		} else {
			map.put("mensajeMonto", "");
		}
		if (request.getSession().getAttribute("tituloAfiliacion") != null) {
			map.put("tituloAfiliacion", request.getSession().getAttribute(
					"tituloAfiliacion"));
		} else {
			map.put("tituloAfiliacion", "");
		}
		if (request.getSession().getAttribute("hidMoneda") != null) {
			map
					.put("hidMoneda", request.getSession().getAttribute(
							"hidMoneda"));
		} else {
			map.put("hidMoneda", "");
		}
		if (request.getSession().getAttribute("MONEDA_SOL") != null) {
			map.put("MONEDA_SOL", request.getSession().getAttribute(
					"MONEDA_SOL"));
		} else {
			map.put("MONEDA_SOL", "");
		}
		if (request.getSession().getAttribute("MONEDA_DOLAR") != null) {
			map.put("MONEDA_DOLAR", request.getSession().getAttribute(
					"MONEDA_DOLAR"));
		} else {
			map.put("MONEDA_DOLAR", "");
		}

		if (request.getSession().getAttribute("origen") != null) {
			map.put("ctafor", request.getSession().getAttribute("origen"));
		} else {
			map.put("ctafor", "");
		}
		if (request.getSession().getAttribute("abonado") != null) {
			map.put("abonado", request.getSession().getAttribute("abonado"));
		} else {
			map.put("abonado", "");
		}
		if (request.getSession().getAttribute("fecemision") != null) {
			map.put("fecemision", request.getSession().getAttribute(
					"fecemision"));
		} else {
			map.put("fecemision", "");
		}

		if (request.getSession().getAttribute("numrecibo") != null) {
			map
					.put("numrecibo", request.getSession().getAttribute(
							"numrecibo"));
		} else {
			map.put("numrecibo", "");
		}
		if (request.getSession().getAttribute("importe") != null) {
			map.put("importe", request.getSession().getAttribute("importe"));
		} else {
			map.put("importe", "");
		}
		//		***************************************************
		if (request.getSession().getAttribute("fecpago") != null) {
			map.put("fecpago", request.getSession().getAttribute("fecpago")
					.toString());
		} else {
			//map.put("fecpago", "");
			map.put("fecpago", pe.cosapi.common.ObjectUtil.getFechaActual()
					.toString());
		}

		if (request.getSession().getAttribute("horpago") != null) {
			map.put("horpago", request.getSession().getAttribute("horpago")
					.toString());
		} else {
			//map.put("horpago", "");
			map.put("horpago", pe.cosapi.common.ObjectUtil
					.getHHMMSSformateado().toString());
		}
		if (request.getSession().getAttribute("empresa") != null) {
			map.put("empresa", request.getSession().getAttribute("empresa")
					.toString());
		} else {
			map.put("empresa", "");
		}

		if (request.getSession().getAttribute("servicio") != null) {
			map.put("servicio", request.getSession().getAttribute("servicio")
					.toString());
		} else {
			map.put("servicio", "");
		}

		if (request.getSession().getAttribute("fectransib") != null) {
			map.put("fectransib", request.getSession().getAttribute(
					"fectransib").toString());
		} else {
			//map.put("fectransib", "");
			map.put("fectransib", pe.cosapi.common.ObjectUtil.getFechaActual()
					.toString());
		}
		if (request.getSession().getAttribute("hortransib") != null) {
			map.put("hortransib", request.getSession().getAttribute(
					"hortransib").toString());
		} else {
			//map.put("hortransib", "");
			map.put("hortransib", pe.cosapi.common.ObjectUtil
					.getHHMMSSformateado().toString());
		}
		if (request.getSession().getAttribute("fectransmb") != null) {
			map.put("fectransmb", request.getSession().getAttribute(
					"fectransmb").toString());
		} else {
			map.put("fectransmb", pe.cosapi.common.ObjectUtil.getFechaActual()
					.toString());
		}
		if (request.getSession().getAttribute("hortransmb") != null) {
			map.put("hortransmb", request.getSession().getAttribute(
					"hortransmb").toString());
		} else {
			map.put("hortransmb", pe.cosapi.common.ObjectUtil
					.getHHMMSSformateado().toString());
		}
		//		***************************************************
		if (request.getSession().getAttribute("nomcliente") != null) {
			map.put("nomcliente", request.getSession().getAttribute(
					"nomcliente").toString());
		} else {
			map.put("nomcliente", "");
		}
		if (request.getSession().getAttribute("nomlocal") != null) {
			map.put("nomlocal", request.getSession().getAttribute("nomlocal")
					.toString());
		} else {
			map.put("nomlocal", "");
		}

		if (request.getSession().getAttribute("nomtipdoc") != null) {
			map.put("nomtipdoc", request.getSession().getAttribute("nomtipdoc")
					.toString());
		} else {
			map.put("nomtipdoc", "");
		}
		//***************************************************
		//System.out.println("***--- Valor TRIBUTO Antes de:"+request.getSession().getAttribute("nomtribcbo"));
		if (request.getSession().getAttribute("nomtribcbo") != null) {
			map.put("nomtribcbo", request.getSession().getAttribute(
					"nomtribcbo").toString());
			//System.out.println("***--- Codigo Tributo - A:"+request.getSession().getAttribute("nomtribcbo").toString());
		} else {
			if (request.getSession().getAttribute(ConstanteSesion.PAGO_TASAS) != null) {
				PagoTasas pTasasTributo = (PagoTasas) request.getSession()
						.getAttribute(ConstanteSesion.PAGO_TASAS);
				map.put("nomtribcbo", pTasasTributo.getTributo().getCodigo()
						+ " - " + pTasasTributo.getTributo().getDescripcion());
				//System.out.println("***--- Codigo Tributo - B:"+pTasasTributo.getTributo().getCodigo() + " - "+pTasasTributo.getTributo().getDescripcion());
			} else {
				//System.out.println("***--- Codigo Tributo - c: Entro Aqui sin valor");
				map.put("nomtribcbo", "");
			}
		}

		if (request.getSession().getAttribute(ConstanteSesion.AFILIAR) != null) {
			Afiliacion af = (Afiliacion) request.getSession().getAttribute(
					ConstanteSesion.AFILIAR);
			map.put("tipoTelefono", af.getTipoTelefono());
			map.put("empresa", af.getEmpresa());
			map.put("servicio", af.getServicio());
			map.put("numSuministro", af.getNumSuministro());
			map.put("montoMaximoDebito", af.getMontoMaximoDebito());
			map.put("fecha", pe.cosapi.common.ObjectUtil.getFechaActual()
					.toString());
			map.put("hora", pe.cosapi.common.ObjectUtil.getHHMMSSformateado()
					.toString());

		}

		//***************************************************
		/**
		 if (request.getSession().getAttribute("nomtribcbo") != null){
		 map.put("nomtribcbo", request.getSession().getAttribute("nomtribcbo").toString());
		 }else{
		 PagoTasas pTasasTributo = (PagoTasas)request.getSession().getAttribute(ConstanteSesion.PAGO_TASAS);
		 map.put("nomtribcbo", pTasasTributo.getTributo().getCodigo() + " - "+pTasasTributo.getTributo().getDescripcion());
		 }
		 */

		//***************************************************
		if (request.getSession().getAttribute("mensajerde") != null) {
			map.put("mensajerde", request.getSession().getAttribute(
					"mensajerde").toString());
		} else {
			map.put("mensajerde", "");
		}
		if (request.getSession().getAttribute("mensajeavisodni") != null) {
			map.put("mensajeavisodni", request.getSession().getAttribute(
					"mensajeavisodni").toString());
		} else {
			map.put("mensajeavisodni", "");
		}
		//***************************************************

		if (request.getSession().getAttribute("mensajeDiferenciaSaldo") != null) {
			map.put("mensajeDiferenciaSaldo", request.getSession()
					.getAttribute("mensajeDiferenciaSaldo").toString());
		} else {
			map.put("mensajeDiferenciaSaldo", "");
		}

		if (request.getSession().getAttribute("mensajeSaldoAhorro") != null) {
			map.put("mensajeSaldoAhorro", request.getSession().getAttribute(
					"mensajeSaldoAhorro").toString());
		} else {
			map.put("mensajeSaldoAhorro", "");
		}

		if (request.getSession().getAttribute("DES_SUBTITULO1") != null) {
			map.put("DES_SUBTITULO1", request.getSession().getAttribute(
					"DES_SUBTITULO1").toString());
		} else {
			map.put("DES_SUBTITULO1", "");
		}

		if (request.getSession().getAttribute("DES_SUBTITULO2") != null) {
			map.put("DES_SUBTITULO2", request.getSession().getAttribute(
					"DES_SUBTITULO2").toString());
		} else {
			map.put("DES_SUBTITULO2", "");
		}

		if (request.getSession().getAttribute("DESAFILIADOS") != null) {
			map.put("DESAFILIADOS", request.getSession().getAttribute(
					"DESAFILIADOS"));
		} else {
			map.put("DESAFILIADOS", "");
		}

		if (request.getSession().getAttribute("TITULOS") != null) {
			map.put("TITULOS", request.getSession().getAttribute("TITULOS"));
		} else {
			map.put("TITULOS", "");
		}
		if (request.getSession().getAttribute("TITULO") != null) {
			map.put("TITULO", request.getSession().getAttribute("TITULO"));
		} else {
			map.put("TITULO", "");
		}
		if (request.getSession().getAttribute("CORREO_AFILIADO") != null) {
			map.put("CORREO_AFILIADO", request.getSession().getAttribute(
					"CORREO_AFILIADO"));
		} else {
			map.put("CORREO_AFILIADO", "");
		}

		map.put("fecConstancia", pe.cosapi.common.ObjectUtil.getFechaBanco());

		if (request.getSession().getAttribute("mensajegeneracionpie") != null) {
			map.put("mensajegeneracionpie", request.getSession().getAttribute(
					"mensajegeneracionpie"));
		} else {
			map.put("mensajegeneracionpie", "");
		}
		if (request.getSession().getAttribute("mensajegeneracionexito") != null) {
			map.put("mensajegeneracionexito", request.getSession()
					.getAttribute("mensajegeneracionexito"));
		} else {
			map.put("mensajegeneracionexito", "");
		}
		if (request.getSession().getAttribute("mensajegeneracionatte") != null) {
			map.put("mensajegeneracionatte", request.getSession().getAttribute(
					"mensajegeneracionatte"));
		} else {
			map.put("mensajegeneracionatte", "");
		}
		//----------------------------------------------------------------------------------------------
		if (request.getSession().getAttribute("mensajedesafiliacionpie") != null) {
			map.put("mensajedesafiliacionpie", request.getSession()
					.getAttribute("mensajedesafiliacionpie"));
		} else {
			map.put("mensajedesafiliacionpie", "");
		}
		if (request.getSession().getAttribute("mensajedesafiliacionatte") != null) {
			map.put("mensajedesafiliacionatte", request.getSession()
					.getAttribute("mensajedesafiliacionatte"));
		} else {
			map.put("mensajedesafiliacionatte", "");
		}
		if (request.getSession().getAttribute("mensajedesafiliacionexito") != null) {
			map.put("mensajedesafiliacionexito", request.getSession()
					.getAttribute("mensajedesafiliacionexito"));
		} else {
			map.put("mensajedesafiliacionexito", "");
		}
		//-----------------------------------------------------------------------------------------------
		if (request.getSession().getAttribute("mensajecambiopie") != null) {
			map.put("mensajecambiopie", request.getSession().getAttribute(
					"mensajecambiopie"));
		} else {
			map.put("mensajecambiopie", "");
		}
		if (request.getSession().getAttribute("mensajecambioatte") != null) {
			map.put("mensajecambioatte", request.getSession().getAttribute(
					"mensajecambioatte"));
		} else {
			map.put("mensajecambioatte", "");
		}
		if (request.getSession().getAttribute("mensajecambioexito") != null) {
			map.put("mensajecambioexito", request.getSession().getAttribute(
					"mensajecambioexito"));
		} else {
			map.put("mensajecambioexito", "");
		}
		//		-----------------------------------------------------------------------------------------------
		if (request.getSession().getAttribute("mensajebloqueopie") != null) {
			map.put("mensajebloqueopie", request.getSession().getAttribute(
					"mensajebloqueopie"));
		} else {
			map.put("mensajebloqueopie", "");
		}
		if (request.getSession().getAttribute("mensajebloqueoatte") != null) {
			map.put("mensajebloqueoatte", request.getSession().getAttribute(
					"mensajebloqueoatte"));
		} else {
			map.put("mensajebloqueoatte", Constante.MSGBLOQUEOATTE);
		}
		if (request.getSession().getAttribute("mensajebloqueoinfo") != null) {
			map.put("mensajebloqueoinfo", request.getSession().getAttribute(
					"mensajebloqueoinfo"));
		} else {
			map.put("mensajebloqueoinfo", Constante.MSGBLOQUEOINFO);
		}
		if (request.getSession().getAttribute("mensajebloqueoexito") != null) {
			map.put("mensajebloqueoexito", request.getSession().getAttribute(
					"mensajebloqueoexito"));
		} else {
			map.put("mensajebloqueoexito", "");
		}
		if (request.getSession().getAttribute("mensajebloqueocabecera") != null) {
			map.put("mensajebloqueocabecera", request.getSession()
					.getAttribute("mensajebloqueocabecera"));
		} else {
			map.put("mensajebloqueocabecera", "");
		}

		if (request.getSession().getAttribute("tasasDetalleTributo") != null) {
			map.put("tasasDetalleTributo", request.getSession().getAttribute(
					"tasasDetalleTributo"));
		} else {
			map.put("tasasDetalleTributo", "");
		}

		if (request.getSession().getAttribute("mensajeCupo1") != null) {
			map.put("mensajeCupo1", request.getSession().getAttribute(
					"mensajeCupo1"));
		} else {
			map.put("mensajeCupo1", "");
		}
		if (request.getSession().getAttribute("mensajeCupo2") != null) {
			map.put("mensajeCupo2", request.getSession().getAttribute(
					"mensajeCupo2"));
		} else {
			map.put("mensajeCupo2", "");
		}
		if (request.getSession().getAttribute("mensajeCupo3") != null) {
			map.put("mensajeCupo3", request.getSession().getAttribute(
					"mensajeCupo3"));
		} else {
			map.put("mensajeCupo3", "");
		}
		if (request.getSession().getAttribute("mensajeCupo4") != null) {
			map.put("mensajeCupo4", request.getSession().getAttribute(
					"mensajeCupo4"));
		} else {
			map.put("mensajeCupo4", "");
		}
		if (request.getSession().getAttribute("mensajeCupo5") != null) {
			map.put("mensajeCupo5", request.getSession().getAttribute(
					"mensajeCupo5"));
		} else {
			map.put("mensajeCupo5", "");
		}

		/*
		 * DATOS PARA FACTURAS
		 */

		if (request.getSession().getAttribute("mensajeFact1") != null) {
			map.put("mensajeFact1", request.getSession().getAttribute(
					"mensajeFact1"));
		} else {
			map.put("mensajeFact1", "");
		}
		if (request.getSession().getAttribute("mensajeFact2") != null) {
			map.put("mensajeFact2", request.getSession().getAttribute(
					"mensajeFact2"));
		} else {
			map.put("mensajeFact2", "");
		}
		if (request.getSession().getAttribute("mensajeFact3") != null) {
			map.put("mensajeFact3", request.getSession().getAttribute(
					"mensajeFact3"));
		} else {
			map.put("mensajeFact3", "");
		}
		if (request.getSession().getAttribute("mensajeFact4") != null) {
			map.put("mensajeFact4", request.getSession().getAttribute(
					"mensajeFact4"));
		} else {
			map.put("mensajeFact4", "");
		}
		if (request.getSession().getAttribute("mensajeFact5") != null) {
			map.put("mensajeFact5", request.getSession().getAttribute(
					"mensajeFact5"));
		} else {
			map.put("mensajeFact5", "");
		}

		/**
		 * MENSAJES DE FACTURA EN LINEA
		 */

		if (request.getSession().getAttribute("mensajeFactWS1") != null) {
			map.put("mensajeFactWS1", request.getSession().getAttribute(
					"mensajeFactWS1"));
		} else {
			map.put("mensajeFactWS1", "");
		}
		if (request.getSession().getAttribute("mensajeFactWS2") != null) {
			map.put("mensajeFactWS2", request.getSession().getAttribute(
					"mensajeFactWS2"));
		} else {
			map.put("mensajeFactWS2", "");
		}
		if (request.getSession().getAttribute("mensajeFactWS3") != null) {
			map.put("mensajeFactWS3", request.getSession().getAttribute(
					"mensajeFactWS3"));
		} else {
			map.put("mensajeFactWS3", "");
		}
		if (request.getSession().getAttribute("mensajeFactWS4") != null) {
			map.put("mensajeFactWS4", request.getSession().getAttribute(
					"mensajeFactWS4"));
		} else {
			map.put("mensajeFactWS4", "");
		}
		if (request.getSession().getAttribute("mensajeFactWS5") != null) {
			map.put("mensajeFactWS5", request.getSession().getAttribute(
					"mensajeFactWS5"));
		} else {
			map.put("mensajeFactWS5", "");
		}
		if (request.getSession().getAttribute("mensajeTarjetaCredito") != null) {
			map.put("mensajeTarjetaCredito", request.getSession().getAttribute(
					"mensajeTarjetaCredito").toString());
		} else {
			map.put("mensajeTarjetaCredito", "");
		}
		if (request.getSession().getAttribute("mensajeTarjetaDeCredito") != null) {
			map.put("mensajeTarjetaDeCredito", request.getSession()
					.getAttribute("mensajeTarjetaDeCredito"));
		} else {
			map.put("mensajeTarjetaDeCredito", "");
		}
		
		if (request.getSession().getAttribute("tdDestino") != null) {
			map.put("tdDestino", request.getSession().getAttribute(
					"tdDestino"));
		} else {
			map.put("tdDestino", "");
		}
		
		return map;
	}

	public static JSONObject callRestGET(String strURL) {
		JSONParser parser = new JSONParser();
		JSONObject jeyson = null; 
		String output;
		
		
		try {
			URL url = new URL(strURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("application","1");
			conn.setRequestProperty("channel","1");
			conn.setRequestProperty("channelCode","1");

			if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP Error code : " + conn.getResponseCode());
            } 

			InputStreamReader in = new InputStreamReader(conn.getInputStream());
			BufferedReader br = new BufferedReader(in);

			while ((output = br.readLine()) != null) {
                //System.out.println("output::::" + output);
                jeyson = (JSONObject) parser.parse(output);
                //System.out.println("jeyson::::" + jeyson.get("data"));
            }
			conn.disconnect();

		} catch (Exception e) {
			System.out.println("Exception in NetClientGet:- " + e);
		}
		
		return jeyson;
	}

	public static JSONObject callRestPOST(String strURL,
			Map<String, String> mapParametros) {
		JSONParser parser = new JSONParser();
		JSONObject jeyson = null; 
        try {
        	String output;
            URL url = new URL(strURL);
            
            StringBuilder json = new StringBuilder();
            json.append("{");
            for (Map.Entry<String, String> param : mapParametros.entrySet()) {
            	json.append("\""+param.getKey()+"\":\""+param.getValue()+"\",");
      		}
            json = new StringBuilder(json.substring(0, json.length()-1));
	        json.append("}");
            
            byte[] postDataBytes = json.toString().getBytes("UTF-8");
//            System.out.println("input::::" + json.toString());
            
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("ChannelCode", "WEB");
            conn.setRequestProperty("ApplicationCode", "SATI");
            conn.setRequestProperty("Imei", "");
            conn.setRequestProperty("Device", "WEB");
            conn.setRequestProperty("Channel", "WEB");
            conn.setRequestProperty("Application", "SATI");
            conn.setRequestProperty("MacAddress", "");
            conn.setConnectTimeout(5000);
            conn.setDoOutput(true);
            conn.getOutputStream().write(postDataBytes);
            
            
            if (conn.getResponseCode() != 200) {
            	
                throw new RuntimeException("Failed : HTTP Error code : " + conn.getResponseCode());
            } 
            
            InputStreamReader in = new InputStreamReader(conn.getInputStream());
            BufferedReader br = new BufferedReader(in);
            
            while ((output = br.readLine()) != null) {
//                System.out.println("output::::" + output);
                jeyson = (JSONObject) parser.parse(output);
                /** BORRAR **/ 
                //System.out.println("jeyson::::" + jeyson.get("data"));
                /** BORRAR **/
            }
            conn.disconnect();
            
        } catch (Exception e) {
            System.out.println("Exception in NetClientGet:- " + e);
        }        
        return jeyson;
    }
	
	public static JSONObject callRestPOSTMapsUsingJSonParser(String strURL,
			StringBuilder json) {
		JSONParser parser = new JSONParser();
		JSONObject jeyson = null; 
        try {
        	String output;
            URL url = new URL(strURL);
            
            byte[] postDataBytes = json.toString().getBytes("UTF-8");
//            System.out.println("input::::" + json.toString());
            
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("ChannelCode", "WEB");
            conn.setRequestProperty("ApplicationCode", "SATI");
            conn.setRequestProperty("Imei", "");
            conn.setRequestProperty("Device", "WEB");
            conn.setRequestProperty("MacAddress", "");
            
            conn.setDoOutput(true);
            conn.getOutputStream().write(postDataBytes);
            
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP Error code : " + conn.getResponseCode());
            } 
            
            InputStreamReader in = new InputStreamReader(conn.getInputStream());
            BufferedReader br = new BufferedReader(in);
            
            while ((output = br.readLine()) != null) {
//                System.out.println("output::::" + output);
                jeyson = (JSONObject) parser.parse(output);
                //System.out.println("jeyson::::" + jeyson.get("data"));
            }
            
            conn.disconnect();
        } catch (Exception e) {
            System.out.println("Exception in NetClientGet:- " + e);
        }        
        return jeyson;
    }
	
	
	public static JSONObject callRestPOSTMapsConcat(String strURL,
			StringBuilder json) {
		JSONParser parser = new JSONParser();
		JSONObject jeyson = null; 
        try {
        	String output;
            URL url = new URL(strURL);
            
            byte[] postDataBytes = json.toString().getBytes("UTF-8");
            System.out.println("input::::" + json.toString());
            
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("ChannelCode", "WEB");
            conn.setRequestProperty("Channel", "WEB");
            conn.setRequestProperty("Application", "SATI");
            conn.setRequestProperty("Imei", "");
            conn.setRequestProperty("Device", "WEB");
            conn.setRequestProperty("MacAddress", "");
            
            conn.setDoOutput(true);
            conn.getOutputStream().write(postDataBytes);
            
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP Error code : " + conn.getResponseCode());
            } 
            
            InputStreamReader in = new InputStreamReader(conn.getInputStream());
            BufferedReader br = new BufferedReader(in);
            
            String json2 = "";
            while ((output = br.readLine()) != null) {
            	json2 += output;
            }
            jeyson = (JSONObject) parser.parse(json2);
            conn.disconnect();
            
            
        } catch (Exception e) {
            System.out.println("Exception in NetClientGet:- " + e);
        }        
        return jeyson;
    }
	
	public static JSONObject callRestPOSTV2(String strURL,
			Map<String, String> mapParametros) {
		JSONParser parser = new JSONParser();
		JSONObject jeyson = null; 
        try {
        	String output;
            URL url = new URL(strURL);
            
            StringBuilder json = new StringBuilder();
            json.append("{");
            for (Map.Entry<String, String> param : mapParametros.entrySet()) {
            	json.append("\""+param.getKey()+"\":\""+param.getValue()+"\",");
      		}
            json = new StringBuilder(json.substring(0, json.length()-1));
	        json.append("}");
            System.out.println("json.toString().getBytes::::"+json.toString().getBytes("UTF-8"));
            byte[] postDataBytes = json.toString().getBytes("UTF-8");
//            System.out.println("input::::" + json.toString());
            
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("application", "SATI");
            conn.setRequestProperty("channelCode", "WEB");
            //conn.setRequestProperty("channel", "WEB");            
            conn.setConnectTimeout(5000);
            conn.setDoOutput(true);
            conn.getOutputStream().write(postDataBytes);
            
            if (conn.getResponseCode() != 200) {
            	
                throw new RuntimeException("Failed : HTTP Error code : " + conn.getResponseCode());
            } 
            
            InputStreamReader in = new InputStreamReader(conn.getInputStream());
            BufferedReader br = new BufferedReader(in);
            
            String json2 = "";
            while ((output = br.readLine()) != null) {
            	json2 += output;
            }
            jeyson = (JSONObject) parser.parse(json2);
            conn.disconnect();
            
        } catch (Exception e) {
            System.out.println("Exception in NetClientGet:- " + e);
        }        
        return jeyson;
    }
	
	public static JSONObject callRestPOSTV3(String strURL,
			Map<String, String> mapParametros) {
		JSONParser parser = new JSONParser();
		JSONObject jeyson = null; 
        try {
        	String output;
            URL url = new URL(strURL);
            
            StringBuilder json = new StringBuilder();
            json.append("{");
            for (Map.Entry<String, String> param : mapParametros.entrySet()) {
            	json.append("\""+param.getKey()+"\":\""+param.getValue()+"\",");
      		}
            json = new StringBuilder(json.substring(0, json.length()-1));
	        json.append("}");
            System.out.println("json.toString().getBytes::::"+json.toString().getBytes("UTF-8"));
            byte[] postDataBytes = json.toString().getBytes("UTF-8");
//            System.out.println("input::::" + json.toString());
            
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("tipoOperacion", "ADMIN");
            conn.setRequestProperty("canal", "WEB");
            conn.setRequestProperty("aplicacion", "SATI");
                      
            conn.setConnectTimeout(5000);
            conn.setDoOutput(true);
            conn.getOutputStream().write(postDataBytes);
            
            if (conn.getResponseCode() != 200) {
            	
                throw new RuntimeException("Failed : HTTP Error code : " + conn.getResponseCode());
            } 
            
            InputStreamReader in = new InputStreamReader(conn.getInputStream());
            BufferedReader br = new BufferedReader(in);
            
            String json2 = "";
            while ((output = br.readLine()) != null) {
            	json2 += output;
            }
            jeyson = (JSONObject) parser.parse(json2);
            conn.disconnect();
            
        } catch (Exception e) {
            System.out.println("Exception in NetClientGet:- " + e);
        }        
        return jeyson;
    }
	
	
	public static MensajesActDesTDC activarClaveSms(TokenSmsActivacionRequest tokenSmsActivacionRq, String urlGeneracion) {
		JSONObject json = null; 
		JSONObject jsonData = null; 
		MensajesActDesTDC resultado = new MensajesActDesTDC(new DataTDC());
		
//		System.out.println("::::generarClaveSmsActivacion()");

		Map<String, String> mapParametros = new HashMap<String, String>();
		mapParametros.put("codeSMS", tokenSmsActivacionRq.getPinblock());
		mapParametros.put("typeNotification", "0");
		mapParametros.put("nameClient", tokenSmsActivacionRq.getNameClient());
		mapParametros.put("email", tokenSmsActivacionRq.getEmail());
		
		
		json = UtilAction.callRestPOST(urlGeneracion+tokenSmsActivacionRq.getCodCliente(), mapParametros);
		
		if(json != null && json.get("codResult").equals("0000")){
			jsonData = (JSONObject)json.get("data");
			
			resultado.setCodRptaPrincipal(0);
			resultado.setCodResultOper("1");
			resultado.setCodResult(json.get("codResult").toString());
			resultado.setMsg(json.get("msgError").toString());
			resultado.getData().setCodeOperation(jsonData.get("codeOperation").toString());
			resultado.getData().setDateActivation(jsonData.get("dateActivation").toString());
			
			resultado.setExito(true);
		} else if(json != null && !json.get("codResult").equals("0000")){
			resultado.setCodRptaPrincipal(Integer.parseInt(json.get("codResult").toString()));
			resultado.setCodResultOper("0");
			resultado.setCodResult(json.get("codResult").toString());
			resultado.setMsg(json.get("msgError").toString());
			resultado.setExito(false);
		} else if(json == null){
			resultado.setCodRptaPrincipal(9999);
			resultado.setCodResultOper("0");
			resultado.setCodResult("9999");
			resultado.setMsg("Ocurrio un problema al activar Clave Dinamica Digital");
			resultado.setExito(false);
		}
		
		return resultado;
		
		//return json;
	}
	
//	public static JSONObject activarClaveSms(TokenSmsActivacionRequest tokenSmsActivacionRq, String urlGeneracion) {
//		JSONObject json = null; 
//		MensajesActivacionTDC resultado = new MensajesActivacionTDC();
//		
//		System.out.println("::::generarClaveSmsActivacion()");
//
//		Map<String, String> mapParametros = new HashMap<String, String>();
//		mapParametros.put("codeSMS", tokenSmsActivacionRq.getPinblock());
//		mapParametros.put("typeNotification", "0");
//		mapParametros.put("nameClient", tokenSmsActivacionRq.getNameClient());
//		mapParametros.put("email", tokenSmsActivacionRq.getEmail());
//		
//		
//		json = UtilAction.callRestPOST(urlGeneracion+tokenSmsActivacionRq.getCodCliente(), mapParametros);
//		
////		if(json != null && json.get("codResult").equals("0000")){
////			resultado.setCodRptaPrincipal(0);
////			resultado.setCodResultOper("1");
////			resultado.setCodResult(json.get("codResult").toString());
////			resultado.setMsg(json.get("msgError").toString());
////			resultado.setData((DataTDC)json.get("data"));
////			resultado.setExito(true);
////		} else {
////			resultado.setCodRptaPrincipal(Integer.parseInt(json.get("codResult").toString()));
////			resultado.setCodResultOper("0");
////			resultado.setCodResult(json.get("codResult").toString());
////			resultado.setMsg(json.get("msgError").toString());
////			resultado.setExito(false);
////		}
//		
//		//return resultado;
//		
//		return json;
//	}
	
	public static MensajesActDesTDC desafiliarClaveSms2(TokenSmsActivacionRequest tokenSmsActivacionRq, String urlGeneracion) {
		JSONObject json = null; 
		JSONObject jsonData = null; 
		MensajesActDesTDC resultado = new MensajesActDesTDC(new DataTDC());
		System.out.println("::::generarClaveSmsActivacion()");

		Map<String, String> mapParametros = new HashMap<String, String>();
		mapParametros.put("codeSMS", tokenSmsActivacionRq.getPinblock());
		//mapParametros.put("typeNotification", "2");
		mapParametros.put("nameClient", tokenSmsActivacionRq.getNameClient());
		mapParametros.put("email", tokenSmsActivacionRq.getEmail());		
		mapParametros.put("reasonCode", tokenSmsActivacionRq.getReasonCode());
		
		
		json = UtilAction.callRestPOST(urlGeneracion+tokenSmsActivacionRq.getCodCliente(), mapParametros);

		if(json != null && json.get("codResult").equals("0000")){
			jsonData = (JSONObject)json.get("data");
			
			resultado.setCodRptaPrincipal(0);
			resultado.setCodResultOper("1");
			resultado.setCodResult(json.get("codResult").toString());
			resultado.setMsg(json.get("msgError").toString());
			resultado.getData().setCodeOperation(jsonData.get("codeOperation").toString());
			resultado.getData().setDateActivation(jsonData.get("dateOperation").toString());
			//resultado.getData().setReasonCode(jsonData.get("reasonCode").toString());
			resultado.getData().setReasonCode("RE");
			//resultado.getData().setDateActivation("");
			
			resultado.setExito(true);
		} else {
			resultado.setCodRptaPrincipal(Integer.parseInt(json.get("codResult").toString()));
			resultado.setCodResultOper("0");
			resultado.setCodResult(json.get("codResult").toString());
			resultado.setMsg(json.get("msgError").toString());
			resultado.setExito(false);
		}
		
		return resultado;
	}
	
	public static JSONObject desafiliarClaveSms(TokenSmsActivacionRequest tokenSmsActivacionRq, String urlGeneracion) {
		JSONObject json = null; 
		System.out.println("::::generarClaveSmsActivacion()");

		Map<String, String> mapParametros = new HashMap<String, String>();
		mapParametros.put("codeSMS", tokenSmsActivacionRq.getPinblock());
		mapParametros.put("typeNotification", "2");
		mapParametros.put("nameClient", tokenSmsActivacionRq.getNameClient());
		mapParametros.put("email", tokenSmsActivacionRq.getEmail());
		
		
		json = UtilAction.callRestPOST(urlGeneracion+tokenSmsActivacionRq.getCodCliente(), mapParametros);
		return json;
	}
	
//	public static JSONObject activarClaveSmsMigracion(TokenSmsActivacionRequest tokenSmsActivacionRq, String urlActivacion) {
//		JSONObject json = null; 
//		System.out.println("::::activarClaveSmsMigracion()");
//
//		Map<String, String> mapParametros = new HashMap<String, String>();
//		mapParametros.put("typeDoc", tokenSmsActivacionRq.getPinblock());
//		mapParametros.put("numberDoc", tokenSmsActivacionRq.getNumberDoc());
//		mapParametros.put("operatorMobile", tokenSmsActivacionRq.getOperatorMobile());
//		mapParametros.put("typeNotification", tokenSmsActivacionRq.getTypeNotification());
//		mapParametros.put("numberMobile", tokenSmsActivacionRq.getNumberMobile());
//		mapParametros.put("email", tokenSmsActivacionRq.getEmail());
//		mapParametros.put("nameClient", tokenSmsActivacionRq.getNameClient());
//		mapParametros.put("flagTerminos", "1");
//		
//		json = UtilAction.callRestPOST(urlActivacion+tokenSmsActivacionRq.getCodCliente(), mapParametros);
//		return json;
//	}
	
	public static MensajesTDC generarClaveSmsActivacion(TokenSmsActivacionRequest tokenSmsActivacionRq, String urlGeneracion) {
		System.out.println("::::generarClaveSmsActivacion()");
		String cic = String.format("%012d",Integer.parseInt(tokenSmsActivacionRq.getCodCliente()));
		String tipDoc = String.valueOf(Integer.parseInt(tokenSmsActivacionRq.getTipoDocumento()));
		String numDoc = Funciones.llenarCerosAlaIzquierda(tokenSmsActivacionRq.getNumberDoc(), 12);
		MensajesTDC resultado = new MensajesTDC();
		
		Map<String, String> mapParametros = new HashMap<String, String>();
		mapParametros.put("dataClient", cic + tipDoc + numDoc);
		mapParametros.put("numberMobile", tokenSmsActivacionRq.getNumberMobile());
		mapParametros.put("operatorMobile", tokenSmsActivacionRq.getOperatorMobile());
		mapParametros.put("flagTerminos", "");
		
		JSONObject json = UtilAction.callRestPOST(urlGeneracion+tokenSmsActivacionRq.getCodCliente(), mapParametros);
		
		if(json != null && json.get("codResult").equals("0000")){
			resultado.setCodRptaPrincipal(0);
			resultado.setCodResultOper("1");
			resultado.setCodResult(json.get("codResult").toString());
			resultado.setMsg(json.get("msgError").toString());
			resultado.setExito(true);
		} else {
			resultado.setCodRptaPrincipal(Integer.parseInt(json.get("codResult").toString()));
			resultado.setCodResultOper("0");
			resultado.setCodResult(json.get("codResult").toString());
			resultado.setMsg(json.get("msgError").toString());
			resultado.setExito(false);
		}
		
		return resultado;
	}
	
	public static MensajesTDC generarClaveSmsMigracion(TokenSmsMigracionRequest tokenSmsMigracionRq, String urlGeneracion) {
		System.out.println("::::generarClaveSmsMigracion()");
		String cic = String.format("%012d",Integer.parseInt(tokenSmsMigracionRq.getCodCliente()));
		String tipDoc = String.valueOf(Integer.parseInt(tokenSmsMigracionRq.getTipoDocumento()));
		String numDoc = Funciones.llenarCerosAlaIzquierda(tokenSmsMigracionRq.getNroDocumento(), 12);

		Map<String, String> mapParametros = new HashMap<String, String>();
		mapParametros.put("dataClient", cic + tipDoc + numDoc);
		mapParametros.put("numberMobile", tokenSmsMigracionRq.getNumberMobile());
		mapParametros.put("operatorMobile", tokenSmsMigracionRq.getCodOperatorMobile());
		
		MensajesTDC resultado = new MensajesTDC();
		
		JSONObject json = UtilAction.callRestPOST(urlGeneracion+tokenSmsMigracionRq.getCodCliente(), mapParametros);
		
		if(json != null && json.get("codResult").equals("0000")){
			resultado.setCodRptaPrincipal(0);
			resultado.setCodResultOper("1");
			resultado.setCodResult(json.get("codResult").toString());
			resultado.setMsg(json.get("msgError").toString());
			resultado.setExito(true);
		} else {
			resultado.setCodRptaPrincipal(Integer.parseInt(json.get("codResult").toString()));
			resultado.setCodResultOper("0");
			resultado.setCodResult(json.get("codResult").toString());
			resultado.setMsg(json.get("msgError").toString());
			resultado.setExito(false);
		}
		
		return resultado;
	
	}

	
	public static MensajesTDC validarClaveSmsMigracion(TokenSmsMigracionRequest tokenSmsMigra, String urlValidacion) {
		System.out.println("::::validarClaveSmsMigracion()");
		MensajesTDC resultado = new MensajesTDC();
		JSONObject jsonConsultaTipoToken = null;
		
		String cic = String.format("%012d",Integer.parseInt(tokenSmsMigra.getCodCliente()));
		String tipDoc = String.valueOf(Integer.parseInt(tokenSmsMigra.getTipoDocumento()));
		String numDoc = Funciones.llenarCerosAlaIzquierda(tokenSmsMigra.getNroDocumento(), 12);
		
		Map<String, String> mapParametros = new HashMap<String, String>();
		mapParametros = new HashMap<String, String>();
		mapParametros.put("dataClient", cic + tipDoc + numDoc);
		mapParametros.put("operatorMobile",tokenSmsMigra.getCodOperatorMobile());
		mapParametros.put("numberMobile",tokenSmsMigra.getNumberMobile());
		mapParametros.put("codeSMS", tokenSmsMigra.getPinblock());
		mapParametros.put("typeOp", tokenSmsMigra.getTypeOp());

		jsonConsultaTipoToken = UtilAction.callRestPOST(urlValidacion+tokenSmsMigra.getCodCliente(), mapParametros);
		
		if(jsonConsultaTipoToken != null && jsonConsultaTipoToken.get("codResult").equals("0000")){
			resultado.setCodRptaPrincipal(0);
			resultado.setCodResultOper("1");
			resultado.setCodResult(jsonConsultaTipoToken.get("codResult").toString());
			resultado.setMsg(jsonConsultaTipoToken.get("msgError").toString());
			resultado.setExito(true);
		} else {
			resultado.setCodRptaPrincipal(Integer.parseInt(jsonConsultaTipoToken.get("codResult").toString()));
			resultado.setCodResultOper("0");
			resultado.setCodResult(jsonConsultaTipoToken.get("codResult").toString());
			resultado.setMsg(jsonConsultaTipoToken.get("msgError").toString());
			resultado.setExito(false);
		}

		return resultado;
	}
	
	public static MensajesTDC generarClaveSms(TokenSmsRequest tokenSms) {
	String flagLog = Parametro.getString(ConstanteParametros.BN_PARAM_FLAG_VER_LOG);
		
		boolean isActiveLog=Boolean.parseBoolean(flagLog);  
		String urlGeneracion = Parametro.getString(ConstanteParametros.BN_PARAM_CLAVE_SMS_GENERA_CLAVE);
		if(isActiveLog){
//		System.out.println("url.rest.service.genera.token:"+urlGeneracion);
//		System.out.println("generarClaveSms");
//		System.out.println("tokenSms:"+tokenSms);
//		System.out.println("urlGeneracion:"+urlGeneracion);
//		System.out.println("tokenSms.getCodCliente():"+tokenSms.getCodCliente());
//		System.out.println("tokenSms.getTipoDocumento():"+tokenSms.getTipoDocumento());
//		System.out.println("tokenSms.getNroDocumento():"+tokenSms.getNroDocumento());
		}
		
		String cic = String.format("%012d",Integer.parseInt(tokenSms.getCodCliente()));
		String tipDoc = String.valueOf(Integer.parseInt(tokenSms.getTipoDocumento()));
		String numDoc = Funciones.llenarCerosAlaIzquierda(tokenSms.getNroDocumento(),12);
		
		MensajesTDC resultado = new MensajesTDC();

		Map<String, String> mapParametros = new HashMap<String, String>();
		
		if(tokenSms.getTypeOp().equals(Constante.COD_TRANSACCION_ADMINISTRATIVA)){//ADMIN
			mapParametros.put("dataClient", cic + tipDoc + numDoc);
			mapParametros.put("typeTrans", tokenSms.getTypeTrans());
			mapParametros.put("typeOp", tokenSms.getTypeOp());
			//mapParametros.put("typeCurrency", tokenSms.getTypeCurrency());
			//mapParametros.put("amount", tokenSms.getAmount());
			mapParametros.put("msgClient", tokenSms.getMsgClient());			
		}else{//FIN
			String amount = tokenSms.getAmount()!=null?tokenSms.getAmount().replace(",",""):"0";
			mapParametros.put("dataClient", cic + tipDoc + numDoc);
			mapParametros.put("typeTrans", tokenSms.getTypeTrans());
			mapParametros.put("typeOp", tokenSms.getTypeOp());
			mapParametros.put("typeCurrency", tokenSms.getTypeCurrency());
			mapParametros.put("amount", amount);
			mapParametros.put("nameBeneficiary", tokenSms.getNomCliDestinatario());
			mapParametros.put("codeBeneficiary", tokenSms.getCodCliDestinatario());
		}
		
		JSONObject json = UtilAction.callRestPOST(urlGeneracion+tokenSms.getCodCliente(), mapParametros);
		
		if(json != null && json.get("codResult").equals("0000")){
			resultado.setCodRptaPrincipal(0);
			resultado.setCodResultOper("1");
			resultado.setCodResult(json.get("codResult").toString());
			resultado.setMsg(json.get("msgError").toString());
			resultado.setExito(true);
		} else if(json != null && !json.get("codResult").equals("0000")){
			resultado.setCodRptaPrincipal(Integer.parseInt(json.get("codResult").toString()));
			resultado.setCodResultOper("0");
			resultado.setCodResult(json.get("codResult").toString());
			resultado.setMsg(json.get("msgError").toString());
			resultado.setExito(false);
		} else {
			resultado.setCodResultOper("0");
			resultado.setExito(false); 
		}
		
		return resultado;
	}

	public static MensajesTDC validarClaveSms(TokenSmsRequest tokenSms) {
		String flagLog = Parametro.getString(ConstanteParametros.BN_PARAM_FLAG_VER_LOG);
		
		boolean isActiveLog=Boolean.parseBoolean(flagLog); 
		
		String urlValidacion = Parametro.getString(ConstanteParametros.BN_PARAM_CLAVE_SMS_VALIDA_CLAVE);
		if(isActiveLog)System.out.println("url.rest.service.valida.token:"+urlValidacion);
		
		MensajesTDC resultado = new MensajesTDC();
		JSONObject jsonConsultaTipoToken = null;
		
		String cic = String.format("%012d",Integer.parseInt(tokenSms.getCodCliente()));
		String tipDoc = String.valueOf(Integer.parseInt(tokenSms.getTipoDocumento()));
		String numDoc = Funciones.llenarCerosAlaIzquierda(tokenSms.getNroDocumento(), 12);
		
		Map<String, String> mapParametros = new HashMap<String, String>();
		mapParametros = new HashMap<String, String>();
		
		if(tokenSms.getTypeOp().equals(Constante.COD_TRANSACCION_ADMINISTRATIVA)){//ADMIN
			mapParametros.put("dataClient", cic + tipDoc + numDoc);
			mapParametros.put("typeTrans", tokenSms.getTypeTrans());
			mapParametros.put("typeOp", tokenSms.getTypeOp());
			//mapParametros.put("typeCurrency", tokenSms.getTypeCurrency());
			//mapParametros.put("amount", tokenSms.getAmount());
			mapParametros.put("msgClient", tokenSms.getMsgClient());
			mapParametros.put("codeSMS", tokenSms.getCodeSMS());			
		}else{//FIN
			String amount = tokenSms.getAmount()!=null?tokenSms.getAmount().replace(",",""):"0";
			
			mapParametros.put("dataClient", cic + tipDoc + numDoc);			
			mapParametros.put("typeTrans", tokenSms.getTypeTrans());
			mapParametros.put("typeOp", tokenSms.getTypeOp());
			mapParametros.put("typeCurrency", tokenSms.getTypeCurrency());
			mapParametros.put("amount", amount);
			mapParametros.put("nameBeneficiary", tokenSms.getNomCliDestinatario());
			mapParametros.put("codeBeneficiary", tokenSms.getCodCliDestinatario());			
			mapParametros.put("codeSMS", tokenSms.getCodeSMS());
		}

		jsonConsultaTipoToken = UtilAction.callRestPOST(urlValidacion+tokenSms.getCodCliente(), mapParametros);
		
		if(jsonConsultaTipoToken != null && jsonConsultaTipoToken.get("codResult").equals("0000")){
			resultado.setCodRptaPrincipal(0);
			resultado.setCodResultOper("1");
			resultado.setCodResult(jsonConsultaTipoToken.get("codResult").toString());
			resultado.setMsg(jsonConsultaTipoToken.get("msg").toString());
			resultado.setExito(true);
		} else if(jsonConsultaTipoToken != null && !jsonConsultaTipoToken.get("codResult").equals("0000")){
			resultado.setCodRptaPrincipal(Integer.parseInt(jsonConsultaTipoToken.get("codResult").toString()));
			resultado.setCodResultOper("0");
			resultado.setCodResult(jsonConsultaTipoToken.get("codResult").toString());
			resultado.setMsg(jsonConsultaTipoToken.get("msg").toString());
			resultado.setExito(false);
		} else {
			resultado.setCodResultOper("0");
			resultado.setExito(false);
		}

		return resultado;
	}
	
	
	public static MensajesActDesTDC migrarClaveSms(TokenSmsMigracionRequest tokenSmsMigracionRq, String urlGeneracion) {
		JSONObject json = null; 
		JSONObject jsonData = null; 
		MensajesActDesTDC resultado = new MensajesActDesTDC(new DataTDC());
		
		String flagLog = Parametro.getString(ConstanteParametros.BN_PARAM_FLAG_VER_LOG);
		
		boolean isActiveLog=Boolean.parseBoolean(flagLog); 
		
		if(isActiveLog){
			
			System.out.println("urlGeneracion:"+urlGeneracion);
		
		}	
	

		Map<String, String> mapParametros = new HashMap<String, String>();
		
		mapParametros.put("typeDoc",String.valueOf(Integer.parseInt(tokenSmsMigracionRq.getTipoDocumento())));
		mapParametros.put("numberDoc",tokenSmsMigracionRq.getNroDocumento());
		mapParametros.put("operatorMobile",tokenSmsMigracionRq.getOperatorMobile());
		mapParametros.put("typeNotification",tokenSmsMigracionRq.getTipoNotificacion());
		mapParametros.put("numberMobile",tokenSmsMigracionRq.getNumberMobile());
		mapParametros.put("email",tokenSmsMigracionRq.getEmail());
		
		//mapParametros.put("nameClient",tokenSmsMigracionRq.getCodCliente());
		mapParametros.put("nameClient",tokenSmsMigracionRq.getNameClient());
		
		mapParametros.put("flagTerminos",tokenSmsMigracionRq.getFlagTerminos());
		
		json = UtilAction.callRestPOST(urlGeneracion+tokenSmsMigracionRq.getCodCliente(), mapParametros);
		
		if(json != null && json.get("codResult").equals("0000")){
			jsonData = (JSONObject)json.get("data");
			
			resultado.setCodRptaPrincipal(0);
			resultado.setCodResultOper("1");
			resultado.setCodResult(json.get("codResult").toString());
			resultado.setMsg(json.get("msgError").toString());
			resultado.getData().setCodeOperation(jsonData.get("codeOperation").toString());
			resultado.getData().setDateActivation(jsonData.get("dateActivation").toString());
			
			resultado.setExito(true);
//		}
//		if(json != null && json.get("codResult").equals("0000")){
//			resultado.setCodRptaPrincipal(0);
//			resultado.setCodResultOper("1");
//			resultado.setCodResult(json.get("codResult").toString());
//			resultado.setMsg(json.get("msg").toString());
//			resultado.setExito(true);
		} else {
			resultado.setCodRptaPrincipal(Integer.parseInt(json.get("codResult").toString()));
			resultado.setCodResultOper("0");
			resultado.setCodResult(json.get("codResult").toString());
			resultado.setMsg(json.get("msg").toString());
			resultado.setExito(false);
		}

		return resultado;
	}
	
	public static String obtenerTipoTransaccionFinanciera(String hidCodServ){
		String typeTrans = StringUtils.EMPTY;

		String grupo = hidCodServ.substring(0, 2); 
		String trx = hidCodServ.substring(2, 4);
		System.out.println("grupo::::" + grupo);
		System.out.println("trx::::" + trx);
		
		if(grupo.equals(Constante.PAGO_SERVICIOS_GRP_ESTADO)){//Grupo 01
			
			
		}else if(grupo.equals(Constante.PAGO_SERVICIOS_GRP_TELEFONO)){//Grupo 02
			if(trx.equals(Constante.PAGO_SERVICIOS_TIPO_TRX_TELEFONICA_FIJA)){//01
				typeTrans = Constante.TIP_OPER_PAGO_TELEF_FIJA;
			}else if(trx.equals(Constante.PAGO_SERVICIOS_TIPO_TRX_CUPON_MN)){//07
				typeTrans = Constante.TIP_OPER_PAGO_CUPON_SERVICIOS;
			}
			
			
		}else if(grupo.equals(Constante.PAGO_SERVICIOS_GRP_CELULAR)){//Grupo 03
			if(trx.equals(Constante.PAGO_SERVICIOS_TIPO_TRX_TELEFONICA_RECARGAS)){//05
				typeTrans = Constante.TIP_OPER_RECARGA;
			}else if(trx.equals(Constante.PAGO_SERVICIOS_TIPO_TRX_CLARO_RECARGAS)){//11
				typeTrans = Constante.TIP_OPER_RECARGA;
			}else if(trx.equals(Constante.PAGO_SERVICIOS_TIPO_TRX_BITEL_RECARGAS)){//14
				typeTrans = Constante.TIP_OPER_RECARGA;
			}else if(trx.equals(Constante.PAGO_SERVICIOS_TIPO_TRX_ENTEL_RECARGAS)){//16
				typeTrans = Constante.TIP_OPER_RECARGA;
			}
			

		}else if(grupo.equals(Constante.PAGO_SERVICIOS_GRP_CABLE)){//Grupo 04
			if(trx.equals(Constante.PAGO_SERVICIOS_TIPO_TRX_TELEFONICA_CABLE)){//04
				typeTrans = Constante.TIP_OPER_PAGO_CABLE;
			}
			
			
		}else if(grupo.equals(Constante.PAGO_SERVICIOS_GRP_AGUA)){//Grupo 05
			if(trx.equals(Constante.PAGO_SERVICIOS_TIPO_TRX_SEDAPAL)){//06
				typeTrans = Constante.TIP_OPER_PAGO_AGUA;
			}else if(trx.equals(Constante.PAGO_SERVICIOS_TIPO_TRX_CUPON_MN)){//07
				typeTrans = Constante.TIP_OPER_PAGO_AGUA;
			}
			
			
		}else if(grupo.equals(Constante.PAGO_SERVICIOS_GRP_LUZ)){//Grupo 06
			if(trx.equals(Constante.PAGO_SERVICIOS_TIPO_TRX_CUPON_MN)){//07
				typeTrans = Constante.TIP_OPER_PAGO_LUZ;
			}
			
			
		}else if(grupo.equals(Constante.PAGO_SERVICIOS_GRP_BANCOSYFIN)){//Grupo 07
			if(trx.equals(Constante.PAGO_SERVICIOS_TIPO_TRX_CUPON_MN)){//07
				typeTrans = Constante.TIP_OPER_PAGO_PRESTAMO;
			}else if(trx.equals(Constante.PAGO_SERVICIOS_TIPO_TRX_FACTURA_MN)){//09
				typeTrans = Constante.TIP_OPER_PAGO_PRESTAMO;
			}
			
			
		}else if(grupo.equals(Constante.PAGO_SERVICIOS_GRP_SEGUROS)){//Grupo 08
			
		}else if(grupo.equals(Constante.PAGO_SERVICIOS_GRP_UNIVERSIDADES)){//Grupo 09
			if(trx.equals(Constante.PAGO_SERVICIOS_TIPO_TRX_CUPON_MN)){//07
				typeTrans = Constante.TIP_OPER_PAGO_UNIVERSIDADES;
			}
			
		}else if(grupo.equals(Constante.PAGO_SERVICIOS_GRP_COLEGIOS)){//Grupo 10
			
		}else if(grupo.equals(Constante.PAGO_SERVICIOS_GRP_EMPRESAS)){//Grupo 11
			if(trx.equals(Constante.PAGO_SERVICIOS_TIPO_TRX_FACTURA_MN)){//09
				typeTrans = Constante.TIP_OPER_PAGO_EMPRESAS_BELLEZA;
			}else if(trx.equals(Constante.PAGO_SERVICIOS_TIPO_TRX_FACTURA_OL_MN)){//12
				typeTrans = Constante.TIP_OPER_PAGO_EMPRESAS_BELLEZA;
			}
			
		}else if(grupo.equals(Constante.PAGO_SERVICIOS_GRP_INTERNET)){//Grupo 12
			
		}else if(grupo.equals(Constante.PAGO_SERVICIOS_GRP_EMP_PUB)){//Grupo 13
			if(trx.equals(Constante.PAGO_SERVICIOS_TIPO_TRX_FACTURA_OL_MN)){//12
				typeTrans = Constante.TIP_OPER_PAGO_INST_PUBLICA;
			}
			
		}else if(grupo.equals(Constante.PAGO_SERVICIOS_GRP_TASAS_EDUCATIVAS)){//Grupo 15
			if(trx.equals(Constante.PAGO_SERVICIOS_TIPO_TRX_CUPON_MN)){//07
				typeTrans = Constante.TIP_OPER_PAGO_UNIVERSIDADES;
			}
		}
		
		
		return typeTrans;
	}
	
	public static boolean enviarCorreo(TokenSmsRequest tokenSms, String urlGeneracion) throws Exception{
		
		
		ObjectMapper mapper = new ObjectMapper();
		
		System.out.println("::::generarClaveSms()");
		System.out.println("tokenSms:::::"+tokenSms);
		System.out.println("urlGeneracion::::"+urlGeneracion);
		System.out.println("tokenSms.getCodCliente()::::"+tokenSms.getCodCliente());
		System.out.println("tokenSms.getTipoDocumento():::"+tokenSms.getTipoDocumento());
		System.out.println("tokenSms.getNroDocumento():::"+tokenSms.getNroDocumento());
		
		String cic = String.format("%012d",Integer.parseInt(tokenSms.getCodCliente()));
		String tipDoc = String.valueOf(Integer.parseInt(tokenSms.getTipoDocumento()));
		String numDoc = String.format("%012d",tokenSms.getNroDocumento());

		Map<String, Object> mapJson = new HashMap<String, Object>();
		Map<String, Object> mapData = new HashMap<String, Object>();
		Map<String, List<Parameters>> mapParameters = new HashMap<String, List<Parameters>>();
		List<Parameters> lstParameters = new ArrayList<Parameters>();
		
		Parameters param = new Parameters();
		param.setCode("[01]");
		param.setCode("15641384");
		lstParameters.add(param);
		
		param = new Parameters();
		param.setCode("[02]");
		param.setCode("Juan Carlos Alzamora Ponce");
		lstParameters.add(param);
		
		param = new Parameters();
		param.setCode("[03]");
		param.setCode("14/10/2019 04:29");
		lstParameters.add(param);
		
		mapParameters.put("parameter", lstParameters);
		
		
		
		/*********************** DATA **************************/
		mapData.put("subject", "Constancia de Registro de Reclamo");
		mapData.put("email", "gromero01@gmail.com");
		mapData.put("emailCC", "gmromerog@canvia.com");
		mapData.put("emailCO", "gmromerog@canvia.com");
		mapData.put("parameter", mapParameters);
		
		/*********************** Json Padre **************************/
		mapJson.put("codeTemplate", cic + tipDoc + numDoc);
		mapJson.put("typeCode", tokenSms.getTypeTrans());
		mapJson.put("typeOp", tokenSms.getTypeOp());
		mapJson.put("data", mapData);			
		
		StringBuilder strBuildJson = new StringBuilder(mapper.writeValueAsString(mapJson));	
		
		JSONObject json = UtilAction.callRestPOSTMapsUsingJSonParser(urlGeneracion+tokenSms.getCodCliente(), strBuildJson);
		
		if(json == null || !json.get("codResult").equals("0000")){
			return false;
		}
		
		return true;
	}
	
	public static MensajesTDC generarListas(String urlGeneracion) {
		MensajesTDC resultado = new MensajesTDC();
		JSONObject json = UtilAction.callRestGET(urlGeneracion);
		
		if(json != null && json.get("codResult").equals("00000")){
			resultado.setCodRptaPrincipal(0);
			resultado.setCodResultOper("1");
			resultado.setCodResult(json.get("codResult").toString());
			resultado.setMsg(json.get("msgError").toString());
			resultado.setDataListas((List<DataListas>)json.get("data"));
			resultado.setExito(true);
		} else if(json != null && !json.get("codResult").equals("0000")){
			resultado.setCodRptaPrincipal(Integer.parseInt(json.get("codResult").toString()));
			resultado.setCodResultOper("0");
			resultado.setCodResult(json.get("codResult").toString());
			resultado.setMsg(json.get("msgError").toString());
			resultado.setExito(false);
		} else {
			resultado.setCodResultOper("0");
			resultado.setExito(false);
		}
		
		return resultado;
	}
	
	
	public static InvitacionesResponse consultarInvitacionesClaveSms(String urlGeneracion, String cic) {
		InvitacionesResponse resultado = new InvitacionesResponse();
		//"bn.url.rest.service.consulta.invitaciones"
		JSONObject json = UtilAction.callRestGET(urlGeneracion.concat(cic));
		JSONObject jsonData = null;
		DataInvitaciones dataInvitaciones = null; 
		if(json != null && json.get("codResult").equals("0000")){
			resultado.setCodResult(json.get("codResult").toString());
			resultado.setMsg(json.get("msg").toString());
			resultado.setMsgError(json.get("msgError").toString());
			
			jsonData = (JSONObject)json.get("data");
			
			if(jsonData !=null ){
				dataInvitaciones = new DataInvitaciones(
					jsonData.get("idclient")!=null?Integer.parseInt(jsonData.get("idclient").toString()):null, 
					jsonData.get("invitationDate")!=null?jsonData.get("invitationDate").toString():null,
					jsonData.get("intrusiveInvitation")!=null?jsonData.get("intrusiveInvitation").toString():null,
					jsonData.get("nonIntrusiveInvitation")!=null?jsonData.get("nonIntrusiveInvitation").toString():null,
					jsonData.get("smsInvitation")!=null?jsonData.get("smsInvitation").toString():null );
				
				resultado.setDataInvitaciones(dataInvitaciones);	
			}
		} else {
			resultado.setCodResult(json.get("codResult").toString());
			resultado.setMsg(json.get("msg").toString());
			resultado.setMsgError(json.get("msgError").toString());
		}
		
		return resultado;
	}
	
	public static boolean validarExisteCodigoErrorMs(String codError){
	
		if(codError.matches("0001|0101|0201|0202|0203|0204|0205|0206|0301|0302|4001|4301|4401|4801|4802|5001|5002|6601|6602|6609")){
			return true;
		}
		
		return false;
		
	}
	
	
	public static CardResponse consultarConfigTarjeta(CardRequest cardRequest, String urlGeneracion) throws Exception, ArrayRuleException{
		JSONObject json = null; 
		JSONObject jsonData = null; 
		ObjectMapper mapper = new ObjectMapper();
		CardResponse cardResponse = null;
		
		MensajesActDesTDC resultado = new MensajesActDesTDC(new DataTDC());
		
		Map<String, String> mapParametros = new HashMap<String, String>();
		mapParametros.put("typeCard", cardRequest.getTypeCard());
		mapParametros.put("numberCard", cardRequest.getNumberCard());
		mapParametros.put("typeClient", cardRequest.getTypeClient());
		
		json = UtilAction.callRestPOSTV2(urlGeneracion, mapParametros);
		
		if(json != null && json.get("codResult").equals("0000")){
			jsonData = (JSONObject)json.get("data");
			cardResponse = mapper.readValue(jsonData.toJSONString(), CardResponse.class);
		} 
		
		return cardResponse;
		
		
	}
	
	public static CardConfSaveResponse guardarConfigTarjeta(CardRequestSave cardRequest, String urlGeneracion) throws JsonGenerationException, JsonMappingException, IOException{
		JSONObject json = null; 
		JSONObject jsonData = null; 
		ObjectMapper mapper = new ObjectMapper();
		CardConfSaveResponse cardConfSaveResponse = null;
		StringBuilder strBuildJson = new StringBuilder(mapper.writeValueAsString(cardRequest));	
		MensajesActDesTDC resultado = new MensajesActDesTDC(new DataTDC());
		json = UtilAction.callRestPOSTMapsConcat(urlGeneracion, strBuildJson);
		
		if(json != null && json.get("codResult").equals("0000")){
			jsonData = (JSONObject)json.get("data");
			cardConfSaveResponse = mapper.readValue(jsonData.toJSONString(), CardConfSaveResponse.class);
		} 
		
		return cardConfSaveResponse;
	}
	
	public static InfoCardResponse consultarInfoCard(InfoCardRequest infoCardRequest, String urlGeneracion) throws JsonGenerationException, JsonMappingException, IOException{
		JSONObject json = null; 
		JSONObject jsonData = null; 
		ObjectMapper mapper = new ObjectMapper();
		InfoCardResponse infoCardResponse = null;
		
		MensajesActDesTDC resultado = new MensajesActDesTDC(new DataTDC());
		
		Map<String, String> mapParametros = new HashMap<String, String>();
		mapParametros.put("typeDoc", infoCardRequest.getTypeDoc());
		mapParametros.put("numberDoc", infoCardRequest.getNumberDoc());
//		mapParametros.put("numberCard", infoCardRequest.getNumberCard());
		
		json = UtilAction.callRestPOSTV2(urlGeneracion, mapParametros);
		
		if(json != null && json.get("codResult").equals("0000")){			
			jsonData = (JSONObject)json.get("data");
			infoCardResponse = mapper.readValue(jsonData.toJSONString(), InfoCardResponse.class);
		} 
		
		return infoCardResponse;
		
	}
	
	
	public static ProductoResponse consultarProductos (String tipoDocumento, String numdocumento) throws JsonParseException, JsonMappingException, IOException{
		
		String urlConsultaProdcutos = Parametro.getString(ConstanteParametros.BN_PARAM_WS_PRODUCTOS);
		
		JSONObject json = null; 
		JSONObject jsonData = null; 
		ObjectMapper mapper = new ObjectMapper();
		ProductoResponse response = new ProductoResponse();
		
		MensajesActDesTDC resultado = new MensajesActDesTDC(new DataTDC());
		
		Map<String, String> mapParametros = new HashMap<String, String>();
		mapParametros.put("tipo_documento", tipoDocumento);
		mapParametros.put("numero_documento", numdocumento);
		
		json = UtilAction.callRestPOSTV3(urlConsultaProdcutos, mapParametros);
		
		response = mapper.readValue(json.toJSONString(), ProductoResponse.class);
		
		//System.out.println("RESPONSE PRODUCTOS::::"+response);
		
		return response;
	}
	
	
	public static SaldoResponse consultaSaldos(String tipoCuenta, String numCuenta) throws JsonParseException, JsonMappingException, IOException{
		
		String urlConsultaSaldos = Parametro.getString(ConstanteParametros.BN_PARAM_WS_SALDOS);
//		String urlConsultaSaldos = "http://10.7.12.75/msconsultasaldo/v1/ConsultaSaldo";
//		
		
		JSONObject json = null; 
		JSONObject jsonData = null; 
		ObjectMapper mapper = new ObjectMapper();
		SaldoResponse response = new SaldoResponse();
		
		MensajesActDesTDC resultado = new MensajesActDesTDC(new DataTDC());

		Map<String, String> mapParametros = new HashMap<String, String>();
		mapParametros.put("codProducto", tipoCuenta);
		mapParametros.put("numProducto", numCuenta);
		
		json = UtilAction.callRestPOSTV3(urlConsultaSaldos, mapParametros);
		
		response = mapper.readValue(json.toJSONString(), SaldoResponse.class);
		
		System.out.println("RESPONSE SALDOS::::"+response);
		
		return response;
	}

}
