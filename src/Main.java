import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.HashMap;


// jar cfve client.jar Main Main.class MyClient.class Combo.class

// https://stackoverflow.com/questions/21753252/why-trayicon-doesnt-appear

public class Main extends Application {

    static String quizletURL;
    static Notification noti;
    private static HashMap<String, Integer> hm = new HashMap<>();
    private Image bgImg = new Image(this.getClass().getResource("resources/background.png").toString());

    public static void main(String[] args) {
        hm.put("Discord", 1);
        hm.put("Skype", 2);
        hm.put("Messenger", 3);
        hm.put("LoL MIA", 4);
        hm.put("Random", 5);
        launch(args);
    }

    @Override
    public void start(Stage primary) {
        final Stage window = primary;
        window.initStyle(StageStyle.TRANSPARENT);
        window.setTitle("Study Notification");
        window.setWidth(395);
        window.setHeight(80);
        final Pane pane = new Pane();
        pane.setBackground(new Background(new BackgroundImage(bgImg, BackgroundRepeat.REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT)));
        Scene scene = new Scene(pane);
        HBox c = new HBox();
        c.setPadding(new Insets(10, 10, 10, 10));
        c.setSpacing(10);

        VBox vb = new VBox();
        vb.setSpacing(10);
        TextField urlField = new TextField("URL of Quizlet");
        HBox hb = new HBox();
        hb.setMaxWidth(310);
        hb.setSpacing(5);
        TextField minTime = new TextField("Min Timeout");
        TextField maxTime = new TextField("Max Timeout");
        ObservableList<String> options =
                FXCollections.observableArrayList("Discord", "Skype", "Messenger", "LoL MIA", "Random");
        final ComboBox comboBox = new ComboBox(options);
        comboBox.setPrefWidth(175);
        hb.getChildren().addAll(minTime, maxTime, comboBox);
        vb.getChildren().addAll(urlField, hb);


        c.getChildren().addAll(vb);

        Button submit = new Button("Submit");
        c.getChildren().addAll(submit);

        submit.setOnAction(actionEvent -> {
            quizletURL = urlField.getText();
            Platform.setImplicitExit(false);
            window.hide();
            noti = new Notification(quizletURL, Integer.parseInt(minTime.getText()),
                    Integer.parseInt(maxTime.getText()), hm.get(comboBox.getValue().toString()));
            QuizletGrabber quizlet = new QuizletGrabber(quizletURL);
            noti.setQuizlet(quizlet);
            noti.startRandomNotify(Integer.MAX_VALUE);
        });

        pane.getChildren().addAll(c);

        submit.setPrefHeight(window.getHeight() - 20);
        window.setScene(scene);
        window.show();
    }
}
