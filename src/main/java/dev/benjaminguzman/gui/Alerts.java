package dev.benjaminguzman.gui;

import dev.benjaminguzman.Main;

import javax.swing.*;

public final class Alerts {
	public static void showErrorOccurred(String message, String title) {
		// TODO IMPROVE ERROR MESSAGE
		JOptionPane.showConfirmDialog(
			Main.home,
			"Ha ocurrido un error, revise un archivo con el nombre \"calificaciones_#_#.log\" en el " +
                                "directorio temporal de su sistema para obtener más información\n\n"
				+ message,
			title,
			JOptionPane.YES_NO_OPTION,
			JOptionPane.ERROR_MESSAGE
		);
	}

	public static void showErrorOccurred(String message) {
		JOptionPane.showConfirmDialog(
			Main.home,
			"Ha ocurrido un error, revise un archivo con el nombre \"calificaciones_#_#.log\" en el " +
                                "directorio temporal de su sistema para obtener más información\n\n"
				+ message,
			"Ocurrió un error",
			JOptionPane.YES_NO_OPTION,
			JOptionPane.ERROR_MESSAGE
		);
	}

	public static void showErrorOccurred() {
		JOptionPane.showConfirmDialog(
			Main.home,
			"Ha ocurrido un error, revise un archivo con el nombre \"calificaciones_#_#.log\" en el " +
                                "directorio temporal de su sistema para obtener más información",
			"Ocurrió un error",
			JOptionPane.YES_NO_OPTION,
			JOptionPane.ERROR_MESSAGE
		);
	}
}
