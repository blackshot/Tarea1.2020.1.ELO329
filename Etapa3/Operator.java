import java.util.Scanner;

public class Operator {
   // FIELDS
   private float t;
   private Scanner inFile;
   private Joystick l_Joystick, r_Joystick;

   // Constructor Methods
   public Operator (Scanner in, Joystick l_Joy, Joystick r_Joy){
      inFile = in;
      l_Joystick = l_Joy;
      r_Joystick = r_Joy;
      inFile.nextLine(); // skip description line
      t = inFile.nextFloat();
   }
   public Operator (Scanner in, SkyController NewSky){
      this(in, NewSky.getRightStick(), NewSky.getLeftStick());
   }

   // Methods
   /** 
    * Toma las acciones pertinentes dependiendo del archivo 
    * de entrada.
    * @param time (float): el tiempo actual
    * @return boolean: si se tomo o no la accion
    */
   public boolean takeAction(float time){
      float l_hpos, l_vpos, r_hpos, r_vpos;
      if (t < time) {
         l_hpos = inFile.nextFloat();
         l_vpos = inFile.nextFloat();
         r_hpos = inFile.nextFloat();
         r_vpos = inFile.nextFloat();
         if((l_hpos == 0) && (l_vpos == 0) && (r_hpos == 0) && (r_vpos == 0)) {
            return false;
         }
         l_Joystick.setHorPos(l_hpos);
         l_Joystick.setVerPos(l_vpos);
         r_Joystick.setHorPos(r_hpos);
         r_Joystick.setVerPos(r_vpos);
         t = inFile.nextFloat();
       }
      return true;
   }
}