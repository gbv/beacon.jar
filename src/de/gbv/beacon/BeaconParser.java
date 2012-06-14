package de.gbv.beacon;

import java.io.Reader;
import java.io.StringReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;

import org.xml.sax.InputSource;

/**
 * Basic interface for Beacon parsers.
 *
 * Implementations of this abstract class parse serialized Beacon dumps.
 * A parser is bound to a {@link BeaconHandler} or to a {@link BeaconProcessor}
 * which process successfully parsed meta fields and links. Applications should 
 * also register a {@link ErrorHandler} before starting to parse.
 *
 * @author Jakob Voss
 * @see BeaconTextParser
 * @see BeaconXMLParser
 */
public abstract class BeaconParser {

	private static ErrorHandler defaultErrorHandler = new DefaultErrorHandler();

	protected BeaconProcessor processor;
	protected ErrorHandler errorHandler = defaultErrorHandler;

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

	/**
	 * Parse a serialized Beacon dump.
	 *
	 * Unrecoverable errors will throw an excpetion. Recoverable errors and 
	 * warnings will be notified to the registered {@link ErrorHandler}, 
	 * which may choose to throw an exception to stop parsing.
	 */
	public void parse(Reader source, String systemID) throws IOException, BeaconException {
		// TODO: deal with systemId
		parse(source);
	}

	/**
	 * Parse a serialized Beacon dump from an input stream.
	 */
	public void parse(InputStream source) throws IOException, BeaconException {
		parse(new InputStreamReader(source),null);
	}

	/**
	 * Parse a serialized Beacon dump from a string. 
	 */
	public void parse(String source) throws IOException, BeaconException {
		parse(new StringReader(source),null);
	}

	/**
	 * Parse a serialized Beacon from an input source.
	 */
	public void parse(InputSource source) throws IOException, BeaconException {
		Reader reader   = source.getCharacterStream();
		String systemId = source.getSystemId();
		parse(reader,systemId);
	}

	/**
	 * Register an error event handler.
	 *
	 * If the application does not register an error handler (also by setting
	 * the handler to null) an instance of {@link DefaultErrorHandler} is used.
	 *
	 * @param handler The error handler or null.
	 */
	public void setErrorHandler(ErrorHandler handler) {
		this.errorHandler = (handler != null ? handler : defaultErrorHandler);
	}

	/**
	 * Return the current error handler.
	 *
	 * @return The current error handler
	 */
	public ErrorHandler getErrorHandler() {
		return this.errorHandler;
	}
}
