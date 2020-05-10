import java.io.*;
import java.util.Scanner;
import java.util.Locale;

public class Stage2Test  {
   public static void main (String[] arg) throws IOException {
      Locale.setDefault(Locale.US);  // to read number in US format, like 1.5 (not like 1,5)
      Scanner in = new Scanner(new File(arg[0]));
      float time = 0;
      Drone drone = new Drone();
      SkyController skyController = new SkyController(drone);
      Operator operator = new Operator(in, skyController);
      
      // Take-off
      skyController.pushTakeOff_Land();
      while(operator.takeAction(time)) {
         skyController.takeAction(time);
         drone.takeAction(time);
         System.out.println(time+ ",\t"+drone);
         time+=0.1;
      }

      // Landing
      skyController.pushTakeOff_Land();
      while (drone.getHeight() > 0) {
         drone.takeAction(time);
         System.out.println(time+ ",\t"+drone);
         time+=0.1;
      }
   }
}