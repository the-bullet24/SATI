/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.novatronic.internet.actions;

import com.novatronic.captcha.Captcha;
import com.novatronic.internet.actionforms.CaptchaForm;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

/**
 *
 * @author nteruya
 */
public class ValidarCaptchaAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    
    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        ActionMessages messages = new ActionMessages();
        // 1. Obtenemos la sesión
        HttpSession session = request.getSession();
        // 2. Obtenemos la clase captcha guardada en la sesión (ver CaptchaAction)
        Captcha captchaSession = (Captcha)session.getAttribute("captcha");
        // 3. Obtenemos el valor del captcha ingresada
        CaptchaForm captchaForm = (CaptchaForm)form;
        String captchaValue = captchaForm.getCaptcha();
        // 4. Comparamos los valores
       
    
        if(!captchaSession.isCorrect(captchaValue)){
            // Captcha es incorrecto
            captchaForm.setCaptcha(null);
            messages.add("captcha", new ActionMessage("error.captcha.incorrect"));
           // saveErrors(request, messages);
            return (new ActionForward(mapping.getInput()));
        }

        captchaForm.setCaptcha(null);
        session.setAttribute("captcha", null);
        return mapping.findForward(SUCCESS);
    }
}
