package net.blockscape.gui;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PFont;

public class Button
{
    PApplet host;
    
    float x, y, w, h;
    int r1, g1, b1, r2, g2, b2, rt, gt, bt;
    
    boolean border;

    public boolean mOvr;
    
    public boolean held;
    
    String text;
    PFont f;
    
    public Button(float x, float y, float w, float h, String text, boolean border, PFont textFont, PApplet host)
    {
         this.x = x;
         this.y = y;
         this.w = w;
         this.h = h;
         this.text = text;
         this.border = border;
         this.f = textFont;
         this.host = host;
      
         mOvr = false;
         held = false;
      
         r1 = 100;
         g1 = 100;
         b1 = 100;
      
         r2 = 200;
         g2 = 200;
         b2 = 200;
      
         rt = 0;
         gt = 0;
         bt = 0;
    }
    
    public void update()
    {
         host.pushMatrix();
      
         host.translate(x, y);
      
         if (border)
             host.stroke(0);
         else
             host.noStroke();
      
         host.fill(mOvr ? r2 : r1, mOvr ? g2 : g1, mOvr ? b2 : b1);
         host.rect(0, 0, w, h);
         host.textFont(f);
         host.textAlign(PConstants.CENTER);
         host.fill(rt, gt, bt);
         host.text(text, w / 2, h / 2 + f.getSize() / 3);
         host.popMatrix();
      
         if(host.mouseX >= x && host.mouseX <= x + w && host.mouseY >= y && host.mouseY <= y + h)
             mOvr = true;
         else
             mOvr = false;
      
         if(mOvr)
         {
             if(host.mousePressed)
                 held = true;
         }
      
         if(!host.mousePressed)
             held = false;
     }
    
     public void setOffColor(int r, int g, int b)
     {
          r1 = r;
          g1 = g;
          b1 = b;
     }
    
     public void setOnColor(int r, int g, int b)
     {
          r2 = r;
          g2 = g;
          b2 = b;
     }
    
     public void setTextColor(int r, int g, int b)
     {
          rt = r;
          gt = g;
          bt = b;
     }
    
     public void setText(String text)
     {
         this.text = text;
     }
  }
