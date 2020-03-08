package io.calificaciones.gui.panels.TablesPanel;

import io.calificaciones.AppPanel;
import io.calificaciones.Main;
import io.calificaciones.gui.Alerts;
import io.calificaciones.gui.App;
import io.calificaciones.gui.components.BlackButton;
import io.calificaciones.gui.components.BlackScrollBarUI;
import io.calificaciones.gui.components.NumberSpinner;
import io.calificaciones.gui.constants.Colors;
import io.calificaciones.gui.constants.Fonts;
import io.calificaciones.gui.constants.RegEx;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.print.PrinterException;

public class TablesPanel extends AppPanel {
    private PlainDocument maxScoreDocument;
    private TableModel tableModel;

    private int maxScore = 10;
    private int nCols = 0;
    private int nRows = 0;

    private JSpinner spinnerMaxScore;
    private JButton buttonExportPDF;
    private JPanel rootPanel;
    private JTable table;
    private JScrollPane scrollPane;

    public TablesPanel() {
        this.setLayout(new BorderLayout());
        this.maxScore = Math.round(App.DEFAULT_SCORE);
    }

    @Override
    public void initComponents() {
        this.add(this.rootPanel, BorderLayout.CENTER);

        // add listeners
        buttonExportPDF.addActionListener((ActionEvent actionEvent) -> {
            try {
                this.table.print();
            } catch (PrinterException e) {
                Main.LOGGER.severe("Error occurred while trying to print in TablesPanel.java");
                Main.LOGGER.severe(e.toString());
                Main.LOGGER.severe(e.getMessage());
                Main.LOGGER.severe(e.getLocalizedMessage());
                Alerts.showErrorOccurred(
                        "Algo salió mal al intentar imprimir. Revise la configuración de impresoras en su sistema.\n\nEn entornos Unix es preferible tener instalado CUPS",
                        "Error al imprimir");
            }
        });
    }

    @Override
    public void afterInserted() {
        try {
            this.maxScoreDocument.remove(0, this.maxScoreDocument.getLength());
            this.maxScoreDocument.insertString(0, App.SCORE_NUMBER_FORMAT.format(App.DEFAULT_SCORE), null);
        } catch (BadLocationException e) {
            e.printStackTrace();
            // TODO: handle exception, how?
        }
    }

    private void createUIComponents() {
        // spinnerMaxScore
        NumberSpinner spinner = new NumberSpinner();
        this.spinnerMaxScore = spinner.createJSpinner(
                new SpinnerNumberModel(10, 0, 999, 1),
                RegEx.DECIMAL_3_INT_2_FLOAT,
                this::updateMaxPoints,
                Fonts.GO_REGULAR_26
        );
        this.maxScoreDocument = spinner.getDocument();

        // scrollPane
        this.scrollPane = new JScrollPane();
        this.scrollPane.getVerticalScrollBar().setUI(new BlackScrollBarUI());
        this.scrollPane.setBackground(Colors.DARK_GREY);
        this.scrollPane.setBorder(BorderFactory.createEmptyBorder());
        this.scrollPane.setViewportBorder(BorderFactory.createEmptyBorder());

        // table
        this.tableModel = new TableModel(this);
        this.table = new JTable(this.tableModel);

        // configure table
        this.table.setRowHeight(30);
        this.table.setDragEnabled(false);
        this.table.setDefaultRenderer(String.class, new StringCellsRenderer());

        // configure table header
        JTableHeader tableHeader = this.table.getTableHeader();
        tableHeader.setReorderingAllowed(false);
        tableHeader.setDefaultRenderer(new TableHeaderRenderer());

        // button
        BlackButton.Builder builder = new BlackButton.Builder("Imprimir")
                .normalFgColor(Color.WHITE)
                .hoverFgColor(Color.LIGHT_GRAY)
                .imageIcon(new ImageIcon(this.getClass().getResource("/resources/icons/outline_print_white_18dp.png")));
        this.buttonExportPDF = new BlackButton(builder);

        this.nCols = this.tableModel.getColumnCount();
        this.nRows = this.tableModel.getRowCount();

        this.changeTableHeaders(this.table.getTableHeader());
    }

    /**
     * Updates this.maxScore if not empty
     * Updates the constraints of the table model
     * Updates this.nCols & this.nRows
     * Updates the table view
     */
    private void updateMaxPoints() {
        String maxPointsStr;
        try {
            maxPointsStr = this.maxScoreDocument.getText(0, this.maxScoreDocument.getLength());
        } catch (BadLocationException e) {
            // TODO: handle exception, how?
            return;
        }
        if (maxPointsStr.isEmpty())
            return;

        this.maxScore = Math.round(Float.parseFloat(maxPointsStr));
        if (this.maxScore == 0)
            this.maxScore = 1;

        this.tableModel.updateConstraints();
        this.nRows = this.tableModel.getRowCount();
        this.nCols = this.tableModel.getColumnCount();
        this.tableModel.fireTableChanged(null);
        this.changeTableHeaders(this.table.getTableHeader());
    }

    /**
     * Changes the label for the tableHeader
     *
     * @param tableHeader the JTableHeader to modify
     */
    private void changeTableHeaders(JTableHeader tableHeader) {
        final TableColumnModel model = tableHeader.getColumnModel();
        final int nCols = model.getColumnCount();

        int i = 0;
        while (i < nCols)
            model.getColumn(i++).setHeaderValue(i % 2 == 0 ? "Calificación" : "Aciertos");

        tableHeader.repaint();
    }

    // Getters
    public int getMaxScore() {
        return maxScore;
    }

    private final class StringCellsRenderer extends JLabel implements TableCellRenderer {
        public StringCellsRenderer() {
            this.setFont(Fonts.GO_REGULAR_20);
            this.setForeground(Color.WHITE);
            this.setOpaque(true);
            this.setHorizontalAlignment(SwingConstants.CENTER);
            this.setHorizontalTextPosition(SwingConstants.CENTER);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            this.setText(String.valueOf(value));
            int factor = column;
            if (column % 2 != 0)
                factor = column / 2 * 2; // [column / 2] * 2, integer division
            int r = Math.round((float) row / TablesPanel.this.nRows * 100) + Math.round((float) factor / TablesPanel.this.nCols * 100);
            this.setBackground(new Color(r, r, r));
            return this;
        }
    }
}
