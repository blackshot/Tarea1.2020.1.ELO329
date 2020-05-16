public abstract class InputDevice {
   public InputDevice (SkyController controller) {
      // At creation, the drone is LANDED
      button=State.IDLE;
      this.controller=controller;
   }
   public abstract float getForwardPos();
   public abstract float getSidewaysPos();
   public abstract float getVerticalPos();
   public abstract float getRotationPos();
   public void pushTakeOff_Land () {
      switch (button) {
         case TAKE_OFF: /* to be coded */
         case LANDING: /* to be coded */
         case FLYING:
         case IDLE:
      }
      button = button==State.TAKE_OFF ? State.LANDING : State.TAKE_OFF;
   }
   public boolean isWaitingToTakeOff () {
      return button == State.TAKE_OFF;
   }
   private State button;
   private SkyController controller;
}