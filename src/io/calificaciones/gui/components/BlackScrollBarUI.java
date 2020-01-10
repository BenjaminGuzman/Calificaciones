package io.calificaciones.gui.components;

import io.calificaciones.gui.constants.Colors;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class BlackScrollBarUI extends BasicScrollBarUI {
    @Override
    protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
        g.setColor(Color.GRAY);
        g.fillRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height);
    }

    @Override
    protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
        g.setColor(Colors.PURPLE_900);
        g.fill3DRect(thumbBounds.x, thumbBounds.y, thumbBounds.width, thumbBounds.height, true);
    }

    @Override
    protected JButton createDecreaseButton(int orientation) {
        BlackButton.Builder builder = new BlackButton.Builder("▲")
                .normalFgColor(Colors.DEEP_PURPLE)
                .border(BorderFactory.createEmptyBorder());
        return new BlackButton(builder);
    }

    @Override
    protected JButton createIncreaseButton(int orientation) {
        BlackButton.Builder builder = new BlackButton.Builder("▼")
                .normalFgColor(Colors.DEEP_PURPLE)
                .border(BorderFactory.createEmptyBorder());
        return new BlackButton(builder);
    }

    @Override
    protected Rectangle getThumbBounds() {
        return super.getThumbBounds();
    }
}
