package pe.cosapi.common;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionError;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.tools.generic.DateTool;
import org.apache.velocity.tools.generic.NumberTool;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.ui.velocity.VelocityEngineUtils;

import pe.cosapi.action.form.MailForm;

/**
 * @author cosapi_ati04
 * 
 * TODO Para cambiar la plantilla de este comentario generado, vaya a Ventana -
 * Preferencias - Java - Estilo de código - Plantillas de código
 */
public class Mail implements Serializable{
	
	private String from;
	
	private String to;

	private String CC;

	private String BCC;

	private String subject;
	
	private String nota;

	private JavaMailSender mailSender;

	private VelocityEngine velocityEngine;

	private String plantilla;
	
	private MailForm mailUsuario;
	private Map mapDetalle;
	
	
	
	public String getNota() {
		return nota;
	}

	public void setNota(String nota) {
		this.nota = nota;
	}

	/**
	 * @return Devuelve from.
	 */
	public String getFrom() {
		return from;
	}

	/**
	 * @param from
	 *            El from a establecer.
	 */
	public void setFrom(String from) {
		this.from = from;
	}

	/**
	 * @return Devuelve mailSender.
	 */
	public JavaMailSender getMailSender() {
		return mailSender;
	}

	/**
	 * @param mailSender
	 *            El mailSender a establecer.
	 */
	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	/**
	 * @return Devuelve subject.
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @param subject
	 *            El subject a establecer.
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @return Devuelve velocityEngine.
	 */
	public VelocityEngine getVelocityEngine() {
		return velocityEngine;
	}

	/**
	 * @param velocityEngine
	 *            El velocityEngine a establecer.
	 */
	public void setVelocityEngine(VelocityEngine velocityEngine) {
		this.velocityEngine = velocityEngine;
	}

