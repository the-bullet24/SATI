package pe.cosapi.sarabank.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import com.ibm.ObjectQuery.crud.util.Array;
import com.ibm.ws.scripting.ArrayListAttrHelper;

import pe.bn.telegiro.action.TelegiroAction;
import pe.com.bn.sati.common.LoggerSati;
import pe.cosapi.common.BNAplicacion;
import pe.cosapi.common.Constante;

/**
 * This type was created in VisualAge.
 */
public class Transaction implements Serializable{
	private static LoggerSati log3 = LoggerSati.getInstance(Transaction.class.getName());
	
	public Vector transactionVector;
	public Vector AlternativeVector;
	public Vector SchemaVector=new Vector();
	public String transaction;
	public String transactionHost;
	public String alternative;
	public Map parametro = new HashMap(); 
	//public Vector diccionary;
	public Vector schema;
	public Vector parameters;
	public Vector controls;
	public Vector mascVector=new Vector();
	public Error error;
	public Vector values=new Vector(10);
	public Vector cuentas;
	public Vector mensajes;
	public Vector messages;
	BNAplicacion aplicacion = BNAplicacion.getInstance();
	
	private static java.util.Vector diccionary = new java.util.Vector(50, 100);
	private static java.util.Vector vctTrxJournal = null;
	private static java.util.Vector vctCommsg = null;
	private boolean revMan = false;
	//Datos de la BD
	private static java.util.Vector vctGlobal = null; // Se usa en el Esquema
	private static java.util.Vector vctTctrfas = null;
	
