package de.gbv.beacon;

/**
 * Encapsulate a general Beacon error or warning.
 *
 * <p>This class can contain basic error or warning information from either
 * the {@link BeaconParser Beacon parser} or the Beacon handler application. Examples include
 * invalid Beacon serialization and invalid link elements in a Beacon dump.</p>
 *
 * @author Jakob Voss
 */

public class BeaconException extends Exception {

	/**
	 * Constructs a new BeaconException without detailed message.
	 */
	public BeaconException() {
		super();
	}

	/**
	 * Constructs a new BeaconException with detailed message.
	 *
	 * @param message The error or warning message.
	 */
	public BeaconException(String message) {
		super(message);
	}

    /**
	 * Constructs a new BeaconException wrapping an existing exception.
     *
     * @param cause The exception to be wrapped.
     */
    public BeaconException(Throwable cause) {
		super(cause);
	}
    
    /**
     * Constructs a new BeaconException from an existing exception 
	 * with a new message.
     *
     * @param message The detail message.
     * @param cause The exception to be wrapped.
     */
    public BeaconException (String message, Throwable cause) {
		super(message, cause);
    }
}
