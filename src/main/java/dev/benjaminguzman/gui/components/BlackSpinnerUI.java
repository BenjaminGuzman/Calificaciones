package dev.benjaminguzman.gui.components;

import dev.benjaminguzman.gui.App;
import dev.benjaminguzman.gui.constants.Colors;
import dev.benjaminguzman.listeners.FocusBorderChanger;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicSpinnerUI;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.text.ParseException;

public class BlackSpinnerUI extends BasicSpinnerUI {
	private final JSpinner.DefaultEditor editor;
	private final Font font;
	private final Document document;
	private final JSpinner spinner;

	public BlackSpinnerUI(JSpinner.DefaultEditor editor, Document document, Font font, JSpinner spinner) {
		this.editor = editor;
		this.document = document;
		this.font = font;
		this.spinner = spinner;
	}

	/**
	 * create modifies the styling of the editor and the text field inside it
	 *
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
		formattedTextField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		formattedTextField.setDocument(this.document);
		this.setColors(formattedTextField);

		formattedTextField.addFocusListener(new FocusBorderChanger(this.editor, blackBorder, purpleBorder));

		return this.editor;
	}

	@Override
	protected Component createNextButton() {
		BlackButton.Builder builder = new BlackButton.Builder("▲").normalFgColor(Colors.DEEP_PURPLE);
		BlackButton button = new BlackButton(builder);
		button.addActionListener((ActionEvent evt) -> {
			Object nextValue = this.spinner.getModel().getNextValue();
			if (nextValue != null)
				try {
					this.document.remove(0, this.document.getLength());
					this.document.insertString(0, App.SCORE_NUMBER_FORMAT.format(nextValue), null);
					this.editor.commitEdit();
				} catch (BadLocationException | ParseException e) {
					e.printStackTrace();
				}
		});
		return button;
	}

	@Override
	protected Component createPreviousButton() {
		BlackButton.Builder builder = new BlackButton.Builder("▼").normalFgColor(Colors.DEEP_PURPLE);
		BlackButton button = new BlackButton(builder);
		button.addActionListener((ActionEvent evt) -> {
			Object prevValue = this.spinner.getModel().getPreviousValue();
			if (prevValue != null)
				try {
					this.document.remove(0, this.document.getLength());
					this.document.insertString(0, App.SCORE_NUMBER_FORMAT.format(prevValue), null);
					this.editor.commitEdit();
				} catch (BadLocationException | ParseException e) {
					e.printStackTrace();
				}
		});
		return button;
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
