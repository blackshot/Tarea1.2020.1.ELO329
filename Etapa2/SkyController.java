public class SkyController {
   // FIELDS
   private Drone drone;
   private Joystick lStick, rStick;
   private DroneState button;

   // Constructor Method
   public SkyController (Drone drone) {
      this.drone = drone;
      // to be coded
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
      // to be coded
   }
   /** 
    * 
    */
   public void takeAction(float time) {
      // to be coded
   }
}

