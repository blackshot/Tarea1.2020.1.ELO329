import java.io.*;
import java.util.Scanner;
import java.util.Locale;

public class Stage1{
    public static void main (String[] arg) throws IOException {
        Locale.setDefault(Locale.US);  // to read number in US format, like 1.5 (not like 1,5)

        final Scanner in = new Scanner(new File(arg[0]));
        
        // Time init
        float time = 0.0f;
        
        final Joystick l_Joy = new Joystick();
        final Joystick r_Joy = new Joystick();
        final Operator operator = new Operator(in, l_Joy, r_Joy);
        
        while(operator.takeAction(time)) {
            time+=0.1;
        }
    }
}