package demo.sample.smartcard;

import java.security.AccessController;
import java.security.Provider;
import java.security.Security;

import javax.smartcardio.TerminalFactory;

import sun.security.action.GetPropertyAction;

public  class CustomTerminalFactory {

    private final static String PROP_NAME =
                        "javax.smartcardio.TerminalFactory.DefaultType";

    /**
     * Returns the default TerminalFactory instance. See
     * {@linkplain #getDefaultType} for more information.
     *
     * <p>A default TerminalFactory is always available. However, depending
     * on the implementation, it may not offer any terminals.
     *
     * @return the default TerminalFactory
     */
    public  TerminalFactory getDefault() {
        // lookup up the user specified type, default to PC/SC
        String type = AccessController.doPrivileged
                            (new GetPropertyAction(PROP_NAME, "PC/SC")).trim();
        TerminalFactory factory = null;
        try {
            factory = TerminalFactory.getInstance(type, null);
            System.out.println(" Created default:" + factory.getDefaultType());
            System.out.println(" Created default:" + factory.getProvider());
        } catch (Exception e) {
        	 System.out.println( e.getLocalizedMessage());
        }
        if (factory == null) {
            // if that did not work, try the Sun PC/SC factory
            try {
                type = "PC/SC";
                System.out.println("Try this");
                Provider sun = Security.getProvider("SunPCSC");
                if (sun == null) {
                    Class<?> clazz = Class.forName("sun.security.smartcardio.SunPCSC");
                    sun = (Provider)clazz.newInstance();
                }
                factory = TerminalFactory.getInstance(type, null, sun);
            } catch (final Exception e) {
                System.out.println( e.getLocalizedMessage());
            }
        }
        System.out.println(" Return Created :" + factory.getDefaultType());
        System.out.println(" Return Created :" + factory.getProvider());
      
        return factory;
    }

  
}