package co.infinum.skliba.BoatIt.utils;

import java.io.InputStream;

/**
 * Created by noxqs on 29.07.15..
 */
public class ResourceUtils {

    /**
     * Converts InputStream to String.
     */
    public static String convertStreamToString(InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

    /**
     * Reads a resource file to <code>String</code>.
     */
    public static String readFromFile(String filename) {
        InputStream is = ResourceUtils.class.getClassLoader().getResourceAsStream(filename);
        return convertStreamToString(is);

    }

}
