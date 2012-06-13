package de.gbv.beacon;

/**
 * Expand links, etc.
 *
 * This class receives and validates raw meta fields and link fields from a
 * serialized Beacon dump, constructs full Beacon links ({@link BeaconLink}),
 * and calls the method of a {@link BeaconHandler} to handle the result. 
 * Applications should not directly use this class but implement a 
 * <code>BeaconHandler</code> to receive fully expanded links.
 *
 * @see BeaconHandler
 * @see BeaconParser
 */
public class BeaconBuilder implements BeaconProcessor {

	protected BeaconHandler handler;

	public BeaconBuilder( BeaconHandler handler ) {
		this.handler = handler;
	}

	public void processMeta(String name, String value) throws BeaconException {
		// TODO
	}

	public void processLink(String source, String qualifier, String target) throws BeaconException {
		// TODO
	}

	public void processEnd() {
		// TODO: handleMeta if empty
		// unset meta fields
		handler.beaconEnd();
	}
}
