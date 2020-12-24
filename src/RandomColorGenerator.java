/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.genie.messenger;

import java.awt.Color;
import java.util.Random;

/**
 *
 * @author faateh
 */
public class RandomColorGenerator {

    //colpr array for random color allocation
    private static String colorHexList[] = {
        "#e17055", "#0984e3", "#6c5ce7", "#00b894", "#d63031", "#2d3436", "#27ae60", "#44bd32", "#e1b12c",
        "#40739e", "#8c7ae6", "#0097e6", "#192a56", "#8c7ae6", "#218c74", "#227093", "#474787", "#2c2c54"
    };

    public static Color getRandColor() {
        return Color.decode(colorHexList[new Random().nextInt(colorHexList.length)]);
    }
}
