package io.calificaciones.listeners;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class DocumentUpdateHandler implements DocumentListener {
    private UpdateHandler onRemoveHandler;
    private UpdateHandler onInsertHandler;
    private UpdateHandler onChangedHandler;

    public DocumentUpdateHandler(Builder builder) {
        this.onChangedHandler = builder.onChangedHandler;
        this.onInsertHandler = builder.onInsertHandler;
        this.onRemoveHandler = builder.onRemoveHandler;
    }

    @Override
    public void insertUpdate(final DocumentEvent documentEvent) {
        if (this.onInsertHandler != null)
            this.onInsertHandler.handleUpdate();
    }
    @Override
    public void removeUpdate(final DocumentEvent documentEvent) {
        if (this.onRemoveHandler != null)
            this.onRemoveHandler.handleUpdate();
    }
    @Override
    public void changedUpdate(final DocumentEvent documentEvent) {
        if (this.onChangedHandler != null)
            this.onChangedHandler.handleUpdate();
    }

    public static interface UpdateHandler {
        void handleUpdate();
    }

    public static final class Builder {
        private UpdateHandler onRemoveHandler;
        private UpdateHandler onInsertHandler;
        private UpdateHandler onChangedHandler;

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
    }
}
