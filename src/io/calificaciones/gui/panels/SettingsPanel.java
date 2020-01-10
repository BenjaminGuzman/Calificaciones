package io.calificaciones.gui.panels;

import io.calificaciones.AppPanel;
import io.calificaciones.gui.App;
import io.calificaciones.gui.components.NumberSpinner;
import io.calificaciones.gui.constants.Fonts;
import io.calificaciones.gui.constants.RegEx;

import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.awt.BorderLayout;

public class SettingsPanel extends AppPanel {
    private PlainDocument defaultScoreDocument, baseDocument;

    private JSpinner spinnerDefaultScore;
    private JSpinner spinnerBase;
    private JPanel rootPanel;

    public SettingsPanel() {
        this.setLayout(new BorderLayout());
    }

    @Override
    public void initComponents() {
        this.add(this.rootPanel, BorderLayout.CENTER);
    }

    private void createUIComponents() {
        NumberSpinner numberDefaultScoreSpinner = new NumberSpinner();
        this.spinnerDefaultScore = numberDefaultScoreSpinner.createJSpinner(
                new SpinnerNumberModel(App.DEFAULT_SCORE, 0, 999, 1),
                RegEx.DECIMAL_3_INT_2_FLOAT,
                this::updateMaxScore,
                Fonts.GO_REGULAR_26);
        this.defaultScoreDocument = numberDefaultScoreSpinner.getDocument();

        NumberSpinner numberBaseSpinner = new NumberSpinner();
        this.spinnerBase = numberBaseSpinner.createJSpinner(
                new SpinnerNumberModel(App.BASE_PERCENT, 0, 9999, 1),
                RegEx.DECIMAL_4_INT_2_FLOAT,
                this::updateBase,
                Fonts.GO_REGULAR_26);
        this.baseDocument = numberBaseSpinner.getDocument();
    }

    private void updateMaxScore() {
        String maxScoreString;
        try {
            maxScoreString = this.defaultScoreDocument.getText(0, this.defaultScoreDocument.getLength());
        } catch (BadLocationException e) {
            // TODO: handle exception, how?
            e.printStackTrace();
            return;
        }
        if (maxScoreString.isEmpty() || maxScoreString.isBlank())
            return;

        App.setDefaultScore(Float.parseFloat(maxScoreString));
    }

    private void updateBase() {
        String baseString;
        try {
            baseString = this.baseDocument.getText(0, this.baseDocument.getLength());
        } catch (BadLocationException e) {
            // TODO: handle exception, how?
            e.printStackTrace();
            return;
        }
        if (baseString.isEmpty() || baseString.isBlank())
            return;

        App.setBasePercent(Integer.parseInt(baseString));
    }
}
