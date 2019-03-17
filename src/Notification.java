import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.awt.*;
import java.io.File;

public class Notification {

    private final Image image = Toolkit.getDefaultToolkit().getImage("assets\\quizlet.png");
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
                playSound();
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

    private void playSound() {
        try {
            File file = new File("assets\\notification1.mp3");
            Media hit = new Media(file.toURI().toURL().toExternalForm()
            );
            MediaPlayer mediaPlayer = new MediaPlayer(hit);
            mediaPlayer.play();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
