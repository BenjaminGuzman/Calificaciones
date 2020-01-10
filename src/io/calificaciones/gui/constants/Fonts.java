package io.calificaciones.gui.constants;

import io.calificaciones.Main;
import io.calificaciones.gui.Alerts;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public final class Fonts {
    public static Font GO_REGULAR_26, GO_REGULAR_BOLD_70, GO_REGULAR_20, GO_REGULAR_15;

    static {
        final String GO_FONT_PATH = "fonts" + File.separatorChar + "Go-Regular.ttf";
        try (InputStream fontIS = Fonts.class.getResourceAsStream(GO_FONT_PATH)) {
            final Font GO_REGULAR = Font.createFont(Font.TRUETYPE_FONT, fontIS);
            Fonts.GO_REGULAR_15 = GO_REGULAR.deriveFont(15f);
            Fonts.GO_REGULAR_20 = GO_REGULAR.deriveFont(20f);
            Fonts.GO_REGULAR_26 = GO_REGULAR.deriveFont(26f);
            Fonts.GO_REGULAR_BOLD_70 = GO_REGULAR.deriveFont(Font.BOLD, 70f);
        } catch (FontFormatException | IOException e) {
            Main.LOGGER.severe("Error while trying to read Go fonts, GO_FONT_PATH: " + GO_FONT_PATH);
            Main.LOGGER.severe(e.toString());
            Main.LOGGER.severe(e.getMessage());
            Main.LOGGER.severe(e.getLocalizedMessage());
            Alerts.showErrorOccurred("Por favor instale la fuente \"Go\" en su sistema y no modifique el ejecutable");

            // fallback fonts
            Fonts.GO_REGULAR_15 = new Font(Font.SANS_SERIF, Font.PLAIN, 15);
            Fonts.GO_REGULAR_20 = new Font(Font.SANS_SERIF, Font.PLAIN, 20);
            Fonts.GO_REGULAR_26 = new Font(Font.SANS_SERIF, Font.PLAIN, 26);
            Fonts.GO_REGULAR_BOLD_70 = new Font(Font.SANS_SERIF, Font.BOLD, 26);
        }
    }
}
