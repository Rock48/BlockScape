package net.blockscape.helper;

import processing.core.PConstants;

public class GeneralHelper
{

    public static boolean isCharInput(char key, int keyCode)
    {
        if (key != PConstants.TAB && key != PConstants.ENTER && key != PConstants.ESC && keyCode != PConstants.SHIFT && key != PConstants.RETURN && keyCode != PConstants.LEFT && keyCode != PConstants.RIGHT && keyCode != PConstants.DOWN && keyCode != PConstants.UP && keyCode != PConstants.ALT)
            return true;
        else
            return false;
    }
    
}
