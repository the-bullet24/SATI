package com.novatronic.captcha.web;

import com.novatronic.captcha.SingletonCaptchaFactory;
import com.novatronic.captcha.exception.CaptchaException;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 *
 * @author rcastillejo
 */
public class CaptchaWebListener implements ServletContextListener {

    private static final String PATH = "/";

  
    public void contextInitialized(ServletContextEvent sce) {
        URL configURLPath;
        File f;
        try {
            f = new File(sce.getServletContext().getRealPath(PATH));
            configURLPath = f.toURI().toURL();
            SingletonCaptchaFactory.init(configURLPath);
        } catch (MalformedURLException ex) {
            throw new CaptchaException("No se pudo cargar el componente captcha", ex);
        }
    }


    public void contextDestroyed(ServletContextEvent sce) {
        SingletonCaptchaFactory.destroy();
    }

}
