import java.util.Scanner;
import java.io.InputStream;
import java.lang.ModuleLayer.Controller;
import java.io.IOException;

public class Keyboard extends InputDevice implements Actionable {
   
   // Fields
   private InputStream in;
   private float rPos, vPos, fPos, sPos;
   private static float sensibility=0.2f;
   
   // Constructor
   public Keyboard(SkyController controller) {
      super(controller);
      rPos=vPos=fPos=sPos=0;
      in = System.in;
   }
   
   // Methods
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
         while (in.available()>0) {// there are bytes to read without being blocked
            c=in.read();
            switch (c) {
               case 'w':
                  vPos += sensibility; 
                  if (vPos > 1) vPos=1;
                  break;
               case 's':
                  vPos -= sensibility; 
                  if (vPos < -1) vPos = -1;
                  break;
               case 'a':
                  rPos -= sensibility; 
                  if (rPos < -1) vPos = -1;
                  break;
               case 'd':
                  rPos += sensibility; 
                  if (rPos > 1) rPos = 1;
                  break;
               case 'i':
                  fPos += sensibility; 
                  if (fPos > 1) fPos = 1;
                  break;
               case 'k':
                  fPos -= sensibility; 
                  if (fPos < -1) fPos = -1;
                  break;
               case 'j':
                  sPos -= sensibility; 
                  if (sPos < -1) sPos = -1;
                  break;
               case 'l':
                  sPos += sensibility; 
                  if (sPos > 1) sPos = 1;
                  break;
               case ' ':
                  super.pushTakeOff_Land();
                  break;
            }
         }
      }
      catch ( IOException e ) { 
         System.out.println("Input error");
         return;
      }
   }
}