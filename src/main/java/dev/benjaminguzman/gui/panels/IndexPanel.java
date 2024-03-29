package dev.benjaminguzman.gui.panels;

import dev.benjaminguzman.AppPanel;
import dev.benjaminguzman.gui.App;
import dev.benjaminguzman.gui.components.NumberSpinner;
import dev.benjaminguzman.gui.constants.Colors;
import dev.benjaminguzman.gui.constants.Fonts;
import dev.benjaminguzman.gui.constants.RegEx;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.awt.*;

public class IndexPanel extends AppPanel {
	// inputs score (the input inside the corresponding JSpinner)
	private PlainDocument scoreDocument;
	private PlainDocument maxScoreDocument;

	private float maxScore = 25;

	private final float BAD_SCORE = 0.5f;
	private final float MEDIUM_SCORE = 0.7f;

	private JSpinner spinnerScore;
	private JSpinner spinnerMaxScore;
	private JLabel labelScore;
	private JLabel warningLabel;
	private JPanel rootPanel;

	public IndexPanel() {
		super();
		this.setLayout(new BorderLayout());
		this.maxScore = App.DEFAULT_SCORE;
	}

	@Override
	public void initComponents() {
		this.add(this.rootPanel, BorderLayout.CENTER);
		this.setBackground(new Color(33, 33, 33));
		this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		// set default values
		this.spinnerMaxScore.setValue((double) this.maxScore);
		this.spinnerScore.setValue((double) this.maxScore);
	}

	@Override
	public void afterInserted() {
		this.maxScore = App.DEFAULT_SCORE;
		String defaultScoreFormatted = App.SCORE_NUMBER_FORMAT.format(App.DEFAULT_SCORE);
		try {
			this.maxScoreDocument.remove(0, this.maxScoreDocument.getLength());
			this.maxScoreDocument.insertString(0, defaultScoreFormatted, null);
		} catch (BadLocationException e) {
			e.printStackTrace();
			// TODO: how to handle exception?
		}
	}

	/**
	 * Creates the JSpinners spinnerScore and spinnerMaxScore
	 * Adds validation and listeners to the text fields of the spinners
	 * Calls updateScore when spinnerScore input is valid
	 * Calls updateMaxScore when spinnerMaxScore input is valid
	 */
	private void createUIComponents() {
		NumberSpinner numberScoreSpinner = new NumberSpinner();
		this.spinnerScore = numberScoreSpinner.createJSpinner(
			new SpinnerNumberModel(1.0, 0.0, 999.0, 1.0),
			RegEx.DECIMAL_3_INT_2_FLOAT,
			this::updateScore,
			Fonts.GO_REGULAR_BOLD_70
		);
		this.scoreDocument = numberScoreSpinner.getDocument();

		NumberSpinner numberMaxScoreSpinner = new NumberSpinner();
		this.spinnerMaxScore = numberMaxScoreSpinner.createJSpinner(
			new SpinnerNumberModel(1.0, 0.0, 999.0, 1.0),
			RegEx.DECIMAL_3_INT_2_FLOAT,
			this::updateMaxScore,
			Fonts.GO_REGULAR_26
		);
		this.maxScoreDocument = numberMaxScoreSpinner.getDocument();
	}

	/**
	 * updates this.maxScore
	 * it is triggered when this.maxScoreDocument updates
	 * calls this.updateScore when finished
	 */
	private void updateMaxScore() {
		String pointsString;
		try {
			pointsString = this.maxScoreDocument.getText(0, this.maxScoreDocument.getLength());
		} catch (BadLocationException e) {
			// TODO: how to handle exception?
			return;
		}

		if (pointsString.isBlank())
			return;

		this.maxScore = Float.parseFloat(pointsString);
		this.updateScore();
	}

	/**
	 * updates the score in the this.labelScore after some checks and styling
	 */
	private void updateScore() {
		String scoreString;
		try {
			scoreString = this.scoreDocument.getText(0, this.scoreDocument.getLength());
		} catch (BadLocationException e) {
			// TODO: how to handle exception?
			return;
		}

		// calculate score
		float score = 0;
		if (RegEx.DECIMAL_3_INT_2_FLOAT.matcher(scoreString).matches())
			score = Float.parseFloat(scoreString) / this.maxScore;

		Color color = Colors.PURPLE;
		// select score color
		if (score <= this.BAD_SCORE)
			color = Color.RED;
		else if (score <= this.MEDIUM_SCORE)
			color = Color.YELLOW;
		else if (score < 1.0f)
			color = Colors.GREEN_A700;
		else if (score == 1.0f)
			color = Color.GREEN;

		this.labelScore.setText(App.SCORE_NUMBER_FORMAT.format(score * App.BASE_PERCENT));
		this.labelScore.setForeground(color);

		this.warningLabel.setVisible(score > 1.0f);
	}

	/**
	 * Format:
	 * IndexPanel {
	 * maxPoints: %d,
	 * scoreDocument: %s {PlainDocument}, -> with scoreDocument.toString
	 * maxScoreDocument: %s {PlainDocument}, -> with maxScoreDocument.toString
	 * }
	 *
	 * @return string representation of the instance of IndexPanel
	 */
	@Override
	public String toString() {
		return String.format("""
				IndexPanel {
				\tmaxPoints: %f,
				\tscoreDocument: %s {PlainDocument},
				\tmaxScoreDocument: %s {PlainDocument}
				\tBAD_SCORE: %f
				\tMEDIUM_SCORE: %f
				}""",
			this.maxScore,
			this.scoreDocument,
			this.maxScoreDocument,
			this.BAD_SCORE,
			this.MEDIUM_SCORE);
	}

}
