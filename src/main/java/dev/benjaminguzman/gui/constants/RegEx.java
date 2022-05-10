package dev.benjaminguzman.gui.constants;

import java.util.regex.Pattern;

public class RegEx {
	public final static Pattern NATURAL_NUMBER = Pattern.compile("^\\d+$");
	public final static Pattern DECIMAL_3_INT_2_FLOAT = Pattern.compile("^\\d{1,3}(\\.(\\d{1,2})?)?$");
	public final static Pattern DECIMAL_4_INT_2_FLOAT = Pattern.compile("^\\d{1,4}(\\.(\\d{1,2})?)?$");
}
