public class Joysticks extends InputDevice {
   public Joysticks (SkyController controller) {
      super(controller);
      rStick = new Joystick();
      lStick = new Joystick();
   }
   public float getForwardPos() {
      /* to be coded */
   }
   /* others methods to be coded*/
   private Joystick lStick, rStick;
}