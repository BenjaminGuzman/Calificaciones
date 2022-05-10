package dev.benjaminguzman;

import dev.benjaminguzman.gui.App;

import javax.swing.*;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Main {
	public final static Logger LOGGER = Logger.getLogger(Main.class.getName());

	public static App home;

	public static void main(String[] args) {
		// system config
		System.setProperty("awt.useSystemAAFontSettings", "on");
		Main.setLogger();

		Main.setLookAndFeel();

		SwingUtilities.invokeLater(() -> {
			// load preferences
			App.loadPreferences();

			Main.home = new App();
			Main.home.initGUI();
		});
	}

	private static void setLookAndFeel() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException |
                         ClassNotFoundException e) {
			e.printStackTrace();
		}
        }

	private static void setLogger() {
		try {
			FileHandler fileHandler = new FileHandler("%t/calificaciones_%g_%u.log", true);
			fileHandler.setFormatter(new SimpleFormatter());
			LOGGER.addHandler(fileHandler);
		} catch (IOException e) {
			System.err.println("Something went wrong while opening log file");
		}
	}
}
