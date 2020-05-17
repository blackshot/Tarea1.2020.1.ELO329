import java.util.Scanner;

public class Operator implements Actionable{
   
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
   public Operator(Scanner in, Joysticks joysticks){
      this(in, joysticks.getLeftStick(), joysticks.getRightStick());
   }
   
   // Methods
   public void takeAction(float time) {
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
      }
      // No more data to read;
      else{ 
         // Take no action
      }
   }
}