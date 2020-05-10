public class Drone {
   // FIELDS
   private DroneState state;
   private float time;
   private float fSpeed, vSpeed, sSpeed, rSpeed;
   private float direction; // angle
   private float x,y,h;
   private static float MAX_F_SPEED;            // forward/backward speed
   private static float MAX_V_SPEED;            // vertical (up/down) speed
   private static float MAX_S_SPEED;            // side speed
   private static float MAX_R_SPEED;            // rotation speed
   private static float TAKEOFF_LANDING_SPEED;

   static {
      MAX_F_SPEED = MAX_S_SPEED = 5; // [m/s]
      MAX_V_SPEED = 2;    // [m/s]
      MAX_R_SPEED = (float)(0.25*Math.PI/2); // [rad/s]
      TAKEOFF_LANDING_SPEED = 1; // [m/s]
   }

   // Constructor Method
   public Drone () {
      state = DroneState.IDLE;
      time = 0.0f;
      x = 0.0f;      y = 0.0f;      h = 0.0f;
      fSpeed = 0.0f; vSpeed = 0.0f; sSpeed = 0.0f; rSpeed = 0.0f;
      direction = 0.0f;
   }

   // Methods
   /** 
    * Realiza alguna de las acciones dependiendo del estado del Dron.
    * @param t (float): tiempo actual.
    */
   public void takeAction(float t){
      float delta_t = t-time;
      switch (state) {
      case TAKE_OFF:  //drone moves only upwards in this stage
          h = h + delta_t * TAKEOFF_LANDING_SPEED;
          if (h >= 1)
            state = DroneState.FLYING;
          break;
      case FLYING:
          x = x + delta_t * fSpeed;
          y = y + delta_t * sSpeed;
          h = h + delta_t * vSpeed;
          direction = direction + delta_t * rSpeed;
          // state = DroneState.FLYING;
          break;
      case LANDING: //drone moves only downwards in this stage
          h = h - delta_t * TAKEOFF_LANDING_SPEED;
          if (h <= 0)
            state = DroneState.IDLE;
          break;
      default: break;
      }
      time = t;
   }

   /** 
    * Determina la velocidad rotacional del dron segun 
    * el left Joy.
    * @param percentage (float): angulo de posicion rotacional, h_pos - left Joy
    */
   public void setRotationSpeed(float percentage) {
      rSpeed = MAX_R_SPEED * percentage;
   }
   /** 
    * Determina las velocidades del dron segun los
    * joysticks.
    * @param vertPer (float): angulo de posicion v_pos - left Joy
    * @param forwPer (float): angulo de posicion v_pos - right Joy
    * @param sidePer (float): angulo de posicion h_pos - right Joy
    */
   public void setFlySpeed(float vertPer, float forwPer, float sidePer) {
      vSpeed = MAX_V_SPEED * vertPer;
      fSpeed = MAX_F_SPEED * forwPer;
      sSpeed = MAX_S_SPEED * sidePer;
   }
   /** 
    * Obtiene la informacion de la altura.
    * @return float: altura a la que se encuentra.
    */
   public float getHeight() {
      return h;
   }
   /** 
    * Obtiene la informacion del dron.
    * @return String: posicion x,y,h en string.
    */
   public String toString() {
      return x + ", " + y + ", " + h;
   }
   /** 
    * Realiza el despegue, dependiendo del estado del Dron.
    */
   public void takeOff() {
      if (state==DroneState.IDLE)
         state = DroneState.TAKE_OFF;
      else System.out.println("No Use - Take Off");
   }
   /** 
    * Realiza el aterrizaje, dependiendo del estado del Dron.
    */
   public void land() {
      if (state==DroneState.FLYING)
         state = DroneState.LANDING;
      else System.out.println("No Use - Landing");
   }
}