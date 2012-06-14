package de.gbv.beacon;

/**
 * Basic interface for Beacon error handlers.
 *
 * Beacon applications should implement this interface and register an instance
 * using the {@link BeaconParser#setErrorHandler setErrorHandler} method to
 * receive notifications of warnings and recoverable errors. The error handler
 * may decide what to do with the message and throw an exception to stop
 * parsing. Non-recoverable errors will not be reported to this interface 
 * but {@link de.gbv.beacon.BeaconParser BeaconParser} will directly throw an 
 * expection.
 *
 * @author Jakob Voss
 * @see de.gbv.beacon.BeaconException
 *
 * Invalid meta fields or links and invalid serializations
 */
public interface ErrorHandler {

	/**
	 * Receive notification of a warning.
	 *
	 * Examples of warnings include violations of recommendations in the Beacon
	 * specification, such as unknown meta fields.
	 *
	 * @param exception The warning information encapsulated as Beacon exception
	 */
	public abstract void warning(BeaconException exception) 
		throws BeaconException;

	/**
	 * Receive notification of a recoverable error.
	 *
	 * Examples of errors include invalid Beacon links and recoverable syntax
	 * errors in serialized Beacon dumps. Applications should not silently
	 * ignore these errors.
	 *
	 * @param exception The error information encapsulated as Beacon exception
	 */
	public abstract void error(BeaconException exception)
		throws BeaconException;
}
