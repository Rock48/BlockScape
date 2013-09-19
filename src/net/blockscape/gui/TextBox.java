package net.blockscape.gui;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PFont;

public class TextBox
{
    PApplet host;
    
    float x, y, w, h;
    
    boolean border;
    boolean mOvr;
    
    String titleText;
    PFont f;
    
    public String input;
    
    public int counter;
    
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
        
        mOvr = false;
        
        counter = 2000;
    }
    
    public void update()
    {
        host.pushMatrix();
        
        host.translate(x, y);
        
        if (border)
            host.stroke(0);
        else
            host.noStroke();
        
        host.fill(200, 200, 200);
        host.rect(0, 0, w, h);
        host.fill(255);
        host.rect(w / 30, h / 2, w - 2 * (w / 30), h / 2.5F);
        host.textFont(f);
        host.textAlign(PConstants.CENTER);
        host.fill(0, 0, 0);
        host.text(titleText, w / 2, h / 3); //TODO Make not so specific to world maker.
        
        if (((int) counter / 30) % 2 == 0)
            host.text(input + "|", w / 2, h / 1.3F); //TODO Same here as above.
        else
            host.text(input, w / 2, h / 1.3F); //TODO Same here as above.
            
        
        
        host.popMatrix();
        
        if(host.mouseX >= x && host.mouseX <= x + w && host.mouseY >= y && host.mouseY <= y + h)
            mOvr = true;
        else
            mOvr = false;
        
        if (counter < 0)
            counter = 2000;
        else
            counter--;
    }
}
