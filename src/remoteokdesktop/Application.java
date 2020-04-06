package remoteokdesktop;

import remoteokdesktop.gui.ListFrame;
import remoteokdesktop.gui.LoginFrame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class Application {

    public static void main(String[] args) {
        LoginFrame login = new LoginFrame();

        Runnable runner = () -> {
            if (SystemTray.isSupported()) {
                SystemTray tray = SystemTray.getSystemTray();
                Image image = Toolkit.getDefaultToolkit().getImage("imagem.jpeg");
                
                PopupMenu popup = new PopupMenu();
                

    
    //ação de executar
    ActionListener actionListener = new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
    }
    };
    //ação de sair
     ActionListener sairListener = new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
    System.exit(0);
    }
    };     
    //Opção executar
     MenuItem executarItem = new MenuItem("Abrir");
     executarItem.addActionListener(actionListener);
     popup.add(executarItem);
     TrayIcon trayIcon = new TrayIcon(image, "Remote OK", popup);
     trayIcon.setImageAutoSize(true);
    //Opção sair
     MenuItem sairItem = new MenuItem("Sair");
     sairItem.addActionListener(sairListener);
     popup.add(sairItem);               

            
                try {
                    tray.add(trayIcon);
                } catch (AWTException e) {
                    System.err.println("Não pode adicionar a tray");
                }
            } else {
                System.err.println("Tray indisponível");
            }
        };

        EventQueue.invokeLater(runner);

    }
}
