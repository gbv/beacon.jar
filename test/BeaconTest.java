import junit.framework.TestCase;

import org.xml.sax.*;
import org.xml.sax.helpers.*;
import org.apache.xerces.parsers.SAXParser;

import java.io.*;

import de.gbv.beacon.*; 

public class BeaconTest extends TestCase {

	private class DebugWriter extends DefaultHandler {
		public void startDocument () throws SAXException {
			System.out.println("startDocument");
		}
	}

	public void testBar() {
	}

	public void testFoo() throws Exception {
		String xmlfile = "test/test.xml";
        SAXParser p = new SAXParser();
        p.setContentHandler(new DebugWriter());
        p.parse(xmlfile);
    }

}
