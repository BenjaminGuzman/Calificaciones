package io.calificaciones.gui.components;


import io.calificaciones.listeners.DocumentUpdateHandler;
import io.calificaciones.validators.InputRegexFilter;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;
import java.awt.Font;
import java.util.regex.Pattern;

public class NumberSpinner {
    private PlainDocument document;
    private JSpinner.NumberEditor editor;

    public JSpinner createJSpinner(SpinnerNumberModel spinnerNumberModel, Pattern regex, DocumentUpdateHandler.UpdateHandler updateHandler, Font font) {
        JSpinner spinner = new JSpinner(spinnerNumberModel);

        final InputRegexFilter inputRegexFilter = new InputRegexFilter(regex, 6);

        this.document = this.createSpinnerDocument(inputRegexFilter, updateHandler);
        this.editor = new JSpinner.NumberEditor(spinner);
        spinner.setEditor(this.editor);
        spinner.setUI(new BlackSpinnerUI(this.editor, this.document, font, spinner));

        return spinner;
    }

    public PlainDocument getDocument() {
        return this.document;
    }

    /**
     * creates a new PlainDocument for the JSpinner input field
     * the create document will have a DocumentFilter to suppress non-digit characters
     * and a DocumentListener to listen changes in the input field, calls updateHandler if valid
     *
     * @param inputRegexFilter self explanatory
     * @param updateHandler    lambda expression to execute when input field changes
     * @return the new PlainDocument
     */
     private PlainDocument createSpinnerDocument(final InputRegexFilter inputRegexFilter, final DocumentUpdateHandler.UpdateHandler updateHandler) {
        // create PlainDocument with regex filter to suppress non-digit characters
        PlainDocument document = new PlainDocument() {
            private InputRegexFilter filter;
            @Override
            public DocumentFilter getDocumentFilter() {
                if (filter == null)
                    filter = inputRegexFilter.setDocument(this);
                return filter;
            }
        };

        // Create DocumentListener that will trigger updateHandler if input is valid
        final DocumentUpdateHandler.Builder builder = new DocumentUpdateHandler.Builder()
                .setEditor(this.editor)
                .onInsertHandler(updateHandler)
                .onRemoveHandler(updateHandler);

        document.addDocumentListener(new DocumentUpdateHandler(builder));

        return document;
    }
}