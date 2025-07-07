package es.jconca.gameab1;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;
import javafx.geometry.Pos;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;
import javafx.scene.input.KeyCode;

import java.util.stream.IntStream;

public class NumbersGame {
    private Stage stage;
    private Scene scene;
    private Label numberLabel;
    private Button nextButton, backButton;
    private ProgressBar progressBar;
    private int index = -1;
    private int[] numbers;

    public NumbersGame(Stage stage, Scene menuScene) {
        this.stage = stage;

        // Generar números dinámicamente (1..100 por ejemplo)
        numbers = IntStream.rangeClosed(1, 100).toArray();

        // Label y tarjeta
        numberLabel = new Label();
        numberLabel.setFont(new Font(240));
        numberLabel.setTextFill(Color.BLACK);

        StackPane card = new StackPane(numberLabel);
        card.setStyle("-fx-background-color: lightgreen; -fx-background-radius: 20px;"
                + "-fx-border-color: black; -fx-border-width: 3px; -fx-border-radius: 20px;");
        card.prefWidthProperty().bind(stage.widthProperty().multiply(0.6));
        card.maxWidthProperty().bind(stage.widthProperty().multiply(0.6));
        card.prefHeightProperty().bind(stage.heightProperty().multiply(0.6));
        card.maxHeightProperty().bind(stage.heightProperty().multiply(0.6));

        // ProgressBar
        progressBar = new ProgressBar(0);
        progressBar.setPrefWidth(300);

        // Botón Siguiente
        nextButton = new Button("Siguiente");
        nextButton.setFont(new Font(24));
        nextButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-background-radius: 30px;");
        nextButton.setOnAction(e -> advance(card));

        // Botón Menú
        backButton = new Button("Menú");
        backButton.setFont(new Font(24));
        backButton.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white; -fx-background-radius: 30px;");
        backButton.setOnAction(e -> stage.setScene(menuScene));

        // Layout
        VBox layout = new VBox(15, card, nextButton, backButton, progressBar);
        layout.setAlignment(Pos.CENTER);
        layout.setFillWidth(false);

        scene = new Scene(layout, 800, 600);

        // Teclas
        scene.setOnKeyPressed(evt -> {
            if (evt.getCode() == KeyCode.SPACE) {
                advance(card);
            } else if (evt.getCode() == KeyCode.ESCAPE) {
                stage.setScene(menuScene);
            }
        });

        // Primera tarjeta
        advance(card);
    }

    public Scene getScene() {
        return scene;
    }

    private void advance(StackPane card) {
        index = (index + 1) % numbers.length;
        int num = numbers[index];
        numberLabel.setText(String.valueOf(num));

        String[] colors = {"#ADFF2F","#87CEEB","#FFB6C1","#FFD700","#FFA07A"};
        card.setStyle("-fx-background-color: "+ colors[index % colors.length] +
                "; -fx-background-radius:20px; -fx-border-color:black; -fx-border-width:3px; -fx-border-radius:20px;");

        progressBar.setProgress((double)(index + 1) / numbers.length);
    }
}
