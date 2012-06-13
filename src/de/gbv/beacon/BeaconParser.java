package de.gbv.beacon;

import java.io.Reader;
import java.io.StringReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;

/**
 * Basic interface for Beacon parsers.
 *
 * <p>Implementations of this abstract class parse serialized Beacon dumps.
 * A parser is bound to a {@link BeaconHandler} or to a {@link BeaconProcessor}
 * which process successfully parsed meta fields and links.</p>
 *
 * @author Jakob Voss
 * @see BeaconTextParser
 * @see BeaconXMLParser
 */
public abstract class BeaconParser {

	protected BeaconProcessor processor;

	protected void setProcessor(BeaconProcessor processor) {
		if (processor == null) {
			throw new IllegalArgumentException("Beacon processor/handler must not be null");
		} else {
			this.processor = processor;
		}
	}

	public BeaconParser(BeaconHandler handler) {
		setProcessor(new BeaconBuilder(handler));
	}

	public BeaconParser(BeaconProcessor processor) {
		setProcessor(processor);
	}

	public abstract void parse(Reader source) throws IOException, BeaconException;

	public void parse(InputStream source) throws IOException, BeaconException {
		parse(new InputStreamReader(source));
	}

	public void parse(String source) throws IOException, BeaconException {
		parse(new StringReader(source));
	}
}
