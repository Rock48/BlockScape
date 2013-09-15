package net.blockscape.gui;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PFont;

public class TextBox
{
    PApplet host;
    
    float x, y, w, h;
    
    boolean border;
    
    String titleText;
    PFont f;
    
    public TextBox(float x, float y, float w, float h, String titleText, boolean border, PFont textFont, PApplet host)
    {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.titleText = titleText;
        this.border = border;
        this.f = textFont;
        this.host = host;
    }
    
    public void update()
    {
        host.pushMatrix();
        
        host.translate(x, y);
        
        if (border)
            host.stroke(0);
        else
            host.noStroke();
        
        host.rect(0, 0, w, h);
        host.textFont(f);
        host.textAlign(PConstants.TOP);
        host.text(titleText, w  / 2, h / 2 + f.getSize() / 3);
        
        
        host.popMatrix();
    }
}
