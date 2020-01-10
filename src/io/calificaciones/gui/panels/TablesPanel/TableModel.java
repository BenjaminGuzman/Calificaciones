package io.calificaciones.gui.panels.TablesPanel;

import io.calificaciones.gui.App;

import javax.swing.table.AbstractTableModel;

public final class TableModel extends AbstractTableModel {
    private short nCols;
    private short nRows;
    private float singleScore;
    private final TablesPanel tablesPanelRef;

    public TableModel(final TablesPanel tablesPanelRef) {
        this.tablesPanelRef = tablesPanelRef;
        this.updateConstraints();
    }

    /**
     * Calculates the number or rows and columns to use
     * as well as a single score percentage (1/maxScore)
     */
    public void updateConstraints() {
        this.nRows = 15;

        if (this.tablesPanelRef.getMaxScore() <= this.nRows)
            this.nRows = (short) (this.tablesPanelRef.getMaxScore() + 1);
        else
            this.nRows = 30;

        this.nCols = (short) (Math.round((float)this.tablesPanelRef.getMaxScore() / this.nRows) * 2);

        this.singleScore = 1f / this.tablesPanelRef.getMaxScore();
    }

    /**
     * Calculates either the score or the percentage in the position row, col
     * @param row row
     * @param col col
     * @return either the score or the percentage depending on row, col
     */
    private int getScoreAt(final int row, final int col) {
        final int nColumnOfScores = (col + 2) / 2;
        final int initScoreFromCol = (nColumnOfScores - 1) * this.nRows;
        return initScoreFromCol + row;
    }

    @Override
    public int getRowCount() {
        return this.nRows;
    }

    @Override
    public int getColumnCount() {
        return this.nCols;
    }

    /**
     *
     * @param row
     * @param col
     * @return the score or the percent depending on row and col
     */
    @Override
    public String getValueAt(final int row, final int col) {
        if (col % 2 == 0) // if column is the actual score
            return String.valueOf(this.getScoreAt(row, col));

        return App.SCORE_NUMBER_FORMAT.format(this.getScoreAt(row, col - 1) * this.singleScore * App.BASE_PERCENT);
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        // all columns should be of type String
        return String.class;
    }
}
