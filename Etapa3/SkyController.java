public class SkyController implements Actionable {
   // Fields
   private Drone drone;
   private Joystick lStick, rStick;
   private State button;

   // Constructor
   public SkyController (Drone drone) {
      this.drone = drone; // private variable "drone" (local) now points to Drone object
      lStick = new Joystick();
      rStick = new Joystick();
      // At creation, the drone is LANDED
      button = State.IDLE;
   }

   // Methods   
   public void setInputDevice(Joysticks joysticks){
      // TBC
   }
   public void setInputDevice(Keyboard keyboard){
      // TBC
   }

   public void pushTakeOff_Land(){
      if (button == State.IDLE){
         drone.takeOff();
         button = State.TAKE_OFF;
      }
      else if (button == State.TAKE_OFF){
         drone.land();
         button = State.LANDING;
      }
   }

   public void takeAction(float time) {
      // JIV, JDV, JDH
      drone.setFlySpeed(lStick.getVerPos(),
                        rStick.getVerPos(),
                        rStick.getHorPos());
      // JIH
      drone.setRotationSpeed(lStick.getHorPos());
   }

   public Joystick getLeftStick(){
      return lStick;
   }
   public Joystick getRightStick(){
      return rStick;
   }
}