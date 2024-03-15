package CosapiSoft.SARAWebBanking;

import pe.cosapi.common.Constante;
/**
 * This type was created in VisualAge.
 */
public abstract class JavaServlet extends javax.servlet.http.HttpServlet {
/**
 * JavaServlet constructor comment.
 */
public JavaServlet() {
	super();
}
/**
 * This method was created in VisualAge.
 * @param req javax.servlet.http.HttpServletRequest
 * @param res javax.servlet.http.HttpServletResponse
 * @param url java.lang.String
 */
public void callPage(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse res, String url) throws java.io.IOException, javax.servlet.ServletException {
//	((com.sun.server.http.HttpServiceResponse) res).callPage(url, req);
    Constante.SESSION_CADUCA = "1";
    if (req.getSession(false) == null) {
        Constante.SESSION_CADUCA = "2";
    }
    System.out.println("Sesion de CADUCIDAD - CallPage:"+Constante.SESSION_CADUCA);
	res.sendRedirect(url);
}
/**
 * This method was created in VisualAge.
 * @param req javax.servlet.http.HttpServletRequest
 * @param res javax.servlet.http.HttpServletResponse
 */
public void doGet(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse res) throws javax.servlet.ServletException, java.io.IOException {
	doPost(req, res);
}
/**
 * This method was created in VisualAge.
 * @return java.lang.Object
 * @param req javax.servlet.http.HttpServletRequest
 * @param name java.lang.String
 */
public Object getAttribute(javax.servlet.http.HttpServletRequest req, String name) {
	Object bean = req.getAttribute(name);
	return bean;
}
/**
 * This method was created in VisualAge.
 * @return boolean
 * @param req javax.servlet.http.HttpServletRequest
 */
public boolean isLoggedIn(javax.servlet.http.HttpServletRequest req) {
	boolean status = false;
	javax.servlet.http.HttpSession session = req.getSession(false);
	if (session != null)
		status = true;
	return status;
}
/**
 * This method was created in VisualAge.
 * @param req javax.servlet.http.HttpServletRequest
 * @param name java.lang.String
 * @param bean java.lang.Object
 */
public void setAttribute(javax.servlet.http.HttpServletRequest req, String name, Object bean) {
	//((com.sun.server.http.HttpServiceRequest) req).setAttribute(name, bean);
	req.setAttribute(name, bean);
}
/**
 * This method was created in VisualAge.
 * @param res javax.servlet.http.HttpServletResponse
 * @param noCache boolean
 */
public final static void setCache(javax.servlet.http.HttpServletResponse res, boolean noCache) {
	setCache(res, noCache, 0);
}
/**
 * This method was created in VisualAge.
 * @param res javax.servlet.http.HttpServletResponse
 * @param noCache boolean
 * @param expiration int
 */
public final static void setCache(javax.servlet.http.HttpServletResponse res, boolean noCache, int expiration) {
	if (noCache) {
		res.setHeader("Pragma", "no-cache");
		res.setHeader("Cache-Control", "no-cache");
		res.setHeader("Cache-Control", "no-store");
		res.setHeader("Expires", "0");
	}
	else {
		if (expiration > 0) {
			res.setHeader("Expires", Integer.toString(expiration));
		}
	}
}
/**
 * This method was created in VisualAge.
 * @return boolean
 * @param obj java.lang.Object
 */
public abstract boolean validate(Object obj) throws Exception;
}