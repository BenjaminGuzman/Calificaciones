package io.calificaciones.gui;

import io.calificaciones.AppPanel;
import io.calificaciones.gui.components.BlackButton;
import io.calificaciones.gui.constants.Colors;
import io.calificaciones.gui.panels.AboutPanel;
import io.calificaciones.gui.panels.IndexPanel;
import io.calificaciones.gui.panels.SettingsPanel;
import io.calificaciones.gui.panels.TablesPanel.TablesPanel;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.function.Supplier;

public class App extends JFrame {
    public final static NumberFormat SCORE_NUMBER_FORMAT = new DecimalFormat("###.##");

    public static int BASE_PERCENT = 10;
    public static float DEFAULT_SCORE = 20F;

    private static Preferences preferences = new Preferences();

    private JPanel rootPanel;
    private JButton menuButtonIndex;
    private JButton menuButtonTables;
    private JButton menuButtonSettings;
    private JButton menuButtonAbout;
    private JPanel mainPanel;

    private byte activePanelIndex = 0;
    private final AppPanel[] appPanels = new AppPanel[4];

    public void initGUI() {
        this.add(this.rootPanel);
        this.pack();

        Dimension initialWindowDimensions = new Dimension(700, 500);

        this.setMinimumSize(initialWindowDimensions);
        this.setMaximumSize(new Dimension(1000, 600));
        this.setPreferredSize(initialWindowDimensions);

        this.setTitle("Calificaciones");
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setIconImage(new ImageIcon(this.getClass().getResource("/resources/icons/grades.jpg")).getImage());

        // create IndexPanel after JFrame, at the end of this method it will be added to this.mainPanel
        AppPanel indexPanel = new IndexPanel();
        indexPanel.initComponents();

        // add listeners to components
        this.menuButtonIndex.addActionListener(this.createMenuBtnListener((byte) 0, IndexPanel::new));
        this.menuButtonTables.addActionListener(this.createMenuBtnListener((byte) 1, TablesPanel::new));
        this.menuButtonSettings.addActionListener(this.createMenuBtnListener((byte) 2, SettingsPanel::new));
        this.menuButtonAbout.addActionListener(this.createMenuBtnListener((byte) 3, AboutPanel::new));

        this.appPanels[0] = indexPanel;
        this.mainPanel.add(indexPanel, BorderLayout.CENTER);
    }

    private void createUIComponents() {
        BlackButton.Builder builder = new BlackButton.Builder("Inicio")
                .clickEffect(true)
                .normalFgColor(Color.LIGHT_GRAY).normalBgColor(Colors.PURPLE_DARK)
                .hoverFgColor(Color.WHITE).hoverBgColor(Colors.DEEP_PURPLE)
                .pressedBgColor(Color.BLACK).pressedFgColor(Color.WHITE)
                .imageIcon(new ImageIcon(this.getClass().getResource("/resources/icons/baseline_home_white_18dp.png")));
        this.menuButtonIndex = new BlackButton(builder);

        builder.text("Tablas")
                .imageIcon(new ImageIcon(this.getClass().getResource("/resources/icons/baseline_menu_white_18dp.png")));
        this.menuButtonTables = new BlackButton(builder);

        builder.text("Configuración")
                .imageIcon(new ImageIcon(this.getClass().getResource("/resources/icons/outline_settings_applications_white_18dp.png")));
        this.menuButtonSettings = new BlackButton(builder);

        builder.text("Acerca de")
                .imageIcon(new ImageIcon(this.getClass().getResource("/resources/icons/outline_help_outline_white_18dp.png")));
        this.menuButtonAbout = new BlackButton(builder);
    }

    private void changeMainPanel() {
        this.mainPanel.remove(0);

        this.mainPanel.add(this.appPanels[this.activePanelIndex], BorderLayout.CENTER);
        this.mainPanel.invalidate();
        this.mainPanel.validate();
        this.mainPanel.repaint();

        this.appPanels[this.activePanelIndex].afterInserted();
    }

    private ActionListener createMenuBtnListener(final byte desiredAppPanelIndex, final Supplier<AppPanel> appPanelConstructor) {
        return (ActionEvent actionEvent) -> {
            if (App.this.activePanelIndex == desiredAppPanelIndex)
                return;

            if (App.this.appPanels[desiredAppPanelIndex] == null) {
                App.this.appPanels[desiredAppPanelIndex] = appPanelConstructor.get();
                App.this.appPanels[desiredAppPanelIndex].initComponents();
            }

            App.this.activePanelIndex = desiredAppPanelIndex;
            App.this.changeMainPanel();
        };
    }

    public static void loadPreferences() {
        App.BASE_PERCENT = App.preferences.getBase();
        App.DEFAULT_SCORE = App.preferences.getDefaultScore();
    }
    public static void setBasePercent(int basePercent) {
        App.BASE_PERCENT = basePercent;
        App.preferences.updateBase(basePercent);
    }
    public static void setDefaultScore(float defaultScore) {
        App.DEFAULT_SCORE = defaultScore;
        App.preferences.updateDefaultScore(defaultScore);
    }
}
