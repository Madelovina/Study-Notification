import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

// https://stackoverflow.com/questions/21753252/why-trayicon-doesnt-appear

public class Main extends Application {

    static String quizletURL;
    static Notification noti;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primary) {
        final Stage window = primary;
        window.initStyle(StageStyle.TRANSPARENT);
        window.setTitle("Study Notification");
        window.setWidth(248);
        window.setHeight(45);
        final Pane pane = new Pane();
        Scene scene = new Scene(pane);
        HBox c = new HBox();
        c.setPadding(new Insets(10, 10, 10, 10));

        HBox hb = new HBox();
        hb.setSpacing(25);
        TextField urlField = new TextField("URL of Quizlet");
        Button submit = new Button("Submit");
        hb.getChildren().addAll(urlField, submit);
        c.getChildren().addAll(hb);

        submit.setOnAction(actionEvent -> {
            quizletURL = urlField.getText();
            Platform.setImplicitExit(false);
            window.hide();
            noti = new Notification(quizletURL);
            noti.displayFactTray("'ello!");
        });

        pane.getChildren().addAll(c);
        window.setScene(scene);
        window.show();
    }
}
