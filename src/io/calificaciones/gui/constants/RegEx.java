package io.calificaciones.gui.constants;

import java.util.regex.Pattern;

public class RegEx {
    public final static Pattern NATURAL_NUMBER = Pattern.compile("^[0-9]+$");
    public final static Pattern DECIMAL_3_INT_2_FLOAT = Pattern.compile("^[0-9]{1,3}(\\.([0-9]{1,2})?)?$");
    public final static Pattern DECIMAL_4_INT_2_FLOAT = Pattern.compile("^[0-9]{1,4}(\\.([0-9]{1,2})?)?$");
}
