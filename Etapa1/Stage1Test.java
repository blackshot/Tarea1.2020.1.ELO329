import java.io.*;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

public class Stage1Test{
	public static void main(String[] arg) throws IOException{
		Scanner in = new Scanner(new File(arg[0]));
		//Scanner in = new Scanner(new File("comandosDron.csv"));
		float time = 0.0f;
      	Joystick l_Joy = new Joystick();
      	Joystick r_Joy = new Joystick();
		Operator operator = new Operator(in, l_Joy, r_Joy);
		
		NumberFormat timef = NumberFormat.getInstance(Locale.US);
		timef.setMaximumFractionDigits(2);

		System.out.println("tiempo,  jih,  jiv,  jdh,  jdv"); 
      	while(operator.takeAction(time)) {
         	System.out.println(timef.format(time)+","+l_Joy.toString()+","+r_Joy.toString());        	
         	time+=0.1;
		  }
		System.out.println(timef.format(time)+","+l_Joy.toString()+","+r_Joy.toString());  
		in.close();
   }
}

// hola ale
