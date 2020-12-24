/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.genie.messenger;

import java.awt.Color;
import java.util.Date;

/**
 *
 * @author faateh
 */
public class Message {
    public String msg;
    public Color msgColor;
    
    public Message(String response) {
      String[] arr = response.split("■■■■■■■■■■");
      msg = arr[0];
      arr = arr[1].split("■");
      msgColor = new Color(Integer.parseInt(arr[0]), Integer.parseInt(arr[1]), Integer.parseInt(arr[2]));
    }
    public Message(String srcMsg, Color srcCol) {
      msg = srcMsg;
      msgColor = srcCol;
    }
    public String toString() {
        String result = msg;
        msg += "■■■■■■■■■■"; //delimeter
        msg += msgColor.getRed() + "■" + msgColor.getGreen() + "■" + msgColor.getBlue();
        return msg;
    }
}
