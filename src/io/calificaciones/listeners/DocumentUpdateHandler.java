package io.calificaciones.listeners;

import javax.swing.JSpinner;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.text.ParseException;

public class DocumentUpdateHandler implements DocumentListener {
    private UpdateHandler onRemoveHandler;
    private UpdateHandler onInsertHandler;
    private UpdateHandler onChangedHandler;
    private JSpinner.DefaultEditor editor;

    public DocumentUpdateHandler(Builder builder) {
        this.editor = builder.editor;
        this.onChangedHandler = builder.onChangedHandler;
        this.onInsertHandler = builder.onInsertHandler;
        this.onRemoveHandler = builder.onRemoveHandler;
    }

    @Override
    public void insertUpdate(final DocumentEvent documentEvent) {
        if (this.onInsertHandler != null)
            this.onInsertHandler.handleUpdate();
        this.commitEdit();
    }
    @Override
    public void removeUpdate(final DocumentEvent documentEvent) {
        if (this.onRemoveHandler != null)
            this.onRemoveHandler.handleUpdate();
        this.commitEdit();
    }
    @Override
    public void changedUpdate(final DocumentEvent documentEvent) {
        if (this.onChangedHandler != null)
            this.onChangedHandler.handleUpdate();
        this.commitEdit();
    }

    private void commitEdit() {
        if (this.editor != null) {
            try {
                this.editor.commitEdit();
            } catch (ParseException e) {
                // TODO: handle exception, how?
                return;
            }
        }
    }

    public static interface UpdateHandler {
        void handleUpdate();
    }

    public static final class Builder {
        private UpdateHandler onRemoveHandler;
        private UpdateHandler onInsertHandler;
        private UpdateHandler onChangedHandler;
        private JSpinner.DefaultEditor editor;

        public Builder() {}

        public Builder onInsertHandler(final UpdateHandler handler) {
            this.onInsertHandler = handler;
            return this;
        }
        public Builder onRemoveHandler(final UpdateHandler handler) {
            this.onRemoveHandler = handler;
            return this;
        }
        public Builder onChangeHandler(final UpdateHandler handler) {
            this.onChangedHandler = handler;
            return this;
        }
        public Builder setEditor(JSpinner.DefaultEditor editor) {
            this.editor = editor;
            return this;
        }
    }
}
