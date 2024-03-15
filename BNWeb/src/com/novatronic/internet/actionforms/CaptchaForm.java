package com.novatronic.internet.actionforms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author novatronic
 */
public class CaptchaForm extends org.apache.struts.action.ActionForm {

    private String captcha;

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public CaptchaForm() {
        super();
    }

    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.
     * @return
     */
    @Override
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        /**
         * Verificamos que el valor del captcha no sea nulo o vacío
         */
        if (getCaptcha() == null || getCaptcha().length() < 1) {
            /** Agregar el key error.captcha.required en ApplicationResource.properties  */
            errors.add("captcha", new ActionMessage("error.captcha.required"));
        }
        return errors;
    }
}