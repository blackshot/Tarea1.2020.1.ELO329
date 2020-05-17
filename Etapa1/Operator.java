import java.util.Scanner;

public class Operator {
   // FIELDS
   private float t;
   private Scanner inFile;
   private Joystick l_Joystick, r_Joystick;

   // Constructor Method
   public Operator (Scanner in, Joystick l_Joy, Joystick r_Joy){
      inFile = in;
      l_Joystick = l_Joy;
      r_Joystick = r_Joy;
      inFile.nextLine(); // skip description line
      t = inFile.nextFloat();
   }

   // Methods
   /** 
    * Toma las acciones pertinentes dependiendo del archivo 
    * de entrada.
    * @param time (float): el tiempo actual
    * @return boolean: si se tomo o no la accion
    */
   public boolean takeAction(float time){
      float ver,rot,forw,side;
      // If there's data to read
      if (inFile.hasNextFloat()){
         // Round to avoid float epsilon difference
         if (Math.round(time * 10) >= Math.round(t*10)){
            rot = inFile.nextFloat();
            ver = inFile.nextFloat();
            side = inFile.nextFloat();
            forw = inFile.nextFloat();

            // Left Joystick Data
            l_Joystick.setHorPos(rot);
            l_Joystick.setVerPos(ver);
             // Right Joystick Data
            r_Joystick.setHorPos(side);
            r_Joystick.setVerPos(forw);

             // If there's a new line
            if (inFile.hasNextLine()){
               t = inFile.nextFloat();
            }
         }
         return true;
      }
      else return false;
   }
}