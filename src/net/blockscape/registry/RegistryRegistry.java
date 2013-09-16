package net.blockscape.registry;

import processing.core.PApplet;

public class RegistryRegistry
{
    public static void init(PApplet host)
    {
        FontRegistry.init(host);
        
        ButtonRegistry.init(host);
        TextBoxRegistry.init(host);
        
        GameRegistry.initialize();
    }
}
