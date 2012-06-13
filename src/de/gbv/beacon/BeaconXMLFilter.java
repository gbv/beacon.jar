package de.gbv.beacon;

import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLFilterImpl;

/**
 * This class implements a filter to produce a normalized BEACON SAX stream.
 */
public class BeaconXMLFilter extends XMLFilterImpl {

	public BeaconXMLFilter() {
	}

	public BeaconXMLFilter(XMLReader parent) { 
		super(parent); 
	}

//	private 
	public void characters(char[] ch, int start, int length) {
		
	}

	// drop all ignorable whitespace, processing instructions, etc.
	public void	ignorableWhitespace(char[] ch, int start, int length) { } 
	public void processingInstruction(String target, String data) {
	}

//	super.
//	public void startElement(String uri, String localName, String qname, Attributes atts) throws SAXException {
}
