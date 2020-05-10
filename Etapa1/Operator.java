import java.util.Scanner;

public class Operator {
   
   // Fields
   private float t;
   private Scanner inFile;
   private Joystick l_Joystick, r_Joystick;

   // Constructor
   public Operator (Scanner in, Joystick l_Joy, Joystick r_Joy){
      inFile = in;
      l_Joystick = l_Joy;
      r_Joystick = r_Joy;

      // Skip description line
      inFile.nextLine();
      t = inFile.nextFloat();
   }

   // Methods
   public boolean takeAction(float time){
      if (time > t) {
         // Time data
         System.out.print(t + ", ");

         // Left Joystick Data
         l_Joystick.setHorPos(inFile.nextFloat());
         l_Joystick.setVerPos(inFile.nextFloat());
         System.out.print(l_Joystick.toString() + ", ");
         
         // Right Joystick Data
         r_Joystick.setHorPos(inFile.nextFloat());
         r_Joystick.setVerPos(inFile.nextFloat());
         System.out.print(r_Joystick.toString());
         
         // Print data

         // Continue if there's another data line
         if(inFile.hasNextLine()){
            inFile.nextLine();
            t = inFile.nextFloat();
            // Create new csv line
            System.out.println();
         }
         else {
            return false;
         }
      }
      return true;
   }
}