package dev.benjaminguzman.listeners;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public record FocusBorderChanger(
	JComponent component,
	Border borderFocusOff,
	Border borderFocusOn
) implements FocusListener {

	@Override
	public void focusGained(final FocusEvent focusEvent) {
		this.component.setBorder(this.borderFocusOn);
	}

	@Override
	public void focusLost(FocusEvent focusEvent) {
		this.component.setBorder(this.borderFocusOff);
	}
}
