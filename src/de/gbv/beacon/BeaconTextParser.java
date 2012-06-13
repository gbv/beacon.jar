package de.gbv.beacon;

import java.io.Reader;
import java.io.IOException;
import java.io.LineNumberReader;

/**
 * Beacon text format parser.
 */
public class BeaconTextParser extends BeaconParser {

	private LineNumberReader linereader;

	public BeaconTextParser(BeaconHandler handler) {
		super(handler);
	}

	public BeaconTextParser(BeaconProcessor processor) {
		super(processor);
	}

	public void parse(Reader reader) throws IOException, BeaconException {

		// TODO: handle errors/warnings with errorHandler?

		// LineNumberReader compressed "\r\n" and "\r" to a single "\n"
		this.linereader = new LineNumberReader(reader);

		String line = linereader.readLine();

		// read meta lines
		while ( line != null ) {
			if (line.charAt(0) != '#') break;
			
			int p = line.indexOf(':');
			if (p == -1) {
				throw new BeaconException("Invalid Beacon meta field");
			} else {
				String name  = line.substring(0,p-1);
				String value = line.substring(p+1);
				processor.processMeta(name, value);
			}

			line = linereader.readLine();
		}

		// skip empty lines
		while ( line != null || line == "" || line.matches("[\t ]+")) {
			line = linereader.readLine();
		}

		// read link lines
		while ( line != null ) {
			String source	 = null;
			String qualifier = null;
			String target    = null;

			int p = line.indexOf('|');
			if (p == -1) {
				source = line;
			} else {
				source = line.substring(0,p-1);
				int q = line.lastIndexOf('|',p+1); // TODO: +1??
				if (q == -1) {
					qualifier = line.substring(p+1);
				} else {
					qualifier = line.substring(p+1,q-1);
					target = line.substring(q+1);
				}
			}
			processor.processLink(source, qualifier, target); 

			line = linereader.readLine();
		}

		processor.processEnd();
	}
}
