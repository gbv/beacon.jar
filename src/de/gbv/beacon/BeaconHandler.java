package de.gbv.beacon;

import java.util.HashMap;

/**
 * Process metadata and links from a Beacon link dump.
 *
 * <p>This is the main interface that applications must implement to process
 * a Beacon dump from a {@link BeaconParser}. The parser uses the method of
 * this interface as callback in the following order:
 *
 * <ul>
 *   <li>first {@link #beaconMeta} is called once,</li>
 *   <li>then {@link #beaconLink} is called zero or more times,</li>
 *   <li>then {@link #beaconEnd} is called once.</li>
 * </ul>
 *
 * This process may be repeated when parsing multiple Beacon dumps.</p>
 *
 * <p>All meta fields and links send to the callback methods should be 
 * validated and expanded. To directly access serialized Beacon dumps,
 * one may alternatively use the interface {@link BeaconProcessor}.</p>
 *
 * @author Jakob Voss
 * @see BeaconProcessor
 */
public interface BeaconHandler {

	/**
	 * Called once to process all annotating meta fields.
	 */
	public void beaconMeta( HashMap<String,String> metadata ); // +, HashMap<String,String> construct...?

	/**
	 * Called for each link in the Beacon dump.
	 * 
	 * The relation type is guaranteed to be equal for all links until the next
	 * call of {@link #beaconLink}.
	 */
	public void beaconLink( BeaconLink link );

	/**
	 * Called once after the last link in a Beacon dump.
	 */
	public void beaconEnd();
}
