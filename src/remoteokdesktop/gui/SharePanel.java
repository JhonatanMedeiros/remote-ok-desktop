package remoteokdesktop.gui;

import remoteokdesktop.model.RemoteOkJob;
import remoteokdesktop.service.EmailService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.prefs.Preferences;

import static java.util.Objects.nonNull;

public class SharePanel extends JPanel {

    private RemoteOkJob remoteOkJob;
    private String email;

    private SharePanel() {
        this.setBackground(new Color(102, 102,102,50));
        this.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
        this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                JPanel panel = ((JPanel) e.getSource());
                panel.setBackground(Color.WHITE);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                JPanel panel = ((JPanel) e.getSource());
                panel.setBackground(new Color(102, 102,102,50));

                JFrame frame = new JFrame("Enviar e-mail");
                String email = JOptionPane.showInputDialog(frame, "Compartilhar com quem? (E-mail)");
                
                if (!email.isEmpty()) {
                    new Thread(() -> EmailService.sendEmail(remoteOkJob, email, email)).start();
                }
            }
        });
    }

    public SharePanel(RemoteOkJob job, String email) {
        this();
        this.remoteOkJob = job;
        this.email = email;
    }
}
