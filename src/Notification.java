import java.awt.*;

public class Notification {

    private final Image image = Toolkit.getDefaultToolkit().getImage("quizlet.png");
    private final PopupMenu menu = new PopupMenu();
    private SystemTray tray;
    private TrayIcon trayIcon;

    public Notification(String url) {
        try {
            tray = SystemTray.getSystemTray();
            trayIcon = new TrayIcon(image, "Tray Demo");
            trayIcon.setImageAutoSize(true);
            trayIcon.setToolTip("Study Notification");
            tray.add(trayIcon);
            trayIcon.displayMessage("Quizlet URL", url, TrayIcon.MessageType.INFO);
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

    public void displayFactTray(String notiText) {
        try {
            trayIcon.displayMessage("Random Fact", notiText, TrayIcon.MessageType.INFO);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
