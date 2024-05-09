package util;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Utility class for common operations.
 */
public class Util {

    /** Default port used for server communication. */
    public static final int DEFAULT_PORT = 9001;

    /** IP address of the local host. */
    public static InetAddress IP_ADDRESS;

    static {
        try {
            // Get the local host IP address
            IP_ADDRESS = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            // Print the stack trace in case of unknown host exception
            e.printStackTrace();
        }
    }
}
