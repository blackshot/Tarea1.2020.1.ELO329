import java.io.*;
import java.util.Scanner;
import java.util.Locale;
import java.util.ArrayList;

public class Stage3Test  {
   public static void main (String[] arg) throws IOException {

      // ---------- CODE INIT ---------- //
      
      if (arg.length == 0){
         System.out.println("ERROR : No input handed. Closing program ...");
         return;
      }
      final Scanner in = new Scanner(new File(arg[0]));
      
      // Drone init
      Drone drone = new Drone();
      // Drone in the controller
      SkyController skyController = new SkyController(drone);
      
      // Create the joysticks
      Joysticks joysticks = new Joysticks(skyController);
      skyController.setInputDevice(joysticks);

      // Hand in the controller to the operator
      Operator operator = new Operator(in, joysticks);

      ArrayList<Actionable> Actionables = new ArrayList<Actionable>();
      Actionables.add(operator);
      Actionables.add(skyController);
      Actionables.add(drone);

      // Time init (as close as possible to the operation to avoid time lag...)
      float time, nextPrintTime;
      time = nextPrintTime = getCurrentTime();

      // ---------- Joystick Interaction ---------- //
      // Turn on the drone
      skyController.pushTakeOff_Land(); // to take-off
      do {
         for (Actionable device : Actionables)
            device.takeAction(time);
         if (time >= nextPrintTime) {
            System.out.println(time + ",\t" + drone);
            nextPrintTime += 0.1;
         }
         sleepFor(0.1f); // let 0.1 [s] pass to run at real time.
         time = getCurrentTime();
      } while (drone.getState() != State.IDLE);

      System.out.println(time + ",\t" + drone);

      // ---------- Keyboard Interaction ---------- //

      Keyboard keyboard = new Keyboard(skyController);
      Actionables.remove(operator); // stop reading automatically from file
      Actionables.add(keyboard); // start reading from keyboard

      skyController.setInputDevice(keyboard); // we switch to another input device
      
      System.out.println("Get ready to control the drone. Now you are its pilot.");
      do { // wait until the user hits space key (to take-off)
         for (Actionable device : Actionables)
            device.takeAction(time);
         sleepFor(0.1f); // users need to run at real time (not at simulation time)
         time = getCurrentTime();
      } while (drone.getState() != State.TAKE_OFF);
      nextPrintTime = time + 0.5f;
      do { // user flies the drone until the drone lands.
         for (Actionable device : Actionables)
         device.takeAction(time);
         if (time >= nextPrintTime){
            System.out.print("\n" + time+ ",\t"+drone + "; move: " );
            nextPrintTime+=0.5;
         }
         sleepFor(0.1f);
         time=getCurrentTime();
      } while (drone.getState()!=State.IDLE);
      System.out.print("\n" + time+ ",\t"+drone + "; move: ");
      //drone.closeFile();
   }
   public static float getCurrentTime(){  // time since program started in [s]
      return (float)(System.currentTimeMillis()-t0)/1000.0f;
   }
   public static void sleepFor(float time) { // to let user react
      try {
         Thread.sleep((int)(time*1000));
      }
      catch (InterruptedException e){
      }
   }
   // ------------------------------------------------------------------ //

   public static long t0; 
   static {
      Locale.setDefault(Locale.US);
      t0=System.currentTimeMillis();  // time in ms since app 1970.
   }
}