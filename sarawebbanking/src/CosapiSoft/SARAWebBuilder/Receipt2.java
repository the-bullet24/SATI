package CosapiSoft.SARAWebBuilder;

/**
 * This type was created in VisualAge.
 */
import CosapiSoft.SARAWebBanking.*;
public class Receipt2 extends CosapiSoft.SARAWebBanking.JavaServlet {
/**
 * Transfer constructor comment.
 */
public Receipt2() {
	super();
}
public void doPost(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse res) {
	try {
		if (!isLoggedIn(req)) {
			try {
				req.getSession(false).setAttribute("error",new Exception(Mensajes.getMessage(Mensajes.SESION_TERMINADA) + " - " + this.getClass().toString()));         
				callPage(req, res, JspServlet.ERROR_JSP);
			}
			catch (javax.servlet.ServletException se) {
				res.getWriter().println(se.getMessage());
			}
			return;
		}
		CosapiSoft.SARAWebBanking.InicioSesion login = (CosapiSoft.SARAWebBanking.InicioSesion) req.getSession(false).getAttribute("builder");
		if (req.getParameter("cmd01").equals("Eliminar")) 
		{
			JDatabase jd = new JDatabase(login.driver, login.url, login.userid, login.password);
			JQuery db = new JQuery(jd.getConnection());
			java.util.Enumeration en=req.getParameterNames();
			while (en.hasMoreElements())
			{
				String name=en.nextElement().toString();
				if (name.substring(0,3).equals("chk"))
				{
					String query1 = "delete from trecdat where codrec='"+name.substring(3)+"'";
					db.setQuery(query1);
					db.executeUpdate();
					res.sendRedirect("/sarawebbanking/servlet/Receipt");		
				}
			}
			db.close();
		}
		
		if (req.getParameter("cmd01").equals("Modificar")) 
		{
			String cad="",cad2="";
			java.util.Vector temp=((ReceiptBean)req.getSession(false).getAttribute("rp")).getReceiptVector();
			for (int i=0;i<temp.size();i++)
				if (((java.util.Vector)temp.elementAt(i)).elementAt(0).toString().equals(req.getParameter("code")))
				{
					 cad=((java.util.Vector)temp.elementAt(i)).elementAt(2).toString();
					 cad2=deleteSpaces(cad);
					((ReceiptBean)req.getSession(false).getAttribute("rp")).setCode(req.getParameter("code"));
					((ReceiptBean)req.getSession(false).getAttribute("rp")).setDescription(((java.util.Vector)temp.elementAt(i)).elementAt(1).toString());
					((ReceiptBean)req.getSession(false).getAttribute("rp")).setData(cad2);
				}
			res.sendRedirect("/sarawebbanking/SARAWebBuilder/receipt/receipt2.jsp");		
		}
		

		
		if (req.getParameter("cmd01").equals("Grabar")) 
		{
			JDatabase jd = new JDatabase(login.driver, login.url, login.userid, login.password);
			JQuery db = new JQuery(jd.getConnection());
			String query1 = "delete from trecdat where codrec='"+req.getParameter("txtcodigo")+"'";
			db.setQuery(query1);
			db.executeUpdate();
			//*****alex  
			String ss=req.getParameter("txacontenido");
			ss.trim();
			// System.out.println("refrendo agregado es : "+ss+"  longitud es : "+ss.length());
			String cad=fillWithSpaces(ss);
			// System.out.println("la nueva cadena es : "+cad+ "longitud es : "+cad.length());  
			
			String aa=deleteSpaces(cad);
			// System.out.println("la cadena sin espacios es : "+aa+" longitud es : "+aa.length());
			
			
			//query1 = "insert into trecdat values ('"+req.getParameter("txtcodigo")+"','"+req.getParameter("txtdescripcion")+"','"+req.getParameter("txacontenido")+"')";
			query1 = "insert into trecdat values ('"+req.getParameter("txtcodigo")+"','"+req.getParameter("txtdescripcion")+"','"+cad+"')";
			  
			db.setQuery(query1);
			db.executeUpdate();
			db.close();
			res.sendRedirect("/sarawebbanking/servlet/Receipt");		
			
			
		}
		if (req.getParameter("cmd01").equals("Regresar")) 
		{
			res.sendRedirect("/sarawebbanking/servlet/Receipt");		
		}


/*********************************************************************************/		
		
		if (req.getParameter("cmd01").equals("Agregar")) {
			((ReceiptBean)req.getSession(false).getAttribute("rp")).setCode("");
			((ReceiptBean)req.getSession(false).getAttribute("rp")).setDescription("");
			((ReceiptBean)req.getSession(false).getAttribute("rp")).setData("");
			res.sendRedirect("/sarawebbanking/SARAWebBuilder/receipt/receipt2.jsp");		
		}

	}
	catch (Exception e) {
		e.printStackTrace();
		// System.out.println("ERROR: " + e.getMessage());
	}
}

/**
 * if finds a char(13)+char(10)+"      "
 * then erase white spaces.
 * @param cad
 * @return String : the new String
 * Alex Valencia
 */
public String deleteSpaces(String cad)
{
 String cad1=cad;
 String cad2="";
 int n=numberOfSpaces(cad1);
 String space=new String(new char[] {(char) 13, (char) 10 });
 space=space+"      ";

 int pos=0;
 for(int i=1;i<=n;i++)
 {
  pos=cad1.indexOf(space);
  if(pos==0)
  {
  	cad2=cad2+cad1.substring(0,2);
  	cad1=cad1.substring(8);
  }
  else if((cad1.length()-pos)>8)
  {
  	cad2=cad2+cad1.substring(0,pos+2);
  	cad1=cad1.substring(pos+8);
  }
  if(i==n)
  {
  	cad2=cad2+cad1;
  } 	
 }   
 
 
 return cad2;	
}

/**
 * if finds a char(13)+char(10)
 * then adds "      " 
 * @param cad
 * @return String : the new String
 * Alex Valencia
 */
public String fillWithSpaces(String cad)
{
 String space="      ";
 String cad1=cad;
 String cad2="";
 String jump=new String(new char[] {(char) 13, (char) 10 });
 int n=numberOfJumps(cad1);
 
 int pos=0;
 for(int i=1;i<=n;i++)
 {
  pos=cad1.indexOf(jump);
  if(pos==0)
  {
  	cad2=cad2+cad1.substring(0,2)+space;
  	cad1=cad1.substring(2);
  }
  else if((cad1.length()-pos)>2)
  {
  	cad2=cad2+cad1.substring(0,pos+2)+space;
  	cad1=cad1.substring(pos+2);
  }
  if(i==n)
  {
  	cad2=cad2+cad1;
  } 	
 }   
 return cad2;
}
/**
 * counts the number of char(13)+char(10)
 * @param cad
 * @return int
 * Alex Valencia
 */
public int numberOfJumps(String cad)
{
 int num=0,pos=0;	
 String cad1=new String(new char[] {(char) 13, (char) 10 });
 String cad2=cad;
 String cad3="";
 pos=cad2.indexOf(cad1);
 
 cad3=cad2;
 while(pos >= 0)
 {
 	num++;
 	if(cad3.length() <= (pos+2))
 	{
 		return num;
 	}
  	cad3=cad2.substring(pos+2);
 	pos=cad3.indexOf(cad1);
 	cad2=cad3;
 } 
 return num;	
}
/**
 * counts the number of char(13)+char(10)+"      "
 * @param cad 
 * @return int
 * Alex Valencia
 */
public int numberOfSpaces(String cad)
{
 int num=0,pos=0;	
 String cad1=new String(new char[] {(char) 13, (char) 10 });
 cad1=cad1+"      ";
 String cad2=cad;
 String cad3="";
 pos=cad2.indexOf(cad1);
 
 cad3=cad2;
 while(pos >= 0)
 {
 	num++;
 	if(cad3.length() <= (pos+8))
 	{
 		return num;
 	}
  	cad3=cad2.substring(pos+8);
 	pos=cad3.indexOf(cad1);
 	cad2=cad3;
 } 
 return num;	
}

public boolean validate(Object obj) throws Exception {
	return false;
}
}