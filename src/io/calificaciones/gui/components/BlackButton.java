package io.calificaciones.gui.components;

import io.calificaciones.gui.constants.Colors;
import io.calificaciones.gui.constants.Fonts;

import javax.swing.BorderFactory;
import javax.swing.ButtonModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.Border;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class BlackButton extends JButton {
    private final Color normalBgColor, normalFgColor;
    private final Color hoverBgColor, hoverFgColor;
    private final Color pressedBgColor, pressedFgColor;

    public BlackButton(Builder builder) {
        this.normalBgColor = builder.normalBgColor;
        this.normalFgColor = builder.normalFgColor;
        this.hoverBgColor = builder.hoverBgColor;
        this.hoverFgColor = builder.hoverFgColor;
        this.pressedBgColor = builder.pressedBgColor;
        this.pressedFgColor = builder.pressedFgColor;

        super.setText(builder.text);
        super.setFont(builder.font);
        super.setForeground(builder.normalFgColor);
        super.setBackground(builder.normalBgColor);
        super.setBorder(builder.border);
        super.setContentAreaFilled(false);
        super.setFocusPainted(false);

        if (builder.imageIcon != null)
            super.setIcon(builder.imageIcon);
    }

    @Override
    protected void paintComponent(Graphics g) {
        ButtonModel model = this.getModel();
        Color fgColor;

        if (model.isRollover()) { // rollover = hover
            g.setColor(this.hoverBgColor);
            fgColor = this.hoverFgColor;
        } else if (model.isPressed()) {
            g.setColor(this.pressedBgColor);
            fgColor = this.pressedFgColor;
        } else {
            g.setColor(this.normalBgColor);
            fgColor = this.normalFgColor;
        }

        this.setForeground(fgColor);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        super.paintComponent(g);
    }

    public static final class Builder {
        private Color normalBgColor, normalFgColor;
        private Color hoverBgColor, hoverFgColor;
        private Color pressedBgColor, pressedFgColor;
        private ImageIcon imageIcon;
        private String text;
        private Border border;
        private boolean addClickEffect;
        private Font font;
        public Builder(String text) {
            this.text = text;
            this.normalBgColor = Color.BLACK;
            this.normalFgColor = Color.WHITE;
            this.hoverBgColor = Color.DARK_GRAY;
            this.hoverFgColor = Colors.DEEP_PURPLE;
            this.pressedBgColor = Color.WHITE;
            this.pressedFgColor = Color.BLACK;
            this.border = BorderFactory.createEmptyBorder(5, 5, 5, 5);
            this.addClickEffect = false;
            this.font = Fonts.GO_REGULAR_15;
        }

        public Builder text(String text) {
            this.text = text;
            return this;
        }
        public Builder clickEffect(boolean addClickEffect) {
            this.addClickEffect = addClickEffect;
            return this;
        }
        public Builder font(Font font) {
            this.font = font;
            return this;
        }
        public Builder normalBgColor(Color color) {
            this.normalBgColor = color;
            return this;
        }
        public Builder normalFgColor(Color color) {
            this.normalFgColor = color;
            return this;
        }

        public Builder hoverBgColor(Color color) {
            this.hoverBgColor = color;
            return this;
        }
        public Builder hoverFgColor(Color color) {
            this.hoverFgColor = color;
            return this;
        }

        public Builder pressedBgColor(Color color) {
            this.pressedBgColor = color;
            return this;
        }
        public Builder pressedFgColor(Color color) {
            this.pressedFgColor = color;
            return this;
        }
        public Builder border(Border border) {
            this.border = border;
            return this;
        }
        public Builder imageIcon(ImageIcon imageIcon) {
            this.imageIcon = imageIcon;
            return this;
        }
    }
}
