package remoteokdesktop.gui;

import lombok.Getter;
import lombok.Setter;
import net.miginfocom.swing.MigLayout;
import remoteokdesktop.model.RemoteOkJob;
import remoteokdesktop.util.RemoteOkUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static remoteokdesktop.util.ComponentUtils.getClosedHeartLabel;
import static remoteokdesktop.util.ComponentUtils.getOpenHeartLabel;

public class LikePanel extends JPanel {

    @Getter
    @Setter
    RemoteOkJob job;
    JPanel fatherPanel;

    public LikePanel() {
        this.setBackground(new Color(102, 102, 102, 50));
        this.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
        this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent ev) {
                JPanel panel = ((JPanel) ev.getSource());
                panel.setBackground(Color.WHITE);
            }

            @Override
            public void mouseReleased(MouseEvent ev) {
                JPanel panel = ((JPanel) ev.getSource());
                panel.setBackground(new Color(102, 102,102,50));

                String name = panel.getComponent(0).getName();
                panel.removeAll();
                if (name.equals("open")) {
                    RemoteOkUtils.likeJob(job);
                    JLabel label = new JLabel(getClosedHeartLabel());
                    panel.add(label);
                    label.setName("closed");
                } else {
                    RemoteOkUtils.dislikeJob(job);
                    JLabel label = new JLabel(getOpenHeartLabel());
                    panel.add(label);
                    label.setName("open");
                }
                panel.repaint();
                panel.revalidate();
                fatherPanel.repaint();
                fatherPanel.revalidate();
            }
        });
    }

    public LikePanel(RemoteOkJob job) {
        this();
        this.job = job;
    }

    public LikePanel(MigLayout migLayout, RemoteOkJob job, JPanel panel) {
        this(job);
        this.setLayout(migLayout);
        this.fatherPanel = panel;
    }

}
