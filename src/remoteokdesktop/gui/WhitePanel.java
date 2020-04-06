package remoteokdesktop.gui;

import java.awt.Color;

import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

public class WhitePanel extends JPanel {
	
	public WhitePanel() {
		this.setBackground(Color.WHITE);
	}
	
	public WhitePanel(MigLayout layout) {
		this();
		super.setLayout(layout);
	}

}