	public void sendMail(final Object o) throws Exception {
	    
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			
			public void prepare(MimeMessage mimeMessage) throws Exception {
								
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
			    
				String delim=";";
				
				String to = getTo();
				to = ObjectUtil.replaceChar(to,',',";"); 
				
				String[] arregloMails = ObjectUtil.getArrayStrings(to,delim);
				
				message.setFrom(getFrom());
				message.setTo(arregloMails);
				message.setCc(arregloMails);
				message.setBcc(arregloMails);
				message.setSubject(getSubject());
				
				String cc = getCC();
				
				String bc = getBCC();
				
				cc = ObjectUtil.replaceChar(cc,',',";"); 
				String[] arregloCc = ObjectUtil.getArrayStrings(cc,delim);
				
				bc = ObjectUtil.replaceChar(bc,',',";");  
				String[] arregloBc = ObjectUtil.getArrayStrings(bc,delim);
				
				if(getCC()!=null&&!getCC().equals("")){
					message.setCc(arregloCc);
				}
				
				if(getBCC()!=null&&!getBCC().equals("")){
					message.setBcc(arregloBc);
				}
			  					
				Map model = new HashMap();
				model.put("param", o);
				model.put("date",new DateTool());
				model.put("number", new NumberTool());
				String text = VelocityEngineUtils.mergeTemplateIntoString(
						velocityEngine, 
						getPlantilla(),
						model);				
				message.setText(ObjectUtil.replaceIlegalCharacterToSendMail(text), true);
				
			}
		};
		this.mailSender.send(preparator);
	}

	public void sendMail(final Map map) throws Exception {
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {
				
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
				
				String delim=";";
				
				String to = getTo();
				to = ObjectUtil.replaceChar(to,',',";"); 
				
				String[] arregloMails = ObjectUtil.getArrayStrings(to,delim);
				
				message.setFrom(getFrom());
				message.setTo(arregloMails);
				message.setSubject(getSubject());
				
				String cc = getCC();
				
				cc = ObjectUtil.replaceChar(cc,',',";"); 
				String[] arregloCc = ObjectUtil.getArrayStrings(cc,delim);
				
				if(getCC()!=null&&!getCC().equals("")){
					message.setCc(arregloCc);
				}
						
				Map model = new HashMap();
				for (Iterator iter = map.keySet().iterator(); iter.hasNext();){ 
				    Object object = iter.next();
				    model.put(object,map.get(object));
				}
				model.put("date",new DateTool());
				model.put("number", new NumberTool());
				String text = VelocityEngineUtils.mergeTemplateIntoString(
						velocityEngine, 
						getPlantilla(),
						model);				
				message.setText(ObjectUtil.replaceIlegalCharacterToSendMail(text), true);
				
			}
		};
		this.mailSender.send(preparator);
	}
	
	public void sendMailConstancia(final Map map, HttpServletRequest request) throws Exception {
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {
				
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
				
				String delim=";";
				
				
				String to = mailUsuario.getTo();
								
			
				to = ObjectUtil.replaceChar(to,',',";"); 
				
				String[] arregloMails = ObjectUtil.getArrayStrings(to,delim);
				
				message.setFrom(getFrom());
				message.setTo(arregloMails);
				message.setSubject(getSubject());
				
				//String cc = getCC();
				String cc = mailUsuario.getCc();
				
				cc = ObjectUtil.replaceChar(cc,',',";"); 
				
				String[] arregloCc = ObjectUtil.getArrayStrings(cc,delim);
				
				if(getCC()!=null&&!getCC().equals("")){
					message.setCc(arregloCc);
				}
				
				mapDetalle = mailUsuario.getMailDetalle();
				
								
				Map model = new HashMap();
				for (Iterator iter = mapDetalle.keySet().iterator(); iter.hasNext();){ 
				    Object object = iter.next();
				    model.put(object,mapDetalle.get(object));
				}
				model.put("date",new DateTool());
				model.put("number", new NumberTool());
				
				
						
				String text = VelocityEngineUtils.mergeTemplateIntoString(
						velocityEngine, 
						getPlantilla(),
						model);

				
				String newText = null;
				String nota = null;
						
							
				String notaConstancia=  VelocityEngineUtils.mergeTemplateIntoString(
						velocityEngine, 
						"/WEB-INF/plantilla/notaConstancia.html",
						model);
				
				
				int finCons = notaConstancia.lastIndexOf("Mensaje");
				
				nota = notaConstancia.substring(0,finCons) + getNota() + notaConstancia.substring(finCons+7);
				
				
				
				//Modifica plantilla original, agrega la  Nota
				
				int fin = text.lastIndexOf("</BODY>");
				newText = text.substring(0, fin) + nota+ text.substring(fin);
				
				if(!getNota().trim().equals("")){
					message.setText(ObjectUtil.replaceIlegalCharacterToSendMail(newText), true);	
				}
				else{
					message.setText(ObjectUtil.replaceIlegalCharacterToSendMail(text), true);
				}
				
			}
		};
		

		mailUsuario = (MailForm)request.getSession().getAttribute(ConstanteSesion.MAIL_CONSTANCIA);

	
		setCC(mailUsuario.getCc());


		this.mailSender.send(preparator);
	}
	
	public void sendMailConstanciaNota(final Map map, HttpServletRequest request) throws Exception {
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {
				
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
				
				String delim=";";
				
				
				String to = mailUsuario.getTo();
								
			
				to = ObjectUtil.replaceChar(to,',',";"); 
				
				String[] arregloMails = ObjectUtil.getArrayStrings(to,delim);
				
				message.setFrom(getFrom());
				message.setTo(arregloMails);
				message.setSubject(getSubject());
				
				//String cc = getCC();
				String cc = mailUsuario.getCc();
				
				cc = ObjectUtil.replaceChar(cc,',',";"); 
				
				String[] arregloCc = ObjectUtil.getArrayStrings(cc,delim);
				
				if(getCC()!=null&&!getCC().equals("")){
					message.setCc(arregloCc);
				}
				
				mapDetalle = mailUsuario.getMailDetalle();
				
								
				Map model = new HashMap();
				for (Iterator iter = mapDetalle.keySet().iterator(); iter.hasNext();){ 
				    Object object = iter.next();
				    model.put(object,mapDetalle.get(object));
				}
				model.put("date",new DateTool());
				model.put("number", new NumberTool());
				
				
						
				String text = VelocityEngineUtils.mergeTemplateIntoString(
						velocityEngine, 
						getPlantilla(),
						model);

				
				String newText = null;
				String nota = null;
						
							
				String notaConstancia=  VelocityEngineUtils.mergeTemplateIntoString(
						velocityEngine, 
						"/WEB-INF/plantilla/notaConstanciaAutomatico.html",
						model);
				
				
				int finCons = notaConstancia.lastIndexOf("Mensaje");
				
				nota = notaConstancia.substring(0,finCons) + getNota() + notaConstancia.substring(finCons+7);
				
				
				
				//Modifica plantilla original, agrega la  Nota
				
				int fin = text.lastIndexOf("</BODY>");
				newText = text.substring(0, fin) + nota+ text.substring(fin);
				
				if(!getNota().trim().equals("")){
					message.setText(ObjectUtil.replaceIlegalCharacterToSendMail(newText), true);	
				}
				else{
					message.setText(ObjectUtil.replaceIlegalCharacterToSendMail(text), true);
				}
				
			}
		};
		

		mailUsuario = (MailForm)request.getSession().getAttribute(ConstanteSesion.MAIL_CONSTANCIA);

	
		setCC(mailUsuario.getCc());


		this.mailSender.send(preparator);
	}
	
	public void sendMailConstanciaAutomatico(final Map map, HttpServletRequest request)  {
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {
				
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
				
				String delim=";";
				
				
				String to = mailUsuario.getTo();
				
			
				to = ObjectUtil.replaceChar(to,',',";"); 
				
				String[] arregloMails = ObjectUtil.getArrayStrings(to,delim);
				
				message.setFrom(getFrom());
				message.setTo(arregloMails);
				message.setSubject(getSubject());
				
				//String cc = getCC();
				String cc = mailUsuario.getCc();
				
				cc = ObjectUtil.replaceChar(cc,',',";"); 
				
				String[] arregloCc = ObjectUtil.getArrayStrings(cc,delim);
				
				if(getCC()!=null&&!getCC().equals("")){
					message.setCc(arregloCc);
				}
				
				mapDetalle = mailUsuario.getMailDetalle();
				
								
				Map model = new HashMap();
				for (Iterator iter = mapDetalle.keySet().iterator(); iter.hasNext();){ 
				    Object object = iter.next();
				    model.put(object,mapDetalle.get(object));
				}
				model.put("date",new DateTool());
				model.put("number", new NumberTool());
				
				String text = VelocityEngineUtils.mergeTemplateIntoString(
						velocityEngine, 
						getPlantilla(),
						model);
				message.setText(ObjectUtil.replaceIlegalCharacterToSendMail(text), true);	
						
			
				

			}
		};
		

		mailUsuario = (MailForm)request.getSession().getAttribute(ConstanteSesion.MAIL_CONSTANCIA);

	
		setCC(mailUsuario.getCc());


		this.mailSender.send(preparator);
	}
	/**
	 * @return Devuelve bCC.
	 */
	public String getBCC() {
		return BCC;
	}

	/**

				
	 * @param bcc
	 *            El bCC a establecer.
	 */
	public void setBCC(String bcc) {
		BCC = bcc;
	}

	/**
	 * @return Devuelve cC.
	 */
     public String getCC() {
		return CC;
	}

	/**
	 * @param cc
	 *            El cC a establecer.
	 */
	public void setCC(String cc) {
		CC = cc;
	}

	/**
	 * @return Devuelve to.
	 */
	public String getTo() {
		return to;
	}

	/**
	 * @param to
	 *            El to a establecer.
	 */
	
	public void setTo(String to) {
		this.to = to;
	}

	
	
	/**
	 * @return Devuelve plantilla.
	 */
	public String getPlantilla() {
		return plantilla;
	}

	/**
	 * @param plantilla
	 *            El plantilla a establecer.
	 */
	public void setPlantilla(String plantilla) {
		this.plantilla = plantilla;
	}
	/**
	 * @return Devuelve cuerpo.
	 */
	public String getCuerpo(Object o) throws Exception{
		Map model = new HashMap();
		model.put("param", o);		
		model.put("date",new DateTool());
		model.put("number", new NumberTool());
		String text = VelocityEngineUtils.mergeTemplateIntoString(
				velocityEngine, 
				getPlantilla(),
				model);
		return text;

	}

	public String getCuerpo(Map map) throws Exception{
		Map model = new HashMap();
		for (Iterator iter = map.keySet().iterator(); iter.hasNext();){ 
		    Object object = iter.next();
		    model.put(object,map.get(object));
		}
		model.put("date",new DateTool());
		model.put("number", new NumberTool());
		String text = VelocityEngineUtils.mergeTemplateIntoString(
				velocityEngine, 
				getPlantilla(),
				model);
		return text;

	}

}


