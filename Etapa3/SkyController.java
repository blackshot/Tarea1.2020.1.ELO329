public class SkyController {
   // FIELDS
   private Drone drone;
   private Joystick lStick, rStick;
   private DroneState button;

   // Constructor Method
   public SkyController (Drone drone) {
      this.drone = drone;
      lStick = new Joystick();
      rStick = new Joystick();
      button = DroneState.IDLE;
   }
   // Methods
   /** 
    * Obtiene la informacion del joystick izquierdo.
    * @return Joystick: informacion del mando izquierdo
    */
   public Joystick getLeftStick(){
      return lStick;
   }
   /** 
    * Obtiene la informacion del joystick derecho.
    * @return Joystick: informacion del mando derecho
    */
   public Joystick getRightStick(){
      return rStick;
   }
   /** 
    * Realiza el despegue o aterrizaje dependiendo del estado del Dron.
    */
   public void pushTakeOff_Land () {
      if (button == DroneState.IDLE){
         drone.takeOff();
         button = DroneState.TAKE_OFF;
      }
      if (button == DroneState.FLYING){
         drone.land();
         button = DroneState.LANDING;
      }
   }
   /** 
    * Dirige las acciones tomadas por los Joystick hacia el Dron.
    */
   public void takeAction(float time) {
      button = DroneState.FLYING;
      drone.setRotationSpeed(lStick.getHorPos());
      drone.setFlySpeed(lStick.getVerPos(), rStick.getVerPos(), rStick.getHorPos());
   }
}

