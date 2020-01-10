package io.calificaciones.gui.components;

import io.calificaciones.gui.constants.Colors;
import io.calificaciones.listeners.FocusBorderChanger;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicSpinnerUI;
import javax.swing.text.Document;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.beans.PropertyChangeListener;

public class BlackSpinnerUI extends BasicSpinnerUI {
    private final JSpinner.DefaultEditor editor;
    private final Font font;
    private final Document document;

    public BlackSpinnerUI(JSpinner.DefaultEditor editor, Document document, Font font) {
        this.editor = editor;
        this.document = document;
        this.font = font;
    }

    /**
     * create modifies the styling of the editor and the text field inside it
     * @return this.editor
     */
    @Override
    protected JComponent createEditor() {
        final Border blackBorder = new LineBorder(Color.DARK_GRAY, 1, true);
        final Border purpleBorder = new LineBorder(Colors.DEEP_PURPLE, 1, true);

        this.setColors(this.editor);
        this.editor.setBorder(blackBorder);

        final JFormattedTextField formattedTextField = this.editor.getTextField();
        formattedTextField.setHorizontalAlignment(SwingConstants.CENTER);
        formattedTextField.setFont(this.font);
        formattedTextField.setCaretColor(Colors.DEEP_PURPLE);
        formattedTextField.setBorder(BorderFactory.createEmptyBorder(5, 5,5, 5));
        formattedTextField.setDocument(this.document);
        this.setColors(formattedTextField);

        formattedTextField.addFocusListener(new FocusBorderChanger(this.editor, blackBorder, purpleBorder));

        return this.editor;
    }

    @Override
    protected Component createNextButton() {
        BlackButton.Builder builder = new BlackButton.Builder("▲").normalFgColor(Colors.DEEP_PURPLE);
        return new BlackButton(builder);
    }

    @Override
    protected Component createPreviousButton() {
        BlackButton.Builder builder = new BlackButton.Builder("▼").normalFgColor(Colors.DEEP_PURPLE);
        return new BlackButton(builder);
    }

    @Override
    protected void installListeners() {
        super.installListeners();
    }

    /**
     * install super defaults and remove L&F border
     */
    @Override
    protected void installDefaults() {
        super.installDefaults();
        LookAndFeel.uninstallBorder(this.spinner);
    }

    @Override
    protected PropertyChangeListener createPropertyChangeListener() {
        return super.createPropertyChangeListener();
    }

    @Override
    protected void installNextButtonListeners(Component c) {
        super.installNextButtonListeners(c);
    }

    @Override
    protected void installPreviousButtonListeners(Component c) {
        super.installPreviousButtonListeners(c);
    }

    @Override
    public void installUI(JComponent c) {
        super.installUI(c);
    }

    private void setColors(JComponent component) {
        component.setForeground(Color.WHITE);
        component.setBackground(Color.BLACK);
    }
}
