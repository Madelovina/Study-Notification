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


public class Main extends Application {

    static String quizletURL;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primary) {
        final Stage window = primary;
        window.initStyle(StageStyle.TRANSPARENT);
        window.setTitle("Study Notification");
        window.setWidth(228);
        window.setHeight(25);
        final Pane pane = new Pane();
        Scene scene = new Scene(pane);
        pane.setPadding(new Insets(25, 25, 25, 25));

        HBox hb = new HBox();
        hb.setSpacing(25);
        TextField urlField = new TextField("URL of Quizlet");
        Button submit = new Button("Submit");
        hb.getChildren().addAll(urlField, submit);

        submit.setOnAction(actionEvent -> {
            quizletURL = urlField.getText();
            Platform.setImplicitExit(false);
            window.hide();
            System.out.println(quizletURL);
        });

        pane.getChildren().addAll(hb);
        window.setScene(scene);
        window.show();
    }
}
