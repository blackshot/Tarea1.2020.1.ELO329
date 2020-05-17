public abstract class InputDevice {
   // Fields
   private State button;
   private SkyController controller;

   // Constructor
   public InputDevice (SkyController controller) {
      // At creation, the drone is LANDED
      button=State.IDLE;
      this.controller=controller;
   }

   // Methods
   public abstract float getForwardPos();
   public abstract float getSidewaysPos();
   public abstract float getVerticalPos();
   public abstract float getRotationPos();
   
   public void pushTakeOff_Land () {
      /*
      switch (button) {
         case TAKE_OFF:
         case LANDING:
         case FLYING:
         case IDLE:
      }
      button = button==State.TAKE_OFF ? State.LANDING : State.TAKE_OFF;
      */
      controller.pushTakeOff_Land();
   }
   
   public boolean isWaitingToTakeOff(){
      return button == State.TAKE_OFF;
   }

}