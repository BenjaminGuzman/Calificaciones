package io.calificaciones.listeners;

import javax.swing.JComponent;
import javax.swing.border.Border;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class FocusBorderChanger implements FocusListener {
    private final Border borderFocusOff, borderFocusOn;
    private final JComponent component;

    public FocusBorderChanger(JComponent component, Border borderFocusOff, Border borderFocusOn) {
        this.borderFocusOff = borderFocusOff;
        this.borderFocusOn = borderFocusOn;
        this.component = component;
    }

    @Override
    public void focusGained(final FocusEvent focusEvent) {
        this.component.setBorder(this.borderFocusOn);
    }

    @Override
    public void focusLost(FocusEvent focusEvent) {
        this.component.setBorder(this.borderFocusOff);
    }
}
