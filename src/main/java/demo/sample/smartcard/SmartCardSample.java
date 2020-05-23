package demo.sample.smartcard;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import javax.smartcardio.CardTerminal;
import javax.smartcardio.CardTerminals;
import javax.smartcardio.TerminalFactory;


public class SmartCardSample {

	
	public static void getReaders() throws Exception {
        try {
            TerminalFactory fac =  ( new CustomTerminalFactory()).getDefault();
        	//TerminalFactory fac = TerminalFactory.getDefault();
        	CardTerminals terminals = fac.terminals();     
          
            if (terminals != null) {
                System.out.println("CardTerminals no null");
           
            } else {
                System.out.println("CardTerminals is null.");
            }
            List<CardTerminal> list = terminals.list();
            if (list != null) {
                System.out.println("terminal list: " + list.size());
            } else {
                System.out.println("List is null.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
///////////////////////////////////////////////////////////////////////////
// MAIN
///////////////////////////////////////////////////////////////////////////	
	public static void main(String[] args) {	
		System.loadLibrary("j2pcsc"); 
		/*if (args != null && args.length > 0 
				&&
				args[0] != null) {
			System.loadLibrary(args[0] ); 
		}*/
        try {
            while (true) {
                getReaders();
                //Print Loaded Libs.
                final String[] libraries = ClassScope.getLoadedLibraries(SmartCardSample.class.getClassLoader()); 
        		for ( int i = 0; i< libraries.length; i++){
        			System.out.println(libraries[i]);
        		}
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("Press enter to continue.");
                reader.readLine(); //wait until user press enter

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
}
