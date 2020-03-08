package io.calificaciones.validators;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;
import java.util.regex.Pattern;

public final class InputRegexFilter extends DocumentFilter {
    private Pattern regex;
    private int maxLength = 10;
    private Document document;

    public InputRegexFilter(Pattern regex, int maxLength) {
        this.regex = regex;
        this.maxLength = maxLength;
    }
    public InputRegexFilter(Pattern regex) {
        this.regex = regex;
    }

    public InputRegexFilter setDocument(final Document document) {
        this.document = document;
        return this;
    }

    @Override
    public void insertString(final FilterBypass fb, final int offset, final String string, final AttributeSet attr) throws BadLocationException {
        // bypass only if the document and the string does not exceed maxLength and the string corresponds the the regex
        if (offset + string.length() <= this.maxLength && this.regex.matcher(this.document.getText(0, offset) + string).matches())
            fb.insertString(offset, string, attr);
    }
    @Override
    public void replace(final FilterBypass fb, final int offset, final int length, final String text, final AttributeSet attrs) throws BadLocationException {
        // bypass only if the document and the string does not exceed maxLength and the string corresponds the the regex
        String text2Replace = this.document.getText(offset, length);
        String remainingText = this.document.getText(0, this.document.getLength()).replace(text2Replace, "");
        if (remainingText.length() + text.length() <= this.maxLength && this.regex.matcher(remainingText + text).matches())
            fb.replace(offset, length, text, attrs);
    }
    @Override
    public void remove(final FilterBypass fb, final int offset, final int length) throws BadLocationException {
        fb.remove(offset, length);
    }

    @Override
    public String toString() {
        return String.format("InputRegexFilter {\n\tregex: \"%s\"\n}", regex.pattern());
    }
}
