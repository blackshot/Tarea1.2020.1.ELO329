import java.util.Scanner;

public class Operator implements Actionable{
   
   // Fields
   private float t;
   private Scanner inFile;
   private Joystick l_Joystick, r_Joystick;
   private SkyController SC;
   // Constructor
   public Operator(Scanner in, Joysticks joystick, SkyController SC){
      inFile = in;
      l_Joystick = joystick.getLeftStick();
      r_Joystick = joystick.getRightStick();
      this.SC = SC;

      // Skip description line
      inFile.nextLine();
      t = inFile.nextFloat();
   }

   // Methods
   public void takeAction(float time) {
      float v,r,f,s;

      // If there's data to read
      if (inFile.hasNextFloat()){
         // Turn on the drone if it wasn't already ...
         if (SC.getDroneState() == State.IDLE){
            SC.pushTakeOff_Land();
         }
         // if time >= t
         if (Math.round(time * 10) >= Math.round(t*10)){
            r = inFile.nextFloat();
            v = inFile.nextFloat();
            s = inFile.nextFloat();
            f = inFile.nextFloat();

            // If there's no input, and no more data to read
            if (v == 0.0 && r == 0.0 && f == 0.0 && s == 0.0 && 
            inFile.hasNextLine() == false){
               SC.pushTakeOff_Land();
               }
            
            // Left Joystick Data
            l_Joystick.setHorPos(r);
            l_Joystick.setVerPos(v);
            
            // Right Joystick Data
            r_Joystick.setHorPos(s);
            r_Joystick.setVerPos(f);
            
            // If there's a new line
            if (inFile.hasNextLine()){
               t = inFile.nextFloat();
            }
         }
      }
      // No more data to read;
      else{
         // Take no action
      }
   }
}