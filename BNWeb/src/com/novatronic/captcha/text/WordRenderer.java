package com.novatronic.captcha.text;

import java.awt.image.BufferedImage;

/**
 *
 * @author rcastillejo
 */
public interface WordRenderer {
    
    public void render(String word, BufferedImage image);
}
