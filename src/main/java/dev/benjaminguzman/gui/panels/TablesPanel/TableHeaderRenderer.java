package dev.benjaminguzman.gui.panels.TablesPanel;

import dev.benjaminguzman.gui.constants.Colors;
import dev.benjaminguzman.gui.constants.Fonts;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class TableHeaderRenderer extends JLabel implements TableCellRenderer {
	public TableHeaderRenderer() {
		this.setFont(Fonts.GO_REGULAR_26);
		this.setOpaque(true);
		this.setForeground(Color.WHITE);
		this.setBackground(Colors.DARK_GREY);
		this.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		this.setHorizontalAlignment(SwingConstants.CENTER);
		this.setHorizontalTextPosition(SwingConstants.CENTER);
	}

	@Override
	public Component getTableCellRendererComponent(
                JTable jTable,
                Object value,
                boolean b,
                boolean b1,
                int i,
                int i1
        ) {
		this.setText(String.valueOf(value));
		return this;
	}
}
