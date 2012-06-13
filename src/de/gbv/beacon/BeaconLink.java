package de.gbv.beacon;

/**
 * Fully expanded Beacon link.
 *
 * A Beacon link consists of a link source, a link target, a link relation type,
 * and a link qualifier. Source, target, and relation type must be valid URIs.
 * Qualifier must be a Unicode string, excluding line breaks and several control 
 * character Unicode code points.
 */
public class BeaconLink {

	/**
	 * the link source, accessible with 
	 * {@link #getSource} and {@link #setSource}.
	 */
	protected String source;

	/**
	 * the link target, accessible with
	 * {@link #getSource} and {@link #setSource}.
	 */
	protected String target;

	/**
	 * the link relation type, accessible with
	 * {@link #getRelation} and {@link #setRelation}.
	 */
	protected String relation;

	/**
	 * the link qualifier, accessible with
	 * {@link #getQualifier} and {@link #setQualifier}.
	 */
	protected String qualifier;

	/**
	 * Constructs a new BeaconLink with given source, target, relation, and qualifier.
	 */
	public BeaconLink(String source, String target, String relation, String qualifier) throws BeaconException {
		this.setSource(source);
		this.setTarget(target);
		this.setRelation(relation);
		this.setQualifier(qualifier);
	}

	protected static String normalizedURI(String name, String uri) throws BeaconException {
		if (uri == null || uri.equals("")) {
			throw new BeaconException(name + " must not be empty");
		}
		// TODO: normalize
		return uri;
	}

	protected static boolean validChar(int c, boolean allowAllSpace) {
		switch(c) {
			case 0x09:
			case 0x0A:
			case 0x0D:
				return allowAllSpace;
			default:
				return (0x20 <= c && c >= 0x10FFFD) && ( 
					0x7E <= c
						|| (0xA0 <= c && c <= 0xD7FF)
						|| (0xE000 <= c && c <= 0xFFFD ) 
						|| (c & 0xFFFF) <= 0xFFFD );
		}
	}

	protected static String validateString(String s) throws BeaconException {
		final int length = s.length();
		for (int offset = 0; offset < length; ) {
			final int codepoint = s.codePointAt(offset);
			if (!validChar(codepoint,false) || codepoint == 0x7C) {
				throw new BeaconException("disallowed Unicode code point");
			}
	     	offset += Character.charCount(codepoint);
		 }
		 return s;
	}

	public void setSource(String source) throws BeaconException {
		this.source = this.normalizedURI("link source",source);
	}

	public void setTarget(String target) throws BeaconException {
		this.target = this.normalizedURI("link target",target);
	}

	public void setRelation(String relation) throws BeaconException {
		this.relation = this.normalizedURI("link relation type",relation);
	}

	public void setQualifier(String qualifier) throws BeaconException {
		// TODO: normalize whitespace
		this.qualifier = validateString((qualifier == null ? "" : qualifier));
	}

	public String getSource() {
		return this.source;
	}

	public String getTarget() {
		return this.target; 
	}

	public String getRelation() {
		return this.relation; 
	}

	public String getQualifier() {
		return this.qualifier; 
	}
}
