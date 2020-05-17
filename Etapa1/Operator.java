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
      float v,r,f,s;
      // If there's data to read
      if (inFile.hasNextFloat()){

         // Round to avoid float epsilon difference
         if (Math.round(time * 10) >= Math.round(t*10)){
            r = inFile.nextFloat();
            v = inFile.nextFloat();
            s = inFile.nextFloat();
            f = inFile.nextFloat();
          
            // Left Joystick Data
            l_Joystick.setHorPos(r);
            l_Joystick.setVerPos(v);
            System.out.print(l_Joystick.toString() + ", ");

            // Right Joystick Data
            r_Joystick.setHorPos(s);
            r_Joystick.setVerPos(f);
            System.out.println(r_Joystick.toString());
            
            // If there's a new line
            if (inFile.hasNextLine()){
               t = inFile.nextFloat();
            }
         }
         return true;
      }
      // No more data to read
      else{
         return false;
      }
   }
}