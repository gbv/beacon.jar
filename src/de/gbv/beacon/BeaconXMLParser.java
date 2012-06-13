package de.gbv.beacon;

import java.io.Reader;
import java.io.IOException;

/**
 * Beacon XML format parser.
 *
 * This class is not implemented yet.
 */
public class BeaconXMLParser extends BeaconParser {

	public BeaconXMLParser(BeaconHandler handler) {
		super(handler);
	}

	public BeaconXMLParser(BeaconProcessor processor) {
		super(processor);
	}

	public void parse(Reader source) throws IOException, BeaconException {
		throw new IOException("not implemented yet");
	}
}
