public class Main {
    public static void main(String[] args) {
        Notification noti = new Notification();
        try {
            noti.displayTray("Fact here");
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
