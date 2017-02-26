package ch.hslu.AD.SW1.Faerben;

import java.awt.Color;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
    	colorArea(5, 4, Color.gray, Color.black);
    }
    
    private static Color[][] area = new Color[][] {
    	{ Color.black, Color.black, Color.black, Color.black, Color.black, Color.black, Color.black, Color.black, Color.black, Color.black },
    	{ Color.black, Color.white, Color.white, Color.white, Color.white, Color.white, Color.white, Color.white, Color.white, Color.black },
    	{ Color.black, Color.white, Color.white, Color.white, Color.black, Color.black, Color.black, Color.black, Color.white, Color.black },
    	{ Color.black, Color.white, Color.white, Color.white, Color.black, Color.black, Color.black, Color.black, Color.white, Color.black },
    	{ Color.black, Color.black, Color.white, Color.white, Color.white, Color.white, Color.white, Color.black, Color.white, Color.black },
    	{ Color.black, Color.black, Color.white, Color.white, Color.white, Color.black, Color.white, Color.black, Color.white, Color.black },
    	{ Color.black, Color.black, Color.black, Color.white, Color.white, Color.black, Color.white, Color.black, Color.white, Color.black },
    	{ Color.black, Color.black, Color.black, Color.white, Color.white, Color.black, Color.white, Color.white, Color.white, Color.black },
    	{ Color.black, Color.black, Color.white, Color.white, Color.white, Color.black, Color.black, Color.black, Color.black, Color.black },
    	{ Color.black, Color.black, Color.white, Color.white, Color.white, Color.black, Color.black, Color.black, Color.black, Color.black },
    	{ Color.black, Color.black, Color.black, Color.black, Color.black, Color.black, Color.black, Color.black, Color.black, Color.black }
    };
    
    public static void colorArea(final int x, final int y, final Color fillColor, final Color outsideColor) {
    	Color actualColor = area[x - 1][y - 1];
    	if((actualColor != outsideColor) && (actualColor != fillColor)) {
    		area[x - 1][y - 1] = fillColor;
    		System.out.println(String.format("x: %d, y: %d", x, y));
    		colorArea(x + 1, y, fillColor, outsideColor);
    		colorArea(x, y + 1, fillColor, outsideColor);
    		colorArea(x - 1, y, fillColor, outsideColor);
    		colorArea(x, y - 1, fillColor, outsideColor);
    	}
    }
}
