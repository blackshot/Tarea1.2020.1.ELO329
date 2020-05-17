import java.io.PrintWriter;
import java.io.IOException;

public class Drone implements Actionable {
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
   private PrintWriter Archive;

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
      try {
         Archive = new PrintWriter("drone.csv", "UTF-8");
         System.out.println("File Created"); // termina el print despues de close
         Archive.write("Drone 1 Start\n");
      } catch (IOException e) {
         System.out.println("File Creation Error");
      }
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
         h += delta_t * TAKEOFF_LANDING_SPEED;
         if (h >= 1.0f){
           state = DroneState.FLYING;
           System.out.println("Drone reached flying altitude...");
         }
         break;
      case FLYING:
         x += delta_t * (fSpeed*(float)Math.cos(direction) + sSpeed*(float)Math.sin(direction));
         y += delta_t * (fSpeed*(float)Math.sin(direction) + sSpeed*(float)Math.cos(direction));
         h += delta_t * vSpeed;
         direction += delta_t * rSpeed;
         break;
      case LANDING: //drone moves only downwards in this stage
          h -= delta_t * TAKEOFF_LANDING_SPEED;
          if (h <= 0){
            state = DroneState.IDLE;
            h = 0.0f; // specifies that it can't go below zero.
          }
          break;
      default: break;
      }
      time = t;

      // Export the data
      this.print2File();
   }

   /** 
    * Determina la velocidad rotacional del dron segun 
    * el left Joy.
    * @param rotPer (float): angulo de posicion left Joy horizontal.
    */
   public void setRotationSpeed(float rotPer) {
      rSpeed = MAX_R_SPEED * rotPer;
   }
   /** 
    * Determina las velocidades del dron segun los
    * joysticks.
    * @param vertPer (float): angulo de posicion left Joy vertical.
    * @param forwPer (float): angulo de posicion right Joy vertical.
    * @param sidePer (float): angulo de posicion right Joy horizontal.
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
    * Obtiene el estado del dron.
    * @return DroneState: estado en el que se encuentra.
    */
   public DroneState getState() {
      return state;
   }
   /** 
    * Obtiene la informacion del dron.
    * @return String: posicion x,y,h en string.
    */
   public String toString() {
      return String.format("% .2f, % .2f, % .2f",x,y,h);
   }
   /** 
    * Realiza el despegue, dependiendo del estado del Dron.
    */
   public void takeOff() {
      if (state==DroneState.IDLE)
         state = DroneState.TAKE_OFF;
      else System.out.println("Error - Take Off");
   }
   /** 
    * Realiza el aterrizaje, dependiendo del estado del Dron.
    */
   public void land() {
      if (state==DroneState.FLYING)
         state = DroneState.LANDING;
      else {
         System.out.println("Error - Landing");
      }
   }
   public void closeFile(){
      Archive.close();
   }
   /** 
    * Imprime la informacion del dron.
    */
   public void print2File(){
      Archive.write(this.toString()+'\n');

   }
}
/* Disposicion espacial
   y
   ^
   |      y
   |   \d |
   |    \ |
   |      D ---- x
   |
   |----------------> x
   */