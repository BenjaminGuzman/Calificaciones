package io.calificaciones.gui.panels;

import io.calificaciones.AppPanel;
import io.calificaciones.gui.constants.Colors;

import javax.swing.BorderFactory;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.event.HyperlinkEvent;
import java.awt.BorderLayout;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class AboutPanel extends AppPanel {
    private JPanel rootPanel;
    private JEditorPane messageEditorPane;

    public AboutPanel() {
        this.setLayout(new BorderLayout());
    }

    @Override
    public void initComponents() {
        this.add(this.rootPanel, BorderLayout.CENTER);

        URL messageURL = this.getClass().getResource("/resources/about.html");
        try {
            this.messageEditorPane.setPage(messageURL);
        } catch (IOException e) {
            e.printStackTrace();
            // TODO: handle exception
        }

        this.messageEditorPane.setEditable(false);
        this.messageEditorPane.addHyperlinkListener((HyperlinkEvent event) -> {
            if (event.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                if (Desktop.isDesktopSupported()) {
                    try {
                        Desktop.getDesktop().browse(event.getURL().toURI());
                    } catch (IOException e) {
                        e.printStackTrace();
                        // TODO: handle exception, how?
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                        // TODO: handle exception, how?
                    }
                }
            }
        });
    }

    private void createUIComponents() {
        this.messageEditorPane = new JEditorPane();
        this.messageEditorPane.setBorder(BorderFactory.createEmptyBorder());
        this.messageEditorPane.setBackground(Colors.DARK_GREY);
    }
}
