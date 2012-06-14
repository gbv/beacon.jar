package de.gbv.beacon;

/**
 * Ignores warnings and throws errors.
 */
public class DefaultErrorHandler implements ErrorHandler {

	/**
	 * Receive and ignore notification of a warning.
	 */
	public void warning(BeaconException exception) 
		throws BeaconException { }

	/**
	 * Receive notification of a recoverable error and throw an exception.
	 */
	public void error(BeaconException exception)
		throws BeaconException { throw exception; }

}
