public class SkyController {
   public SkyController (Drone drone) {
      this.drone = drone;
      lStick = new Joystick();
      rStick = new Joystick();
      button = State.LANDED; //El boton comienza aterrizado
   }
   public Joystick getLeftStick(){
      return lStick;
   }
   public Joystick getRightStick(){
      return rStick;
   }
   public void pushTakeOff_Land() {
      if (button == State.LANDED){
         drone.takeOff();
         button = State.FLYING;
      }
      if (button == State.FLYING){
         drone.land();
         button = State.LANDED;
      }
   }
   public void takeAction(float time) {
      drone.setRotationSpeed(lStick.getHorPos());
      drone.setFlySpeed(lStick.getVerPos(), rStick.getVerPos(), rStick.getHorPos());
   }
   private Drone drone;
   private Joystick lStick, rStick;
   private State button;
}

