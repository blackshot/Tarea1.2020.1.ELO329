import java.util.Scanner;
import java.io.InputStream;
import java.io.IOException;

public class Keyboard extends InputDevice implements Actionable {
   public Keyboard(SkyController controller) {
      super(controller);
      rPos=vPos=fPos=sPos=0;
      in = System.in;
   }
   public float getForwardPos() {
      return fPos;
   }
   public float getSidewaysPos(){
      return sPos;
   }
   public float getVerticalPos(){
      return vPos;
   }
   public float getRotationPos(){
      return rPos;
   }
   public void takeAction(float time) {
      int c;
      try {  // reading from "in" could cause an error.
 // We will see instruction try...catch ahead in this course.
         if (in.available()>0) {// there are bytes to read without being blocked
            c=in.read();
            switch (c) {
               case 'w': vPos+=sensibility; 
                         if (vPos > 1) vPos=1;
                         break;
              /* others cases to be coded */
            }
         }
      } catch ( IOException e ) { 
         System.out.println("Input error");
         return;
      }
   }
   private InputStream in;
   private float rPos, vPos, fPos, sPos;
   private static float sensibility=0.2f;
}