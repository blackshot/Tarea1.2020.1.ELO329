import java.io.*;
import java.util.Scanner;
import java.util.Locale;

public class Stage2Test  {
   public static void main (String[] arg) throws IOException {
      Locale.setDefault(Locale.US);  // to read number in US format, like 1.5 (not like 1,5)
      Scanner in = new Scanner(new File(arg[0]));
      // Objects init
      float time = 0.00f;
      Drone drone = new Drone();
      SkyController skyController = new SkyController(drone);
      Operator operator = new Operator(in, skyController);
      
      System.out.println(" t, \tx,\ty,\th");
      // Take-off
      skyController.pushTakeOff_Land();
      while(operator.takeAction(time)) {
         skyController.takeAction(time);
         drone.takeAction(time);
         System.out.println(String.format("% .2f,",time) + drone);
         time += 0.1f;
      }

      // Landing
      skyController.pushTakeOff_Land();
      while (drone.getHeight() > 0) {
         drone.takeAction(time);
         System.out.println(String.format("% .2f,",time) + drone);
         time += 0.1f;
      }
   }
}