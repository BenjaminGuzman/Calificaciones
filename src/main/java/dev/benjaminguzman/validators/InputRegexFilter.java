package dev.benjaminguzman.validators;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;
import java.util.regex.Pattern;

public final class InputRegexFilter extends DocumentFilter {
	private final Pattern regex;
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
	public void insertString(final FilterBypass fb, int offset, String string, final AttributeSet attr) throws BadLocationException {
		boolean isLengthOk = offset + string.length() <= this.maxLength;
		boolean isValidPattern = this.regex.matcher(this.document.getText(0, offset) + string).matches();

		if (isLengthOk && isValidPattern)
			fb.insertString(offset, string, attr);
	}

	@Override
	public void replace(final FilterBypass fb, int offset, int length, String text, final AttributeSet attrs) throws BadLocationException {
		String text2Replace = this.document.getText(offset, length);
		String remainingText = this.document.getText(0, this.document.getLength()).replace(text2Replace, "");

		boolean isLengthOk = remainingText.length() + text.length() <= this.maxLength;
		boolean isValidPattern = this.regex.matcher(remainingText + text).matches();
		if (isLengthOk && isValidPattern)
			fb.replace(offset, length, text, attrs);
	}

	@Override
	public void remove(final FilterBypass fb, int offset, int length) throws BadLocationException {
		fb.remove(offset, length);
	}

	@Override
	public String toString() {
		return String.format("InputRegexFilter {\n\tregex: \"%s\"\n}", regex.pattern());
	}
}
