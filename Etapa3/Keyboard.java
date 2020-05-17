//import java.util.Scanner;
import java.io.InputStream;
import java.io.IOException;

public class Keyboard extends InputDevice implements Actionable {
    // FIELDS
    private InputStream in;
    private float rPos, vPos, fPos, sPos;
    private static float sensibility=0.2f;

    // Constructor Method
    public Keyboard(SkyController controller) {
      super(controller);
      rPos=vPos=fPos=sPos=0;
      in = System.in;
    }

    // Methods
    /** 
    * Obtiene la posicion direccional que se le da al dron 
    * tipo float.
    * @return float: posicion direccional.
    */
    public float getForwardPos() {
        return fPos;
    }
    /** 
    * Obtiene la posicion lateral que se le da al dron 
    * tipo float.
    * @return float: posicion lateral.
    */
    public float getSidewaysPos(){
        return sPos;
    }
    /** 
    * Obtiene la posicion vertical que se le da al dron 
    * tipo float.
    * @return float: posicion vertical.
    */
    public float getVerticalPos(){
        return vPos;
    }
    /** 
    * Obtiene la posicion rotacional del dron 
    * tipo float.
    * @return float: posicion rotacional.
    */
    public float getRotationPos(){
        return rPos;
    }
    /** 
    * Determina una posicion vertical del joystick segun 
    * el parametro tipo float Value.
    * @param Value (float): angulo de posicion
    * @return float: 
    */
    public void takeAction(float time) {
        int c;
        try {  // reading from "in" could cause an error.
    // We will see instruction try...catch ahead in this course.
            if (in.available()>0) {// there are bytes to read without being blocked
                c=in.read();
                switch (c) {
                    // Left Joy
                    case 'w': vPos+=sensibility; 
                        if (vPos > 1) vPos=1;
                        break;
                    case 'a': rPos-=sensibility; 
                        if (rPos > 1) rPos=1;
                        break;
                    case 's': rPos+=sensibility; 
                        if (rPos > 1) rPos=1;
                        break;
                    case 'z': vPos-=sensibility; 
                        if (vPos > 1) vPos=1;
                        break;
                    // Right Joy
                    case 'i': fPos+=sensibility; 
                        if (fPos > 1) fPos=1;
                        break;
                    case 'j': sPos-=sensibility; 
                        if (sPos > 1) sPos=1;
                        break;
                    case 'k': sPos+=sensibility; 
                        if (sPos > 1) sPos=1;
                        break;
                    case 'm': fPos-=sensibility; 
                        if (fPos > 1) fPos=1;
                        break;
                    case ' ': this.pushTakeOff_Land(); break;
                    default: System.out.println("Incorrect Input"); break;
                }
            }
        } catch ( IOException e ) { 
            System.out.println("Input error");
            return;
        }
        return;
    }
}