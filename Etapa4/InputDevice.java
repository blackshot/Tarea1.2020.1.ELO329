public abstract class InputDevice {
   // Fields
   private DroneState button;
   private SkyController controller;

   // Constructor Method
   public InputDevice (SkyController controller) {
      // At creation, the drone is LANDED
      button=DroneState.IDLE;
      this.controller=controller;
   }

   // Abstract Methods
   public abstract float getForwardPos();
   public abstract float getSidewaysPos();
   public abstract float getVerticalPos();
   public abstract float getRotationPos();

   //Methods
   /** 
    * Realiza el despegue o aterrizaje dependiendo del estado del Dron.
    */
   public void pushTakeOff_Land () {
      controller.pushTakeOff_Land();
   }
   /** 
    * Responde si el device está esperando a partir.
    * @return boolean: si está o no listo para TAKE_OFF
    */
   public boolean isWaitingToTakeOff () {
      return button == DroneState.IDLE;
   }
}