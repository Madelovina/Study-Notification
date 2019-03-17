import java.awt.*;

public class Notification {

    private final Image image = Toolkit.getDefaultToolkit().getImage("quizlet.png");
    private SystemTray tray;
    private TrayIcon trayIcon;
    private QuizletGrabber quizlet;

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

    public void displayFactTray(String notiTerm, String notiDef) {
        try {
            trayIcon.displayMessage(notiTerm, notiDef, TrayIcon.MessageType.INFO);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void startRandomNotify(int numNoti) {
        if (quizlet == null) {
            try {
                trayIcon.displayMessage("Add a Quizlet!", "Don't mess with the source. ", TrayIcon.MessageType.WARNING);
            } catch (Exception ex) {
                System.out.println(ex);
            }
        } else
            for (int i = 0; i < numNoti; i++) {
                Term ph = quizlet.returnRandom();
                displayFactTray(ph.getTerm(), ph.getDef());
                try {
                    Thread.sleep((int) (60 * 1000 * Math.random()));
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
    }

    public void setQuizlet(QuizletGrabber quizlet) {
        this.quizlet = quizlet;
    }
}
