package de.gbv.beacon;

/**
 * Process a serialized Beacon dump with raw meta fields and link fields.
 *
 * This interface is used by a {@link BeaconParser} to process raw meta fields
 * and link fields from a serialized Beacon dump.
 *
 * @see BeaconHandler
 */
public interface BeaconProcessor {

	/**
	 * Processes a meta field from a serialized Beacon dump.
	 */
	public void processMeta(String name, String value) throws BeaconException;

	/**
	 * Processes a serialized Beacon link.
	 *
	 * @param source the source field (must not be empty)
	 * @param qualifier the qualifier field (may be empty)
	 * @param target the target field (may be empty)
	 */
	public void processLink(String source, String qualifier, String target) throws BeaconException;

	/**
	 * Process the end of a serialized Beacon dump.
	 */
	public void processEnd();
}