	private Object objeto;
	
	
	
	
    public Object getObjeto() {
        return objeto;
    }
    public void setObjeto(Object objeto) {
        this.objeto = objeto;
    }
/**
 * Transaction constructor comment.
 */
public Transaction() throws Exception{
	super();
	setDiccionary(aplicacion.getDicc());
	setControls(aplicacion.getCont());
	setSchema(aplicacion.getEsq());
	setParameters(aplicacion.getParam());
	//setMessages(aplicacion.getMensajes());
}

public Transaction(String transaction)  throws Exception{
	super();
	setDiccionary(aplicacion.getDicc());
	setControls(aplicacion.getCont());
	setSchema(aplicacion.getEsq());
	setParameters(aplicacion.getParam());
	//setMessages(aplicacion.getMensajes());
	setTransaction(transaction);
}

public Transaction(String transaction,Vector valores,Vector ctas)  throws Exception{
	super();
	setDiccionary(aplicacion.getDicc());
	setControls(aplicacion.getCont());
	setSchema(aplicacion.getEsq());
	setParameters(aplicacion.getParam());
	//setMessages(aplicacion.getMensajes());
	setTransaction(transaction);
	this.values 	= valores;
	this.cuentas 	= ctas;
}

/**
 * This method was created in VisualAge.
 * @return Vector
 * @param getTransactionVector() Vector
 */
public Vector alternativeFilter(String alternative) throws Exception{
	Vector temp=new Vector(10);
	for (int i=0;i<getTransactionVector().size();++i)
	{
		if (((Vector)getTransactionVector().elementAt(i)).elementAt(4).toString().equals(alternative))
		{
			Vector temp1=new Vector(10);
			temp1.addElement(((Vector)getTransactionVector().elementAt(i)).elementAt(0));
			temp1.addElement(((Vector)getTransactionVector().elementAt(i)).elementAt(1));
			temp1.addElement(((Vector)getTransactionVector().elementAt(i)).elementAt(2));
			temp1.addElement(((Vector)getTransactionVector().elementAt(i)).elementAt(3));
			temp1.addElement(((Vector)getTransactionVector().elementAt(i)).elementAt(4));
			temp1.addElement(((Vector)getTransactionVector().elementAt(i)).elementAt(5));
			temp1.addElement(((Vector)getTransactionVector().elementAt(i)).elementAt(6));
			temp1.addElement(((Vector)getTransactionVector().elementAt(i)).elementAt(7));
			temp1.addElement(((Vector)getTransactionVector().elementAt(i)).elementAt(8));
			temp1.addElement(((Vector)getTransactionVector().elementAt(i)).elementAt(9));
			temp1.addElement(((Vector)getTransactionVector().elementAt(i)).elementAt(10));
			temp1.addElement(((Vector)getTransactionVector().elementAt(i)).elementAt(11));
			temp1.addElement(((Vector)getTransactionVector().elementAt(i)).elementAt(12));
			temp1.addElement(((Vector)getTransactionVector().elementAt(i)).elementAt(13));
			temp.addElement(temp1);
		}
	}
	return temp;
}

/**
 * This method was created in VisualAge.
 * @return Vector
 */
public Vector buildMascVector() throws Exception{
		int jk=0;
		

//		System.out.println("***********CONTROLS*****"+getControls());
//		System.out.println("***********DICTIONARY***"+getDiccionary());
//		System.out.println("***********VALUES*******"+getValues());
		
		for (int i=0;i<getControls().size();++i)
		{
			if (((Vector)getControls().elementAt(i)).elementAt(0).toString().equals(getTransaction()))
			{				
				Vector temp=new Vector(10);

				temp.addElement(((Vector)getControls().elementAt(i)).elementAt(1));//codigo de diccionario
				temp.addElement(((Vector)getControls().elementAt(i)).elementAt(2));//codigo de canal
				temp.addElement(((Vector)getControls().elementAt(i)).elementAt(3));//codigo de tipo de control html (combo, textfield, etc)
				for (int j=0;j<getDiccionary().size();j++)
				{

					if (((Vector)getControls().elementAt(i)).elementAt(1).toString().equals(((Vector)getDiccionary().elementAt(j)).elementAt(0)))
					{
						temp.addElement(((Vector)getDiccionary().elementAt(j)).elementAt(1));
						temp.addElement(((Vector)getDiccionary().elementAt(j)).elementAt(2));
						temp.addElement(((Vector)getDiccionary().elementAt(j)).elementAt(3));
						jk=j;
						break;
					}
				}
				for (int k=0;k<getValues().size();k++)
				{
					if (((Vector)getControls().elementAt(i)).elementAt(4).toString().equals(((Vector)getValues().elementAt(k)).elementAt(0)))
					{
						temp.addElement(((Vector)getValues().elementAt(k)).elementAt(1));
					}
				}

				temp.addElement(((Vector)getControls().elementAt(i)).elementAt(5));//null
				temp.addElement(((Vector)getDiccionary().elementAt(jk)).elementAt(4));//idedic
				temp.addElement(((Vector)getControls().elementAt(i)).elementAt(4));//txtCtr
				if (temp.size()>8) getMascVector().addElement(temp);
			}
		}
		
		//if(Constante.VER_LOG) System.out.println("****************************getMascVector() "+getMascVector());
		
		//yaddif a12 - PARA ENVIAR EL ARCHIVO DE ID. PARA LAS TRAMAS
		/*
		Vector temp=new Vector(10);
		temp.addElement("idTrama");
		temp.addElement("999");
		temp.addElement("Id de la trama");
		temp.addElement("Id de la trama");
		temp.addElement("23");
		temp.addElement("99999999999999999999999");
		temp.addElement("valor a ser cambiado");
		temp.addElement("0");
		temp.addElement("A");
		temp.addElement("00");
		getMascVector().addElement(temp);
		*/
		return getMascVector();
}

/**
 * This method was created in VisualAge.
 * @return Vector
 */
public Vector buildSchemaVector()  throws Exception{
	setTransactionVector(transactionFilter(getSchema()));
	//if(Constante.VER_LOG) System.out.println("***********TRANSACTION VECTOR*****"+getTransactionVector());
	//if(Constante.VER_LOG) System.out.println("***********TRANSACTION VECTOR*****"+getTransactionVector());
	if (!((Vector)getTransactionVector().elementAt(0)).elementAt(4).equals("00"))
	{
		String clase=setAlternativeClass(getTransaction(),((Vector)getTransactionVector().elementAt(0)).elementAt(5).toString(),((Vector)getTransactionVector().elementAt(0)).elementAt(6).toString(),getParameters());
		String metodo=setAlternativeMethod(getTransaction(),((Vector)getTransactionVector().elementAt(0)).elementAt(5).toString(),((Vector)getTransactionVector().elementAt(0)).elementAt(6).toString(),getParameters());
		setAlternative(executeMethod(clase,metodo,setMethodParameters(getTransaction(),((Vector)getTransactionVector().elementAt(0)).elementAt(5).toString(),((Vector)getTransactionVector().elementAt(0)).elementAt(6).toString(),getParameters())));
		setAlternativeVector(alternativeFilter(getAlternative()));
		setSchemaVector(filterSchemaVector(getAlternativeVector()));
	}
	else
	{
		setSchemaVector(filterSchemaVector(getTransactionVector()));
	}
		return null;
}

/**
 * This method was created in VisualAge.
 * @return java.lang.String
 * @param clas java.lang.String
 * @param method java.lang.String
 * @param parameters java.lang.Object[]
 */

public String executeMethod(String clas, String method, Object[] parameters) throws Exception {
	Object retobj = "";
	Class partypes[] = new Class[parameters.length];
	for (int i = 0; i < parameters.length - 2; ++i) {
		partypes[i] = "any".getClass();
	}
	if(Constante.VER_LOG) System.out.println("Invocado "+clas+"."+method+"********"+parameters+"******"+parameters.length);
	
	for(int i = 0 ; i < parameters.length; i++){
		//if(Constante.VER_LOG) System.out.println("*****CLASE: "+parameters[i].getClass().toString()+"*****************VALOR :"+parameters[i]);			
	}
	setTransactionHost((String)parameters[0]);
	partypes[parameters.length - 1] = new Vector().getClass();
	partypes[parameters.length - 2] = new Vector().getClass();
	Class cls = Class.forName(clas); // 1ra forma
	java.lang.reflect.Method meth = cls.getMethod(method, partypes);
	retobj = meth.invoke(cls.newInstance(), parameters);
	return ((String) retobj);
}

//public String executeMethod(String clas, String method, Object[] parameters) throws Exception {
//	Object retobj = "";
//	Class partypes[] = new Class[parameters.length];
//	for (int i = 0; i < parameters.length - 2; ++i) {
//		partypes[i] = "any".getClass();
//	}
//	//if(Constante.VER_LOG) System.out.println("Invocado "+clas+"."+method+"********"+parameters+"******"+parameters.length);
//	
//	for(int i = 0 ; i < parameters.length; i++){
//		//if(Constante.VER_LOG) System.out.println("*****CLASE: "+parameters[i].getClass().toString()+"*****************VALOR :"+parameters[i]);			
//	}
//	setTransactionHost((String)parameters[0]);
//	partypes[parameters.length - 1] = new Vector().getClass();
//	partypes[parameters.length - 2] = new Vector().getClass();
//	Class cls = Class.forName(clas); // 1ra forma
//	java.lang.reflect.Method meth = cls.getMethod(method, partypes);
//	
//	try {
//		retobj = meth.invoke(cls.newInstance(), parameters);
//	} catch (Exception e) {
//		
//		Vector prueba = (Vector) parameters[3];		
//		
//		for (int i=0 ; i<=prueba.size() ; i++){
//				System.out.println(prueba.elementAt(i));
//		}
//		
//		throw e;
//	}
//	
//	
//	return ((String) retobj);
//}

/**
 * This method was created in VisualAge.
 * @exception beans.Error The exception description.
 */
public void executeSchema() throws Exception {
	try{
	for (int i=0;i<getSchemaVector().size();i++)
	{
		executeMethod(((Vector)getSchemaVector().elementAt(i)).elementAt(0).toString(),((Vector)getSchemaVector().elementAt(i)).elementAt(1).toString(),setObjectMethod(((Vector)((Vector)getSchemaVector().elementAt(i)).elementAt(2))));
		
		//if(Constante.VER_LOG) System.out.println("*****************FIN*******************");
	}
	}
	catch(Exception e)
	{
		log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
		System.out.println(""+e);
		throw e;
	}
}

/**
 * This method was created in VisualAge.
 * @return Vector
 */
public Vector filterSchemaVector(Vector data) {
	int sw=0;
	int cont;
	for (int i=0;i<data.size();i++)
	{
		Vector temp=new Vector();
		temp.addElement(((Vector)data.elementAt(i)).elementAt(10));
		temp.addElement(((Vector)data.elementAt(i)).elementAt(11));
		cont=i;
		Vector temp1=new Vector();
		while((((Vector)data.elementAt(cont)).elementAt(8).toString().equals(((Vector)data.elementAt(i)).elementAt(8).toString()) && ((Vector)data.elementAt(cont)).elementAt(9).toString().equals(((Vector)data.elementAt(i)).elementAt(9).toString())) && ((Vector)data.elementAt(cont)).elementAt(7).toString().equals(((Vector)data.elementAt(i)).elementAt(7).toString()))
		{
			temp1.addElement(((Vector)data.elementAt(i)).elementAt(13));
			if (i<data.size()-1) i++;
			else {sw=1;break;}
		}
		if (sw!=1) i--;
		temp.addElement(temp1);
		getSchemaVector().addElement(temp);
	}
	
	return getSchemaVector();
}

/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getAlternative() {
	return alternative;
}

/**
 * This method was created in VisualAge.
 * @return Vector
 */
public Vector getAlternativeVector() {
	return AlternativeVector;
}

/**
 * This method was created in VisualAge.
 * @return Vector
 */
public Vector getControls() {
	return controls;
}

/**
 * This method was created in VisualAge.
 * @return Vector
 */
public Vector getCuentas() {
	return cuentas;
}

/**
 * This method was created in VisualAge.
 * @return Vector
 */
public Vector getDiccionary() {
	return diccionary;
}

/**
 * This method was created in VisualAge.
 * @return beans.Error
 */
public Error getError() {
	return error;
}

/**
 * This method was created in VisualAge.
 * @return Vector
 */
public Vector getMascVector() {
	return mascVector;
}

/**
 * This method was created in VisualAge.
 * @return Vector
 */
public Vector getMensajes() {
	return mensajes;
}

/**
 * This method was created in VisualAge.
 * @return Vector
 */
public Vector getMessages() {
	return messages;
}

/**
 * This method was created in VisualAge.
 * @return Vector
 */
public Vector getParameters() {
	return parameters;
}

/**
 * This method was created in VisualAge.
 * @return Vector
 */
public Vector getSchema() {
	return schema;
}

/**
 * This method was created in VisualAge.
 * @return Vector
 */
public Vector getSchemaVector() {
	return SchemaVector;
}

/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getTransaction() {
	return transaction;
}

/**
 * This method was created in VisualAge.
 * @return Vector
 */
public Vector getTransactionVector() {
	return transactionVector;
}
/**
 * This method was created in VisualAge.
 * @return Vector
 */
public Vector getValues() {
	return values;
}
/**
 * This method was created in VisualAge.
 */
public void loadVectors() throws Exception{
	//if(Constante.VER_LOG) System.out.println("|------| paso 1");
	buildMascVector();
	//if(Constante.VER_LOG) System.out.println("|------| paso 2");
	buildSchemaVector();
	//if(Constante.VER_LOG) System.out.println("|------| paso 3 -");
	executeSchema();
	//if(Constante.VER_LOG) System.out.println("-----------FINALIZO LOS #3 PASOS----------");
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 * @param dicc java.lang.String
 */
public static void putValuebyDicc(Object value,String dicc,Vector data) throws Exception{
	try{
		for (int i=0;i<data.size();++i){
			if (((Vector)data.elementAt(i)).elementAt(1).toString().equals(dicc)){
				((Vector)data.elementAt(i)).setElementAt(value,6);
			}
		}
	}
	catch(Exception e){
		log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
		e.printStackTrace();	
		throw new Exception();
	}
}

/**
 * This method was created in VisualAge.
 * @return java.lang.String
 * @param dicc java.lang.String
 */
public static String searchDescriptionbyDicc(String dicc,Vector data) throws Exception{
	String res="";
	try{
	for (int i=0;i<data.size();++i)
	{
		if (((Vector)data.elementAt(i)).elementAt(1).toString().equals(dicc))
		{
			res=((Vector)data.elementAt(i)).elementAt(2).toString();
		}

	
	}
	}
	catch(Exception e){
		log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
		throw new Exception();
		}
	return res;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 * @param dicc java.lang.String
 */
public static String searchFormatbyDicc(String dicc,Vector data) throws Exception{
	String res="";
	try{
	for (int i=0;i<data.size();++i)
	{
		if (((Vector)data.elementAt(i)).elementAt(1).toString().equals(dicc))
		{
			res=((Vector)data.elementAt(i)).elementAt(5).toString();
		}

	
	}
	}
	catch(Exception e){
		log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
		throw new Exception();
		}
	return res;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 * @param dicc java.lang.String
 */
public static Object searchFormatValuebyDicc(String dicc,Vector data) throws Exception{
	String res="";
	try{
	for (int i=0;i<data.size();++i)
	{
		if (((Vector)data.elementAt(i)).elementAt(1).toString().equals(dicc))
		{
			res=((Vector)data.elementAt(i)).elementAt(10).toString();
		}

	
	}
	}
	catch(Exception e){
		log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
		throw new Exception();
		}
	return res;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 * @param dicc java.lang.String
 */
public static int searchIndexbyDicc(String dicc,Vector data) throws Exception{
	try{
	for (int i=0;i<data.size();++i)
	{
		if (((Vector)data.elementAt(i)).elementAt(1).toString().equals(dicc))
		{
			return i;
		}

	
	}
	}
	catch(Exception e){
		log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
		throw new Exception();
		}
	return 0;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 * @param dicc java.lang.String
 */
public static Object searchMessagebyCode(String code,Vector data) throws Exception{
	String res="";
	for (int i=0;i<data.size();++i){
		if (((Vector)data.elementAt(i)).elementAt(1).toString().equals(code)){
			res=((Vector)data.elementAt(i)).elementAt(2).toString();
		}	
	}
	return res;
}

public Object searchMessagebyCode(String code) throws Exception{
	String res="";
	Vector data = getMessages();
	for (int i=0;i<data.size();++i)	{
		if (((Vector)data.elementAt(i)).elementAt(1).toString().equals(code)){
			res=((Vector)data.elementAt(i)).elementAt(2).toString();
		}	
	}
	return res;
}


/**
 * This method was created in VisualAge.
 * @return java.lang.String
 * @param dicc java.lang.String
 */
public String searchValuebyDicc(String dicc) throws Exception{
	String res="";
	try{
	for (int i=0;i<getMascVector().size();++i)
	{
		if (((Vector)getMascVector().elementAt(i)).elementAt(1).toString().equals(dicc))
		{
			res=((Vector)getMascVector().elementAt(i)).elementAt(7).toString();
		}

	
	}
	}
	catch(Exception e){
		log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
		throw new Exception();
		}
	return res;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 * @param dicc java.lang.String
 */
public static Object searchValuebyDicc(String dicc,Vector data) throws Exception{
	String res="";
	
	for (int i=0;i<data.size();++i){
		if (((Vector)data.elementAt(i)).elementAt(1).toString().equals(dicc)){
			res=((Vector)data.elementAt(i)).elementAt(6).toString();
		}
	}	
	return res;
}

/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setAlternative(String newValue) {
	this.alternative = newValue;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.Object[]
 * @param transaction java.lang.String
 * @param clas java.lang.String
 * @param method java.lang.String
 */
public String setAlternativeClass (String transaction, String clas, String method, Vector data) throws Exception{
	for (int i=0;i<data.size();i++)
	{
		if ((((Vector)data.elementAt(i)).elementAt(0).toString().equals(transaction)) && (((Vector)data.elementAt(i)).elementAt(2).toString().equals(clas)) && (((Vector)data.elementAt(i)).elementAt(1).toString().equals(method)))
		{
			return ((Vector)data.elementAt(i)).elementAt(3).toString();
			//temp1.addElement(((Vector)data.elementAt(i)).elementAt(4));
		}
	}
	return null;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.Object[]
 * @param transaction java.lang.String
 * @param clas java.lang.String
 * @param method java.lang.String
 */
public String setAlternativeMethod (String transaction, String clas, String method, Vector data) throws Exception{
	for (int i=0;i<data.size();i++)
	{
		if ((((Vector)data.elementAt(i)).elementAt(0).toString().equals(transaction)) && (((Vector)data.elementAt(i)).elementAt(2).toString().equals(clas)) && (((Vector)data.elementAt(i)).elementAt(1).toString().equals(method)))
		{
			return ((Vector)data.elementAt(i)).elementAt(4).toString();
			//temp1.addElement(((Vector)data.elementAt(i)).elementAt(4));
		}
	}
	return null;
}
/**
 * This method was created in VisualAge.
 * @param newValue Vector
 */
public void setAlternativeVector(Vector newValue) {
	this.AlternativeVector = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue Vector
 */
public void setControls(Vector newValue) {
	this.controls = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue Vector
 */
public void setCuentas(Vector newValue) {
	this.cuentas = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue Vector
 */
public void setDiccionary(Vector newValue) {
	this.diccionary = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue beans.Error
 */
public void setError(Error newValue) {
	this.error = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue Vector
 */
public void setMascVector(Vector newValue) {
	this.mascVector = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue Vector
 */
public void setMensajes(Vector newValue) {
	this.mensajes = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue Vector
 */
public void setMessages(Vector newValue) {
	this.messages = newValue;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.Object[]
 * @param transaction java.lang.String
 * @param clas java.lang.String
 * @param method java.lang.String
 */
public Object[] setMethodParameters (String transaction, String clas, String method, Vector data) throws Exception{
	Vector temp1=new Vector();
	for (int i=0;i<data.size();i++)
	{
		if ((((Vector)data.elementAt(i)).elementAt(0).toString().equals(transaction)) && (((Vector)data.elementAt(i)).elementAt(2).toString().equals(clas)) && (((Vector)data.elementAt(i)).elementAt(1).toString().equals(method)))
		{
			if(!parametro.containsKey(((Vector)data.elementAt(i)).elementAt(6))){
				temp1.addElement(((Vector)data.elementAt(i)).elementAt(6));
				parametro.put(((Vector)data.elementAt(i)).elementAt(6),((Vector)data.elementAt(i)).elementAt(6));
				break;
			}
				
		}
	}
	Object[] temp2=new Object[temp1.size()+2];
	for (int i=0;i<temp1.size();++i)
	{
		temp2[i]=temp1.elementAt(i).toString();		
	}
	temp2[temp1.size()]=getMascVector();
	temp2[temp1.size()+1]=getCuentas();

	
	if (clas.equalsIgnoreCase("047") || method.equalsIgnoreCase("001")){
		//System.out.println(">>SendHost");
		if (temp2.length >6){
			//System.out.println(">>Aqui");
		}
	}
	
	return temp2;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.Object[]
 * @param temp Vector
 */
public Object[] setObjectMethod(Vector temp) {
	Object[] res=new Object[temp.size()+2];
	for (int i=0;i<temp.size();++i)
	{
		res[i]=temp.elementAt(i).toString();
	}
	res[temp.size()]=getMascVector();
	res[temp.size()+1]=getCuentas();
	return res;
}
/**
 * This method was created in VisualAge.
 * @param newValue Vector
 */
public void setParameters(Vector newValue) {
	this.parameters = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue Vector
 */
public void setSchema(Vector newValue) {
	this.schema = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue Vector
 */
public void setSchemaVector(Vector newValue) {
	this.SchemaVector = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setTransaction(String newValue) {
	this.transaction = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue Vector
 */
public void setTransactionVector(Vector newValue) {
	this.transactionVector = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue Vector
 */
public void setValues(Vector newValue) {
	this.values = newValue;
}
/**
 * This method was created in VisualAge.
 * @return Vector
 * @param data Vector
 */
public Vector transactionFilter(Vector data) throws Exception{
	Vector temp=new Vector(20);
	for (int i=0;i<data.size();++i)
	{
		if (((Vector)data.elementAt(i)).elementAt(0).toString().equals(getTransaction()))
		{
			Vector temp1=new Vector(20);
			temp1.addElement(((Vector)data.elementAt(i)).elementAt(0));
			temp1.addElement(((Vector)data.elementAt(i)).elementAt(1));
			temp1.addElement(((Vector)data.elementAt(i)).elementAt(2));
			temp1.addElement(((Vector)data.elementAt(i)).elementAt(3));
			temp1.addElement(((Vector)data.elementAt(i)).elementAt(4));
			temp1.addElement(((Vector)data.elementAt(i)).elementAt(5));
			temp1.addElement(((Vector)data.elementAt(i)).elementAt(6));
			temp1.addElement(((Vector)data.elementAt(i)).elementAt(7));
			temp1.addElement(((Vector)data.elementAt(i)).elementAt(8));
			temp1.addElement(((Vector)data.elementAt(i)).elementAt(9));
			temp1.addElement(((Vector)data.elementAt(i)).elementAt(10));
			temp1.addElement(((Vector)data.elementAt(i)).elementAt(11));
			temp1.addElement(((Vector)data.elementAt(i)).elementAt(12));
			temp1.addElement(((Vector)data.elementAt(i)).elementAt(13));
			temp.addElement(temp1);
		}
	}
	return temp;
}

/**
 * This method was created in VisualAge.
 * @return java.lang.Object
 */
public static Object getMaskGlobal() {
	if (getVctGlobal().size() < 2)
		return null;
	return getVctGlobal().elementAt(1);
}

/**
 * This method was created in VisualAge.
 * @return java.util.Vector
 */
public static java.util.Vector getVctGlobal() {
	if (vctGlobal == null)
		vctGlobal = new java.util.Vector(5, 10);
	return vctGlobal;
}

public static Object getLoginGlobal() {
	if (getVctGlobal().size() < 1)
		return null;
	return getVctGlobal().elementAt(0);
}

	/**
	 * @return Devuelve transactionHost.
	 */
	public String getTransactionHost() {
		if(transactionHost!=null){
			if(this.transactionHost.length()>4){
				return this.transactionHost.substring(0,4);
			}else
				return this.transactionHost;
		}else
			return this.transactionHost;
	}
	/**
	 * @param transactionHost El transactionHost a establecer.
	 */
	public void setTransactionHost(String transactionHost) {
		this.transactionHost = transactionHost;
	}
}