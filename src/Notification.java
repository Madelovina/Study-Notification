import java.awt.*;

public class Notification {

    public void displayTray(String notiText) throws Exception {
        SystemTray tray = SystemTray.getSystemTray();
        Image image = Toolkit.getDefaultToolkit().createImage("icon.png");
        TrayIcon trayIcon = new TrayIcon(image, "Tray Demo");
        trayIcon.setImageAutoSize(true);
        trayIcon.setToolTip("Random Fact");
        tray.add(trayIcon);
        trayIcon.displayMessage("Random Fact", notiText, TrayIcon.MessageType.WARNING);
    }
}
