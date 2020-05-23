package demo.sample.smartcard;

import java.util.Vector;

public class ClassScope {
    private static final java.lang.reflect.Field LIBRARIES;
    static {
        try {
			LIBRARIES = ClassLoader.class.getDeclaredField("loadedLibraryNames");
			LIBRARIES.setAccessible(true);
		} catch (NoSuchFieldException | SecurityException e) {		
			e.printStackTrace();
		    throw new  IllegalStateException( "Cannot initialize");
		}
    }
    public static String[] getLoadedLibraries(final ClassLoader loader) {
        Vector<String> libraries;
		try {
			libraries = (Vector<String>) LIBRARIES.get(loader);
		    return libraries.toArray(new String[] {});
		} catch (IllegalArgumentException | IllegalAccessException e) {			
			throw new  IllegalStateException(e);
		}
	
   
    }
}