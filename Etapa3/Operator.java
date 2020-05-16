import java.util.Scanner;

public class Operator {
   
   // Fields
   private float t;
   private Scanner inFile;
   private Joystick l_Joystick, r_Joystick;
   
   // Constructor
   public Operator(Scanner in, Joystick l_Joy, Joystick r_Joy){
      inFile = in;
      l_Joystick = l_Joy;
      r_Joystick = r_Joy;
      
      // Skip description line
      inFile.nextLine();
      t = inFile.nextFloat();
   }
   
   // Another constructor
   public Operator(Scanner in, SkyController newSky){
      this(in, newSky.getLeftStick(), newSky.getRightStick());
   }
   
   // Methods
   public boolean takeAction(float time){
      // If there's data to read
      if (inFile.hasNextLine()){
         // if time >= t
         if (Math.round(time * 10) >= Math.round(t*10)){
            
            // Left Joystick Data
            l_Joystick.setHorPos(inFile.nextFloat());
            l_Joystick.setVerPos(inFile.nextFloat());
            
            // Right Joystick Data
            r_Joystick.setHorPos(inFile.nextFloat());
            r_Joystick.setVerPos(inFile.nextFloat());
            
            // If there's a new line
            if (inFile.hasNextLine()){
               t = inFile.nextFloat();
            }
         }
         return true;
      }
      // No more data to read;
      else{ 
         // Take no action
         return false;
      }
   }
}