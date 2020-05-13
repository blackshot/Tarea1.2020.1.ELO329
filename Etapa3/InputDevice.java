public abstract class InputDevice {
    // FIELDS
    private DroneState button;
    private SkyController controller;

    // Constructor Method
    public InputDevice (SkyController controller) {
       button=DroneState.TAKE_OFF;
       this.controller=controller;
    }

    // Methods
    public abstract float getForwardPos();
    public abstract float getSidewaysPos();
    public abstract float getVerticalPos();
    public abstract float getRotationPos();
    /** 
    * Realiza el despegue o aterrizaje dependiendo del estado del Dron.
    */
    public void pushTakeOff_Land () {
       switch (button) {
          case TAKE_OFF: /* to be coded */
          case LANDING: /* to be coded */
       }
       button = button==DroneState.TAKE_OFF?DroneState.LANDING:DroneState.TAKE_OFF;
    }
    /** 
    * Responde si el device está esperando a partir.
    * @return boolean: si está o no en TAKE_OFF
    */
    public boolean isWaitingToTakeOff () {
       return button == DroneState.TAKE_OFF;
    }
 }